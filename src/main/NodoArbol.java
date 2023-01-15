
package main;

public class NodoArbol {
    
    private String clave;
    private NodoArbol siguiente;
    private NodoArbol der;
    private NodoArbol izq;

    public NodoArbol(String clave, NodoArbol der, NodoArbol izq) {
        this.clave = clave;
        this.der = der;
        this.izq = izq;
        siguiente = null;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public NodoArbol getDer() {
        return der;
    }

    public void setDer(NodoArbol der) {
        this.der = der;
    }

    public NodoArbol getIzq() {
        return izq;
    }

    public void setIzq(NodoArbol izq) {
        this.izq = izq;
    }

    public NodoArbol getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoArbol siguiente) {
        this.siguiente = siguiente;
    }
     
}
