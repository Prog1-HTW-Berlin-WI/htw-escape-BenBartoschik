package model;

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


}