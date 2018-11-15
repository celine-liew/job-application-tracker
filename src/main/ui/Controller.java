package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.Observable;

public class Controller {
    @FXML
    String foo;

    @FXML
    public ListView<String> listv() {
        ListView<String> listv = new ListView();
        ObservableList items = FXCollections.observableArrayList(
                "Single", "Double");
        listv.setItems(items);
        return listv;
    }

    public String getFoo() {
        return foo;
}





}
