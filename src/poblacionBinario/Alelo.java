/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poblacionBinario;

import java.util.Random;

/**
 *
 * @author Hatake
 */
public class Alelo {
    private Integer alelo;

    public Alelo(){
    	alelo = nuevoAlelo();
    }
    
    public Alelo(Integer alelo){
        this.alelo = alelo;
    }

    public Integer getValor(){
        return alelo;
    }

    public void setValor(Integer val){
        alelo = val;
    }

    private Integer nuevoAlelo(){
        Random rn = new Random();
        if (rn.nextBoolean())
            return 1;
        else
            return 0;
    }

    public Alelo cambiarValor(){
        if (alelo == 0)
            alelo = 1;
        else
            alelo = 0;
        return this;
    }
    
    public Alelo XOR(Alelo al){
        if (this.getValor() != al.getValor())
            return new Alelo(1);
        else
            return new Alelo(0);
    }
        
    @Override
    public String toString(){
    	return alelo + "";
    }
}
