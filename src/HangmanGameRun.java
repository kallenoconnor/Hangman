import javax.swing.text.AttributeSet;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HangmanGameRun {




    public static void main(String[] args) {
            List<String> dictionary = new ArrayList<>();
            dictionary.add("Tiger");
            dictionary.add("meadow");
            dictionary.add("alpaca");
            dictionary.add("butterfly");


        int randomSelection = (int)(Math.random()*(dictionary.size() - 1));
        String selection = dictionary.get(randomSelection);
        Scanner input = new Scanner(System.in);
        System.out.println("Want to play Hangman? You get 6 tries to guess the word I'm thinking of!");
        System.out.println();
        System.out.println("I'm thinking of a word...");
        System.out.println();
        System.out.println("My word has " + selection.length() + " letters.");
        System.out.println();
        System.out.println("(Enter a letter to guess):");
        String userInput = input.nextLine();

        int numberOfTimesLetterAppears = 0;
        for (int i = 0; i < selection.length(); i++) {
            if (selection.charAt(i) == userInput.charAt(0)) {
                numberOfTimesLetterAppears++;
            }
        }

        if (selection.toLowerCase().contains(userInput.toLowerCase())) {
            System.out.println("My word contains " + numberOfTimesLetterAppears + " " + userInput.toUpperCase() + "(s).");
        } else {
            System.out.println("My word doesn't contain that letter! You lose a life!");
        }


    }
}
