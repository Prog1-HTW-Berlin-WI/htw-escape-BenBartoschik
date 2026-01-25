package model;

import java.io.Serializable;

/**
 * Abstrakte Klasse, die einen Alien im Spiel repräsentiert.
 * Ein Alien hat einen Namen, Lebenspunkte, eine
 * Freundlichkeitseigenschaft/Agresivität und eine Begrüßung.
 */

public abstract class Alien implements Serializable {

    private static final long serialVersionUID = 1729389822767173584L;

    protected String name;
    protected int lifePoints;
    protected boolean friendly;
    protected String greeting;

    /**
     * Erstellt ein Alien.
     *
     * @param name       Name
     * @param lifePoints Lebenspunkte
     * @param friendly   Freundlichkeitsstatus
     * @param greeting   Begrüßung
     */

    public Alien(String name, int lifePoints, boolean friendly, String greeting) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.friendly = friendly;
        this.greeting = greeting;
    }

    /**
     * Zieht dem Alien Lebenspunkte ab.
     *
     * @param amount Schaden
     * @return verbleibende Lebenspunkte
     */

    public int takeDamage(int amount) {
        this.lifePoints -= amount;
        return this.lifePoints;
    }

    /**
     * Prüft, ob das Alien besiegt ist.
     *
     * @return besiegt oder nicht
     */

    public boolean isDefeated() {
        if (lifePoints < 0) {
            lifePoints = 0;
        }
        return lifePoints <= 0;
    }

    /**
     * Führt einen Angriff aus.
     *
     * @return Schaden
     */

    public abstract int doDamage();

    /**
     * Gibt den Namen des Aliens zurück.
     *
     * @return Name
     */

    public String getName() {
        return name;
    }

    /**
     * Gibt die Lebenspunkte zurück.
     *
     * @return Lebenspunkte
     */

    public int getLifePoints() {
        return lifePoints;
    }

    /**
     * Prüft, ob das Alien freundlich ist.
     *
     * @return freundlich oder feindlich
     */

    public boolean isFriendly() {
        return friendly;
    }

    /**
     * Gibt die Begrüßung zurück.
     *
     * @return Begrüßung
     */

    public String getGreeting() {
        return greeting;
    }

}