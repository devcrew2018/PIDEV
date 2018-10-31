/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop.entites;

import java.util.Objects;

/**
 *
 * @author takoua
 */
public class Statistic {
    private String evenement ;
    private String etat ;

    public Statistic() {
    }

    public Statistic(String evenement, String etat) {
        this.evenement = evenement;
        this.etat = etat;
    }

    public String getEvenement() {
        return evenement;
    }

    public String getEtat() {
        return etat;
    }

    public void setEvenement(String evenement) {
        this.evenement = evenement;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Statistic{" + "evenement=" + evenement + ", etat=" + etat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.evenement);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Statistic other = (Statistic) obj;
        if (!Objects.equals(this.evenement, other.evenement)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
}
