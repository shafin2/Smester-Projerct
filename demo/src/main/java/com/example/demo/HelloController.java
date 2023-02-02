package com.example.demo;

import java.io.File;
import java.net.URL;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class HelloController implements Initializable {
    @FXML
    private Pane pane;
    @FXML
    private Label songLabel;
    @FXML
    private Button playButton, pauseButton, resetButton, previousButton, nextButton;
    @FXML
    private ComboBox<String> speedBox;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ProgressBar songProgressBar;
    @FXML
    private ImageView imageView;

    private Media media;
    private MediaPlayer mediaPlayer;
    private File directory;
    private File[] files;
    private ArrayList<File> songs;
    private int songNumber;
    private final int[] speeds = {25, 50, 75, 100, 125, 150, 175, 200};
    private Timer timer;
    private TimerTask task;
    private boolean running;
    private boolean isPlay = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        songs = new ArrayList<File>();
        directory = new File("E:\\User\\Music");
        files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                songs.add(file);
                System.out.println(file);
            }
        };
        media = new Media(songs.get(1).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(songs.get(songNumber).getName().substring(0, songs.get(songNumber).getName().length() - 4));

        for (int i = 0; i < speeds.length; i++) {
            speedBox.getItems().add(Integer.toString(speeds[i]) + "%");
        }

        Image image = new Image("icon.png");
        imageView.setImage(image);

        speedBox.setOnAction(this::changeSpeed);

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }

        });
    }

    public void browseMusic(ActionEvent event) {
        /*
            System.out.println("Browse button pressed");
            String location = JOptionPane.showInputDialog(null, "Please enter music location", "jMusic | Import Music", 1);
            directory = new File(location);
            files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    songs.add(file);
                }
            }
         */
        Window window = ((Node) (event.getSource())).getScene().getWindow();
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("MP3 Files (.mp3)", "*.mp3");
        fc.getExtensionFilters().add(ext);
        File song = fc.showOpenDialog(window);
        event.consume();
        songs.add(song);
    }

    public void handleMedia() {
        if (isPlay) {
            beginTimer();
            changeSpeed(null);
            mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            mediaPlayer.play();
            isPlay = false;
            pauseButton.setText("");
        } else {
            cancelTimer();
            mediaPlayer.pause();
            isPlay = true;
            pauseButton.setText("");
        }
    }

    public void resetMedia() {
        songProgressBar.setProgress(0);
        mediaPlayer.seek(Duration.seconds(0));
    }

    public void previousMedia() {
        if (songNumber > 0) {
            songNumber--;
            mediaPlayer.stop();
            if (running) {
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName().substring(0, songs.get(songNumber).getName().length() - 4));
        } else {
            songNumber = songs.size() - 1;
            mediaPlayer.stop();
            if (running) {
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName().substring(0, songs.get(songNumber).getName().length() - 4));
        }
        handleMedia();
    }

    public void nextMedia() {
        if (songNumber < songs.size() - 1) {
            songNumber++;
            mediaPlayer.stop();
            if (running) {
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName().substring(0, songs.get(songNumber).getName().length() - 4));
        } else {
            songNumber = 0;
            mediaPlayer.stop();
            if (running) {
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName().substring(0, songs.get(songNumber).getName().length() - 4));
        }
        handleMedia();
    }

    public void changeSpeed(ActionEvent event) {
        if (speedBox.getValue() == null) {
            mediaPlayer.setRate(1);
        } else {
            mediaPlayer.setRate(Integer.parseInt(speedBox.getValue().substring(0, speedBox.getValue().length() - 1)) * 0.01);
        }
    }

    public void closeApp() {
        Platform.exit();
        System.exit(0);
    }

    public void beginTimer() {
        timer = new Timer();
        task = new TimerTask() {
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                songProgressBar.setProgress(current / end);
            }
        };
        timer.scheduleAtFixedRate(task, 0, 500);
    }

    public void cancelTimer() {
        running = false;
    }
}