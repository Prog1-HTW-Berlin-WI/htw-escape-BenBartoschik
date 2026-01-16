package model;

import java.io.Serializable;

public class Lecturer implements Serializable {

    private static final long serialVersionUID = 540082607047283589L;

    private String name;
    private boolean hasSigned;

    public Lecturer(String name) {
        this.name = name;
        this.hasSigned = false;
    }

    public boolean isReadyToSign() {
        return !hasSigned;
    }

    public void sign() {
        this.hasSigned = true;
    }

    public boolean hasSigned() {
        return hasSigned;
    }

    public String getName() {
        return name;
    }
}
