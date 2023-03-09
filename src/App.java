import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        ArrayList<String> words = readWords("res/words.txt"); // step 4
        ArrayList<String> paragraphs = readParagraphs("res/paragraph.txt"); // step 14

        HashMap<String, Integer> wordCounter = buildHashMap(words); // step 5
        HashMap<String, Integer> paragraphCounter = buildParHashMap(paragraphs); // step 15

        createHTMLFile(wordCounter); // step 6
        createHTMLFile(paragraphCounter); // step 16

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

        // Step 18
        ArrayList<ParagraphFrequency> paragraphFrequencyList = new ArrayList<>();
        for(String key: paragraphCounter.keySet()) {
            ParagraphFrequency paragraphLists = new ParagraphFrequency(key, paragraphCounter.get(key));
            paragraphFrequencyList.add(paragraphLists);
        }
        Collections.sort(paragraphFrequencyList);
        for (ParagraphFrequency paragraphLists : paragraphFrequencyList)
        {
            System.out.println(paragraphLists.paragraph + ": " + paragraphLists.countPa);
        }
        createSortedPaHTMLFile(paragraphFrequencyList);
    }

    /**
     * Read input file words.txt, separate it into words and store it in an ArrayList
     * @param fileName
     * @return
     */
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

    /**
     * Create HashMap to store the number of occurrences of each word
     * @param words
     * @return
     */
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

    /**
     * Create HTML file that contains a table
     * @param wordCounter
     */
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

    /**
     * Create Output HTML File arranged in ascending wordcount order.
     * @param wordFrequencyList
     */
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

    /**
     * Read input file paragraph.txt, separate it into words and store it in an ArrayList
     * @param fileName
     * @return
     */
    // Step 14 - Read Input file
    private static ArrayList<String> readParagraphs(String fileName) {
        //Specify the file name and path here
        File newFile = new File(fileName);
        ArrayList<String> paragraphList = new ArrayList<>();

        try {
            FileReader readerParagraph = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(readerParagraph); // Read data from the file

            String line = bufferedReader.readLine(); // readLine() - Reads a line of text and returns it as a string
            while (line != null) {
                // parse the line into its words
                String[] paragraphs = line.split("[ .,]+");
                for (String paragraph : paragraphs) {
                    if (paragraph.trim().length() > 0) {
                        paragraphList.add(paragraph.toLowerCase());
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
        return paragraphList;
    }

    /**
     * Create HashMap to store the number of occurrences of each word
     * @param paragraphs
     * @return
     */
    // Step 15 – Count Word Occurrences
    private static HashMap<String, Integer> buildParHashMap(ArrayList<String> paragraphs) {
        HashMap<String, Integer> paragraphCounter = new HashMap<>();
        for(String paragraph: paragraphs) {
            Integer count = paragraphCounter.get(paragraph);
            if (count == null)
            {
                paragraphCounter.put(paragraph, 1);
            }
            else {
                paragraphCounter.put(paragraph, count + 1);
            }        
        }
        return paragraphCounter;
    }

    /**
     * Create HTML file that contains a table
     * @param paragraphCounter
     */
    // Step 16 – Create Output HTML File
    private static void createHTMLFileParagraph(HashMap<String, Integer> paragraphCounter) {
        File file = new File("res/paragraphs.html");

        try {
            FileWriter FileWriter = new FileWriter(file);
            StringBuilder builder = new StringBuilder();

            final String css = "<style>"
            + "td, th { border: solid} "
            + " table, td, th { border-collapse: collapse}"
            + "</style>";
            builder.append(css).append("\n");

            builder.append("<h1>Paragraph Count</h1>");

            builder.append("<table>");
            for(String key: paragraphCounter.keySet()) {
                builder.append("<tr>");
                builder.append("<td>" + key + "</td>");
                builder.append("<td>" + paragraphCounter.get(key) + "</td>");
                builder.append("</tr>");
            }
            builder.append("</table>");
            FileWriter.append(builder.toString());
            FileWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (String keyWord: paragraphCounter.keySet()) {
            System.out.println(keyWord + ": " + paragraphCounter.get(keyWord));
        
        }
    }

    /**
     * Create Output HTML File arranged in ascending wordcount order.
     * @param paragraphFrequencyList
     */
     // Step 20: Create Output HTML File arranged in ascending paragraphcount order.
     private static void createSortedPaHTMLFile(ArrayList<ParagraphFrequency> paragraphFrequencyList) {
        File file = new File("res/sortedParagraphWords.html");
    
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
            for(ParagraphFrequency paragraphLists : paragraphFrequencyList) {
                builder.append("<tr>");
                builder.append("<td>" + paragraphLists.getParagraph() + "</td>");
                builder.append("<td>" + paragraphLists.getCountP() + "</td>");
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

    
