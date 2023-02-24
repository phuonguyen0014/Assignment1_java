import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        ArrayList<String> words = readWords("res/words.txt"); // step 4
        HashMap<String, Integer> wordCounter = buildHashMap(words); // step 5
        createHTMLFile(wordCounter); // step 6
        //Step 9: Populate the ArrayList with the data stored in the HashMap created in Step 5.
        ArrayList<WordFrequency> wordFrequencyList = new ArrayList<>();
        for(String key: wordCounter.keySet()) //  wordCounter.keySet() --> {incididunt, tempor,...}
        {
            // Constructor
            WordFrequency wordLists = new WordFrequency(key, wordCounter.get(key)); // wordCounter.get(key) --> {1, 3,...}
            wordFrequencyList.add(wordLists);
        }
        Collections.sort(wordFrequencyList);
        for (WordFrequency wordLists : wordFrequencyList)
        {
            System.out.println(wordLists.word + ": " +wordLists.count);

        }
        createSortedHTMLFile(wordFrequencyList);
    }

    // Step 4 - Read Input file
    private static ArrayList<String> readWords(String fileName) {
        //Specify the file name and path here
        File file = new File(fileName);
        ArrayList<String> wordList = new ArrayList<>();

        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader); // Read data from the file

            String line = bufferedReader.readLine(); // readLine() - Reads a line of text and returns it as a string
            while (line != null) {
                // parse the line into its words
                String[] words = line.split("[ .,]+");
                for (String word : words) {
                    if (word.trim().length() > 0) {
                        wordList.add(word.toLowerCase());
                    }
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close(); //Closes the input stream and flushes the buffer.

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return wordList;
    }

    // Step 5: Count Word Occurences
    private static HashMap<String, Integer> buildHashMap(ArrayList<String> words) {
        HashMap<String, Integer> wordCounter = new HashMap<>();
        for(String word: words) {
            Integer count = wordCounter.get(word);
            if (count == null)
            {
                wordCounter.put(word, 1);
            }
            else{
                wordCounter.put(word, count + 1);
            }
        }
        return wordCounter;
    }

    // Step 6: Create Output HTML File
    private static void createHTMLFile(HashMap<String, Integer> wordCounter) {
        File file = new File("res/words.html");

        try {
            FileWriter FileWriter = new FileWriter(file);
            StringBuilder builder = new StringBuilder();

            final String css = "<style>"
            + "td, th { border: solid} "
            + " table, td, th { border-collapse: collapse}"
            + "</style>";
            builder.append(css).append("\n");

            builder.append("<h1>Word Count</h1>");

            builder.append("<table>");
            for(String key: wordCounter.keySet()) {
                builder.append("<tr>");
                builder.append("<td>" + key + "</td>");
                builder.append("<td>" + wordCounter.get(key) + "</td>");
                builder.append("</tr>");
            }
        builder.append("</table>");
        FileWriter.append(builder.toString());
        FileWriter.close();    
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (String keyWord: wordCounter.keySet()) {
            System.out.println(keyWord + ": " + wordCounter.get(keyWord));
        }

    }

// Step 10: Create Output HTML File arranged in ascending wordcount order.
private static void createSortedHTMLFile(ArrayList<WordFrequency> wordFrequencyList) {
    File file = new File("res/sorted.html");

    try {
        FileWriter FileWriter = new FileWriter(file);
        StringBuilder builder = new StringBuilder();

        final String css = "<style>"
        + "td, th { border: solid} "
        + " table, td, th { border-collapse: collapse}"
        + "</style>";
        builder.append(css).append("\n");

        builder.append("<h1> Sorted Word Count</h1>");

        builder.append("<table>");
        for(WordFrequency wordLists : wordFrequencyList) {
            builder.append("<tr>");
            builder.append("<td>" + wordLists.getWord() + "</td>");
            builder.append("<td>" + wordLists.getCount() + "</td>");
            builder.append("</tr>");
        }
    builder.append("</table>");
    FileWriter.append(builder.toString());
    FileWriter.close();    
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

}

  
    

        
    }



    
