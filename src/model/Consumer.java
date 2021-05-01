package model;

import javafx.application.Platform;

import util.MyBuffer;
import view.ScreenView;

import java.util.List;

public class Consumer extends Thread {

    private MyBuffer buffer;

    public Consumer(MyBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true && getClass() != null) {
            while (ScreenView.consumerView1.getY() >= 300) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.consumerView1.setY(ScreenView.consumerView1.getY() - 1);
                    }
                });

                try {
                    sleep((int) (50 - ScreenView.consumerSld.getValue()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Consumer wants to consume");
            List consumedItem = buffer.remove();
            if (!consumedItem.isEmpty()) {
                System.out.println("Consumer consumed \"" + consumedItem.get(0) + "\"");
                System.out.println("Elements in queue " + consumedItem);
            } else {
                System.out.println("Elements in queue " + consumedItem);
            }

            while (ScreenView.consumerView1.getY() <= 550) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.consumerView1.setY(ScreenView.consumerView1.getY() + 1);
                    }
                });
                try {
                    sleep((int) (50 - ScreenView.consumerSld.getValue()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}