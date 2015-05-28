package ColeccionDatos;

public class Salon {
    private int id;
    private String nombre;
    private int tipoSalon;  

    public Salon() {

    }
    public Salon(int id, String nombre, int tipoSalon) {
        this.id = id;
        this.nombre = nombre;
        this.tipoSalon = tipoSalon;
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

    public int getTipoSalon() {
        return tipoSalon;
    }

    public void setTipoSalon(int tipoSalon) {
        this.tipoSalon = tipoSalon;
    }
    
}
