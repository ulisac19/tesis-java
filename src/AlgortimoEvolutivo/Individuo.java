package AlgortimoEvolutivo;

import BaseDatos.ConexionDB;
import ColeccionDatos.Arbol;
import ColeccionDatos.BloqueHorario;
import static ColeccionDatos.Parametros.NUMERO_BLOQUES;
import static ColeccionDatos.Parametros.NUMERO_DIAS;
import static ColeccionDatos.Parametros.SEMESTRES;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Individuo {
    
    
    private int valorFuncionObjetivo;
    private Arbol arbol;
    private BloqueHorario semestreBloquesUsados[][];
    private boolean diaHoraSalonOcupado[][];    
    
    public Individuo(Arbol arbol) {
        this.valorFuncionObjetivo = valorFuncionObjetivo;
        this.arbol = arbol;
        semestreBloquesUsados = new BloqueHorario[SEMESTRES][NUMERO_DIAS * NUMERO_BLOQUES];
    }
    
    public int getValorFuncionObjetivo() {
        return valorFuncionObjetivo;
    }

    public void setValorFuncionObjetivo(int valorFuncionObjetivo) {
        this.valorFuncionObjetivo = valorFuncionObjetivo;
    }

    public Arbol getArbol() {
        return arbol;
    }

    public void setArbol(Arbol arbol) {
        this.arbol = arbol;
    }
    public void setSemestreBloquesUsados(int x, int y, BloqueHorario value)
    {
        semestreBloquesUsados[x][y] = value;
    }
   
    public int funcionObjetivo()
    {
    int rtn = 0;
        for (int i = 0; i < SEMESTRES ; i++) 
        {           
            for (int j = 0; j < ( NUMERO_BLOQUES * NUMERO_DIAS )- 1 ; j++) 
            {
                if(semestreBloquesUsados[i][j] != null)
                    
                    for (int k = j; k < ( NUMERO_BLOQUES * NUMERO_DIAS ) -1; k++) 
                    { 
                        if(k != j && semestreBloquesUsados[i][k] != null)                        
                        {  
                            rtn = rtn + BloqueHorario.choque(semestreBloquesUsados[i][j], semestreBloquesUsados[i][k]);
                        }
                    }
            }
                
        }
        return rtn;
    }
    
    private void cargarVectorOcupados() throws SQLException
    {  
        this.diaHoraSalonOcupado = new boolean[cantidadSalones() + 1][(NUMERO_BLOQUES * NUMERO_DIAS) + 1];
        for (int i = 0; i <= cantidadSalones(); i++) 
            for (int j = 0; j <= NUMERO_BLOQUES * NUMERO_DIAS; j++) 
                diaHoraSalonOcupado[i][j] = false;
        
        for (int i = 0; i < SEMESTRES; i++) 
            for (int j = 0; j < NUMERO_BLOQUES * NUMERO_DIAS; j++)
                semestreBloquesUsados[i][j] = null;
    }
    private int cantidadSalones() throws SQLException 
    {
        int i = 0, id;
        Connection miConexion;
        miConexion =  ConexionDB.GetConnection();
        Statement st = miConexion.createStatement();
        if(miConexion==null)
        {
            System.out.println("Conexión No Realizada Correctamente");
            return -1;
        }
        
        ResultSet querySalon = st.executeQuery("SELECT * FROM salon");
       
        while(querySalon.next())
            i++;
        return i;
    }
    
    public void setDiaHoraSalonOcupado(int x, int y, boolean value)
    {
        diaHoraSalonOcupado[x][y] = value;
    }
    
    public boolean getDiaHoraSalonOcupado(int x, int y)
    {
        return diaHoraSalonOcupado[x][y];
    }
            
    
}
