package main;

import javax.swing.JTextArea;

public class PilaArboles {

    private NodoArbol inicio;
    private NodoArbol fin;
    private String salida;

    public PilaArboles() {
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

    private void mostrar(NodoArbol nodo) {
        salida += " " + nodo.getClave();
    }

    public void imprimirArbol(JTextArea jTextArea) {
        jTextArea.setText(traversePreOrder(getFin()));
    }

    private void preOrden(NodoArbol nodo) {
        if (nodo != null) {
            mostrar(nodo);
            preOrden(nodo.getIzq());
            preOrden(nodo.getDer());
        }
    }

    private void inOrden(NodoArbol nodo) {
        if (nodo != null) {
            inOrden(nodo.getIzq());
            mostrar(nodo);
            inOrden(nodo.getDer());
        }
    }

    public String parsearInfija() {
        salida = "";
        inOrden(getFin());
        return salida;
    }

    public String parsearPrefija() {
        salida = "";
        preOrden(getFin());
        return salida;
    }

    public void agregarNodoArbol(NodoArbol arbol) {
        if (getInicio() == null) {
            setInicio(arbol);
            setFin(getInicio());
        } else {
            getFin().setSiguiente(arbol);
            setFin(arbol);
        }
    }

    public NodoArbol quitarNodoArbol() {

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

    public void guardarExpresion(String expresion) {

        setInicio(null);

        for (int i = 0; i < expresion.length(); i++) {

            String elemento = String.valueOf(expresion.charAt(i));

            if ("-+*/^".contains(elemento)) {

                NodoArbol nodo = new NodoArbol(elemento, null, null);

                NodoArbol nodo1 = quitarNodoArbol();
                NodoArbol nodo2 = quitarNodoArbol();

                nodo.setDer(nodo1);
                nodo.setIzq(nodo2);

                agregarNodoArbol(nodo);

            } else {

                int numero = Integer.parseInt(elemento);

                if (numero >= 0 && numero <= 9) {
                    NodoArbol nodo = new NodoArbol(elemento, null, null);
                    agregarNodoArbol(nodo);
                }
            }
        }
    }

    private String traversePreOrder(NodoArbol root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.getClave());

        String pointerRight = "└──";
        String pointerLeft = (root.getDer() != null) ? "├──" : "└──";

        traverseNodes(sb, "", pointerLeft, root.getIzq(), root.getDer() != null);
        traverseNodes(sb, "", pointerRight, root.getDer(), false);

        return sb.toString();
    }

    private void traverseNodes(StringBuilder sb, String padding, String pointer, NodoArbol node,
            boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getClave());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (node.getDer() != null) ? "├──" : "└──";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.getIzq(), node.getDer() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getDer(), false);
        }
    }

}
