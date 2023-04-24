import java.util.List;
import java.util.ArrayList;
import java.util.Random;

class Contenedor {
    private ArrayList<Imagen> imagenes;
    private int iluminacionMejorada;
    private int redimensionadas;
    private int agregadas;
    private int imagenesCargadas;
    private int imagenes_totales;
    private boolean cargando = false;


    //constructor generico
    public Contenedor( int imagenes_totales){
        this.imagenes_totales = imagenes_totales;
        this.imagenes =  new ArrayList<>();
        this.iluminacionMejorada = 0;
        this.redimensionadas = 0;
        this.agregadas = 0;
    }


     public synchronized void add(Imagen imagen) {
        imagenes.add(imagen);
        setAgregadas();
     }

     public synchronized Imagen getImagenRandom(){
         Random random = new Random();
         Imagen aux;
         while (true) {
             aux = imagenes.get(random.nextInt(getSize()));
             if (aux.useImagen()) break;
         }
         return aux;
     }

     public synchronized void remove(Imagen imagen){
        imagenes.remove(imagen);
     }
    public synchronized Imagen removeRandom(){

        Random random = new Random();
        Imagen aux = imagenes.get(random.nextInt(getSize()));
        imagenes.remove(aux);
        return aux;
    }

     public synchronized int getIluminacionMejorada() {
         return iluminacionMejorada;
     }

     public synchronized int getRedimensionadas() {
         return redimensionadas;
     }

     public synchronized int getSize() {
         int value = imagenes.size();
         return value;
     }

     public synchronized void incrementIluminacionMejorada() {
         iluminacionMejorada ++;
     }

    public synchronized int getAgregadas() {
        return agregadas;
    }

    public synchronized void setAgregadas() {
        this.agregadas ++;
    }
    public synchronized boolean isCargando() {
        return cargando;
    }

    public synchronized void setCargando(boolean cargando) {
        this.cargando = cargando;
    }

    public synchronized int getImagenes_totales() {
        return imagenes_totales;
    }
    public synchronized int getImagenesCargadas() {
        return imagenesCargadas;
    }

    public synchronized void incrementarImagenesCargadas() {
        imagenesCargadas ++;
    }
}
