import java.util.ArrayList;
import java.util.Random;

class Contenedor {
    private final ArrayList<Imagen> imagenes;
    private int iluminacionMejorada;
    private int redimensionadas;
    private int agregadas;
    private final int imagenes_totales;
    private boolean cargando = false;

    private final Object AgregadasLock, RedimencionadasLock, IluminadasLock, ListLock;

    //constructor generico
    public Contenedor( int imagenes_totales){
        this.imagenes_totales = imagenes_totales;
        this.imagenes =  new ArrayList<>();
        this.iluminacionMejorada = 0;
        this.redimensionadas = 0;
        this.agregadas = 0;
        AgregadasLock = new Object();
        RedimencionadasLock = new Object();
        IluminadasLock = new Object();
        ListLock = new Object();
    }


     public void add(Imagen imagen) {
        synchronized (ListLock) {
            imagenes.add(imagen);
        }
        setAgregadas();
     }

     public Imagen getImagenRandom(){
         Random random = new Random();
         Imagen aux;
         synchronized (ListLock) {
             while (true) {
                 aux = imagenes.get(random.nextInt(getSize()));
                 if (aux.useImagen()) break;
             }
         }
         return aux;
     }

     public synchronized void remove(Imagen imagen){
        synchronized (ListLock) {
            imagenes.remove(imagen);
        }
     }

     public int getIluminacionMejorada() {
         synchronized (IluminadasLock) {
             return iluminacionMejorada;
         }
     }

     public int getRedimensionadas() {
         synchronized (RedimencionadasLock) {
             return redimensionadas;
         }
     }

    public void incrementarRedimensionadas() {
        synchronized (RedimencionadasLock) {
            redimensionadas++;
        }
    }

     public int getSize() {
        synchronized (ListLock) {
            return imagenes.size();
        }
     }

     public void incrementIluminacionMejorada() {
        synchronized (IluminadasLock) {
            iluminacionMejorada++;
        }
     }

    public int getAgregadas() {
        synchronized (AgregadasLock) {
            return agregadas;
        }
    }

    public void setAgregadas() {
        synchronized (AgregadasLock) {
            this.agregadas++;
        }
    }
    public synchronized boolean isCargando() {
        return cargando;
    }

    public synchronized void setCargando(boolean cargando) {
        this.cargando = cargando;
    }

    public int getImagenes_totales() {
        return imagenes_totales;
    }
}
