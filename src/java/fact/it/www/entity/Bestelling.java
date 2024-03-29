/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.entity;

import fact.it.www.beans.BetaalStrategie;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author bertv
 */
@NamedQuery(
name="Bestelling.opTafel",
        query="select b from Bestelling b where b.tafel.code like :code")
@Entity
public class Bestelling implements Serializable {

   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private GregorianCalendar datum;
    private boolean betaald;
    
    @OneToMany(mappedBy="bestelling", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<BesteldItem> besteldeItems = new ArrayList<BesteldItem>();
    
    @ManyToOne
    private Tafel tafel;
    
    @ManyToOne
    private Zaalpersoneel zaalpersoneel;

    @Transient
    private BetaalStrategie betaalStrategie;
    
    public Zaalpersoneel getZaalpersoneel() {
        return zaalpersoneel;
    }

    public void setZaalpersoneel(Zaalpersoneel zaalpersoneel) {
        this.zaalpersoneel = zaalpersoneel;
    }

    public List<BesteldItem> getBesteldeItems() {
        return besteldeItems;
    }

    public void setBesteldeItems(List<BesteldItem> besteldeItems) {
        this.besteldeItems = besteldeItems;
    }

    public Tafel getTafel() {
        return tafel;
    }

    public void setTafel(Tafel tafel) {
        this.tafel = tafel;
    }

    public Bestelling() {
    }

    public GregorianCalendar getDatum() {
        return datum;
    }

    public void setDatum(GregorianCalendar datum) {
        this.datum = datum;
    }

    public boolean isBetaald() {
        return betaald;
    }

    public void setBetaald(boolean betaald) {
        this.betaald = betaald;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void addItem(Gerecht gerecht, int aantal){
        BesteldItem besteldItem = new BesteldItem();
        besteldItem.setAantal(aantal);
        besteldItem.setGerecht(gerecht);
        besteldItem.setBestelling(this);
        besteldItem.setToegepastePrijs(betaalStrategie.getToegepastePrijs(gerecht.getActuelePrijs()));
        besteldeItems.add(besteldItem);
    }
    
    public void maakRekening(){
        double sum = 0;
        for (BesteldItem bi:besteldeItems){
            sum += bi.getAantal() * bi.getToegepastePrijs();
            System.out.println(bi.getAantal() + " " + bi.getGerecht().getNaam() + " prijs " + bi.getAantal() * bi.getToegepastePrijs());
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("Totaal: " + sum);
        System.out.println("----------------------------------------------------------");
        System.out.println("Op dit moment is het volgende betaaltype van toepassing: ");
    }

    public BetaalStrategie getBetaalStrategie() {
        return betaalStrategie;
    }

    public void setBetaalStrategie(BetaalStrategie betaalStrategie) {
        this.betaalStrategie = betaalStrategie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bestelling)) {
            return false;
        }
        Bestelling other = (Bestelling) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fact.it.www.entity.Bestelling[ id=" + id + " ]";
    }
    
}
