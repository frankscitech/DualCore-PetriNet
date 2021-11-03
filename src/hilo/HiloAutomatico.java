package hilo;

import main.Monitor;
import main.Transicion;

import java.io.IOException;

public class HiloAutomatico implements Runnable {

    private Transicion[] secuenciaDeDisparos;
    private Monitor monitor;

    public HiloAutomatico( Transicion[] secuenciaDeDisparos, Monitor monitor) {
        this.secuenciaDeDisparos = secuenciaDeDisparos;
        this.monitor = monitor;
    }

    public void run() {
        boolean keepGoing=true;
        while (keepGoing) {
            for(Transicion transicion: secuenciaDeDisparos) {
                try {
                    keepGoing=monitor.dispararTransicion(transicion);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("["+ Thread.currentThread().getName()+ "          Terminado ]" );
        
    }
}