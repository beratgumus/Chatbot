
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class SenticNet {
    private Scanner scanner;

    public SenticNet() {

        String file = "..\\Chatbot\\src\\test.txt";
        try {
            scanner = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public double calculateReviewPoint(String tweetText) {
        Double sum = 0.0;
        String sentence = tweetText;
        ArrayList<String> vocab = new ArrayList<>();
        String[] sp = sentence.split(" ");
        for (int i = 0; i < sp.length; i++) {
            vocab.add(sp[i]);
        }
        for (int i = 0; i < vocab.size(); i++) {
            Double polarity = getPolarity(vocab.get(i));
            sum += polarity;
            System.out.println(vocab.get(i) + " : " + polarity);
        }

        return sum;
    }

    public double getPolarity(String s) {
        double reviewValue = 0.00;
        boolean found = false;
        while (scanner.hasNextLine() && !found) {
            String currentLine = scanner.nextLine();
            if (currentLine.contains(s)) {
                reviewValue += Double.parseDouble(currentLine.split(" ")[1]);
                found = true;
            }
        }
        return reviewValue;
    }
}