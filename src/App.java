import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        ArrayList<String> words = readWords("res/words.txt"); // step 4
        HashMap<String, Integer> wordCounter = buildHashMap(words); // step 5
        createHTMLFile(wordCounter); // step 6
    }

    // Step 4 - Read Input file
    private static ArrayList<String> readWords(String fileName) {
        File file = new File(fileName);
        ArrayList<String> wordList = new ArrayList<>();

        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line = bufferedReader.readLine();
            while (line != null) {
                String[] words = line.split("[ .,]+");
                for (String word : words) {
                    if (word.trim().length() > 0) {
                        wordList.add(word.toLowerCase());
                    }
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();

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
}