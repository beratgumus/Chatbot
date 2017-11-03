
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class SenticNet {
    private Scanner scanner;
    private HashMap<String, Double> data;

    public SenticNet() {

        String file = "..\\Chatbot\\src\\senticnet4-v2.txt";
        try {
            scanner = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        data = new HashMap<>();

        //put values from text to hashmap
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            if (!currentLine.contains("_")) {
                String parts[] = currentLine.split(" ");
                double val = Double.parseDouble(parts[1]);
                data.put(parts[0], val);
            }
        }

    }

    /**
     * Calculates review point of given string from SenticNet
     * @param tweetText text
     * @return calculated point
     */
    public double calculateReviewPoint(String tweetText) {
        Double sum = 0.0;
        tweetText = tweetText.replaceAll("[^a-zA-Z ]+","");
        String[] words = tweetText.split(" ");
        for (String word : words) {
            Double polarity = data.get(word);
            if (polarity != null) {
                sum += polarity;
                System.out.println(word + " : " + polarity);
            }
        }
        return sum;
    }

}