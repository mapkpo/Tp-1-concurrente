import java.util.List;
import java.util.ArrayList;
import java.util.Random;

class Contenedor {
     private ArrayList<Imagen> imagenes;
    private int iluminacionMejorada;
    private int redimencionadas;

    public Contenedor(int capacidad){ //constructor generico
        imagenes =  new ArrayList<>();;
        iluminacionMejorada = 0;
        redimencionadas = 0;
    }

    /**
     * agrega una imagen al arreglo imagen
     * @return true si la imagen se pudo agregar, false si el contenedor ya está lleno
     */
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



     public int getIluminacionMejorada() {
         int contador = 0;
         for (Imagen i: imagenes) {
             if(i.isIluminadaMejorada()){
                 contador++;
             }
         }
         iluminacionMejorada = contador;
         return iluminacionMejorada;
     }

     public int getRedimencionadas() {
         return redimencionadas;
     }

     public synchronized int getSize() {
         return imagenes.size();
     }

     public void setIluminacionMejorada(int iluminacionMejorada) {
         this.iluminacionMejorada = iluminacionMejorada;
     }

     public void setRedimencionadas(int redimencionadas) {
         this.redimencionadas = redimencionadas;
     }
 }
