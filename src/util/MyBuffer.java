package util;

import javafx.application.Platform;
import javafx.scene.image.Image;
import view.ScreenView;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class MyBuffer {

    private static final int BUFFER_SIZE = 4; // tamanho maximo do array do buffer
    public static int count; // numero de itens no buffer
    private List<Integer> buffer; // array de objetos
    private Semaphore mutex, empty, full;
    private String PACMAN_EATFRUIT = "/audios/pacman_eatfruit.wav";

    public MyBuffer() {
        // buffer eh iniciamente vazio
        count = 0;
        buffer = new ArrayList<>(BUFFER_SIZE);
        mutex = new Semaphore(1); // 1 para exclusao mutua
        empty = new Semaphore(BUFFER_SIZE); // array comeca com todos os elementos vazios
        full = new Semaphore(0); // array comeca sem elementos
    }

    // produtor chama esse metodo
    public void insert(int item) {
        try {
            empty.acquire();
            mutex.acquire(); // exclusao mutua
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        // adiciona um item no buffer
        ++count;
        buffer.add(item);
        putItemIntoBuffer();

        // informacao de feedback do buffer
        if (count == BUFFER_SIZE) {
            System.out.println("Producer inserted \"" + buffer.get(0) + "\" count = " + count);
            System.out.println("Elements in queue " + buffer);
            System.out.println("BUFFER FULL");
        } else {
            System.out.println("Producer inserted \"" + buffer.get(0) + "\" count = " + count);
            System.out.println("Elements in queue " + buffer);
        }

        mutex.release(); // exclusao mutua
        full.release();
        // se buffer esta vazio, entao acorda o consumidor
    }

    // consumer chama esse metodo
    public List<Integer> remove() {
        try {
            full.acquire();
            mutex.acquire(); // exclusao mutua

        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        // remove um item do buffer
        --count;
        if (!buffer.isEmpty()) {
            System.out.println("\nConsumer removed \"" + buffer.get(0) + "\" count = " + count);
            int item = buffer.remove(0);
        }
        removeItemFromBuffer();

        // feedback de informacao do buffer
        if (buffer.isEmpty()) {
            System.out.println("BUFFER EMPTY");
        }
//        } else {
//            System.out.println("\nConsumer removed \"" + buffer.get(0) + "\" count = " + count);
//        }

        mutex.release(); // exclusao mutua
        empty.release();
        // se buffer esta cheio, entao acorda o produtor
        return buffer;
    }

    private void putItemIntoBuffer() {
        Image ballImg = new Image(getClass().getResourceAsStream("/img/fruit" + count + ".png"));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ScreenView.fruitsView.setImage(ballImg);
            }
        });
    }

    private void removeItemFromBuffer() {
        if (count < 1) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.fruitsView.setImage(null);
                }
            });
        } else {
            Image fruitsImg = new Image(getClass().getResourceAsStream("/img/fruit" + count + ".png"));
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ScreenView.fruitsView.setImage(fruitsImg);
                }
            });
            URL file = getClass().getResource(PACMAN_EATFRUIT);
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}