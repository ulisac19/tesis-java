package AlgortimoEvolutivo;

import ColeccionDatos.Arbol;


public class Individuo {
    
    
    private int valorFuncionObjetivo;
    private Arbol arbol;
    
    public Individuo(int valorFuncionObjetivo, Arbol arbol) {
        this.valorFuncionObjetivo = valorFuncionObjetivo;
        this.arbol = arbol;
    }
    
    public int getValorFuncionObjetivo() {
        return valorFuncionObjetivo;
    }

    public void setValorFuncionObjetivo(int valorFuncionObjetivo) {
        this.valorFuncionObjetivo = valorFuncionObjetivo;
    }

    public Arbol getArbol() {
        return arbol;
    }

    public void setArbol(Arbol arbol) {
        this.arbol = arbol;
    }

}
