package com.example.hardwareshop2.Controllers;

import com.example.hardwareshop2.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class InventoryController  {
    public static int sceneStatus=0;
    private static File PTfile=new File("PowerToolsInventory.txt");
    private static File HTfile=new File("HandToolsInventory.txt");
    private static File CTfile=new File("CuttingToolsInventory.txt");
    private static File SaleFile=new File("SalesData.txt");
    private ArrayList<PowerTools> PowerToolarr=new ArrayList<>();
    private ArrayList<HandTools> HandToolarr=new ArrayList<>();
    private ArrayList<CuttingTools> CuttingToolarr=new ArrayList<>();
    private ArrayList<Tools> SaleToolarr=new ArrayList<>();
    private ObservableList<Tools> pwoblst=FXCollections.observableArrayList();
    private ObservableList<Tools> htoblst=FXCollections.observableArrayList();
    private ObservableList<Tools> ctoblst=FXCollections.observableArrayList();
    private ObservableList<Tools> Saleoblst=FXCollections.observableArrayList();
    @FXML
    void initialize() throws IOException {
        if(sceneStatus==0){
            InventoryPane.setVisible(true);
            InventoryTablePane.setVisible(true);
            SellToolPane.setVisible(false);
            saleTableBtn.setVisible(false);
            SalesTablewindow.setVisible(false);
        }
        else {
            InventoryPane.setVisible(false);
            InventoryTablePane.setVisible(true);
            SalesTablewindow.setVisible(false);
            SellToolPane.setVisible(true);
        }
        if (User.userStatus.compareTo("Admin")==0){
            AddUserBtn.setVisible(true);
            deleteItemBtn.setVisible(true);
            editItemBtn.setVisible(true);
        }
        else {
            AddUserBtn.setVisible(false);
            deleteItemBtn.setVisible(false);
            editItemBtn.setVisible(false);

        }
        toolCategoryfield.setItems(FXCollections.observableArrayList("Power tools","Cutting tools","Hand tools"));
        CategoryMenu2.setItems(FXCollections.observableArrayList("Power tools","Cutting tools","Hand tools"));
        SelltoolCategoryfield1.setItems(FXCollections.observableArrayList("Power tools","Cutting tools","Hand tools"));
        readData();
        sellwlatableaction();
    }
    public void readData(){
        PowerToolarr=FileHandling.readFile(PTfile);
        HandToolarr=FileHandling.readFile(HTfile);
        CuttingToolarr=FileHandling.readFile(CTfile);
        SaleToolarr=FileHandling.readFile(SaleFile);
        populateobslst();
    }
    public void populateobslst(){
        pwoblst.clear();
        htoblst.clear();
        ctoblst.clear();
        Saleoblst.clear();
        for(PowerTools p:PowerToolarr){
            pwoblst.add(new Tools(p.getToolId(),p.getToolName(),p.getToolQuantity(),p.getPricePerUnit(),p.getTotalAmount()));
        }
        for(HandTools p:HandToolarr){
            htoblst.add(new Tools(p.getToolId(),p.getToolName(),p.getToolQuantity(),p.getPricePerUnit(),p.getTotalAmount()));
        }
        for(CuttingTools p:CuttingToolarr){
            ctoblst.add(new Tools(p.getToolId(),p.getToolName(),p.getToolQuantity(),p.getPricePerUnit(),p.getTotalAmount()));
        }
        for(Tools p:SaleToolarr){
            Saleoblst.add(new Tools(p.getToolId(),p.getToolName(),p.getToolQuantity(),p.getPricePerUnit(),p.getTotalAmount()));
        }
    }
    @FXML
    private AnchorPane InventoryPane;
    @FXML
    private Button saleTableBtn;
    @FXML
    private AnchorPane InventoryTablePane;

    @FXML
    private AnchorPane SellToolPane;
    @FXML
    private Button AddUserBtn;


    @FXML
    private ComboBox<String> CategoryMenu2;


    @FXML
    private Button addItemBtn;


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
    private AnchorPane SalesTablewindow;

    @FXML
    private TableView<Tools> SellTableview;

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
    private TableColumn<Tools, Integer> TableToolId1;

    @FXML
    private TableColumn<Tools, String> TableToolName1;

    @FXML
    private TableColumn<Tools, Double> TableToolPricePerUnit1;

    @FXML
    private TableColumn<Tools, Integer> TableToolQuantity1;

    @FXML
    private TableColumn<Tools,Double> TableTotalPrice1;
    @FXML
    private Label toolpriceError;

    @FXML
    private Label toolquantityError;
    @FXML
    private Label toolnameError;
    @FXML
    private Label toolCategoryError;
    @FXML
    private Button SellItemBtn;

    @FXML
    private Button SelldeleteItemBtn;

    @FXML
    private Button SelleditItemBtn;

    @FXML
    private ComboBox<String> SelltoolCategoryfield1;

    @FXML
    private Button SelltoolIdSearch;

    @FXML
    private TextField SelltoolQuantityField1;

    @FXML
    private TextField SelltoolidField;

    @FXML
    private TextField SelltoolnameField;

    @FXML
    private TextField SellunitPriceField1;

    @FXML
    void HomeClick(MouseEvent event) throws IOException {
        Driver.changeScene("MainView.fxml");
    }
    @FXML
    void AddUser(MouseEvent event) throws IOException {
        Driver.changeScene("SignUp.fxml");
    }

    @FXML
    void LogOut(MouseEvent event) throws IOException {
        Driver.changeScene("login-view.fxml");
    }

    @FXML
    void CalculationClick(MouseEvent event) throws IOException {
        Driver.changeScene("Calculations.fxml");
    }


    @FXML
    void InventoryClick(MouseEvent event) throws IOException {
        InventoryController.sceneStatus=0;
        Driver.changeScene("Inventory.fxml");
    }

    @FXML
    void SellClick(MouseEvent event) throws IOException {
        InventoryController.sceneStatus=1;
        Driver.changeScene("Inventory.fxml");
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
//                    SingleSelectionModel s=CategoryMenu2.getSelectionModel();
                    toolCategoryfield.setValue(CategoryMenu2.getValue());

                    SelltoolidField.setText(Integer.toString(rowData.getToolId()));
                    selltoolid=rowData.getToolId();
                    SelltoolnameField.setText(rowData.getToolName());
                    SellunitPriceField1.setText(Double.toString(rowData.getPricePerUnit()));
                    SelltoolCategoryfield1.setValue(CategoryMenu2.getValue());

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




    //Selling Portion
    @FXML
    void SellItemBtn(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "First search or select Tool");
        if(SelltoolCategoryfield1.getSelectionModel().getSelectedItem()==null || SelltoolnameField.getText().compareTo("")==0 || SellunitPriceField1.getText().compareTo("")==0){
            alert.showAndWait();
        }
        else {
            if(SelltoolQuantityField1.getText().compareTo("")==0){
                alert.setContentText("Add Quantity");
                alert.showAndWait();
            }
            else {
                if (Integer.parseInt(SelltoolidField.getText())==selltoolid){
                    Tools t=new Tools(selltoolid,SelltoolnameField.getText(), Integer.parseInt(SelltoolQuantityField1.getText()), Double.parseDouble(SellunitPriceField1.getText()),Integer.parseInt(SelltoolQuantityField1.getText())*Double.parseDouble(SellunitPriceField1.getText()));
                    if(selltoolid>=1000 && selltoolid<2000){
                        editFilewrtSale(PowerToolarr,PTfile,t);
                    }
                    else if (selltoolid>=2000 && selltoolid<3000){
                        editFilewrtSale(CuttingToolarr,CTfile,t);
                    }
                    else if (selltoolid>=3000 && selltoolid<4000) {
                        editFilewrtSale(HandToolarr,HTfile,t);

                    }
                    else {
                        SelltoolIdError.setText("Your tool id is out of range");
                    }
                }
                else {
                    alert.setContentText("You change id");
                    alert.showAndWait();
                    clearField();
                    clearError();
                }
            }
        }

        clearField();
        clearError();
        showTools();
    }

    @FXML
    void SelldeleteItemBtn(MouseEvent event) {

    }

    @FXML
    void SelleditItemBtn(MouseEvent event) {

    }
    @FXML
    private Label SelltoolIdError;

    static int selltoolid=0;
    @FXML
    void SelltoolIdSearch(MouseEvent event) {
        SelltoolIdError.setText("");
        if(SelltoolidField.getText().compareTo("")==0){
            SelltoolIdError.setText("Enter tool id");
        }
        else {
            int id=Integer.parseInt(SelltoolidField.getText());
            int check=0;
            if(id>=1000 && id<2000){
                check=checktool(pwoblst,id);
                SelltoolCategoryfield1.setValue("Power Tool");

            }
            else if (id>=2000 && id<3000){
                check=checktool(ctoblst,id);
                SelltoolCategoryfield1.setValue("Cutting Tools");

            }
            else if (id>=3000 && id<4000) {
                check=checktool(htoblst,id);
                SelltoolCategoryfield1.setValue("Hand Tools");

            }
            else {
                SelltoolIdError.setText("Your tool id is out of range");
            }
        }
    }
    public int checktool(ObservableList<Tools> lst,int id){
        int exist=0;
        for (Tools t:lst){
            if (t.getToolId()==id){
                if (t.getToolQuantity()>0){
                    exist=1;
                    selltoolid=id;
                    SelltoolnameField.setText(t.getToolName());
                    SellunitPriceField1.setText(Double.toString(t.getPricePerUnit()));
                }
                else {
                    clearError();
                    clearField();
                }
                break;
            }
        }
        return exist;
    }
    @FXML
    void saleTableBtn(MouseEvent event) {
        sellwlatableaction();
        if(InventoryTablePane.isVisible()){
            readSaleData();
            InventoryTablePane.setVisible(false);
            SalesTablewindow.setVisible(true);
            saleTableBtn.setText("Tools");
        }
        else {
            InventoryTablePane.setVisible(true);
            SalesTablewindow.setVisible(false);
            saleTableBtn.setText("Sales");
        }
    }
    void readSaleData(){
        readData();
        TableToolId1.setCellValueFactory(new PropertyValueFactory<Tools,Integer>("toolId"));
        TableToolName1.setCellValueFactory(new PropertyValueFactory<Tools,String>("toolName"));
        TableToolPricePerUnit1.setCellValueFactory(new PropertyValueFactory<Tools,Double>("pricePerUnit"));
        TableToolQuantity1.setCellValueFactory(new PropertyValueFactory<Tools,Integer>("toolQuantity"));
        TableTotalPrice1.setCellValueFactory(new PropertyValueFactory<Tools,Double>("totalAmount"));
        SellTableview.setItems(Saleoblst);
    }
    public void clearError(){
        toolCategoryError.setText("");
        toolnameError.setText("");
        toolpriceError.setText("");
        toolquantityError.setText("");
        SelltoolIdError.setText("");
    }
    public void clearField(){
        toolnameField.setText("");
        unitPriceField.setText("");
        toolQuantityField.setText("");
        toolidField.setText("");
        SelltoolnameField.setText("");
        SellunitPriceField1.setText("");
        SelltoolidField.setText("");
        SelltoolQuantityField1.setText("");
        SelltoolCategoryfield1.setValue("");
    }
    public <T extends Tools> void editFilewrtSale(ArrayList<T> arr,File file,Tools t){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Tool sell");
        for (T p:arr){
            if(p.getToolId()==t.getToolId()){
                if(t.getToolQuantity()>p.getToolQuantity()){
                    alert.setContentText("That tool quantity is 0 in inventory!");
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.showAndWait();
                }
                else {
                    int q=p.getToolQuantity()-t.getToolQuantity();
                    p.setToolQuantity(q);
                    p.setTotalAmount(q*p.getPricePerUnit());
                    FileHandling.AddNewTool(SaleFile,t,true);
                    alert.setContentText("Tool sell");
                    alert.showAndWait();
                }
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

    public void sellwlatableaction(){
        SellTableview.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.D)) {
                    Tools rowData = (Tools) SellTableview.getSelectionModel().getSelectedItem();
                    int id=rowData.getToolId();
                    if(id>=1000 && id<2000){
                        deleteFilewrtSale(PowerToolarr,PTfile,rowData);
                    }
                    else if (id>=2000 && id<3000){
                        deleteFilewrtSale(CuttingToolarr,CTfile,rowData);
                    }
                    else if (id>=3000 && id<4000) {
                        deleteFilewrtSale(HandToolarr,HTfile,rowData);

                    }
                }
            }
        });

    }
    public <T extends Tools> void deleteFilewrtSale(ArrayList<T> arr,File file,Tools t){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Tool deleted");
        for (T p:arr){
            if(p.getToolId()==t.getToolId()){
                int q=p.getToolQuantity()+t.getToolQuantity();
                p.setToolQuantity(q);
                p.setTotalAmount(q*p.getPricePerUnit());
                break;
            }
        }
        for (Tools p1:SaleToolarr){
            if(p1.getToolId()==t.getToolId() && p1.getToolQuantity()==t.getToolQuantity()){
                SaleToolarr.remove(p1);
                System.out.println(SaleToolarr);
                alert.showAndWait();
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
        int clear2=0;
        for (Tools p1:SaleToolarr){
            if(clear2==0){
                FileHandling.AddNewTool(SaleFile,p1,false);
                clear2++;
            }
            else{
                FileHandling.AddNewTool(SaleFile,p1,true);
            }
        }
        readSaleData();
    }

}
