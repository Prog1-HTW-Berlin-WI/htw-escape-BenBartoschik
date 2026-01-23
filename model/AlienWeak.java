package model;

public class AlienWeak extends Alien {
    public AlienWeak() {
        super("Manfred", 25, false, "Zzz... I'm Manfred, the Alien! U can run, but u can't hide!");
    }

    @Override
    public int doDamage() {
        return 5;
    }
}
