package model;

/**
 * Abstrakte Klasse, die einen Alien im Spiel repräsentiert.
 * Ein Alien hat einen Namen, Lebenspunkte, eine Freundlichkeitseigenschaft/Agresivität und eine Begrüßung.
 */

public abstract class Alien {

    private static final long serialVersionUID = 1729389822767173584L;

    protected String name;
    protected int lifePoints;
    protected boolean friendly;
    protected String greeting;

    public Alien(String name, int lifePoints, boolean friendly, String greeting) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.friendly = friendly;
        this.greeting = greeting;
    }

    public int takeDamage(int amount) {
        this.lifePoints -= amount;
        return this.lifePoints;
    }

    public boolean isDefeated() {
        return this.lifePoints <= 0;
    }

    public  abstract int doDamage();

    public String getName() {
     return name; }

     public int getLifePoints() {
    return lifePoints; }

    public boolean isFriendly() {
     return friendly; }

     public String getGreeting() {
     return greeting; }



}