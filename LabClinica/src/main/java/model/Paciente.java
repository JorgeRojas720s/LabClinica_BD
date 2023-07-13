
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@javax.persistence.Entity
@javax.persistence.Table(name = "TBL_PACIENTES")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @javax.persistence.NamedQuery(name = "Paciente.findByPacId", query = "SELECT p FROM Paciente p WHERE p.pacId = :pacId"),
    @javax.persistence.NamedQuery(name = "Paciente.findByPacCedula", query = "SELECT p FROM Paciente p WHERE p.pacCedula = :pacCedula"),
    @javax.persistence.NamedQuery(name = "Paciente.findByPacNombre", query = "SELECT p FROM Paciente p WHERE p.pacNombre = :pacNombre"),
    @javax.persistence.NamedQuery(name = "Paciente.findByPacPapellido", query = "SELECT p FROM Paciente p WHERE p.pacPapellido = :pacPapellido"),
    @javax.persistence.NamedQuery(name = "Paciente.findByPacSapellido", query = "SELECT p FROM Paciente p WHERE p.pacSapellido = :pacSapellido"),
    @javax.persistence.NamedQuery(name = "Paciente.findByPacDireccion", query = "SELECT p FROM Paciente p WHERE p.pacDireccion = :pacDireccion"),
    @javax.persistence.NamedQuery(name = "Paciente.findByPacFecnacimiento", query = "SELECT p FROM Paciente p WHERE p.pacFecnacimiento = :pacFecnacimiento")})

public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    //SECUENCIA EN CITAS
    @Id
    @SequenceGenerator(name = "PACIENTES_ID_GENERATOR", sequenceName = "SEC_PACIENTES", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_PACIENTES")
    @Basic(optional = false)
    private Integer pacId;
    // FIN SECUENCIA CITAS
  
    
    
    @javax.persistence.Column(name = "PAC_CEDULA")
    private String pacCedula;
    @javax.persistence.Column(name = "PAC_NOMBRE")
    private String pacNombre;
    @javax.persistence.Column(name = "PAC_PAPELLIDO")
    private String pacPapellido;
    @javax.persistence.Column(name = "PAC_SAPELLIDO")
    private String pacSapellido;
    @javax.persistence.Column(name = "PAC_DIRECCION")
    private String pacDireccion;
    @javax.persistence.Column(name = "PAC_FECNACIMIENTO")
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date pacFecnacimiento;
    @javax.persistence.OneToMany(cascade = javax.persistence.CascadeType.ALL, mappedBy = "paciente")
    private Collection<Cita> citaCollection;

    public Paciente() {
    }
    
    public Paciente(String cedula, String nombre,String pApellido,String sApellido) {
        
        this.pacCedula = cedula;
        this.pacNombre = nombre;
        this.pacPapellido = pApellido;
        this.pacSapellido = sApellido;   
    }

    public Paciente(Integer pacId) {
        this.pacId = pacId;
    }

    public Integer getPacId() {
        return pacId;
    }

    public void setPacId(Integer pacId) {
        this.pacId = pacId;
    }

    public String getPacCedula() {
        return pacCedula;
    }

    public void setPacCedula(String pacCedula) {
        this.pacCedula = pacCedula;
    }

    public String getPacNombre() {
        return pacNombre;
    }

    public void setPacNombre(String pacNombre) {
        this.pacNombre = pacNombre;
    }

    public String getPacPapellido() {
        return pacPapellido;
    }

    public void setPacPapellido(String pacPapellido) {
        this.pacPapellido = pacPapellido;
    }

    public String getPacSapellido() {
        return pacSapellido;
    }

    public void setPacSapellido(String pacSapellido) {
        this.pacSapellido = pacSapellido;
    }

    public String getPacDireccion() {
        return pacDireccion;
    }

    public void setPacDireccion(String pacDireccion) {
        this.pacDireccion = pacDireccion;
    }

    public Date getPacFecNacimiento() {
        return pacFecnacimiento;
    }

    public void setPacFecNacimiento(Date pacFecnacimiento) {
        this.pacFecnacimiento = pacFecnacimiento;
    }

    public Collection<Cita> getCitaCollection() {
        return citaCollection;
    }

    public void setCitaCollection(Collection<Cita> citaCollection) {
        this.citaCollection = citaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pacId != null ? pacId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.pacId == null && other.pacId != null) || (this.pacId != null && !this.pacId.equals(other.pacId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Paciente[ pacId=" + pacId + " ]";
    }
    
}
