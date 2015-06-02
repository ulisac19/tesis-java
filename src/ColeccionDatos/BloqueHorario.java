package ColeccionDatos;

public class BloqueHorario {
    private int id;
    private int id_horaInicio;
    private int id_horaFin;
    private int id_dia;
    private int canthoras;

  
    public BloqueHorario() {
      
    }
    
    public BloqueHorario(int id, int id_horaInicio, int id_horaFin, int id_dia) {
        this.id = id;
        this.id_horaInicio = id_horaInicio;
        this.id_horaFin = id_horaFin;
        this.id_dia = id_dia;
        this.canthoras = ( id_horaFin - id_horaInicio );
    }

    public int getCanthoras() {
        return canthoras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_horaInicio() {
        return id_horaInicio;
    }

    public void setId_horaInicio(int id_horaInicio) {
        this.id_horaInicio = id_horaInicio;
    }

    public int getId_horaFin() {
        return id_horaFin;
    }

    public void setId_horaFin(int id_horaFin) {
        this.id_horaFin = id_horaFin;
    }

    public int getId_dia() {
        return id_dia;
    }

    public void setId_dia(int id_dia) {
        this.id_dia = id_dia;
    }
    
}
