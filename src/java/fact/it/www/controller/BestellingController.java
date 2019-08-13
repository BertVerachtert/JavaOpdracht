/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.controller;

import fact.it.www.beans.HappyHourBetaling;
import fact.it.www.beans.NormaleBetaling;
import fact.it.www.dao.BestellingFacade;
import fact.it.www.dao.GerechtFacade;
import fact.it.www.dao.TafelFacade;
import fact.it.www.entity.Bestelling;
import fact.it.www.entity.Gerecht;
import fact.it.www.entity.Tafel;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author bertv
 */
@Named(value = "bestellingController")
@Dependent
public class BestellingController {

    @EJB
    private GerechtFacade gerechtFacade;

    @EJB
    private BestellingFacade bestellingFacade;
    
    @EJB
    private TafelFacade tafelFacade;
    
    private List<Bestelling> bestellingen;
    private List<Tafel> tafels = new ArrayList<>();
    private List<Gerecht> gerechten = new ArrayList<>();
    private Tafel tafel;
    private Bestelling bestelling;
    

    public Tafel getTafel() {
        return tafel;
    }

    public void setTafel(Tafel tafel) {
        this.tafel = tafel;
    }

    /**
     * Creates a new instance of BestellingController
     */
    public BestellingController() {
    }

    public List<Gerecht> getGerechten() {
        return gerechten;
    }

    public void setGerechten(List<Gerecht> gerechten) {
        this.gerechten = gerechten;
    }

    public Bestelling getBestelling() {
        return bestelling;
    }

    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }
    
    public List<Bestelling> findAll(){
        return this.bestellingFacade.findAll();
    }

    public List<Bestelling> getBestellingen() {
        return bestellingen;
    }

    public void setBestellingen(List<Bestelling> bestellingen) {
        this.bestellingen = bestellingen;
    }
    
    public String testStrategyPatroon() {
        System.out.println("####################################################################");
        //Betalingstrategieën aanmaken
        HappyHourBetaling happyHourBetaling = new HappyHourBetaling();
        NormaleBetaling normaleBetaling = new NormaleBetaling();
        //gerechten aanmaken
        Gerecht videe = new Gerecht();
        videe.setNaam("Vidée met frietjes");
        videe.setActuelePrijs(15.0);
        Gerecht croque = new Gerecht();
        croque.setNaam("Croque Monsieur");
        croque.setActuelePrijs(10.0);
        gerechtFacade.create(croque);
        gerechtFacade.create(videe);

        //maak bestelling met bestelitems
        Bestelling bestelling = new Bestelling();
        //NORMAAL
        bestelling.setBetaalStrategie(normaleBetaling);
        bestelling.setDatum(new GregorianCalendar());
        bestelling.addItem(videe, 2);
        bestelling.addItem(croque, 2);

        //HAPPYHOUR
        bestelling.setBetaalStrategie(happyHourBetaling);
        bestelling.addItem(videe, 2);
        bestelling.addItem(croque, 2);

        //NORMAAL
        bestelling.setBetaalStrategie(normaleBetaling);
        bestelling.addItem(videe, 2);
        bestelling.addItem(croque, 2);

        bestelling.maakRekening();
        System.out.println("####################################################################");
        
        //persistent maken
        this.bestellingFacade.create(bestelling);

        return "index";
    }
    public String getBestellingOpTafel(String tafel){
        bestellingen = this.bestellingFacade.BestellingPerTafel(tafel);
        return "zoekOpTafel";
    }
}
