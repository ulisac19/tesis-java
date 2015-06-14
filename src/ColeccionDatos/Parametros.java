package ColeccionDatos;

import BaseDatos.ConexionDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Parametros {
    
    static public int TIPO_NODO_RAIZ = 1;
    static public int TIPO_NODO_PROFESOR = 2;
    static public int TIPO_NODO_MATERIA = 3;
    static public int TIPO_NODO_SALON_HORARIO = 4;
    static public int TIPO_NODO_INDEFINIDO = -1;
    static public int ALGORITMO_GENETICO_ID_MUTACION = 1;
    static public int ALGORITMO_GENETICO_ID_CRUCE = 2;
    static public int ALGORITMO_GENETICO_ID_COPIAR =  5;
    static public int ALGORITMO_GENETICO_ID_BUSQUEDA_TABU =  3;
    static public int NUMERO_DIAS = 5;
    static public int NUMERO_BLOQUES = 36;
    static public int SEMESTRES = 10;

    
    public float ALGORITMO_GENETICO_PROBABILIDAD_MUTACION;
    public float ALGORITMO_GENETICO_PROBABILIDAD_CRUCE;
    public float ALGORITMO_GENETICO_PROBABILIDAD_BUSQUEDA_TABU;
    public float ALGORITMO_GENETICO_PORCENTAJE_LISTA_ELITE;
    public float ALGORITMO_GENETICO_CONDICION_PARADA_MEJORA_ACEPTABLE;
    public int ALGORITMO_GENETICO_MAXIMO_NUMERO_GENERACION;
    public int ALGORITMO_GENETICO_CANTIDAD_LISTA_ELITE;
    public int ALGORITMO_GENETICO_CONDICION_PARADA_MAXIMO_ITERACIONES;
    public int ALGORITMO_GENETICO_CANTIDAD_MINIMO_ITERACIONES;
    public int ALGORITMO_GENETICO_CONDICION_PARADA_MAXIMO_SEGUNDOS;

    public float ALGORITMO_TABU_CONDICION_PARADA_MEJORA_ACEPTABLE;
    public float ALGORITMO_TABU_PORCENTAJE_LISTA_ELITE;
    public int ALGORITMO_TABU_CANTIDAD_LISTA_ELITE;
    public int ALGORITMO_TABU_MAXIMO_NUMERO_GENERACION;
    public int ALGORITMO_TABU_CONDICION_PARADA_MAXIMO_ITERACIONES;
    public int ALGORITMO_TABU_CANTIDAD_MINIMO_ITERACIONES;
    public int ALGORITMO_TABU_CONDICION_PARADA_MAXIMO_SEGUNDOS;
    public int ALGORITMO_TABU_TOPE_LISTA_TABU;
    
    public Parametros() throws SQLException {
        
        Connection miConexion;
        miConexion =  ConexionDB.GetConnection();
        Statement st = miConexion.createStatement();
        if(miConexion==null)
        {
            System.out.println("Conexión No Realizada Correctamente");
        }
        ResultSet queryGenetico = st.executeQuery("SELECT * FROM parametrosgenetico");
        queryGenetico.next();
            this.ALGORITMO_GENETICO_CANTIDAD_MINIMO_ITERACIONES         = (int) queryGenetico.getObject("generacion_inicio");
            this.ALGORITMO_GENETICO_CONDICION_PARADA_MAXIMO_ITERACIONES = (int) queryGenetico.getObject("generacion_fin");
            this.ALGORITMO_GENETICO_MAXIMO_NUMERO_GENERACION            = (int) queryGenetico.getObject("hijos_generacion");
            this.ALGORITMO_GENETICO_CONDICION_PARADA_MAXIMO_SEGUNDOS    = (int) queryGenetico.getObject("tiempo_maximo");
            this.ALGORITMO_GENETICO_CONDICION_PARADA_MEJORA_ACEPTABLE   = (float) queryGenetico.getObject("mejora_aceptable");
            this.ALGORITMO_GENETICO_PORCENTAJE_LISTA_ELITE              = (float) queryGenetico.getObject("porcentaje_lista_elite");
            this.ALGORITMO_GENETICO_PROBABILIDAD_CRUCE                  = (float) queryGenetico.getObject("probabilidad_cruce");
            this.ALGORITMO_GENETICO_PROBABILIDAD_MUTACION               = (float) queryGenetico.getObject("probabilidad_mutacion");
            this.ALGORITMO_GENETICO_PROBABILIDAD_BUSQUEDA_TABU          = (float) queryGenetico.getObject("probabilidad_tabu");  
            this.ALGORITMO_GENETICO_CANTIDAD_LISTA_ELITE                = (int) (this.ALGORITMO_GENETICO_MAXIMO_NUMERO_GENERACION * this.ALGORITMO_GENETICO_PORCENTAJE_LISTA_ELITE);
        
        ResultSet queryTabu = st.executeQuery("SELECT * FROM parametrostabu");
        queryTabu.next();
        
            this.ALGORITMO_TABU_CONDICION_PARADA_MEJORA_ACEPTABLE       = (float) queryTabu.getObject("mejora_aceptable");
            this.ALGORITMO_TABU_PORCENTAJE_LISTA_ELITE                  = (float) queryTabu.getObject("porcentaje_lista_elite");
            this.ALGORITMO_TABU_MAXIMO_NUMERO_GENERACION                = (int) queryTabu.getObject("hijos_generacion");
            this.ALGORITMO_TABU_CANTIDAD_LISTA_ELITE                    = (int) (this.ALGORITMO_TABU_MAXIMO_NUMERO_GENERACION * this.ALGORITMO_TABU_PORCENTAJE_LISTA_ELITE);
            this.ALGORITMO_TABU_CONDICION_PARADA_MAXIMO_ITERACIONES     = (int) queryTabu.getObject("generacion_fin");
            this.ALGORITMO_TABU_CANTIDAD_MINIMO_ITERACIONES             = (int) queryTabu.getObject("generacion_inicio");
            this.ALGORITMO_TABU_CONDICION_PARADA_MAXIMO_SEGUNDOS        = (int) queryTabu.getObject("tiempo_maximo");
            this.ALGORITMO_TABU_TOPE_LISTA_TABU                         = (int) queryTabu.getObject("tope_lista_tabu");
        
    }
    
}
