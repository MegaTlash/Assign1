import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.lang.Math;

public class Question2 extends Application {
  //Creates text fields
  public TextField tfInvest = new TextField();
  public TextField tfYears = new TextField();
  public TextField tfAnnual = new TextField();
  public TextField tfFuture = new TextField();

  @Override
  public void start(Stage primaryStage) {
    //Creates a grid pane
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.TOP_LEFT);
    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
    pane.setHgap(5.5);
    pane.setVgap(5.5);

    //Adds text fields to pane
    pane.add(new Label("Investment Amount:"), 0, 0);
    pane.add(tfInvest, 1, 0);

    pane.add(new Label("Years:"), 0, 1);
    pane.add(tfYears, 1, 1);

    pane.add(new Label("Annual Interest Rate:"), 0, 2);
    pane.add(tfAnnual, 1, 2);

    pane.add(new Label("Future Value:"), 0, 3);
    pane.add(tfFuture, 1, 3);

    //Disable TextField
    tfFuture.setDisable(true);

    //Right aligning textfields
    tfFuture.setAlignment(Pos.CENTER_RIGHT);
    tfInvest.setAlignment(Pos.CENTER_RIGHT);
    tfYears.setAlignment(Pos.CENTER_RIGHT);
    tfAnnual.setAlignment(Pos.CENTER_RIGHT);

    //Creates the calculate button
    Button btCalculate = new Button("Calculate");
    pane.add(btCalculate, 1, 4);
    GridPane.setHalignment(btCalculate, HPos.RIGHT);
    CalculateHandlerClass handler1 = new CalculateHandlerClass();
    btCalculate.setOnAction(handler1);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 350, 200);
    primaryStage.setTitle("Question 2");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  //Performs calculation for Future Value & places value in its textfield
  class CalculateHandlerClass implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent e) {
      Double yearsCalc = Double.valueOf(tfYears.getText()) * 12;
      Double futureValue = Double.valueOf(tfInvest.getText()) * Math.pow(1 + (Double.valueOf(tfAnnual.getText())/12)/100, yearsCalc);
      tfFuture.setText(futureValue.toString());
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
