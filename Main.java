public class Main {

    public static void main(String[] args) {
        final int cantidadImagenes = 100;


        //Va a contener las imagenes desde la creacion y mientras se estén procesando
        Contenedor contenedor_inicial = new Contenedor(cantidadImagenes);
        Contenedor contenedor_final = new Contenedor(cantidadImagenes);

        //Creo los arreglos para almacenar los objetos necesarios

        ImagenCargador[] cargadores = new ImagenCargador[2];
        Thread[] threadsCargadores = new Thread[2];

        ImagenIluminador[] iluminadores = new ImagenIluminador[3];
        Thread[] threadIluminadores = new Thread[3];

        ImagenTamanio[] redimensionadores = new ImagenTamanio[3];
        Thread[] threadRedimensionadores = new Thread[3];

        ImagenMovedor[] movedores = new ImagenMovedor[2];
        Thread[] threadMovedores = new Thread[2];


//----------------------------------------------------------------------------------------------------------------------


        //Inicializo los objetos necesarios

        for(int i = 0; i < 2; i++){
            cargadores[i] = new ImagenCargador(contenedor_inicial);
            threadsCargadores[i] = new Thread(cargadores[i]);
            threadsCargadores[i].setName("CARGADOR " + i);

            movedores[i] = new ImagenMovedor(contenedor_inicial,contenedor_final);
            threadMovedores[i] = new Thread(movedores[i]);
            threadMovedores[i].setName("MOVEDOR " + i);

        }

        for(int i = 0; i < 3; i++){
            iluminadores[i] = new ImagenIluminador(contenedor_inicial, i);
            threadIluminadores[i] = new Thread(iluminadores[i]);
            threadIluminadores[i].setName("ILUMINADOR " + i);

            redimensionadores[i] = new ImagenTamanio(contenedor_inicial, i);
            threadRedimensionadores[i] = new Thread(redimensionadores[i]);
            threadRedimensionadores[i].setName("REDIMENCIONADOR " + i);
        }



//----------------------------------------------------------------------------------------------------------------------


        //Arranco los distintos hilos segun corresponda
        Log log = new Log(contenedor_inicial, contenedor_final,threadsCargadores,threadIluminadores,threadRedimensionadores,threadMovedores );
        new Thread(log).start();

        for(int i = 0; i < 2; i++){
            threadsCargadores[i].start();
            System.out.printf("Contenedor %d inicializado\n", i);
        }

        for(int i = 0; i<3; i++){
            threadIluminadores[i].start();
            threadRedimensionadores[i].start();
        }

        for(int i = 0; i < 2; i++){
            threadMovedores[i].start();
        }


    }
}