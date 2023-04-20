package main.java.kallenprojects;


import Services.WordGeneratorAPI;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HangmanGameRun {


    public static void main(String[] args) {

        HangmanGameRun game = new HangmanGameRun();
        game.run();
    }

    private void run() {

        //randomly select a word using the API
        WordGeneratorAPI generator = new WordGeneratorAPI();
        String selection = generator.getRandomWord().substring(2,generator.getRandomWord().length()-2);

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

        //create list to hold guessed letters
        List<Character> lettersGuessed = new ArrayList<>();

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

            //place guessed letter in a list
            lettersGuessed.add(guess);

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

            System.out.println("You have guess the following letters:" + lettersGuessed);



        }

        //upon winning or losing, display the proper message to the user
        System.out.println("-----------------------------------");
        if (numberOfLives == 0) {
            System.out.println("You lose! I win! The word was \"" + selection + "\"!");
        } else {
            System.out.println("You win!");
        }
    }





    private void validateUserInput(String input) throws IllegalArgumentException {
        if (input.length() > 1 || input.equals("")) {
            throw new IllegalArgumentException("***Input must be one character in length***");
        }
        if (!isLetter(input)) {
            throw new IllegalArgumentException("***Input must not be number or symbol***");
        }
    }

    private boolean isLetter(String str) {
        for (char ch: str.toCharArray()) {
            if (Character.isDigit(ch) || !Character.isLetter(ch)) {
                return false;
            }
        }
        return true;
    }
}

