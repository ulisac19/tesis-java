package ColeccionDatos;

import AlgortimoEvolutivo.Individuo;
import BaseDatos.ConexionDB;
import ColeccionDatos.Nodo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Arbol 
{

    Nodo raiz;
    
    public Arbol()     
    {
       this.raiz = null;
    }
    
    public Arbol(Nodo raiz)     
    {
       this.raiz = raiz;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
    
    public Nodo buscar(Nodo temporal, Informacion bus) // pre-orden
    {
    Nodo rtn = null;    
    Nodo buscar = new Nodo(bus); 
   
        if(temporal.esIgual(buscar))
        {
            rtn = temporal;
            return rtn;
        }
        
        while(temporal.tieneHijo() && rtn == null)
        {
         temporal = temporal.getHijo();
            rtn = buscar(temporal, bus);
            while(temporal.tieneHermano() && rtn == null )
            {
                 temporal = temporal.getHermano();
                 rtn = buscar(temporal, bus);
            }
        }
        
        
        return rtn;
    }
  
    public void mostrarArbol(Nodo n)
    {
       System.out.println("\t\t\tHorario\t\t\tProfesor\t\tMateria\t\t\tSalon Hora");
       Arbol.imprimir(n, 0);
    }        
    public static void imprimir(Nodo n, int nivel)        
    {   
        for (int i = 0; i < n.getInfo().getTipoNodo(); i++) 
            System.out.print("\t\t\t");
        n.getInfo().ImprimirInfo();
        
        if(n.tieneHijo())
            imprimir(n.getHijo(), nivel+1);
        if(n.tieneHermano())
            imprimir(n.getHermano(), nivel);
        
        
    }

 
    public static Arbol cargarArbol() throws SQLException
    {
        Connection miConexion;
        miConexion =  ConexionDB.GetConnection();
        Statement st = miConexion.createStatement();
        if(miConexion==null)
        {
            System.out.println("Conexión No Realizada Correctamente");
        }
        
        ResultSet queryHorario = st.executeQuery("SELECT * FROM horario");
        queryHorario.next();
        
        int id_nodo_padre = (int)queryHorario.getObject("id");
        Nodo nodo_padre = new Nodo(new Informacion(id_nodo_padre, -1, Parametros.TIPO_NODO_RAIZ), null,null);
        Arbol arbol = new Arbol(nodo_padre);
        
        
        
      
        /* agregar profesores al arbol */
        ResultSet queryProfesor = st.executeQuery("SELECT * FROM profesor");
        ArrayList<Integer> profesor = new ArrayList<>();
            while(queryProfesor.next())
                profesor.add((int)queryProfesor.getObject("id"));
            
            
           
            
        Nodo nodo_aux_profesor, nodo_aux2_profesor;
        int id_nodo_profesor;
        for (int i = 0; i < profesor.size(); i++) 
        {
            id_nodo_profesor = profesor.get(i);
            nodo_aux_profesor = new Nodo(new Informacion(id_nodo_profesor, -1, Parametros.TIPO_NODO_PROFESOR), null,null);
            if(!nodo_padre.tieneHijo())
            {
               nodo_padre.setHijo(nodo_aux_profesor);
                    
            }else{
                nodo_aux2_profesor = nodo_padre.getHijo();
                    
                while(nodo_aux2_profesor.tieneHermano())
                {   
                    nodo_aux2_profesor = nodo_aux2_profesor.getHermano();
                        
                }
                nodo_aux2_profesor.setHermano(nodo_aux_profesor);
                    
            }
                ResultSet queryMateria = st.executeQuery("SELECT * FROM seccion WHERE profesor_id = "+id_nodo_profesor);
                ArrayList<Integer> materia = new ArrayList<>();
                    while(queryMateria.next())
                        materia.add((int)queryMateria.getObject("id"));
                        
                Nodo nodo_aux_materia, nodo_aux2_materia;
                int id_nodo_seccion;
                Nodo nodo_padre_local = nodo_aux_profesor;
                for (int j = 0; j < materia.size(); j++) 
                {
                    id_nodo_seccion = materia.get(j);
                    nodo_aux_materia = new Nodo(new Informacion(id_nodo_seccion, -1, Parametros.TIPO_NODO_MATERIA), null,null);
                    if(!nodo_padre_local.tieneHijo())
                    {
                       nodo_padre_local.setHijo(nodo_aux_materia);

                    }else{
                        nodo_aux2_materia = nodo_padre_local.getHijo();

                        while(nodo_aux2_materia.tieneHermano())
                        {   
                            nodo_aux2_materia = nodo_aux2_materia.getHermano();

                        }
                        nodo_aux2_materia.setHermano(nodo_aux_materia);

                    }       
                }
        }
       
    return arbol;    
    }
    

   

    
}
