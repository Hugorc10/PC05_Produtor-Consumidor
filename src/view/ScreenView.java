package view;

import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

public class ScreenView {

    public static Button startBtn;
    public static Slider producerSld;
    public static Slider consumerSld;
    public static ImageView producerView1;
    public static ImageView consumerView1;
    public static ImageView fruitsView;
    public static Pane pane;

    public Parent createContent() {
        pane = new Pane();
        pane.setPrefSize(620, 680);

        Image labyrinthImg = new Image(getClass().getResourceAsStream("/img/labyrinth.png"));

        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
                true, true, true, false);

        BackgroundImage backgroundImage = new BackgroundImage(labyrinthImg, null, null,
                null, backgroundSize);

        Background labyrinthBkgd = new Background(backgroundImage);

        pane.setBackground(labyrinthBkgd);

        startBtn = new Button("Start");
        startBtn.setDefaultButton(true);
        startBtn.setPrefSize(97.5, 35);
        startBtn.setLayoutX(265);
        startBtn.setLayoutY(295);

        Image producerImg = new Image(getClass().getResourceAsStream("/img/ghost.png"));

        producerView1 = new ImageView(producerImg);
        producerView1.setFitHeight(60);
        producerView1.setSmooth(true);
        producerView1.setPreserveRatio(true);
        producerView1.setX(120);
        producerView1.setY(18);

        ImageView producerView2 = new ImageView(producerImg);
        producerView2.setFitHeight(30);
        producerView2.setSmooth(true);
        producerView2.setPreserveRatio(true);
        producerView2.setX(200);
        producerView2.setY(125);

        producerSld = new Slider(0, 49, 25);
        producerSld.setPrefWidth(100);
        producerSld.setOrientation(Orientation.HORIZONTAL);
        producerSld.setShowTickMarks(true);
        producerSld.setMajorTickUnit(12.5f);
        producerSld.setBlockIncrement(5);
        producerSld.setLayoutX(200);
        producerSld.setLayoutY(170);

        fruitsView = new ImageView();
        fruitsView.setFitWidth(65);
        fruitsView.setPreserveRatio(true);
        fruitsView.setSmooth(true);
        fruitsView.setX(115);
        fruitsView.setY(280);

        Image consumerImg = new Image(getClass().getResourceAsStream("/img/pacman.gif"));

        consumerView1 = new ImageView(consumerImg);
        consumerView1.setFitHeight(50);
        consumerView1.setSmooth(true);
        consumerView1.setPreserveRatio(true);
        consumerView1.setRotate(-90);
        consumerView1.setX(125);
        consumerView1.setY(550);

        ImageView consumerView2 = new ImageView(consumerImg);
        consumerView2.setFitHeight(25);
        consumerView2.setSmooth(true);
        consumerView2.setPreserveRatio(true);
        consumerView2.setX(400);
        consumerView2.setY(125);

        consumerSld = new Slider(0, 49, 25);
        consumerSld.setPrefWidth(100);
        consumerSld.setOrientation(Orientation.HORIZONTAL);
        consumerSld.setShowTickMarks(true);
        consumerSld.setMajorTickUnit(12.5f);
        consumerSld.setBlockIncrement(5);
        consumerSld.setLayoutX(330);
        consumerSld.setLayoutY(170);

        pane.getChildren().addAll(startBtn, producerSld, consumerSld, consumerView1,
                consumerView2, producerView1, producerView2, fruitsView);

        return pane;
    }
}

