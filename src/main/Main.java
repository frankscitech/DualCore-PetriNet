package main;

import hilo.HiloAutomatico;
import hilo.HiloGenerador;

import java.io.IOException;



public class Main {
    /* Useful to LogFileManager */
    public static long startTime = System.nanoTime();
    public static void main(String[] args) throws IOException {

        int cantTarea=1000;

        LogFileManager log = new LogFileManager();
        Monitor monitor = new Monitor(log,cantTarea);
        Transicion[] secuencia1 = {Transicion.TAREA_A_BUFFER_1};
        Transicion[] secuencia2 = {Transicion.TAREA_A_BUFFER_2};
        Transicion[] secuencia3 = {Transicion.PROCESANDO_EN_NUCLEO_1, Transicion.T4};
        Transicion[] secuencia4 = {Transicion.PROCESANDO_EN_NUCLEO_2, Transicion.T10};
        Transicion[] secuencia5 = {Transicion.ENCENDER_CPU_1, Transicion.T7, Transicion.APAGAR_CPU_1};
        Transicion[] secuencia6 = {Transicion.T6};
        Transicion[] secuencia7 = {Transicion.ENCENDER_CPU_2, Transicion.T12,Transicion.APAGAR_CPU_2};
        Transicion[] secuencia8 = {Transicion.T13};
        Thread generadorDeTareas = new Thread(new HiloGenerador(Transicion.GENERAR_TAREA, monitor));
        Thread aBuffer1 = new Thread(new HiloAutomatico(secuencia1,monitor));
        Thread aBuffer2 = new Thread(new HiloAutomatico(secuencia2,monitor));
        Thread nucleoUno = new Thread(new HiloAutomatico(secuencia3, monitor));
        Thread nucleoDos = new Thread(new HiloAutomatico(secuencia4, monitor));
        Thread CPU1 = new Thread(new HiloAutomatico(secuencia5, monitor));
        Thread CPU2 = new Thread(new HiloAutomatico(secuencia7, monitor));
        Thread disparaT6 = new Thread(new HiloAutomatico(secuencia6, monitor)); 
        Thread disparaT13 = new Thread(new HiloAutomatico(secuencia8, monitor));

        generadorDeTareas.start();
        aBuffer1.start();
        aBuffer2.start();
        nucleoUno.start();
        nucleoDos.start();
        CPU1.start();
        CPU2.start();
        disparaT6.start();
        disparaT13.start();


        try{
        generadorDeTareas.join();
        aBuffer1.join();
        aBuffer2.join();
        nucleoUno.join();
        nucleoDos.join();
        CPU1.join();
        CPU2.join();
        disparaT6.join();
        disparaT13.join();
        }catch(Exception ex){
            System.out.println("Exception has been" +
            " caught" + ex);

        }
        
    }
}





