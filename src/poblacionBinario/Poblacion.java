/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poblacionBinario;

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
    private ArrayList<Integer> patron;
    private ArrayList<ArrayList<Integer>> patronMultiple;

    public Poblacion(Integer alelos,Integer individuos){
    	this.individuos = new ArrayList<Individuo>();
    	puntoDeCruza = new ArrayList<Integer>();
        patron = new ArrayList<Integer>();
        patronMultiple = new ArrayList<ArrayList<Integer>>();
    	generarPolacion(alelos,individuos);
    }

    public Poblacion(ArrayList<Individuo> in, ArrayList<Integer> pdc){
    	individuos = in;
    	puntoDeCruza = pdc;
        patron = new ArrayList<Integer>();
    }

    public ArrayList<ArrayList<Integer>> getPatronMultiple() {
        return patronMultiple;
    }

    public void setPatronMultiple(ArrayList<ArrayList<Integer>> patronMultiple) {
        this.patronMultiple = patronMultiple;
    }

    public ArrayList<Integer> getPatron() {
        return patron;
    }

    public void setPatron(ArrayList<Integer> patron) {
        this.patron = patron;
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

    /**
     * Primera mutación simple, se intercambian los cromozomas de dos 
     * individuos dependiendo del punto de cruza aleatorio
     */
    public Poblacion cruzaUnPunto() throws CloneNotSupportedException{
        puntoDeCruza = new ArrayList<Integer>();
        patron = new ArrayList<Integer>();
        patronMultiple = new ArrayList<ArrayList<Integer>>();
    	Random rn = new Random();
    	ArrayList<Individuo> auxIND = new ArrayList<Individuo>();
        ArrayList<Integer> pCruza = new ArrayList<Integer>();
        Individuo inaux,inaux2;
    	Integer in,al,pc;
    	in = getIndividuos();
    	al = individuos.get(0).getAlelos();
    	for (int i = 0; i < in; i += 2){
            pc = rn.nextInt(al);
            pCruza.add(pc);
            pCruza.add(pc);
            inaux = new Individuo((ArrayList<Alelo>) individuos.get(i).clone());
            inaux2 = new Individuo((ArrayList<Alelo>) individuos.get(i+1).clone());
            
            for (int j = al-1; j >= pc; j--){
                inaux.reamplazarAlelo(j,individuos.get(i+1).get(j).getValor());
                inaux2.reamplazarAlelo(j,individuos.get(i).get(j).getValor());
            }
            auxIND.add(inaux);
            auxIND.add(inaux2);
	}
        return new Poblacion(auxIND, pCruza);
    }

    /**
     * Dos puntos de cruza aleatorios
     */
    public Poblacion cruzaDosPuntos() throws CloneNotSupportedException{
        puntoDeCruza = new ArrayList<Integer>();
        patron = new ArrayList<Integer>();
        patronMultiple = new ArrayList<ArrayList<Integer>>();
    	Random rn = new Random();
    	ArrayList<Individuo> auxIND = new ArrayList<Individuo>();
        ArrayList<Integer> pCruza = new ArrayList<Integer>();
        Individuo inaux,inaux2;
    	Integer in,al,pc1,pc2;
        Boolean flag;
    	in = getIndividuos();
    	al = individuos.get(0).getAlelos();
    	for (int i = 0; i < in; i += 2){
            flag = true;
            pc1 = rn.nextInt(al);
            do{
                pc2 = rn.nextInt(al);
                if(pc2 >= pc1)
                    flag = false;
            }while(flag);
            pCruza.add(pc1);
            pCruza.add(pc2);
            inaux = new Individuo((ArrayList<Alelo>) individuos.get(i).clone());
            inaux2 = new Individuo((ArrayList<Alelo>) individuos.get(i+1).clone());
            
            for (int j = pc2-1; j >= pc1; j--){
                inaux.reamplazarAlelo(j,individuos.get(i+1).get(j).getValor());
                inaux2.reamplazarAlelo(j,individuos.get(i).get(j).getValor());
            }
            auxIND.add(inaux);
            auxIND.add(inaux2);
	}
        return new Poblacion(auxIND, pCruza);
    }
    
    /**
     * Cruza uniformemente distribuida
     */
    public Poblacion cruzaUniforme() throws CloneNotSupportedException{
        puntoDeCruza = new ArrayList<Integer>();
        patron = new ArrayList<Integer>();
        patronMultiple = new ArrayList<ArrayList<Integer>>();
    	Random rn = new Random();
    	ArrayList<Individuo> auxIND = new ArrayList<Individuo>();
        patron = generaPatronDeCruza();
        Individuo inaux,inaux2;
        Integer in;
        in = getIndividuos();
        for(int i = 0; i < in; i += 2){
            inaux = new Individuo((ArrayList<Alelo>) individuos.get(i).clone());
            inaux2 = new Individuo((ArrayList<Alelo>) individuos.get(i+1).clone());
            for (int j = 0; j < patron.size(); j++){
                inaux.reamplazarAlelo(patron.get(j),individuos.get(i+1).get(patron.get(j)).getValor());
                inaux2.reamplazarAlelo(patron.get(j),individuos.get(i).get(patron.get(j)).getValor());
            }
            auxIND.add(inaux2);
            auxIND.add(inaux);
        }
        // Imprime el patros de cruza que cambiará a la poblacion en esta generacion
        System.out.println("Patron de cruza" + patron);
        return new Poblacion(auxIND, new ArrayList<Integer>());
    }
    
    /**
     * Cruza uniformemente distribuida
     */
    public Poblacion cruzaAcentuada() throws CloneNotSupportedException{
        puntoDeCruza = new ArrayList<Integer>();
        patron = new ArrayList<Integer>();
        patronMultiple = new ArrayList<ArrayList<Integer>>();
    	Random rn = new Random();
    	ArrayList<Individuo> auxIND = new ArrayList<Individuo>();
        ArrayList<ArrayList<Integer>> patron2 = generarPatronMultiple(50.0);
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        Individuo inaux,inaux2;
        Integer in,contador1,contador2,al;
        Boolean flag,flag2;
        in = getIndividuos();
        al = individuos.get(0).getAlelos();
        patronMultiple = patron2;
        for(int i = 0; i < in; i += 2){
            inaux = new Individuo((ArrayList<Alelo>) individuos.get(i).clone());
            inaux2 = new Individuo((ArrayList<Alelo>) individuos.get(i+1).clone());
            flag = false;
            flag2 = true;
            contador1 = 0;
            contador2 = 0;               
            for (int j = 0; j < al; j++) {
                if (patron2.get(i).get(contador1) == j) {
                    flag = !flag;
                    contador1++;
                    if(contador1 == patron2.get(i).size())
                        contador1 = 0;
                    for (int k = 0; k < patron2.get(i+1).size(); k++) {
                        if (patron2.get(i+1).get(k) == j) {
                            flag2 = false;
                            contador2++;
                            if(contador2 == patron2.get(i+1).size())
                                contador2 = 0;
                            break;
                        }
                        else
                            flag2 = true;
                    }
                }
                else if(patron2.get(i+1).get(contador2) == j && flag2){
                    flag = !flag;
                    contador2++;
                    if(contador2 == patron2.get(i+1).size())
                        contador2 = 0;
                }
                if (flag) {
                    inaux.reamplazarAlelo(j,individuos.get(i+1).get(j).getValor());
                    inaux2.reamplazarAlelo(j,individuos.get(i).get(j).getValor());
                }
            }
            auxIND.add(inaux);
            auxIND.add(inaux2);
        }
        // Imprime el patros de cruza que cambiará a la poblacion en esta generacion
        System.out.println("Patron de cruza" + patron2);
        return new Poblacion(auxIND, new ArrayList<Integer>());
    }
    
    private ArrayList<ArrayList<Integer>> generarPatronMultiple(Double porcentaje){
        ArrayList<ArrayList<Integer>> patronMul = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> tmp;
        Integer cantidad,seleccion,in,aux,count,j;
        Random rn = new Random();
        Boolean flag;
        in = individuos.size();
        Double maxAlelosPatron = porcentaje*in/100;
        cantidad = maxAlelosPatron.intValue();
        for (int i = 0; i < in; i++) {
            seleccion = rn.nextInt(cantidad);
            tmp = new ArrayList<Integer>();
            j = 0;
            do{
                aux = rn.nextInt(in);
                if (aux == 0) {
                    aux = 1;
                }
                flag = false;
                do {
                    count = 0;
                    for (int k = 0; k < tmp.size(); k++) {
                        if (tmp.get(k) == aux) {
                            aux = rn.nextInt(in);
                            flag = true;
                        }
                        else
                            count++;
                    }
                    if (count == tmp.size()) {
                        flag = false;
                    }
                } while (flag);
                tmp.add(aux);
                j++;
            }while(j < seleccion);
            Collections.sort(tmp);
            patronMul.add(tmp);
        }
        return patronMul;
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
    
    /**
     * La mutación se hace a un alelo de todos los individuos aleatoriamente
     * @return 
     */
    public Poblacion muta1(Double porcentaje) throws CloneNotSupportedException{
    	Random rn = new Random();
    	ArrayList<Individuo> auxIND = new ArrayList<Individuo>();
        ArrayList<Integer> cambios = individuosAMutar(porcentaje);
    	Integer tmp,al,in,aux,contador,stop;
        Individuo auxIndi;
    	al = individuos.get(0).getAlelos();
        in = individuos.size();
        contador = 0;
        stop = cambios.size();
        if(cambios.isEmpty()){
            for(int x = 0; x < in; x++){
                auxIndi = new Individuo(individuos.get(x));
                auxIND.add(auxIndi);
            }
        }
        else{
            for (int x = 0; x < in;x++) {
                if(cambios.get(contador) == x){
                    tmp = rn.nextInt(al);
                    auxIndi = new Individuo(individuos.get(x));
                    auxIndi.get(tmp).cambiarValor();
                    auxIND.add(auxIndi);
                    contador++;
                    if(contador == stop)
                        contador = 0;
                }
                else{
                    auxIndi = new Individuo(individuos.get(x));
                    auxIND.add(auxIndi);
                }
            }
        }
    	return new Poblacion(auxIND,getPuntoDeCruza());
    }
    
    private ArrayList<Integer> individuosAMutar(Double porcentaje){
        ArrayList<Integer> seleccion = new ArrayList<Integer>();
        Random rn = new Random();
        Integer cantidad,tmp;
        Boolean flag;
        cantidad = 0;
        Double aux = porcentaje*individuos.size()/100;
        cantidad = aux.intValue();
        for(int x = 0;x<cantidad;x++){
            tmp = rn.nextInt(individuos.size());
            flag = false;
            do{
                for(Integer comp:seleccion){
                    if(tmp.equals(comp)){
                        flag = true;
                        tmp = rn.nextInt(individuos.size());
                    }
                    else
                        flag = false;
                }
            }while(flag);
            seleccion.add(tmp);
        }
        Collections.sort(seleccion);
        return seleccion;
    }
    
    public void imprimirPoblacion(){
    	Integer aux = 1;
        for(Individuo in: individuos){
        	System.out.print(aux++ +" : " + in);
        }
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
