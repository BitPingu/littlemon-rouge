package com.company;

public class MoveData {

    //Declaring instance variables
    String name, category, type, description;
    int powerPoints, currentPowerPoints, power, accuracy;

    /**********************
     * Method Name: MoveData
     * A constructor which initializes new objects from MoveData (or this) class
     * @param name - Name of the Move
     **********************/
    public MoveData(String name) {

        this.name = name;

    }

    /**********************
     * Method Name: setCategory
     * A mutator which sets the category of the move
     **********************/
    public void setCategory() {

        //Generate category
        switch (name) {

            case "Tackle" :
            case "Scratch" :
            case "Vine Whip" :
            case "Poison Sting" :
            case "Rock Tomb" :
            case "Quick Attack" :
            case "Unlock" :
            case "Locked" :
                this.category = "Physical";
                break;

            case "Ember" :
            case "Bubble" :
            case "Thunder Shock" :
            case "Gust" :
                this.category = "Special";
                break;

            case "Sand-Attack" :
            case "Growl" :
            case "Tail Whip" :
            case "String Shot" :
            case "Defense Curl" :
                this.category = "Status";
                break;

        }

    }

    /**********************
     * Method Name: setPowerPoints
     * A mutator which sets the power points (uses) of the move
     **********************/
    public void setPowerPoints() {

        //Generate power points
        switch (name) {

            case "Unlock" :
            case "Locked" :
                this.powerPoints = 100;
                break;

            case "Growl" :
            case "String Shot" :
            case "Defense Curl" :
                this.powerPoints = 40;
                break;

            case "Tackle" :
            case "Scratch" :
            case "Gust" :
            case "Poison Sting" :
                this.powerPoints = 35;
                break;

            case "Thunder Shock" :
            case "Quick Attack" :
            case "Tail Whip" :
                this.powerPoints = 30;
                break;

            case "Vine Whip" :
            case "Ember" :
            case "Bubble" :
                this.powerPoints = 25;
                break;

            case "Rock Tomb" :
            case "Sand-Attack" :
                this.powerPoints = 15;
                break;

        }

        //Set current power points to max power points
        this.currentPowerPoints = this.powerPoints;

    }

    /**********************
     * Method Name: setType
     * A mutator which sets the type of the move
     **********************/
    public void setType() {

        //Generate type
        switch (name) {

            case "Tackle" :
            case "Scratch" :
            case "Quick Attack" :
            case "Sand-Attack" :
            case "Growl" :
            case "Tail Whip" :
            case "Defense Curl" :
                this.type = "NORMAL";
                break;

            case "Vine Whip" :
                this.type = "GRASS";
                break;

            case "Ember" :
                this.type = "FIRE";
                break;

            case "Bubble" :
                this.type = "WATER";
                break;

            case "Thunder Shock" :
                this.type = "ELECTRIC";
                break;

            case "Gust" :
                this.type = "FLYING";
                break;

            case "Poison Sting" :
                this.type = "POISON";
                break;

            case "String Shot" :
                this.type = "BUG";
                break;

            case "Rock Tomb" :
                this.type = "ROCK";
                break;

            case "Unlock" :
            case "Locked" :
                this.type = "???";
                break;

        }

    }

    /**********************
     * Method Name: setPower
     * A mutator which sets the power of the move
     **********************/
    public void setPower() {

        //Generate power
        switch (name) {

            case "Tackle" :
            case "Scratch" :
            case "Thunder Shock" :
            case "Quick Attack" :
            case "Gust" :
                this.power = 4;
                break;

            case "Poison Sting" :
                this.power = 5;
                break;

            case "Vine Whip" :
            case "Ember" :
            case "Bubble" :
                this.power = 6;
                break;

            case "Rock Tomb" :
                this.power = 8;
                break;

            case "Unlock" :
                this.power = 2147483647;
                break;

            case "Sand-Attack" :
            case "Growl" :
            case "Tail Whip" :
            case "String Shot" :
            case "Defense Curl" :
            case "Locked" :
                this.power = 0;
                break;

        }

    }

    /**********************
     * Method Name: setAccuracy
     * A mutator which sets the accuracy of the move
     **********************/
    public void setAccuracy() {

        //Generate accuracy
        switch (name) {

            case "Tackle" :
            case "Scratch" :
            case "Quick Attack" :
            case "Vine Whip" :
            case "Ember" :
            case "Bubble" :
            case "Thunder Shock" :
            case "Gust" :
            case "Poison Sting" :
            case "Sand-Attack" :
            case "Growl" :
            case "Tail Whip" :
            case "Defense Curl" :
            case "Unlock" :
            case "Locked" :
                this.accuracy = 100;
                break;

            case "String Shot" :
                this.accuracy = 95;
                break;

            case "Rock Tomb" :
                this.accuracy = 65;
                break;
        }

    }

    /**********************
     * Method Name: setDescription
     * A mutator which sets the description of the move
     **********************/
    public void setDescription() {

        //Generate description (Effects are not applicable)
        switch (name) {

            case "Tackle" :
                this.description = "A physical attack in which the user charges, full body, into the foe.";
                break;

            case "Scratch" :
                this.description = "Hard, pointed, and sharp claws rake the foe.";
                break;

            case "Quick Attack" :
                this.description = "An almost invisibly fast attack that is certain to strike first.";
                break;

            case "Vine Whip" :
                this.description = "The foe is struck with slender, whiplike vines.";
                break;

            case "Ember" :
                this.description = "The foe is attacked with small flames. The foe may suffer a burn.";
                break;

            case "Bubble" :
                this.description = "A spray of bubbles hits the foe. It may lower the foe's Speed stat.";
                break;

            case "Thunder Shock" :
                this.description = "An electric shock attack that may also leave the foe paralyzed.";
                break;

            case "Gust" :
                this.description = "Strikes the foe with a gust of wind whipped up by wings.";
                break;

            case "Poison Sting" :
                this.description = "The foe is stabbed with a toxic barb, etc. It may poison the foe.";
                break;

            case "Rock Tomb" :
                this.description = "Boulders are hurled at the foe. It also lowers the foe's Speed if it hits.";
                break;

            case "Sand-Attack" :
                this.description = "A lot of sand is hurled in the foe's face, reducing its accuracy.";
                break;

            case "Growl" :
                this.description = "The user growls in a cute way, making the foe lower its Attack. stat.";
                break;

            case "Tail Whip" :
                this.description = "The user wags its tail cutely, making the foe lower its Defense stat.";
                break;

            case "String Shot" :
                this.description = "The foe is bound with strings shot from the mouth to reduce its Speed.";
                break;

            case "Defense Curl" :
                this.description = "\tThe user curls up to conceal weak spots and raise its Defense stat.";
                break;

            case "Unlock" :
                this.description = "Unlocks the power within to break the game.";
                break;

            case "Locked" :
                this.description = "This move is locked...";
                break;

        }

    }

}
