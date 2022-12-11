module alpha.hardwareshop {
    requires javafx.controls;
    requires javafx.fxml;


    opens alpha.hardwareshop to javafx.fxml;
    exports alpha.hardwareshop;
}