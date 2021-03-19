package com.company;

import java.util.Random;
import java.util.Scanner;

public class LittlemonData {

    //Initializing objects - MoveData, Random
    MoveData[] moveset = new MoveData[4];
    Random rand = new Random();

    //Declaring instance variables
    String name, nickname, gender;
    int level, experience = 100, currentExperience, health, currentHealth, movesLearned = 0, savedLevel, savedExperience,
            savedHealth, savedMoves, savedPowerPoints1, savedPowerPoints2;
    String[] starters = {"Herbasaur", "Scorchmander", "Spurtle", "Kipachu", "Eve"};
    String[] wild = {"Ratatouille", "Mordecai", "Heimlich", "Beedle", "Rocky", "Onixpected"};
    String[] special = {"Techkey"};

    /**********************
     * Method Name: LittlemonData
     * A constructor which initializes new objects from LittlemonData (or this) class
     * @param name - Name of the Littlemon
     **********************/
    public LittlemonData(String name) {

        this.name = name;


    }

    /**********************
     * Method Name: setNickname
     * A mutator which sets the nickname of the Littlemon
     * @param playerPrompt - Player input from main class
     **********************/
    public void setNickname(int playerPrompt) {

        //Initializing Scanners
        Scanner scanN = new Scanner(System.in);
        Scanner scanS = new Scanner(System.in);

        //Declaring variables within setNickname method
        String nickname = "";

        if (playerPrompt == 1) {

            do {

                //Check Nickname Length
                if (nickname.length() > 15) {
                    System.out.println("\nSorry, that nickname is too long. Please try again. (Max: 15)");
                }

                System.out.println("\n" + name + gender + "'s nickname?");
                System.out.print("Nickname: ");
                nickname = scanS.nextLine();

                //Starter Name Confirmation
                do {

                    if (nickname.equalsIgnoreCase("")) {
                        nickname = name;
                    }
                    System.out.println("\nIs " + nickname + " the nickname you want to give?");
                    System.out.println("1: Yes.");
                    System.out.println("2: No.");
                    playerPrompt = scanN.nextInt();

                } while (playerPrompt < 1 || playerPrompt > 2);

            } while (playerPrompt == 2 || nickname.length() > 15);

            this.nickname = nickname;

        } else {
            this.nickname = name;
        }

    }

    /**********************
     * Method Name: setGender
     * A mutator which sets the gender of the Littlemon
     **********************/
    public void setGender() {

        //Declaring variables within setGender method
        int genderChance;
        String gender;

        genderChance = rand.nextInt(100);

        //Generate male or female
        switch (name) {
            case "Kipachu" :
            case "Ratatouille" :
            case "Mordecai" :
            case "Heimlich" :
            case "Beedle" :
            case "Rocky" :
            case "Onixpected" :
                if (genderChance <= 50) {
                    gender = "♂";
                } else {
                    gender = "♀";
                }
                break;

            default :
                if (genderChance <= 87.5) {
                    gender = "♂";
                } else {
                    gender = "♀";
                }
                break;

        }

        this.gender = gender;

    }

    /**********************
     * Method Name: setLevel
     * A mutator which sets the level of the Littlemon
     * @param handlerName - The foe's class during battle
     **********************/
    public void setLevel(String handlerName) {

        //Declaring variables within setLevel method
        int level = 0, levelChance;

        //Generate level for starters
        for (String littlemon : starters) {
            //If Littlemon is a starter
            if (name.equalsIgnoreCase(littlemon)) {
                level = 5;
                break;
            }
        }

        //Generate level for wild Littlemon
        for (String littlemon : wild) {

            if (name.equalsIgnoreCase(littlemon)) {

                switch (handlerName) {

                    //If Littlemon is a handlers'
                    case "JoseJesus" :
                    case "JimJam" :
                        level = 4;
                        break;

                    case "MatPat" :
                        level = 3;
                        break;

                    case "Ken" :
                        level = 100;
                        break;

                    case "Jerome" :
                    case "Mitch" :
                        level = 5;
                        break;

                    case "Liam" :
                    case "Selina" :
                        level = 6;
                        break;

                    case "Sylvester Stone" :
                        if (name.equalsIgnoreCase("Rocky")) {
                            level = 7;
                        }
                        if (name.equalsIgnoreCase("Onixpected")) {
                            level = 11;
                        }
                        break;

                    //If Littlemon is wild
                    default :
                        levelChance = rand.nextInt(100);

                        //Generate random level 2-5
                        if (levelChance <= 35) {
                            level = 2;
                        } else if (levelChance <= 70) {
                            level = 3;
                        } else if (levelChance <= 75) {
                            level = 4;
                        } else {
                            level = 5;
                        }
                        break;

                }

            }

        }

        for (String littlemon : special) {
            //If Littlemon belongs to SinanSinan
            if (name.equalsIgnoreCase(littlemon)) {

                if ("Techkey".equals(littlemon)) {
                    level = 1;
                }

            }
        }

        this.level = level;

        //Set experience to 0
        this.currentExperience = 0;

    }

    /**********************
     * Method Name: setStats
     * A mutator which sets the stats of the Littlemon depending on level
     **********************/
    public void setStats() {

        //Generate stats
        this.health = level * 4;

    }

    /**********************
     * Method Name: setMoves
     * A mutator which sets the moves of the Littlemon depending on level
     **********************/
    public void setMoves() throws InterruptedException {

        //Initialize new move objects for starters
        for (String littlemon : starters) {
            //If Littlemon is a starter
            if (name.equalsIgnoreCase(littlemon)) {

                switch (littlemon) {

                    case "Herbasaur" :
                        if (level == 5) {
                            this.moveset[0] = new MoveData("Tackle");
                            this.moveset[1] = new MoveData("Growl");
                            this.movesLearned += 2;
                        }
                        if (level == 7) {
                            this.moveset[2] = new MoveData("Vine Whip");
                            this.movesLearned += 1;
                        }
                        break;

                    case "Scorchmander" :
                        if (level == 5) {
                            this.moveset[0] = new MoveData("Scratch");
                            this.moveset[1] = new MoveData("Growl");
                            this.movesLearned += 2;
                        }
                        if (level == 7) {
                            this.moveset[2] = new MoveData("Ember");
                            this.movesLearned += 1;
                        }
                        break;

                    case "Spurtle" :
                        if (level == 5) {
                            this.moveset[0] = new MoveData("Tackle");
                            this.moveset[1] = new MoveData("Tail Whip");
                            this.movesLearned += 2;
                        }
                        if (level == 7) {
                            this.moveset[2] = new MoveData("Bubble");
                            this.movesLearned += 1;
                        }
                        break;

                    case "Kipachu" :
                        if (level == 5) {
                            this.moveset[0] = new MoveData("Thunder Shock");
                            this.moveset[1] = new MoveData("Growl");
                            this.movesLearned += 2;
                        }
                        if (level == 7) {
                            this.moveset[2] = new MoveData("Quick Attack");
                            this.movesLearned += 1;
                        }
                        break;

                    case "Eve" :
                        if (level == 5) {
                            this.moveset[0] = new MoveData("Tackle");
                            this.moveset[1] = new MoveData("Tail Whip");
                            this.movesLearned += 2;
                        }
                        break;

                }

                if (level == 7) {
                    //If Littlemon learns a new move
                    System.out.println("\n===================================================");
                    System.out.println("\t" + nickname + " learned " + this.moveset[2].name + "!");
                    System.out.println("===================================================");
                    Thread.sleep(1500);
                }

            }

        }

        //Initialize new move objects for wild Littlemon
        for (String littlemon : wild) {
            //If Littlemon is wild
            if (name.equalsIgnoreCase(littlemon)) {

                switch (littlemon) {

                    case "Ratatouille" :
                        this.moveset[0] = new MoveData("Tackle");
                        this.moveset[1] = new MoveData("Tail Whip");
                        this.movesLearned = 2;
                        break;

                    case "Mordecai" :
                        this.moveset[0] = new MoveData("Gust");
                        this.moveset[1] = new MoveData("Sand-Attack");
                        this.movesLearned = 2;
                        break;

                    case "Heimlich" :
                        this.moveset[0] = new MoveData("Tackle");
                        this.moveset[1] = new MoveData("String Shot");
                        this.movesLearned = 2;
                        break;

                    case "Beedle" :
                        this.moveset[0] = new MoveData("Poison Sting");
                        this.moveset[1] = new MoveData("String Shot");
                        this.movesLearned = 2;
                        break;

                    case "Rocky" :
                        this.moveset[0] = new MoveData("Tackle");
                        this.moveset[1] = new MoveData("Defense Curl");
                        this.movesLearned = 2;
                        break;

                    case "Onixpected" :
                        this.moveset[0] = new MoveData("Tackle");
                        this.moveset[1] = new MoveData("Rock Tomb");
                        this.movesLearned = 2;
                        break;

                }

            }

        }

        for (String littlemon : special) {
            //If Littlemon belongs to SinanSinan
            if (name.equalsIgnoreCase(littlemon)) {

                if ("Techkey".equals(littlemon)) {
                    this.moveset[0] = new MoveData("Unlock");
                    this.moveset[1] = new MoveData("Locked");
                    this.movesLearned = 2;
                }

            }
        }

        //Set move data of each respective move object through MoveData class
        for (int i = 0; i < movesLearned; i++) {
            moveset[i].setCategory();
            moveset[i].setPowerPoints();
            moveset[i].setType();
            moveset[i].setPower();
            moveset[i].setAccuracy();
            moveset[i].setDescription();
        }

    }

    /**********************
     * Method Name: heal
     * Resets the Littlemon's health and power points
     **********************/
    public void heal() {

        //Set current health to max health
        currentHealth = this.health;

        //Set current power points of each move to max power points respectively
        for (int i = 0; i < movesLearned; i++) {
            moveset[i].currentPowerPoints = this.moveset[i].powerPoints;
        }

    }

    /**********************
     * Method Name: save
     * Saves the Littlemon's data for the next checkpoint
     * @param littlemon - The Littlemon's data to be saved
     **********************/
    public void save(LittlemonData littlemon) {

        //Set saved level to current level
        this.savedLevel = littlemon.level;

        //Set saved experience to current experience
        this.savedExperience = littlemon.currentExperience;

        //Set saved health to current health
        this.savedHealth = littlemon.currentHealth;

        //Set saved moves to current moves learned
        this.savedMoves = littlemon.movesLearned;

        //Set saved power points to current power points for first two moves
        this.savedPowerPoints1 = littlemon.moveset[0].currentPowerPoints;
        this.savedPowerPoints2 = littlemon.moveset[1].currentPowerPoints;

    }

    /**********************
     * Method Name: recover
     * Recovers the Littlemon's data from before the checkpoint
     **********************/
    public void recover() {

        //Set level to saved level
        level = this.savedLevel;

        //Set experience to saved experience
        currentExperience = this.savedExperience;

        //Set health to saved health
        currentHealth = this.savedHealth;

        //Reset stats
        setStats();

        //Set moves to saved moves learned
        movesLearned = this.savedMoves;

        //Set power points to saved power points for first two moves
        moveset[0].currentPowerPoints = this.savedPowerPoints1;
        moveset[1].currentPowerPoints = this.savedPowerPoints2;

    }

    /**********************
     * Method Name: useMove
     * Prints dialogue for when a Littlemon uses a move
     * @param moveUsed - The MoveData of the move being used
     * @param user - The Littlemon that uses the move
     * @param foe - The Littlemon that the move is used on
     * @param playerUsesMove - If player uses a move
     **********************/
    public void useMove(MoveData moveUsed, LittlemonData user, LittlemonData foe, boolean playerUsesMove)
            throws InterruptedException {

        //Declaring variables within useMove method
        int moveHits, damageTaken = 0;

        if (playerUsesMove) {
            System.out.println("\n===================================================");
            System.out.println("\t" + user.nickname + " used " + moveUsed.name + "!");
            System.out.println("===================================================");
            Thread.sleep(1500);
        } else {
            System.out.println("\n===================================================");
            System.out.println("\tFoe " + user.name + " used " + moveUsed.name + "!");
            System.out.println("===================================================");
            Thread.sleep(1500);
        }

        //Accuracy check
        moveHits = rand.nextInt(100);

        if (moveHits <= moveUsed.accuracy) {

            //If hits
            moveUsed.currentPowerPoints -= 1;

            if (moveUsed.category.equals("Physical") || moveUsed.category.equals("Special")) {

                //If attacking move (deal damage based on power)
                if (foe.name.equalsIgnoreCase("Techkey")) {
                    foe.currentHealth -= 1;
                    damageTaken++;
                } else {
                    //Deal damage until damage is dealt or if health is at 0
                    for (int i = 0; i < moveUsed.power; i++) {
                        if (foe.currentHealth == 0) {
                            break;
                        }
                        foe.currentHealth -= 1;
                        damageTaken++;
                    }
                }

                if (playerUsesMove) {
                    System.out.println("\n===================================================");
                    System.out.println("\tFoe " + foe.name + " took " + damageTaken + " damage.");
                    System.out.println("===================================================");
                    Thread.sleep(1500);
                } else {
                    System.out.println("\n===================================================");
                    System.out.println("\t" + foe.nickname + " took " + damageTaken + " damage.");
                    System.out.println("===================================================");
                    Thread.sleep(1500);
                }

            } else if (moveUsed.category.equals("Status")) {

                //If status move (doesn't actually do anything)
                switch (moveUsed.name) {

                    case "Sand-Attack" :
                        if (playerUsesMove) {
                            System.out.println("\n===================================================");
                            System.out.println("\tFoe " + foe.name + "'s ACCURACY fell!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                        } else {
                            System.out.println("\n===================================================");
                            System.out.println("\t" + foe.nickname + "'s ACCURACY fell!'");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                        }
                        break;

                    case "Growl" :
                        if (playerUsesMove) {
                            System.out.println("\n===================================================");
                            System.out.println("\tFoe " + foe.name + "'s ATTACK fell!'");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                        } else {
                            System.out.println("\n===================================================");
                            System.out.println("\t" + foe.nickname + "'s ATTACK fell!'");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                        }
                        break;

                    case "Tail Whip" :
                        if (playerUsesMove) {
                            System.out.println("\n===================================================");
                            System.out.println("\tFoe " + foe.name + "'s DEFENSE fell!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                        } else {
                            System.out.println("\n===================================================");
                            System.out.println("\t" + foe.nickname + "'s DEFENSE fell!'");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                        }
                        break;

                    case "String Shot" :
                        if (playerUsesMove) {
                            System.out.println("\n===================================================");
                            System.out.println("\tFoe " + foe.name + "'s SPEED fell!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                        } else {
                            System.out.println("\n===================================================");
                            System.out.println("\t" + foe.nickname + "'s SPEED fell!'");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                        }
                        break;

                    case "Defense Curl" :
                        if (playerUsesMove) {
                            System.out.println("\n===================================================");
                            System.out.println("\t" + user.nickname + "'s DEFENSE rose!");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                        } else {
                            System.out.println("\n===================================================");
                            System.out.println("\tFoe " + user.name + "'s DEFENSE rose!'");
                            System.out.println("===================================================");
                            Thread.sleep(1500);
                        }
                        break;

                }

            }

        } else {

            //If misses
            if (playerUsesMove) {
                System.out.println("\n===================================================");
                System.out.println("\t" + user.nickname + "'s attack missed!");
                System.out.println("===================================================");
                Thread.sleep(1500);
            } else {
                System.out.println("\n===================================================");
                System.out.println("\tFoe " + user.name + "'s attack missed!");
                System.out.println("===================================================");
                Thread.sleep(1500);
            }

        }

    }

    /**********************
     * Method Name: gainEXP
     * Add experience when Littlemon wins a battle
     * @param foeClass - The foe's class during battle
     **********************/
    public void gainEXP(String foeClass) throws InterruptedException {

        //Declaring variables within gainEXP method
        int experienceGained = 0;

        //Gain EXP depending on defeated foe
        switch (foeClass) {

            case "Rival " :
            case "Commander " :
                experienceGained = 100;
                break;

            case "Wild " :
                experienceGained = 10;
                break;

            case "Youngster " :
                experienceGained = 20;
                break;

            case "School Kid " :
                experienceGained = 50;
                break;

            case "Ace Handler " :
            case "Guide Gent " :
                experienceGained = 9999999;
                break;

        }

        this.currentExperience += experienceGained;

        System.out.println("\n===================================================");
        System.out.println("\t" + nickname + " gained " + experienceGained + " EXP. Points!");
        System.out.println("===================================================");
        Thread.sleep(1500);

        //If Littlemon levels up
        if (currentExperience >= experience) {

            //Increase level by one
            level += 1;

            System.out.println("\n===================================================");
            System.out.println("\t" + nickname + " grew to LV." + level + "!");
            System.out.println("===================================================");
            Thread.sleep(1500);
            setStats();
            setMoves();

            //Reset experience to 0
            this.currentExperience = 0;

        }

    }

}
