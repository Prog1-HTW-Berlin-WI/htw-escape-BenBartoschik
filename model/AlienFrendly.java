package model;

import java.io.Serializable;

/**
 * Repräsentiert einen freundlichen Alien im Spiel.
 * Ein Freundliches Alien es soll keine Herausforderung für den Spieler
 * darstellen.
 */
public class AlienFrendly extends Alien implements Serializable {

    private static final long serialVersionUID = 1729389822767173584L;

    /**
     * Erstellt ein freundliches Alien.
     */

    public AlienFrendly() {
        super("Eduat", 1, true, "Hey I'm Eduat, wanne learn how to traide crypto?");
    }

    /**
     * Verursacht keinen Schaden.
     *
     * @return Schaden
     */

    @Override
    public int doDamage() {
        return 0;
    }
}
