
package model.Dao;

import model.Dto.CitaDto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.CitaPK;


public class CitaDao {

    private Connection connection;

    public CitaDao() {
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

    public void saveCita(CitaDto citaDto) {
        String sql = "INSERT INTO TBL_CITAS (CIT_IDPACIENTE, CIT_FECHA, CIT_HORA) " +
                "VALUES (SEC_CITAS.NEXTVAL, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            //statement.setLong(1, citaDto.getCitDtoId());
//          statement.setString(2, Long.toString(citaDto.getCitId()));
            statement.setDate(1, citaDto.getCitDtoFecha());
            statement.setShort(2, citaDto.getCitDtoHora());
            
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
    
    
     public CitaDto getCitaById(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM TBL_CITAS WHERE CIT_IDPACIENTE = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                CitaDto cita = new CitaDto();
                cita.setCitDtoFecha(resultSet.getDate("CIT_FECHA"));
                cita.setCitDtoHora(Short.valueOf(resultSet.getString("CIT_HORA")));
                
               // paciente.setPacDtoFecNacimiento(new SimpleObjectProperty<>(resultSet.getDate("fecha_nacimiento").toLocalDate()));
                return cita;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
     
    public void actualizarCita(CitaDto citaDto) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE TBL_CITAS SET CIT_FECHA = ?, CIT_HORA = ? WHERE CIT_IDPACIENTE = ?");
            statement.setDate(1, citaDto.getCitDtoFecha());
            statement.setShort(2, citaDto.getCitDtoHora());
            statement.setInt(3, citaDto.getCitDtoId());
     
        
         statement.executeUpdate();
        
            statement.close();

        } catch (SQLException e) {
             e.printStackTrace();
     }
}
     
    public void eliminarCita(int idPaciente) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM TBL_CITAS WHERE CIT_IDPACIENTE = ?");
            statement.setInt(1, idPaciente);
        
            statement.executeUpdate();
        
            statement.close();
        
     } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
       public List<CitaPK> obtenerCitas() {
        List<CitaPK> listaCitas = new ArrayList<>();
        
        try {
            String query = "SELECT * FROM TBL_CITAS ORDER BY CIT_IDPACIENTE";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Date fecha = resultSet.getDate("CIT_FECHA");
                Short hora = resultSet.getShort("CIT_HORA");

                CitaPK citas = new CitaPK(fecha, hora);
                listaCitas.add(citas);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listaCitas;
    }
    
}