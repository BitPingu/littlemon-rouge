package com.company;

import java.util.Random;
import java.util.Scanner;

public class ItemData {

    //Initializing objects - Scanner, Random, HandlerData
    Scanner scanN = new Scanner(System.in);
    Random rand = new Random();

    HandlerData player = Main.player;

    //Declaring instance variables
    int amount = 0, catchChance, hpRestored;
    String name, category, description;
    boolean catchSuccess;

    /**********************
     * Method Name: ItemData
     * A constructor which initializes new objects from ItemData (or this) class
     * @param name - Name of the Move
     **********************/
    public ItemData(String name) {

        this.name = name;

        setCategory();
        setDescription();

    }

    /**********************
     * Method Name: setCategory
     * A mutator which sets the category of the item
     **********************/
    public void setCategory() {

        //Generate category
        switch (name) {

            case "Littledex" :
            case "Map" :
                this.category = "Key Items";
                break;

            case "Little Ball" :
                this.category = "Little Balls";
                break;

            case "Potion" :
                this.category = "Items";
                break;

            case "Boulder Pin" :
                this.category = "Gym Pins";
                break;

        }

    }

    /**********************
     * Method Name: setDescription
     * A mutator which sets the description of the item
     **********************/
    public void setDescription() {

        //Generate description
        switch (name) {

            case "Little Ball" :
                this.description = "A ball thrown to catch a wild Littlemon. It is designed in a capsule style.";
                break;

            case "Potion" :
                this.description = "A spray-type wound medicine. It restores the HP of one Littlemon by 20 points.";
                break;

        }

    }

    /**********************
     * Method Name: gainItems
     * Adds items to the player's bag
     * @param amount - Quantity of item(s) player receives
     **********************/
    public void gainItems(int amount) throws InterruptedException {

        //Add items to player's bag
        if (amount == 1) {
            System.out.println("\n" + player.name + " received a " + name + "!");
            Thread.sleep(1500);
            System.out.println("\n" + player.name + " put the " + name + " in the " + category + " Pocket.");
        } else {
            System.out.println("\n" + player.name + " received " + amount + " " + name + "s!");
            Thread.sleep(1500);
            System.out.println("\n" + player.name + " put the " + name + "s in the " + category + " Pocket.");
        }
        Thread.sleep(1500);

        this.amount += amount;

    }

    /**********************
     * Method Name: useItem
     * Uses an item from the bag option during battle
     * @param littlemon - The Wild Littlemon that the Little Ball is used on
     **********************/
    public boolean useItem(LittlemonData littlemon) throws InterruptedException {

        //Declaring variables within useItem method
        int playerPrompt;
        boolean firstAppearance = true;

        catchSuccess = false;

        switch (name) {

            //If player uses Little Ball
            case "Little Ball":
                System.out.println("\n=========================================" +
                        "==========");
                System.out.println("\t" + player.name + " used one Little Ball!");
                System.out.println("===========================================" +
                        "========");
                Thread.sleep(1500);
                System.out.println("\n=========================================" +
                        "==========");
                System.out.println("\t*wiggle*");
                System.out.println("===========================================" +
                        "========");
                Thread.sleep(1500);

                //Generate chance of successful capture
                catchChance = rand.nextInt(100);

                if (catchChance <= 70) {

                    System.out.println("\n=========================================" +
                            "==========");
                    System.out.println("\t*wiggle*");
                    System.out.println("===========================================" +
                            "========");
                    Thread.sleep(1500);

                    if (catchChance <= 40) {

                        System.out.println("\n=========================================" +
                                "==========");
                        System.out.println("\t*wiggle*");
                        System.out.println("===========================================" +
                                "========");
                        Thread.sleep(1500);

                        if (catchChance <= 1500) {

                            //If Littlemon is caught
                            //Set Littlemon in HandlerData class for player object
                            player.setLittlemon(littlemon.name);

                            System.out.println("\n=========================================" +
                                    "==========");
                            System.out.println("\t*click!*");
                            System.out.println("===========================================" +
                                    "========");
                            Thread.sleep(1500);
                            System.out.println("\n=========================================" +
                                    "==========");
                            System.out.println("\tGotcha! " + littlemon.name + " was caught!");
                            System.out.println("===========================================" +
                                    "========");
                            Thread.sleep(1500);
                            
                            for (int i = 0; i<player.numberOfLittlemonSeen; i++) {
                                if (player.littlemonSeen[i].equalsIgnoreCase(littlemon.name)) {
                                    firstAppearance = false;
                                    break;
                                }
                            }

                            //Add Littlemon caught to Littledex if it is a first appearance
                            if (firstAppearance) {
                                player.addToLittledex(littlemon.name);
                                System.out.println("\n=========================================" +
                                        "==========");
                                System.out.println("\t" + littlemon.name + "'s data was added to the Littledex.");
                                System.out.println("===========================================" +
                                        "========");
                                Thread.sleep(1500);
                            }

                            do {

                                System.out.println("\nDo you want to give a nickname to this " +
                                        littlemon.name + "?");
                                System.out.println("1: Yes.");
                                System.out.println("2: No.");
                                playerPrompt = scanN.nextInt();

                            } while (playerPrompt < 1 || playerPrompt > 2);

                            //Call setNickname mutator method in LittlemonData class through player's HandlerData class
                            //for its Littlemon object
                            player.littlemonTeam[player.littlemonCaught - 1].setNickname(playerPrompt);

                            catchSuccess = true;

                        } else {

                            System.out.println("\n=========================================" +
                                    "==========");
                            System.out.println("\tShoot! It was so close, too!");
                            System.out.println("===========================================" +
                                    "========");
                            Thread.sleep(1500);

                        }

                    } else {

                        System.out.println("\n=========================================" +
                                "==========");
                        System.out.println("\tAargh! Almost had it!");
                        System.out.println("===========================================" +
                                "========");
                        Thread.sleep(1500);

                    }

                } else {

                    System.out.println("\n=========================================" +
                            "==========");
                    System.out.println("\tOh, no! The Littlemon broke free!");
                    System.out.println("===========================================" +
                            "========");
                    Thread.sleep(1500);

                }
                break;

            //If player uses Potion
            case "Potion":
                hpRestored = 0;
                //Heal Littlemon until health is full or if health restored is at 20
                for (int i = 0; i < 20; i++) {
                    if (littlemon.currentHealth == littlemon.health) {
                        break;
                    }
                    littlemon.currentHealth += 1;
                    hpRestored++;
                }
                System.out.println("\n" + littlemon.nickname + "'s HP was restored by " + hpRestored + " point(s).");
                Thread.sleep(1500);
                break;

        }

        //Return if capture of Littlemon was successful
        return catchSuccess;

    }

}
