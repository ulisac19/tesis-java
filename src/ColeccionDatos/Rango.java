package ColeccionDatos;

public class Rango
{
        private int inicio;
        private int fin;

        public Rango() 
        {
        }

        public int getInicio() 
        {
            return inicio;
        }

        public void setInicio(int inicio) 
        {
            this.inicio = inicio;
        }

        public int getFin() 
        {
            return fin;
        }

        public void setFin(int fin) 
        {
            this.fin = fin;
        }
        
        public boolean estaDentroRango(int c)
        {
            return inicio >= c && c <= fin;
        }
        
    }