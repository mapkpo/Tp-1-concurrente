import java.util.concurrent.TimeUnit;

public class ImagenCargador implements Runnable{

    private Contenedor contenedor;
    private int toAdd;

    public ImagenCargador(Contenedor contenedor, int toAdd) {
        this.contenedor = contenedor;
        this.toAdd = toAdd;
    }


    @Override
    public void run() {
        System.out.printf("%s inicializado\n", Thread.currentThread().getName());
        contenedor.setCargando(true);
        int contador = 0;

        while (contador < toAdd){
            /*Primero chequeo que no supere la cantidad a agregar, LUEGO intento agregar*/

            contenedor.add(new Imagen());
            contador++;

            try{
                TimeUnit.MILLISECONDS.sleep(5);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.printf("%s: Cargo %d imagenes\n", Thread.currentThread().getName(), contador);
        contenedor.setCargando(false);
    }
}
