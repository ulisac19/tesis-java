package horarios.universitarios;

import AlgortimoEvolutivo.AlgoritmoGenetico;
import AlgortimoEvolutivo.Individuo;
import BaseDatos.ConexionDB;
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
    
    public static void main(String[] args) throws SQLException 
    {     
        
        Arbol a1  = null;
        int generacion = 0;
        a1 = Arbol.cargarArbol();
        Individuo i0, i1 = new Individuo(a1);        
        Parametros p = new Parametros();        
        poblacion = new Individuo[p.ALGORITMO_GENETICO_MAXIMO_NUMERO_GENERACION];
        poblacionElite = new Individuo[p.ALGORITMO_GENETICO_CANTIDAD_LISTA_ELITE];
        AlgoritmoGenetico b1 = new AlgoritmoGenetico(p);
        
        // generar individuo inicial
        b1.crearIndividuoAlAzar(i1);
        
        while(generacion < 100 /* p.ALGORTIMO_GENETICO_CANTIDAD_MINIMO_ITERACIONES */ )
        {    
            for (int i = 0; i < p.ALGORITMO_GENETICO_MAXIMO_NUMERO_GENERACION; i++) 
            {
                if(b1.probabilidades(p) == Parametros.ALGORITMO_GENETICO_ID_MUTACION )
                {   System.out.println(".");
                    b1.mutar(i1);
                    poblacion[i] = i1;
                }else if(b1.probabilidades(p) == Parametros.ALGORITMO_GENETICO_ID_CRUCE && i > 0){
                    i0 = poblacion[ i - 1 ];
                    b1.cruzar(i0, i1);
                    poblacion[i] = i0;
                    System.out.println("*");
                    
                }else{
                    poblacion[i] = new Individuo(a1);
                    
                }
                
            }
            
            b1.selecccionar(poblacion, poblacionElite);
            
            generacion++;
        }
        for (int i = 0; i < poblacionElite.length; i++) {
            poblacionElite[i].mostrar();
            
        }
       
       
       
       
        
        
        
            
               // b1.mutar(i2);
            
            
           // b1.cruzar(i1, i2);
       
        
    }

}
