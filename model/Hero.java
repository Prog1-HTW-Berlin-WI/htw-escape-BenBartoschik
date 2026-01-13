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
        
        double grundschaden = experiencePoints * 2.3 + 1;
        int schaden = (int)grundschaden;

        double zufallsgenerator = Math.random();
        if(zufallsgenerator < 0.13){
            return 0;
        }
        else if(zufallsgenerator < 0.25){
            return schaden * 2;
        }else{
            return schaden;
        }
    }

    public void signExerciseLeader(Lecturer lecturer) {
        for (int i = 0; i < signedExerciseLeaders.length; i++) {
             if (signedExerciseLeaders[i] == lecturer){
                return;
             }
            
        
        }
        for (int i = 0; i < signedExerciseLeaders.length; i++) {
             if (signedExerciseLeaders[i] == null){
                signedExerciseLeaders[i] = lecturer;
                return;
             }
         }
   }

   public int getExperiencePoints(){
        return experiencePoints;
   }

   public void addExperiencePoints(int experiencePoints){
        this.experiencePoints = this.experiencePoints + experiencePoints;
   }

   public boolean isOperational(){
        if(healthPoints > 0){
            return true;
        }else{
            return false;
        }
   }

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.
    private static final long serialVersionUID = 3578735620108186013L;
}