import java.util.List;
import java.util.ArrayList;
import java.util.Random;

class Contenedor {
    private ArrayList<Imagen> imagenes;
    private int iluminacionMejorada;
    private int redimensionadas;
    public boolean cargando = false;


    //constructor generico
    public Contenedor(){
        this.imagenes =  new ArrayList<>();
        this.iluminacionMejorada = 0;
        this.redimensionadas = 0;
    }


     public void add(Imagen imagen) {
        //sizeLock.writeLock().lock();
        imagenes.add(imagen);
        //sizeLock.writeLock().unlock();

     }

     public Imagen getImagen(int indice) {
         if (indice < 0 || indice >= getSize()) {
             throw new ArrayIndexOutOfBoundsException("El índice está fuera del rango de la lista.");
         }
         return imagenes.get(indice);
     }

     public synchronized Imagen getImagenRandom(){
         Random random = new Random();
         return imagenes.get(random.nextInt(getSize()));
     }

     public synchronized int getIluminacionMejorada() {
         int contador = 0;
         for (Imagen i: imagenes) {
             if(i.isIluminadaMejorada()){
                 contador++;
             }
         }
         iluminacionMejorada = contador;
         return iluminacionMejorada;
     }

     public synchronized int getRedimensionadas() {
         int contador1 = 0;
         for (Imagen i: imagenes) {
             if(i.isRedimensionada()){
                 contador1++;
             }
         }
         redimensionadas = contador1;
         return redimensionadas;
     }

     public synchronized int getSize() {

         //sizeLock.readLock().lock();
         int value = imagenes.size();
         //sizeLock.readLock().unlock();
         return value;

     }

     public void setIluminacionMejorada(int iluminacionMejorada) { //deprecado
         this.iluminacionMejorada = iluminacionMejorada;
     }

     public void setRedimensionadas() { //deprecado
         redimensionadas++;
     }
 }
