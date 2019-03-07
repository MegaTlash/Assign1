import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.Scanner;
import java.io.*;

public class Question4 extends Application {

  public void start(Stage primaryStage) throws Exception {
      makeHistogram(primaryStage);
  }

  public void makeHistogram(Stage primaryStage) throws Exception
  {
    //Creates a border pane
    BorderPane pane = new BorderPane();

    //-------------------------------------- Making VBOX -------------------------------------

    //Creates VBox
    VBox vBox = new VBox(15);
    vBox.setPadding(new Insets(15, 5, 5, 5));

    //Defining the axes
    CategoryAxis xAxis = new CategoryAxis();
    xAxis.setCategories(FXCollections.<String>
    observableArrayList(Arrays.asList("A", "B", "C","D","E","F","G","H","I","J",
    "K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")));
    xAxis.setLabel("Letter");

    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Occurence");

    //Creating the Bar Chart
    BarChart<String,Number> barChart = new BarChart<>(xAxis,yAxis);

    //Creates empty Bar Chart
    XYChart.Series<String,Number> series1 = new XYChart.Series<>();
    series1.setName("# of occurences");
    for (int x = 0; x < 26; x++){
      series1.getData().add(new XYChart.Data(String.valueOf((char)(65+x)), 0));
    }
    /*
    for (int x = 0; x < alphabetint.length; x++){
      series1.getData().add(new XYChart.Data(String.valueOf((char)(65+x)), alphabetint[x]));
    }
    */

    //Adding data to bar chart
    barChart.getData().add(series1);

    //Creating a Group object
    Group root = new Group(barChart);

    //Adds bar graph to pane
    vBox.getChildren().add(root);

    //-------------------------------------- Making HBOX -------------------------------------

    //Creates HBox
    HBox hBox = new HBox(15);
    hBox.setPadding(new Insets(15, 15, 15, 15));

    //Creates textfield to enter filename
    TextField tfFileName = new TextField();

    //Create view button
    Button btView = new Button("View");

    //Adds Label, Textfield & Button to pane
    hBox.getChildren().add(new Label("Filename:"));
    hBox.getChildren().add(tfFileName);
    hBox.getChildren().add(btView);

    btView.setOnAction(e -> {
        String fileName = tfFileName.getText();
        int[] alphabetVal = readNewFile(fileName);
        ReplaceHistogram(alphabetVal, series1, barChart, vBox);
    });

    //-------------------------------------- Making Stage -------------------------------------

    //Places nodes in the pane
    pane.setTop(vBox);
    pane.setBottom(hBox);

    //Creates scene & places it in the stage
    Scene scene = new Scene(pane);
    primaryStage.setTitle("Question 4");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public int[] readNewFile(String fileName) {
    Scanner input = null;

    //Creates a File instance
    File file = new File(fileName);

    try {
      input = new Scanner(file);

    }
    catch(FileNotFoundException err) {
      System.out.println("ERROR - File not found");
    }

      //Holds frequency of letters
      int[] alphabetint = new int[26];

      //Creates array of alphabet
      char alphabet[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
              'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
              'w', 'x', 'y', 'z'};

      //Creates a Scanner for the file

      String letter = "";
      while (input.hasNext()) {
        String str = input.next();
        letter = letter + str;
      }

      //Closes the file
      input.close();

      //Converting the letters to lower case
      letter = letter.toLowerCase();

      for (int x = 0; x  < alphabet.length; x++){
        for (int y = 0; y < letter.length(); y++){
          if (letter.charAt(y) == alphabet[x]){
            alphabetint[x] += 1;
          }
        }
      }
      return alphabetint;
    }

  public void ReplaceHistogram(int[] alphabetint, XYChart.Series series1, BarChart barChart, VBox vBox){

    for (int x = 0; x < alphabetint.length; x++){
      series1.getData().add(new XYChart.Data(String.valueOf((char)(65+x)), 0));
    }

    for(int x = 0; x < alphabetint.length; x++){
      series1.getData().add(new XYChart.Data(String.valueOf((char)(65+x)), alphabetint[x]));
    }

    //Adding data to bar chart
    barChart.getData().add(series1);

    //Creating a Group object
    Group root = new Group(barChart);

    //Adds bar graph to pane
    vBox.getChildren().add(root);
  }

  public static void main(String[] args) {
    launch(args);
  }
}
