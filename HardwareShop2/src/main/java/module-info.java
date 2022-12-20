module com.example.hardwareshop2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.seleniumhq.selenium.api;
    requires org.seleniumhq.selenium.firefox_driver;
    requires org.seleniumhq.selenium.chrome_driver;


    opens com.example.hardwareshop2 to javafx.fxml;
    exports com.example.hardwareshop2;
    exports com.example.hardwareshop2.Controllers;
    opens com.example.hardwareshop2.Controllers to javafx.fxml;
}