package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.Random;

public class Q3 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Creating a Stack Pane
        Pane pane = new Pane();

        //Creating a circle as the outer layer
        Circle circle = new Circle(225, 150, 100);
        Double circleRadius = circle.getRadius();
        Double circleDiamater = 2 * circleRadius;

        //Making the big circle lined and adding it to the pane
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        pane.getChildren().add(circle);


        //Creating 3 small circles
        Circle[] scircleArray = {new Circle(0, 0, 5, Color.RED), new Circle(0, 0, 5, Color.RED),
        new Circle(0, 0, 5, Color.RED)};

        //Creating random location when you first build the program
        randomLocation(scircleArray[0], circle);
        randomLocation(scircleArray[1], circle);
        randomLocation(scircleArray[2], circle);

        //Adding the smaller circles/points to the pane
        pane.getChildren().addAll(scircleArray);

        //Creating a triangle with three lines
        Line line1 = new Line(scircleArray[0].getCenterX(), scircleArray[0].getCenterY(), scircleArray[1].getCenterX(), scircleArray[1].getCenterY());
        Line line2 = new Line(scircleArray[1].getCenterX(), scircleArray[1].getCenterY(), scircleArray[2].getCenterX(), scircleArray[2].getCenterY());
        Line line3 = new Line(scircleArray[2].getCenterX(), scircleArray[2].getCenterY(), scircleArray[0].getCenterX(), scircleArray[0].getCenterY());

        //Adding the lines to the pane
        pane.getChildren().addAll(line1, line2, line3);


        //Calculating the length of the line between two points
        double A = Distance(scircleArray[2], scircleArray[1]);
        double B = Distance(scircleArray[2], scircleArray[0]);
        double C = Distance(scircleArray[1], scircleArray[0]);


        //Creating the angles for the triangles
        double angleA = Math.acos((A * A - B * B - C * C)/ (-2 * B * C));
        double angleB = Math.acos((B * B - A * A - C * C)/ (-2 * A * C));
        double angleC = Math.acos((C * C - A * A - B * B)/ (-2 * A * B));

        //Decimal Places
        DecimalFormat numberFormat = new DecimalFormat("#.00");

        //Creating Text for calculating the angle
        Text angletxtA = new Text(line1.getStartX()+15,line1.getStartY()+12,numberFormat.format(Math.toDegrees(angleA))+ "");
        Text angletxtB = new Text(line2.getStartX(),line2.getStartY()+12,numberFormat.format(Math.toDegrees(angleB))+ "");
        Text angletxtC = new Text(line3.getStartX(),line3.getStartY()+12,numberFormat.format(Math.toDegrees(angleC)) + "");

        pane.getChildren().addAll(angletxtA,angletxtB, angletxtC);

//---------------Creating mouse events for the drag for circle 1 ------------------------------------------------------------>
        scircleArray[0].setOnMouseDragged(e -> {
            //Making sure that the points is only within the big circle
            Point2D bigCircle = new Point2D(circle.getCenterX(), circle.getCenterY());
            Point2D mouse = new Point2D(e.getX(), e.getY());
            Point2D centerMouse = mouse.subtract(bigCircle);
            Point2D centerNewpoint = centerMouse.normalize().multiply(circle.getRadius());
            Point2D newPoint = centerNewpoint.add(bigCircle);

            //Getting the new X and Y points
            scircleArray[0].setCenterX(newPoint.getX());
            scircleArray[0].setCenterY(newPoint.getY());

            //Making the line 1 the same thing as scircleArray[0]
            line1.setStartX(scircleArray[0].getCenterX());
            line1.setStartY(scircleArray[0].getCenterY());

            //Making the line 3 the same thing as scircleArray[0]
            line3.setEndX(scircleArray[0].getCenterX());
            line3.setEndY(scircleArray[0].getCenterY());

            //Calcualting angle while moving
            double distA = Distance(scircleArray[2], scircleArray[1]);
            double distB = Distance(scircleArray[2], scircleArray[0]);
            double distC = Distance(scircleArray[1], scircleArray[0]);
            double lambangleA = Math.acos((distA * distA - distB * distB - distC * distC)/ (-2 * distB * distC));
            double lambangleB = Math.acos((distB * distB - distA * distA - distC * distC)/ (-2 * distA * distC));
            double lambangleC = Math.acos((distC * distC - distA * distA - distB * distB)/ (-2 * distA * distB));


            //Setting text for angle A
            angletxtA.setX(line1.getStartX() + 12);
            angletxtA.setY(line1.getStartY() + 12);
            angletxtA.setText(numberFormat.format(Math.toDegrees(lambangleA)) + "");

            //Setting text for angle B
            angletxtB.setX(line2.getStartX() + 12);
            angletxtB.setY(line2.getStartY() + 12);
            angletxtB.setText(numberFormat.format(Math.toDegrees(lambangleB)) + "");


            //Setting text for angle C
            angletxtC.setX(line3.getStartX() + 12);
            angletxtC.setY(line3.getStartY() + 12);
            angletxtC.setText(numberFormat.format(Math.toDegrees(lambangleC)) + "");

    });

//---------------Creating mouse events for the drag for circle 2 ------------------------------------------------------------>
        scircleArray[1].setOnMouseDragged(e -> {
            Point2D bigCircle = new Point2D(circle.getCenterX(), circle.getCenterY());
            Point2D mouse = new Point2D(e.getX(), e.getY());
            Point2D centerMouse = mouse.subtract(bigCircle);
            Point2D centerNewpoint = centerMouse.normalize().multiply(circle.getRadius());
            Point2D newPoint = centerNewpoint.add(bigCircle);
            scircleArray[1].setCenterX(newPoint.getX());
            scircleArray[1].setCenterY(newPoint.getY());


            //Making the line 2 the same thing as scircleArray[1]
            line2.setStartX(scircleArray[1].getCenterX());
            line2.setStartY(scircleArray[1].getCenterY());


            //Making the line 1 the same thing as scircleArray[1]
            line1.setEndX(scircleArray[1].getCenterX());
            line1.setEndY(scircleArray[1].getCenterY());

            //Calcualting angle while moving
            double distA = Distance(scircleArray[2], scircleArray[1]);
            double distB = Distance(scircleArray[2], scircleArray[0]);
            double distC = Distance(scircleArray[1], scircleArray[0]);
            double lambangleA = Math.acos((distA * distA - distB * distB - distC * distC)/ (-2 * distB * distC));
            double lambangleB = Math.acos((distB * distB - distA * distA - distC * distC)/ (-2 * distA * distC));
            double lambangleC = Math.acos((distC * distC - distA * distA - distB * distB)/ (-2 * distA * distB));


            //The reason why we have this is becaues we need to keep updating the angle
            //Setting text for angle A
            angletxtA.setX(line1.getStartX() + 12);
            angletxtA.setY(line1.getStartY() + 12);
            angletxtA.setText(numberFormat.format(Math.toDegrees(lambangleA)) + "");

            //Setting text for angle B
            angletxtB.setX(line2.getStartX() + 12);
            angletxtB.setY(line2.getStartY() + 12);
            angletxtB.setText(numberFormat.format(Math.toDegrees(lambangleB)) + "");


            //Setting text for angle C
            angletxtC.setX(line3.getStartX() + 12);
            angletxtC.setY(line3.getStartY() + 12);
            angletxtC.setText(numberFormat.format(Math.toDegrees(lambangleC)) + "");
        });

//---------------Creating mouse events for the drag for circle 3 ------------------------------------------------------------>
        scircleArray[2].setOnMouseDragged(e -> {
            Point2D bigCircle = new Point2D(circle.getCenterX(), circle.getCenterY());
            Point2D mouse = new Point2D(e.getX(), e.getY());
            Point2D centerMouse = mouse.subtract(bigCircle);
            Point2D centerNewpoint = centerMouse.normalize().multiply(circle.getRadius());
            Point2D newPoint = centerNewpoint.add(bigCircle);
            scircleArray[2].setCenterX(newPoint.getX());
            scircleArray[2].setCenterY(newPoint.getY());


            //Making the line 3 the same thing as scircleArray[2]
            line3.setStartX(scircleArray[2].getCenterX());
            line3.setStartY(scircleArray[2].getCenterY());

            //Making the line 2 the same thing as scircleArray[2]
            line2.setEndX(scircleArray[2].getCenterX());
            line2.setEndY(scircleArray[2].getCenterY());

            //Calcualting angle while moving
            double distA = Distance(scircleArray[2], scircleArray[1]);
            double distB = Distance(scircleArray[2], scircleArray[0]);
            double distC = Distance(scircleArray[1], scircleArray[0]);
            double lambangleA = Math.acos((distA * distA - distB * distB - distC * distC)/ (-2 * distB * distC));
            double lambangleB = Math.acos((distB * distB - distA * distA - distC * distC)/ (-2 * distA * distC));
            double lambangleC = Math.acos((distC * distC - distA * distA - distB * distB)/ (-2 * distA * distB));

            //The reason why we have this is becaues we need to keep updating the angle
            //Setting text for angle A
            angletxtA.setX(line1.getStartX() + 12);
            angletxtA.setY(line1.getStartY() + 12);
            angletxtA.setText(numberFormat.format(Math.toDegrees(lambangleA)) + "");

            //Setting text for angle B
            angletxtB.setX(line2.getStartX() + 12);
            angletxtB.setY(line2.getStartY() + 12);
            angletxtB.setText(numberFormat.format(Math.toDegrees(lambangleB)) + "");


            //Setting text for angle C
            angletxtC.setX(line3.getStartX() + 12);
            angletxtC.setY(line3.getStartY() + 12);
            angletxtC.setText(numberFormat.format(Math.toDegrees(lambangleC)) + "");

        });


        //Creating a scene for Q3
        primaryStage.setTitle("Q3");
        Scene scene = new Scene(pane, 450, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    //Functions so you can get a random point on the circle
    public void randomLocation(Circle smallerCircle, Circle bigCircle){
        double angle = Math.random() *360;
        double x = bigCircle.getCenterX() + bigCircle.getRadius()* Math.cos(Math.toRadians(angle));
        double y = bigCircle.getCenterY() + bigCircle.getRadius()*Math.sin(Math.toRadians(angle));
        smallerCircle.setCenterX(x);
        smallerCircle.setCenterY(y);
    }

    //Calcuationg the disctance of circles between two points
    public double Distance(Circle circle, Circle circle1){
        double A = new Point2D(circle.getCenterX(), circle.getCenterY()).distance(circle1.getCenterX(), circle1.getCenterY());
        return A;
    }


}
