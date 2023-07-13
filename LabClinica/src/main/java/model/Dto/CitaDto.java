package model.Dto;

import java.sql.Date;
import java.util.Objects;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import model.CitaPK;

public class CitaDto {
    
    public SimpleStringProperty citId;
    public SimpleStringProperty citHora;
    public SimpleObjectProperty citFecha;
    
    public Boolean modificador;
    
    public CitaDto(){
        this.modificador = false;
        this.citId = new SimpleStringProperty();
        this.citHora = new SimpleStringProperty();
        this.citFecha = new SimpleObjectProperty();
    }
    
    public CitaDto(CitaPK cita) {
        this();
       
       // this.citId.set(String.valueOf(cita.getCitIdpaciente()));
        
        this.citHora.set(String.valueOf(cita.getCitHora()));
        
        this.citFecha.set(cita.getCitFecha());
    }
    
    public Integer getCitDtoId() {
        if(citId.get()!=null && !citId.get().isEmpty())
            return Integer.valueOf(citId.get());
        else
            return null;
    }
    
    public void setCitDtoId(Integer pCitId) {
        this.citId.set(pCitId.toString());
    }
    
    public Short getCitDtoHora() {
        return Short.valueOf(citHora.get());
    }
    
    public void setCitDtoHora(Short citHora) {
        this.citHora.set(citHora.toString());
    }
    
    public Date getCitDtoFecha(){
        return (Date) citFecha.get();
    }
    
    public void setCitDtoFecha(Date citFecha) {
        this.citFecha.set(citFecha);
    }
    
    public Boolean getCitDtoModificador() {
        return modificador;
    }

    public void setCitDtoModificador(Boolean modificador) {
        this.modificador = modificador;
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
        final CitaDto other = (CitaDto) obj;
        /*if (other.id.get() == null) {
            return false;
        }*/
        return Objects.equals(this.citId.get(), other.citId.get());
    }

    @Override
    public String toString() {
        return "CitasDto{" + "citId=" + citId + '}';
    }
}