package collections;

import java.text.DecimalFormat;
import java.util.*;

public final class CollectionExercises {

    private static CollectionExercises collectionExercisesInstance = null;

    long startTime;
    long endTime;
    long duration;
    Random random = new Random();
    Input input = Input.getInstance();
    DecimalFormat formatter = new DecimalFormat("#,###");

    private CollectionExercises() {
    }

    public static CollectionExercises getInstance() {
        if (collectionExercisesInstance == null) {
            collectionExercisesInstance = new CollectionExercises();
        }
        return collectionExercisesInstance;
    }

    public void listExercise() {
        System.out.println("Reading a large list of unique words into an ArrayList...");
        List<String> wordList = readFileIntoArray("resources/words_alpha.txt");

        // Search for a word in the list and track time to find it
        int wordIndex = -1;
        startTime = System.currentTimeMillis();
        wordIndex = wordList.indexOf(wordList.get(random.nextInt(wordList.size() - 1)));
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        if (wordIndex >= 0) {
            System.out.println("Time (ms) to find " + wordList.get(wordIndex) + " ArrayList: " + duration);
        }
        System.out.println("Five random items from the list are: ");
        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(wordList.size() - 1);
            System.out.println(wordList.get(randomIndex));
        }
    }

    public void setExercise() {
        System.out.println("Reading the Gutenberg published edition of 'Pride and Prejudice' into an ArrayList...");
        List<String> wordList = readFileIntoArray("resources/prideandprejudice.txt");

        // Read the word list into a set
        Set<String> uniqueWordList = readListIntoSet(wordList);
        // Use the set as a unique list to inspect the array with all the duplicates
        System.out.println("Let's see how many words are exactly 17 letters long in the book...");
        Iterator<String> it = uniqueWordList.iterator();
        int i = 0;
        while (it.hasNext()) {
            String word = it.next();
            if (word.length() == 17) {
                System.out.println("Number of letters in '" + word + "': " + word.length());
            }
        }
        System.out.println("\nI think there were some issues with my source material, oh well! It's pretty obvious" +
                           "\na set would dp well spell-checking a large list of words with duplicates.");
    }

    public void mapExercise() {
        System.out.println("Reading the Gutenberg published edition of 'Pride and Prejudice' into an ArrayList...");
        List<String> wordList = readFileIntoArray("resources/prideandprejudice.txt");

        // Read the word list into a set
        Set<String> uniqueWordList = readListIntoSet(wordList);

        // Create a map using the word frequency as the key, and list of words as the value
        System.out.println("Creating a Map of K (Frequencies) and V (Words)...");
        startTime = System.currentTimeMillis();
        Map<Integer, List<String>> wordsByFrequency = new HashMap<>();

        for (String word : uniqueWordList) {
            int frequency = Collections.frequency(wordList, word);
            // If the key already exists, add the word to the list
            if (wordsByFrequency.containsKey(frequency)) {
                List<String> valueList = wordsByFrequency.get(frequency);
                valueList.add(word);
            }
            // If the key doesn't exist, create the list, add the word, and add the pair to the map
            else {
                List<String> valueList = new ArrayList<>();
                valueList.add(word);
                wordsByFrequency.put(frequency, valueList);
            }
        }
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println("Time (ms) to create a Map from the Set and ArrayList: " + formatter.format(duration));

        // Map stats
        System.out.println("\nUnique keys in the map: " + wordsByFrequency.size());

        // largest key
        int largestKey = 0;
        for (Integer mKey : wordsByFrequency.keySet()) {
            if (mKey > largestKey) {
                largestKey = mKey;
            }
        }
        System.out.println("Largest key (# repeats for most repeated words): " + largestKey);

        // largest value
        int largestValue = 0;
        for (List<String> mValue : wordsByFrequency.values()) {
            if (mValue.size() > largestValue) {
                largestValue = mValue.size();
            }
        }
        System.out.println("Largest value (Largest list of words with the same # of repeats): " + largestValue + "\n");

        // Check the map for some frequencies
        System.out.println("Let's look at words that appear between 10 and 12 times...");
        for (int i = 10; i < 13; i++) {
            if (wordsByFrequency.containsKey(i)) {
                System.out.println("Some Words that repeat " + i + " times:");
                List<String> valueList = wordsByFrequency.get(i);
                int listSize = valueList.size();
                // let's display a maximum of 10 words
                if (listSize > 10) {
                    listSize = 10;
                }
                for (int j = 0; j < listSize; j++) {
                    if (j == listSize - 1) {
                        System.out.print(valueList.get(j) + "\n");
                    }
                    else {
                        System.out.print(valueList.get(j) + ", ");
                    }
                }
            }
        }
    }

    public void queueExercise() {
        Deque<String> waitingList = new ArrayDeque<>();
        waitingList.add("Billy");
        waitingList.add("Suzie");
        waitingList.add("Joe");
        waitingList.add("Kyle");
        waitingList.add("George");
        waitingList.add("Lorne");

        System.out.println("Welcome to Urgent Care! We'll take your name and call you when it's your turn.");
        System.out.println("People in line currently: " + waitingList.size());
        String name;
        name = input.getString("Name: ");
        waitingList.add(name);

        for (String patient : waitingList) {
            if (patient == name) {
                System.out.println("Hold, we have a critical patient we need to see right a way!");
                waitingList.addFirst("Super Secret VIP");
            }
            System.out.print("Now serving: " + waitingList.getFirst() + ", ");
            waitingList.remove();
            System.out.print("next in line: " + waitingList.getFirst() + "\n");
            try {
                Thread.sleep(2000);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void treeExercise() {
        System.out.println("Reading a very large, mostly unique list into an ArrayList...");
        List<String> wordList = readFileIntoArray("resources/1million.txt");

        // Populate the tree
        System.out.println("populating a TreeSet from the ArrayList...");
        startTime = System.currentTimeMillis();
        TreeSet<String> tree = new TreeSet<>(wordList);
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println("Time (ms) to read " + formatter.format(wordList.size()) + " items into a TreeSet: " +
                           duration);

        // Search the tree
        System.out.println("\nSearching the tree for each item from the ArrayList...");
        startTime = System.currentTimeMillis();
        for (String word : wordList) {
            tree.contains(word);
        }
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println("Time (ms) to find all " + formatter.format(wordList.size()) + " items in the TreeSet: " +
                duration);

        // Find the largest element in the tree
        System.out.println("\nGenerate a new tree from all words less than 'laviolette'...");
        startTime = System.currentTimeMillis();
        Set<String> newTree = new TreeSet<>();
        newTree = (TreeSet<String>)tree.headSet("laviolette");
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println("Time (ms) to create subtree of " + formatter.format(newTree.size()) + " items: " +
                duration);

    }

    // Read the words into an ArrayList and track time in milliseconds
    private List<String> readFileIntoArray(String fileName) {
        startTime = System.currentTimeMillis();
        List<String> wordList = input.getFileContents(fileName);
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println("Time (ms) to read " + formatter.format(wordList.size()) + " items into an ArrayList: " +
                           duration);
        return wordList;
    }

    // Read the word list into a set
    private Set<String> readListIntoSet(List<String> list) {
        startTime = System.currentTimeMillis();
        Set<String> uniqueWordList = new LinkedHashSet<>(list);
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println("Time (ms) to create a Set from the ArrayList: " + duration);
        System.out.println("The unique word count in this edition of 'Pride and Prejudice' is: " +
                formatter.format(uniqueWordList.size()));
        return uniqueWordList;
    }
}
