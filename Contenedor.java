import java.util.ArrayList; //esta clase anda creo
class Contenedor {
     private ArrayList<Imagen> imagenes;
    private int iluminacionMejorada;
    private int redimencionadas;

    public Contenedor(){ //constructor generico
        imagenes = new ArrayList<>();
        iluminacionMejorada = 0;
        redimencionadas = 0;
    }

    public void add(Imagen imagen) {//agregar una imagen
         imagenes.add(imagen);
     }

     public Imagen getImagen(int indice) {
         if (indice < 0 || indice >= imagenes.size()) {
             throw new ArrayIndexOutOfBoundsException("El índice está fuera del rango de la lista.");
         }
         return imagenes.get(indice);
     }

     public void remove(int i){
        imagenes.remove(i);
     }

     public int getIluminacionMejorada() {
         return iluminacionMejorada;
     }

     public int getRedimencionadas() {
         return redimencionadas;
     }

     public int getSize() {
         return imagenes.size();
     }

     public void setIluminacionMejorada(int iluminacionMejorada) {
         this.iluminacionMejorada = iluminacionMejorada;
     }

     public void setRedimencionadas(int redimencionadas) {
         this.redimencionadas = redimencionadas;
     }
 }
