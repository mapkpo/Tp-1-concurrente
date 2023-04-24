import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ImagenTamanio implements Runnable {
    private Contenedor contenedor;
    private int mejorasHechas=0;
    private Random random;
    private int numerohilo;

    public ImagenTamanio(Contenedor contenedor, int hilo) {
        this.mejorasHechas = 0;
        this.contenedor = contenedor;
        this.numerohilo = hilo;
    }

    @Override
    public void run() {
        System.out.printf("%s inicializado\n", Thread.currentThread().getName());
        while (contenedor.getRedimensionadas() < contenedor.getSize() || contenedor.isCargando()) {
            Imagen imagen = contenedor.getImagenRandom();                          // Obtener un objeto Imagen al azar del contenedor

            if (!imagen.isRedimensionada() && imagen.isIluminadaMejorada()) {
                imagen.redimensionar();                                     //redimesiona la imagen
                mejorasHechas++;
                //System.out.printf("Se redimensiono una imagen");
            }
            imagen.liberar();
            try{
                TimeUnit.MILLISECONDS.sleep(5);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        //System.out.printf("se redimensionaron " + mejorasHechas + "\n");
        System.out.printf("%s: redimenciono %d imagenes\n", Thread.currentThread().getName(), mejorasHechas);

    }

    public int imagenes_redimensionadas(){
        return mejorasHechas;
    }
}

