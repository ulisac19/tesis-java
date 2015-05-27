package horarios.universitarios;

import AlgortimoEvolutivo.AlgoritmoGenetico;
import ColeccionDatos.Arbol;
import ColeccionDatos.Parametros;
import java.sql.SQLException;

public class HorariosUniversitarios 
{

    public static void main(String[] args) throws SQLException 
    {     
        
        Arbol a = null;
        a = Arbol.cargarArbol();
        a.mostrarArbol(a.getRaiz());
        //Parametros p = new Parametros();
        //AlgoritmoGenetico a = new AlgoritmoGenetico(p);
       

    }
    
    
}
