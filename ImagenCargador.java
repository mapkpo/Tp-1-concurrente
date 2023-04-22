import java.util.concurrent.TimeUnit;

public class ImagenCargador implements Runnable{

    private Contenedor contenedor;
    private int toAdd;

    public ImagenCargador(Contenedor contenedor, int toAdd) {
        this.contenedor = contenedor;
        this.toAdd = toAdd;
    }

    /**
     * carga imagenes en el contenedor siempre que este tenga menos imagenes que las deseadas (toAdd)
        *  o hasta que el contenedor este lleno
     */
    @Override
    public void run() {
        int contador = 0;

        while (true){
            /*Primero chequeo que no supere la cantidad a agregar, LUEGO intento agregar*/
            if(contenedor.getSize() >= toAdd)
                break;

            contenedor.add(new Imagen());

            contador++;

            try{
                TimeUnit.MILLISECONDS.sleep(50);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.printf("%s: Cargo %d imagenes\n", Thread.currentThread().getName(), contador);
    }
}
