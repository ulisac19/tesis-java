package ColeccionDatos;

public class Informacion {
    
    private int dato1;
    private int dato2;
    private int tipoNodo; 

    public Informacion(int dato1, int dato2, int tipoNodo) {
        this.dato1 = dato1;
        this.dato2 = dato2;
        this.tipoNodo = tipoNodo;
    }
    
  
    
    public Informacion(int dato1) {
        this.dato1 = dato1;
    }
    
    public Informacion() {
    }

 
    public int getDato1() 
    {
        return dato1;
    }

    public void setDato1(int dato1) 
    {
        this.dato1 = dato1;
    }

    public int getDato2() {
        return dato2;
    }

    public void setDato2(int dato2) {
        this.dato2 = dato2;
    }
    
    public void ImprimirInfo()
    {
        if(dato2 > -1)
            System.out.println("("+dato1+","+dato2+")");
        else
            System.out.println("("+dato1+")");
    }  

    public int getTipoNodo() {
        return tipoNodo;
    }

    public void setTipoNodo(int tipo_nodo) {
        this.tipoNodo = tipoNodo;
    }
    
    
    
    public static Informacion crearInformacion(int numero1)
    {
        return new Informacion(numero1, -1, Parametros.TIPO_NODO_INDEFINIDO); 
    }
   
    public static Informacion crearInformacion(int numero1, int numero2)
    {
        return new Informacion(numero1, numero2,Parametros.TIPO_NODO_INDEFINIDO); 
    }
    
}
