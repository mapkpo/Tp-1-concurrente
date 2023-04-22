public class Imagen {
    private boolean[] iluminada;
    private boolean tamanioajustado;

    public Imagen(){ //constructor de la clase imagen
        iluminada = new boolean[]{false,false,false};
        tamanioajustado = false;
    }

    public void iluminar(int indice) { //funcion que nos deja "iluminar" la imagen y poner que fue iluminada por uno de los threads
        if (indice >= 0 && indice < iluminada.length) {
            iluminada[indice] = true;
        } else {
            System.out.println("El índice está fuera de rango");
        }
    }

    public boolean isIluminacionMejoradaBy(int indice) { //chequea con el int si fue iluminado por el thread entre 0 y 2
        if (indice >= 0 && indice < iluminada.length) {
            return iluminada[indice];
        } else {
            System.out.println("El índice está fuera de rango");
            return false; ///NOTA, quizas deberia retornar true
        }
    }

    public boolean isIluminadaMejorada() { //chequea si toda la imagen fue mejorada y que el array sea "all true"
        for (boolean b : iluminada) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public void redimensionar(){
        setTamanioajustado(true);
    }

    private void setTamanioajustado(boolean tamanioajustado) {
        this.tamanioajustado = tamanioajustado;
    }

    public boolean isRedimensionada() {
        return tamanioajustado;
    }
}
