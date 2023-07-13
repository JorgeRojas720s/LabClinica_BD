package model.Dto;

import java.sql.Date;
import java.util.Objects;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import model.Paciente;

public class PacienteDto {
    
    public SimpleStringProperty pacId;
    public SimpleStringProperty pacNombre;
    public SimpleStringProperty pacPApellido;
    public SimpleStringProperty pacSApellido;
    public SimpleStringProperty pacCedula;
    public SimpleStringProperty pacDireccion;
    public SimpleObjectProperty pacFecNacimiento;
    
    public Boolean modificador;
    
    public PacienteDto(){
        this.modificador = false;
        this.pacId = new SimpleStringProperty();
        this.pacNombre = new SimpleStringProperty();
        this.pacPApellido = new SimpleStringProperty();
        this.pacSApellido = new SimpleStringProperty();
        this.pacCedula = new SimpleStringProperty();
        this.pacDireccion = new SimpleStringProperty();
        this.pacFecNacimiento = new SimpleObjectProperty();
    }
    
    public PacienteDto(Paciente paciente) {
        this();
       // this.pacId.set(paciente.getPacId().toString());
        this.pacNombre.set(paciente.getPacNombre());
        this.pacPApellido.set(paciente.getPacPapellido());
        this.pacSApellido.set(paciente.getPacSapellido());
        this.pacCedula.set(paciente.getPacCedula());
        this.pacDireccion.set(paciente.getPacDireccion());
        this.pacFecNacimiento.set(paciente.getPacFecNacimiento());
    }
    
    public Integer getPacDtoId() {
        if(pacId.get()!=null && !pacId.get().isEmpty())
            return Integer.valueOf(pacId.get());
        else
            return null;
    }
    
    public void setPacDtoId(Integer pacId) {
        this.pacId.set(pacId.toString());
    }
    
    public String getPacDtoNombre() {
        return pacNombre.get();
    }
    
    public void setPacDtoNombre(String pacNombre) {
        this.pacNombre.set(pacNombre);
    }
    
    public String getPacDtoPApellido() {
        return pacPApellido.get();
    }
    
    public void setPacDtoPApellido(String pacPApellido) {
        this.pacPApellido.set(pacPApellido);
    }
    
    public String getPacDtoSApellido() {
        return pacSApellido.get();
    }
    
    public void setPacDtoSApellido(String pacSApellido) {
        this.pacSApellido.set(pacSApellido);
    }
    
    public String getPacDtoCedula() {
        return pacCedula.get();
    }
    
    public void setPacDtoCedula(String pacCedula) {
        this.pacCedula.set(pacCedula);
    }
    
    public String getPacDtoDireccion() {
        return pacDireccion.get();
    }
    
    public void setPacDtoDireccion(String pacDireccion) {
        this.pacDireccion.set(pacDireccion);
    }
    
    public Date getPacDtoFecNacimiento(){
        return (Date) pacFecNacimiento.get();
    }
    
    public void setPacDtoFecNacimiento(Date pacFecha) {
        this.pacFecNacimiento.set(pacFecha);
    }
    
    public Boolean getCitDtoModificador() {
        return modificador;
    }

    public void setCitDtoModificador(Boolean modificador) {
        this.modificador = modificador;
    }
    
 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.pacId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PacienteDto other = (PacienteDto) obj;
        /*if (other.id.get() == null) {
            return false;
        }*/
        return Objects.equals(this.pacId.get(), other.pacId.get());
    }

    @Override
    public String toString() {
        return "PacienteDto{" + "pacId=" + pacId +  '}';
    }


}
