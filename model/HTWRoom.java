package model;

import java.io.Serial;
import java.io.Serializable;

/**
 * Repräsentiert die Räume im Spiel.
 * Jeder Raum hat eine eindeutige Kennung, eine Beschreibung und einen zugehörigen Dozenten.
 */

public class HTWRoom implements Serializable {

    private static final long serialVersionUID = 9065680017147292999L;

    private String identifier;
    private String description;
    private Lecturer lecturer;

    public HTWRoom(String identifier, String description, Lecturer lecturer){
        this.identifier = identifier;
        this.description = description;
        this.lecturer = lecturer;
    }
        
        public String getIdentifier(){
            return identifier;
        }

        public String getDescription(){
            return description;
        }
        public Lecturer getLecturer(){
            return lecturer;
        }

}
