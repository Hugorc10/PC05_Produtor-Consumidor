/*
  Created by Hugo Teixeira Mafra <hugorc10@hotmail.com> on 25/08/2018. Last modification on 27/08/2018.
  <p>
  Enrollment number: 201611540
  <p>
  Consumer Problem it is a software a classic example of a multi-process synchronization problem.
  <p>
  Consumer Problem is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.
  <p>
  Consumer Problem is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
  GNU General Public License for more details.
 */

import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import model.Consumer;
import model.Producer;
import util.MyBuffer;
import view.ScreenView;

public class Principal extends Application {

    private final String PACMAN_BEGNNING = "/audios/pacman_beginning.wav";

    Producer producer;
    Consumer consumer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ScreenView sv = new ScreenView();

        MyBuffer buffer = new MyBuffer();

        producer = new Producer(buffer);
        consumer = new Consumer(buffer);

        Scene scene = new Scene(sv.createContent());

        ScreenView.startBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!producer.isAlive() && !consumer.isAlive()) {
                    URL file = getClass().getResource(PACMAN_BEGNNING);
                    try {
                        Clip clip = AudioSystem.getClip();
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
                        clip.open(inputStream);
                        clip.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ScreenView.startBtn.setDisable(true);
                    consumer.setDaemon(true);
                    consumer.start();
                    producer.setDaemon(true);
                    producer.start();
                }
            }
        });

        primaryStage.setTitle("Consumer Problem");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
