import java.util.Random;
public class ImagenTamanio implements Runnable {
    private Contenedor contenedor;
    private int mejorasHechas;
    private Random random;

    public ImagenTamanio(Contenedor contenedor) {
        mejorasHechas = 0;
        this.contenedor = contenedor;
    }

    @Override
    public void run() {
        while (true) {
            // Obtener un objeto Imagen al azar del contenedor
            Imagen imagen;
            synchronized (contenedor) {
                int indice = random.nextInt(contenedor.getSize());
                imagen = (Imagen) contenedor.getImagen(indice);
                if (imagen.isRedimensionada() == false) {
                    imagen.redimensionar(); //ilumina la imagen
                    mejorasHechas++;
                }
            }
        }
    }
}
