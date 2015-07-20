package horarios.universitarios;

import AlgortimoEvolutivo.AlgoritmoGenetico;
import AlgortimoEvolutivo.Individuo;
import BaseDatos.ConexionDB;
import BusquedaTabu.AlgoritmoBusquedaTabu;
import ColeccionDatos.Aleatorio;
import ColeccionDatos.Arbol;
import ColeccionDatos.BloqueHorario;
import ColeccionDatos.Informacion;
import ColeccionDatos.Nodo;
import ColeccionDatos.Parametros;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HorariosUniversitarios 
{
    // Variables AG
    private static Individuo poblacion[];
    private static Individuo poblacionElite[];
    private static Arbol arbol[];
    private static AlgoritmoBusquedaTabu bt;
    private static long marcaTiempoFin;
    private static long marcaTiempoInicio;
    
    // Variables BT
    private static Individuo poblacionBT[];
    private static Individuo poblacionEliteBT[];
    private static Arbol arbolBT[];
    private static long marcaTiempoFinBT;
    private static long marcaTiempoInicioBT;
    public static void main(String[] args) throws SQLException 
    {     
        
          
        Parametros p = new Parametros();    
        
        
        
        //-----------------------------------------------------------
        /*
        * Algoritmo Tabu 
        */
        
        Individuo i = new Individuo(Arbol.cargarArbol());
        int generacionBT = p.ALGORITMO_TABU_CANTIDAD_MINIMO_ITERACIONES; 
        int aleatorio1BT, aleatorio2BT;
        poblacionBT = new Individuo[p.ALGORITMO_TABU_MAXIMO_NUMERO_GENERACION];
        poblacionEliteBT = new Individuo[p.ALGORITMO_TABU_CANTIDAD_LISTA_ELITE];
        arbolBT = new Arbol[p.ALGORITMO_TABU_CANTIDAD_LISTA_ELITE];
        
        bt = new AlgoritmoBusquedaTabu(p, i);
        for (int k = 0; k < p.ALGORITMO_TABU_CANTIDAD_LISTA_ELITE; k++) 
        {  
            arbolBT[k] = Arbol.cargarArbol();
            poblacionEliteBT[k] = new Individuo(arbolBT[k]);
            bt.crearIndividuoAlAzar(poblacionEliteBT[k]);
            
        }
        Individuo nd1BT = null, nd2BT = null;
        marcaTiempoInicioBT = System.currentTimeMillis();     
    
        while(generacionBT <  p.ALGORITMO_TABU_CONDICION_PARADA_MAXIMO_ITERACIONES  )
        {
            for (int l = 0; l < p.ALGORITMO_TABU_MAXIMO_NUMERO_GENERACION; l++) 
            {
                aleatorio1BT = Aleatorio.getAleatorio(0, poblacionEliteBT.length);
                nd1BT = poblacionEliteBT[aleatorio1BT];
                
                aleatorio2BT = Aleatorio.getAleatorio(0, poblacionEliteBT.length);
                nd2BT = poblacionEliteBT[aleatorio2BT];
                bt.cruzar(nd1BT, nd2BT);
            }
        
        marcaTiempoFinBT = System.currentTimeMillis();
        generacionBT++;
        }
        
        
        //-----------------------------------------------------------
        for (Individuo poblacionEliteBT1 : poblacionEliteBT) {
            poblacionEliteBT1.mostrar();
        }
        
        /*
        int generacion = 0, aleatorio1, aleatorio2;
        poblacion = new Individuo[p.ALGORITMO_GENETICO_MAXIMO_NUMERO_GENERACION];
        poblacionElite = new Individuo[p.ALGORITMO_GENETICO_CANTIDAD_LISTA_ELITE];
        arbol = new Arbol[p.ALGORITMO_GENETICO_CANTIDAD_LISTA_ELITE];
        AlgoritmoGenetico b1 = new AlgoritmoGenetico(p);
        for (int i = 0; i < p.ALGORITMO_GENETICO_CANTIDAD_LISTA_ELITE; i++) 
        {   arbol[i] = Arbol.cargarArbol();
        poblacionElite[i] = new Individuo(arbol[i]);
        b1.crearIndividuoAlAzar(poblacionElite[i]);
        }
        Individuo nd1 = null, nd2 = null;
        marcaTiempoInicio = System.currentTimeMillis();
        while(generacion < p.ALGORITMO_GENETICO_CANTIDAD_MINIMO_ITERACIONES  )
        {
        for (int i = 0; i < p.ALGORITMO_GENETICO_MAXIMO_NUMERO_GENERACION; i++)
        {
        aleatorio1 = Aleatorio.getAleatorio(0, poblacionElite.length);
        nd1 = poblacionElite[aleatorio1];
        if(b1.probabilidades(p) == Parametros.ALGORITMO_GENETICO_ID_MUTACION )
        {
        b1.mutar(nd1);
        poblacion[i] = nd1;
        poblacion[i].cargarVectorOcupados();
        }else if(b1.probabilidades(p) == Parametros.ALGORITMO_GENETICO_ID_CRUCE){
        aleatorio2 = Aleatorio.getAleatorio(0, poblacionElite.length);
        nd2 = poblacionElite[aleatorio2];
        b1.cruzar(nd1, nd2);
        poblacion[i] = nd1;
        poblacion[i].cargarVectorOcupados();
        }else if(b1.probabilidades(p) == Parametros.ALGORITMO_GENETICO_ID_BUSQUEDA_TABU){
      //  bt = new AlgoritmoBusquedaTabu(p);
        poblacion[i] = nd1;
        }else{
        poblacion[i] = nd1;
        }
        }
        b1.seleccionar(poblacion, poblacionElite);
        generacion++;
        }
        marcaTiempoFin = System.currentTimeMillis();
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < poblacionElite.length; i++)
        {
        poblacionElite[i].mostrar();
        }
        System.out.println("tardo "+((marcaTiempoFin - marcaTiempoInicio))+" ml sg");
         */
    }

}
