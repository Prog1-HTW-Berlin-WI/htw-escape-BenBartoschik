package model;

import java.io.Serializable;

public class Hero implements Serializable {

    private static final int DREILEBENSPUNKTE = 3;


    private static final int ZEHNLEBENSPUNKTE = 10;


    private static final int HEALTHPOINTS = 50;


    private static final long serialVersionUID = 3578735620108186013L;


    private String name;
    private int healthPoints;
    private int experiencePoints;
    private Lecturer[] signedExerciseLeaders;

   
    public Hero(String name) {
        this.name = name;
        this.healthPoints = 50;
        this.experiencePoints = 0;
        this.signedExerciseLeaders = new Lecturer[5]; 
    }

    public void takeDamage(int amount){

        healthPoints = healthPoints - amount;

        if(healthPoints < 0){
            healthPoints = 0;
        }

    }

    public void regenerate(boolean longRest){

        if(longRest == false){
            healthPoints = healthPoints + DREILEBENSPUNKTE;
        }
        if(longRest == true){
            healthPoints = healthPoints + ZEHNLEBENSPUNKTE;
        }
        if (healthPoints > 50){
            healthPoints = HEALTHPOINTS;
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

    public String getName() {
        return name;
    }
    
    public int getHealthPoints() {
        return healthPoints;
    }
    
}