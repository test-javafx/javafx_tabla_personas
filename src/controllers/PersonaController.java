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
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle(null);
            alert.setContentText("Ya existe la persona");
            alert.showAndWait();
        }

    }
}
