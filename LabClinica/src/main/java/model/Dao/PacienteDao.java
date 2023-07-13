
package model.Dao;

import model.Dto.PacienteDto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Paciente;

public class PacienteDao {

    private Connection connection;

    public PacienteDao() {
        // Initialize the database connection
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String username = "JRojas";
            String password = "Palomo_123";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePaciente(PacienteDto paciente) {
        String sql = "INSERT INTO TBL_PACIENTES (PAC_ID, PAC_CEDULA, PAC_NOMBRE, PAC_PAPELLIDO,"
                + "PAC_SAPELLIDO , PAC_DIRECCION, PAC_FECNACIMIENTO) " +
                "VALUES (SEC_PACIENTES.NEXTVAL, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
           // statement.setLong(1, paciente.getPacDtoId());
            statement.setString(1, paciente.getPacDtoCedula());
            statement.setString(2, paciente.getPacDtoNombre());
            statement.setString(3, paciente.getPacDtoPApellido());
            statement.setString(4, paciente.getPacDtoSApellido());
            statement.setString(5, paciente.getPacDtoDireccion());
            statement.setDate(6, (Date)paciente.getPacDtoFecNacimiento());
            
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public PacienteDto getPacienteByCedula(String cedula) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM TBL_PACIENTES WHERE PAC_CEDULA = ?");
            statement.setString(1, cedula);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                PacienteDto paciente = new PacienteDto();
                paciente.setPacDtoId(Integer.valueOf(resultSet.getString("PAC_ID")));
                paciente.setPacDtoCedula(resultSet.getString("PAC_CEDULA"));
                paciente.setPacDtoNombre(resultSet.getString("PAC_NOMBRE"));
                paciente.setPacDtoPApellido(resultSet.getString("PAC_PAPELLIDO"));
                paciente.setPacDtoSApellido(resultSet.getString("PAC_SAPELLIDO"));
                paciente.setPacDtoDireccion(resultSet.getString("PAC_DIRECCION"));
               // paciente.setPacDtoFecNacimiento(resultSet.getDate("fecha_nacimiento"));
                return paciente;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    
    public void actualizarPaciente(PacienteDto pacienteDto) {
    try {
        PreparedStatement statement = connection.prepareStatement("UPDATE TBL_PACIENTES SET PAC_NOMBRE = ?, PAC_PAPELLIDO = ?, PAC_SAPELLIDO = ?, PAC_DIRECCION = ? WHERE PAC_ID = ?");
        statement.setString(1, pacienteDto.getPacDtoNombre());
        statement.setString(2, pacienteDto.getPacDtoPApellido());
        statement.setString(3, pacienteDto.getPacDtoSApellido());
        statement.setString(4, pacienteDto.getPacDtoDireccion());
        statement.setInt(5, pacienteDto.getPacDtoId());
        
        statement.executeUpdate();
        
        statement.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    public void eliminarPaciente(int idPaciente) {
    try {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM TBL_PACIENTES WHERE PAC_ID = ?");
        
        statement.setInt(1, idPaciente);
        
        statement.executeUpdate();
        
        statement.close();
       
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
   
    
    public List<Paciente> obtenerPacientes() {
        List<Paciente> listaPacientes = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM TBL_PACIENTES ORDER BY PAC_ID";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String cedula = resultSet.getString("PAC_CEDULA");
                String nombre = resultSet.getString("PAC_NOMBRE");
                String pApellido = resultSet.getString("PAC_PAPELLIDO");
                String sApellido = resultSet.getString("PAC_SAPELLIDO");

                Paciente paciente = new Paciente(cedula, nombre,pApellido,sApellido);
                listaPacientes.add(paciente);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listaPacientes;
    }
    
}
