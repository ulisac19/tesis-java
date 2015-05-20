package horarios.universitarios;

import static BaseDatos.ConexionDB.GetConnection;
import ColeccionDatos.Arbol;
import ColeccionDatos.Informacion;
import ColeccionDatos.Nodo;
import BaseDatos.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class HorariosUniversitarios 
{

    public static void main(String[] args) throws SQLException 
    {     
        Connection miConexion;
        miConexion =  ConexionDB.GetConnection();
        Statement st = miConexion.createStatement();
        if(miConexion==null)
        {
            System.out.println("Conexión No Realizada Correctamente");
        }
        ResultSet rs = st.executeQuery("SELECT * FROM parametrosgenetico");
            while (rs.next())
            {
                System.out.println(rs.getObject("descripcion"));
            }
            
        
    }
    
    
}
