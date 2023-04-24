import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ImagenCargador implements Runnable{

    private Contenedor contenedor;

    public ImagenCargador(Contenedor contenedor) {
        this.contenedor = contenedor;
    }


    @Override
    public void run() {
        System.out.printf("%s inicializado\n", Thread.currentThread().getName());
        contenedor.setCargando(true);
        int contador = 0;

        while (contenedor.getImagenesCargadas() < contenedor.getImagenes_totales()){
            /*Primero chequeo que no supere la cantidad a agregar, LUEGO intento agregar*/

            contenedor.add(new Imagen());
            contador++;
            contenedor.incrementarImagenesCargadas();

            try{
                Random r = new Random();
                TimeUnit.MILLISECONDS.sleep(10 +r.nextInt(15));
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.printf("%s: Cargo %d imagenes\n", Thread.currentThread().getName(), contador);
        contenedor.setCargando(false);
    }
}
