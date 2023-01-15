
package main;

public class Main {

    public static void main(String[] args) { 
        Vista vista = new Vista();
        PilaArboles modelo = new PilaArboles();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
