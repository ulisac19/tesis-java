package AlgortimoEvolutivo;

import BaseDatos.ConexionDB;
import ColeccionDatos.Arbol;
import ColeccionDatos.BloqueHorario;
import ColeccionDatos.Informacion;
import ColeccionDatos.Nodo;
import ColeccionDatos.Parametros;
import ColeccionDatos.Rango;
import ColeccionDatos.Salon;
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
    private Salon salon[];
    private BloqueHorario bloqueHorario[];
    private int cantSalones;
    private int cantBloques;

    public int getCantSalones() {
        return cantSalones;
    }

    public void setCantSalones(int cantSalones) {
        this.cantSalones = cantSalones;
    }

    public int getCantBloques() {
        return cantBloques;
    }

    public void setCantBloques(int cantBloques) {
        this.cantBloques = cantBloques;
    }
   
    
    public AlgoritmoGenetico(Parametros parametros){
        poblacion = new ArrayList<>();
        listaElite = new ArrayList<>();
        iterador = 0;
        this.parametros = parametros;
    }
    
    public void agregarIndividuoPoblacion(Individuo individuo){}
    
    public Individuo crearIndividuoAlAzar() throws SQLException
    {
        Individuo individuo = null;
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
    
    private void cargarSalones() throws SQLException
    {   
        int i = 0, id, tipoSalon_id;
        String nombre;
        Connection miConexion;
        miConexion =  ConexionDB.GetConnection();
        Statement st = miConexion.createStatement();
        if(miConexion==null)
        {
            System.out.println("Conexión No Realizada Correctamente");
            return;
        }
        
        ResultSet querySalon = st.executeQuery("SELECT * FROM bloquehorario");
       
        while(querySalon.next())
            i++;
        setCantSalones(i);
        salon = new Salon[getCantSalones()];
        querySalon = st.executeQuery("SELECT * FROM salon");
        i = 0;
        while(querySalon.next())
        {
            id = (int)(querySalon.getObject("id"));
            nombre =  (String)(querySalon.getObject("nombre"));
            tipoSalon_id =  (int)(querySalon.getObject("tipoSalon_id")); 
            salon[i] = new Salon(id,nombre , tipoSalon_id);
        i++;
        }
    }
    
    private void cargarBloqueHorario() throws SQLException
    {   
        int i = 0, id, id_horaInicio, id_horaFin, id_dia;
        
        Connection miConexion;
        miConexion =  ConexionDB.GetConnection();
        Statement st = miConexion.createStatement();
        if(miConexion==null)
        {
            System.out.println("Conexión No Realizada Correctamente");
            return;
        }
        
        ResultSet queryBloqueHorario = st.executeQuery("SELECT * FROM bloquehorario");
       
        while(queryBloqueHorario.next())
            i++;
        setCantBloques(i);
        bloqueHorario = new BloqueHorario[getCantBloques()];
        
        queryBloqueHorario = st.executeQuery("SELECT * FROM bloquehorario");
        i = 0;
        while(queryBloqueHorario.next())
        {
            id = (int)(queryBloqueHorario.getObject("id"));
            id_horaInicio =  (int)(queryBloqueHorario.getObject("hora_inicio"));
            id_horaFin =  (int)(queryBloqueHorario.getObject("hora_fin"));
            id_dia =  (int)(queryBloqueHorario.getObject("dia_id")); 
            bloqueHorario[i] = new BloqueHorario(id, id_horaInicio, id_horaFin, id_dia);
        i++;
        }
    }
    
    public void iterar() throws SQLException
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
