

import custom.NumberField;
import entidades.libro;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javax.swing.JOptionPane;
import DAO.libroDAO;
import util.Fecha;

	public class LibroController implements Initializable {

	    public ObservableList<libro> dataLibro = FXCollections.observableArrayList();
	    @FXML
	    private Tab tabEntrada;
	    @FXML
	    private TextField txtId;
	    @FXML
	    private TextField txtNombre;
	    @FXML
	    private TextField txtAutor;
	    @FXML
	    private ChoiceBox<String> choiceGenero;
	    @FXML
	    private TextField txtPaginas;
	    @FXML
	    private DatePicker dateFecha;
	    @FXML
	    private Button btnNuevo;
	    @FXML
	    private Button btnGuardar;
	    @FXML
	    private Button btnEliminar;
	    @FXML
	    private Button btnCancelar;
	    @FXML
	    private Tab tabSalida;
	    @FXML
	    private TextField txtBuscar;
	    @FXML
	    private ChoiceBox<String> choiceBuscar;
	    @FXML
	    private Button btnBuscar;
	    @FXML
	    private Button btnRefrescar;
	    @FXML
	    private TableView<libro> tablaLibro;
	    @FXML
	    private TableColumn<libro, Long> colId;
	    @FXML
	    private TableColumn<libro, String> colNombre;
	    @FXML
	    private TableColumn<libro, String> colAutor;
	    @FXML
	    private TableColumn<libro, String> colGenero;
	    @FXML
	    private TableColumn<libro, Integer> colPaginas;
	    @FXML
	    private TableColumn<libro, Long> colFechas;
	    @FXML
	    private Label labResultados;
	    @FXML
	    private TabPane tabPaneLibro;

	    /**
	     * Initializes the controller class.
	     */
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	        configurarTabla();
	        rellenarTablaLibro();
	        vaciarCampos();
	        deshabilitarCampos();
	        
	       // colocarImagenBotones();
            	btnNuevo.setDisable(false);
	        btnGuardar.setDisable(true);
	        btnEliminar.setDisable(true);
	        btnCancelar.setDisable(true);
	        
	        choiceGenero.getItems().addAll("Novela","Comedia","Drama");
	        choiceGenero.setValue("");
	        choiceBuscar.getItems().addAll("Id","Nombre","Autor","G�nero");
	        choiceBuscar.setValue("Id");
	    }    
	    
	    public void configurarTabla(){
	        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
	        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
	        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
	        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
	        colFechas.setCellValueFactory(new PropertyValueFactory<>("fechaString"));
	        colPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
	        tablaLibro.setItems(dataLibro);
	    }
            
	    
	    public void rellenarTablaLibro(){
	        dataLibro.clear();
	        libroDAO libroRepo = new libroDAO();
	        ObservableList<libro> libros = libroRepo.buscarTodos();
	        dataLibro.setAll(libros);
	        int resultados = libros.size();
	        labResultados.setText("Resultados: "+resultados);
	    }
	    
            
	    private void vaciarCampos(){
	        txtId.setText("");
	        txtNombre.setText("");
	        txtAutor.setText("");
	        txtPaginas.setText("0");
	        dateFecha.setValue(null);
	    }
	    
            
	    private void deshabilitarCampos(){
	        txtId.setDisable(true);
	        txtNombre.setDisable(true);
	        txtAutor.setDisable(true);
	        choiceGenero.setDisable(true);
	        txtPaginas.setDisable(true);
	        dateFecha.setDisable(true);
	    }
	    
            
	    private void habilitarCampos(){
	        txtId.setDisable(true); //siempre ira deshabilitado
	        txtNombre.setDisable(false);
	        txtAutor.setDisable(false);
	        choiceGenero.setDisable(false);
	        txtPaginas.setDisable(false);
	        dateFecha.setDisable(false);
	    }

	    @FXML
	    private void btnNuevo_click(ActionEvent event) {
	        vaciarCampos();
	        habilitarCampos();
	        btnNuevo.setDisable(true);
	        btnGuardar.setDisable(false);
	        btnEliminar.setDisable(true);
	        btnCancelar.setDisable(false);
	    }

	    @FXML
	    private void btnGuardar_click(ActionEvent event) {
	        long id;
	        String nombre = txtNombre.getText();
	        String autor = txtAutor.getText();
	        String genero = choiceGenero.getValue();
	        DatePicker fecha;
	        int paginas;
	        try{
	            fecha = Fecha.getFechaLocalDate(dateFecha.getValue());
	            paginas = Integer.parseInt(txtPaginas.getText());
	        }catch(Exception ex){
	            JOptionPane.showMessageDialog(null,"No puede ingresar letras en "
	                    + "campos num�ricos.","Error de validaci�n",JOptionPane.WARNING_MESSAGE);
	            return;
	        }
	        
	        libro libro;
	        if(txtId.getText().trim().equals(""))
	            libro = new libro(nombre, autor, genero, paginas, fecha);
	        else{
	            id = Long.parseLong(txtId.getText());
	            libro = new libro(id, nombre, autor, genero, paginas, fecha);
	        }
	        libroDAO libroDao = new libroDAO();
	        if(libroDao.guardar(libro)){
                    vaciarCampos();
                    deshabilitarCampos();
                    btnNuevo.setDisable(false);
                    btnGuardar.setDisable(true);
                    btnEliminar.setDisable(true);
                    btnCancelar.setDisable(true);
                    JOptionPane.showMessageDialog(null,"Se han guardado los cambios.",
                            "Aviso",JOptionPane.INFORMATION_MESSAGE);
                    rellenarTablaLibro();
                } else {
                }
	    }

	    @FXML
	    private void btnEliminar_click(ActionEvent event) {
	        int opcion = JOptionPane.showConfirmDialog(null,"Desea eliminar el"
	                + "registro?","Confirmaci�n",JOptionPane.YES_NO_OPTION,2);
	        if(opcion == JOptionPane.YES_OPTION){
	            long id = Long.parseLong(txtId.getText());
	            libroDAO libroRepo = new libroDAO();
	            libroRepo.eliminar(id);
	            JOptionPane.showMessageDialog(null,"Registro eliminado con �xito.",
	                    "Aviso",JOptionPane.INFORMATION_MESSAGE);
	            
	            vaciarCampos();
	            deshabilitarCampos();
	            btnNuevo.setDisable(false);
	            btnGuardar.setDisable(true);
	            btnEliminar.setDisable(true);
	            btnCancelar.setDisable(true);
	            rellenarTablaLibro();
	            
	        }
	    }

	    @FXML
	    private void btnCancelar_click(ActionEvent event) {
	        vaciarCampos();
	        deshabilitarCampos();
	        btnNuevo.setDisable(false);
	        btnGuardar.setDisable(true);
	        btnEliminar.setDisable(true);
	        btnCancelar.setDisable(true);
	    }

	    @FXML
	    private void btnBuscar_click(ActionEvent event) {
	        dataLibro.clear();
	        String modoBusqueda = choiceBuscar.getValue();
	        if(modoBusqueda.equals("Id")){
	            long id=0;
	            try{
	                id = Long.parseLong(txtBuscar.getText());
	            }catch(Exception ex){
	                JOptionPane.showMessageDialog(null,"Ingrese un Id v�lido.",
	                        "Error",JOptionPane.WARNING_MESSAGE);
	                return;
	            }
	            
	            libroDAO libroDAO = new libroDAO();
	            libro libro = libroDAO.buscarLibro(id);
	            if(libro != null){
	                dataLibro.add(libro);
	                labResultados.setText("Resultados: 1");
	            }else{
	                labResultados.setText("Resultados: 0");
	            }
	            return;
	        }
	        
	        libroDAO libroRepo = new libroDAO();
	        ObservableList<libro> libros = FXCollections.observableArrayList();
	        
	        switch(modoBusqueda){
	            case "Nombre":
	                String nombreBusqueda = txtBuscar.getText();
	                libros = libroRepo.buscarPorNombre(nombreBusqueda);
	                break;
	            case "Autor":
	                String autorBusqueda = txtBuscar.getText();
	                libros = libroRepo.buscarPorAutor(autorBusqueda);
	                break;
	            case "G�nero":
	                String generoBusqueda = txtBuscar.getText();
	                libros = libroRepo.buscarPorGenero(generoBusqueda);
	                break;
	            default:
	                JOptionPane.showMessageDialog(null,"Modo de b�squeda incorrecto.",
	                        "Error",JOptionPane.WARNING_MESSAGE);
	                return;
	        }
	        dataLibro.setAll(libros);
	        int resultados = libros.size();
	        labResultados.setText("Resultados: "+resultados);
	    }

	    @FXML
	    private void btnRefrescar_click(ActionEvent event) {
	        rellenarTablaLibro();
	    }

	    @FXML
	    private void contextMenu_click(ContextMenuEvent event) {
	        ContextMenu menu = new ContextMenu();
	        MenuItem itemModificar = new MenuItem("Modificar");
	        itemModificar.setOnAction(new EventHandler<ActionEvent>(){
	           @Override
	           public void handle(ActionEvent event){
	               libro libro = tablaLibro.getSelectionModel().getSelectedItem();
	               if(libro == null){
	                   JOptionPane.showMessageDialog(null,"Seleccione un libro para"
	                           + " editar.","Advertencia",JOptionPane.WARNING_MESSAGE);
	                   return;
	               }
	               txtId.setText(String.valueOf(libro.getId()));
	               txtNombre.setText(libro.getNombre());
	               txtAutor.setText(libro.getAutor());
	               choiceGenero.setValue(libro.getGenero());
	               txtPaginas.setText(String.valueOf(libro.getPaginas()));
	               dateFecha.setValue(Fecha.getFechaLocalDate(libro.getFechaLong()));
	               
	               habilitarCampos();
	               btnNuevo.setDisable(true);
	               btnGuardar.setDisable(false);
	               btnEliminar.setDisable(false);
	               btnCancelar.setDisable(false);
	               
	               //cambiar a la pesta�a 1
	               SingleSelectionModel<Tab> selectionModel = tabPaneLibro.getSelectionModel();
	               selectionModel.select(0);
	               
	           }
	        });
	        menu.getItems().add(itemModificar);
	        tablaLibro.setContextMenu(menu);
	    }

	    /*@FXML
	    private void btnReporte_click(ActionEvent event) {
	        Reporte reporte = new Reporte("libro");
	        reporte.generarReporte();
	    }*/
	    /*
	    private void colocarImagenBotones(){
	        URL linkNuevo = getClass().getResource("/img/nuevo.png");
	        URL linkGuardar = getClass().getResource("/img/guardar.png");
	        URL linkEliminar = getClass().getResource("/img/eliminar.png");
	        URL linkCancelar = getClass().getResource("/img/cancelar.png");
	        URL linkBuscar = getClass().getResource("/img/buscar.png");
	        URL linkRefrescar = getClass().getResource("/img/refrescar.png");
	        URL linkReporte = getClass().getResource("/img/reporte.png");
	        
	        Image imagenNuevo = new Image(linkNuevo.toString(),24,24,false,true);
	        Image imagenGuardar = new Image(linkGuardar.toString(),24,24,false,true);
	        Image imagenEliminar = new Image(linkEliminar.toString(),24,24,false,true);
	        Image imagenCancelar = new Image(linkCancelar.toString(),24,24,false,true);
	        Image imagenBuscar = new Image(linkBuscar.toString(),16,16,false,true);
	        Image imagenRefrescar = new Image(linkRefrescar.toString(),16,16,false,true);
	        Image imagenReporte = new Image(linkReporte.toString(),16,16,false,true);
	        
	        btnNuevo.setGraphic((new ImageView(imagenNuevo)));
	        btnGuardar.setGraphic((new ImageView(imagenGuardar)));
	        btnEliminar.setGraphic((new ImageView(imagenEliminar)));
	        btnCancelar.setGraphic((new ImageView(imagenCancelar)));
	        btnBuscar.setGraphic((new ImageView(imagenBuscar)));
	        btnRefrescar.setGraphic((new ImageView(imagenRefrescar)));
	        
	        
	    }
	    */
	}

