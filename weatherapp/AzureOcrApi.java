package com.adrianjlane.snapnspell.services;

import android.graphics.Bitmap;
import android.net.Uri;

import com.adrianjlane.snapnspell.models.ImageManager;
import com.adrianjlane.snapnspell.models.OcrResponseLine;
import com.adrianjlane.snapnspell.models.OcrResponseObj;
import com.adrianjlane.snapnspell.models.OcrResponseRegion;
import com.adrianjlane.snapnspell.models.OcrResponseWord;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AzureOcrApi {
    private static final String subscriptionKey = "<REDACTED>";
    private static final String uriBase = "centralus.api.cognitive.microsoft.com";

    public static ArrayList<String> getWordListFromImage(String imageFilePath) {

        try {
            // Build the URL for the API call
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("https")
                    .authority(uriBase)
                    .appendPath("vision")
                    .appendPath("v2.0")
                    .appendPath("ocr")
                    //.appendQueryParameter("visualFeatures", "Categories,Description,Color")
                    .appendQueryParameter("language", "en");

            URL apiUrl = new URL(builder.build().toString());

            // Build the request
            HttpURLConnection request = (HttpURLConnection) apiUrl.openConnection();
            request.setRequestProperty("Content-Type", "application/octet-stream");
            request.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);
            request.setRequestMethod("POST");
            request.setDoInput(true);
            request.setDoOutput(true);

            // Upload the image
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            Bitmap bitmap = ImageManager.getBitmapFromFile(imageFilePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteStream);
            byteStream.close();

            request.getOutputStream().write(byteStream.toByteArray());
            request.getOutputStream().close();

            // Check response code
            int responseCode = request.getResponseCode();

            if (responseCode != 200) {
                System.out.println("Upload failed: response was " + responseCode);
                return null;
            }

            // Get the response
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();

            String responseStr = sb.toString();

            //Generate array list to return
            ArrayList<String> wordList = getWordsFromResponse(responseStr);

            return wordList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    private static ArrayList<String> getWordsFromResponse(String jsonString) {

        ArrayList<String> wordList = new ArrayList<>();

        Gson gson = new Gson();
        OcrResponseObj ocrResponseObj = gson.fromJson(jsonString, OcrResponseObj.class);

        // Grab all words from the array and format them as best as possible for a spelling test
        List<OcrResponseRegion> regions = ocrResponseObj.getRegions();
        for (OcrResponseRegion region : regions) {
            List<OcrResponseLine> lines = region.getLines();
            for (OcrResponseLine line : lines) {
                List<OcrResponseWord> words = line.getWords();
                for (OcrResponseWord word : words) {
                    String formattedWord = word
                            .getText()
                            .toLowerCase()
                            // Regex removes all punctuation except apostrophes in words
                            .replaceAll("[\\p{Punct}&&[^']]|(?<![a-zA-Z])'|'(?![a-zA-Z])",
                                    "");
                    // Don't add duplicates, if I have time, I switch to a set
                    if (!wordList.contains(formattedWord)) {
                        wordList.add(formattedWord);
                    }
                }
            }
        }

        if (wordList.size() > 0) {
            return wordList;
        }
        else {
            return null;
        }
    }
}
