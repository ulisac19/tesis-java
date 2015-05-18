
package ColeccionDatos;

public class Pila<T> 
{		
    private Nodo raiz;
    
    public Pila() 
    {
        raiz=null;
    }
    
    public void insertar(T x) 
    {
    	Nodo nuevo;
        nuevo = new Nodo();
        nuevo.setInfo((Informacion) x);
        
        if (raiz==null)
        {
            nuevo.setHermano(null);
            raiz = nuevo;
        }
        else
        {
            nuevo.setHermano(raiz);
            raiz = nuevo;
        }
    }
    
    public Informacion extraer ()
    {
        if (raiz!=null)
        {
            Informacion info =  raiz.getInfo();
            raiz = raiz.getHermano();
            return info;
        }
        else
        {
            return null;
        }
    }
    
    
    public boolean vacia() {
        if (raiz==null) {
            return true;
        } else {
    	    return false; 
        }
    }
    
    public int cantidad() 
    {
        int cant=0;
        Nodo reco=raiz;
        
        while (reco!=null) 
        {
            cant++;
            reco=reco.getHermano();
        }
        return cant;
    }
    
}
