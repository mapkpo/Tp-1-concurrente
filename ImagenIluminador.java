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
        while (cantidadIluminadas < contenedor.getSize()) {
            // Obtener un objeto Imagen al azar del contenedor
            Imagen imagen;
            imagen = contenedor.getImagenRandom();
            if(!imagen.isIluminacionMejoradaBy(numerohilo)){
                imagen.iluminar(numerohilo); //ilumina la imagen
                cantidadIluminadas++;
                //System.out.printf("Se ilumino una imagen %b \n", imagen.isIluminacionMejoradaBy(numerohilo));
            }
            try{
                TimeUnit.MILLISECONDS.sleep(50);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            //System.out.printf("Se ilumino una imagen " + imagen.isIluminacionMejoradaBy(numerohilo));
        }
        System.out.println("Se iluminaron " + cantidadIluminadas);
    }

    public int getCantidadIluminadas() {
        return cantidadIluminadas;
    }
}
