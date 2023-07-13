package com.mycompany.labclinica;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CitaPK;
import model.Dao.CitaDao;
import model.Dao.PacienteDao;
import model.Dto.CitaDto;
import model.Dto.PacienteDto;
import model.Paciente;

public class PrimaryController {
    
    private int id;
       
    PacienteDao pacienteDao = new PacienteDao();
    
    CitaDao citaDao = new CitaDao();
 
    Paciente paciente;
    
    @FXML
    private TextField cedula;
    @FXML
    private TextField nombre;
    @FXML
    private TextField pApellido;
    @FXML
    private TextField sApellido;
    @FXML
    private TextArea direccion;
    @FXML
    private DatePicker fechaNac;
    @FXML
    private Button save;
    @FXML
    private DatePicker dia;
    @FXML
    private ComboBox hora;
    @FXML
    private Button gurdar2;
    @FXML
    private Label nombrePaciente;
    @FXML
    private TabPane paneTab;
    @FXML
    private Button buscar;
    @FXML
    private TextField cedulaPaciente;
    @FXML
    private TextField pacNombre;
    @FXML
    private TextField pacPApellido;
    @FXML
    private TextField pacSApellido;
    @FXML
    private TextArea pacDireccion;
    @FXML
    private TextField pacFecCita;
    @FXML
    private TextField pacHoraCita;
    @FXML
    private Button modificar;
    @FXML
    private Button Eliminar;
    @FXML
    private TableColumn<CitaPK, String> colmFecha;
    @FXML
    private TableColumn<CitaPK, String> colmHora;
    @FXML
    private TableColumn<Paciente, String> colmCedula;
    @FXML
    private TableColumn<Paciente, String> colmNombre;
    @FXML
    private TableColumn<Paciente, String> colmPApellido;
    @FXML
    private TableColumn<Paciente, String> colmSApellido;
    @FXML
    private TableView<Paciente> tableView;
    @FXML
    private TableView<CitaPK> tableView2;
   
    public void initialize(){ 
        
        fillTable();
        
        setFecNac();
        
        citaDataNull();
 
        ObservableList<String> listaDeHoras = FXCollections.observableArrayList(
        "7 a.m","8 a.m","9 a.m","10 a.m","11 a.m","1 p.m","2 p.m","3 p.m","4 p.m","5 p.m");
        
        hora.setItems(listaDeHoras);

        actualizarTablaPacientes();
        actualizarTablaCitas();

        tableView.setSortPolicy((TableView<Paciente> tableView01) -> false);  
        tableView2.setSortPolicy((TableView<CitaPK> tableView02) -> false);   
    }    
    
    public void fillTable(){
        
        colmFecha.setCellValueFactory(new PropertyValueFactory<>("citFecha"));
        colmHora.setCellValueFactory(new PropertyValueFactory<>("citHora"));
        
        colmCedula.setCellValueFactory(new PropertyValueFactory<>("pacCedula"));
        colmNombre.setCellValueFactory(new PropertyValueFactory<>("pacNombre"));
        colmPApellido.setCellValueFactory(new PropertyValueFactory<>("pacPapellido"));
        colmSApellido.setCellValueFactory(new PropertyValueFactory<>("pacSapellido"));
    
    }
    
    public void resetSpaces(){
        
    cedula.setText(null);
    nombre.setText(null);
    pApellido.setText(null);
    sApellido.setText(null);
    direccion.setText(null);
    setFecNac();
    citaDataNull();
 
    }
    
    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }  
    
    public void setFecNac(){

            LocalDate aux = LocalDate.now();       
            
            fechaNac.setValue(aux);   
    }
    
    public boolean patientDataNull(){
        
        if(cedula.getText().isEmpty()){
            
            showAlert("Olvidate el campo <Cedula>");     
            return false;
        }
        else if(nombre.getText().isEmpty()){
            
            showAlert("Olvidate el campo <Nombre>");          
        }    
        else if(pApellido.getText().isEmpty()){
            
            showAlert("Olvidate el campo <Primer Apellido>");
            return false;
        }  
        else if(sApellido.getText().isEmpty()){
            
            showAlert("Olvidate el campo <Segundo Apellido>");  
            return false;
        } 
        else if(direccion.getText().isEmpty()){
            
            showAlert("Olvidate el campo <Direccion>"); 
            
            return false;
        }  
      return true;
    }
    
    public void citaDataNull(){
        
        LocalDate aux1 = LocalDate.now(); 
        
        LocalTime aux2 = LocalTime.now();
        
        String x;
        
        x = (aux2.getHour() >= 12) ? "p.m" : "a.m";
          
        
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH ");
     
        dia.setValue(aux1);   
        
        hora.setValue(aux2.format(timeFormatter) + x);
    }
    
    
    public void guardarPaciente(){
        
        paciente = new Paciente();

        paciente.setPacCedula(cedula.getText());
        
        paciente.setPacNombre(nombre.getText());
        
        paciente.setPacPapellido(pApellido.getText());
        
        paciente.setPacSapellido(sApellido.getText());
        
        paciente.setPacDireccion(direccion.getText());
        
        paciente.setPacFecNacimiento(Date.valueOf(fechaNac.getValue().toString()));
        
        PacienteDto pacienteDto = new PacienteDto(paciente);
                
        pacienteDao = new PacienteDao();
        
        pacienteDao.savePaciente(pacienteDto);
        
        pacienteDao.closeConnection();
        
        System.out.println("paciente gaardado");

    }

    @FXML
    private void saveData(ActionEvent event) throws IOException {

        if(event.getSource() == save){
            
        nombrePaciente.setText(nombre.getText() + " " + pApellido.getText() + " " + sApellido.getText());
            
        if(patientDataNull() == true){
 
          paneTab.getSelectionModel().select(1);
        }
     
        }
    }    
    
      @FXML
    private void guardarCita(ActionEvent event) {
        
        if(event.getSource() == gurdar2){
     
        guardarPaciente();  

        CitaPK cita = new CitaPK();   
         
        cita.setCitFecha(Date.valueOf(dia.getValue()));
         
        String aux = String.valueOf(hora.getSelectionModel().getSelectedItem());
         
        int n = aux.indexOf(" ");

        aux =  aux.substring(0, n);

        cita.setCitHora(Short.parseShort(aux));
         
        CitaDto citadto = new CitaDto(cita);
        
        citaDao = new CitaDao();
         
        citaDao.saveCita(citadto);
        
        citaDao.closeConnection();
        
        System.out.println("Cita gaardada");
        
        actualizarTablaPacientes();
        actualizarTablaCitas();
        
        resetSpaces();
        
        paneTab.getSelectionModel().select(0);
  
        }   
    }
 
    @FXML
    private void buscarPaciente(ActionEvent event) {
        if (event.getSource() == buscar) {
            pacienteDao = new PacienteDao();
            citaDao = new CitaDao();
            String idPaciente = cedulaPaciente.getText();
            if (!idPaciente.isEmpty()) {
                System.out.println("Holaaaaaaaaaaa");
                 //pacienteDao = new PacienteDao();
                PacienteDto pacienteDto = pacienteDao.getPacienteByCedula(idPaciente);
                if (pacienteDto != null) {
                    System.out.println("kkkk");
                    id = pacienteDto.getPacDtoId();
                    pacNombre.setText(pacienteDto.getPacDtoNombre());
                    pacPApellido.setText(pacienteDto.getPacDtoPApellido());
                    pacSApellido.setText(pacienteDto.getPacDtoSApellido());
                    pacDireccion.setText(pacienteDto.getPacDtoDireccion());
//                  fechaNac.setValue(pacienteDto.getPacDtoFecNacimiento());
                    CitaDto citaDto = citaDao.getCitaById(id);
                    String x;
        
                    x = (citaDto.getCitDtoHora() >= 1 && citaDto.getCitDtoHora() <= 5) ? " p.m" : " a.m";
                    
                    pacHoraCita.setText(citaDto.getCitDtoHora().toString() + x);
                    pacFecCita.setText(citaDto.getCitDtoFecha().toString());

                } else {
                    showAlert("No se encontró ningún paciente con la cédula ingresada.");
                }
            } else {
                showAlert("Ingrese la cédula del paciente para buscar.");
            }
        }
    }

    @FXML
    private void modificarPaciente(ActionEvent event) {
        
        if(event.getSource() == modificar){
            
        String idPaciente = cedulaPaciente.getText();
            
        if(!idPaciente.isEmpty()){

        String newNombre = pacNombre.getText();
        String newPApellido = pacPApellido.getText();
        String newSApellido = pacSApellido.getText();
        String newDireccion = pacDireccion.getText();
        String newFecha = pacFecCita.getText();
//      String newHora= pacHoraCita.getText();
        String aux = String.valueOf(pacHoraCita.getText());
        
        PacienteDto pacienteDto = new PacienteDto();
        CitaDto citaDto = new CitaDto();
        
        System.out.println("lololo:"+id);
        
        pacienteDto.setPacDtoId(id); 
        pacienteDto.setPacDtoNombre(newNombre);
        pacienteDto.setPacDtoPApellido(newPApellido);
        pacienteDto.setPacDtoSApellido(newSApellido);
        pacienteDto.setPacDtoDireccion(newDireccion);
        
        
        citaDto.setCitDtoId(id); 
        citaDto.setCitDtoFecha(Date.valueOf(newFecha));

        int n = aux.indexOf(" ");

        aux =  aux.substring(0, n);
        
        System.out.println(aux);
        
        citaDto.setCitDtoHora(Short.valueOf(aux));

        pacienteDao.actualizarPaciente(pacienteDto);
        citaDao.actualizarCita(citaDto);
   
        actualizarTablaPacientes();
        actualizarTablaCitas(); 
       System.out.println("Paciente actualizado ");
        
      
        }else{
            showAlert("No a ingresado una cedula");
        }
     
        }   
        
    }

    @FXML
    private void eliminarPaciente(ActionEvent event) {
        
    if(event.getSource() == Eliminar){     
        
    System.out.println("pppppppp  " + id);
    
    String idPaciente = cedulaPaciente.getText();
    
    if(!idPaciente.isEmpty()){
    
    citaDao.eliminarCita(id);   

    pacienteDao.eliminarPaciente(id);
    
    id = 0;
    
    cedulaPaciente.setText(null);
    pacNombre.setText(null);
    pacPApellido.setText(null);
    pacSApellido.setText(null);
    pacDireccion.setText(null);
    pacHoraCita.setText(null);
    pacFecCita.setText(null);
    System.out.println("Paciente y Cita borrado exitosamente");
    
    actualizarTablaPacientes();
    actualizarTablaCitas();
      
    }else{
        showAlert("No a ingresado una cedula");
    }
    
    }
        
    }
    
    
private void actualizarTablaPacientes() {
    PacienteDao pacienteDao1 = new PacienteDao();
    
    List<Paciente> listaPacientes = pacienteDao1.obtenerPacientes();
    pacienteDao1.closeConnection();
    
    ObservableList<Paciente> pacientesObservableList = FXCollections.observableArrayList(listaPacientes);
    tableView.setItems(pacientesObservableList);
} 



private void actualizarTablaCitas() {
    CitaDao citaDao1 = new CitaDao();
    
    List<CitaPK> listaCitas = citaDao1.obtenerCitas();
    citaDao1.closeConnection();
    
    ObservableList<CitaPK> citasObservableList = FXCollections.observableArrayList(listaCitas);
    
    tableView2.setItems(citasObservableList);
}

}
