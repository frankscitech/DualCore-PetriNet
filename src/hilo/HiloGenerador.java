package hilo;

import main.Monitor;
import main.Transicion;

import java.io.IOException;

public class HiloGenerador implements Runnable {

    private Transicion transicion;
    private Monitor monitor;
    private int tareasTotales;

    public HiloGenerador(Transicion transicion, Monitor monitor) {
        this.transicion = transicion;
        this.monitor = monitor;
        this.tareasTotales=monitor.tareas;

    }

    public void run() {
        int tareas = 0;
        while (tareas < tareasTotales) {
            try {
                if (monitor.dispararTransicion(transicion)) {
                    tareas++;
                    System.out.println("#################################################################################################################" + tareas);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("["+ Thread.currentThread().getName()+ "          Terminado ]" );

    }
}
