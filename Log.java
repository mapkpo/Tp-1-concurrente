import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Log implements Runnable {
    int contador= 0;
    Contenedor cont_inicial;
    Contenedor cont_final;
    Thread[] threadsCargadores;
    Thread[] threadsIluminadores;
    Thread[] threadsRedimensionadores;
    Thread[] threadsMovedores;


    public Log(Contenedor cont_inicial, Contenedor cont_final, Thread[] threadsCargadores,Thread[] threadsIluminadores,Thread[] threadsRedimensionadores,Thread[] threadsMovedores){
        this.cont_inicial = cont_inicial;
        this.cont_final = cont_final;

        this.threadsCargadores = threadsCargadores;
        this.threadsIluminadores = threadsIluminadores;
        this.threadsRedimensionadores = threadsRedimensionadores;
        this.threadsMovedores = threadsMovedores;

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

    private void crear_archivo(int numero){
        try {
            File archivo = new File("Logs/log"+numero+".txt");
            FileWriter escribir = new FileWriter(archivo, true);
            try {
                escribir.write("Imagenes insertadas:" + cont_inicial.getAgregadas()+"\n");
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
            }
            catch (IOException e){
                System.out.println("Problema al escribir el archivo de LOG.");
            }
            finally {
                escribir.close();
            }
        }
        catch (Exception e){

        }
    }


}
