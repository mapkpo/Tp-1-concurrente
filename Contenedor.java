import java.util.List;
import java.util.ArrayList;
import java.util.Random;

class Contenedor {
     private ArrayList<Imagen> imagenes;
    private int iluminacionMejorada;
    private int redimensionadas;

    public Contenedor(){ //constructor generico
        imagenes =  new ArrayList<>();;
        iluminacionMejorada = 0;
        redimensionadas = 0;
    }

     public synchronized void add(Imagen imagen) {
             imagenes.add(imagen);
             //return true;
         //return false;
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
         return imagenes.size();
     }

     public void setIluminacionMejorada(int iluminacionMejorada) { //deprecado
         this.iluminacionMejorada = iluminacionMejorada;
     }

     public void setRedimensionadas() { //deprecado
         redimensionadas++;
     }
 }
