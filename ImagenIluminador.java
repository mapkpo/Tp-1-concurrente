import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ImagenIluminador implements Runnable {
    private Contenedor contenedor;
    private int numerohilo;
    private int cantidadIluminadas;

    public ImagenIluminador(Contenedor contenedor, int hilo) {
        this.contenedor = contenedor;
        numerohilo = hilo;
        cantidadIluminadas = 0;
    }

    @Override
    public void run() {

        System.out.printf("%s inicializado\n", Thread.currentThread().getName());
        while (cantidadIluminadas < contenedor.getTotalSize() || contenedor.isCargando()) {

            // Obtener un objeto Imagen al azar del contenedor
            if(contenedor.getSize() > 0) {
                Imagen imagen;
                imagen = contenedor.getImagenRandom();
                if (!imagen.isIluminacionMejoradaBy(numerohilo)) {
                    imagen.iluminar(numerohilo); //ilumina la imagen
                    cantidadIluminadas++;
                    //System.out.printf("Se ilumino una imagen %b \n", imagen.isIluminacionMejoradaBy(numerohilo));
                }
                imagen.liberar();
            }
                try {
                    Random r = new Random();
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            //System.out.printf("Se ilumino una imagen " + imagen.isIluminacionMejoradaBy(numerohilo));
        }
        //System.out.println("Se iluminaron: " + cantidadIluminadas + " imagenes, por el hilo: " + numerohilo);
        System.out.printf("%s: Ilumino %d imagenes\n", Thread.currentThread().getName(), cantidadIluminadas);
    }

    public int getCantidadIluminadas() {
        return cantidadIluminadas;
    }
}
