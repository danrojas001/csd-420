module com.randomcards.randomcards {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.smartcardio;


    opens com.randomcards.randomcards to javafx.fxml;
    exports com.randomcards.randomcards;
}