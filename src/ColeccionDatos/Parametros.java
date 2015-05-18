package ColeccionDatos;


public class Parametros {
    
    static public int TIPO_NODO_RAIZ = 1;
    static public int TIPO_NODO_PROFESOR = 2;
    static public int TIPO_NODO_MATERIA = 3;
    static public int TIPO_NODO_SALON_HORARIO = 4;
    static public int TIPO_NODO_INDEFINIDO = -1;
    
    static public int ALGORTIMO_GENETICO_MAXIMO_NUMERO_GENERACION = 100;
    static public double ALGORTIMO_GENETICO_PROBABILIDAD_MUTACION = 0.1;
    static public int ALGORTIMO_GENETICO_ID_MUTACION = 1;
    static public double ALGORTIMO_GENETICO_PROBABILIDAD_CRUCE = 0.1;
    static public int ALGORTIMO_GENETICO_ID_CRUCE = 2;
    static public double ALGORTIMO_GENETICO_PROBABILIDAD_BUSQUEDA_TABU =  0.1;
    static public int ALGORTIMO_GENETICO_ID_BUSQUEDA_TABU =  3;
    static public int ALGORTIMO_GENETICO_ID_COPIAR =  5;
    static public double ALGORTIMO_GENETICO_PORCENTAJE_LISTA_ELITE =  0.1;
    static public int ALGORTIMO_GENETICO_POBLACION_INICIAL =  100;
    static public int ALGORTIMO_GENETICO_CANTIDAD_LISTA_ELITE =  (int) (Parametros.ALGORTIMO_GENETICO_POBLACION_INICIAL * Parametros.ALGORTIMO_GENETICO_PORCENTAJE_LISTA_ELITE);
    static public int ALGORTIMO_GENETICO_CONDICION_PARADA_MAXIMO_ITERACIONES =  1000;
    static public int ALGORTIMO_GENETICO_CONDICION_PARADA_MAXIMO_SEGUNDOS =  60;
    
    
}
