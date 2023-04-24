import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ImagenTamanio implements Runnable {
    private final Contenedor contenedor;
    private int imagenesRedimensionadas;
    private final int numerohilo;

    public ImagenTamanio(Contenedor contenedor, int hilo) {
        this.imagenesRedimensionadas = 0;
        this.contenedor = contenedor;
        this.numerohilo = hilo;
    }

    @Override
    public void run() {
        System.out.printf("%s inicializado\n", Thread.currentThread().getName());
        while (contenedor.getRedimensionadas() < contenedor.getImagenes_totales() || contenedor.isCargando()) {
            if (contenedor.getSize() > 0) {
                Imagen imagen = contenedor.getImagenRandom();                          // Obtener un objeto Imagen al azar del contenedor

                if (!imagen.isRedimensionada() && imagen.isIluminadaMejorada()) {
                    imagen.redimensionar();                                     //redimesiona la imagen
                    imagenesRedimensionadas++;
                    contenedor.incrementarRedimensionadas();
                }
                imagen.liberar();
            }
            try {
                Random r = new Random();
                TimeUnit.MILLISECONDS.sleep(25 + r.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("%s: redimenciono %d imagenes\n", Thread.currentThread().getName(), getImagenesRedimensionadas());
    }

    public int getImagenesRedimensionadas() {
        return imagenesRedimensionadas;
    }

}
