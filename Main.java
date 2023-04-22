public class Main {
    public static void main(String[] args) {

        Contenedor contenedor = new Contenedor(100);

        ImagenCargador[] imagenCargador = new ImagenCargador[3];
        Thread[] threadsCargadores = new Thread[3];

        for(int i = 0; i < 3; i++){
            imagenCargador[i] = new ImagenCargador(contenedor, 100);
            threadsCargadores[i] = new Thread(imagenCargador[i]);
            threadsCargadores[i].start();

            System.out.printf("Contenedor %d inicializado\n", i);
        }

        System.out.printf("Contenedor cargado con %d elementos\n", contenedor.getSize());
/*
        try{
            threadsCargadores[0].join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }*/

        ImagenIluminador[] iluminadores = new ImagenIluminador[3];
        Thread[] threadIluminadores = new Thread[3];

        for(int i = 0; i<3; i++){
            iluminadores[i] = new ImagenIluminador(contenedor, i);
            threadIluminadores[i] = new Thread(iluminadores[i]);
            threadIluminadores[i].start();
        }

        try{
            threadIluminadores[0].join();
            threadIluminadores[1].join();
            threadIluminadores[2].join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.printf("Se iluminaron completamente %d imagenes\n", contenedor.getIluminacionMejorada());
    }
}