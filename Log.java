import java.io.File;
import java.io.FileWriter;

public class Log implements Runnable {
    int contador= 0;
    Contenedor cont_inicial;
    Contenedor cont_final;
    ImagenIluminador[] iluminadores;
    ImagenTamanio[] redimensionadores;
    public Log(Contenedor cont_inicial, Contenedor cont_final, ImagenIluminador[] iluminadores,ImagenTamanio[] redimensionadores){
        this.cont_inicial = cont_inicial;
        this.cont_final = cont_final;
        this.iluminadores = iluminadores;
        this.redimensionadores = redimensionadores;
        File directorio = new File("./Logs");
        if(directorio.exists()){
            for (File file : directorio.listFiles()){
                file.delete();
            }
        }
        else {
            if (directorio.mkdirs()) {
            }
            else {
                System.out.println("Error al crear directorio");
            }
        }



    }

    @Override
    public void run() {
        while (cont_final.getSize()<100){
            try {
                this.contador++;
                crear_archivo(contador);
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.contador++;
        crear_archivo(contador);
    }

    private int imagenes_iluminadas(){
        int imagenes_iluminadas_totales = 0;
        for (ImagenIluminador il:iluminadores){
            imagenes_iluminadas_totales =+ il.getCantidadIluminadas();
        }
        return imagenes_iluminadas_totales;
    }

    private void crear_archivo(int numero){
        try {
            File archivo = new File("Logs/log"+numero+".txt");
            FileWriter escribir = new FileWriter(archivo, true);
            try {
                escribir.write("Imagenes insertadas:" + cont_inicial.getAgregadas()+"\n");
                escribir.write("Imagenes iluminadas: "+ cont_inicial.getIluminacionMejorada()+"\n");
                escribir.write("Imagenes redimensionadas: "+ cont_inicial.getRedimensionadas()+"\n");
                escribir.write("Imagenes finalizadas: "+ cont_final.getSize()+"\n");
            }
            catch (Exception e){

            }
            finally {
                escribir.close();
            }

        }
        catch (Exception e){

        }
    }

}
