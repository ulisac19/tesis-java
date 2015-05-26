package ColeccionDatos;

public class Nodo
{
    private Informacion info;
    private Nodo hermano;
    private Nodo hijo;
    
    
   

    public Nodo(Informacion info, Nodo hermano, Nodo hijo) 
    {
        this.info = info;
        this.hermano = hermano;    
        this.hijo = hijo;
    }


    public Informacion getInfo() 
    { 
        return info;
    }
    
    public int getInfo(int tipoDato) 
    { 
        if(tipoDato == 1)
        {
            return info.getDato1();
        }
        
        if(tipoDato == 2)
        {
            return info.getDato2();
        }
        return -1;
    }

    public void setInfo(Informacion info) 
    {
        this.info = info;
    }

    public Nodo getHermano() {
        return hermano;
    }

    public void setHermano(Nodo hermano) {
        this.hermano = hermano;
    }

    public Nodo getHijo() 
    {
        return hijo;
    }

    public void setHijo(Nodo nuevo) 
    {
        if(hijo == null)    // no tiene hijo
        {
            hijo = nuevo;
        }else{              // si tiene hijo, insertar hermano
            Nodo tmp = hijo;
            Nodo tmp_ant = null;
            while(tmp != null)
            {
               tmp_ant = tmp; 
               tmp = tmp.getHermano();
               if(tmp == null)
               {
                   tmp_ant.setHermano(nuevo);
               }
            }
            
        }
       
    }
    
    public boolean esHoja()
    {
        return !(this.hijo != null || this.hermano != null);
    }
    
    public boolean esIgual(Nodo nodo)
    {
        return this.getInfo().getDato1() == nodo.getInfo().getDato1() && this.getInfo().getDato2()== nodo.getInfo().getDato2();
    }
    
    public boolean tieneHijo()
    {
        return this.hijo != null;
    }
    
    public boolean tieneHermano()
    {
        return this.hermano != null;
    }
}
