package ColeccionDatos;

public class Seccion {
    private int id;
    private int numero;
    private int materia;
    private int carrera;
    private boolean es_grupo;
    private boolean semestre;

    public Seccion() {
        
    }
    
    public Seccion(int id, int numero, int materia, int carrera, boolean es_grupo, boolean semestre) {
        this.id = id;
        this.numero = numero;
        this.materia = materia;
        this.carrera = carrera;
        this.es_grupo = es_grupo;
        this.semestre = semestre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getMateria() {
        return materia;
    }

    public void setMateria(int materia) {
        this.materia = materia;
    }

    public int getCarrera() {
        return carrera;
    }

    public void setCarrera(int carrera) {
        this.carrera = carrera;
    }

    public boolean isEs_grupo() {
        return es_grupo;
    }

    public void setEs_grupo(boolean es_grupo) {
        this.es_grupo = es_grupo;
    }

    public boolean isSemestre() {
        return semestre;
    }

    public void setSemestre(boolean semestre) {
        this.semestre = semestre;
    }
    
    
}
