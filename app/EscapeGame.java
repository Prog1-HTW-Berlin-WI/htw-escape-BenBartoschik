package app;

import model.Hero;
import model.HTWRoom;

/*
@author: Ben Bartoschik und emmanuel bessong

Dies ist die haubtklasse für die spiellogic hier werden die räume und der held initialisiert.
und so alles was das spiel ausmacht.
*/

public class EscapeGame {
    private final Hero hero;
    private final HTWRoom[] rooms = new HTWRoom[3];
    private boolean gameRunning = true;
    private boolean gameFinished = false;

    public EscapeGame() {
        this.hero = new Hero();
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void run() {
        System.out.println("The game has started. Or not?");
    }

    public Hero getHero() {
        return hero;
    }
}
