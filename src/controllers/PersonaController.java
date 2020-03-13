package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Persona;

public class PersonaController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtEdad;

    @FXML
    private TableView<Persona> tblPersonas;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colApellido;

    @FXML
    private TableColumn<?, ?> colEdad;

    private ObservableList<Persona> personas;

    public void initialize(){

        this.personas = FXCollections.observableArrayList();

        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        this.colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

    }

    public void agregarPersona(ActionEvent actionEvent) {

        String nombre = this.txtNombre.getText();
        String apellido = this.txtApellido.getText();
        Integer edad = Integer.valueOf(this.txtEdad.getText());

        Persona p = new Persona(nombre, apellido, edad);

        if(!this.personas.contains(p)){
            this.personas.add(p);
            this.tblPersonas.setItems(personas);

            this.limpiar();

        }else{
            this.advertencia("Ya existe la persona");
        }

    }

    public void seleccionar(MouseEvent mouseEvent) {

        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();

        if(p != null){
            this.txtNombre.setText(p.getNombre());
            this.txtApellido.setText(p.getApellido());
            this.txtEdad.setText(String.valueOf(p.getEdad()));
        }

    }

    public void modificar(ActionEvent actionEvent) {

        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();

        if(p == null){
            this.advertencia("Debes seleccionar una persona");
        }else{

            String nombre = this.txtNombre.getText();
            String apellido = this.txtApellido.getText();
            Integer edad = Integer.valueOf(this.txtEdad.getText());

            Persona aux = new Persona(nombre, apellido, edad);

            if(!this.personas.contains(aux)){

                p.setNombre(aux.getNombre());
                p.setApellido(aux.getApellido());
                p.setEdad(aux.getEdad());

                this.tblPersonas.refresh();

            }

        }


    }

    private void advertencia(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText((String)null);
        alert.setTitle(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void eliminar(ActionEvent actionEvent) {

        Persona p = this.tblPersonas.getSelectionModel().getSelectedItem();

        if(p == null){
            this.advertencia("Debes seleccionar una persona para eliminar");
        }else{

            this.personas.remove(p);
            this.tblPersonas.refresh();

            this.limpiar();

        }

    }

    public void limpiar(){

        this.txtNombre.setText("");
        this.txtApellido.setText("");
        this.txtEdad.setText("");

    }

}
