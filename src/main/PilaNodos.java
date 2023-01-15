package main;

import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class PilaNodos {

    private NodoArbol inicio;
    private NodoArbol fin;

    public PilaNodos() {
        inicio = null;
        fin = null;
    }

    public NodoArbol getInicio() {
        return inicio;
    }

    public void setInicio(NodoArbol inicio) {
        this.inicio = inicio;
    }

    public NodoArbol getFin() {
        return fin;
    }

    public void setFin(NodoArbol fin) {
        this.fin = fin;
    }

    private int operar(int a, int b, String op) {
        int resultado = -1;
        switch (op) {
            case "+":
                resultado = a + b;
                break;
            case "-":
                resultado = a - b;
                break;
            case "*":
                resultado = a * b;
                break;
            case "/":
                resultado = a / b;
                break;
            case "^":
                resultado = (int)Math.pow(a, b);
                break;
        }
        return resultado;
    }

    public String evaluarExpresion(String expresion) {

        setInicio(null);
        for (int i = 0; i < expresion.length(); i++) {
            String elemento = String.valueOf(expresion.charAt(i));
            NodoArbol n = new NodoArbol(elemento, null, null);
            agregarNodo(n);

        }

        return getFin().getClave();
    }

    public NodoArbol quitarNodo() {
        NodoArbol tem = getInicio();
        NodoArbol ant = null;
        if (tem != null) {
            while (tem.getSiguiente() != null) {
                ant = tem;
                tem = tem.getSiguiente();
            }
        }

        if (ant != null) {
            ant.setSiguiente(null);
            setFin(ant);
        } else {
            setInicio(null);
        }

        return tem;
    }

    public void agregarNodo(NodoArbol n) {

        try {

            Integer.parseInt(n.getClave());

            if (getInicio() == null) {
                setInicio(n);
                setFin(n);

            } else {
                getFin().setSiguiente(n);
                setFin(n);
            }

        } catch (NumberFormatException e) {
            try {
                
            NodoArbol n1 = quitarNodo();
            NodoArbol n2 = quitarNodo();

            int num1 = Integer.parseInt(n1.getClave());
            int num2 = Integer.parseInt(n2.getClave());

            int resultado = operar(num2, num1, n.getClave());

            NodoArbol nR = new NodoArbol("" + resultado, null, null);

            agregarNodo(nR);
            
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "¡Ha ingresado una expresion no valida!");
            }
        }
    }
}
