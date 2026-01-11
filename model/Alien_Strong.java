package model;

public class Alien_Strong extends Alien {

    public Alien_Strong() {
        super("Gerald", 50, false, "Grrrrr... I'm Gerald, the Alien! And I will eat u alive!");
    }

    @Override
    public int doDamage() {
        return 30;
    }
}

