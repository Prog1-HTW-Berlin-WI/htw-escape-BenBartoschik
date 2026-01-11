package model;

import java.io.Serializable;

public class Lecturer implements Serializable {

    private String name = "Prof. Gärtner";
    private boolean hasSigned = false;

   public boolean isReadyToSign() {
        if (!hasSigned) {
            sign();
            return true;
        }
            
        else{
            return false;
        }

    }

    private void sign() {
        this.hasSigned = true;
    }
       private static final long serialVersionUID = 540082607047283589L;
}