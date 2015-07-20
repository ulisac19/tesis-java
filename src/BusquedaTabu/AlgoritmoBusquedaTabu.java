package BusquedaTabu;

import AlgortimoEvolutivo.Individuo;
import BaseDatos.ConexionDB;
import ColeccionDatos.Aleatorio;
import ColeccionDatos.Arbol;
import ColeccionDatos.BloqueHorario;
import ColeccionDatos.Informacion;
import ColeccionDatos.Nodo;
import ColeccionDatos.Parametros;
import static ColeccionDatos.Parametros.NUMERO_BLOQUES;
import static ColeccionDatos.Parametros.TIPO_NODO_MATERIA;
import static ColeccionDatos.Parametros.TIPO_NODO_SALON_HORARIO;
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
    
    public AlgoritmoBusquedaTabu(Parametros parametros, Individuo individuoInicial) throws SQLException 
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
    
    public void crearIndividuoAlAzar(Individuo individuo) throws SQLException
    {
        Arbol arbol = individuo.getArbol();
        Aleatorio rnd = new Aleatorio(); 
        int idsalon = 0, idbloque, cantbloque = -1, iddia = 0, iddiaAnterior = -1, iddiaTrasAnterior = -1;
        int  bloq1 = 0, bloq2 = 0, bloq3 = 0, tamBloq;
        Nodo aux, padre_aux;
        boolean bandera;
        
        /* Recorrer materias para asignares horarios y salon */
        for (int i = 0; i < materia.length; i++) 
        {
            bandera = true;
            /* obtener nodo materia */
            padre_aux = arbol.buscar(arbol.getRaiz(), new Informacion(materia[i].getId(), -1, Parametros.TIPO_NODO_MATERIA));
            
            if(materia[i].getTipoMateria() == 1)
            {
                cantbloque = 1;
                bloq1 = 2;
                bloq2 = 0;
                bloq3 = 0;
                
            }
            
            if(materia[i].getTipoMateria() == 2)
            {
                cantbloque = 1;
                bloq1 = 3;
                bloq2 = 0;
                bloq3 = 0;
            } 
            
            if(materia[i].getTipoMateria() == 3)
            {
                cantbloque = 2;
                bloq1 = 2;
                bloq2 = 3;
                bloq3 = 0;
            } 
            
            if(materia[i].getTipoMateria() == 4)
            {
                cantbloque = 3;
                bloq1 = 2;
                bloq2 = 2;
                bloq3 = 2;
            } 
            
            for (int j = 0; j < cantbloque; j++) 
            {
                
                //elegir dia
                if(j == 1)
                    iddiaAnterior = iddia;
                if(j == 2 && bandera)
                {
                    iddiaTrasAnterior = iddiaAnterior;
                    iddiaAnterior = iddia;
                }
                iddia = rnd.getAleatorio(1, 5);
                
                if(materia[i].getTipoMateria() == 2 || materia[i].getTipoMateria() == 3 || materia[i].getTipoMateria() == 4)
                {
                    if(j == 1 && iddia == iddiaAnterior)
                    {
                        j--;
                        continue;
                    }
                    
                }
                
                if(materia[i].getTipoMateria() == 4)
                {                    
                    if(j == 2 && (iddia == iddiaAnterior || iddia == iddiaTrasAnterior) )
                    {
                        bandera = false;
                        j--;
                        continue;
                    }
                    
                }                
               
                
                
            }
            
            boolean ingresoLaboratorio = false;
            for (int k = 0; k < cantbloque; k++) 
            {
                int auxD = 0, idbloquefinal;
                idbloque = rnd.getAleatorio(1, NUMERO_BLOQUES);
                tamBloq = bloqueHorario[idbloque].getId_horaFin() - bloqueHorario[idbloque].getId_horaInicio() + 1;
                
                if(iddia != -1 && k == 0)
                {
                    if(tamBloq != bloq1 && bloq1 > 0)
                    {
                        k--;
                        continue;
                    }
                    auxD = iddia;
                }

                if(iddiaAnterior != -1 && k == 1)
                {
                    if(tamBloq != bloq2 && bloq2 > 0)
                    {
                        k--;
                        continue;
                    }
                    auxD = iddiaAnterior;
                }

                if(iddiaTrasAnterior != -1 && k == 2)
                {
                    if(tamBloq != bloq3 && bloq3 > 0)
                    {
                        k--;
                        continue;
                    }
                    auxD = iddiaTrasAnterior;
                }
                
                    
                    idsalon = rnd.getAleatorio(1, salon.length - 1);
                    
                    if(materia[i].getLaboratorio() == 2 && !ingresoLaboratorio)// requiere laboratorio
                    {
                        if(salon[idsalon].getTipoSalon() != 2)
                        {
                            k--;
                            continue;
                        }else{
                            ingresoLaboratorio = true;
                        }
                    }
                    
                    idbloquefinal = idbloque + ((auxD -1) * NUMERO_BLOQUES);

                    if(!individuo.getDiaHoraSalonOcupado(idsalon,idbloquefinal) && perimetroLibre(idsalon, idbloquefinal, individuo))
                    {
                        aux = new Nodo(new Informacion(idsalon,  idbloquefinal , TIPO_NODO_SALON_HORARIO));            
                        padre_aux.setHijo(aux); 
                        individuo.setDiaHoraSalonOcupado(idsalon,idbloquefinal, true);
                        individuo.setSemestreBloquesUsados(materia[i].getSemestre(), idbloquefinal, bloqueHorario[idbloquefinal]); 
                    }else{
                        k--;
                        continue;
                    }
                 
                       
                    
            }
            
            iddia = iddiaAnterior = iddiaTrasAnterior = -1;
            
        }
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
    
     public void cruzar(Individuo individuo) throws SQLException
    {
        Individuo individuoLocal1 = individuo1;
        Arbol arbol1 = individuoLocal1.getArbol();
                     
        boolean bandera = true;
        Nodo aux1 = null;
        Nodo aux2 = null;
        Nodo auxh1 , auxh2 ;
        
        
        int id_rnd_materia1 = 0;
        int id_rnd_materia2 = 0;
        
        while(bandera)
        {
            id_rnd_materia1 = Aleatorio.getAleatorio(0, materia.length);
            id_rnd_materia2 = Aleatorio.getAleatorio(0, materia.length);
            
            if(materia[id_rnd_materia1].getTipoMateria() == materia[id_rnd_materia2].getTipoMateria() && id_rnd_materia1 != id_rnd_materia2)
               bandera = false;
        }
        
        aux1 =  arbol1.buscar(arbol1.getRaiz(), new Informacion(materia[id_rnd_materia1].getId(), -1, TIPO_NODO_MATERIA ));
        
        
        
        auxh1 = aux1.getHijo();
        auxh2 = aux2.getHijo();
                 
        aux1.eliminarHijo();
        aux2.eliminarHijo();
        
        
        aux1.setHijo(auxh2);
        aux2.setHijo(auxh1);
       
        individuoLocal1.setArbol(arbol1);
        Individuo vector[] = new Individuo[2];
      
        vector[0] = new Individuo(arbol1);
        
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
