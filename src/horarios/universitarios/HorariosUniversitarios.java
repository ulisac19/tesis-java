package horarios.universitarios;

import AlgortimoEvolutivo.AlgoritmoGenetico;
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
        
        Arbol a = null;
        a = Arbol.cargarArbol();
    
        Parametros p = new Parametros();
        AlgoritmoGenetico b = new AlgoritmoGenetico(p, a);
        b.crearIndividuoAlAzar();
       // a.mostrarArbol(a.getRaiz());
        
        
        
            //********************************************************* //
            int i = 0, id, id_horaInicio, id_horaFin, id_dia;

            Connection miConexion;
            miConexion =  ConexionDB.GetConnection();
            Statement st = miConexion.createStatement();
            if(miConexion==null)
            {
                System.out.println("Conexión No Realizada Correctamente");
                return;
            }
/*
            ResultSet queryBloqueHorario = st.executeQuery("SELECT * FROM bloquehorario");

            while(queryBloqueHorario.next())
                i++;

            BloqueHorario[] bloqueHorario = new BloqueHorario[i];

            queryBloqueHorario = st.executeQuery("SELECT * FROM bloquehorario");
            i = 0;
            while(queryBloqueHorario.next())
            {
                id = (int)(queryBloqueHorario.getObject("id"));
                id_horaInicio =  (int)(queryBloqueHorario.getObject("hora_inicio"));
                id_horaFin =  (int)(queryBloqueHorario.getObject("hora_fin"));
                id_dia =  (int)(queryBloqueHorario.getObject("dia_id")); 
                bloqueHorario[i] = new BloqueHorario(id, id_horaInicio, id_horaFin, id_dia);
            i++;
            }*/
        int id1 = 77;
            ResultSet queryBloqueHorario = st.executeQuery("SELECT * FROM bloquehorario where id = "+id1);
            queryBloqueHorario.next();
                id = (int)(queryBloqueHorario.getObject("id"));
                id_horaInicio =  (int)(queryBloqueHorario.getObject("hora_inicio"));
                id_horaFin =  (int)(queryBloqueHorario.getObject("hora_fin"));
                id_dia =  (int)(queryBloqueHorario.getObject("dia_id")); 
            BloqueHorario idbloq1 = new BloqueHorario(id, id_horaInicio, id_horaFin, id_dia);
                
                
        int id2 = 80;
            ResultSet queryBloqueHorario2 = st.executeQuery("SELECT * FROM bloquehorario where id = "+id2);
            queryBloqueHorario2.next();
                id = (int)(queryBloqueHorario2.getObject("id"));
                id_horaInicio =  (int)(queryBloqueHorario2.getObject("hora_inicio"));
                id_horaFin =  (int)(queryBloqueHorario2.getObject("hora_fin"));
                id_dia =  (int)(queryBloqueHorario2.getObject("dia_id")); 
            BloqueHorario idbloq2 = new BloqueHorario(id, id_horaInicio, id_horaFin, id_dia);
        
            
            System.out.println(BloqueHorario.choque(idbloq1, idbloq2));
            idbloq1.imprimir();
            idbloq2.imprimir();
            
            //********************************************************* //
        
    }

}
