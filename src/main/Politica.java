package main;

import java.util.concurrent.ThreadLocalRandom;

public class Politica {

    //a mayor val mayor prioridad
    private int[] politica;
    private Buffer buffer1;
    private Buffer buffer2;

    Politica(Buffer buffer1, Buffer buffer2, int numeroTransiciones) {
        this.buffer1 = buffer1;
        this.buffer2 = buffer2;
        politica = new int[numeroTransiciones];

        for (int i = 0; i < politica.length; i++) {
            politica[i] = ThreadLocalRandom.current().nextInt(0, 200);
        }
    }

    public int cual(int[] vectorSens, int[] vectorCola) {

        int vectorAND[] = new int[vectorSens.length];
        int max = 0;
        int transicion = 0;

        /**
         * Creo un vector AND con aquella transiciones que  están sencibilizada, encoladas y no son temporizadas.
         */
        for (Transicion t :
                Transicion.values()) {
            if (vectorSens[t.getValor()] == 1 && vectorCola[t.getValor()] == 1 && !t.esTemporizada()) {
                vectorAND[t.getValor()] = 1;
            } else {
                vectorAND[t.getValor()] = 0;
            }
        }

        /**
         * Si la transicon TAREA_A_BUFFER_1/2 está sencibilizada y encolada.
         * Despierta aquella correspondiente al procesador cuyo buffer está menos cargado.
         */
        if (vectorAND[Transicion.TAREA_A_BUFFER_1.getValor()] == 1) {
            if (buffer1.getEstado() == buffer2.getEstado()){
                return (int)(Math.random()*2+1);
            }
            if (buffer1.getEstado() > buffer2.getEstado()) {
                return Transicion.TAREA_A_BUFFER_2.getValor();
            } else {
                return Transicion.TAREA_A_BUFFER_1.getValor();
            }
        }

        /**
         * Si la transicon TAREA_A_BUFFER_1/2  NO está sencibilizada y encolada.
         * Despierta una trasicion aleatoria con mayor valor de politica. 
         */
        for (int i = 0; i < vectorSens.length; i++) {
            if (vectorAND[i] == 1 && politica[i] > max) {
                max = politica[i];
                transicion = i;
            }
        }
        return transicion;
    }

}
