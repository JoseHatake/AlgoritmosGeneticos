/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.util.ArrayList;

/**
 *
 * @author Hatake
 */
public class Graficar {
    private XYSeries series;
    private XYSeriesCollection dataset;
    private JFreeChart chart;
    private ChartFrame frame;
    private String nombreFrame;
    private String titulo;
    private String etiquetaX;
    private String etiquetaY;
    /**
     * @param args the command line arguments
     */
    public Graficar() {
        dataset = new XYSeriesCollection();
    }

    public void setNombreFrame(String nFrame){
        nombreFrame = nFrame;
    }

    public void setTitutlo(String titulo){
        this.titulo = titulo;
    }

    public void setEtiquetaX(String etiX){
        etiquetaX = etiX;
    }

    public void setEtiquetaY(String etiY){
        etiquetaY = etiY;
    }

    private void limpiarColecciones(){
        series.clear();
        dataset.removeAllSeries();
    }

    private void generarSerie(ArrayList<Double> datos,String nombre){
        series = new XYSeries(nombre);
        int size = datos.size();
        for (int x = 1;x<=size;x++) {
            series.add(x,datos.get(x-1));
        }
        dataset.addSeries(series);
    }

    private void construirGrafica(){
        chart = ChartFactory.createXYLineChart(
                titulo, // Título
                etiquetaX, // Etiqueta Coordenada X
                etiquetaY, // Etiqueta Coordenada Y
                dataset, // Datos
                PlotOrientation.VERTICAL,
                true, // Muestra la leyenda de los productos (Producto A)
                false,
                false
        );
    }

    public void dibujaGrafica(ArrayList<Double> datos,String nombre){
        generarSerie(datos,nombre);
        construirGrafica();
        frame = new ChartFrame(nombreFrame, chart);
        frame.pack();
        frame.setVisible(true);
    }
    
}

