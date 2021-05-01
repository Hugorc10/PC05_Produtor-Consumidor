package model;

import javafx.application.Platform;
import util.MyBuffer;
import view.ScreenView;

public class Producer extends Thread {

    private MyBuffer buffer;

    public Producer(MyBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (true && getClass() != null) {
            while (ScreenView.producerView1.getY() <= 240) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.producerView1.setY(ScreenView.producerView1.getY() + 1);
                    }
                });

                try {
                    sleep((int) (50 - ScreenView.producerSld.getValue()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Producer napping");
            int randomNumber = (int) (Math.random() * 100);
            System.out.println("\nProducer produced \"" + randomNumber + "\"");
            buffer.insert(randomNumber);

            while (ScreenView.producerView1.getY() >= 18) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ScreenView.producerView1.setY(ScreenView.producerView1.getY() - 1);
                    }
                });
                try {
                    sleep((int) (50 - ScreenView.producerSld.getValue()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
