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
        a = Arbol.cargarArbol();
      /*  Informacion info;
        a.mostrarArbol(a.getRaiz());
        Nodo o;
        info = new Informacion(17, -1, Parametros.TIPO_NODO_MATERIA);
        
        o = a.buscar(a.getRaiz(), new Nodo(info));
       
        o.setHermano(new Nodo(new Informacion(7, 7, Parametros.TIPO_NODO_SALON_HORARIO)));
        
     */
        Parametros p = new Parametros();
        AlgoritmoGenetico b = new AlgoritmoGenetico(p, a);
        b.crearIndividuoAlAzar();
       // a.mostrarArbol(a.getRaiz());
        
    }

}
