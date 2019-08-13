/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fact.it.www.beans;

/**
 *
 * @author bertv
 */
public final class IngangTeller extends Subject{
    private int aantal;
    private static IngangTeller ingangTeller;
    
    private IngangTeller(){
        System.out.println("Er wordt een singleton-object gemaakt.");
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
        notifyObservers();
    }
    
    public static IngangTeller getInstance(){
        if (ingangTeller == null){
            ingangTeller = new IngangTeller();
        }
        return ingangTeller;
    }
    
}
