/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poblacionDecimal;

import java.util.Objects;
import poblacionBinario.*;
import java.util.Random;

/**
 *
 * @author Hatake
 */
public class Alelo {
    private Integer alelo;

    public Alelo(){
    	alelo = 1;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Alelo other = (Alelo) obj;
        if (!Objects.equals(this.alelo, other.alelo)) {
            return false;
        }
        return true;
    }
    
    public Alelo(Integer alelo){
        this.alelo = alelo;
    }

    public Integer getValor(){
        return alelo;
    }

    public void setValor(Integer val){
        if (val == 0) {
            val = 1;
        }
        alelo = val;
    }

    public int compareTo(Integer anotherInteger) {
        return alelo.compareTo(anotherInteger);
    }
        
    @Override
    public String toString(){
    	return alelo + " ";
    }
}
