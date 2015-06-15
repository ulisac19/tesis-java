package horarios.universitarios;

import AlgortimoEvolutivo.AlgoritmoGenetico;
import AlgortimoEvolutivo.Individuo;
import BaseDatos.ConexionDB;
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
    private static Individuo poblacion[];
    private static Individuo poblacionElite[];
    private static Arbol arbol[];
    
    public static void main(String[] args) throws SQLException 
    {     
        int generacion = 0, aleatorio1, aleatorio2;
          
        Parametros p = new Parametros();        
        poblacion = new Individuo[p.ALGORITMO_GENETICO_MAXIMO_NUMERO_GENERACION];
        poblacionElite = new Individuo[p.ALGORITMO_GENETICO_CANTIDAD_LISTA_ELITE];
        arbol = new Arbol[p.ALGORITMO_GENETICO_CANTIDAD_LISTA_ELITE];
        AlgoritmoGenetico b1 = new AlgoritmoGenetico(p);
        
        
        // generar poblacion     inicial       
        for (int i = 0; i < p.ALGORITMO_GENETICO_CANTIDAD_LISTA_ELITE; i++) 
        {   arbol[i] = Arbol.cargarArbol();
            poblacionElite[i] = new Individuo(arbol[i]);
            b1.crearIndividuoAlAzar(poblacionElite[i]);
            
        }
        
        Individuo nd1 = null, nd2 = null;
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

                    }else{
                        poblacion[i] = nd1;

                    }

            }
             
            b1.seleccionar(poblacion, poblacionElite);
            
            generacion++;
        }
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < poblacionElite.length; i++)
        {
            poblacionElite[i].mostrar();
        }
        
      
       
       
    }

}
