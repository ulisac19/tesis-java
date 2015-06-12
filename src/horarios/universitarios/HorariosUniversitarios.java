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
        Arbol a2  = null;
       
        a1 = a2 = Arbol.cargarArbol();
        Individuo i = new Individuo(a2);
        
        Parametros p = new Parametros();
        
        AlgoritmoGenetico b1 = new AlgoritmoGenetico(p);
        
        Individuo i1 = b1.crearIndividuoAlAzar(i);
       /* i1.getArbol().mostrarArbol(i1.getArbol().getRaiz());
        Individuo i2 = b1.mutar(i1, a1);
        i1.getArbol().mostrarArbol(i1.getArbol().getRaiz());
        for (int i = 0; i < 100; i++)
        i2 = b1.mutar(i2, a1);
        
        i2.getArbol().mostrarArbol(i2.getArbol().getRaiz());
        
        b1.cruzar(i1, i2);
        */
        
    }

}
