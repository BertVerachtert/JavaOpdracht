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
public class Keukenpersoneel extends Personeel implements Serializable {

    public Keukenpersoneel() {
    }

    public Keukenpersoneel(String naam) {
        super(naam);
    }
       
    //Doe dit als er klanten binnenkomen
    @Override
    public void update(){
        String keukenstring = "Ik ben " + getNaam() + " en ik begin onmiddellijk met het maken van " 
                + IngangTeller.getInstance().getAantal() + " amuse-geules!";
        System.out.println(keukenstring);
    }
    
}
