package model;

import java.io.Serializable;

public class Hero implements Serializable {


    String name;
    int healthPoints;
    int experiencePoints;
    Lecturer[] signedExerciseLeaders;

    public void takeDamage(int amount){

        healthPoints = healthPoints - amount;

        if(healthPoints < 0){
            healthPoints = 0;
        }

    }
    public void regenerate(boolean longRest){

        if(longRest == false){
            healthPoints = healthPoints + 3;
        }
        if(longRest == true){
            healthPoints = healthPoints + 10;
        }
        if (healthPoints > 50){
            healthPoints = 50;
        }
    }
    public boolean flee(){

        double wahrscheinlichkeit = Math.random();
        if(wahrscheinlichkeit < 0.42){
            return true;
        }else{
            return false;
        }
    }
    public int attack(){
        


    }



    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 3578735620108186013L;
}