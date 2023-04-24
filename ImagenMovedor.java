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
        System.out.printf("%s inicializado\n", Thread.currentThread().getName());
        /*while(inicial.getSize() > 0){
            Imagen imagen = inicial.removeRandom();
        }*/
        while (inicial.getSize() > 0){

            Imagen imagen = inicial.getImagenRandom();
            //System.out.printf(" " + imagen.isRedimensionada() + "\n");

            if(imagen.isRedimensionada()){
                _final.add(imagen);
                imagenesMovidas++;
                inicial.remove(imagen);

                if(inicial.getSize() > 0)
                {
                    inicial.remove(imagen);
                }
            }

            System.out.println(imagenesMovidas);
            imagen.liberar();
            try{
                TimeUnit.MILLISECONDS.sleep(5);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.printf("%s: Se movieron %d imagenes\n", Thread.currentThread().getName(), imagenesMovidas);

    }
}
