package model;

/**
 * Repräsentiert einen freundlichen Alien im Spiel.
 * Ein Freundliches Alien es soll keine Herausforderung für den Spieler darstellen.
 */
public class AlienFrendly extends Alien {
    
    public AlienFrendly() {
        super("Eduat", 1, true, "Hey I'm Eduat, wanne learn how to traide crypto?");
    }

    @Override
    public int doDamage() {
        return 0;
    }
}



