import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Contains code to generate a query set for load testing
 */
public class LoadTest {

    private static final int NUM_QUERIES = 100000;
    private static final int MAX_QUERY_LENGTH = 10;

    public static void main(String[] args) throws IOException {
        String inputFile = args[0];
        String outputFile = args[1];
        String URL = args[2];
        ArrayList<String> dictionary = getDictionary(inputFile);
        createTestFile(outputFile,dictionary, NUM_QUERIES, MAX_QUERY_LENGTH,URL);
    }

    private static void createTestFile(String outputFile, ArrayList<String> dictionary,Integer numQueries, int maxQueryLength, String URL)
            throws FileNotFoundException, UnsupportedEncodingException {
        Random random = new Random();
        PrintWriter writer = new PrintWriter(outputFile, "UTF-8");

        for(Integer i = 0; i < numQueries; i++){
            Integer randomQueryLength = random.nextInt(maxQueryLength) + 1;
            String query = getRandomQuery(dictionary,randomQueryLength,URL);
            writer.write(query+"\n");
        }
        writer.close();
    }

    private static String getRandomQuery(ArrayList<String> dictionary, Integer randomQueryLength,String URL) {
        Random random = new Random();
        String query = URL;
        for(Integer i = 0; i < randomQueryLength; i++){
            Integer randomDictionaryIndex = random.nextInt(dictionary.size()-1) + 1;
            query = query + dictionary.get(randomDictionaryIndex) + " ";
        }
        return query;

    }

    private static ArrayList<String> getDictionary(String inputFile) throws IOException {
        ArrayList<String> dictionary = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
        String word;
        while(null != (word = bufferedReader.readLine())){
                dictionary.add(word);
        }
        return dictionary;
    }

}