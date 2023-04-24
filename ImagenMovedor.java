import java.util.concurrent.TimeUnit;

public class ImagenMovedor implements Runnable{
    private Contenedor inicial;
    private Contenedor _final;
    private int imagenesMovidas;
    private int amover;

    public ImagenMovedor(Contenedor inicial, Contenedor _final,int cantidad){
        imagenesMovidas = 0;
        this.inicial = inicial;
        this._final = _final;
        amover = cantidad;

    }

    @Override
    public void run() {

        while (_final.getSize() < inicial.getSize()){

            Imagen imagen = inicial.getImagenRandom();
            System.out.printf(" " + imagen.isRedimensionada() + "\n");

            if(imagen.isRedimensionada() == true){
                _final.add(imagen);
                imagenesMovidas++;

                /*if(inicial.getSize() > 0)
                {
                    inicial.remove(imagen);
                }*/
            }
            try{
                TimeUnit.MILLISECONDS.sleep(5);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.printf("%s: Se movieron %d imagenes\n", Thread.currentThread().getName(), imagenesMovidas);

    }
}
