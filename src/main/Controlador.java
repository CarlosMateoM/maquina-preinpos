package main;

import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Controlador {

    private final PilaArboles modelo;
    private final Vista vista;
    private final String regex = "^[0-9]{2}([+|\\-|\\^|\\/|*]{1,2}[0-9]{0,2})*([+|\\-|*|\\^|\\/]){1,2}$";


    public Controlador(Vista vista, PilaArboles modelo) {
        this.vista = vista;
        this.modelo = modelo;
        iniciarControlador();
        iniciarVista();
    }

    private void iniciarControlador() {
        vista.getCargarExpresionBtn().addActionListener(e -> cargarExpresion());
        vista.getApagarBtn().addActionListener(e -> System.exit(0));
    }

    private void iniciarVista() {
        vista.setVisible(true);
    }

    private void cargarExpresion() {
        
        String expresion = vista.getExpresionTxt().getText();

        if (!expresion.isEmpty()) {

            if (Pattern.matches(regex, expresion)) {

                modelo.guardarExpresion(expresion);

                parsearExpresion();

                modelo.imprimirArbol(vista.getjTextAreaGrafo());

                evaluarExpresion(expresion);
            } else {
                JOptionPane.showMessageDialog(null, "¡No es una expresion valida!");
            }

        } else {
            JOptionPane.showMessageDialog(null, "¡Ingrese la expresion a evaluar!");
        }

    }

    private void parsearExpresion() {

        String expresionInfija = modelo.parsearInfija();
        vista.getInfijaTxt().setText(expresionInfija);

        String expresionPrefija = modelo.parsearPrefija();
        vista.getPrefijaTxt().setText(expresionPrefija);
    }

    private void evaluarExpresion(String expresion) {

        PilaNodos pilaNodos = new PilaNodos();

        String resultado = pilaNodos.evaluarExpresion(expresion);
        vista.getResultadoTxt().setText(resultado);
    }

}
