package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.Year;
import java.util.concurrent.Future;

public class Q2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Creating a flow pane
        GridPane pane = new GridPane();

        //Making a font
        Font font = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 17);

        //Setting Text Fields to input data;
        TextField InvestAmount = new TextField();
        TextField years = new TextField();
        TextField AnnualRate = new TextField();
        TextField FutureValue = new TextField();

        //Setting a font for the label
        Label Investlabel = new Label("Invest Amount: ");
        Investlabel.setFont(font);
        Label Yearlabel = new Label("Years: ");
        Yearlabel.setFont(font);
        Label Annuallabel = new Label("Annual Rate: ");
        Annuallabel.setFont(font);
        Label Futurelabel = new Label("Future Value: ");
        Futurelabel.setFont(font);


        pane.add(Investlabel, 0, 0);
        pane.add(InvestAmount, 1, 0);

        pane.add(Yearlabel, 0, 1);
        pane.add(years, 1, 1);

        pane.add(Annuallabel, 0, 2);
        pane.add(AnnualRate, 1, 2);

        pane.add(Futurelabel, 0, 3);
        pane.add(FutureValue, 1, 3);



        //Creating Calculate Button
        HBox hbox = new HBox(5);
        Button btnCalculate = new Button("Calculate");
        hbox.setAlignment(Pos.CENTER);
        pane.add(btnCalculate, 0, 4);


        //This is to create the screen and naming it
        primaryStage.setTitle("Q2");
        Scene scene = new Scene(pane, 450, 200);
        primaryStage.setScene(scene);
        primaryStage.show();


        //Creating a function for Button
        btnCalculate.setOnAction(e ->{
            FutureValue.setText(Double.parseDouble(InvestAmount.getText()) * Math.pow(1 + (Double.parseDouble(AnnualRate.getText())/12)/100, Double.parseDouble(years.getText()) * 12) + "");
        });


    }


    public static void main(String[] args) {
        launch(args);
    }
}





