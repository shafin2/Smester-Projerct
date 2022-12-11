module com.example.hardwareshop2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hardwareshop2 to javafx.fxml;
    exports com.example.hardwareshop2;
    exports com.example.hardwareshop2.Controllers;
    opens com.example.hardwareshop2.Controllers to javafx.fxml;
}