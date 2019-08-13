/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.controller;

import fact.it.www.beans.Administrator;
import fact.it.www.beans.HappyHourBetaling;
import fact.it.www.beans.IngangTeller;
import fact.it.www.beans.NormaleBetaling;
import fact.it.www.beans.PoetsPersoon;
import fact.it.www.dao.BestellingFacade;
import fact.it.www.dao.PersoneelFacade;
import fact.it.www.dao.GerechtFacade;
import fact.it.www.dao.KeukenpersoneelFacade;
import fact.it.www.dao.TafelFacade;
import fact.it.www.dao.ZaalpersoneelFacade;
import fact.it.www.entity.Bestelling;
import fact.it.www.entity.Gerecht;
import fact.it.www.entity.Keukenpersoneel;
import fact.it.www.entity.Personeel;
import fact.it.www.entity.Tafel;
import fact.it.www.entity.Zaalpersoneel;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author bertv
 */
@Named(value = "personeelController")
@SessionScoped
public class PersoneelController implements Serializable {

    @EJB
    private PersoneelFacade personeelFacade;
    
    @EJB
    private ZaalpersoneelFacade zaalpersoneelFacade;
    
    @EJB
    private KeukenpersoneelFacade keukenpersoneelFacade;
    
    @EJB
    private GerechtFacade gerechtFacade;
    
    @EJB
    private TafelFacade tafelFacade;
    
    @EJB
    private BestellingFacade bestellingFacade;
    
    private List<Personeel> personeel = new ArrayList();
    /**
     * Creates a new instance of PersoneelController
     */
    public PersoneelController() {
    }

    public List<Personeel> getPersoneel() {
        return personeel;
    }

    public void setPersoneel(List<Personeel> personeel) {
        this.personeel = personeel;
    }
    
    public List<Personeel> findAll(){
        return this.personeelFacade.findAll();
    }
    public List<Zaalpersoneel> getAllZaalpersoneel(){
        return this.zaalpersoneelFacade.findAll();
    }
    
    public List<Keukenpersoneel> getAllKeukenpersoneel(){
        return this.keukenpersoneelFacade.findAll();
    }
    
    public String testSingletonPatroon() {
        System.out.println("####################################################################");
        IngangTeller it1  = IngangTeller.getInstance();
        IngangTeller it2; 
        it2 = IngangTeller.getInstance();
        if (it1 == it2) {
            System.out.println("De twee singletonvariabelen verwijzen naar hetzelfde object.");
        } else {
            System.out.println("Dit kan in principe niet.");
        }
        System.out.println("####################################################################");
        return "index";
    }
    
    public String testObserverPatroon() {
        IngangTeller klantTeller = IngangTeller.getInstance();

        //een paar personeelsleden
        Zaalpersoneel jan = new Zaalpersoneel("Jan");
        Zaalpersoneel piet = new Zaalpersoneel("Piet");
        Keukenpersoneel serge = new Keukenpersoneel("Serge");
        Keukenpersoneel jeroen = new Keukenpersoneel("Jeroen");

        //we koppelen de spelers en scheidsrechter als observer aan de bal
        klantTeller.attachObserver(jan);
        klantTeller.attachObserver(piet);
        klantTeller.attachObserver(serge);
        klantTeller.attachObserver(jeroen);
        this.personeelFacade.create(jan);
        this.personeelFacade.create(piet);
        this.personeelFacade.create(serge);
        this.personeelFacade.create(jeroen);

        System.out.println("####################################################################");
        System.out.println("Na het toevoegen van de observers...");
        //bal van positie veranderen
        klantTeller.setAantal(5);
        //lege lijn
        System.out.println();
        //we doen enkele observers weg
        klantTeller.detachObserver(piet);
        klantTeller.detachObserver(serge);

        System.out.println("Na het ontkoppelen van Piet en Serge ...");
        //we veranderen de bal weer van positie
        klantTeller.setAantal(3);
        System.out.println("####################################################################");
        return "index";
    }
    
    public String testDecoratorPatroon() {
        IngangTeller ingangTeller = IngangTeller.getInstance();
        // een nieuw zaalpersoneelslid toevoegen   
        System.out.println("####################################################################");
        Zaalpersoneel manu = new Zaalpersoneel("Manu");
        ingangTeller.attachObserver(manu);
        ingangTeller.setAantal(7);
        // we gaan manu detachen en hem als poetspersoon attachen zodat hij nog altijd kan reageren op de klantenteller maar daarbij ook kan schoonmaken
        System.out.println("####################################################################");
        ingangTeller.detachObserver(manu);
        ingangTeller.setAantal(10);
        PoetsPersoon poetsPersoon = new PoetsPersoon();
        poetsPersoon.setPersoneel(manu);
        poetsPersoon.schoonMaken();
        // Manu moet nu ook nog de administratie erbij nemen als iemand binnenkomt
        System.out.println("####################################################################");
        Administrator administrator = new Administrator();
        administrator.setPersoneel(manu);
        ingangTeller.attachObserver(administrator);
        ingangTeller.setAantal(5);
        System.out.println("####################################################################");

        return "index";
    }
    
    public String objectenAanmaken(){
        IngangTeller klantTeller = IngangTeller.getInstance();
        
        //5 tafelobjecten aanmaken
        Tafel tafel1 = new Tafel("tafel1");
        Tafel tafel2 = new Tafel("tafel2");
        Tafel tafel3 = new Tafel("tafel3");
        Tafel tafel4 = new Tafel("tafel4");
        Tafel tafel5 = new Tafel("tafel5");
        
        tafelFacade.create(tafel1);
        tafelFacade.create(tafel2);
        tafelFacade.create(tafel3);
        tafelFacade.create(tafel4);
        tafelFacade.create(tafel5);
        
        
        //5 personeelobjecten aanmaken
        Zaalpersoneel dirk = new Zaalpersoneel("Dirk");
        Zaalpersoneel joeri = new Zaalpersoneel("Joeri");
        Zaalpersoneel karel = new Zaalpersoneel("Karel");
        Keukenpersoneel liam = new Keukenpersoneel("Liam");
        Keukenpersoneel arne = new Keukenpersoneel("Arne");
        Administrator administrator = new Administrator();
        administrator.setPersoneel(dirk);
        
        klantTeller.attachObserver(dirk);
        klantTeller.attachObserver(joeri);
        klantTeller.attachObserver(karel);
        klantTeller.attachObserver(liam);
        klantTeller.attachObserver(arne);
        
        personeelFacade.create(dirk);
        personeelFacade.create(joeri);
        personeelFacade.create(karel);
        personeelFacade.create(liam);
        personeelFacade.create(arne);
        
        //Betalingstrategiën aanmaken
        HappyHourBetaling happyHourBetaling = new HappyHourBetaling();
        NormaleBetaling normaleBetaling = new NormaleBetaling();
        
        //5 gerechtobjecten aanmaken
        Gerecht frieten = new Gerecht();
        Gerecht spaghetti = new Gerecht();
        Gerecht gehaktbrood = new Gerecht();
        Gerecht hamburger = new Gerecht();
        Gerecht lasagne = new Gerecht();
        
        frieten.setNaam("Frieten");
        frieten.setActuelePrijs(4.70);
        spaghetti.setNaam("Spaghetti");
        spaghetti.setActuelePrijs(10.90);
        gehaktbrood.setNaam("Gehaktbrood");
        gehaktbrood.setActuelePrijs(8.60);
        hamburger.setNaam("Hamburger");
        hamburger.setActuelePrijs(6.90);
        lasagne.setNaam("Lasagne");
        lasagne.setActuelePrijs(9.30);
        
        gerechtFacade.create(frieten);
        gerechtFacade.create(spaghetti);
        gerechtFacade.create(gehaktbrood);
        gerechtFacade.create(hamburger);
        gerechtFacade.create(lasagne);
        
        
        //5 bestellingobjecten aanmaken
        Bestelling bestelling1 = new Bestelling();
        Bestelling bestelling2 = new Bestelling();
        Bestelling bestelling3 = new Bestelling();
        Bestelling bestelling4 = new Bestelling();
        Bestelling bestelling5 = new Bestelling();
        
        bestelling1.setBetaalStrategie(normaleBetaling);
        bestelling1.setDatum(new GregorianCalendar());
        bestelling1.setTafel(tafel1);
        bestelling1.addItem(frieten, 2);
        bestelling1.setZaalpersoneel(dirk);
        
        bestelling2.setBetaalStrategie(normaleBetaling);
        bestelling2.setDatum(new GregorianCalendar());
        bestelling2.setTafel(tafel2);
        bestelling2.addItem(lasagne, 2);
        bestelling2.addItem(hamburger, 1);
        bestelling2.setZaalpersoneel(joeri);
        
        bestelling3.setBetaalStrategie(happyHourBetaling);
        bestelling3.setDatum(new GregorianCalendar());
        bestelling3.setTafel(tafel3);
        bestelling3.addItem(gehaktbrood, 4);
        bestelling3.setZaalpersoneel(karel);
        
        bestelling4.setBetaalStrategie(normaleBetaling);
        bestelling4.setDatum(new GregorianCalendar());
        bestelling4.setTafel(tafel4);
        bestelling4.addItem(lasagne, 2);
        bestelling4.setZaalpersoneel(dirk);
        
        bestelling5.setBetaalStrategie(normaleBetaling);
        bestelling5.setDatum(new GregorianCalendar());
        bestelling5.setTafel(tafel5);
        bestelling5.addItem(frieten, 2);
        bestelling5.addItem(lasagne, 2);
        bestelling5.setZaalpersoneel(karel);
        
        bestellingFacade.create(bestelling1);
        bestellingFacade.create(bestelling2);
        bestellingFacade.create(bestelling3);
        bestellingFacade.create(bestelling4);
        bestellingFacade.create(bestelling5);
        
        personeel = personeelFacade.findAll();
        return "startpagina";
    }
}
