/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poblacionDecimal;

import poblacionBinario.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hatake
 */
public class Poblacion {
    private ArrayList<Individuo> individuos;
    private ArrayList<Integer> puntoDeCruza;
    private ArrayList<ArrayList<Integer>> patronDeCruza;

    public Poblacion(Integer alelos,Integer individuos){
    	this.individuos = new ArrayList<Individuo>();
    	puntoDeCruza = new ArrayList<Integer>();
    	generarPolacion(alelos,individuos);
    }
    
    public Poblacion(ArrayList<Individuo> in,ArrayList<Integer> pdc,ArrayList<ArrayList<Integer>> path){
        individuos = in;
        puntoDeCruza = pdc;
        patronDeCruza = path;
    }

    public ArrayList<ArrayList<Integer>> getPatronDeCruza() {
        return patronDeCruza;
    }

    public void setPatronDeCruza(ArrayList<ArrayList<Integer>> patronDeCruza) {
        this.patronDeCruza = patronDeCruza;
    }

    public Integer getIndividuos(){
    	return individuos.size();
    }

    public Individuo getIndividuo(Integer in){
    	return individuos.get(in);
    }

    public ArrayList<Integer> getPuntoDeCruza(){
    	return puntoDeCruza;
    }

    public Integer getPuntoDeCruzaPorIndividuo(Integer pCruza){
        return puntoDeCruza.get(pCruza);
    }

    private void generarPolacion(Integer alelos,Integer individuos){
    	for (int i = 0; i < individuos ; i++) {
    		this.individuos.add(new Individuo(alelos));
    	}
    }

    public Double probabilidad(Integer individuo){
    	return individuos.get(individuo).aptitud()*1.0/aptitud()*1.0;
    }
    
    public Double probabilidadRnaking(Integer individuo, Double S){
        Integer miu = individuos.size();
        return ((2.0-S)/miu)+(((2*individuo)*(S-1))/(miu*(miu-1.0)));
    }

    public Double aptitud() {
        Double sumAptitud;
        sumAptitud = 0.0;
        for(Individuo in: individuos)
            sumAptitud += in.aptitud();
        return sumAptitud;
    }

    public Double valor(){
    	Double sumValor;
        sumValor = 0.0;
        for(Individuo in: individuos)
            sumValor += in.valor();
        return sumValor;
    }

    public Double promedioAptitud(){
    	return Double.valueOf( aptitud()/getIndividuos() );
    }

    public Double promedioValor(){
    	return Double.valueOf( valor()/getIndividuos() );
    }

    public Double maxAptitud(){
		Double tmpIndi,aux;
		aux = 0.0;
		for (int i = 0; i < getIndividuos(); i++){
			tmpIndi = individuos.get(i).aptitud();
			if (tmpIndi > aux)
				aux = tmpIndi;
		}
		return aux;
    }

    /**
     * Regresa los datos para graficar la probabilidad de cada indivuduo de la poblacion
     */
    public ArrayList<Double> datosPorPoblacion(){
    	ArrayList<Double> datos = new ArrayList<Double>();
    	Integer aux = 0;
    	for (Individuo in: individuos)
    		datos.add(probabilidad(aux++));
    	return datos;
    }

    public Poblacion cruzaOX() throws CloneNotSupportedException{
    	puntoDeCruza = new ArrayList<Integer>();
    	Random rn = new Random();
    	ArrayList<Individuo> auxIND = new ArrayList<Individuo>();
        ArrayList<Integer> pCruza = new ArrayList<Integer>();
        ArrayList<Integer> acumulado,acumulado2, aux,aux2;
        Individuo inaux,inaux2;
    	Integer in,al,pc1,pc2,counter;
        Boolean flag,flag2;
    	in = getIndividuos();
    	al = individuos.get(0).getAlelos()+1;
    	for (int i = 0; i < in; i += 2){
            flag = true;
            pc1 = rn.nextInt(al);
            if (pc1 == 0) {
                pc1 = 1;
            }
            do{
                pc2 = rn.nextInt(al);
                if(pc2 >= pc1)
                    flag = false;
            }while(flag);
            pCruza.add(pc1);
            pCruza.add(pc2);
            inaux = new Individuo((ArrayList<Alelo>) individuos.get(i).clone());
            inaux2 = new Individuo((ArrayList<Alelo>) individuos.get(i+1).clone());
            counter = 0;
            acumulado = new ArrayList<Integer>();
            acumulado2 = new ArrayList<Integer>();
            
            aux = new ArrayList<Integer>();
            aux2 = new ArrayList<Integer>();
            
            for (int j = pc1-1; j < pc2; j++) {
                    aux.add(individuos.get(i).get(j).getValor());
                    aux2.add(individuos.get(i+1).get(j).getValor());
            }
            for (int j = 0; j < al-1; j++) {
                flag = true;
                flag2 = true;
                for (int k = 0; k < aux.size(); k++) {
                    if (individuos.get(i+1).get(j).getValor() == aux.get(k)) {
                        flag = false;
                    }
                    if (individuos.get(i).get(j).getValor() == aux2.get(k)) {
                        flag2 = false;
                    }
                }
                if (flag) {
                    acumulado.add(individuos.get(i+1).get(j).getValor());
                }
                if (flag2) {
                    acumulado2.add(individuos.get(i).get(j).getValor());
                }
            }
            for (int j = 0; j < al-1; j++) {
                if (j < pc1-1 || j >= pc2) {
                    inaux.reamplazarAlelo(j, acumulado.get(counter));
                    inaux2.reamplazarAlelo(j, acumulado2.get(counter++));
                }
            }
            
            auxIND.add(inaux);
            auxIND.add(inaux2);
	}
        return new Poblacion(auxIND, pCruza,new ArrayList<ArrayList<Integer>>());
    }
    
    public Poblacion cruzaPMX() throws CloneNotSupportedException{
    	puntoDeCruza = new ArrayList<Integer>();
    	Random rn = new Random();
    	ArrayList<Individuo> auxIND = new ArrayList<Individuo>();
        ArrayList<Integer> pCruza = new ArrayList<Integer>();
        ArrayList<Integer> aux,aux2;
        Individuo inaux,inaux2;
    	Integer in,al,pc1,pc2,counter1,counter2;
        Boolean flag,flag2;
    	in = getIndividuos();
    	al = individuos.get(0).getAlelos()+1;
    	for (int i = 0; i < in; i += 2){
            pc1 = rn.nextInt(al);
            flag = true;
            if (pc1 == 0) {
                pc1 = 1;
            }
            do{
                pc2 = rn.nextInt(al);
                if(pc2 >= pc1)
                    flag = false;
            }while(flag);
            pCruza.add(pc1);
            pCruza.add(pc2);
            inaux = new Individuo((ArrayList<Alelo>) individuos.get(i).clone());//Hijo uno
            inaux2 = new Individuo((ArrayList<Alelo>) individuos.get(i+1).clone());//Hijo dos
            
            aux = new ArrayList<Integer>();
            aux2 = new ArrayList<Integer>();
            
            for (int j = pc1-1; j < pc2; j++) {//Números para comparar en la contrucción de los hijos
                    aux.add(individuos.get(i).get(j).getValor());//Sellección del hijo uno
                    aux2.add(individuos.get(i+1).get(j).getValor());//Selección del hijo dos
                    inaux.reamplazarAlelo(j, individuos.get(i+1).get(j).getValor());
                    inaux2.reamplazarAlelo(j, individuos.get(i).get(j).getValor());
            }
            for (int j = 0; j < al-1; j++) {
                for (int k = 0; k < aux.size(); k++) {
                    if (j < pc1-1 || j >= pc2) {  
                        if (individuos.get(i+1).get(j).getValor() == aux.get(k)) {
                            inaux2.reamplazarAlelo(j, -1);
                        }
                        if (individuos.get(i).get(j).getValor() == aux2.get(k)) {
                            inaux.reamplazarAlelo(j, -1);
                        }
                    }
                }
            }
            counter1 = 0;
            counter2 = 0;
            Collections.sort(aux);
            Collections.sort(aux2);
            flag = true;
            flag2 = true;
            for (int j = 0; j < al-1; j++) {
                if (inaux.get(j).getValor() == -1) {
                    if (flag) {
                        for (int k = 0; k < inaux.getAlelos(); k++) {
                            if (inaux.get(k).getValor() == aux.get(counter1)){
                                if (counter1+1 == aux.size())
                                    break;
                                else
                                    counter1++;
                                k = 0;
                            }
                        }
                    }
                    inaux.reamplazarAlelo(j, aux.get(counter1++));
                }
                if (inaux2.get(j).getValor() == -1) {
                    if (flag2) {
                        for (int k = 0; k < inaux2.getAlelos(); k++) {
                            if (inaux2.get(k).getValor() == aux2.get(counter2)) {
                                if (counter2+1 == aux2.size())
                                    break;
                                else
                                    counter2++;
                                k = 0;
                            }
                        }
                    }
                    inaux2.reamplazarAlelo(j, aux2.get(counter2++));
                }
                if (counter1 == aux.size()) {
                    counter1 = 0;
                    flag = false;
                }
                if (counter2 == aux2.size()) {
                    counter2 = 0;
                    flag2 =false;
                }
            }
            
            auxIND.add(inaux);
            auxIND.add(inaux2);
	}
        return new Poblacion(auxIND, pCruza,new ArrayList<ArrayList<Integer>>());
    }
    
    public Poblacion cruzaPBX() throws CloneNotSupportedException{
    	puntoDeCruza = new ArrayList<Integer>();
    	Random rn = new Random();
    	ArrayList<Individuo> auxIND = new ArrayList<Individuo>();
        ArrayList<Integer> pCruza = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> patron = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> acumulado,acumulado2,tmp,tmp2;
        Individuo inaux,inaux2;
    	Integer in,al,counter,counter2;
        Double porcentaje1,porcentaje2;
    	in = getIndividuos();
    	al = individuos.get(0).getAlelos();
    	for (int i = 0; i < in; i += 2){
            inaux = new Individuo((ArrayList<Alelo>) individuos.get(i).clone());
            inaux2 = new Individuo((ArrayList<Alelo>) individuos.get(i+1).clone());
            acumulado = new ArrayList<Integer>();
            acumulado2 = new ArrayList<Integer>();
            porcentaje1 = rn.nextDouble()*100.0;
            porcentaje2 = rn.nextDouble()*100.0;
            tmp = generaPatronDeCruzaPorcentual(porcentaje1);
            tmp2 = generaPatronDeCruzaPorcentual(porcentaje2);
            counter = 0;
            counter2 = 0;
            for (int j = 0; j < al; j++) {
                if (tmp.get(counter) == j) {
                    acumulado.add(individuos.get(i).get(j).getValor());
                    counter++;
                    if (counter == tmp.size())
                        counter = 0;
                }
                else
                    inaux.reamplazarAlelo(j, -1);
                if (tmp2.get(counter2) == j) {
                    acumulado2.add(individuos.get(i+1).get(j).getValor());
                    counter2++;
                    if (counter2 == tmp2.size())
                        counter2 = 0;
                }
                else
                    inaux2.reamplazarAlelo(j, -1);
            }
            Collections.sort(acumulado);
            Collections.sort(acumulado2);
            patron.add(acumulado);
            patron.add(acumulado2);
            counter = 0;
            counter2 = 0;
            for (int j = 0; j < al; j++) {
                if (inaux.get(j).getValor() == -1) {
                    for (int k = 0; k < acumulado.size(); k++) {
                        if (individuos.get(i+1).get(counter).getValor() == acumulado.get(k)) {
                            counter++;
                            k = -1;
                        }
                    }
                    inaux.reamplazarAlelo(j, individuos.get(i+1).get(counter++).getValor());
                    if (counter == al) {
                        counter = 0;
                    }
                }
                if (inaux2.get(j).getValor() == -1) {
                    for (int k = 0; k < acumulado2.size(); k++) {
                        if (individuos.get(i).get(counter2).getValor() == acumulado2.get(k)) {
                            counter2++;
                            k = -1;
                        }
                    }
                    inaux2.reamplazarAlelo(j, individuos.get(i).get(counter2++).getValor());
                    if (counter2 == al) {
                        counter2 = 0;
                    }
                }
            }
            auxIND.add(inaux);
            auxIND.add(inaux2);
	}
        return new Poblacion(auxIND, pCruza, patron);
    }
    
    private ArrayList<Integer> generaPatronDeCruza(){
        Random rn = new Random();
        ArrayList<Integer> patron = new ArrayList<Integer>();
        Integer cantidad,longitud;
        longitud = individuos.get(0).getAlelos();
        Integer aux,anterior;
        Boolean flag = false;
        cantidad = longitud;
        cantidad /= 2;
        for(int i = 0;i < cantidad ; i++){
            aux = rn.nextInt(longitud);
            if(i != 0){
                do{
                    for(int x = 0; x < patron.size(); x++){
                        if(patron.get(x) == aux){
                            flag = true;
                            aux = rn.nextInt(longitud);
                            break;
                        }
                        else
                            flag = false;
                    }
                }while(flag);
            }
            patron.add(aux);
        }
        Collections.sort(patron);
        return patron;
    }
    
    private ArrayList<Integer> generaPatronDeCruzaPorcentual(Double porcentaje){
        ArrayList<Integer> seleccion = new ArrayList<Integer>();
        Random rn = new Random();
        Integer cantidad,tmp;
        Double aux = porcentaje*individuos.size()/100;
        cantidad = aux.intValue();
        if (cantidad == 0)
            cantidad = 1;
        for(int x = 0;x < cantidad;x++){
            tmp = rn.nextInt(individuos.size());
            for (int i = 0; i < seleccion.size(); i++) {
                if(tmp.equals(seleccion.get(i))){
                    tmp = rn.nextInt(individuos.size());
                    i = 0;
                }
            }
            seleccion.add(tmp);
        }
        Collections.sort(seleccion);
        return seleccion;
    }
    
    private Double ruletaDouble(){
        Random rn = new Random();
        return rn.nextInt(100)*1.0/100.0;
    }
    
    private Integer ruletaInteger(){
        Random rn = new Random();
        return rn.nextInt(100);
    }
    
    private Individuo acumulado(Double ac){
        Double cumulo = 0.0;
        Integer tmp = 0;
        for(int x = 0;x<individuos.size();x++){
            if(ac >= cumulo)
                cumulo += probabilidad(x);
            else{
                tmp = x;
                break;
            }
        }
        return individuos.get(tmp);
    }
    
    private ArrayList<Integer> ordenaLosIndividuosPorAptitud(){
        ArrayList<Individuo> tmp = new ArrayList<Individuo>(individuos);
        ArrayList<Integer> aux = new ArrayList<Integer>();
        Collections.sort(tmp);
        for(Individuo in:tmp){
            for(int x = 0;x<individuos.size();x++){
                if(in.equals(individuos.get(x)))
                    aux.add(x);
            }
        }
        return aux;
        //Hacer metodo de ordenamiento para generar el oden por aptitus osea 5,3,8,1,4,2,6,7
    }
    
    private Individuo acumuladoRanking(Double ac,Double S){
        Double cumulo = 0.0;
        Integer tmp = 0;
        ArrayList<Integer> ordenPorAptitud = ordenaLosIndividuosPorAptitud();
        for(int x = 0;x<individuos.size();x++){
            if(ac >= cumulo)
                cumulo += probabilidadRnaking(ordenPorAptitud.get(x),S);
            else{
                tmp = x;
                break;
            }
        }
        return individuos.get(tmp);
    }
    
    private Individuo torneo(Double torneo){
        Random rn = new Random();
        Integer rn1,rn2;
        rn1 = rn.nextInt(individuos.size());
        rn2 = rn.nextInt(individuos.size());
        return competir(individuos.get(rn1),individuos.get(rn2),torneo);
    }
    
    private Individuo competir(Individuo in1, Individuo in2,Double torneo){
        Double aux1,aux2;
        Integer mayor,menor;
        aux1 = in1.aptitud();
        aux2 = in2.aptitud();
        if (aux1 > aux2){
            mayor = 1;
            menor = 2;
        }
        else{
            mayor = 2;
            menor = 1;
        }
        
        if(ganador(torneo,mayor,menor) == 1)
            return in1;
        else
            return in2;
    }
    
    private Integer ganador(Double torneo,Integer mayor,Integer menor){
        if(ruletaDouble() <= torneo)
            return mayor;
        else
            return menor;
    }
    
    public void seleccionarPorRuleta(){
        ArrayList<Individuo> newPoblacion =  new ArrayList<Individuo>();
        newPoblacion.add(maxAlelo());
        for(int x = 0;x < individuos.size()-1;x++){
            newPoblacion.add(acumulado(ruletaDouble()));
        }
        individuos = newPoblacion;
    }
    
    public void seleccionarPorRuletaConRanking(Double S){
        ArrayList<Individuo> newPoblacion =  new ArrayList<Individuo>();
        newPoblacion.add(maxAlelo());
        if(S > 2.0){
            S = 2.0;
            System.out.println("El valor de S supera el valor tope y fue reasignado por el valor: " + S);
        }
        else if(S < 1.0){
            S = 1.0;
            System.out.println("El valor de S es menor que el valor nímino y fue reasignado por el valor: " + S);
        }
        for(int x = 0;x < individuos.size()-1;x++){
            newPoblacion.add(acumuladoRanking(ruletaDouble(),S));
        }
        individuos = newPoblacion;
    }
    
    public void seleccionarPorTorneoP(Double torneo){
        ArrayList<Individuo> newPoblacion = new ArrayList<Individuo>();
        for(int x = 0; x < individuos.size();x++){
            newPoblacion.add(torneo(torneo));
        }
        individuos = newPoblacion;
    }
    
    public Individuo maxAlelo(){
        Double aux = 0.0;
        Individuo in = null;
        for(int x = 0;x < individuos.size();x++){
            if(individuos.get(x).aptitud() > aux){
                aux = individuos.get(x).aptitud();
                in = individuos.get(x);
            }
        }
        return in;
    }
    
    public Double min(){
        Double aux = max();
        for(int x = 0;x < individuos.size();x++){
            if(individuos.get(x).aptitud() < aux)
                aux = individuos.get(x).aptitud();
        }
        return aux;
    }
    
    public Double max(){
        Double aux = 0.0;
        for(int x = 0;x < individuos.size();x++){
            if(individuos.get(x).aptitud() > aux)
                aux = individuos.get(x).aptitud();
        }
        return aux;
    }

    @Override
    protected void finalize() throws Throwable {
    	individuos.clear();
    	puntoDeCruza.clear();
    }
    
    @Override
    public String toString(){
    	return "La poblacion tiene las caracteristicas:\n\tAlelos: " + individuos.get(0) + "\n\tIndividuos: " + this.getIndividuos();
    }
}
