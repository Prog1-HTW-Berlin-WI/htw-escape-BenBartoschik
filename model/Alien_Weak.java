package model;

public class Alien_Weak extends Alien {
    public Alien_Weak() {
        super("Manfred", 25, false, "Zzz... I'm Manfred, the Alien! U can run, but u can't hide!");
    }

    @Override
    public int doDamage() {
        return 5;
    }
}
