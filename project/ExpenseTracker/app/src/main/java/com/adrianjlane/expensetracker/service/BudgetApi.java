package com.adrianjlane.expensetracker.service;

import com.adrianjlane.expensetracker.model.Category;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BudgetApi {

    private final String baseUrlString = "https://cit360budgetapi.azurewebsites.net/BudgetAPI_war";

    public List<Category> getAllCategories() {

        List<Category> categories = null;

        try {
            StringBuffer content = new StringBuffer();
            URL url = new URL(baseUrlString + "/test?command=getAllCategories");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();

            if (status == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
            }

            if (content.length() != 0) {
                Type listType = new TypeToken<List<Category>>() {}.getType();
                categories = new Gson().fromJson(content.toString(), listType);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }

    public boolean saveCategory(String body, String command) {

        try {
            StringBuffer content = new StringBuffer();
            URL url = new URL(baseUrlString + "/test?command=" + command);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/json");

            byte[] outputInBytes = body.getBytes("UTF-8");
            OutputStream os = con.getOutputStream();
            os.write( outputInBytes );
            os.close();

            int status = con.getResponseCode();
            System.out.println("save category status: " + status);


            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            System.out.println("response body" + content.toString());

            if (status != 200) {
                return false;
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
