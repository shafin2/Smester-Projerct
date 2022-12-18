package com.example.hardwareshop2.Controllers;

import com.example.hardwareshop2.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class InventoryController  {
    private static File PTfile=new File("PowerToolsInventory.txt");
    private static File HTfile=new File("HandToolsInventory.txt");
    private static File CTfile=new File("CuttingToolsInventory.txt");
    private ArrayList<PowerTools> PowerToolarr=new ArrayList<>();
    private ArrayList<HandTools> HandToolarr=new ArrayList<>();
    private ArrayList<CuttingTools> CuttingToolarr=new ArrayList<>();
    private ObservableList<Tools> pwoblst=FXCollections.observableArrayList();
    private ObservableList<Tools> htoblst=FXCollections.observableArrayList();
    private ObservableList<Tools> ctoblst=FXCollections.observableArrayList();
    @FXML
    void initialize() throws IOException {
        if (User.userStatus.compareTo("Admin")==0){
            AddUserBtn.setVisible(true);
            CalcBtn.setVisible(true);
            EmployeeBtn.setVisible(true);
        }
        else {
            AddUserBtn.setVisible(false);
            CalcBtn.setVisible(false);
            EmployeeBtn.setVisible(false);
        }
        toolCategoryfield.setItems(FXCollections.observableArrayList("Power tools","Cutting tools","Hand tools"));
       CategoryMenu2.setItems(FXCollections.observableArrayList("Power tools","Cutting tools","Hand tools"));
       readData();
    }
    public void readData(){
        PowerToolarr=FileHandling.readFile(PTfile);
        HandToolarr=FileHandling.readFile(HTfile);
        CuttingToolarr=FileHandling.readFile(CTfile);
        populateobslst();
    }
    public void populateobslst(){
        pwoblst.clear();
        htoblst.clear();
        ctoblst.clear();
        for(PowerTools p:PowerToolarr){
            pwoblst.add(new Tools(p.getToolId(),p.getToolName(),p.getToolQuantity(),p.getPricePerUnit(),p.getTotalAmount()));
        }
        for(HandTools p:HandToolarr){
            htoblst.add(new Tools(p.getToolId(),p.getToolName(),p.getToolQuantity(),p.getPricePerUnit(),p.getTotalAmount()));
        }
        for(CuttingTools p:CuttingToolarr){
            ctoblst.add(new Tools(p.getToolId(),p.getToolName(),p.getToolQuantity(),p.getPricePerUnit(),p.getTotalAmount()));
        }
    }
    @FXML
    private Button AddUserBtn;

    @FXML
    private Button CalcBtn;

    @FXML
    private ComboBox<String> CategoryMenu2;

    @FXML
    private Button EmployeeBtn;

    @FXML
    private Button addItemBtn;

    @FXML
    private DatePicker dateField;

    @FXML
    private Button deleteItemBtn;

    @FXML
    private Button editItemBtn;

    @FXML
    private ComboBox<String> toolCategoryfield;

    @FXML
    private TextField toolQuantityField;

    @FXML
    private TextField toolidField;

    @FXML
    private TextField toolnameField;

    @FXML
    private TextField unitPriceField;
    @FXML
    private TableView<Tools> TableView;
    @FXML
    private TableColumn<Tools, Integer> TableToolId;

    @FXML
    private TableColumn<Tools, String> TableToolName;

    @FXML
    private TableColumn<Tools, Double> TableToolPricePerUnit;

    @FXML
    private TableColumn<Tools, Integer> TableToolQuantity;

    @FXML
    private TableColumn<Tools,Double> TableTotalPrice;
    @FXML
    private Label toolpriceError;

    @FXML
    private Label toolquantityError;
    @FXML
    private Label toolnameError;
    @FXML
    private Label toolCategoryError;
    @FXML
    void AddUser(MouseEvent event) throws IOException {
        Driver.changeScene("SignUp.fxml");
    }

    @FXML
    void LogOut(MouseEvent event) throws IOException {
        Driver.changeScene("login-view.fxml");
    }

    @FXML
    void CalculationClick(MouseEvent event) {

    }

    @FXML
    void EmployeeClick(MouseEvent event) {

    }

    @FXML
    void InventoryClick(MouseEvent event) {

    }

    @FXML
    void SellClick(MouseEvent event) {

    }
    @FXML
    void addItemBtn(MouseEvent event) {
        if(toolCategoryfield.getSelectionModel().getSelectedItem()==null || toolnameField.getText().compareTo("")==0 || unitPriceField.getText().compareTo("")==0 || toolQuantityField.getText().compareTo("")==0){
            if(toolCategoryfield.getSelectionModel().getSelectedItem()==null){
                toolCategoryError.setText("Please select one");
            }
            if(toolnameField.getText().compareTo("")==0){
                toolnameError.setText("Enter tool name");
            }
            if(unitPriceField.getText().compareTo("")==0){
                toolpriceError.setText("Invalid input");
            }
            if(toolQuantityField.getText().compareTo("")==0){
                toolquantityError.setText("Invalid input");
            }
        }
        else {
            clearError();
            String category=toolCategoryfield.getSelectionModel().getSelectedItem();
            int id=0;
            int check=0;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "new "+category+" created");

            //"Power tools","Cutting tools","Hand tools"
            if(category.compareTo("Power tools")==0){
                if(pwoblst.size()==0){
                    id=1000;
                }
                else
                    id= pwoblst.get(pwoblst.size()-1).getToolId()+1;
                check=checkIfToolExist(pwoblst,toolnameField.getText());
                if(check==0){
                    PowerTools pw=new PowerTools(toolnameField.getText(),Integer.parseInt(toolQuantityField.getText()),Double.parseDouble(unitPriceField.getText()),id);
                    FileHandling.AddNewTool(PTfile,pw,true);
                    alert.showAndWait();
                    clearField();
                }
                else {
                    toolnameError.setText("That tool already Exist!");
                }
            }
            if(category.compareTo("Cutting tools")==0){
                if(ctoblst.size()==0){
                    id=2000;
                }
                else
                    id=ctoblst.get(ctoblst.size()-1).getToolId()+1;
                check=checkIfToolExist(ctoblst,toolnameField.getText());
                if(check==0){
                    CuttingTools ct=new CuttingTools(toolnameField.getText(),Integer.parseInt(toolQuantityField.getText()),Double.parseDouble(unitPriceField.getText()),id);
                    FileHandling.AddNewTool(CTfile,ct,true);
                    alert.showAndWait();
                    clearField();
                }
                else {
                    toolnameError.setText("That tool already Exist!");
                }

            }
            if(category.compareTo("Hand tools")==0){
                if(htoblst.size()==0){
                    id=3000;
                }
                else
                    id=htoblst.get(htoblst.size()-1).getToolId()+1;
                check=checkIfToolExist(htoblst,toolnameField.getText());
                if(check==0){
                    HandTools ht=new HandTools(toolnameField.getText(),Integer.parseInt(toolQuantityField.getText()),Double.parseDouble(unitPriceField.getText()),id);
                    FileHandling.AddNewTool(HTfile,ht,true);
                    alert.showAndWait();
                    clearField();
                }
                else {
                    toolnameError.setText("That tool already Exist!");
                }
            }
        }
        showTools();
    }
    @FXML
    void deleteItemBtn(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "First select tool from table");
        Alert editAlert = new Alert(Alert.AlertType.CONFIRMATION, "Your tool is Deleted");
        if(toolidField.getText().compareTo("")==0){
            alert.showAndWait();
        }
        else {
            String category=toolCategoryfield.getSelectionModel().getSelectedItem();
            if(category.compareTo(CategoryMenu2.getSelectionModel().getSelectedItem())==0){
                if(category.compareTo("Power tools")==0){
                    deleteFromFile(PowerToolarr,PTfile);
                    editAlert.showAndWait();
                    clearField();
                }
                if(category.compareTo("Cutting tools")==0){
                    deleteFromFile(CuttingToolarr,CTfile);
                    editAlert.showAndWait();
                    clearField();
                }
                if(category.compareTo("Hand tools")==0){
                    deleteFromFile(HandToolarr,HTfile);
                    editAlert.showAndWait();
                    clearField();
                }
            }
            else {
                alert.setContentText("You change the category");
                alert.showAndWait();
                clearField();
            }
        }
        showTools();
    }

    @FXML
    void editItemBtn(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "First select tool from table");
        Alert editAlert = new Alert(Alert.AlertType.CONFIRMATION, "Your tool is edited");
        if(toolidField.getText().compareTo("")==0){
            alert.showAndWait();
        }
        else {
            String category=toolCategoryfield.getSelectionModel().getSelectedItem();
            if(category.compareTo(CategoryMenu2.getSelectionModel().getSelectedItem())==0){
                if(category.compareTo("Power tools")==0){
                    editFile(PowerToolarr,PTfile);
                    editAlert.showAndWait();
                    clearField();
                }
                if(category.compareTo("Cutting tools")==0){
                    editFile(CuttingToolarr,CTfile);
                    editAlert.showAndWait();
                    clearField();
                }
                if(category.compareTo("Hand tools")==0){
                    editFile(HandToolarr,HTfile);
                    editAlert.showAndWait();
                    clearField();
                }
            }
            else {
                alert.setContentText("You change the category");
                alert.showAndWait();
                clearField();
            }
        }
        showTools();
    }

    @FXML
    void tableDataChange(MouseEvent event) {
//        CategoryMenu2.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
//            System.out.println(newValue);
//        });
    }
    @FXML
    void showTablebtn(MouseEvent event) {
        clearError();
        tableActions();
        showTools();

    }
    public void tableActions(){
        TableView.setRowFactory( tv -> {
            TableRow<Tools> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Tools rowData = row.getItem();
                    toolidField.setText(Integer.toString(rowData.getToolId()));
                    toolnameField.setText(rowData.getToolName());
                    toolQuantityField.setText(Integer.toString(rowData.getToolQuantity()));
                    unitPriceField.setText(Double.toString(rowData.getPricePerUnit()));
                    SingleSelectionModel s=CategoryMenu2.getSelectionModel();
                    toolCategoryfield.setValue(CategoryMenu2.getValue());
                }
            });
            return row ;
        });
    }
    @FXML
    void clearBtn(MouseEvent event) {
        clearField();
        clearError();
    }
    public void clearError(){
        toolCategoryError.setText("");
        toolnameError.setText("");
        toolpriceError.setText("");
        toolquantityError.setText("");
    }
    public void clearField(){
        toolnameField.setText("");
        unitPriceField.setText("");
        toolQuantityField.setText("");
        toolidField.setText("");
    }
    @FXML
    void fullScreenClick(MouseEvent event) {
        clearError();
    }
    public <T extends Tools> void editFile(ArrayList<T> arr,File file){
        for (T p:arr){
            if(p.getToolId()==Integer.parseInt(toolidField.getText())){
                p.setToolName(toolnameField.getText());
                p.setPricePerUnit(Double.parseDouble(unitPriceField.getText()));
                p.setToolQuantity(Integer.parseInt(toolQuantityField.getText()));
                p.setTotalAmount(Double.parseDouble(unitPriceField.getText())*Integer.parseInt(toolQuantityField.getText()));
                break;
            }
        }
        int clear=0;
        for (T p1:arr){
            if(clear==0){
                FileHandling.AddNewTool(file,p1,false);
                clear++;
            }
            else{
                FileHandling.AddNewTool(file,p1,true);
            }

        }
    }
    public <T extends Tools> void deleteFromFile(ArrayList<T> arr,File file){
        for (T p:arr){
            if(p.getToolId()==Integer.parseInt(toolidField.getText())){
                arr.remove(p);
                break;
            }
        }
        int clear=0;
        for (T p1:arr){
            if(clear==0){
                FileHandling.AddNewTool(file,p1,false);
                clear++;
            }
            else{
                FileHandling.AddNewTool(file,p1,true);
            }

        }
    }
    public void showTools(){
        readData();
        String category=CategoryMenu2.getSelectionModel().getSelectedItem();
        if(category.compareTo("Power tools")==0){
            TableToolId.setCellValueFactory(new PropertyValueFactory<Tools,Integer>("toolId"));
            TableToolName.setCellValueFactory(new PropertyValueFactory<Tools,String>("toolName"));
            TableToolPricePerUnit.setCellValueFactory(new PropertyValueFactory<Tools,Double>("pricePerUnit"));
            TableToolQuantity.setCellValueFactory(new PropertyValueFactory<Tools,Integer>("toolQuantity"));
            TableTotalPrice.setCellValueFactory(new PropertyValueFactory<Tools,Double>("totalAmount"));
            TableView.setItems(pwoblst);
        }
        if(category.compareTo("Cutting tools")==0){
            TableToolId.setCellValueFactory(new PropertyValueFactory<Tools,Integer>("toolId"));
            TableToolName.setCellValueFactory(new PropertyValueFactory<Tools,String>("toolName"));
            TableToolPricePerUnit.setCellValueFactory(new PropertyValueFactory<Tools,Double>("pricePerUnit"));
            TableToolQuantity.setCellValueFactory(new PropertyValueFactory<Tools,Integer>("toolQuantity"));
            TableTotalPrice.setCellValueFactory(new PropertyValueFactory<Tools,Double>("totalAmount"));
            TableView.setItems(ctoblst);
        }
        if(category.compareTo("Hand tools")==0){
            TableToolId.setCellValueFactory(new PropertyValueFactory<Tools,Integer>("toolId"));
            TableToolName.setCellValueFactory(new PropertyValueFactory<Tools,String>("toolName"));
            TableToolPricePerUnit.setCellValueFactory(new PropertyValueFactory<Tools,Double>("pricePerUnit"));
            TableToolQuantity.setCellValueFactory(new PropertyValueFactory<Tools,Integer>("toolQuantity"));
            TableTotalPrice.setCellValueFactory(new PropertyValueFactory<Tools,Double>("totalAmount"));
            TableView.setItems(htoblst);
        }
    }
    public int checkIfToolExist(ObservableList<Tools> lst,String toolname){
        int exist=0;
        for (Tools t:lst){
            if (t.getToolName().compareTo(toolname)==0){
                exist=1;
                break;
            }
        }
        return exist;
    }
}
