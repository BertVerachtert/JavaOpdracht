/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.entity;

import fact.it.www.beans.IngangTeller;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author bertv
 */
@Entity
public class Zaalpersoneel extends Personeel implements Serializable {

     public Zaalpersoneel(String naam) {
        super(naam);
    }

    public Zaalpersoneel() {
    }
    
    @Override
    public void update(){
        String zaalstring = "Ik ben " + getNaam() + " en ik ga het nodige doen om " 
                + IngangTeller.getInstance().getAantal() + " klanten een tafel klaar te maken!";
        System.out.println(zaalstring);
    }
    
}
