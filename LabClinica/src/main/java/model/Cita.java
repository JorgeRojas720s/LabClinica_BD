
package model;

import java.io.Serializable;
import java.util.Date;

@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_CITAS")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c"),
    @javax.persistence.NamedQuery(name = "Cita.findByCitIdpaciente", query = "SELECT c FROM Cita c WHERE c.citaPK.citIdpaciente = :citIdpaciente"),
    @javax.persistence.NamedQuery(name = "Cita.findByCitFecha", query = "SELECT c FROM Cita c WHERE c.citaPK.citFecha = :citFecha"),
    @javax.persistence.NamedQuery(name = "Cita.findByCitHora", query = "SELECT c FROM Cita c WHERE c.citaPK.citHora = :citHora")})
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.EmbeddedId
    protected CitaPK citaPK;
    @javax.persistence.JoinColumn(name = "CIT_IDPACIENTE", referencedColumnName = "PAC_ID", insertable = false, updatable = false)
    @javax.persistence.ManyToOne(optional = false)
    private Paciente paciente;

    public Cita() {
    }

    public Cita(CitaPK citaPK) {
        this.citaPK = citaPK;
    }

    public Cita(int citIdpaciente, Date citFecha, short citHora) {
        this.citaPK = new CitaPK(citIdpaciente, citFecha, citHora);
    }

    public CitaPK getCitaPK() {
        return citaPK;
    }

    public void setCitaPK(CitaPK citaPK) {
        this.citaPK = citaPK;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (citaPK != null ? citaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cita)) {
            return false;
        }
        Cita other = (Cita) object;
        if ((this.citaPK == null && other.citaPK != null) || (this.citaPK != null && !this.citaPK.equals(other.citaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Cita[ citaPK=" + citaPK + " ]";
    }
    
}
