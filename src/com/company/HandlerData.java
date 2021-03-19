package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class HandlerData {

    //Initializing global objects - Scanner, LittlemonData
    Scanner scanN = new Scanner(System.in);
    Scanner scanS = new Scanner(System.in);

    LittlemonData[] littlemonTeam = new LittlemonData[6];

    //Declaring global variables
    public static int playerPrompt;

    //Declaring instance variables
    int littlemonCaught = 0, numberOfLittlemonSeen = 0, moneyEarned = 0, savedItem3, savedItem4, savedMoney;
    String gender, name, rival;
    String[] littlemonSeen = new String[12];


    /**********************
     * Method Name: HandlerData
     * A constructor which initializes new objects from HandlerData (or this) class
     * @param name - Name of the Handler
     **********************/
    public HandlerData(String name) {

        this.name = name;

    }

    /**********************
     * Method Name: setPlayerGender
     * A mutator which sets the gender of the Player
     **********************/
    public void setPlayerGender() {

        //Gender Selection
        do {

            do {

                //Boy or Girl
                System.out.println("\nTimber: Are you a boy or are you a girl? Won't you please tell me?");
                System.out.println("1: I'm a boy.");
                System.out.println("2: I'm a girl.");
                playerPrompt = scanN.nextInt();

                if (playerPrompt == 1) {
                    gender = "boy";
                } else if (playerPrompt == 2) {
                    gender = "girl";
                }

            } while (playerPrompt < 1 || playerPrompt > 2);

            //Gender Confirmation
            do {

                System.out.println("\nTimber: So, you're a " + gender + " then?");
                System.out.println("1: Yes.");
                System.out.println("2: No.");
                playerPrompt = scanN.nextInt();

            } while (playerPrompt < 1 || playerPrompt > 2);

        } while (playerPrompt == 2);

    }

    /**********************
     * Method Name: setPlayerName
     * A mutator which sets the name of the Player
     **********************/
    public void setPlayerName() {

        //Name Selection
        do {

            //Check Name with Scientist's Name
            if (name.equalsIgnoreCase("Timber") && playerPrompt == 1) {
                System.out.println("\nTimber: Erm... sorry, you can't have my name...");
            }

            //Check Name Length
            if (name.length() > 15) {
                System.out.println("\nSorry that name is too long. Please try again. (Max: 15)");
            }

            System.out.println("\nTimber: Please tell me your name.");
            System.out.print("My name is: ");
            name = scanS.nextLine();

            //Name Confirmation
            do {

                if (name.equalsIgnoreCase("") && gender.equalsIgnoreCase("boy")) {
                    name = "Maroon";
                } else if (name.equalsIgnoreCase("") && gender.equalsIgnoreCase("girl")) {
                    name = "Jade";
                }
                System.out.println("\nTimber: Your name is " + name + "?");
                System.out.println("1: Yes.");
                System.out.println("2: No.");
                playerPrompt = scanN.nextInt();

            } while (playerPrompt < 1 || playerPrompt > 2);

        } while (playerPrompt == 2 || name.equalsIgnoreCase("Timber") || name.length() > 15);

    }

    /**********************
     * Method Name: setRivalName
     * A mutator which sets the name of the Rival
     **********************/
    public void setRivalName() {

        //Rival Name Selection
        do {

            //Check Name with Scientist's Name
            if (name.equalsIgnoreCase("Timber") && playerPrompt == 1) {
                System.out.println("\nTimber: Erm... no, I'm pretty sure I didn't name him with the same exact name I " +
                        "have...");
            }

            //Check Name Length
            if (name.length() > 15) {
                System.out.println("\nSorry that name is too long. Please try again. (Max: 15)");
            }

            System.out.println("\nTimber: Erm, what was his name now?");
            System.out.print("My rival's name is: ");
            name = scanS.nextLine();

            //Rival Name Confirmation
            do {

                if (name.equalsIgnoreCase("")) {
                    name = "Cyan";
                }

                rival = name;
                System.out.println("\nTimber: ...Er, was it " + name + "?");
                System.out.println("1: Yes.");
                System.out.println("2: No.");
                playerPrompt = scanN.nextInt();

            } while (playerPrompt < 1 || playerPrompt > 2);

        } while (playerPrompt == 2 || name.equalsIgnoreCase("Timber") || name.length() > 15);

    }

    /**********************
     * Method Name: addToLittledex
     * Adds newly seen Littlemon to a list that goes to the Littledex
     * @param littlemon - The Littlemon seen
     **********************/
    public void addToLittledex(String littlemon) {

        //Add Littlemon to littlemonSeen array
        littlemonSeen[numberOfLittlemonSeen] = littlemon;
        numberOfLittlemonSeen++;

    }

    /**********************
     * Method Name: littledex
     * Prints the Littledex from the navigation method
     * @throws InterruptedException - for thread.sleep
     **********************/
    public void littledex() throws  InterruptedException {

        //Declaring variables within littledex method
        String[] name = {"#001 Herbasaur", "#004 Scorchmander", "#007 Spurtle", "#010 Heimlich", "#013 Beedle",
                "#016 Mordecai", "#019 Ratatouille", "#025 Kipachu", "#074 Rocky", "#095 OnixpectedToken", "#133 Eve",
        "#??? Techkey"};
        String[] type = {"Seed", "Lizard", "Tiny Turtle", "Worm", "Hairy Bug", "Tiny Bird", "Mouse", "Mouse", "Rock",
                "Rock Snake", "Evolution", "Key"};
        String[] description = {"There is a plant seed on its back right from the day this Littlemon is born. The seed " +
                "slowly grows larger.", "From the time it is born, a flame burns at the tip of its tail. Its life " +
                "would end if the flame were to go out.", "When it retracts its long neck into its shell, it squirts " +
                "out water with vigorous force.", "It is covered with a green skin. When it grows, it sheds the skin, " +
                "covers itself with silk, and becomes a cocoon.", "Often found in forests and grasslands. It has a " +
                "sharp, toxic barb of around two inches on top of its head.", "Does not like to fight. It hides in " +
                "tall grass and so on, foraging for food such as small bugs.", "Its fangs are long and very sharp. " +
                "They grow continuously, so it gnaws on hard things to whittle them down.", "It has small electric " +
                "sacs on both its cheeks. If threatened, it looses electric charges from the sacs.", "Its round form " +
                "makes it easy to pick up. Some people have used them to hurl at each other in a snowball fight.", "It " +
                "usually lives underground. It searches for food while boring its way through the ground at 50 miles " +
                "per hour.", "An extremely rare Littlemon that may evolve in a number of different ways depending on " +
                "stimuli.", "A mysterious Littlemon... Rumored to be lingering in the depths of the JV Theatre..."};

        //Open Littledex
        System.out.println("\n*beep!*");
        Thread.sleep(1500);

        do {

            //Print Littledex based on littlemonSeen array
            System.out.println("\n\t\t\t\t\t\t\tThe Onto Region Littledex" + "\n");

            //If elements in littlemonSeen array matches with name array, print that Littlemon information
            for (int i = 0; i<name.length; i++) {
                if (Arrays.asList(littlemonSeen).contains(name[i].substring(5))) {
                    System.out.format("\n%-20s %5s", name[i], "The " + type[i] + " Littlemon\n");
                    System.out.println(description[i] + "\n");
                }
            }

            //Return to main class
            System.out.println("\n(Type '0' to return.)");
            playerPrompt = scanN.nextInt();

        } while (playerPrompt != 0);

    }

    /**********************
     * Method Name: map
     * Prints the map from the navigation method
     * @param map - The reference grid/2D Array of the map
     * @param playerPositionRow - The x position of the player based on the 2D Array of map
     * @param playerPositionColumn - The y position of the player based on the 2D Array of map
     * @throws InterruptedException - for thread.sleep
     **********************/
    public void map(String[][] map, String mapName, int playerPositionRow, int playerPositionColumn) throws
            InterruptedException {

        //Declaring variables within map method
        String playerPosition = "YOU", currentLocation;

        //Open Map
        System.out.println("\nYou opened the map of " + mapName + ".");
        Thread.sleep(1500);

        //Get player position
        currentLocation = map[playerPositionRow][playerPositionColumn];
        map[playerPositionRow][playerPositionColumn] = playerPosition;

        do {

            //Print map based on 2D array of map provided
            System.out.println("\n\t\t\t\t\t\t\t" + mapName + "\n");

            for (String[] x : map) {
                for (String y : x) {
                    System.out.format("%-10s", y);
                }
                System.out.println("\n");
            }

            //Print description depending on map
            switch (mapName) {
                case "Tallet Town" :
                    System.out.println("\"Shades of Your Journey Await!\"");
                    System.out.println("\nA fairly new and quiet town. It's a small and pretty place.");
                    break;
                case "Route 1" :
                    System.out.println("\nA country road full of greenery and rough paths.");
                    break;
                case "Verdant City" :
                    System.out.println("\"The City of Evergreen\"");
                    System.out.println("\nA beautiful city that is enveloped in green year-round.");
                    break;
            }

            //Return to main class
            System.out.println("\n(Type '0' to return.)");
            playerPrompt = scanN.nextInt();

        } while (playerPrompt != 0);

        //Reset current location name on grid
        map[playerPositionRow][playerPositionColumn] = currentLocation;

    }

    /**********************
     * Method Name: setLittlemon
     * A mutator which sets Littlemon in the Handler's party
     * @param littlemon - The Littlemon to be added
     **********************/
    public void setLittlemon(String littlemon) throws InterruptedException {

        //If number of Littlemon caught exceeds party size
        if (littlemonCaught > 6) {

            System.out.println("\n=========================================" +
                    "==========");
            System.out.println("\t" + littlemon + " has been sent to Box 1.");
            System.out.println("===========================================" +
                    "========");
            Thread.sleep(1500);

        } else {

            //Initialize new Littlemon objects for Handlers
            switch (littlemon) {

                case "Herbasaur":
                    this.littlemonTeam[littlemonCaught] = new LittlemonData("Herbasaur");
                    break;

                case "Scorchmander":
                    this.littlemonTeam[littlemonCaught] = new LittlemonData("Scorchmander");
                    break;

                case "Spurtle":
                    this.littlemonTeam[littlemonCaught] = new LittlemonData("Spurtle");
                    break;

                case "Kipachu":
                    this.littlemonTeam[littlemonCaught] = new LittlemonData("Kipachu");
                    break;

                case "Eve":
                    this.littlemonTeam[littlemonCaught] = new LittlemonData("Eve");
                    break;

                case "Ratatouille":
                    this.littlemonTeam[littlemonCaught] = new LittlemonData("Ratatouille");
                    break;

                case "Mordecai":
                    this.littlemonTeam[littlemonCaught] = new LittlemonData("Mordecai");
                    break;

                case "Techkey":
                    this.littlemonTeam[littlemonCaught] = new LittlemonData("Techkey");
                    break;

                case "Heimlich":
                    this.littlemonTeam[littlemonCaught] = new LittlemonData("Heimlich");
                    break;

                case "Beedle":
                    this.littlemonTeam[littlemonCaught] = new LittlemonData("Beedle");
                    break;

                case "Rocky":
                    this.littlemonTeam[littlemonCaught] = new LittlemonData("Rocky");
                    break;

                case "Onixpected":
                    this.littlemonTeam[littlemonCaught] = new LittlemonData("Onixpected");
                    break;


            }

            //Set Littlemon information of Littlemon object selected through LittlemonData class
            littlemonTeam[littlemonCaught].setGender();
            littlemonTeam[littlemonCaught].setLevel(name);
            littlemonTeam[littlemonCaught].setStats();
            littlemonTeam[littlemonCaught].setMoves();
            littlemonTeam[littlemonCaught].heal();

            //Increase party of Handler
            littlemonCaught++;

        }

    }

    /**********************
     * Method Name: defeat
     * What the Handler says when defeated in battle
     **********************/
    public void defeat() throws InterruptedException {

        if (name.equalsIgnoreCase(rival) || (name.equalsIgnoreCase("Cyan"))) {
            //Rival's quote
            System.out.println("\n" + name + ": WHAT? Unbelievable!");
            Thread.sleep(1500);
            System.out.println("\n" + name + ": I picked the wrong Littlemon!");
            Thread.sleep(1500);

        } else {

            //Other Handlers' quotes
            switch (name) {

                case "JoseJesus" :
                    System.out.println("\n" + name + ": Too strong! Too strong!");
                    Thread.sleep(1500);
                    break;

                case "JimJam" :
                    System.out.println("\n" + name + ": Oh, boo! I can't win!");
                    Thread.sleep(1500);
                    break;

                case "MatPat" :
                    System.out.println("\n" + name + ": You beat me?! You've got talent!");
                    Thread.sleep(1500);
                    break;

                case "SinanSinan" :
                    System.out.println("\n" + name + ": ...");
                    Thread.sleep(1500);
                    break;

                case "Ken" :
                    System.out.println("\n" + name + ": What... how did you... are you cheating?");
                    Thread.sleep(1500);
                    break;

                case "Jerome" :
                    System.out.println("\n" + name + ": Ugh... I didn't use moves effectively.");
                    Thread.sleep(1500);
                    break;

                case "Mitch" :
                    System.out.println("\n" + name + ": I like to study a lot... I thought I could win...");
                    Thread.sleep(1500);
                    break;

                case "Liam" :
                    System.out.println("\n" + name + ": Huh, so that's why I couldn't win...");
                    Thread.sleep(1500);
                    break;

                case "Selina" :
                    System.out.println("\n" + name + ": Wow, you might be strong enough to beat Sylvester!");
                    Thread.sleep(1500);
                    break;

                case "Sylvester Stone" :
                    System.out.println("\n" + name + ": I see... so that is how it is...");
                    Thread.sleep(1500);
                    break;

            }

        }

    }

}
