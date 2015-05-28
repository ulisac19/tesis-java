package ColeccionDatos;

public class Materia {
    private int id;
    private String nombre;
    private String codigo;
    private int UC;
    private int departamento;
    private int laboratorio;

    public Materia() {
        
    }
    
    public Materia(int id, String nombre, String codigo, int UC, int departamento, int laboratorio) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.UC = UC;
        this.departamento = departamento;
        this.laboratorio = laboratorio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getUC() {
        return UC;
    }

    public void setUC(int UC) {
        this.UC = UC;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public int getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(int laboratorio) {
        this.laboratorio = laboratorio;
    }
    
    
}
