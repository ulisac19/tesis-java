package BusquedaTabu;

import AlgortimoEvolutivo.Individuo;
import BaseDatos.ConexionDB;
import ColeccionDatos.Arbol;
import ColeccionDatos.BloqueHorario;
import ColeccionDatos.Parametros;
import ColeccionDatos.Salon;
import ColeccionDatos.Seccion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlgoritmoBusquedaTabu 
{
    
    private ArrayList<Individuo> poblacion;
    private ArrayList<Individuo> listaElite;
    private Individuo listaTabu[];
    private long marcaTiempoFin;
    private long marcaTiempoInicio;
    private int iterador;
    private Parametros parametros;
   
    private Seccion materia[];
    private Salon salon[];
    private BloqueHorario bloqueHorario[];
    private int cantSalones;
    private int cantBloques;
    
    public AlgoritmoBusquedaTabu(Parametros parametros) throws SQLException 
    {
        poblacion = new ArrayList<>();
        listaElite = new ArrayList<>();        
        iterador = 0;
        this.parametros = parametros;
        this.listaTabu = new Individuo[parametros.ALGORITMO_TABU_CANTIDAD_LISTA_ELITE];
        
        cargarSalones();
        cargarBloqueHorario();
        cargarMaterias();     
    }
    
    private void cargarBloqueHorario() throws SQLException 
    {   
        int i = 0, id, id_horaInicio, id_horaFin, id_dia;
        
        Connection miConexion;
        miConexion =  ConexionDB.GetConnection();
        Statement st = miConexion.createStatement();
        if(miConexion==null)
        {
            System.out.println("Conexión No Realizada Correctamente");
            return;
        }
        
        ResultSet queryBloqueHorario = st.executeQuery("SELECT * FROM bloquehorario");
       
        while(queryBloqueHorario.next())
            i++;
        setCantBloques(1+i);
        bloqueHorario = new BloqueHorario[getCantBloques()];
        
        queryBloqueHorario = st.executeQuery("SELECT * FROM bloquehorario");
        i = 1;
        while(queryBloqueHorario.next())
        {
            id = (int)(queryBloqueHorario.getObject("id"));
            id_horaInicio =  (int)(queryBloqueHorario.getObject("hora_inicio"));
            id_horaFin =  (int)(queryBloqueHorario.getObject("hora_fin"));
            id_dia =  (int)(queryBloqueHorario.getObject("dia_id")); 
            bloqueHorario[i] = new BloqueHorario(id, id_horaInicio, id_horaFin, id_dia);
        i++;
        }
    miConexion.close();
    
    } 
    
    private void cargarSalones() throws SQLException
    {   
        int i = 0, id, tipoSalon_id;
        String nombre;
        Connection miConexion;
        miConexion =  ConexionDB.GetConnection();
        Statement st = miConexion.createStatement();
        if(miConexion==null)
        {
            System.out.println("Conexión No Realizada Correctamente");
            return;
        }
        
        ResultSet querySalon = st.executeQuery("SELECT * FROM salon");
       
        while(querySalon.next())
            i++;
        setCantSalones(1 + i);
        salon = new Salon[getCantSalones()];
        querySalon = st.executeQuery("SELECT * FROM salon");
        i = 1;
        while(querySalon.next())
        {
            id = (int)(querySalon.getObject("id"));
            nombre =  (String)(querySalon.getObject("nombre"));
            tipoSalon_id =  (int)(querySalon.getObject("tipoSalon_id")); 
            salon[i] = new Salon(id,nombre , tipoSalon_id);
        i++;
        }
    miConexion.close();    
    }
    
    private boolean perimetroLibre(int idsalon, int idbloque, Individuo i) throws SQLException
    {
        Connection miConexion;
        miConexion =  ConexionDB.GetConnection();
        Statement st = miConexion.createStatement();
        int idaux;
        boolean band = true; 
        String sql = "SELECT * "
                   + "FROM bloquehorario "
                   + "WHERE "
                        + "dia_id = "+bloqueHorario[idbloque].getId_dia()+" AND ("+
                          "(hora_inicio >= " + bloqueHorario[idbloque].getId_horaInicio() + " AND  hora_inicio <=" + bloqueHorario[idbloque].getId_horaFin()+" ) OR ( "+
                          "hora_fin >= " + bloqueHorario[idbloque].getId_horaInicio() + " AND  hora_fin <=" + bloqueHorario[idbloque].getId_horaFin()+ "))";
        
        ResultSet queryMateria = st.executeQuery(sql);
        
        while(band && queryMateria.next())
        {
            idaux = (int)queryMateria.getObject("id");
            band = band || ! i.getDiaHoraSalonOcupado(idsalon,idaux);               
        }
        miConexion.close();
        return band; 
    }
    
    public int getCantSalones() {
        return cantSalones;
    }

    public void setCantSalones(int cantSalones) {
        this.cantSalones = cantSalones;
    }

    public int getCantBloques() {
        return cantBloques;
    }

    public void setCantBloques(int cantBloques) {
        this.cantBloques = cantBloques;
    }
    public void cargarMaterias() throws SQLException
    {   
        Connection miConexion;
        miConexion =  ConexionDB.GetConnection();
        Statement st = miConexion.createStatement();
        ResultSet queryMateria = st.executeQuery("SELECT * FROM seccion"),queryMateria2 ;
        
        int i = 0, j = 0;
        boolean grupo;
        while (queryMateria.next()) 
            i++;

        materia = new Seccion[i];
      
        queryMateria2 = st.executeQuery("SELECT * FROM seccion, materia where materia.id = seccion.materia_id");
        while (queryMateria2.next()) 
        {
            int gr = (int)queryMateria2.getObject("seccion.es_grupo");
            grupo = gr == 1;
            int id = (int)queryMateria2.getObject("seccion.id");
            int numero =(int)queryMateria2.getObject("seccion.numero");
            int materia_id = (int)queryMateria2.getObject("seccion.materia_id");
            int carrera = (int)queryMateria2.getObject("seccion.carrera_id");
            int semestre = (int)queryMateria2.getObject("seccion.semestre");
            int tipoMateria = (int)queryMateria2.getObject("materia.unidades_credito");
            int laboratorio = (int)queryMateria2.getObject("materia.requiere_laboratorio");            
            materia[j] = new Seccion(id, numero, materia_id, carrera, grupo, semestre, tipoMateria, laboratorio);  
         j++;   
        }
       
    miConexion.close();    
    }
    
}
