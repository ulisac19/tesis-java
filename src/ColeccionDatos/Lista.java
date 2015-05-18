package ColeccionDatos;

import ColeccionDatos.Informacion;
import ColeccionDatos.Nodo;

public class Lista<T>
{
    
    private Nodo inicio;
    private Nodo fin;
    private int listaTamano;

    public Lista()
    {
        this.inicio = null;
        this.fin = null;
        listaTamano = 0;
    }
    
    public void InsertarInicio(Informacion info)
    {
        Nodo nuevo = new Nodo(info, inicio, null);
        inicio = nuevo;
        
            if(fin == null)
            {
                fin = inicio;
            }
        listaTamano++;
    }
    
    public void InsertarFinal(Informacion info)
    {
        Nodo nuevo = new Nodo(info, null, null);
        
            if (inicio == null) 
            {
                fin = nuevo;
                inicio = nuevo;
            }else{
                fin.setHermano(nuevo);
                fin = nuevo;
            }
        listaTamano++;    
    }
    
    public void EliminarInicio()
    {
        inicio = inicio.getHermano();
        listaTamano--;
    }
    
    public boolean EliminarItem(Nodo eliminar)
    {
        Nodo temporal = inicio;
        Nodo anterior = null;
        
        while(temporal != null)
        {
            if(temporal.esIgual(eliminar))
            {
                if(temporal == inicio)
                {
                    EliminarInicio();
                    eliminar = null;
                    return true;
                }else{                    
                    anterior.setHermano(temporal.getHermano());
                    eliminar = null;
                    return true;
                }
                
            }
            
            anterior = temporal;
            temporal = temporal.getHermano();
        }
        return false;
    }
    
    public Nodo getInicio()
    {
        return inicio;         
    }
    
    public void listar()
    {
        Nodo temporal = inicio;
        while(temporal != null)
        {   
            temporal.getInfo().ImprimirInfo();
            temporal = temporal.getHermano();
        }
                
    }
    
    public boolean buscar(int tipo, int dato1, int dato2)
    {
        Nodo temporal = inicio;
        boolean encontrado =  false;
        while(temporal != null || encontrado)
        {
               if(dato1 == temporal.getInfo().getDato1() && tipo == 1) // solo dato1
               {
                   encontrado = true;
                   break;
               }
               
               if(dato2 == temporal.getInfo().getDato2() && tipo == 2) // solo dato2
               {
                   encontrado = true;
                   break;
               }
               if(dato1 == temporal.getInfo().getDato1() && dato2 == temporal.getInfo().getDato2() && tipo == 3) // ambos datos
               {                   
                   encontrado = true;
                   break;
               }
            temporal = temporal.getHermano();
        }
        
        return encontrado;
        
    }

    public int getListaTamano() 
    {
        return listaTamano;
    }
    
}
