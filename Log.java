import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log implements Runnable {
    int contador= 0;
    Contenedor cont_inicial;
    Contenedor cont_final;
    Thread[] threadsCargadores;
    Thread[] threadsIluminadores;
    Thread[] threadsRedimensionadores;
    Thread[] threadsMovedores;
    File archivo;

    public Log(Contenedor cont_inicial, Contenedor cont_final, Thread[] threadsCargadores,Thread[] threadsIluminadores,Thread[] threadsRedimensionadores,Thread[] threadsMovedores){
        this.cont_inicial = cont_inicial;
        this.cont_final = cont_final;

        this.threadsCargadores = threadsCargadores;
        this.threadsIluminadores = threadsIluminadores;
        this.threadsRedimensionadores = threadsRedimensionadores;
        this.threadsMovedores = threadsMovedores;

        File directorio = new File("./Logs");
        if(!directorio.exists()){
            if (directorio.mkdirs()) {
            }
            else {
                System.out.println("Error al crear directorio");
            }
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String nombreArchivo = "log-" + dateFormat.format(new Date()) + ".txt";
            archivo = new File(directorio, nombreArchivo);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
        }
        catch (IOException e) {
            System.out.println("Problema al crear el archivo de LOG.");
        }
    }

    @Override
    public void run() {
        while (cont_final.getSize()<100){
            try {

                escribir_archivo();
                this.contador++;
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.contador++;
        escribir_archivo();
    }

    private void escribir_archivo(){
        try {
            FileWriter escribir = new FileWriter(archivo, true);
            try {
                escribir.write("Iteración: " + contador + " tiempo: " + contador*500 + "ms\n");
                escribir.write("Imagenes insertadas: " + cont_inicial.getAgregadas()+"\n");
                escribir.write("Imagenes iluminadas: "+ cont_inicial.getIluminacionMejorada()+"\n");
                escribir.write("Imagenes redimensionadas: "+ cont_inicial.getRedimensionadas()+"\n");
                escribir.write("Imagenes finalizadas: "+ cont_final.getSize()+"\n");
                for (Thread thread:threadsCargadores){
                    escribir.write("Hilo: "+thread.getName() +". Estado: "+thread.getState()+"\n");
                }
                for (Thread thread:threadsIluminadores){
                    escribir.write("Hilo: "+thread.getName() +". Estado: "+thread.getState()+"\n");
                }
                for (Thread thread:threadsRedimensionadores){
                    escribir.write("Hilo: "+thread.getName() +". Estado: "+thread.getState()+"\n");
                }
                for (Thread thread:threadsMovedores){
                    escribir.write("Hilo: "+thread.getName() +". Estado: "+thread.getState()+"\n");
                }
                escribir.write("\n\n");
            }
            catch (IOException e){
                System.out.println("Problema al escribir en el archivo de LOG.");
            }
            finally {
                escribir.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}




