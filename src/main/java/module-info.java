module uniquindio.edu.co.restauranteparcial3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens uniquindio.edu.co.restauranteparcial3 to javafx.fxml;
    exports uniquindio.edu.co.restauranteparcial3;
}