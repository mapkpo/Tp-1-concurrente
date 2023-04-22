public class ImagenMovedor implements Runnable{
    private Contenedor inicial;
    private Contenedor _final;
    private int imagenesMovidas;

    public ImagenMovedor(Contenedor inicial, Contenedor _final){
        imagenesMovidas = 0;
        this.inicial = inicial;
        this._final = _final;
    }

    @Override
    public void run() {
        while (true){
            for (int i = 0; i < inicial.getSize(); i++){
                _final.add(inicial.getImagen(i));
                //inicial.remove(i);
                imagenesMovidas++;
            }
        }

    }
}
