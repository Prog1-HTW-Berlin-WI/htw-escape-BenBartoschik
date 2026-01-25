package model;
import model.Lecturer;
import app.EscapeGame;

import java.io.Serializable;

/**
 * Repräsentiert den Spielcharakter (Helden) im Spiel.
 * Der Held hat Lebenspunkte, Erfahrungspunkte und kann Unterschriften
 * von Übungsleitern sammeln. Er kann kämpfen, fliehen und sich regenerieren.
 * 
 * @author [Dein Name hier einfügen]
 */
public class Hero implements Serializable {

    /** Anzahl der Lebenspunkte bei einer kleinen Verschnaufpause. */
    private static final int DREILEBENSPUNKTE = 3;

    /** Anzahl der Lebenspunkte bei einer großen Verschnaufpause. */
    private static final int ZEHNLEBENSPUNKTE = 10;

    /** Maximale Anzahl an Lebenspunkten des Helden. */
    private static final int HEALTHPOINTS = 50;

    /** Serial-Version UID für die Serialisierung. */
    private static final long serialVersionUID = 3578735620108186013L;

    /** Name des Helden. */
    private String name;
    
    /** Aktuelle Lebenspunkte des Helden. */
    private int healthPoints;
    
    /** Aktuelle Erfahrungspunkte des Helden. */
    private int experiencePoints;
    
    /** Array mit unterschriebenen Übungsleitern. */
    private Lecturer[] signedExerciseLeaders;

    //Difiniert ob er "einsatzbereit" ist
    private boolean isOperational;

    /**
     * Konstruktor für den Helden.
     * Initialisiert den Helden mit einem Namen, 50 Lebenspunkten,
     * 0 Erfahrungspunkten und einem leeren Laufzettel.
     * 
     * @param name Der Name des Helden.
     */
    public Hero(String name) {
        this.name = name;
        this.healthPoints = 50;
        this.experiencePoints = 0;
        this.signedExerciseLeaders = new Lecturer[6]; 
        this.isOperational = true;
    }

    /**
     * Reduziert die Lebenspunkte des Helden um den angegebenen Betrag.
     * Die Lebenspunkte fallen nicht unter 0.
     * 
     * @param amount Der Schadensbetrag, der abgezogen wird.
     */
    public void takeDamage(int amount) {
        healthPoints = healthPoints - amount;

        if(healthPoints <= 0) {
            healthPoints = 0;
            this.isOperational = false;
        }
    }

    /**
     * Prüft, ob der Held einsatzfähig ist.
     *
     * @return einsatzfähig
     */
    
    public boolean isOperational() {
        return isOperational;
    }

    /**
     * Regeneriert Lebenspunkte des Helden durch eine Verschnaufpause.
     * Bei einer kleinen Pause (longRest = false) werden 3 LP regeneriert.
     * Bei einer großen Pause (longRest = true) werden 10 LP regeneriert.
     * Die Lebenspunkte überschreiten nicht das Maximum von 50.
     * 
     * @param longRest Gibt an, ob es sich um eine große Pause handelt.
     */
    public void regenerate(boolean longRest) {
        if(longRest == false) {
            healthPoints = healthPoints + DREILEBENSPUNKTE;
        }
        if(longRest == true) {
            healthPoints = healthPoints + ZEHNLEBENSPUNKTE;
        }
        if (healthPoints > 50) {
            healthPoints = HEALTHPOINTS;
        }
    }

    /**
     * Versucht, aus einer Begegnung zu fliehen.
     * Die Flucht gelingt mit einer Wahrscheinlichkeit von 42%.
     * 
     * @return true, wenn die Flucht gelungen ist, sonst false.
     */
    public boolean flee() {
        double wahrscheinlichkeit = Math.random();
        if(wahrscheinlichkeit < 0.42) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Führt einen Angriff auf ein feindliches Wesen durch.
     * Der Grundschaden berechnet sich aus Erfahrungspunkten * 2,3 + 1.
     * Mit 13% Wahrscheinlichkeit schlägt der Angriff fehl (0 Schaden).
     * Mit 12% Wahrscheinlichkeit ist es ein kritischer Treffer (doppelter Schaden).
     * 
     * @return Der berechnete Schadenswert als Ganzzahl.
     */
    public int attack() {
        double grundschaden = experiencePoints * 2.3 + 1;
        int schaden = (int)grundschaden;

        double zufallsgenerator = Math.random();
        if(zufallsgenerator < 0.13) {
            return 0;
        }
        else if(zufallsgenerator < 0.25) {
            return schaden * 2;
        } else {
            return schaden;
        }
    }

    /**
     * Trägt einen Übungsleiter in den Laufzettel ein.
     * Jeder Übungsleiter kann nur einmal eingetragen werden.
     * 
     * @param lecturer Der einzutragende Übungsleiter.
     */
    public void signExerciseLeader(Lecturer lecturer) {
        for (int i = 0; i < signedExerciseLeaders.length; i++) {
             if (signedExerciseLeaders[i] == lecturer) {
                return;
             }
        }
        for (int i = 0; i < signedExerciseLeaders.length; i++) {
             if (signedExerciseLeaders[i] == null) {
                signedExerciseLeaders[i] = lecturer;
                return;
             }
         }
   }

   /**
    * Gibt die aktuellen Erfahrungspunkte des Helden zurück.
    * 
    * @return Die Erfahrungspunkte.
    */
   public int getExperiencePoints() {
        return experiencePoints;
   }

   /**
    * Erhöht die Erfahrungspunkte um den angegebenen Wert.
    * 
    * @param experiencePoints Die hinzuzufügenden Erfahrungspunkte.
    */
   public void addExperiencePoints(int experiencePoints) {
        this.experiencePoints = this.experiencePoints + experiencePoints;
   }

    /**
     * Gibt den Namen des Helden zurück.
     * 
     * @return Der Name des Helden.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Setzt den Namen des Helden.
     * 
     * @param name Der neue Name des Helden.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gibt die aktuellen Lebenspunkte des Helden zurück.
     * 
     * @return Die Lebenspunkte.
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * Setzt die Lebenspunkte des Helden auf einen neuen Wert.
     * Der Wert wird automatisch auf den Bereich 0-50 begrenzt.
     * 
     * @param healthPoints Die neuen Lebenspunkte.
     */
    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
        if (this.healthPoints < 0) this.healthPoints = 0;
        if (this.healthPoints > HEALTHPOINTS) this.healthPoints = HEALTHPOINTS;
    }
}