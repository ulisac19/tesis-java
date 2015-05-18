package AlgortimoEvolutivo;

import ColeccionDatos.Arbol;


public class Individuo {
    
    private int valorFuncionObjetivo;
    private Arbol a;

    public Individuo(int valorFuncionObjetivo, Arbol a) {
        this.valorFuncionObjetivo = valorFuncionObjetivo;
        this.a = a;
    }

    public int getValorFuncionObjetivo() {
        return valorFuncionObjetivo;
    }

    public void setValorFuncionObjetivo(int valorFuncionObjetivo) {
        this.valorFuncionObjetivo = valorFuncionObjetivo;
    }

    public Arbol getA() {
        return a;
    }

    public void setA(Arbol a) {
        this.a = a;
    }
    
    public int calculoFuncionObjetivo(){
        return 1;
    }
    
}
