package AlgortimoEvolutivo;

import BaseDatos.ConexionDB;
import ColeccionDatos.Arbol;
import ColeccionDatos.Informacion;
import ColeccionDatos.Nodo;
import ColeccionDatos.Parametros;
import ColeccionDatos.Rango;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class AlgoritmoGenetico 
{ 
    private ArrayList<Individuo> poblacion;
    private ArrayList<Individuo> listaElite;
    private long marcaTiempoFin;
    private long marcaTiempoInicio;
    private int iterador;
    private Parametros parametros;
    private Arbol arbol;
   
    
    public AlgoritmoGenetico(Parametros parametros){
        poblacion = new ArrayList<>();
        listaElite = new ArrayList<>();
        iterador = 0;
        this.parametros = parametros;
    }
    
    public void agregarIndividuoPoblacion(Individuo individuo){}
    
    public Individuo crearIndividuoAlAzar() throws SQLException
    {
        Individuo individuo = new Individuo(-1, new Arbol());
        // basado en restricciones generar individuo al azar
        Connection miConexion;
        miConexion =  ConexionDB.GetConnection();
        Statement st = miConexion.createStatement();
        if(miConexion==null)
        {
            System.out.println("Conexión No Realizada Correctamente");
        }
        
        ResultSet queryHorario = st.executeQuery("SELECT * FROM horario");
        queryHorario.next();
        
        int id_nodo_padre = (int)queryHorario.getObject("id");
        Nodo nodo_padre = new Nodo(new Informacion(id_nodo_padre, -1, Parametros.TIPO_NODO_RAIZ), null,null);
        arbol = new Arbol(nodo_padre);
        
        
        
        ResultSet queryProfesor = st.executeQuery("SELECT * FROM profesores");
        int i = 0;
        while(queryProfesor.next())
        {
            
            if(i == 0)
            {
               nodo_padre.setHijo(new Nodo(new Informacion((int)queryProfesor.getObject("id"), -1, Parametros.TIPO_NODO_PROFESOR), null,null));
            }else{
                while(nodo_padre.getHijo().tieneHermano())
                {
                    
                }
                
               nodo_padre.getHijo().setHermano(new Nodo(new Informacion((int)queryProfesor.getObject("id"), -1, Parametros.TIPO_NODO_PROFESOR), null,null));
            }
           i++;
        }
            
       
        
        return individuo;
    }
    
    public void mutar(Individuo individuo){}
    public void cruzar(Individuo individuo1, Individuo individuo2)
    {
    
    }
    
    public void selecccionar(ArrayList<Individuo> poblacion, ArrayList<Individuo> listaElite)
    {
        
    }
    
    public boolean maximoIteraciones(int iteraciones)
    {
        return iteraciones >= parametros.ALGORTIMO_GENETICO_CONDICION_PARADA_MAXIMO_ITERACIONES;
    }
    
    public boolean maximoTiempo(int tiempo)
    {
        return tiempo >= parametros.ALGORTIMO_GENETICO_CONDICION_PARADA_MAXIMO_SEGUNDOS;
    }
    
    public boolean nivelOptimo() //mucho tiempo sin mejora significativamente
    {
        return false;
    }
    
    public void iterar()
    {
        Individuo individuoInicial = crearIndividuoAlAzar();        
        marcaTiempoInicio = System.currentTimeMillis() / 1000L;
        Integer idOperadorGenetico;

        
        while(maximoIteraciones(iterador) || maximoTiempo((int)(marcaTiempoFin - marcaTiempoInicio)) || nivelOptimo())
        {
            
            /* Seleccionar operador genetico a aplicar */
            idOperadorGenetico = probabilidades();
            
            if(idOperadorGenetico.equals(Parametros.ALGORTIMO_GENETICO_ID_MUTACION))
            {
                mutar(poblacion.get(iterador));
            }
            
            if(idOperadorGenetico.equals(Parametros.ALGORTIMO_GENETICO_ID_CRUCE))
            {
                //cruzar();
            }
            
            if(idOperadorGenetico.equals(Parametros.ALGORTIMO_GENETICO_ID_BUSQUEDA_TABU))
            {
            }
            

            iterador++;
            marcaTiempoFin = System.currentTimeMillis() / 1000L;
        }
        
        
    }
    
    public int probabilidades() // tabu, cruzar, mutar o dejar pasar
    {
        Random rn = new Random();
        int numeroAlazar = rn.nextInt(100) + 1;
        int a, b;
        int rsp = Parametros.ALGORTIMO_GENETICO_ID_COPIAR;
        
        Rango mutacion = new Rango();
        Rango cruce = new Rango();
        Rango busqueTabu = new Rango();
        
        a = 1;
        b = a + (int)parametros.ALGORTIMO_GENETICO_PROBABILIDAD_MUTACION*100;
        mutacion.setInicio(a);
        mutacion.setFin(b);
        
        a = b + 1;
        b = a + (int)parametros.ALGORTIMO_GENETICO_PROBABILIDAD_CRUCE*100;
        cruce.setInicio(a);
        cruce.setFin(b);
        
        a = b + 1;
        b = a + (int)parametros.ALGORTIMO_GENETICO_PROBABILIDAD_BUSQUEDA_TABU*100;
        busqueTabu.setInicio(a);
        busqueTabu.setFin(b);
        
        if(mutacion.estaDentroRango(numeroAlazar))
            rsp = Parametros.ALGORTIMO_GENETICO_ID_MUTACION;
                    
        if(cruce.estaDentroRango(numeroAlazar))
            rsp = Parametros.ALGORTIMO_GENETICO_ID_CRUCE;
                    
        if(busqueTabu.estaDentroRango(numeroAlazar))
            rsp = Parametros.ALGORTIMO_GENETICO_ID_BUSQUEDA_TABU;
    
    return rsp;      
    }
    
    
}
