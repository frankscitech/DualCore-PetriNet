package main;/*
 * CREAS CONDITIONS CON TIEMPO PARA ARRIVAL Y SERVICE RATE
 *
 * */

import java.util.concurrent.Semaphore;
import java.util.Arrays;

public class Colas {

    private Semaphore[] arregloSemaphores;
    private int numeroTransiciones;
    private final int  finalStatus[]={0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0};

    public Colas(int numeroTransiciones) {
        this.numeroTransiciones = numeroTransiciones;
        arregloSemaphores = new Semaphore[numeroTransiciones];

        for (int i = 0; i < numeroTransiciones; i++) {
            arregloSemaphores[i] = new Semaphore(0);
        }
    }

    public void signal(int i) {        
            arregloSemaphores[i].release();
    }

    public void releaseAll() {
        for (int i = 0; i < arregloSemaphores.length; i++) {
            if (arregloSemaphores[i].hasQueuedThreads()) {
                arregloSemaphores[i].release();
            }
        }
    }


    public void await(Transicion transicion) throws InterruptedException {
        arregloSemaphores[transicion.getValor()].acquire();
    }

    public  int[] quienesEstan() {
        int[] vectorCola = new int[numeroTransiciones];
        for (int i = 0; i < arregloSemaphores.length; i++) {
            if (arregloSemaphores[i].hasQueuedThreads()) {
                vectorCola[i] = 1;
            }
        }
        return vectorCola;
    }

    public void printStatus(){
        int[] vectorCola = quienesEstan();
        String vectorColaString;
        int count=0;
        for (int i = 0; i < arregloSemaphores.length; i++) {
            if(vectorCola[i]==1)
                count++;
        }
        vectorCola[0]=vectorCola[4]=vectorCola[10]=-1;
        vectorColaString=Arrays.toString(vectorCola);
        System.out.println("Encolados=" + vectorColaString + "No = "+ count);
    }

    public boolean checkFinalStatus(Transicion transicion){
        int[] vectorCola = quienesEstan();
        vectorCola[transicion.getValor()]=1;
        if(Arrays.equals(vectorCola,finalStatus)){
            return true;
        }else{
            return false;
        }
    }
    

}