package app;

import model.Hero;
import model.HTWRoom;
import java.util.Scanner;

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

    public void gameMenu(){

    }
   /* 
noch hinzuzufügen 

liste der Lehrer
liste der räume
verbindung operational
beim spielstart zuweisung von räumen und lehrern
}
    */
   private void mainMenuoptions(){ 
        System.out.println("(1) Hochschule erkunden");
        System.out.println("(2) Hero Status anzeigen");
        System.out.println("(3) Laufzettel anzeigen");
        System.out.println("(4) Verschnaufpause machen");
        System.out.println("(5) Spiel verlassen");
   }

    private String mainMenuinput() {
        Scanner sc1 = new Scanner(System.in);
        String menuInput = sc1.nextLine();
        return menuInput;
    }
 


   private void mainMenu(String menuInput){
    switch (menuInput) {
        case "1":
            
            break;
    
        default:
            break;
    }

   }




   







}
