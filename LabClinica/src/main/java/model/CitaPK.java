
package model;

import java.io.Serializable;
import java.util.Date;


@javax.persistence.Embeddable
public class CitaPK implements Serializable {

    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "CIT_IDPACIENTE")
    private int citIdpaciente;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "CIT_FECHA")
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date citFecha;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "CIT_HORA")
    private short citHora;

    public CitaPK() {
    }
    
     public CitaPK(Date fecha, Short hora) {
         
         this.citFecha = fecha;
         this.citHora = hora;
         
    }

    public CitaPK(int citIdpaciente, Date citFecha, short citHora) {
        this.citIdpaciente = citIdpaciente;
        this.citFecha = citFecha;
        this.citHora = citHora;
    }

    public int getCitIdpaciente() {
        return citIdpaciente;
    }

    public void setCitIdpaciente(int citIdpaciente) {
        this.citIdpaciente = citIdpaciente;
    }

    public Date getCitFecha() {
        return citFecha;
    }

    public void setCitFecha(Date citFecha) {
        this.citFecha = citFecha;
    }

    public short getCitHora() {
        return citHora;
    }

    public void setCitHora(short citHora) {
        this.citHora = citHora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) citIdpaciente;
        hash += (citFecha != null ? citFecha.hashCode() : 0);
        hash += (int) citHora;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CitaPK)) {
            return false;
        }
        CitaPK other = (CitaPK) object;
        if (this.citIdpaciente != other.citIdpaciente) {
            return false;
        }
        if ((this.citFecha == null && other.citFecha != null) || (this.citFecha != null && !this.citFecha.equals(other.citFecha))) {
            return false;
        }
        if (this.citHora != other.citHora) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CitaPK[ citIdpaciente=" + citIdpaciente + ", citFecha=" + citFecha + ", citHora=" + citHora + " ]";
    }
    
}
