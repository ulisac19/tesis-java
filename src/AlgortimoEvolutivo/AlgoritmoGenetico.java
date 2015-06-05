package AlgortimoEvolutivo;

import BaseDatos.ConexionDB;
import ColeccionDatos.Aleatorio;
import ColeccionDatos.Arbol;
import ColeccionDatos.BloqueHorario;
import ColeccionDatos.Informacion;
import ColeccionDatos.Materia;
import ColeccionDatos.Nodo;
import ColeccionDatos.Parametros;
import ColeccionDatos.Rango;
import ColeccionDatos.Salon;
import ColeccionDatos.Seccion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public final class AlgoritmoGenetico 
{ 
    private ArrayList<Individuo> poblacion;
    private ArrayList<Individuo> listaElite;
    private long marcaTiempoFin;
    private long marcaTiempoInicio;
    private int iterador;
    private Parametros parametros;
    private Arbol arbol;
    private Seccion materia[];
    private Salon salon[];
    private BloqueHorario bloqueHorario[];
 
    private int cantSalones;
    private int cantBloques;

     public AlgoritmoGenetico(Parametros parametros, Arbol arbol) throws SQLException{
        poblacion = new ArrayList<>();
        listaElite = new ArrayList<>();
        iterador = 0;
        this.parametros = parametros;
        this.arbol = arbol;
        cargarSalones();
        cargarBloqueHorario();
        cargarMaterias();
        
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
   
    
   
    
    public void agregarIndividuoPoblacion(Individuo individuo){}
    
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
            materia[j] = new Seccion(id, numero, materia_id, carrera, grupo, semestre, tipoMateria);  
         j++;   
        }
       
        
    }
    
    public Individuo crearIndividuoAlAzar() throws SQLException
    {
        Individuo individuo = null;
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
                // elegir salon
                idsalon = rnd.getAleatorio(1, this.salon.length);
                
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
                
                //elegir bloque                
                
                
                
            }
            
            for (int k = 0; k < cantbloque; k++) 
            {
                int auxD = 0;
                idbloque = rnd.getAleatorio(1, 36);
                tamBloq = bloqueHorario[idbloque-1].getId_horaFin() - bloqueHorario[idbloque-1].getId_horaInicio() + 1;
                
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
                
            aux = new Nodo(new Informacion(auxD, idbloque + ((auxD -1) *36), Parametros.TIPO_NODO_SALON_HORARIO));
            padre_aux.setHijo(aux); 
                
            }
            
            iddia = iddiaAnterior = iddiaTrasAnterior = -1;
            
            
            
        }
        
        return individuo;
    }
    
   
    
    public void mutar(Individuo individuo){}
    public void cruzar(Individuo individuo1, Individuo individuo2)
    {
    
    }
    
    public void selecccionar(ArrayList<Individuo> poblacion, ArrayList<Individuo> listaElite)
    {
        
    }
    
    public boolean maximoIteraciones(int iteraciones)
    {
        return iteraciones >= parametros.ALGORTIMO_GENETICO_CONDICION_PARADA_MAXIMO_ITERACIONES;
    }
    
    public boolean maximoTiempo(int tiempo)
    {
        return tiempo >= parametros.ALGORTIMO_GENETICO_CONDICION_PARADA_MAXIMO_SEGUNDOS;
    }
    
    public boolean nivelOptimo() //mucho tiempo sin mejora significativamente
    {
        return false;
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
        setCantSalones(i);
        salon = new Salon[getCantSalones()];
        querySalon = st.executeQuery("SELECT * FROM salon");
        i = 0;
        while(querySalon.next())
        {
            id = (int)(querySalon.getObject("id"));
            nombre =  (String)(querySalon.getObject("nombre"));
            tipoSalon_id =  (int)(querySalon.getObject("tipoSalon_id")); 
            salon[i] = new Salon(id,nombre , tipoSalon_id);
        i++;
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
        setCantBloques(i);
        bloqueHorario = new BloqueHorario[getCantBloques()];
        
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
        }
    }
    
    public void iterar() throws SQLException
    {
        Individuo individuoInicial = crearIndividuoAlAzar();        
        marcaTiempoInicio = System.currentTimeMillis() / 1000L;
        Integer idOperadorGenetico;

        
        while(maximoIteraciones(iterador) || maximoTiempo((int)(marcaTiempoFin - marcaTiempoInicio)) || nivelOptimo())
        {
            
            /* Seleccionar operador genetico a aplicar */
            idOperadorGenetico = probabilidades();
            
            if(idOperadorGenetico.equals(Parametros.ALGORTIMO_GENETICO_ID_MUTACION))
            {
                mutar(poblacion.get(iterador));
            }
            
            if(idOperadorGenetico.equals(Parametros.ALGORTIMO_GENETICO_ID_CRUCE))
            {
                
            }
            
            if(idOperadorGenetico.equals(Parametros.ALGORTIMO_GENETICO_ID_BUSQUEDA_TABU))
            {
            }
            

            iterador++;
            marcaTiempoFin = System.currentTimeMillis() / 1000L;
        }
        
        
    }
    
    public int probabilidades() // tabu, cruzar, mutar o dejar pasar
    {
        Random rn = new Random();
        int numeroAlazar = rn.nextInt(100) + 1;
        int a, b;
        int rsp = Parametros.ALGORTIMO_GENETICO_ID_COPIAR;
        
        Rango mutacion = new Rango();
        Rango cruce = new Rango();
        Rango busqueTabu = new Rango();
        
        a = 1;
        b = a + (int)parametros.ALGORTIMO_GENETICO_PROBABILIDAD_MUTACION*100;
        mutacion.setInicio(a);
        mutacion.setFin(b);
        
        a = b + 1;
        b = a + (int)parametros.ALGORTIMO_GENETICO_PROBABILIDAD_CRUCE*100;
        cruce.setInicio(a);
        cruce.setFin(b);
        
        a = b + 1;
        b = a + (int)parametros.ALGORTIMO_GENETICO_PROBABILIDAD_BUSQUEDA_TABU*100;
        busqueTabu.setInicio(a);
        busqueTabu.setFin(b);
        
        if(mutacion.estaDentroRango(numeroAlazar))
            rsp = Parametros.ALGORTIMO_GENETICO_ID_MUTACION;
                    
        if(cruce.estaDentroRango(numeroAlazar))
            rsp = Parametros.ALGORTIMO_GENETICO_ID_CRUCE;
                    
        if(busqueTabu.estaDentroRango(numeroAlazar))
            rsp = Parametros.ALGORTIMO_GENETICO_ID_BUSQUEDA_TABU;
    
    return rsp;      
    }
    
   
   
    
}
