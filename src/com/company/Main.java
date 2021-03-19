package com.company;

import java.util.Random;
import java.util.Scanner;

/***********************************************
 * Program: LittlemonCombustionRed-Part1
 * Programmer: Gavin Eugenio
 * Date: January 8, 2020
 * A text-based adventure game inspired by Pokemon FireRed - with an (objectively) better story, and added decision-making
 ************************************************/

public class Main {

    /*
    Notes:
    -I increased the red line to column 120
     */

    //Initializing global objects - Scanner, Random, HandlerData, ItemData
    public static Scanner scanN = new Scanner(System.in);
    public static Scanner scanS = new Scanner(System.in);
    public static Random rand = new Random();

    //Player and Rival
    public static HandlerData player = new HandlerData("Maroon");
    public static HandlerData rival = new HandlerData("Cyan");

    //Items
    public static ItemData item1 = new ItemData("Littledex");
    public static ItemData item2 = new ItemData("Map");
    public static ItemData item3 = new ItemData("Little Ball");
    public static ItemData item4 = new ItemData("Potion");
    public static ItemData item5 = new ItemData("Boulder Pin");

    //Declaring global variables
    public static int playerPrompt, nextChapter, chapter = 0;
    public static String[] chapterName = {"Prologue", "Chapter 1: " + player.name + "'s Awakening",
            "Chapter 2: The Littlemon Scientist", "Chapter 3: A Journey's Beginnings", "Chapter 4: Verdant City"};
    public static boolean checkpoint = false, kipachuPath = false, tutorialBattle = true, receiveLittledex = false,
    receiveMap = false, visitMom = false, visitRivalSister = false, visitHouse, visitGuide, visitSchool, beatGym = false;

    //Navigation Areas
    public static String[][] talletTown = new String[][] {
            {"Tree", "Tree", "Tree",   "Exit", "Tree",   "Tree", "Tree"},
            {"Tree", "Path", "Path",   "Path", "Path",   "Path", "Tree"},
            {"Tree", "Path", "House1", "Path", "House2", "Path", "Tree"},
            {"Tree", "Path", "Path",   "Path", "Path",   "Path", "Tree"},
            {"Tree", "Path", "Tree",   "Path", "Lab",    "Path", "Tree"},
            {"Tree", "Path", "Path",   "Path", "Path",   "Path", "Tree"},
            {"Tree", "Tree", "Tree",   "Tree", "Tree",   "Tree", "Tree"}
    };

    public static String[][] route1 = new String[][] {
            {"Tree", "Tree",    "Tree",  "Exit",     "Tree",     "Tree",  "Tree"},
            {"Tree", "Path",    "Path",  "Path",     "Handler3", "Path",  "Tree"},
            {"Tree", "Ledge",   "Tree",  "Ledge",    "Ledge",    "Grass", "Tree"},
            {"Tree", "Ace",     "Tree",  "Grass",    "Grass",    "Grass", "Tree"},
            {"Tree", "Ledge",   "Tree",  "Grass",    "Grass",    "Grass", "Tree"},
            {"Tree", "Path",    "Path",  "Path",     "Path",     "Path",  "Tree"},
            {"Tree", "Ledge",   "Ledge", "Tree",     "Tree",     "Grass", "Tree"},
            {"Tree", "Worker",  "Path",  "Path",     "Handler2", "Grass", "Tree"},
            {"Tree", "Ledge",   "Path",  "Ledge",    "Ledge",    "Ledge", "Tree"},
            {"Tree", "Path",    "Path",  "Path",     "Path",     "Path",  "Tree"},
            {"Tree", "Tree",    "Tree",  "Tree",     "Grass",    "Ledge", "Tree"},
            {"Tree", "Path",    "Path",  "Handler1", "Path",     "Path",  "Tree"},
            {"Tree", "Grass",   "Ledge", "Ledge",    "Ledge",    "Ledge", "Tree"},
            {"Tree", "Grass",   "Grass", "Grass",    "Grass",    "Grass", "Tree"},
            {"Tree", "Tree",    "Tree",  "Enter",    "Tree",     "Tree",  "Tree"},
            {"Tree", "Tree",    "Tree",  "Block",    "Tree",     "Tree",  "Tree"}
    };

    public static String[][] verdantCity = new String[][] {
            {"Tree",  "Tree",   "Block", "Tree",   "Tree",  "Tree",  "Tree"},
            {"Tree",  "Tree",   "Path",  "Path",   "Path",  "Path",  "Tree"},
            {"Tree",  "Tree",   "Path",  "Path",   "Gym",   "Path",  "Tree"},
            {"Tree",  "Tree",   "Path",  "House4", "Path",  "Path",  "Tree"},
            {"Tree",  "Tree",   "Path",  "Path",   "Ledge", "Ledge", "Tree"},
            {"Tree",  "Tree",   "Path",  "Path",   "Path",  "Path",  "Tree"},
            {"Block", "Path",   "Path",  "House3", "Mart",  "Path",  "Tree"},
            {"Tree",  "Tree",   "Path",  "Path",   "Path",  "Path",  "Tree"},
            {"Tree",  "School", "Path",  "Center", "Tree",  "Path",  "Tree"},
            {"Tree",  "Path",   "Path",  "Path",   "Path",  "Path",  "Tree"},
            {"Tree",  "Ledge",  "Path",  "Ledge",  "Ledge", "Ledge", "Tree"},
            {"Tree",  "Path",   "Path",  "Path",   "Path",  "Path",  "Tree"},
            {"Tree",  "Tree",   "Tree",  "Enter",  "Tree",  "Tree",  "Tree"},
            {"Tree",  "Tree",   "Tree",  "Block",  "Tree",  "Tree",  "Tree"}
    };

    public static void main(String[] args) throws InterruptedException {

        //Game Start
        do {

            //Chapter Selection - Go to case # depending on current chapter variable #
            switch (chapter) {

                case 0 :
                    //Call prologue method
                    prologue();
                    break;

                case 1 :
                    //Call chapter1 method
                    chapter1();
                    break;

                case 2 :
                    //Call chapter2 method
                    chapter2();
                    break;

                case 3 :
                    //Call chapter3 method
                    chapter3();
                    break;

                case 4 :
                    //Call chapter4 method
                    chapter4();
                    break;

                case 5 :
                    //Call ending method
                    ending();
                    break;

                case 6 :
                    //End the game
                    break;

            }

        } while (nextChapter == 1);

    }

    /**********************
     * Method Name: prologue
     * Prologue segment of the game - The start screen, first dialogue, meeting the Scientist, creating your character,
     * naming your rival
     * @throws InterruptedException - for thread.sleep
     **********************/
    public static void prologue() throws InterruptedException {

        //Start Screen
        System.out.println("\n\n");
        System.out.println("=======================================================================================");
        System.out.println("\n" +
                    "         ,--.   ,--.,--------.,--------.,--.       ,--.   ,--.       ,--.  ,--. \n" +
                    "         |  |   `--''--.  .--''--.  .--'|  | ,---. |   `.'   | ,---. |  ,'.|  | \n" +
                    "         |  |   ,--.   |  |      |  |   |  || .-. :|  |'.'|  || .-. ||  |' '  | \n" +
                    "         |  '--.|  |   |  |      |  |   |  |\\   --.|  |   |  |' '-' '|  | `   | \n" +
                    "         `-----'`--'   `--'      `--'   `--' `----'`--'   `--' `---' `--'  `--'");

        System.out.println("" +
                    "                )    *               (          (       )     )   (      (      \n" +
                    "          (  ( /(  (  `     (        )\\ )  *   ))\\ ) ( /(  ( /(   )\\ )   )\\ )   \n" +
                    "          )\\ )\\()) )\\))(  ( )\\    ( (()/(` )  /(()/( )\\()) )\\()) (()/(( (()/(   \n" +
                    "        (((_|(_)\\ ((_)()\\ )((_)   )\\ /(_))( )(_))(_)|(_)\\ ((_)\\   /(_))\\ /(_))  \n" +
                    "        )\\___ ((_)(_()((_|(_)_ _ ((_|_)) (_(_()|_))   ((_) _((_) (_))((_|_))_   \n" +
                    "       ((/ __/ _ \\|  \\/  || _ ) | | / __||_   _|_ _| / _ \\| \\| | | _ \\ __|   \\  \n" +
                    "        | (_| (_) | |\\/| || _ \\ |_| \\__ \\  | |  | | | (_) | .` | |   / _|| |) | \n" +
                    "         \\___\\___/|_|  |_||___/\\___/|___/  |_| |___| \\___/|_|\\_| |_|_\\___|___/ ");

        System.out.println("" +
                    "                          __   _____ ___  ___ ___ ___  _  _ \n" +
                    "                          \\ \\ / / __| _ \\/ __|_ _/ _ \\| \\| |\n" +
                    "                           \\ V /| _||   /\\__ \\| | (_) | .` |\n" +
                    "                            \\_/ |___|_|_\\|___/___\\___/|_|\\_|");

        System.out.println("\n\n\n" +
                    "                             ────────▄███████████▄────────\n" +
                    "                             ─────▄███▓▓▓▓▓▓▓▓▓▓▓███▄─────\n" +
                    "                             ────███▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓███────\n" +
                    "                             ─██▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██─\n" +
                    "                             ██▓▓▓▓▓▓▓▓▓███████▓▓▓▓▓▓▓▓▓██\n" +
                    "                             ██▓▓▓▓▓▓▓▓██░░░░░██▓▓▓▓▓▓▓▓██\n" +
                    "                             ███████████░░███░░███████████\n" +
                    "                             ██░░░░░░░░██░░░░░██░░░░░░░░██\n" +
                    "                             ██░░░░░░░░░███████░░░░░░░░░██\n" +
                    "                             ─██░░░░░░░░░░░░░░░░░░░░░░░██─\n" +
                    "                             ────███░░░░░░░░░░░░░░░███────\n" +
                    "                             ─────▀███░░░░░░░░░░░███▀─────\n" +
                    "                             ────────▀███████████▀────────");

        System.out.println("\n" +
                    "                    __   __   ___  __   __      ___      ___  ___  __  \n" +
                    "                   |__) |__) |__  /__` /__`    |__  |\\ |  |  |__  |__) \n" +
                    "                   |    |  \\ |___ .__/ .__/    |___ | \\|  |  |___ |  \\ \n");
        System.out.println("=======================================================================================");
        scanS.nextLine();

        //Prologue Start
        start();

        //Meeting the Scientist
        System.out.println("\n???: Hey, there! Glad to meet ya!");
        Thread.sleep(1500);
        System.out.println("\n???: Welcome to the world of Littlemon!");
        Thread.sleep(1500);
        System.out.println("\nTimber: My name is Timber.");
        Thread.sleep(1500);
        System.out.println("\nTimber: But everyone refers to me as Tim, the Littlemon Scientist.");
        Thread.sleep(1500);
        System.out.println("\nTimber: This world... is inhabited far and wide by creatures known as Littlemom.");
        Thread.sleep(1500);
        System.out.println("\nTimber: We humans live alongside Littlemon as friends.");
        Thread.sleep(1500);
        System.out.println("\nTimber: At times we play together, and at other times we work together.");
        Thread.sleep(1500);
        System.out.println("\nTimber: Some people use their Littlemon to fight and develop closer bonds with them.");
        Thread.sleep(1500);
        System.out.println("\nTimber: As for myself... I study Littlemon as a profession.");
        Thread.sleep(1500);
        System.out.println("\nTimber: Now, why don't you tell me a little bit about yourself?");
        Thread.sleep(1500);

        //Call setPlayerGender mutator method in HandlerData class for player object
        player.setPlayerGender();

        //Call setPlayerName mutator method in HandlerData class for player object
        player.setPlayerName();

        System.out.println("\nTimber: I have a grandson that has been your rival since you were both babies.");
        Thread.sleep(1500);

        //Call setRivalName mutator method in HandlerData class for rival object
        rival.setRivalName();

        System.out.println("\nTimber: That's right! I remember now! His name is "
                + rival.name + "!");
        Thread.sleep(1500);

        //Prologue End
        System.out.println("\nTimber: " + player.name + "! Are you ready?");
        Thread.sleep(1500);
        System.out.println("\nTimber: Your very own tale of grand adventure is about to unfold.");
        Thread.sleep(1500);
        System.out.println("\nTimber: Fun experiences, difficult experiences, there's so much waiting for you!");
        Thread.sleep(1500);
        System.out.println("\nTimber: Dreams! Adventure! Let's go to the world of Littlemon!");
        Thread.sleep(1500);
        System.out.println("\nTimber: I'll see you later!");
        Thread.sleep(1500);

        //Update Chapter 1 Name with player name
        chapterName[1] = "Chapter 1: " + player.name + "'s Awakening";

        //Prologue Complete
        complete();

    }

    /**********************
     * Method Name: chapter1
     * Chapter 1 segment of the game - Story begins, start from your house, first decision-making, waking up from bed,
     * meeting your mother, leaving your house, first navigation maze, going to the Scientist's Laboratory
     * @throws InterruptedException - for thread.sleep
     **********************/
    public static void chapter1() throws InterruptedException {

        if (!checkpoint) {

            //Chapter 1 Start
            start();

            //Wake Up
            System.out.println("\n*beep*  *beep*  *beep*");
            Thread.sleep(1500);
            System.out.println("\nYour alarm wakes you up at 7 in the morning.");
            Thread.sleep(1500);
            System.out.println("\nYou feel very tired and have a sensation of falling back asleep");
            Thread.sleep(1500);

            do {

                System.out.println("\nWhat will you do?");
                System.out.println("1: Snooze and go back to sleep.");
                System.out.println("2: Throw your alarm clock.");
                System.out.println("3: Wake yourself up.");
                playerPrompt = scanN.nextInt();

                switch (playerPrompt) {

                    case 1:
                        System.out.println("\nYou snoozed the alarm clock and slowly closed your eyes.");
                        Thread.sleep(1500);
                        System.out.println("\nBut your determination of becoming a Littlemon Master instantly woke " +
                                "you up.");
                        Thread.sleep(1500);
                        break;

                    case 2:
                        System.out.println("\nYou threw the alarm clock, but it ended up smashing your brand new 4k tv.");
                        Thread.sleep(1500);
                        System.out.println("\nWhich knocked over along with your dock which had your brand new " +
                                "Knitendoe " +
                                "Swap in it.");
                        Thread.sleep(1500);
                        System.out.println("\nYou felt so sad that you lost interest for Littlemon...");
                        Thread.sleep(1500);
                        fail();

                    case 3:
                        System.out.println("\nYou tried to wake yourself up, but felt really sleepy and went back to " +
                                "sleep.");
                        Thread.sleep(1500);
                        System.out.println("\nYou wake up to find out that you were sleeping through a History Lesson " +
                                "at " +
                                "your school.");
                        Thread.sleep(1500);
                        System.out.println("\nYou saw and heard people laughing at you.");
                        Thread.sleep(1500);
                        System.out.println("\nLooks like dreams cannot come true...");
                        Thread.sleep(1500);
                        fail();

                }

            } while (playerPrompt < 1 || playerPrompt > 3);

            //Breakfast
            System.out.println("\nYou quickly got changed, and went down the stairs.");
            Thread.sleep(1500);
            System.out.println("\nYou see your mom in the kitchen and she made breakfast for you.");
            Thread.sleep(1500);

            do {

                System.out.println("\nWhat will you do?");
                System.out.println("1: Skip breakfast.");
                System.out.println("2: Stay for breakfast");
                System.out.println("3: Take out your phone.");
                playerPrompt = scanN.nextInt();

                switch (playerPrompt) {

                    case 1:
                        System.out.println("\nBecause of your determination of becoming a Littlemon Master, you " +
                                "decided to skip breakfast.");
                        Thread.sleep(1500);
                        System.out.println("\nAs soon as you ran out the front door, you soon realize that you don't " +
                                "know where to go.");
                        Thread.sleep(1500);
                        System.out.println("\nYou decided to walk around aimlessly without having any idea where to go.");
                        Thread.sleep(1500);
                        System.out.println("\nYou realize that you are lost.");
                        Thread.sleep(1500);
                        System.out.println("\nYou tried backtracking to your house, but you still had no idea where " +
                                "you are.");
                        Thread.sleep(1500);
                        System.out.println("\nMaybe you should've have stayed home to ask your mom where to go...");
                        Thread.sleep(1500);
                        fail();

                    case 2:
                        System.out.println("\nYou decided to spend time eating breakfast.");
                        Thread.sleep(1500);
                        break;

                    case 3:
                        System.out.println("\nYou took out your brand new MePhone X and tweeted a picture of yourself.");
                        Thread.sleep(1500);
                        System.out.println("\nYou said that that you were going to become a Littlemon Master.");
                        Thread.sleep(1500);
                        System.out.println("\nBut when people saw your tweet, they thought it was fake and made fun of " +
                                "you.");
                        Thread.sleep(1500);
                        System.out.println("\nAfter a couple of minutes, it already went viral and even more people " +
                                "made fun of you.");
                        Thread.sleep(1500);
                        System.out.println("\nYou felt so sad that you lost interest for Littlemon...");
                        Thread.sleep(1500);
                        fail();

                }

            } while (playerPrompt < 1 || playerPrompt > 3);

            //Your Feelings
            System.out.println("\nWhile having breakfast, you had a conversation with your mom.");
            Thread.sleep(1500);
            System.out.println("\nMom: So " + player.name + ", today is your big day!");
            Thread.sleep(1500);

            do {

                System.out.println("\nMom: Are you excited?");
                System.out.println("1: Yeah!");
                System.out.println("2: I'm kinda scared...");
                System.out.println("3: Not really...");
                System.out.println("4: Ignore your mom and leave.");
                playerPrompt = scanN.nextInt();

                switch (playerPrompt) {

                    case 1:
                        System.out.println("\n" + player.name + ": Yeah! I want to be the very best that no one ever " +
                                "was!");
                        Thread.sleep(1500);
                        System.out.println("\nMom: Well that's good to hear!");
                        Thread.sleep(1500);
                        System.out.println("\nMom: Maybe one day you will be the very best!");
                        Thread.sleep(1500);
                        break;

                    case 2:
                        System.out.println("\n" + player.name + ": I'm kinda scared... what if the Littlemon hurt me?");
                        Thread.sleep(1500);
                        System.out.println("\nMom: Well, that's why it's your job as a Littlemon Handler to train " +
                                "them.");
                        Thread.sleep(1500);
                        System.out.println("\nMom: Because it's your cause.");
                        Thread.sleep(1500);
                        break;

                    case 3:
                        System.out.println("\n" + player.name + ": Not really... I don't like having the job of " +
                                "training little monsters.");
                        Thread.sleep(1500);
                        System.out.println("\n" + player.name + " It's just not my thing.");
                        Thread.sleep(1500);
                        System.out.println("\nMom: Really, well if you don't want to be a Littlemon Handler...");
                        Thread.sleep(1500);

                        do {

                            System.out.println("\nMom: Do you want to go back to school instead?");
                            System.out.println("1: Yes.");
                            System.out.println("2: No.");
                            playerPrompt = scanS.nextInt();

                            switch (playerPrompt) {

                                case 1:
                                    System.out.println("\nYou went to school the following year.");
                                    Thread.sleep(1500);
                                    System.out.println("\nThe subjects were harder than you've expected it to be.");
                                    Thread.sleep(1500);
                                    System.out.println("\nYou were failing in every single one except for biology " +
                                            "and parenting.");
                                    Thread.sleep(1500);
                                    System.out.println("\nMaybe you're better off becoming a Littlemon Handler " +
                                            "instead...");
                                    Thread.sleep(1500);
                                    break;

                                case 2:
                                    System.out.println("\nAs soon as you were about to answer, your Mom's MePhone " +
                                            "dinged and she took it out.");
                                    Thread.sleep(1500);
                                    System.out.println("\nShe read her notifications, and later had a frown on her " +
                                            "face.");
                                    Thread.sleep(1500);
                                    System.out.println("\nMom: Unfortunately, the Littlemon Scientist has given out " +
                                            "his last Littlemon.");
                                    Thread.sleep(1500);
                                    System.out.println("\nMom: Sorry " + player.name + ", better luck next year.");
                                    Thread.sleep(1500);
                                    System.out.println("\nMom: Next time, take the time to decide if you truly want to " +
                                            "become a Littlemon Handler.");
                                    Thread.sleep(1500);
                                    System.out.println("\nMom: Then you could've left earlier without having this talk.");
                                    Thread.sleep(1500);
                                    break;

                            }

                        } while (playerPrompt < 1 || playerPrompt > 2);
                        fail();

                    case 4:
                        System.out.println("\nBecause of your determination of becoming a Littlemon Master, you got up " +
                                "and walked to the front door.");
                        Thread.sleep(1500);
                        System.out.println("\nYour mom catches you and gets mad at you for skipping breakfast and " +
                                "ignoring her.");
                        Thread.sleep(1500);
                        System.out.println("\nYou were grounded...");
                        Thread.sleep(1500);
                        fail();

                }

            } while (playerPrompt < 1 || playerPrompt > 4);

            //Path to the Lab
            System.out.println("\nMom: Anyways you would need a Littlemon if you want to become a Littlemon Handler.");
            Thread.sleep(1500);
            System.out.println("\nMom: You can get one from Scientist Tim's Laboratory.");
            Thread.sleep(1500);

            do {

                System.out.println("\nMom: Do you know where it is?");
                System.out.println("1: Yeah!");
                System.out.println("2: Nope.");
                playerPrompt = scanN.nextInt();

                switch (playerPrompt) {

                    case 1:
                        System.out.println("\n" + player.name + ": Yeah, I know where it is.");
                        Thread.sleep(1500);
                        System.out.println("\nMom: Wow you're prepared!");
                        Thread.sleep(1500);
                        break;

                    case 2:
                        System.out.println("\n" + player.name + ": Nope, I have no clue...");
                        Thread.sleep(1500);

                        do {

                            System.out.println("\nMom: To get to the Laboratory from here, take 1 step right.");
                            Thread.sleep(1500);
                            System.out.println("\nMom: Then 2 steps down.");
                            Thread.sleep(1500);
                            System.out.println("\nMom: Then 1 step right.");
                            Thread.sleep(1500);
                            System.out.println("\nMom: Finally, 1 step up.");
                            Thread.sleep(1500);

                            do {

                                System.out.println("\nMom: Did you got all that?");
                                System.out.println("1: Yep.");
                                System.out.println("2: Nope.");
                                playerPrompt = scanN.nextInt();

                                switch (playerPrompt) {

                                    case 1:
                                        System.out.println("\n" + player.name + ": Yep I got it.");
                                        Thread.sleep(1500);
                                        break;

                                    case 2:
                                        System.out.println("\n" + player.name + ": Um, can you repeat it again?");
                                        Thread.sleep(1500);
                                        break;

                                }

                            } while (playerPrompt < 1 || playerPrompt > 2);

                        } while (playerPrompt == 2);

                }

            } while (playerPrompt < 1 || playerPrompt > 2);

            //Leaving the house
            System.out.println("\nMom: Ok good luck out there!");
            Thread.sleep(1500);
            System.out.println("\nMom: Remember to visit home from time to time!");
            Thread.sleep(1500);

        }

        //Chapter 1 Checkpoint
        checkpoint = true;
        System.out.println("\n*Checkpoint Reached!*");

        //Walking Outside
        System.out.println("You walked outside your house, and see the sun rise in the far distance.");
        Thread.sleep(1500);
        System.out.println("\nYou need to get to Tim's Laboratory as quickly as possible, before all the Littlemon " +
                "are taken.");
        Thread.sleep(1500);

        //Navigation Start - Tallet Town
        navigation(talletTown,"Tallet Town", 3, 2);

    }

    /**********************
     * Method Name: chapter2
     * Chapter 2 segment of the game - Entering the Scientist's Laboratory, meeting your rival, choosing your starter,
     * first battle, journey begins
     * @throws InterruptedException - for thread.sleep
     **********************/
    public static void chapter2() throws InterruptedException {

        //Declaring variables within chapter2 method
        String greenDinosaurName = "Herbasaur", redLizardName = "Scorchmander", blueTurtleName = "Spurtle",
                yellowMouseName = "Kipachu", brownFoxName = "Eve";
        int rivalChoice;
        boolean rivalChooses = false;

        if (!checkpoint) {

            //Chapter 2 Start
            start();

            //Meeting your Rival
            if (kipachuPath) {
                System.out.println("\nYou followed the man in the white lab coat into the laboratory.");
                Thread.sleep(1500);
            }
            System.out.println("\nAs you walked into the laboratory, you see a boy about your age.");
            Thread.sleep(1500);
            System.out.println("\n???: Gramps! I'm fed up with waiting!");
            Thread.sleep(1500);
            System.out.println("\nThe boy faced you.");
            Thread.sleep(1500);

            do {

                System.out.println("\n???: Oh hey " + player.name + ", didn't expect to see you here too.");
                System.out.println("1: Hello.");
                System.out.println("2: Do I know you?");
                System.out.println("3: Stranger danger!");
                playerPrompt = scanN.nextInt();

                switch (playerPrompt) {

                    case 1:
                        System.out.println("\n" + player.name + ": Hello there " + rival.name + ", didn't expect to " +
                                "see you here as well.");
                        Thread.sleep(1500);
                        break;

                    case 2:
                        System.out.println("\n" + player.name + ": Um, do I know you? You look familiar...");
                        Thread.sleep(1500);
                        System.out.println("\n" + rival.name + ": HOW DO YOU NOT REMEMBER ME? I'M " + rival.name + "!");
                        Thread.sleep(1500);
                        System.out.println("\n" + rival.name + ": WE'VE KNOWN EACH OTHER SINCE WE WERE LITTLE BRATS!");
                        Thread.sleep(1500);
                        System.out.println("\n" + rival.name + ": Gosh, you and my grandfather are alike...");
                        Thread.sleep(1500);
                        break;

                    case 3:
                        System.out.println("\n" + player.name + ": Stranger danger!");
                        Thread.sleep(1500);
                        System.out.println("\nYou started freaking out because you just met someone who you didn't " +
                                "know.");
                        Thread.sleep(1500);
                        System.out.println("\nYou ran around the laboratory and knocked over the table which had " +
                                "three balls.");
                        Thread.sleep(1500);
                        System.out.println("\nThe balls dropped and opened, and three different little creatures " +
                                "appeared.");
                        Thread.sleep(1500);
                        System.out.println("\nThe three little creatures ran around aimlessly destroying the lab, and " +
                                "escaped.");
                        Thread.sleep(1500);
                        System.out.println("\nThe man in the lab coat gave you a mean look and kicked you out.");
                        Thread.sleep(1500);
                        fail();

                }

            } while (playerPrompt < 1 || playerPrompt > 3);

            //Meeting the Scientist (again)
            System.out.println("\n???: " + rival.name + "? Let me think...");
            Thread.sleep(1500);
            System.out.println("\n???: Oh that's right, I told you to come! Just wait!");
            Thread.sleep(1500);
            System.out.println("\n???: Oh I forgot to introduce myself! I'm Scientist Tim!");
            Thread.sleep(1500);
            System.out.println("\nTim: The Littlemon Scientist in all of the Onto Region!");
            Thread.sleep(1500);

            if (!kipachuPath) {
                System.out.println("\nTim pointed at a desk nearby with three balls colored blue and black.");
                Thread.sleep(1500);
                System.out.println("\nTim: There, " + player.name + " are three Littlemon. Haha!");
                Thread.sleep(1500);
                System.out.println("\nTim: The Littlemon are held inside those Littleballs.");
            } else {
                System.out.println("\nTim pointed at a desk nearby with one ball colored blue and black.");
                Thread.sleep(1500);
                System.out.println("\nTim: That, " + player.name + " is a Littlemon. Haha!");
                Thread.sleep(1500);
                System.out.println("\nTim: The Littlemon is held inside that Littleball.");
            }
            Thread.sleep(1500);

            System.out.println("\nTim: When I was young, I was a serious Littlemon Handler.");
            Thread.sleep(1500);

            if (!kipachuPath) {
                System.out.println("\nTim: But now, in my old age, I have only these three left.");
                Thread.sleep(1500);
                System.out.println("\nTim: You can have one. Go on, choose!");
            } else {
                System.out.println("\nTim: But now, in my old age, I have only one left.");
                Thread.sleep(1500);
                System.out.println("\nTim: You can have it. Go on, take it!");
            }
            Thread.sleep(1500);

            System.out.println("\n" + rival.name + ": Hey! Gramps! No fair! What about me?");
            Thread.sleep(1500);

            if (!kipachuPath) {
                System.out.println("\nTim: Be patient, " + rival.name + ". You can have one, too!");
            } else {
                System.out.println("\nTim: Be patient, " + rival.name + ". I'll give you one later.");
            }
            Thread.sleep(1500);

            if (!kipachuPath) {

                //Normal Route
                do {

                    System.out.println("\nWhat will you do?");
                    System.out.println("1: Choose your starter.");
                    System.out.println("2: Tell " + rival.name + " to shut up.");
                    System.out.println("3: Let " + rival.name + " choose his starter first.");
                    playerPrompt = scanN.nextInt();

                    switch (playerPrompt) {

                        case 1:
                            System.out.println("\nYou walked over to the table to choose your starter.");
                            Thread.sleep(1500);
                            break;

                        case 2:
                            System.out.println("\n" + player.name + ": Hey shut up!");
                            Thread.sleep(1500);
                            System.out.println("\n" + player.name + ": I get to choose first.");
                            Thread.sleep(1500);
                            System.out.println("\n" + rival.name + ": What did you say?");
                            Thread.sleep(1500);
                            System.out.println("\n" + rival.name + ": You wanna fight?");
                            Thread.sleep(1500);
                            System.out.println("\n" + player.name + ": Um, no than-");
                            Thread.sleep(1500);
                            System.out.println("\n===================================================");
                            System.out.println("\tRival " + rival.name + " wants to fight!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                            System.out.println("\n===================================================");
                            System.out.println("\t" + rival.name + " used Tackle!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                            System.out.println("\n===================================================");
                            System.out.println("\t" + player.name + " took 999 damage.");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                            System.out.println("\n===================================================");
                            System.out.println("\tA critical hit!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                            System.out.println("\n===================================================");
                            System.out.println("\t" + player.name + " fainted!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                            System.out.println("\n===================================================");
                            System.out.println("\t" + player.name + ": blacked out!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                            fail();

                        case 3:
                            //If rival chooses starter first
                            rivalChooses = true;
                            System.out.println("\n*" + rival.name + " will remember that.*");
                            System.out.println(player.name + ": No its fine, " + rival.name + " can go first.");
                            Thread.sleep(1500);
                            System.out.println("\n" + rival.name + ": Heh, thanks for the offer, " + player.name + ".");
                            Thread.sleep(1500);

                            //Rival randomly chooses one of the three starters
                            rivalChoice = (int) (Math.random() * 3);

                            if (rivalChoice == 0) {
                                //Set Littlemon1 in HandlerData class for rival and player objects
                                rival.setLittlemon(greenDinosaurName);
                                player.setLittlemon(redLizardName);
                            } else if (rivalChoice == 1) {
                                //Set Littlemon1 in HandlerData class for rival and player objects
                                rival.setLittlemon(redLizardName);
                                player.setLittlemon(blueTurtleName);
                            } else if (rivalChoice == 2) {
                                //Set Littlemon1 in HandlerData class for rival and player objects
                                rival.setLittlemon(blueTurtleName);
                                player.setLittlemon(greenDinosaurName);
                            }

                            System.out.println("\n" + rival.name + ": I'll take this one, then!");
                            Thread.sleep(1500);
                            System.out.println("\n" + rival.name + " received the " + rival.littlemonTeam[0].name +
                                    " from Scientist Tim!");
                            Thread.sleep(1500);
                            System.out.println("\nYou decided to choose the Littlemon which had a type advantage to " +
                                    "your rival's Littlemon.");
                            Thread.sleep(1500);
                            System.out.println("\nBecause, Littlemon logic.");
                            Thread.sleep(1500);

                    }

                } while (playerPrompt < 1 || playerPrompt > 3);

                //Choosing a Starter
                if (!rivalChooses) {

                    System.out.println("\nYou see the three shiny black and blue balls lying on a table.");
                    Thread.sleep(1500);
                    System.out.println("\nThe one on the left contains a green dinosaur.");
                    Thread.sleep(1500);
                    System.out.println("\nThe one in the middle contains a red lizard.");
                    Thread.sleep(1500);
                    System.out.println("\nThe one on the right contains a blue turtle.");
                    Thread.sleep(1500);

                    do {

                        System.out.println("\nTim: Now, " + player.name + ", which Littlemon do you want?");
                        System.out.println("1: Green Dinosaur.");
                        System.out.println("2: Red Lizard.");
                        System.out.println("3: Blue Turtle.");
                        playerPrompt = scanN.nextInt();

                        switch (playerPrompt) {

                            //The Grass Starter
                            case 1:
                                do {

                                    System.out.println("\nTim: So! You want the grass Littlemon, " + greenDinosaurName +
                                            "?");
                                    System.out.println("1: Yes.");
                                    System.out.println("2: No.");
                                    playerPrompt = scanN.nextInt();

                                    if (playerPrompt == 1) {
                                        System.out.println("\nThis Littlemon is really spirited!");
                                        Thread.sleep(1500);
                                        //Set Littlemon1 in HandlerData class for player and rival objects
                                        player.setLittlemon(greenDinosaurName);
                                        rival.setLittlemon(redLizardName);
                                    }

                                } while (playerPrompt < 1 || playerPrompt > 2);

                                break;

                            //The Fire Starter
                            case 2:
                                do {

                                    System.out.println("\nTim: So! You want the fire Littlemon, " + redLizardName +
                                            "?");
                                    System.out.println("1: Yes.");
                                    System.out.println("2: No.");
                                    playerPrompt = scanN.nextInt();

                                    if (playerPrompt == 1) {
                                        System.out.println("\nTim: This Littlemon is really aggressive!");
                                        Thread.sleep(1500);
                                        //Set Littlemon1 in HandlerData class for player and rival objects
                                        player.setLittlemon(redLizardName);
                                        rival.setLittlemon(blueTurtleName);
                                    }

                                } while (playerPrompt < 1 || playerPrompt > 2);

                                break;

                            //The Water Starter
                            case 3:
                                do {

                                    System.out.println("\nTim: So! You want the water Littlemon, " + blueTurtleName +
                                            "?");
                                    System.out.println("1: Yes.");
                                    System.out.println("2: No.");
                                    playerPrompt = scanN.nextInt();

                                    if (playerPrompt == 1) {
                                        System.out.println("\nTim: This Littlemon is really active!");
                                        Thread.sleep(1500);
                                        //Set Littlemon1 in HandlerData class for player and rival objects
                                        player.setLittlemon(blueTurtleName);
                                        rival.setLittlemon(greenDinosaurName);
                                    }

                                } while (playerPrompt < 1 || playerPrompt > 2);

                                break;

                        }

                    } while (playerPrompt < 1 || playerPrompt > 3 || playerPrompt == 2);

                }

            } else {

                //Kipachu Route
                System.out.println("\nYou walked over to the table to take the Littlemon.");
                Thread.sleep(1500);
                System.out.println("\nBut as you reached out to grab it, " + rival.name + " quickly snatches it.");
                Thread.sleep(1500);
                System.out.println("\n" + rival.name + ": No way! " + player.name + ", I want this Littlemon!");
                Thread.sleep(1500);

                //Rival snatches Eve
                //Set Littlemon1 in HandlerData class for rival object
                rival.setLittlemon(brownFoxName);

                //Rival's Tolerance
                System.out.println("\n" + rival.name + " snatched the Littlemon!");
                Thread.sleep(1500);
                System.out.println("\nTim: " + rival.name + "! What are you doing?");
                Thread.sleep(1500);
                System.out.println("\n" + rival.name + ": Gramps, I want this one!");
                Thread.sleep(1500);
                System.out.println("\nTim: But, I...");
                Thread.sleep(1500);
                System.out.println("\nTim: Oh, all right then. That Littlemon is yours.");
                Thread.sleep(1500);
                System.out.println("\nTim: I was going to give you one anyway...");
                Thread.sleep(1500);
                System.out.println("\nTim: " + player.name + ", come over here.");
                Thread.sleep(1500);

                do {

                    System.out.println("\nWhat will you do?");
                    System.out.println("1: Go to Scientist Tim.");
                    System.out.println("2: Complain.");
                    System.out.println("3: Fight " + rival.name + ".");
                    playerPrompt = scanN.nextInt();

                    switch (playerPrompt) {

                        case 1:
                            System.out.println("\nYou walked over to Scientist Tim.");
                            Thread.sleep(1500);
                            break;

                        case 2:
                            System.out.println("\nTim: Hey " + player.name + "! Don't you even think about complaining " +
                                    "to me.");
                            Thread.sleep(1500);
                            System.out.println("\nTim: Be grateful because I have something here for you.");
                            Thread.sleep(1500);
                            break;

                        case 3:
                            System.out.println("\n===================================================");
                            System.out.println("\tHandler " + player.name + " wants to fight!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                            System.out.println("\n===================================================");
                            System.out.println("\t" + player.name + " used Dynamic Punch!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                            System.out.println("\n===================================================");
                            System.out.println("\t" + player.name + "'s attack missed!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                            System.out.println("\n===================================================");
                            System.out.println("\t" + rival.name + " used Counter!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                            System.out.println("\n===================================================");
                            System.out.println("\tA critical hit!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                            System.out.println("\n===================================================");
                            System.out.println("\t" + player.name + " fainted!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                            System.out.println("\n===================================================");
                            System.out.println("\t" + player.name + ": blacked out!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                            fail();

                    }

                } while (playerPrompt < 1 || playerPrompt > 3);

                System.out.println("\nTim: " + player.name + ", this is the Littlemon I caught earlier.");
                Thread.sleep(1500);
                System.out.println("\nTim: You can have it. I caught it in the wild and it's not tame yet.");
                Thread.sleep(1500);

                //You receive Kipachu
                //Set Littlemon1 in HandlerData class for player object
                player.setLittlemon(yellowMouseName);

            }

            System.out.println("\n" + player.name + " received the " + player.littlemonTeam[0].name + " from Scientist " +
                    "Tim!");
            Thread.sleep(1500);

            //Starter Name Selection
            do {

                System.out.println("\nDo you want to give a nickname to this " +
                        player.littlemonTeam[0].name + "?");
                System.out.println("1: Yes.");
                System.out.println("2: No.");
                playerPrompt = scanN.nextInt();

            } while (playerPrompt < 1 || playerPrompt > 2);

            //Call setNickname mutator method in LittlemonData class through player's HandlerData class for its
            //Littlemon object
            player.littlemonTeam[0].setNickname(playerPrompt);

            if (!rivalChooses && !kipachuPath) {
                //If rival chooses starter afterwards
                System.out.println("\n" + rival.name + ": I'll take this one, then!");
                Thread.sleep(1500);
                System.out.println("\n" + rival.name + " received the " + rival.littlemonTeam[0].name + " from " +
                        "Scientist Tim!");
                Thread.sleep(1500);
            }

            //First Battle with Rival
            System.out.println("\nAs you were heading out of the Laboratory with your new Littlemon in your hand, " +
                    rival.name + " called you.");
            Thread.sleep(1500);
            System.out.println("\n" + rival.name + ": Wait, " + player.name + "! Let's check out our Littlemon!");
            Thread.sleep(1500);

            do {

                System.out.println("\n" + rival.name + ": Come on, I'll take you on!");
                System.out.println("1. Sure.");
                System.out.println("2. Nope.");
                System.out.println("3. Run.");
                playerPrompt = scanN.nextInt();

                switch (playerPrompt) {

                    case 1:
                        System.out.println("\n" + player.name + ": Ok sure, let's battle!");
                        Thread.sleep(1500);
                        System.out.println("\n" + rival.name + ": Ha! That's the spirit. You're on!");
                        Thread.sleep(1500);
                        break;

                    case 2:
                        System.out.println("\n" + player.name + ": Erm, no thanks. I have no interest in battling a " +
                                "weakling like you.");
                        Thread.sleep(1500);
                        System.out.println("\n" + rival.name + ": What did you say?");
                        Thread.sleep(1500);
                        System.out.println("\n" + rival.name + ": Well too bad, we're gonna battle anyways.");
                        Thread.sleep(1500);
                        System.out.println("\n" + rival.name + ": FOR REAL!");
                        Thread.sleep(1500);
                        System.out.println("\n===================================================");
                        System.out.println("\tRival " + rival.name + " wants to fight!");
                        System.out.println("===================================================");
                        Thread.sleep(1500);
                        System.out.println("\n===================================================");
                        System.out.println("\t" + rival.name + " used Tackle!");
                        System.out.println("===================================================");
                        Thread.sleep(1500);
                        System.out.println("\n===================================================");
                        System.out.println("\t" + player.name + " took 999 damage.");
                        System.out.println("===================================================");
                        Thread.sleep(1500);
                        System.out.println("\n===================================================");
                        System.out.println("\tA critical hit!");
                        System.out.println("===================================================");
                        Thread.sleep(1500);
                        System.out.println("\n===================================================");
                        System.out.println("\t" + player.name + " fainted!");
                        System.out.println("===================================================");
                        Thread.sleep(1500);
                        System.out.println("\n===================================================");
                        System.out.println("\t" + player.name + ": blacked out!");
                        System.out.println("===================================================");
                        Thread.sleep(1500);
                        fail();

                    case 3:
                        System.out.println("\nCouldn't get away!");
                        Thread.sleep(1500);
                        System.out.println("\n" + rival.name + ": Hey! Where do you think you're going?!");
                        Thread.sleep(1500);
                        System.out.println("\n" + rival.name + ": What's wrong " + player.name + "? Are you a chicken?");
                        Thread.sleep(1500);
                        System.out.println("\nYou stopped just as you were about to exit the laboratory.");
                        Thread.sleep(1500);
                        System.out.println("\nYou had an unbearable sensation of hatred building up within you.");
                        Thread.sleep(1500);
                        System.out.println("\n" + rival.name + ": That's right. You're nothing but a lil' chicken, " +
                                player.name + ".");
                        Thread.sleep(1500);
                        System.out.println("\nYou slowly turned your head towards " + rival.name + " with a look of " +
                                "killing intent on your face.");
                        Thread.sleep(1500);
                        System.out.println("\nYou sent out your Littlemon from your hand and walked up to " +
                                rival.name + ".");
                        Thread.sleep(1500);
                        System.out.println("\nYour eyes were filled with combustion.");
                        Thread.sleep(1500);
                        System.out.println("\n" + player.name + ": Nobody. Calls me. Chicken.");
                        Thread.sleep(1500);
                        System.out.println("\nYou pointed at " + rival.name + ".");
                        Thread.sleep(1500);
                        System.out.println("\n" + player.name + ": " + player.littlemonTeam[0].nickname + "!! Use " +
                                player.littlemonTeam[0].moveset[0].name + "!!");
                        Thread.sleep(1500);
                        System.out.println("\nAs your Littlemon sensed your feelings of hatred, it started to fear you.");
                        Thread.sleep(1500);
                        System.out.println("\nIt disobeyed your command and attacked you instead.");
                        Thread.sleep(1500);
                        System.out.println("\nYou blacked out!");
                        fail();

                }

            } while (playerPrompt < 1 || playerPrompt > 3);

        }

        //Add starters chosen to Littledex
        player.addToLittledex(player.littlemonTeam[0].name);
        player.addToLittledex(rival.littlemonTeam[0].name);

        //Chapter 2 Checkpoint
        checkpoint = true;
        System.out.println("\n*Checkpoint Reached!*");

        //Reset health and power points
        player.littlemonTeam[0].heal();
        rival.littlemonTeam[0].heal();

        //Battle Start - Rival
        battle("Rival ", rival.name, rival);
        tutorialBattle = false;

        //After battle ends
        System.out.println("\n" + rival.name + ": Okay! I'll make my Littlemon battle more to toughen it up!");
        Thread.sleep(1500);
        System.out.println("\n" + rival.name + ": " + player.name + "! I'll be sure to beat you next time!");
        Thread.sleep(1500);

        if (kipachuPath) {
            System.out.println("\nSuddenly, your Littleball starts shaking.");
            Thread.sleep(1500);
            System.out.println("\n" + player.littlemonTeam[0].nickname + " came outside of the Littleball!");
            Thread.sleep(1500);
            System.out.println("\nTim: What? Would you look at that!");
            Thread.sleep(1500);
            System.out.println("\nTim: It's odd, but it appears that your " + player.littlemonTeam[0].name + " " +
                    "dislikes Little Balls.");
            Thread.sleep(1500);
            System.out.println("\nTim: You should just keep it with you. That should make it happy!");
            Thread.sleep(1500);
            System.out.println("\nTim: You can talk to it and see how it feels about you.");
            Thread.sleep(1500);
        }

        //The Scientist's Request
        System.out.println("\nTim: Oh, right! I have a request for you two.");
        Thread.sleep(1500);
        System.out.println("\nTim walked over to a table with two mysterious devices sitting on top of it.");
        Thread.sleep(1500);
        System.out.println("\nTim: On the desk there is my invention, the Littledex!");
        Thread.sleep(1500);
        System.out.println("\nTim: It automatically records data on Littlemon you've seen or caught, it's a high-tech " +
                "encyclopedia!");
        Thread.sleep(1500);
        System.out.println("\nTim: " + player.name + " and " + rival.name + ". Take these with you.");
        Thread.sleep(1500);

        //Receive the Littledex
        receiveLittledex = true;
        item1.gainItems(1);

        System.out.println("\nTim: You can't get detailed data on Littlemon by just seeing them, you must catch them " +
                "to obtain complete data.");
        Thread.sleep(1500);
        System.out.println("\nTim: So, here are some tools for catching wild Littlemon.");
        Thread.sleep(1500);
        item3.gainItems(5);
        System.out.println("\nTim: When a wild Littlemon appears, it's fair game, just throw a Littleball at it and " +
                "try to catch it!");
        Thread.sleep(1500);
        System.out.println("\nTim: This won't always work, however. A healthy Littlemon can escape. You have to be " +
                "lucky!");
        Thread.sleep(1500);
        System.out.println("\nTim: To make a complete guide on all the Littlemon in the world... That was my dream!");
        Thread.sleep(1500);
        System.out.println("\nTim: But, I'm too old. I can't get the job done. So, I want you two to fulfill my dream " +
                "for me.");
        Thread.sleep(1500);
        System.out.println("\nTim: Along the way, battle handlers, gym commanders, earn pins, get stronger!");
        Thread.sleep(1500);
        System.out.println("\nTim: Who knows, maybe you may become champion of Onto!");
        Thread.sleep(1500);
        System.out.println("\nTim: Get moving, you two. This is a great undertaking in Littlemon history!");
        Thread.sleep(1500);

        //Chapter 2 Complete
        complete();

    }

    /**********************
     * Method Name: chapter3
     * Chapter 3 segment of the game - Leaving the Laboratory, starting your journey, first wild encounters, Route 1,
     * going to Verdant City
     * @throws InterruptedException - for thread.sleep
     **********************/
    public static void chapter3() throws InterruptedException {

        if (!checkpoint) {

            //Chapter 3 Start
            start();

            //Leaving the Lab
            System.out.println("\n" + rival.name + ": All right, Gramps! Leave it all to me!");
            Thread.sleep(1500);
            System.out.println("\n" + rival.name + ": " + player.name + ", I hate to say it, but you won't be " +
                    "necessary for this.");
            Thread.sleep(1500);
            System.out.println("\n" + rival.name + ": I know! I'll borrow a town map from my sis!");
            Thread.sleep(1500);
            System.out.println("\n" + rival.name + ": I'll tell her not to lend you one, " + player.name + "! Hahaha!");
            Thread.sleep(1500);
            System.out.println("\n" + rival.name + ": Don't bother coming around to my place after this!");
            Thread.sleep(1500);
            System.out.println("\n" + rival.name + ": " + player.name + "! Gramps! Smell you later!");
            Thread.sleep(1500);
            System.out.println("\nYou watched as " + rival.name + " exits the Laboratory.");
            Thread.sleep(1500);
            System.out.println("\nTim: Ah, that " + rival.name + "... " + player.name + ", don't mind him.");
            Thread.sleep(1500);
            System.out.println("\nTim: Hmm... from what I can remember, the next city, Verdant City has the first gym.");
            Thread.sleep(1500);
            System.out.println("\nTim: You're going to have to pass through Route 1, which is north from here.");
            Thread.sleep(1500);
            System.out.println("\nTim: Oh but before you leave, be sure to talk to your mom.");
            Thread.sleep(1500);
            System.out.println("\nTim: And also ask " + rival.name + "'s sister for a map so that you don't get lost.");
            Thread.sleep(1500);
            System.out.println("\nTim: I'm sure she will lend you one.");
            Thread.sleep(1500);
            System.out.println("\nTim: Good luck out there!");
            Thread.sleep(1500);

            //Walking Outside
            System.out.println("\nYou walked outside of the Laboratory, it's already the afternoon.");
            Thread.sleep(1500);
            System.out.println("\nYou should probably go to your house first since you can backtrack your way to the " +
                    "Lab.");
            Thread.sleep(1500);

            //Save point
            player.savedMoney = player.moneyEarned;
            player.littlemonTeam[0].save(player.littlemonTeam[0]);

            //Navigation Start - Tallet Town
            navigation(talletTown, "Tallet Town", 5, 4);

        }

        //Chapter 3 Checkpoint
        checkpoint = true;
        System.out.println("\n*Checkpoint Reached!*");

        //Reset money, items, level, experience, health, power points, stats, and moves of Littlemon, and Littlemon caught
        player.moneyEarned = player.savedMoney;
        item3.amount = 5;
        item4.amount = 0;
        player.littlemonTeam[0].recover();
        player.littlemonCaught = 1;
        if (visitMom) {
            player.littlemonTeam[0].heal();
        }

        //Entering Route 1
        System.out.println("You see the sun going down in the far distance.");
        Thread.sleep(1500);
        System.out.println("\nYou need to get to Verdant City and challenge the first gym.");
        Thread.sleep(1500);
        System.out.println("\nYou also remember to catch Littlemon on the way for Scientist Tim.");
        Thread.sleep(1500);
        System.out.println("\nLittlemon hide in the long grass, so be sure to check your map.");
        Thread.sleep(1500);

        //Navigation Start - Route 1
        navigation(route1, "Route 1", 14, 3);

    }

    /**********************
     * Method Name: chapter4
     * Chapter 4 segment of the game - Arriving at Verdant City, exploring the city, introduction to Little Centers and
     * Little Marts, first gym battle, end of Part 1
     * @throws InterruptedException - for thread.sleep
     **********************/
    public static void chapter4() throws InterruptedException {

        //Initializing objects - HandlerData
        HandlerData guide = new HandlerData("Ken");
        guide.setLittlemon("Ratatouille");

        if (!checkpoint) {

            //Chapter 4 Start
            start();

            //Entering the City and Meeting the Guide Gent
            System.out.println("\nAs you entered the city, you were met with a very vibrant and colourful landscape.");
            Thread.sleep(1500);
            System.out.println("\nThe area is full of greenery, with many trees surrounding the city's houses.");
            Thread.sleep(1500);
            System.out.println("\nIt is a very quiet and peaceful place, with only a few people out on a walk.");
            Thread.sleep(1500);
            System.out.println("\nThat is, until you heard someone running up towards you.");
            Thread.sleep(1500);

            do {

                System.out.println("\n???: Hey you! Yeah you! You're a rookie Handler, aren't you? I can tell!");
                System.out.println("1: Yes...");
                System.out.println("2: Nope.");
                System.out.println("3: Who are you?");
                System.out.println("4: Turn back.");
                playerPrompt = scanN.nextInt();

                switch (playerPrompt) {

                    case 1:
                        System.out.println("\n" + player.name + ": Uh... yeah...");
                        Thread.sleep(1500);
                        System.out.println("\n???: That's OK! Everyone is a rookie at some point! If you'd like, I can " +
                                "teach you a few things.");
                        Thread.sleep(1500);
                        break;

                    case 2:
                        System.out.println("\n" + player.name + ": No I'm not. In fact, I am a Littlemon Master!");
                        Thread.sleep(1500);
                        System.out.println("\n???: Oh really...? Is that so?");
                        Thread.sleep(1500);

                        do {

                            System.out.println("\n???: Well then why don't you prove it in a battle then?");
                            System.out.println("1: Yes.");
                            System.out.println("2: No.");
                            playerPrompt = scanS.nextInt();

                            switch (playerPrompt) {

                                case 1:
                                    battle("Guide Gent ", guide.name, guide);
                                    fail();

                                case 2:
                                    System.out.println("\n" + player.name + ": Umm... no thanks...");
                                    Thread.sleep(1500);
                                    System.out.println("\n???: I see... that's what I figured...");
                                    Thread.sleep(1500);
                                    break;

                            }

                        } while (playerPrompt != 2);
                        break;

                    case 3:
                        System.out.println("\n" + player.name + ": Uh... who are you?");
                        Thread.sleep(1500);
                        System.out.println("\n???: That information I will reveal to you at a later time.");
                        Thread.sleep(1500);
                        break;

                    case 4:
                        System.out.println("\n" + player.name + ": Stranger danger!");
                        Thread.sleep(1500);
                        System.out.println("\nYou started freaking out because you just met someone who you didn't " +
                                "know.");
                        Thread.sleep(1500);
                        System.out.println("\nYou ran back to Route 1.");
                        Thread.sleep(1500);
                        System.out.println("\nYou fell into a tree...");
                        Thread.sleep(1500);
                        fail();

                }

            } while (playerPrompt < 1 || playerPrompt > 4);

            System.out.println("\n???: OK, then! Follow me! I'll give you a tour of the city!");
            Thread.sleep(1500);
            System.out.println("\n???: Your consciousness started speaking to you.");
            Thread.sleep(1500);

            do {

                System.out.println("\nShould you trust this person?");
                System.out.println("1: Yep. I'm all in.");
                System.out.println("2: Nope. Definitely not.");
                playerPrompt = scanN.nextInt();

                switch (playerPrompt) {

                    case 1:
                        System.out.println("\nYou decided to followed the mysterious man around the city.");
                        Thread.sleep(1500);
                        break;

                    case 2:
                        System.out.println("\n" + player.name + ": Erm... my mom said not to trust strangers...");
                        Thread.sleep(1500);
                        System.out.println("\n???: Of course you can trust me! I'm just you friendly neighbourhood " +
                                "guide gent.");
                        Thread.sleep(1500);
                        System.out.println("\nYou ignored your conscious and decided to follow the man anyways.");
                        Thread.sleep(1500);
                        break;

                }

            } while (playerPrompt < 1 || playerPrompt > 2);

            System.out.println("\nYou followed the man to a big building with a red roof and a Littleball sign on top " +
                    "of it.");
            Thread.sleep(1500);
            System.out.println("\n???: This is a Littlemon Center.");
            Thread.sleep(1500);
            System.out.println("\n???: They heal your Littlemon in no time at all. Free of charge!");
            Thread.sleep(1500);
            System.out.println("\n???: You'll be relying on them a lot, so you better learn about them.");
            Thread.sleep(1500);
            System.out.println("\nYou followed the man around the corner of the Center to a similar building with a " +
                    "blue roof.");
            Thread.sleep(1500);
            System.out.println("\n???: This is a Littlemon Mart.");
            Thread.sleep(1500);
            System.out.println("\n???: They sell Little Balls for catching wild Littlemon and other useful items.");
            Thread.sleep(1500);
            System.out.println("\nYou followed the man heading directly north of the city.");
            Thread.sleep(1500);
            System.out.println("\n???: Route 2 is out this way, but I wouldn't go any further until you get this " +
                    "city's gym badge.");
            Thread.sleep(1500);
            System.out.println("\n???: Speaking of which... I'll show you the gym.");
            Thread.sleep(1500);
            System.out.println("\nYou followed the man to a large building with a brown roof. There seems to be a " +
                    "battle going on inside it.");
            Thread.sleep(1500);
            System.out.println("\n???: Here is the city's gym. Our gym commander's name is Sylvester.");
            Thread.sleep(1500);
            System.out.println("\n???: If you beat him, you can get your first gym badge!");
            Thread.sleep(1500);
            System.out.println("\n???: But I would recommend preparing first at the Handlers' school, which I'll show " +
                    "you now.");
            Thread.sleep(1500);
            System.out.println("\nYou followed the man heading south to a smaller building with some people your age " +
                    "inside it.");
            Thread.sleep(1500);
            System.out.println("\n???: Here is the Handlers' school.");
            Thread.sleep(1500);
            System.out.println("\n???: If you go inside, you can learn more about battling and how to get better at it!");
            Thread.sleep(1500);
            System.out.println("\n???: You can also battle other Littlemon Handlers here to train your Littlemon.");
            Thread.sleep(1500);
            System.out.println("\n???: OK! That concludes the tour of Verdant City. I hope you'll enjoy your time here!");
            Thread.sleep(1500);
            System.out.println("\n???: Oh I forgot to introduce myself! I'm Ken! The Guide Gent!");
            Thread.sleep(1500);
            System.out.println("\nKen: If you ever want to find me, I'll be at my house! Check your map!");
            Thread.sleep(1500);
            System.out.println("\nKen: Well, I'll be seeing you. Good luck on your journey!");
            Thread.sleep(1500);

            //Save point
            player.savedItem3 = item3.amount;
            player.savedItem4 = item4.amount;
            player.savedMoney = player.moneyEarned;

            for (int i = 0; i < player.littlemonCaught; i++) {
                player.littlemonTeam[i].save(player.littlemonTeam[i]);
            }


        }

        //Chapter 4 Checkpoint
        checkpoint = true;
        System.out.println("\n*Checkpoint Reached!*");

        //Reset money, items, level, experience, health, stats, and moves of each Littlemon, and places visited
        item3.amount = player.savedItem3;
        item4.amount = player.savedItem4;
        player.moneyEarned = player.savedMoney;

        for (int i = 0; i < player.littlemonCaught; i++) {
            player.littlemonTeam[i].recover();
        }

        visitHouse = false;
        visitGuide = false;
        visitSchool = false;

        //Exploring Verdant City
        System.out.println("With the man out of your way, you can finally start exploring the city.");
        Thread.sleep(1500);
        System.out.println("\nYou should probably head to the Littlemon Center first to rest up your tired Littlemon " +
                "after a long journey.");
        Thread.sleep(1500);

        //Navigation Start - Verdant City
        navigation(verdantCity, "Verdant City", 12, 3);

    }

    /**********************
     * Method Name: ending
     * Ending segment of the game - Prints player data and prompt to end the game
     **********************/
    public static void ending() {

        System.out.println("\n\n");
        System.out.println("=======================================================================================");
        System.out.println("\t\tLittlemon Combustion Red Part 1 - Complete!");
        System.out.println("=======================================================================================");

        System.out.println("\nPlayer Data");
        System.out.format("\n\n%-23s %25s", "Player", player.name);
        System.out.format("\n%-23s %25s", "Badges", item5.amount);
        System.out.format("\n%-23s %25s", "Littledex", player.numberOfLittlemonSeen + "/" + "151");

        System.out.println("\n\nRoster:");
        for (int i = 0; i<player.littlemonCaught; i++) {
            System.out.format("%-23s %25s", "| " +
                    player.littlemonTeam[i].name + " - " +
                    player.littlemonTeam[i].nickname, " | | Lv" +
                    player.littlemonTeam[i].level + "\t" +
                    player.littlemonTeam[i].gender + "\tHP: " +
                    player.littlemonTeam[i].currentHealth + "/" +
                    player.littlemonTeam[i].health + " |\n");
        }

        //End the game
        nextChapter = 1;
        chapter++;

    }


    /**********************
     * Method Name: start
     * Prints the beginning of each chapter when started
     **********************/
    public static void start() {

        System.out.println("\n\n");
        System.out.println("===========================================================================================");
        System.out.println("\t\t" + chapterName[chapter] + " - Start");
        System.out.println("===========================================================================================");
        System.out.println("-------------------------------------------------------------------------------------------");

    }

    /**********************
     * Method Name: fail
     * Allows the player the choice of restarting the chapter or quitting when choosing a bad route
     * @throws InterruptedException - for returning to main method
     **********************/
    public static void fail() throws InterruptedException {

        do {

            System.out.println("\n\n(Type 1 to restart the chapter/go back to last checkpoint or 0 to quit.)");
            nextChapter = scanN.nextInt();

        } while (nextChapter != 1 && nextChapter != 0);

        //If player quits, end game
        if (nextChapter == 0) {
            System.exit(0);
        }

        //Return to main method
        main(null);

    }

    /**********************
     * Method Name: complete
     * Prints the ending of each chapter when completed. Allows the player the choice of going to the next chapter or
     * quitting
     **********************/
    public static void complete() {

        do {

            System.out.println("\n\n");
            System.out.println("=======================================================================================");
            System.out.println("\t\t" + chapterName[chapter] + " - Complete!");
            System.out.println("=======================================================================================");
            System.out.println("\n(Type 1 to continue or 0 to quit.)");
            nextChapter = scanN.nextInt();

        } while (nextChapter != 1 && nextChapter != 0);

        //If player wants to continue, reset checkpoint and increase chapter by 1, else end game
        if (nextChapter == 1) {
            checkpoint = false;
            chapter++;
        } else {
            chapter = 6;
        }


    }

    /**********************
     * Method Name: navigation
     * Prints the dialogue and menu when navigating a map. Allows the player to navigate using 1-4, and ends when the
     * player hits an obstacle or gets to their destination. Also allows the player to check their map and their
     * Littledex once they obtain each one respectively
     * @param map - The reference grid/2D Array of the map
     * @param playerPositionRow - The x position of the player based on the 2D Array of map
     * @param playerPositionColumn - The y position of the player based on the 2D Array of map
     * @throws InterruptedException - for thread.sleep
     *********************/
    public static void navigation(String[][] map, String mapName, int playerPositionRow, int playerPositionColumn) throws
            InterruptedException {

        //Initializing objects - HandlerData
        //Handlers in Route 1
        HandlerData youngster1 = new HandlerData("JoseJesus");
        HandlerData youngster2 = new HandlerData("JimJam");
        HandlerData youngster3 = new HandlerData("MatPat");
        HandlerData ace1 = new HandlerData("SinanSinan");
        youngster1.setLittlemon("Ratatouille");
        youngster2.setLittlemon("Mordecai");
        youngster3.setLittlemon("Ratatouille");
        youngster3.setLittlemon("Mordecai");
        ace1.setLittlemon("Techkey");

        //Wild Littlemon in Route 1
        HandlerData wild1 = new HandlerData("Ratatouille");
        HandlerData wild2 = new HandlerData("Mordecai");
        wild1.setLittlemon("Ratatouille");
        wild2.setLittlemon("Mordecai");

        //Declaring variables within navigation method
        int previousPositionRow, previousPositionColumn, encounterChance;
        boolean finish = false, battle1 = false, battle2 = false, battle3 = false, meetWorker = false, aceBattle = false;

        /*

            Map of Tallet Town:

            {"Tree", "Tree", "Tree",   "Exit", "Tree",   "Tree", "Tree"},
            {"Tree", "Path", "Path",   "Path", "Path",   "Path", "Tree"},
            {"Tree", "Path", "House1", "Path", "House2", "Path", "Tree"},
            {"Tree", "Path", "Path",   "Path", "Path",   "Path", "Tree"},
            {"Tree", "Path", "Tree",   "Path", "Lab",    "Path", "Tree"},
            {"Tree", "Path", "Path",   "Path", "Path",   "Path", "Tree"},
            {"Tree", "Tree", "Tree",   "Tree", "Tree",   "Tree", "Tree"}


            Map of Route 1:

            {"Tree", "Tree",    "Tree",  "Exit",     "Tree",     "Tree",  "Tree"},
            {"Tree", "Path",    "Path",  "Path",     "Handler3", "Path",  "Tree"},
            {"Tree", "Ledge",   "Tree",  "Ledge",    "Ledge",    "Grass", "Tree"},
            {"Tree", "Ace",     "Tree",  "Grass",    "Grass",    "Grass", "Tree"},
            {"Tree", "Ledge",   "Tree",  "Grass",    "Grass",    "Grass", "Tree"},
            {"Tree", "Path",    "Path",  "Path",     "Path",     "Path",  "Tree"},
            {"Tree", "Ledge",   "Ledge", "Tree",     "Tree",     "Grass", "Tree"},
            {"Tree", "Worker",  "Path",  "Path",     "Handler2", "Grass", "Tree"},
            {"Tree", "Ledge",   "Path",  "Ledge",    "Ledge",    "Ledge", "Tree"},
            {"Tree", "Path",    "Path",  "Path",     "Path",     "Path",  "Tree"},
            {"Tree", "Tree",    "Tree",  "Tree",     "Grass",    "Ledge", "Tree"},
            {"Tree", "Path",    "Path",  "Handler1", "Path",     "Path",  "Tree"},
            {"Tree", "Grass",   "Ledge", "Ledge",    "Ledge",    "Ledge", "Tree"},
            {"Tree", "Grass",   "Grass", "Grass",    "Grass",    "Grass", "Tree"},
            {"Tree", "Tree",    "Tree",  "Enter",    "Tree",     "Tree",  "Tree"}
            {"Tree", "Tree",    "Tree",  "Block",    "Tree",     "Tree",  "Tree"}


            Map of Verdant City:

            {"Tree",  "Tree",   "Block", "Tree",   "Tree",  "Tree",  "Tree"},
            {"Tree",  "Tree",   "Path",  "Path",   "Path",  "Path",  "Tree"},
            {"Tree",  "Tree",   "Path",  "Path",   "Gym",   "Path",  "Tree"},
            {"Tree",  "Tree",   "Path",  "House4", "Path",  "Path",  "Tree"},
            {"Tree",  "Tree",   "Path",  "Path",   "Ledge", "Ledge", "Tree"},
            {"Tree",  "Tree",   "Path",  "Path",   "Path",  "Path",  "Tree"},
            {"Block", "Path",   "Path",  "House3", "Mart",  "Path",  "Tree"},
            {"Tree",  "Tree",   "Path",  "Path",   "Path",  "Path",  "Tree"},
            {"Tree",  "School", "Path",  "Center", "Tree",  "Path",  "Tree"},
            {"Tree",  "Path",   "Path",  "Path",   "Path",  "Path",  "Tree"},
            {"Tree",  "Ledge",  "Path",  "Ledge",  "Ledge", "Ledge", "Tree"},
            {"Tree",  "Path",   "Path",  "Path",   "Path",  "Path",  "Tree"},
            {"Tree",  "Tree",   "Tree",  "Enter",  "Tree",  "Tree",  "Tree"}
            {"Tree",  "Tree",   "Tree",  "Block",  "Tree",  "Tree",  "Tree"}

         */

        //Set previous position to player current position
        previousPositionRow = playerPositionRow;
        previousPositionColumn = playerPositionColumn;

        do {

            do {

                //Print Navigation Menu
                System.out.println("\nWhere to go?");
                System.out.println("1: Up");
                System.out.println("2: Left");
                System.out.println("3: Right");
                System.out.println("4: Down");
                if (receiveLittledex) {
                    System.out.println("5: Littledex");
                }
                if (receiveMap) {
                    System.out.println("6: Map");
                }
                playerPrompt = scanN.nextInt();

            } while (playerPrompt < 1 || playerPrompt > 6);

            //Navigation Maze - Start
            switch (playerPrompt) {

                //Up Path
                case 1:
                    playerPositionRow -= 1;
                    System.out.println("\nYou walked up.");
                    Thread.sleep(1500);
                    break;


                //Left Path
                case 2 :
                    playerPositionColumn -= 1;
                    System.out.println("\nYou walked left.");
                    Thread.sleep(1500);
                    break;

                //Right Path
                case 3 :
                    playerPositionColumn += 1;
                    System.out.println("\nYou walked right.");
                    Thread.sleep(1500);
                    break;

                //Down Path
                case 4 :
                    playerPositionRow += 1;
                    System.out.println("\nYou walked down.");
                    Thread.sleep(1500);
                    break;

                //Print Littledex
                case 5 :
                    if (receiveLittledex) {
                        player.littledex();
                        playerPrompt = 0;
                    }
                    break;

                //Print Map of current location
                case 6 :
                    if (receiveMap) {
                        player.map(map, mapName, playerPositionRow, playerPositionColumn);
                        playerPrompt = 0;
                    }
                    break;

            }

            //Check player position on the map
            switch (map[playerPositionRow][playerPositionColumn]) {

                case "Block" :
                    if (playerPrompt == 1) {
                        System.out.println("\nThat way leads to Route 2... you cannot go further until you have " +
                                "obtained the city's gym pin.");
                    } else if (playerPrompt == 2) {
                        System.out.println("\nThat way leads to Victory Road... you cannot go there unless you have " +
                                "all 8 of the Onto Region's gym pins.");
                    } else {
                        if (chapter == 3) {
                            System.out.println("\nThat way leads back to Tallet Town...");
                        } else {
                            System.out.println("\nThat way leads back to Route 1...");
                        }
                    }
                    Thread.sleep(1500);
                    System.out.println("\nYou walked back.");
                    Thread.sleep(1500);
                    playerPositionRow = previousPositionRow;
                    playerPositionColumn = previousPositionColumn;
                    break;

                case "House1" :
                    if (playerPrompt == 1) {

                        if (chapter == 3 && !visitMom) {
                            //Call house1 method
                            house1();
                        } else {
                            //If re-entering your house
                            System.out.println("\nYou somehow went back inside your house... you walked back");
                            Thread.sleep(1500);
                        }

                        playerPositionRow = previousPositionRow;
                        playerPositionColumn = previousPositionColumn;

                    } else {
                        //If entering through the side
                        System.out.println("\nYou face-planted onto a building...");
                        Thread.sleep(1500);
                        fail();
                    }
                    break;

                case "House2" :
                    if (playerPrompt == 1) {

                        if (chapter == 3 && !visitRivalSister) {
                            //Call house2 method
                            house2();
                        } else {
                            //If re-entering the house
                            System.out.println("\nYou are at your rival's house... you walked back");
                            Thread.sleep(1500);
                        }

                        playerPositionRow = previousPositionRow;
                        playerPositionColumn = previousPositionColumn;

                    } else {
                        //If entering through the side
                        System.out.println("\nYou face-planted onto a building...");
                        Thread.sleep(1500);
                        fail();
                    }
                    break;

                case "House3" :
                    if (playerPrompt == 1) {

                        if (!visitHouse) {
                            //Call house3 method
                            house3();
                        } else {
                            //If re-entering the house
                            System.out.println("\nYou've already been here... you walked back.");
                            Thread.sleep(1500);
                        }

                        playerPositionRow = previousPositionRow;
                        playerPositionColumn = previousPositionColumn;

                    } else {
                        //If entering through the side
                        System.out.println("\nYou face-planted onto a building...");
                        Thread.sleep(1500);
                        fail();
                    }
                    break;

                case "House4" :
                    if (playerPrompt == 1) {

                        if (!visitGuide) {
                            //Call house4 method
                            house4();
                        } else {
                            //If re-entering the house
                            System.out.println("\nYou've already been here... you walked back.");
                            Thread.sleep(1500);
                        }

                        playerPositionRow = previousPositionRow;
                        playerPositionColumn = previousPositionColumn;

                    } else {
                        //If entering through the side
                        System.out.println("\nYou face-planted onto a building...");
                        Thread.sleep(1500);
                        fail();
                    }
                    break;

                case "Tree" :
                    System.out.println("\nYou fell into a tree...");
                    Thread.sleep(1500);
                    fail();

                case "Ledge" :
                    if (playerPrompt == 4) {
                        System.out.println("\nYou hopped over the ledge.");
                        Thread.sleep(1500);
                        playerPositionRow += 1;

                        if (map[playerPositionRow][playerPositionColumn].equalsIgnoreCase("Ace")) {
                            if (!aceBattle) {
                                aceBattle = true;
                                System.out.println("\nAs you somehow stumbled here, you felt an ominous presence " +
                                        "watching over you...");
                                Thread.sleep(1500);
                                System.out.println("\n...");
                                Thread.sleep(1500);
                                System.out.println("\n???: Hello there.");
                                Thread.sleep(1500);

                                //Battle Start - Ace Handler SinanSinan
                                battle("Ace Handler ", ace1.name, ace1);

                                //Beating SinanSinan
                                System.out.println("\nSinanSinan: Wow, you're good.");
                                Thread.sleep(1500);
                                System.out.println("\nSinanSinan: Here, you are worthy...");
                                Thread.sleep(1500);

                                //You receive Kipachu
                                //Set Littlemon1 in HandlerData class for player object
                                player.setLittlemon("Techkey");
                                player.addToLittledex("Techkey");

                                System.out.println("\n" + player.name + " received the Techkey from Ace Handler " +
                                        "SinanSinan!");
                                Thread.sleep(1500);

                                do {

                                    System.out.println("\nDo you want to give a nickname to Techkey?");
                                    System.out.println("1: Yes.");
                                    System.out.println("2: No.");
                                    playerPrompt = scanN.nextInt();

                                } while (playerPrompt < 1 || playerPrompt > 2);

                                //Call setNickname mutator method in LittlemonData class through player's HandlerData
                                //class for its Littlemon object
                                player.littlemonTeam[player.littlemonCaught - 1].setNickname(playerPrompt);

                                System.out.println("\nThe mysterious Handler vanished...");
                                Thread.sleep(1500);

                            }

                        }
                    } else {
                        System.out.println("\nYou bumped into a ledge... you walked back.");
                        Thread.sleep(1500);
                        playerPositionRow = previousPositionRow;
                        playerPositionColumn = previousPositionColumn;
                    }
                    break;

                case "Grass" :
                    if (playerPrompt != 0) {
                        //Random Encounters
                        encounterChance = rand.nextInt(100);

                        if (encounterChance <= 50) {
                            //If Wild Littlemon appears
                            System.out.println("\nYou started to hear the rustling of the " +
                                    "long grass.");
                            Thread.sleep(1500);

                            encounterChance = rand.nextInt(100);

                            //Generate chance of encountering one of two Littlemon
                            if (encounterChance <= 50) {
                                wild1.littlemonTeam[0].heal();
                                //Battle Start - Wild Ratatouille
                                battle("Wild ", wild1.name, wild1);
                            } else {
                                wild2.littlemonTeam[0].heal();
                                //Battle Start - Wild Mordecai
                                battle("Wild ", wild2.name, wild2);
                            }

                        } else {
                            System.out.println("\n" + "*rustle*");
                            Thread.sleep(1500);
                        }

                    }
                    break;

                case "Worker" :
                    if (!meetWorker) {
                        meetWorker = true;
                        System.out.println("\nYou hear someone approaching you... but it doesn't seem like a Handler.");
                        Thread.sleep(1500);
                        System.out.println("\nWorker: Hi! I work at a Littlemon Mart!");
                        Thread.sleep(1500);
                        System.out.println("\nWorker: It's part of a convenient chain selling all sorts of items.");
                        Thread.sleep(1500);
                        System.out.println("\nWorker: Please, visit us in Verdant City.");
                        Thread.sleep(1500);
                        System.out.println("\nWorker: I know, I'll give you a sample. Here you go!");
                        Thread.sleep(1500);

                        //Receive free Potion
                        item4.gainItems(1);

                        System.out.println("\nWorker: Its a Potion! Use it to restore your Littlemons' HP when hurt " +
                                "during battle.");
                        Thread.sleep(1500);
                        System.out.println("\nWorker: Please come see us if you need Little Balls for catching " +
                                "Littlemon.");
                        Thread.sleep(1500);
                    }
                    break;

                //Handler battles
                case "Handler1" :
                    if (!battle1) {
                        battle1 = true;
                        System.out.println("\nAs you were walking by, you hear someone running towards you.");
                        Thread.sleep(1500);
                        System.out.println("\n" + youngster1.name + ": You're a Littlemon Handler, and so am I!");
                        Thread.sleep(1500);
                        System.out.println("\n" + youngster1.name + ": Our eyes met, so battle we must!");
                        Thread.sleep(1500);

                        //Battle Start - Youngster JoseJesus
                        battle("Youngster ", youngster1.name, youngster1);
                    }
                    break;

                case "Handler2" :
                    if (!battle2) {
                        battle2 = true;
                        System.out.println("\nAs you were walking by, you hear someone running towards you.");
                        Thread.sleep(1500);
                        System.out.println("\n" + youngster2.name + ": I spy, with my little eye... A Handler!");
                        Thread.sleep(1500);
                        System.out.println("\n" + youngster2.name + ": A battle, please!");
                        Thread.sleep(1500);

                        //Battle Start - Youngster JimJam
                        battle("Youngster ", youngster2.name, youngster2);
                    }
                    break;

                case "Handler3" :
                    if (!battle3) {
                        battle3 = true;
                        System.out.println("\nAs you were walking by, you hear someone running towards you.");
                        Thread.sleep(1500);
                        System.out.println("\n" + youngster3.name + ": Let's have a Littlemon battle!");
                        Thread.sleep(1500);
                        System.out.println("\n" + youngster3.name + ": Humph! But I'm gonna win!");
                        Thread.sleep(1500);

                        //Battle Start - Youngster MatPat
                        battle("Youngster ", youngster3.name, youngster3);
                    }
                    break;

                case "Lab" :
                    if (playerPrompt == 1) {

                        if (chapter == 1) {
                            //Entering the Laboratory
                            System.out.println("\nYou stood before a door that had a sign " +
                                    "saying,");
                            Thread.sleep(1500);
                            System.out.println("\n=========================================" +
                                    "==========");
                            System.out.println("\tScientist Tim's Laboratory");
                            System.out.println("===========================================" +
                                    "========");
                            Thread.sleep(1500);
                            System.out.println("\nYou made it!");
                            Thread.sleep(1500);
                            finish = true;

                            //Chapter 1 Complete - Normal Route
                            complete();

                        } else if (chapter == 3) {
                            //If re-entering Laboratory
                            System.out.println("\nYou somehow went back inside the Laboratory... you walked back");
                            Thread.sleep(1500);
                            playerPositionRow = previousPositionRow;
                            playerPositionColumn = previousPositionColumn;
                        }

                    } else {
                        //If through the side
                        System.out.println("\nYou face-planted onto a building...");
                        Thread.sleep(1500);
                        fail();
                    }
                    break;

                case "Center" :
                    if (playerPrompt == 1) {
                        //Call littlemonCenter method
                        littlemonCenter();
                        playerPositionRow = previousPositionRow;
                        playerPositionColumn = previousPositionColumn;
                    } else {
                        //If entering through the side
                        System.out.println("\nYou face-planted onto a building...");
                        Thread.sleep(1500);
                        fail();
                    }
                    break;

                case "Mart" :
                    if (playerPrompt == 1) {
                        //Call littlemonMart method
                        littlemonMart();
                        playerPositionRow = previousPositionRow;
                        playerPositionColumn = previousPositionColumn;
                    } else {
                        //If entering through the side
                        System.out.println("\nYou face-planted onto a building...");
                        Thread.sleep(1500);
                        fail();
                    }
                    break;

                case "School" :
                    if (playerPrompt == 1) {

                        if (!visitSchool) {
                            //Call school method
                            school();
                        } else {
                            System.out.println("\nYou've already been here... you walked back.");
                        }

                        Thread.sleep(1500);
                        playerPositionRow = previousPositionRow;
                        playerPositionColumn = previousPositionColumn;

                    } else {
                        //If entering through the side
                        System.out.println("\nYou face-planted onto a building...");
                        Thread.sleep(1500);
                        fail();
                    }
                    break;

                case "Gym" :
                    if (playerPrompt == 1) {
                        //Call gym method
                        gym();

                        if (beatGym) {
                            finish = true;
                            //Chapter 4 Complete
                            complete();
                        }

                    } else {
                        //If entering through the side
                        System.out.println("\nYou face-planted onto a building...");
                        Thread.sleep(1500);
                        fail();
                    }
                    break;


                case "Exit" :
                    if (chapter == 1) {
                        //If entering during chapter 1
                        System.out.println("\nYou stood before a patch of long green " +
                                "grass.");
                        Thread.sleep(1500);
                        System.out.println("\nOut of curiousity, you decided to walk " +
                                "into it...");
                        Thread.sleep(1500);
                        System.out.println("\n???: Hey! Wait! Don't go out!");
                        Thread.sleep(1500);
                        System.out.println("\nA man in a white lab coat ran towards " +
                                "you.");
                        Thread.sleep(1500);
                        System.out.println("\n???: It's unsafe! Wild Littlemon live in " +
                                "the long grass!");
                        Thread.sleep(1500);
                        System.out.println("\nYou started to hear the rustling of the " +
                                "long grass.");
                        Thread.sleep(1500);
                        System.out.println("\n=========================================" +
                                "==========");
                        System.out.println("\tA wild Kipachu appeared!");
                        System.out.println("===========================================" +
                                "========");
                        Thread.sleep(1500);
                        System.out.println("\n=========================================" +
                                "==========");
                        System.out.println("\tScientist Tim used one Little Ball!");
                        System.out.println("===========================================" +
                                "========");
                        Thread.sleep(1500);
                        System.out.println("\n=========================================" +
                                "==========");
                        System.out.println("\t*wiggle*");
                        System.out.println("===========================================" +
                                "========");
                        Thread.sleep(1500);
                        System.out.println("\n=========================================" +
                                "==========");
                        System.out.println("\t*wiggle*");
                        System.out.println("===========================================" +
                                "========");
                        Thread.sleep(1500);
                        System.out.println("\n=========================================" +
                                "==========");
                        System.out.println("\t*wiggle*");
                        System.out.println("===========================================" +
                                "========");
                        Thread.sleep(1500);
                        System.out.println("\n=========================================" +
                                "==========");
                        System.out.println("\t*click!*");
                        System.out.println("===========================================" +
                                "========");
                        Thread.sleep(1500);
                        System.out.println("\n=========================================" +
                                "==========");
                        System.out.println("\tAll right! Kipachu was caught!");
                        System.out.println("===========================================" +
                                "========");
                        Thread.sleep(1500);
                        System.out.println("\n???: Whew... A Littlemon can appear " +
                                "anytime in the long grass.");
                        Thread.sleep(1500);
                        System.out.println("\n???: You need your own Littlemon for " +
                                "your protection.");
                        Thread.sleep(1500);
                        System.out.println("\n???: I know! Here come with me!");
                        Thread.sleep(1500);
                        kipachuPath = true;
                        finish = true;

                        //Chapter 1 Complete - Kipachu Route
                        complete();

                    } else if (chapter == 3 && !checkpoint) {
                        //If entering during chapter 3 - Route 1
                        System.out.println("\nYou arrived at Route 1.");
                        Thread.sleep(1500);
                        checkpoint = true;
                        finish = true;

                    } else if (chapter == 3){
                        //If entering during chapter 3 - Verdant City
                        System.out.println("\nYou arrived at Verdant City.");
                        Thread.sleep(1500);
                        finish = true;
                        //Chapter 3 Complete
                        complete();

                     }
                    break;

            }

            //Set previous position to player current position
            previousPositionRow = playerPositionRow;
            previousPositionColumn = playerPositionColumn;

        } while (!finish);

        //Return to main method
        main(null);

    }

    /**********************
     * Method Name: house1
     * Prints House1 from navigation method
     * @throws InterruptedException - for thread.sleep
     *********************/
    public static void house1() throws InterruptedException {

        //Visiting your house
        visitMom = true;
        System.out.println("\nYou arrived back at your house.");
        Thread.sleep(1500);
        System.out.println("\nAs you entered, you see your Mom watching television.");
        Thread.sleep(1500);
        System.out.println("\nMom: Oh! " + player.name + "! You're back!");
        Thread.sleep(1500);
        System.out.println("\nMom: So what Littlemon did you get?");
        Thread.sleep(1500);

        if (!kipachuPath) {

            System.out.println("\nYou sent out " + player.littlemonTeam[0].nickname + " to show " +
                    "your mom.");
            Thread.sleep(1500);

            //Littlemon cry depending on chosen starter
            switch (player.littlemonTeam[0].name) {

                case "Herbasaur":
                    System.out.println("\n" + player.littlemonTeam[0].nickname + ": Herba! " +
                            "Herbasaur!");
                    Thread.sleep(1500);
                    break;

                case "Scorchmander":
                    System.out.println("\n" + player.littlemonTeam[0].nickname + ": Scorchmander! " +
                            "Scorch! Scorch!");
                    Thread.sleep(1500);
                    break;

                case "Spurtle":
                    System.out.println("\n" + player.littlemonTeam[0].nickname + ": Spurtle!");
                    Thread.sleep(1500);
                    break;

            }

        } else {
            System.out.println("\n" + player.littlemonTeam[0].nickname + ": Ki! Kipachu!");
            Thread.sleep(1500);
        }

        System.out.println("\nMom: Aw! That's a cute Littlemon you have.");
        Thread.sleep(1500);
        System.out.println("\nMom: So, Scientist Tim must have given it to you?");
        Thread.sleep(1500);
        System.out.println("\n" + player.name + ": Yup! He also has a task for me to capture all " +
                "the Littlemon in Onto and become champion!");
        Thread.sleep(1500);
        System.out.println("\nMom: I see. That sounds very challenging.");
        Thread.sleep(1500);
        System.out.println("\nMom: But when someone makes that kind of request of you, it must be " +
                "important!");
        Thread.sleep(1500);
        System.out.println("\nMom: With your mother and your Littlemon by your side, you've got " +
                "this kiddo!");
        Thread.sleep(1500);
        System.out.println("\nMom: Oh! But before you leave, you should take quick rest.");
        Thread.sleep(1500);

        //Reset health and power points
        player.littlemonTeam[0].heal();

        System.out.println("\nYou went to your room and slept. Your Littlemon's health is fully " +
                "restored!");
        Thread.sleep(1500);
        System.out.println("\nMom: So, ready to go?");
        Thread.sleep(1500);

        if (receiveMap) {
            System.out.println("\nMom: Oh wow, you already have a map? You're prepared!");
            Thread.sleep(1500);
            System.out.println("\nMom: I guess that you don't have to keep coming back to me for " +
                    "directions anymore ha ha.");
            Thread.sleep(1500);
        } else {
            System.out.println("\nMom: Oh, I almost forgot! You must get a town map from " +
                    rival.name + "'s sister.");
            Thread.sleep(1500);
            System.out.println("\nMom: So that you don't have to keep coming back to me for " +
                    "directions.");
            Thread.sleep(1500);

            do {

                System.out.println("\nMom: Do you know where their house is?");
                System.out.println("1: Yeah!");
                System.out.println("2: Nope.");
                playerPrompt = scanN.nextInt();

                switch (playerPrompt) {

                    case 1:
                        System.out.println("\n" + player.name + ": Yeah, I know where it is.");
                        Thread.sleep(1500);
                        System.out.println("\nMom: Wow you're prepared!");
                        Thread.sleep(1500);
                        break;

                    case 2:
                        System.out.println("\n" + player.name + ": Nope, I have no clue...");
                        Thread.sleep(1500);

                        do {

                            System.out.println("\nMom: To get to their house from here, take 2 " +
                                    "steps left.");
                            Thread.sleep(1500);
                            System.out.println("\nMom: Then, 1 step up.");
                            Thread.sleep(1500);

                            do {

                                System.out.println("\nMom: Did you got all that?");
                                System.out.println("1: Yep.");
                                System.out.println("2: Nope.");
                                playerPrompt = scanN.nextInt();

                                switch (playerPrompt) {

                                    case 1:
                                        System.out.println("\n" + player.name + ": Yep I got it.");
                                        Thread.sleep(1500);
                                        break;

                                    case 2:
                                        System.out.println("\n" + player.name + ": Um, can you " +
                                                "repeat it again?");
                                        Thread.sleep(1500);
                                        break;

                                }

                            } while (playerPrompt < 1 || playerPrompt > 2);

                        } while (playerPrompt == 2);

                }

            } while (playerPrompt < 1 || playerPrompt > 2);

        }

        //Leaving the house (again)
        System.out.println("\nMom: Ok good luck out there!");
        Thread.sleep(1500);
        System.out.println("\nMom: Remember to visit home from time to time!");
        Thread.sleep(1500);
        System.out.println("\nYou hugged your mother goodbye and walked back outside.");
        Thread.sleep(1500);

    }

    /**********************
     * Method Name: house2
     * Prints House2 from navigation method
     * @throws InterruptedException - for thread.sleep
     *********************/
    public static void house2() throws InterruptedException {

        //Visiting rival's house
        visitRivalSister = true;
        System.out.println("\nYou arrived at your rival's house.");
        Thread.sleep(1500);
        System.out.println("\nAs you entered, you see " + rival.name + "'s sister.");
        Thread.sleep(1500);
        System.out.println("\nPeach: Hi there! I'm assuming grandpa asked you to run an errand?");
        Thread.sleep(1500);
        System.out.println("\nPeach: Gee, that's lazy of him. Here, this will help you.");
        Thread.sleep(1500);

        //Receive Map
        receiveMap = true;
        item2.gainItems(1);

        //Leaving rival's house
        System.out.println("\nPeach: Oh, and I heard what " + rival.name + " said.");
        Thread.sleep(1500);
        System.out.println("\nPeach: Make sure you kick his butt the next time you see him!");
        Thread.sleep(1500);
        System.out.println("\nPeach: And please watch over him.");
        Thread.sleep(1500);
        System.out.println("\nYou waved goodbye to Daisy walked back outside.");
        Thread.sleep(1500);

    }

    /**********************
     * Method Name: house3
     * Prints House3 from navigation method
     * @throws InterruptedException - for thread.sleep
     *********************/
    public static void house3() throws InterruptedException {

        //Visiting old woman's house
        visitHouse = true;
        System.out.println("\nYou arrived at a house.");
        Thread.sleep(1500);
        System.out.println("\nAs you entered, you see an old woman.");
        Thread.sleep(1500);
        System.out.println("\n???: Well hello there, young traveller.");
        Thread.sleep(1500);

        do {

            System.out.println("\n???: So what brings you here?");
            System.out.println("1: Visiting...");
            System.out.println("2: I don't know...");
            System.out.println("3: A battle!");
            playerPrompt = scanN.nextInt();

            switch (playerPrompt) {

                case 1:
                    System.out.println("\n" + player.name + ": Oh, I was just exploring the city, " +
                            "and I wanted to visit your place.");
                    Thread.sleep(1500);
                    System.out.println("\n???: I see... well there isn't much here. So I suggest " +
                            "that you leave now.");
                    Thread.sleep(1500);
                    break;

                case 2:
                    System.out.println("\n" + player.name + ": Uh... I was just wandering around " +
                            "the city and then I happened to stumble upon here...");
                    Thread.sleep(1500);
                    System.out.println("\n???: I see... well scram! Get out of my house!");
                    Thread.sleep(1500);
                    break;

                case 3:
                    System.out.println("\n" + player.name + ": I challenge you to a battle!");
                    Thread.sleep(1500);
                    System.out.println("\n???: Haha! That sure takes me back.");
                    Thread.sleep(1500);
                    System.out.println("\n???: I would love to challenge you young one, but " +
                            "because of my old age, I'm unable to do so.");
                    Thread.sleep(1500);
                    System.out.println("\n???: I know! How about I help you on your journey by " +
                            "giving you this!");
                    Thread.sleep(1500);

                    //Receive free Potion
                    item4.gainItems(1);

                    System.out.println("\n" + player.name + ": Wow! Thanks!");
                    Thread.sleep(1500);
                    System.out.println("\n???: No worries! Now off you go!");
                    Thread.sleep(1500);
                    break;

            }

        } while (playerPrompt < 1 || playerPrompt > 3);

        //Leaving old woman's house
        System.out.println("\nYou left the building.");
        Thread.sleep(1500);

    }

    /**********************
     * Method Name: house4
     * Prints House4 from navigation method
     * @throws InterruptedException - for thread.sleep
     *********************/
    public static void house4() throws InterruptedException {

        //Visiting Guide Gent's house
        visitGuide = true;
        System.out.println("\nYou arrived at a house.");
        Thread.sleep(1500);
        System.out.println("\nAs you entered, you see a familiar face...");
        Thread.sleep(1500);
        System.out.println("\nKen: Oh hey there, young Handler! I see you've found my house!");
        Thread.sleep(1500);

        do {

            System.out.println("\nKen: How are you finding the city so far?");
            System.out.println("1: Good.");
            System.out.println("2: Meh...");
            System.out.println("3: Terrible.");
            playerPrompt = scanN.nextInt();

            switch (playerPrompt) {

                case 1:
                    System.out.println("\n" + player.name + ": It's really nice. I love the " +
                            "greenery here.");
                    Thread.sleep(1500);
                    System.out.println("\nKen: Heh, well that is what this city is known for.");
                    Thread.sleep(1500);
                    break;

                case 2:
                    System.out.println("\n" + player.name + ": It's alright... I find it too bland " +
                            "here though...");
                    Thread.sleep(1500);
                    System.out.println("\nKen: Heh, well this is a natural space. This city isn't " +
                            "as bustling as Pooder, the city up north.");
                    Thread.sleep(1500);
                    break;

                case 3:
                    System.out.println("\n*Ken will remember that.*");
                    System.out.println(player.name + ": Erm... I don't like it. I think " +
                            "Tallet Town is way cooler.");
                    Thread.sleep(1500);
                    System.out.println("\nKen: Heh, thanks for your opinion.");
                    Thread.sleep(1500);
                    break;

            }

        } while (playerPrompt < 1 || playerPrompt > 3);

        System.out.println("\nKen: So I see you haven't challenged our gym yet.");
        Thread.sleep(1500);
        System.out.println("\nKen: A word of advice, make sure to train your Littlemon at the " +
                "school if you haven't done so already.");
        Thread.sleep(1500);
        System.out.println("\nKen: Also, bring lots of Potions. Buy some from the Littlemart if " +
                "you need more.");
        Thread.sleep(1500);
        System.out.println("\nKen: Our gym commander uses Rock type Littlemon, so they are sure to " +
                "to have a lot of health.");
        Thread.sleep(1500);
        System.out.println("\nKen: Also, if you only have your starter Littlemon with you, you are " +
                "in big trouble...");
        Thread.sleep(1500);

        //Leaving Guide Gent's house
        System.out.println("\nKen: Well anyways, good luck with your battle!");
        Thread.sleep(1500);
        System.out.println("\nYou waved goodbye to Ken walked back outside.");
        Thread.sleep(1500);

    }

    /**********************
     * Method Name: littlemonCenter
     * Prints the Littlemon Center menu from navigation method
     * @throws InterruptedException - for thread.sleep
     *********************/
    public static void littlemonCenter() throws InterruptedException {

        //Entering the Littlemon Center
        System.out.println("\nYou went inside the Littlemon Center and walked towards the counter with " +
                "a lady behind it.");
        Thread.sleep(1500);
        System.out.println("\nNurse Delight: Hello! Welcome to the Littlemon Center.");
        Thread.sleep(1500);
        System.out.println("\nNurse Delight: We restore your tired Littlemon to full health.");
        Thread.sleep(1500);

        do {

            System.out.println("\nNurse Delight: Would you like to rest your Littlemon?");
            System.out.println("1: Yes.");
            System.out.println("2: No.");
            playerPrompt = scanS.nextInt();

            switch (playerPrompt) {

                case 1:
                    //Heal Littlemon in party
                    System.out.println("\n" + player.name + ": Yes please.");
                    Thread.sleep(1500);
                    System.out.println("\nYou handed your Littlemon to the lady.");
                    Thread.sleep(1500);
                    System.out.println("\nNurse Delight: OK, I'll take your Littlemon for a few " +
                            "seconds.");
                    Thread.sleep(1500);

                    //Reset health and power points of all Littlemon in party
                    for (int i = 0; i < player.littlemonCaught; i++) {
                        player.littlemonTeam[i].heal();
                    }

                    System.out.println("\nNurse Delight: Thank you for waiting. We've restored your " +
                            "Littlemon to full health.");
                    Thread.sleep(1500);
                    break;

                case 2:
                    System.out.println("\n" + player.name + ": No thanks.");
                    Thread.sleep(1500);
                    System.out.println("\nNurse Delight: Then why did you come here?");
                    Thread.sleep(1500);
                    break;

            }

        } while (playerPrompt < 1 || playerPrompt > 2);

        //Leaving the Littlemon Center
        System.out.println("\nNurse Delight: We hope to see you again!");
        Thread.sleep(1500);
        System.out.println("\nYou left the building.");
        Thread.sleep(1500);

    }

    /**********************
     * Method Name: littlemonMart
     * Prints the Littlemon Mart menu from navigation method
     * @throws InterruptedException - for thread.sleep
     *********************/
    public static void littlemonMart() throws InterruptedException {

        //Declaring variables within navigation method
        int quantity, moneyOwed;
        boolean cashierGreeting;

        //Entering the Littlemon Mart
        cashierGreeting = true;
        System.out.println("\nYou went inside the Littlemon Mart and walked towards the counter with a " +
                "lady behind it.");
        Thread.sleep(1500);
        System.out.println("\nCashier: Hi there! Welcome to the Littlemon Mart!");
        Thread.sleep(1500);

        do {

            if (cashierGreeting) {
                System.out.println("\nCashier: How may I help you?");
                cashierGreeting = false;
            } else {
                System.out.println("\nCashier: Is there anything else I may do for you?");
            }
            System.out.println("1: Buy.");
            System.out.println("2. See ya!");
            playerPrompt = scanS.nextInt();

            switch (playerPrompt) {

                case 1:
                    //Buy something
                    System.out.println("\n" + player.name + ": I would like to purchase something.");
                    Thread.sleep(1500);
                    System.out.println("\nCashier: Here is what we have in stock today:");
                    Thread.sleep(1500);

                    do {

                        //Print shopping menu
                        System.out.println("\n----------------------------------------------------");
                        System.out.println("Your Money: $" + player.moneyEarned);
                        System.out.format("\n%-23s %25s", "1. Little Ball", "$200");
                        System.out.format("\n%-23s %25s", "2. Potion", "$300");
                        System.out.println("\n----------------------------------------------------");
                        System.out.println("\n(Type '0' to return.)");
                        playerPrompt = scanN.nextInt();

                        switch (playerPrompt) {
                            case 0 :
                                break;

                            //Buy Little Ball(s)
                            case 1 :

                                do {

                                    System.out.println("\nCashier: " + item3.name + "? Certainly. How " +
                                            "many would you like?");
                                    quantity = scanN.nextInt();

                                } while (quantity < 0);

                                if (quantity == 0) {
                                    System.out.println("\nCashier: Oh changed your mind? That's fine.");
                                    Thread.sleep(1500);
                                    break;
                                }

                                do {

                                    //Calculate price
                                    moneyOwed = 200 * quantity;

                                    System.out.println("\nCashier: For " + quantity + ", that will be $" +
                                            moneyOwed + ", please.");
                                    System.out.println("1: Yes.");
                                    System.out.println("2: No.");
                                    playerPrompt = scanN.nextInt();

                                    switch (playerPrompt) {

                                        case 1 :
                                            if (player.moneyEarned >= moneyOwed) {
                                                //Receive items if player has enough cash
                                                player.moneyEarned -= moneyOwed;
                                                item3.gainItems(quantity);
                                                System.out.println("\nHere you are! Thank you!");
                                                Thread.sleep(1500);
                                            } else {
                                                System.out.println("\nCashier: Sorry, you don't have " +
                                                        "enough cash...");
                                                Thread.sleep(1500);
                                                break;
                                            }
                                            break;

                                        case 2 :
                                            System.out.println("\nCashier: Oh changed your mind? " +
                                                    "That's fine.");
                                            Thread.sleep(1500);
                                            break;

                                    }

                                } while (playerPrompt < 1 || playerPrompt > 2);
                                break;

                            //Buy Potion(s)
                            case 2 :
                                do {

                                    System.out.println("\nCashier: " + item4.name + "? Certainly. How " +
                                            "many would you like?");
                                    quantity = scanN.nextInt();

                                } while (quantity < 0);

                                if (quantity == 0) {
                                    System.out.println("\nCashier: Oh changed your mind? That's fine.");
                                    Thread.sleep(1500);
                                    break;
                                }

                                do {

                                    //Calculate price
                                    moneyOwed = 300 * quantity;

                                    System.out.println("\nCashier: For " + quantity + ", that will be $" +
                                            moneyOwed + ", please.");
                                    System.out.println("1: Yes.");
                                    System.out.println("2: No.");
                                    playerPrompt = scanN.nextInt();

                                    switch (playerPrompt) {

                                        case 1 :
                                            if (player.moneyEarned >= moneyOwed) {
                                                //Receive items if player has enough cash
                                                player.moneyEarned -= moneyOwed;
                                                item4.gainItems(quantity);
                                                System.out.println("\nHere you are! Thank you!");
                                                Thread.sleep(1500);
                                            } else {
                                                System.out.println("\nCashier: Sorry, you don't have " +
                                                        "enough cash...");
                                                Thread.sleep(1500);
                                                break;
                                            }
                                            break;

                                        case 2 :
                                            System.out.println("\nCashier: Oh changed your mind? " +
                                                    "That's fine.");
                                            Thread.sleep(1500);
                                            break;

                                    }

                                } while (playerPrompt < 1 || playerPrompt > 2);
                                break;

                        }

                    } while (playerPrompt != 0);

                case 2:
                    break;

            }

        } while (playerPrompt != 2);

        //Leaving the Littlemon Mart
        System.out.println("\n" + player.name + ": That's all for now.");
        Thread.sleep(1500);
        System.out.println("\nCashier: Please come again!");
        Thread.sleep(1500);
        System.out.println("\nYou left the building.");
        Thread.sleep(1500);

    }

    /**********************
     * Method Name: school
     * Prints the School menu from navigation method
     * @throws InterruptedException - for thread.sleep
     *********************/
    public static void school() throws InterruptedException {

        //Initializing objects - HandlerData
        HandlerData student1 = new HandlerData("Jerome");
        HandlerData student2 = new HandlerData("Mitch");
        student1.setLittlemon("Heimlich");
        student2.setLittlemon("Beedle");

        //Visiting the Handler's School
        System.out.println("\nYou went inside the school and saw a Teacher teaching a group of " +
                "children a little younger than you are.");
        Thread.sleep(1500);
        System.out.println("\nTeacher: Oh hello there! Come on in! Welcome to our classroom!");
        Thread.sleep(1500);
        System.out.println("\nTeacher: We were just in a middle of doing a lesson here...");
        Thread.sleep(1500);

        do {

            System.out.println("\nTeacher: Why don't you join us? We're just about to start " +
                    "battles.");
            System.out.println("1: Sure.");
            System.out.println("2: Nope.");
            playerPrompt = scanS.nextInt();

            switch (playerPrompt) {

                case 1:
                    //Partake in battling students
                    System.out.println("\n" + player.name + ": Sure, I'm in.");
                    Thread.sleep(1500);
                    System.out.println("\nTeacher: Great! Let's get started then.");
                    Thread.sleep(1500);
                    System.out.println("\nTeacher: Jerome! Mitch! Come over here.");
                    Thread.sleep(1500);
                    System.out.println("\nYou see two students get up from their seats.");
                    Thread.sleep(1500);
                    System.out.println("\nJerome: What is it miss?");
                    Thread.sleep(1500);
                    System.out.println("\nTeacher: You two are going to have practice battles with " +
                            "this Handler over here.");
                    Thread.sleep(1500);
                    System.out.println("\nTeacher: We're going to show the class the art of " +
                            "Littlemon battling.");
                    Thread.sleep(1500);
                    System.out.println("\nTeacher: As they say, doing is the best way to learn!");
                    Thread.sleep(1500);
                    System.out.println("\nThe Teacher faced you.");
                    Thread.sleep(1500);
                    System.out.println("\nTeacher: You will be battling these two students of mine " +
                            "in a row without breaks in-between. Good luck!");
                    Thread.sleep(1500);
                    System.out.println("\nJerome: All right, I'm first! Go! My Littlemon, go!");
                    Thread.sleep(1500);

                    //Battle Start - School Kid Jerome
                    battle("School Kid ", student1.name, student1);

                    System.out.println("\nTeacher: Good job Jerome! Mitch! You're up next.");
                    Thread.sleep(1500);
                    System.out.println("\nMitch: I'm not confident that I can win... but I'll do " +
                            "my best!");
                    Thread.sleep(1500);

                    //Battle Start - School Kid Mitch
                    battle("School Kid ", student2.name, student2);

                    System.out.println("\nJerome: Wow! You're a tough Handler!");
                    Thread.sleep(1500);

                    //Add students' Littlemon to Littledex
                    player.addToLittledex(student1.littlemonTeam[0].name);
                    player.addToLittledex(student2.littlemonTeam[0].name);


                    System.out.println("\nTeacher: Splendid job you two. Although you did not win, " +
                            "you learned a lot about battling now.");
                    Thread.sleep(1500);
                    System.out.println("\nMitch: We certainly did!");
                    Thread.sleep(1500);
                    System.out.println("\nTeacher: As for you, terrific work! Here, I have " +
                            "something for you.");
                    Thread.sleep(1500);

                    //Receive free Potion
                    item4.gainItems(1);

                    System.out.println("\nTeacher: Hopefully this item will be useful in your " +
                            "future endeavours.");
                    Thread.sleep(1500);
                    System.out.println("\nTeacher: Thank you for attending!");
                    Thread.sleep(1500);
                    visitSchool = true;
                    playerPrompt = 1;
                    break;

                case 2:
                    System.out.println("\n" + player.name + ": No thanks.");
                    Thread.sleep(1500);
                    System.out.println("\nTeacher: Oh all right... have a wonderful day!");
                    Thread.sleep(1500);
                    break;

            }

        } while (playerPrompt < 1 || playerPrompt > 2);

        //Leaving the School
        System.out.println("\nYou left the building.");
        Thread.sleep(1500);

    }

    /**********************
     * Method Name: gym
     * Prints the Gym menu from navigation method
     * @throws InterruptedException - for thread.sleep
     *********************/
    public static void gym() throws InterruptedException {

        //Initializing objects - HandlerData
        HandlerData youngster4 = new HandlerData("Liam");
        HandlerData youngster5 = new HandlerData("Selina");
        HandlerData gymCommander1 = new HandlerData("Sylvester Stone");
        youngster4.setLittlemon("Rocky");
        youngster5.setLittlemon("Rocky");
        gymCommander1.setLittlemon("Rocky");
        gymCommander1.setLittlemon("Onixpected");

        //Arriving at the Gym
        System.out.println("\nYou arrived at the Gym.");
        Thread.sleep(1500);
        System.out.println("\nOnce you enter, you cannot go back outside until you defeat the gym " +
                "commander.");
        Thread.sleep(1500);

        do {

            System.out.println("\nAre you ready?");
            System.out.println("1: Yes.");
            System.out.println("2: No.");
            playerPrompt = scanS.nextInt();

            switch (playerPrompt) {

                case 1:
                    //Going inside and meeting the Gym Guide
                    System.out.println("\nYou went inside the gym.");
                    Thread.sleep(1500);
                    System.out.println("\nYour determination of becoming a Littlemon Master flows " +
                            "through you.");
                    Thread.sleep(1500);
                    System.out.println("\nAs you entered the gym, you see a man with sunglasses on " +
                            "looking at you.");
                    Thread.sleep(1500);
                    System.out.println("\n???: Hello! I'm Clyde. I'm the guide for Handlers " +
                            "challenging a Littlemon Gym.");
                    Thread.sleep(1500);
                    System.out.println("\nClyde: Do you want to dream big?");
                    Thread.sleep(1500);
                    System.out.println("\nClyde: Do you dare to dream of becoming the " +
                            "Littlemon champ?");
                    Thread.sleep(1500);
                    System.out.println("\n" + player.name + ": Of course! I want to be the very best, " +
                            "like no one ever was...");
                    Thread.sleep(1500);
                    System.out.println("\n" + player.name + ": To catch them is my real test, to train " +
                            "them is my cause!");
                    Thread.sleep(1500);
                    System.out.println("\nClyde: Ha! That's the spirit. Then look no further. If you " +
                            "manage to defeat our gym commander...");
                    Thread.sleep(1500);
                    System.out.println("\nClyde: You will be able to obtain your first gym pin!");
                    Thread.sleep(1500);
                    System.out.println("\nClyde: Collect all 8 of them, then you will be able to " +
                            "challenge the Littlemon League.");
                    Thread.sleep(1500);
                    System.out.println("\nClyde: But that's for a different time... Anyways Sylvester " +
                            "uses rock-hard rock type Littlemon.");
                    Thread.sleep(1500);
                    System.out.println("\nClyde: Rock type Littlemon are known to have a lot of " +
                            "health, so this will be a test of durability.");
                    Thread.sleep(1500);
                    System.out.println("\nClyde: But to get to Sylvester, you'll have to fight two " +
                            "of our gym handlers first...");
                    Thread.sleep(1500);
                    System.out.println("\nYou see a Handler coming to you.");
                    Thread.sleep(1500);
                    System.out.println("\nLiam: That's right! I'll be your first opponent.");
                    Thread.sleep(1500);

                    //Battle Start - Youngster Liam
                    battle("Youngster ", youngster4.name, youngster4);

                    System.out.println("\nYou see another Handler coming to you.");
                    Thread.sleep(1500);
                    System.out.println("\nSelina: Wow you're tough! I'll be your second opponent!");
                    Thread.sleep(1500);

                    //Battle Start - Youngster Selina
                    battle("Youngster ", youngster5.name, youngster5);

                    //Arrival of the Gym Commander
                    System.out.println("\nClyde: It seems you have defeated our gym handlers... " +
                            "well done!");
                    Thread.sleep(1500);
                    System.out.println("\nClyde: Sylvester! We have a new challenger here!");
                    Thread.sleep(1500);
                    System.out.println("\nYou see a tall man with big biceps coming towards you.");
                    Thread.sleep(1500);
                    System.out.println("\n???: So, you're here. I'm Sylvester Stone. I'm Verdant's " +
                            "gym commander.");
                    Thread.sleep(1500);
                    System.out.println("\nSylvester Stone: My rock-hard willpower is evident even in " +
                            "my Littlemon.");
                    Thread.sleep(1500);
                    System.out.println("\nSylvester Stone: My Littlemon are all rock hard, and have " +
                            "true-grit determination.");
                    Thread.sleep(1500);
                    System.out.println("\nSylvester Stone: That's right - my Littlemon are all the " +
                            "rock type!");
                    Thread.sleep(1500);
                    System.out.println("\nSylvester Stone: Fuhaha! You're going to challenge me " +
                            "knowing that you'll lose?");
                    Thread.sleep(1500);
                    System.out.println("\nSylvester Stone: That's the Handler's honor that compels " +
                            "you to challenge me.");
                    Thread.sleep(1500);
                    System.out.println("\nSylvester Stone: Fine, then! Show me your best!");
                    Thread.sleep(1500);
                    System.out.println("\nThe man rips off his shirt.");
                    Thread.sleep(1500);

                    //Battle Start - Gym Commander Sylvester Stone (Part 1 Final Battle)
                    battle("Commander ", gymCommander1.name, gymCommander1);

                    System.out.println("\nSylvester Stone: Ugh, I took you for granted, and so I lost.");
                    Thread.sleep(1500);
                    System.out.println("\nSylvester Stone: As proof of your victory, I confer on you.");
                    Thread.sleep(1500);
                    System.out.println("\nSylvester Stone: This is the (un)official Littlemon League Boulder Pin.");
                    Thread.sleep(1500);

                    //Receive Gym Pin
                    item5.gainItems(1);

                    //Ending
                    System.out.println("\nSylvester Stone: Just having the Boulder Pin makes your " +
                            "Littlemon more powerful.");
                    Thread.sleep(1500);
                    System.out.println("\nSylvester Stone: There are all kinds of Handlers in this " +
                            "huge world of ours.");
                    Thread.sleep(1500);
                    System.out.println("\nSylvester Stone: You appear to be very gifted as a Littlemon " +
                            "Handler.");
                    Thread.sleep(1500);
                    System.out.println("\nSylvester Stone: So let me make a suggestion. Go to the gym " +
                            "in Pooter and test your abilities.");
                    Thread.sleep(1500);
                    System.out.println("\nSylvester Stone: I'm sure that you'll be a fine Littlemon " +
                            "Champion one day.");
                    Thread.sleep(1500);
                    System.out.println("\nSylvester Stone: Keep working hard and stay determined!");
                    Thread.sleep(1500);

                    beatGym = true;

                case 2:
                    break;

            }

        } while (playerPrompt < 1 || playerPrompt > 2);

        //Leaving the Gym
        if (!beatGym) {
            System.out.println("\nYou decided not to enter.");
            Thread.sleep(1500);
        }


    }

    /**********************
     * Method Name: battle
     * Prints the dialogue and menu during battle with a handler or wild Littlemon. Allows the player the choice to use
     * an attack, use an item, switch into a different Littlemon in their party, or run (if it's a wild encounter). The
     * battle ends when the player's or foe's Littlemon faints first
     * @param foeClass - The class of the Handler whom you are battling (eg. Rival, Youngster)
     * @param foeName - The name of the Handler whom you are battling
     * @param foe - The foe's HandlerData
     * @throws InterruptedException - for thread.sleep
     *********************/
    public static void battle(String foeClass, String foeName, HandlerData foe) throws InterruptedException {

        //Initializing objects
        MoveData moveUsed = new MoveData("");

        //Declaring variables within battle method
        int foeChoice, runChance, currentLittlemon = 0, foeCurrentLittlemon = 0, previousLittlemon, faintedLittlemon = 0,
        foeFaintedLittlemon = 0;
        boolean tip1 = true, tip2 = true, turnEnds, skipTurn, foeFainted, endBattle = false;

        //Battle Start
        if (foeClass.equalsIgnoreCase("Wild ")) {
            //If Wild Encounter
            System.out.println("\n===================================================");
            System.out.println("\t" + foeClass + foeName + " appeared!");
            System.out.println("===================================================");
            Thread.sleep(1500);
        } else {
            //If Handler battle
            System.out.println("\n===================================================");
            System.out.println("\t" + foeClass + foeName + " would like to battle!");
            System.out.println("===================================================");
            Thread.sleep(1500);
            System.out.println("\n===================================================");
            System.out.println("\t" + foeClass + foeName + " sent out " + foe.littlemonTeam[foeCurrentLittlemon].name
                    + "!");
            System.out.println("===================================================");
            Thread.sleep(1500);
        }

        //Starting Littlemon - Choose one who has not fainted
        for (int i = 0; i<player.littlemonCaught; i++) {
            if (player.littlemonTeam[i].currentHealth > 0) {
                currentLittlemon = i;
                break;
            }
        }

        //Count fainted Littlemon
        for (int i = 0; i<player.littlemonCaught; i++) {
            if (player.littlemonTeam[i].currentHealth <= 0) {
                faintedLittlemon++;
            }
        }

        System.out.println("\n===================================================");
        System.out.println("\tGo! " + player.littlemonTeam[currentLittlemon].nickname + "!");
        System.out.println("===================================================");
        Thread.sleep(1500);

        if (tutorialBattle) {
            System.out.println("\nTim: Oh, for Pete's sake... So pushy, as always.");
            Thread.sleep(1500);
            System.out.println("\nTim: " + player.name + ".");
            Thread.sleep(1500);
            System.out.println("\nTim: You've never had a Littlemon battle before, have you?");
            Thread.sleep(1500);
            System.out.println("\nTim: A Littlemon battle is when Handlers pit their Littlemon against each other.");
            Thread.sleep(1500);
            System.out.println("\nTim: The Handler that makes the other Handler's Littlemon faint by lowering their HP " +
                    "to 0 wins.");
            Thread.sleep(1500);
            System.out.println("\nTim: But rather than talking about it, you'll learn more from experience.");
            Thread.sleep(1500);
            System.out.println("\nTim: Try battling and see for yourself.");
            Thread.sleep(1500);
        }

        do {

            //Player turn
            do {

                turnEnds = false;
                skipTurn = false;
                foeFainted = false;

                //Print Foe's and Player's Littlemon Battle Information (Name, Gender, Level, Health, and Experience)
                System.out.println("\n\n");
                System.out.println(" -------------------------");
                System.out.format("%-15s %5s", " " + foe.littlemonTeam[foeCurrentLittlemon].name +
                                foe.littlemonTeam[foeCurrentLittlemon].gender,
                        "\tLv" + foe.littlemonTeam[foeCurrentLittlemon].level);
                System.out.println("\n\t HP: " + foe.littlemonTeam[foeCurrentLittlemon].currentHealth +
                        "/" + foe.littlemonTeam[foeCurrentLittlemon].health);
                System.out.println(" -------------------------");
                System.out.println("\t\t\t\t\t\t   -------------------------");
                System.out.format("%-23s %5s", "\t\t\t\t\t\t   " + player.littlemonTeam[currentLittlemon].nickname +
                        player.littlemonTeam[currentLittlemon].gender,
                        "\t  Lv" + player.littlemonTeam[currentLittlemon].level);
                System.out.println("\n\t\t\t\t\t\t\t   HP: " + player.littlemonTeam[currentLittlemon].currentHealth +
                        "/" + player.littlemonTeam[currentLittlemon].health +
                        "\tEXP: " + player.littlemonTeam[currentLittlemon].currentExperience);
                System.out.println("\t\t\t\t\t\t   -------------------------");
                System.out.println("\n====================================================");
                System.out.println("    What will " + player.littlemonTeam[currentLittlemon].nickname + " do?");
                System.out.println("====================================================");
                System.out.println("| 1. FIGHT | | 2. BAG |  | 3. LiTTleMoN | | 4. RUN |");
                playerPrompt = scanN.nextInt();

                //Print Battle Menu
                switch (playerPrompt) {

                    //Fight
                    case 1:

                        do {

                            //Print current Littlemon's move set information (Name, Power Points (uses), and Type)
                            System.out.println("----------------------------------------------------");
                            for (int i = 0; i<player.littlemonTeam[currentLittlemon].movesLearned; i++) {
                                System.out.format("%-23s %25s", "| " + (i + 1) + ". " +
                                                player.littlemonTeam[currentLittlemon].moveset[i].name, " | | PP: " +
                                        player.littlemonTeam[currentLittlemon].moveset[i].currentPowerPoints + "/" +
                                        player.littlemonTeam[currentLittlemon].moveset[i].powerPoints + " TYPE/" +
                                        player.littlemonTeam[currentLittlemon].moveset[i].type + " |\n");
                            }
                            System.out.println("----------------------------------------------------");
                            System.out.println("(Type '5' to view move descriptions.)");
                            System.out.println("(Type '0' to return.)");
                            playerPrompt = scanN.nextInt();

                            switch (playerPrompt) {

                                //Use move 1
                                case 1:
                                    moveUsed = player.littlemonTeam[currentLittlemon].moveset[0];
                                    turnEnds = true;
                                    break;

                                //Use move 2
                                case 2:
                                    if (player.littlemonTeam[currentLittlemon].movesLearned > 1) {
                                        moveUsed = player.littlemonTeam[currentLittlemon].moveset[1];
                                        turnEnds = true;
                                    }
                                    break;

                                //Use move 3
                                case 3:
                                    if (player.littlemonTeam[currentLittlemon].movesLearned > 2) {
                                        moveUsed = player.littlemonTeam[currentLittlemon].moveset[2];
                                        turnEnds = true;
                                    }
                                    break;

                                //Use move 4
                                case 4:
                                    if (player.littlemonTeam[currentLittlemon].movesLearned > 3) {
                                        moveUsed = player.littlemonTeam[currentLittlemon].moveset[3];
                                        turnEnds = true;
                                    }
                                    break;

                                //View move info
                                case 5:

                                    do {

                                        //Print current Littlemon's move set details (Name, Power, Accuracy, and
                                        //Description)
                                        System.out.println("----------------------------------------------------");
                                        for (int i = 0; i<player.littlemonTeam[currentLittlemon].movesLearned; i++) {
                                            System.out.format("%-23s %25s", "| " + (i + 1) + ". " +
                                                    player.littlemonTeam[currentLittlemon].moveset[i].name,
                                                    " | | POWER: " +
                                                    player.littlemonTeam[currentLittlemon].moveset[i].power +
                                                            " ACCURACY: " +
                                                    player.littlemonTeam[currentLittlemon].moveset[i].accuracy +
                                                            " |\n");
                                            System.out.println(player.littlemonTeam[currentLittlemon].moveset[i].
                                                    description);
                                        }
                                        System.out.println("----------------------------------------------------");
                                        System.out.println("(Type '5' to return.)");
                                        playerPrompt = scanN.nextInt();

                                    } while (playerPrompt != 5);

                            }

                        } while (playerPrompt != 0 && !turnEnds);

                        break;

                    //Bag
                    case 2:

                        do {

                            //Print player's available items for use
                            System.out.println("----------------------------------------------------");
                            if (item3.amount > 0) {
                                System.out.format("%-23s %25s", "| 1. " + item3.name, "x " + item3.amount + " |\n");
                                System.out.println(item3.description);
                            }
                            if (item4.amount > 0) {
                                System.out.format("%-23s %25s", "| 2. " + item4.name, "x " + item4.amount + " |\n");
                                System.out.println(item4.description);
                            }
                            if (item3.amount == 0 && item4.amount == 0) {
                                System.out.println("The bag is empty.");
                            }
                            System.out.println("----------------------------------------------------");
                            System.out.println("(Type '0' to return.)");
                            playerPrompt = scanN.nextInt();

                            switch (playerPrompt) {

                                //Use Little Ball (only works on Wild Littlemon)
                                case 1:
                                    if (item3.amount > 0) {
                                        item3.amount -= 1;
                                        if (!foeClass.equalsIgnoreCase("Wild ")) {
                                            System.out.println("\nThe Handler blocked the ball!");
                                            Thread.sleep(1500);
                                            System.out.println("\nDon't be a thief!");
                                            Thread.sleep(1500);
                                        } else {
                                            if (item3.useItem(foe.littlemonTeam[currentLittlemon])) {
                                                endBattle = true;
                                            }
                                        }
                                        skipTurn = true;
                                    }
                                    break;

                                //Use Potion
                                case 2:
                                    if (item4.amount > 0) {
                                        item4.amount -= 1;
                                        item4.useItem(player.littlemonTeam[currentLittlemon]);
                                        skipTurn = true;
                                    }
                                    break;

                            }

                        } while (playerPrompt != 0 && !skipTurn);

                        break;

                    //Switch
                    case 3:

                        if (tutorialBattle && tip2) {
                            System.out.println("\nTim: It's important to get to know your Littlemon thoroughly.");
                            Thread.sleep(1500);
                            System.out.println("\nTim: This is a list of your Littlemon, " + player.name + ".");
                            Thread.sleep(1500);
                            System.out.println("\nTim: Open this if you ever are in a dire need to switch into your " +
                                    "other Littlemons.");
                            Thread.sleep(1500);
                            tip2 = false;
                        }

                        previousLittlemon = currentLittlemon;

                        do {

                            //Print player's Littlemon Party
                            System.out.println("----------------------------------------------------");
                            System.out.println("In Battle:");
                            System.out.format("%-24s %25s", "| " + (currentLittlemon + 1) + ". " +
                                            player.littlemonTeam[currentLittlemon].nickname, " | | Lv" +
                                    player.littlemonTeam[currentLittlemon].level + "\t" +
                                    player.littlemonTeam[currentLittlemon].gender + "\tHP: " +
                                    player.littlemonTeam[currentLittlemon].currentHealth + "/" +
                                    player.littlemonTeam[currentLittlemon].health + " |\n\n");

                            System.out.println("In Party:");
                            for (int i = 0; i<player.littlemonCaught; i++) {
                                System.out.format("%-23s %25s", "| " + (i + 1) + ". " +
                                                player.littlemonTeam[i].nickname, " | | Lv" +
                                        player.littlemonTeam[i].level + "\t" +
                                        player.littlemonTeam[i].gender + "\tHP: " +
                                        player.littlemonTeam[i].currentHealth + "/" +
                                        player.littlemonTeam[i].health + " |\n");
                            }
                            System.out.println("----------------------------------------------------");
                            System.out.println("(Type '0' to return.)");
                            playerPrompt = scanN.nextInt();

                            switch (playerPrompt) {

                                //Switch to Littlemon 1
                                case 1:
                                    if (playerPrompt == (currentLittlemon + 1)) {
                                        System.out.println("\nThat Littlemon is already in battle!");
                                        Thread.sleep(1500);
                                        break;
                                    } else if (player.littlemonTeam[0].currentHealth <= 0) {
                                        System.out.println("\nThat Littlemon is too exhausted to battle!");
                                        Thread.sleep(1500);
                                        break;
                                    } else {
                                        currentLittlemon = 0;
                                        skipTurn = true;
                                    }
                                    break;

                                //Switch to Littlemon 2
                                case 2:
                                    if (player.littlemonCaught > 1) {
                                        if (playerPrompt == (currentLittlemon + 1)) {
                                            System.out.println("\nThat Littlemon is already in battle!");
                                            Thread.sleep(1500);
                                            break;
                                        } else if (player.littlemonTeam[1].currentHealth <= 0) {
                                            System.out.println("\nThat Littlemon is too exhausted to battle!");
                                            Thread.sleep(1500);
                                            break;
                                        } else {
                                            currentLittlemon = 1;
                                            skipTurn = true;
                                        }
                                    }
                                    break;

                                //Switch to Littlemon 3
                                case 3:
                                    if (player.littlemonCaught > 2) {
                                        if (playerPrompt == (currentLittlemon + 1)) {
                                            System.out.println("\nThat Littlemon is already in battle!");
                                            Thread.sleep(1500);
                                            break;
                                        } else if (player.littlemonTeam[2].currentHealth <= 0) {
                                            System.out.println("\nThat Littlemon is too exhausted to battle!");
                                            Thread.sleep(1500);
                                            break;
                                        } else {
                                            currentLittlemon = 2;
                                            skipTurn = true;
                                        }
                                    }
                                    break;

                                //Switch to Littlemon 4
                                case 4:
                                    if (player.littlemonCaught > 3) {
                                        if (playerPrompt == (currentLittlemon + 1)) {
                                            System.out.println("\nThat Littlemon is already in battle!");
                                            Thread.sleep(1500);
                                            break;
                                        } else if (player.littlemonTeam[3].currentHealth <= 0) {
                                            System.out.println("\nThat Littlemon is too exhausted to battle!");
                                            Thread.sleep(1500);
                                            break;
                                        } else {
                                            currentLittlemon = 3;
                                            skipTurn = true;
                                        }
                                    }
                                    break;

                                //Switch to Littlemon 5
                                case 5:
                                    if (player.littlemonCaught > 4) {
                                        if (playerPrompt == (currentLittlemon + 1)) {
                                            System.out.println("\nThat Littlemon is already in battle!");
                                            Thread.sleep(1500);
                                            break;
                                        } else if (player.littlemonTeam[4].currentHealth <= 0) {
                                            System.out.println("\nThat Littlemon is too exhausted to battle!");
                                            Thread.sleep(1500);
                                            break;
                                        } else {
                                            currentLittlemon = 4;
                                            skipTurn = true;
                                        }
                                    }
                                    break;

                                //Switch to Littlemon 6
                                case 6:
                                    if (player.littlemonCaught > 5) {
                                        if (playerPrompt == (currentLittlemon + 1)) {
                                            System.out.println("\nThat Littlemon is already in battle!");
                                            Thread.sleep(1500);
                                            break;
                                        } else if (player.littlemonTeam[5].currentHealth <= 0) {
                                            System.out.println("\nThat Littlemon is too exhausted to battle!");
                                            Thread.sleep(1500);
                                            break;
                                        } else {
                                            currentLittlemon = 5;
                                            skipTurn = true;
                                        }
                                    }
                                    break;

                            }

                        } while (playerPrompt != 0 && !skipTurn);

                        if (skipTurn) {
                            System.out.println("\n===================================================");
                            System.out.println("\t" + player.littlemonTeam[previousLittlemon].nickname + ", switch " +
                                    "out! Come back!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                            System.out.println("\n===================================================");
                            System.out.println("\tGo! " + player.littlemonTeam[currentLittlemon].nickname + "!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                        }

                        break;

                    //Run (only works if facing Wild Littlemon)
                    case 4:
                        if (!foeClass.equals("Wild ")) {
                            System.out.println("\nNo! There's no running from a Trainer battle!");
                        } else {
                            //Generate chance of running away
                            runChance = rand.nextInt(100);
                            if (runChance <= 65) {
                                System.out.println("\nGot away safely!");
                                endBattle = true;
                            } else {
                                System.out.println("\nCouldn't get away!");
                            }
                        }
                        Thread.sleep(1500);
                        skipTurn = true;
                        break;

                }

            } while (!skipTurn && !turnEnds);

            if (endBattle) {
                break;
            }

            if (!skipTurn) {
                //Player's Littlemon uses move
                player.littlemonTeam[currentLittlemon].useMove(moveUsed, player.littlemonTeam[currentLittlemon],
                        foe.littlemonTeam[foeCurrentLittlemon], true);

                if (tutorialBattle && tip1 && foe.littlemonTeam[0].currentHealth < foe.littlemonTeam[0].health) {
                    System.out.println("\nTim: Inflicting damage on the foe is the key to any battle.");
                    Thread.sleep(1500);
                    tip1 = false;
                }

                //If foe faints
                if (foe.littlemonTeam[foeCurrentLittlemon].currentHealth <= 0) {
                    foeFainted = true;
                    foeFaintedLittlemon++;

                    if (!foeClass.equals("Wild ")) {
                        System.out.println("\n===================================================");
                        System.out.println("\tFoe " + foe.littlemonTeam[foeCurrentLittlemon].name + " fainted!");
                        System.out.println("===================================================");
                        Thread.sleep(1500);
                    } else {
                        System.out.println("\n===================================================");
                        System.out.println("\t " + foeClass + foeName + " fainted!");
                        System.out.println("===================================================");
                        Thread.sleep(1500);
                    }

                    //EXP gain
                    player.littlemonTeam[currentLittlemon].gainEXP(foeClass);

                    //If foe still has Littlemon left in party, send it out
                    if (foeFaintedLittlemon == foe.littlemonCaught) {
                        break;
                    } else {
                        System.out.println("\n===================================================");
                        System.out.println("\t" + foeClass + foeName + " sent out " +
                                foe.littlemonTeam[++foeCurrentLittlemon].name + "!");
                        System.out.println("===================================================");
                        Thread.sleep(1500);
                    }

                }

            }

            if (!foeFainted) {
                //Foe turn
                foeChoice = rand.nextInt();

                //Foe randomly selects a move
                if (foeClass.equalsIgnoreCase("Rival ") || foeClass.equalsIgnoreCase(
                        "Commander ")) {
                    if (foeChoice <= 70) {
                        moveUsed = foe.littlemonTeam[foeCurrentLittlemon].moveset[0];
                    } else {
                        moveUsed = foe.littlemonTeam[foeCurrentLittlemon].moveset[1];
                    }
                } else if (foeClass.equalsIgnoreCase("Ace Handler ")) {
                    moveUsed = foe.littlemonTeam[foeCurrentLittlemon].moveset[0];
                } else {
                    if (foeChoice <= 45) {
                        moveUsed = foe.littlemonTeam[foeCurrentLittlemon].moveset[0];
                    } else {
                        moveUsed = foe.littlemonTeam[foeCurrentLittlemon].moveset[1];
                    }
                }

                //Foe's Littlemon uses move
                foe.littlemonTeam[foeCurrentLittlemon].useMove(moveUsed, foe.littlemonTeam[foeCurrentLittlemon],
                        player.littlemonTeam[currentLittlemon], false);

                //If you faint
                if (player.littlemonTeam[currentLittlemon].currentHealth <= 0) {
                    faintedLittlemon++;
                    System.out.println("\n===================================================");
                    System.out.println("\t" + player.littlemonTeam[currentLittlemon].nickname + " fainted!");
                    System.out.println("===================================================");
                    Thread.sleep(1500);

                    if (faintedLittlemon == player.littlemonCaught) {
                        System.out.println("\n===================================================");
                        System.out.println("\t" + player.name + " is out of usable Littlemon!");
                        System.out.println("===================================================");
                        Thread.sleep(1500);
                        System.out.println("\n===================================================");
                        System.out.println("\t" + player.name + " blacked out!");
                        System.out.println("===================================================");
                        Thread.sleep(1500);
                        fail();
                    } else {
                        //Send out Littlemon who are not fainted
                        for (int i = 0; i<player.littlemonCaught; i++) {
                            if (player.littlemonTeam[i].currentHealth > 0) {
                                currentLittlemon = i;
                                break;
                            }
                        }
                        System.out.println("\n===================================================");
                        System.out.println("\t" + player.name + " sent out " +
                                player.littlemonTeam[currentLittlemon].nickname + "!");
                        System.out.println("===================================================");
                        Thread.sleep(1500);
                    }

                }

            }

        } while (true);

        if (!endBattle) {

            //Battle End
            if (!foeClass.equals("Wild ")) {
                System.out.println("\n===================================================");
                System.out.println("\tPlayer defeated " + foeClass + foeName + "!");
                System.out.println("===================================================");
                Thread.sleep(1500);
            }

            //Call defeat method through HandlerData class
            foe.defeat();

            //Prize money
            if (!foeClass.equals("Wild ")) {
                player.moneyEarned += 100;
                System.out.println("\n" + player.name + " got $100 for winning!");
                Thread.sleep(1500);
            }

            if (tutorialBattle) {
                System.out.println("\nTim: Hm! Excellent!");
                Thread.sleep(1500);
                System.out.println("\nTim: If you win, you earn prize money, and your Littlemon will grow!");
                Thread.sleep(1500);
                System.out.println("\nTim: Battle other Handlers and make your Littlemon strong!");
                Thread.sleep(1500);
            }

        }

    }

}
