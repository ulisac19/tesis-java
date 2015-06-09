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

public class HorariosUniversitarios 
{

    public static void main(String[] args) throws SQLException 
    {     
        
        Arbol a1  = null;
       
        a1 = Arbol.cargarArbol();
    
        Parametros p = new Parametros();
        
        AlgoritmoGenetico b1 = new AlgoritmoGenetico(p, a1);
        // creo un individuo inicial
        Individuo i = b1.crearIndividuoAlAzar();
        a1.mostrarArbol(a1.getRaiz());
        b1.mutar(i);
        a1.mostrarArbol(a1.getRaiz());
        
 
        
    }

}
