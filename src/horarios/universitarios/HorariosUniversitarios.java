package horarios.universitarios;

import AlgortimoEvolutivo.AlgoritmoGenetico;
import ColeccionDatos.Arbol;
import ColeccionDatos.Informacion;
import ColeccionDatos.Nodo;
import ColeccionDatos.Parametros;
import java.sql.SQLException;

public class HorariosUniversitarios 
{

    public static void main(String[] args) throws SQLException 
    {     
        
        Arbol a = null;
        Nodo o;
        a = Arbol.cargarArbol();
        a.mostrarArbol(a.getRaiz());
        Informacion info;
        info = new Informacion(17, -1, Parametros.TIPO_NODO_MATERIA);
        
        o = a.buscar(a.getRaiz(), new Nodo(info));
       
        o.setHermano(new Nodo(new Informacion(7, 7, Parametros.TIPO_NODO_SALON_HORARIO)));
        
     
        a.mostrarArbol(a.getRaiz());
        Parametros p = new Parametros();
        AlgoritmoGenetico b = new AlgoritmoGenetico(p);
      
    }
    
    
}
