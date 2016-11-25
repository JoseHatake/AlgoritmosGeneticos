/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poblacionDecimal;

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
        
    @Override
    public String toString(){
    	return alelo + "";
    }
}
