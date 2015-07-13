package AlgortimoEvolutivo;

import BaseDatos.ConexionDB;
import ColeccionDatos.Aleatorio;
import ColeccionDatos.Arbol;
import ColeccionDatos.BloqueHorario;
import ColeccionDatos.Informacion;
import ColeccionDatos.Nodo;
import ColeccionDatos.Parametros;
import static ColeccionDatos.Parametros.*;
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
    private int iterador;
    private Parametros parametros;
    //private Arbol arbol;
    private Seccion materia[];
    private Salon salon[];
    private BloqueHorario bloqueHorario[];
    private int cantSalones;
    private int cantBloques;

     public AlgoritmoGenetico(Parametros parametros) throws SQLException
     {
        poblacion = new ArrayList<>();
        listaElite = new ArrayList<>();        
        iterador = 0;
        this.parametros = parametros;
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
    
    public void mutar(Individuo individuoLocal) throws SQLException
    {
       
        Arbol arbolLocal = individuoLocal.getArbol();
        int id_rnd_materia = Aleatorio.getAleatorio(0, materia.length);
        
        int idsalon = 0, idbloque, cantbloque = -1, iddia = 0, iddiaAnterior = -1, iddiaTrasAnterior = -1;
        int  bloq1 = 0, bloq2 = 0, bloq3 = 0, tamBloq;
        int salonViejo, horarioViejo;
        boolean bandera = true, bandera2 = true;
        Nodo aux, padre_aux = null, hijo;
        
        padre_aux = arbolLocal.buscar(arbolLocal.getRaiz(), new Informacion(materia[id_rnd_materia].getId(), -1, Parametros.TIPO_NODO_MATERIA));
        hijo = padre_aux.getHijo();
        while(bandera2)        
        {            
            salonViejo = hijo.getInfo().getDato1();
            horarioViejo = hijo.getInfo().getDato2();
            individuoLocal.setDiaHoraSalonOcupado(salonViejo, horarioViejo, false);
            if( hijo.tieneHermano() )
            hijo = hijo.getHermano();
            {
                bandera2 = false;
                
            }
        }
        padre_aux.eliminarHijo();
        
            if(materia[id_rnd_materia].getTipoMateria() == 1)
            {
                cantbloque = 1;
                bloq1 = 2;
                bloq2 = 0;
                bloq3 = 0;
                
            }
            
            if(materia[id_rnd_materia].getTipoMateria() == 2)
            {
                cantbloque = 1;
                bloq1 = 3;
                bloq2 = 0;
                bloq3 = 0;
            } 
            
            if(materia[id_rnd_materia].getTipoMateria() == 3)
            {
                cantbloque = 2;
                bloq1 = 2;
                bloq2 = 3;
                bloq3 = 0;
            } 
            
            if(materia[id_rnd_materia].getTipoMateria() == 4)
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
                iddia = Aleatorio.getAleatorio(1, NUMERO_DIAS);
                
                if(materia[id_rnd_materia].getTipoMateria() == 2 || materia[id_rnd_materia].getTipoMateria() == 3 || materia[id_rnd_materia].getTipoMateria() == 4)
                {
                    if(j == 1 && iddia == iddiaAnterior)
                    {
                        j--;
                        continue;
                    }
                    
                }
                
                if(materia[id_rnd_materia].getTipoMateria() == 4)
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
                idbloque = Aleatorio.getAleatorio(1, NUMERO_BLOQUES);
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
                
                    
                    idsalon = Aleatorio.getAleatorio(1, salon.length - 1);
                    
                    if(materia[id_rnd_materia].getLaboratorio() == 2 && !ingresoLaboratorio)// requiere laboratorio
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

                    if( !individuoLocal.getDiaHoraSalonOcupado(idsalon, idbloquefinal) && perimetroLibre(idsalon, idbloquefinal, individuoLocal))
                    {
                        aux = new Nodo(new Informacion(idsalon,  idbloquefinal , TIPO_NODO_SALON_HORARIO));            
                        padre_aux.setHijo(aux); 
                        
                        individuoLocal.setDiaHoraSalonOcupado(idsalon, idbloquefinal, true);                         
                        individuoLocal.setSemestreBloquesUsados(materia[id_rnd_materia].getSemestre(), idbloquefinal, bloqueHorario[idbloquefinal]); 
                    }else{
                        k--;
                        continue;
                    }
                 
                       
                    
            }
    }
    
    public void cruzar(Individuo individuo1, Individuo individuo2) throws SQLException
    {
        Individuo individuoLocal1 = individuo1, individuoLocal2 = individuo2;
        Arbol arbol1 = individuoLocal1.getArbol();
        Arbol arbol2 = individuoLocal2.getArbol();
                     
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
        aux2 =  arbol2.buscar(arbol2.getRaiz(), new Informacion(materia[id_rnd_materia2].getId(), -1, TIPO_NODO_MATERIA ));
        
        
        
        auxh1 = aux1.getHijo();
        auxh2 = aux2.getHijo();
                 
        aux1.eliminarHijo();
        aux2.eliminarHijo();
        
        
        aux1.setHijo(auxh2);
        aux2.setHijo(auxh1);
       
        individuoLocal1.setArbol(arbol1);
        individuoLocal2.setArbol(arbol2);
        Individuo vector[] = new Individuo[2];
      
        vector[0] = new Individuo(arbol1);
        vector[1] = new Individuo(arbol2);
        
    }
    
    public void seleccionar(Individuo poblacion[], Individuo listaElite[])
    {
    Individuo aux;
    int a, b;
        for (int i = 0; i < poblacion.length - 2; i++) 
        {
            for (int j = 0; j < poblacion.length - 2; j++) 
            {
                a = poblacion[j].getValorFuncionObjetivo();
                b = poblacion[j + 1].getValorFuncionObjetivo();
                if( a > b )
                {
                    aux = poblacion[j];
                    poblacion[j] = poblacion[j + 1];
                    poblacion[j + 1] = aux; 
                }                
            }
        }
        
        for (int i = 0; i < listaElite.length; i++) 
        {
            listaElite[i] = poblacion[i];            
        }
        
    }
    
  
    public boolean maximoIteraciones(int iteraciones)
    {
        return iteraciones >= parametros.ALGORITMO_GENETICO_CONDICION_PARADA_MAXIMO_ITERACIONES;
    }
    
    public boolean maximoTiempo(int tiempo)
    {
        return tiempo >= parametros.ALGORITMO_GENETICO_CONDICION_PARADA_MAXIMO_SEGUNDOS;
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
    
    public int probabilidades(Parametros parametros) // tabu, cruzar, mutar o dejar pasar
    {
        Random rn = new Random();
        int numeroAlazar =  rn.nextInt(100) + 1;
        int a, b;
        int rsp = ALGORITMO_GENETICO_ID_COPIAR;
        
        Rango mutacion = new Rango();
        Rango cruce = new Rango();
        Rango busqueTabu = new Rango();
        
        a = 1;
        b = a + (int)(parametros.ALGORITMO_GENETICO_PROBABILIDAD_MUTACION*100);
        mutacion.setInicio(a);
        mutacion.setFin(b);
        
        a = b + 1;
        b = a + (int)(parametros.ALGORITMO_GENETICO_PROBABILIDAD_CRUCE*100);
        cruce.setInicio(a);
        cruce.setFin(b);
        
        a = b + 1;
        b = a + (int)(parametros.ALGORITMO_GENETICO_PROBABILIDAD_BUSQUEDA_TABU*100);
        busqueTabu.setInicio(a);
        busqueTabu.setFin(b);
        
        if(mutacion.estaDentroRango(numeroAlazar))
            rsp = Parametros.ALGORITMO_GENETICO_ID_MUTACION;
                    
        if(cruce.estaDentroRango(numeroAlazar))
            rsp = Parametros.ALGORITMO_GENETICO_ID_CRUCE;
                    
        if(busqueTabu.estaDentroRango(numeroAlazar))
            rsp = Parametros.ALGORITMO_GENETICO_ID_BUSQUEDA_TABU;
    
    return rsp;      
    }
    
    
}
