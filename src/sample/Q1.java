package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Q1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<=54; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);

        //Creating a Pane to use to add images
        HBox pane = new HBox();


        for (int x = 0; x < 3; x++) {
            //Creating a random number
            //Creating an imageView image with the random number
            ImageView imageCard = new ImageView("file:/../Cards/" + list.get(x) + ".png");
            pane.getChildren().add(imageCard);
        }

        primaryStage.setTitle("Q1");
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
