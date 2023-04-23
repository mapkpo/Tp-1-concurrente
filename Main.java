public class Main {
    public static void main(String[] args) {

        //Va a contener las imagenes desde la creacion y mientras se esten procesando
        Contenedor contenedor_inicial = new Contenedor();
        Contenedor contenedor_final = new Contenedor();

        //Creo los arreglos para almacenar los objetos necesarios
        ImagenCargador[] imagenCargador = new ImagenCargador[3];
        Thread[] threadsCargadores = new Thread[3];

        ImagenIluminador[] iluminadores = new ImagenIluminador[3];
        Thread[] threadIluminadores = new Thread[3];

        ImagenTamanio[] redimensionadores = new ImagenTamanio[3];
        Thread[] threadRedimensionadores = new Thread[3];

        //Inicializo los objetos necesarios
        for(int i = 0; i < 3; i++){
            imagenCargador[i] = new ImagenCargador(contenedor_inicial, 100);
            iluminadores[i] = new ImagenIluminador(contenedor_inicial, i);
            redimensionadores[i] = new ImagenTamanio(contenedor_inicial, i);
        }

        Log log = new Log(contenedor_inicial, contenedor_final,iluminadores,redimensionadores );

        //Arranco los distintos hilos segun corresponda
        new Thread(log).start();

        for(int i = 0; i < 3; i++){
            threadsCargadores[i] = new Thread(imagenCargador[i]);
            threadsCargadores[i].start();

            System.out.printf("Contenedor %d inicializado\n", i);
        }

        // System.out.printf("Contenedor cargado con %d elementos\n", contenedor.getSize());
/*
        try{
            threadsCargadores[0].join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }*/


        //Se iluminan las imagenes
        for(int i = 0; i<3; i++){
            threadIluminadores[i] = new Thread(iluminadores[i]);
            threadIluminadores[i].setName("ILUMINADOR " + i);
            threadIluminadores[i].start();
        }


/*
        //Espera que se iluminen todas las imagenes por cada uno de los hilos
        try{
            threadIluminadores[0].join();
            threadIluminadores[1].join();
            threadIluminadores[2].join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }*/


        System.out.printf("Se iluminaron completamente %d imagenes\n", contenedor_inicial.getIluminacionMejorada());

/*
        ImagenTamanio xd = new ImagenTamanio(contenedor,0);
        Thread xd1 = new Thread(xd);
        xd1.start();
*/


        //System.out.printf("Se iluminaron completamente %d imagenes\n", contenedor.getIluminacionMejorada());




        for(int i = 0; i<3; i++){
            threadRedimensionadores[i] = new Thread(redimensionadores[i]);
            threadRedimensionadores[i].setName("REDIMENCIONADOR " + i);
            threadRedimensionadores[i].start();
        }



        try{

            threadRedimensionadores[0].join();
            threadRedimensionadores[1].join();
            threadRedimensionadores[2].join();

        } catch (InterruptedException e){
            e.printStackTrace();
        } 
    }
}