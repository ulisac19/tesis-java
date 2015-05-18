package ColeccionDatos;

import ColeccionDatos.Nodo;


public class Arbol 
{

    Nodo raiz;
    
    public Arbol()     
    {
       this.raiz = null;
    }
    
    public Arbol(Nodo raiz)     
    {
       this.raiz = raiz;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
    
    public Nodo buscar(Nodo temporal, Nodo buscar) // pre-orden
    {
    Nodo rtn = null;    
        if(temporal.esIgual(buscar))
        {
            return temporal;
        }else{
            if(temporal.tieneHijo()) // tiene hijo
            {   
                if(temporal.esIgual(buscar))
                {
                    return temporal;
                }else{
                    rtn = buscar(temporal.getHijo(), buscar);
                }
                
            }else{                   // si no tiene hijo, buscar en hermanos
                    if(temporal.tieneHermano())
                    {
                        if(temporal.esIgual(buscar))
                        {
                            return temporal;
                        }
                        rtn = buscar(temporal.getHermano(), buscar);
                    }else{
                        return rtn;
                    }
            }
        }
        return rtn;
    }
    
    public void insertar_nodo(Nodo nodo)
    {
    
    }
    
    public int contarHojas()
    {
        return 1;
    }
    

   

    
}
