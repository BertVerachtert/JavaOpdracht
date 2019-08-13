/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.controller;

import fact.it.www.dao.TafelFacade;
import fact.it.www.entity.Tafel;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;

/**
 *
 * @author bertv
 */
@Named(value = "navigatieController")
@Dependent
public class NavigatieController {

    @EJB
    private TafelFacade tafelFacade;
    
    private List<Tafel> tafels;
    /**
     * Creates a new instance of NavigatieController
     */
    public NavigatieController() {
    }

    public List<Tafel> getTafels() {
        return tafels;
    }

    public void setTafels(List<Tafel> tafels) {
        this.tafels = tafels;
    }
    
    public String startpagina(){
        return "startpagina";
    }
    
    public String zoeken(){
        return "zoeken";
    }
}
