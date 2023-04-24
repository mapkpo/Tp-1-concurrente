import java.util.List;
import java.util.ArrayList;
import java.util.Random;

class Contenedor {
    private ArrayList<Imagen> imagenes;
    private int iluminacionMejorada;
    private int redimensionadas;
    private boolean cargando = false;

    private int totalSize = 0;

    public boolean isCargando() {
        return cargando;
    }

    public void setCargando(boolean cargando) {
        this.cargando = cargando;
    }

    //constructor generico
    public Contenedor(){
        this.imagenes =  new ArrayList<>();
        this.iluminacionMejorada = 0;
        this.redimensionadas = 0;
    }


     public synchronized void add(Imagen imagen) {
        //sizeLock.writeLock().lock();
        imagenes.add(imagen);
        totalSize++;
        //sizeLock.writeLock().unlock();
     }

     public Imagen getImagen(int indice) { //deprecado
         if (indice < 0 || indice >= getSize()) {
             throw new ArrayIndexOutOfBoundsException("El índice está fuera del rango de la lista.");
         }
         return imagenes.get(indice);
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

     public synchronized int getTotalSize(){
        return totalSize;
     }

     public void setIluminacionMejorada(int iluminacionMejorada) { //deprecado
         this.iluminacionMejorada = iluminacionMejorada;
     }

     public synchronized void setRedimensionadas() { //deprecado
         redimensionadas++;
     }
 }
