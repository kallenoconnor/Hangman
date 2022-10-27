
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HangmanGameRun {


    public static void main(String[] args) {

        HangmanGameRun game = new HangmanGameRun();
        game.run();
    }

    private void run() {

        //create dictionary of words from which to choose
        List<String> dictionary = createDictionary();

        //randomly select a word
        int randomSelection = (int) (Math.random() * (dictionary.size() - 1));
        String selection = dictionary.get(randomSelection);

        //setup scanner to accept user input
        Scanner input = new Scanner(System.in);

        //initial game setup and output greeting
        int numberOfLives = 6;
        System.out.println("Want to play Hangman? You get " + numberOfLives + " tries to guess the word I'm thinking of!");
        System.out.println();
        System.out.println("I'm thinking of a word...");
        System.out.println();
        System.out.println("My word has " + selection.length() + " letters.");
        System.out.println();

        //create list to hold letters of the selection string
        List<String> lettersOfGuess = new ArrayList<>();
        for (int i = 0; i < selection.length(); i++) {
            lettersOfGuess.add("*");
        }
        String output = String.join("",lettersOfGuess);

        //begin loop to run until the user loses or wins
        while (numberOfLives > 0) {
            System.out.println("(Enter a letter to guess):");
            String userInput = "";

            //validate user input
            while (true) {
                userInput = input.nextLine().toLowerCase();
                try {
                    validateUserInput(userInput);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    System.out.println("(Enter a letter to guess):");
                }
            }
            //convert user string to char
            char guess = userInput.charAt(0);

            //count the number of times the guessed letter appears in the string
            int numberOfTimesLetterAppears = 0;
            for (int i = 0; i < selection.length(); i++) {
                if (selection.charAt(i) == guess) {
                    numberOfTimesLetterAppears++;
                }
            }
            //display number of results if letter is found in word
            if (selection.toLowerCase().contains(userInput.toLowerCase())) {
                System.out.println("My word contains " + numberOfTimesLetterAppears + " " + userInput.toUpperCase() + "(s).");

                //loop through the letters in the word to update the letter guessed from "*" to proper letter
                for (int i = 0; i < lettersOfGuess.size(); i++) {
                    if (selection.charAt(i) == guess) {
                        lettersOfGuess.remove(i);
                        lettersOfGuess.add(i, userInput);
                    }

                }
                //display word with correct letters
                output = String.join("",lettersOfGuess);
                System.out.println(output);

                //if all letters have been guessed successfully, break the loop
                if (output.equals(selection)) {
                    break;
                }


            } else {
                //if the word doesn't contain the guessed letter, inform the user and decrease life count
                System.out.println("My word doesn't contain that letter! You lose a life!");
                numberOfLives--;
                System.out.println(output);
                System.out.println("You have " + numberOfLives + " lives left!");
            }
            System.out.println("-----------------------------------");
            System.out.println();


        }

        //upon winning or losing, display the proper message to the user
        System.out.println("-----------------------------------");
        if (numberOfLives == 0) {
            System.out.println("You lose! I win!");
        } else {
            System.out.println("You win!");
        }
    }



    private List<String> createDictionary() {
        List<String> dictionary = new ArrayList<>();
        dictionary.add("Tiger");
        dictionary.add("meadow");
        dictionary.add("alpaca");
        dictionary.add("butterfly");
        dictionary.add("teaching");
        dictionary.add("moose");
        return dictionary;
    }

    private void validateUserInput(String input) throws IllegalArgumentException {
        if (input.length() > 1 || input.equals("")) {
            throw new IllegalArgumentException("***Input must be one character in length***");
        }
        if (isDigits(input)) {
            throw new IllegalArgumentException("***Input must not be numeric***");
        }
    }

    private boolean isDigits(String str) {
        for (char ch: str.toCharArray()) {
            if ((ch < '0') || (ch > '9')) {
                return false;
            }
        }
        return true;
    }
}

