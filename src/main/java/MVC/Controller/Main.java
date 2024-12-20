package MVC.Controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;

public class Main extends Application {
    private static Program p = new Program();
    private int number =0 ;
    private ObservableList<String> langs = FXCollections.observableArrayList(p.information_SQLite());
    public static void main(String[] args) {

        launch(args);
    }

    private void def1(String s1, String s2 ,String i1, String i2){
        p.add_SQLite(s1, s2, Integer.parseInt( i1 ), Integer.parseInt( i2 ));
        langs.add("number: " + s1 + ", stamp: " + s2 + ", release year: " + Integer.parseInt( i1 ) + ", mileage: " + Integer.parseInt( i2 ));
    }
    private void def2(String s1, String s2 ,String i1, String i2){
        p.delete_SQLite(s1);
        langs.remove("number: " + s1 + ", stamp: " + s2 + ", release year: " + Integer.parseInt( i1 ) + ", mileage: " + Integer.parseInt( i2 ));
    }
    private void def3(String s1, String s2 ,String i1, String i2,String s1_c, String s2_c ,String i1_c, String i2_c){
        p.change_SQLite(s1_c, s2_c, Integer.parseInt( i1_c ), Integer.parseInt( i2_c ));
        langs.add("number: " + s1_c + ", stamp: " + s2_c + ", release year: " + Integer.parseInt( i1_c ) + ", mileage: " + Integer.parseInt( i2_c ));
        langs.remove("number: " + s1 + ", stamp: " + s2 + ", release year: " + Integer.parseInt( i1 ) + ", mileage: " + Integer.parseInt( i2 ));
    }

    public void start(Stage stage) throws Exception {

        p.create_SQLite();

        ListView<String> langsListView = new ListView<String>(langs);
        langsListView.setPrefSize(250, 150);

        Button addBtn = new Button("Add");
        Button deleteBtn = new Button("Delete");
        Button changeBtn = new Button("Change");

        Label label_number = new Label("number:");
        TextField textField_number = new TextField();
        Label label_stamp = new Label("stamp:");
        TextField textField_stamp = new TextField();
        Label label_release_year = new Label("release_year:");
        TextField textField_release_year = new TextField();
        Label label_mileage = new Label("mileage:");
        TextField textField_mileage = new TextField();

        addBtn.setOnAction(event -> def1(textField_number.getText(), textField_stamp.getText(), textField_release_year.getText() , textField_mileage.getText() ));
        deleteBtn.setOnAction(event ->def2(textField_number.getText(), textField_stamp.getText(), textField_release_year.getText() , textField_mileage.getText() ) );

        FlowPane buttonPane_stamp = new FlowPane(10,10, label_stamp, textField_stamp);
        FlowPane buttonPane_release_year = new FlowPane(10,10, label_release_year, textField_release_year, addBtn);

        FlowPane buttonPane2 = new FlowPane(10, 10, label_number, textField_number, deleteBtn, buttonPane_stamp, buttonPane_release_year, label_mileage, textField_mileage);

        Label label_number_change = new Label("number:");
        TextField textField_number_change = new TextField();
        Label label_stamp_change = new Label("stamp:");
        TextField textField_stamp_change = new TextField();
        Label label_release_year_change = new Label("release_year:");
        TextField textField_release_year_change = new TextField();
        Label label_mileage_change = new Label("mileage:");
        TextField textField_mileage_change = new TextField();

        changeBtn.setOnAction(event -> def3(textField_number.getText(), textField_stamp.getText(), textField_release_year.getText() , textField_mileage.getText() ,textField_number_change.getText(), textField_stamp_change.getText(),  textField_release_year_change.getText() ,  textField_mileage_change.getText() ) );

        FlowPane buttonPane_stamp_change = new FlowPane(10,10, label_stamp_change, textField_stamp_change);
        FlowPane buttonPane_release_year_change = new FlowPane(10,10, label_release_year_change, textField_release_year_change);

        FlowPane buttonPane3 = new FlowPane(10, 10, label_number_change, textField_number_change, changeBtn, buttonPane_stamp_change, buttonPane_release_year_change, label_mileage_change, textField_mileage_change);

        RadioButton s_number = new RadioButton("number");
        RadioButton s_stamp = new RadioButton("stamp");
        RadioButton s_release_year = new RadioButton("release year");
        RadioButton s_mileage = new RadioButton("mileage");

        ToggleGroup group = new ToggleGroup();
        s_number.setToggleGroup(group);
        s_stamp.setToggleGroup(group);
        s_release_year.setToggleGroup(group);
        s_mileage.setToggleGroup(group);

        s_number.setOnAction(event -> number = 0);
        s_stamp.setOnAction(event -> number = 1);
        s_release_year.setOnAction(event -> number = 2);
        s_mileage.setOnAction(event -> number = 3);

        Button sortingBtn = new Button("sorting");
        sortingBtn.setOnAction(event -> langs.setAll( p.information_sorting_SQLite(number)) );
        //sorting
        FlowPane buttonPane4 = new FlowPane(10, 10, s_number, s_stamp, s_release_year, s_mileage, sortingBtn);

        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10, langsListView, buttonPane2, buttonPane3, buttonPane4);

        Scene scene = new Scene(root, 800, 400);

        stage.setScene(scene);
        stage.setTitle("JavaFX x SQLite");
        stage.show();
    }
}
