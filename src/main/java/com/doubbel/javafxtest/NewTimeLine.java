package com.doubbel.javafxtest;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class NewTimeLine {

    public NewTimeLine(NewTaskDelegator myTaskDelegator) {
        Timeline myTimeLine;
        myTimeLine = new Timeline(new KeyFrame(Duration.millis(12), actionEvent -> {
            myTaskDelegator.executeToDo();
        }));
        myTimeLine.setCycleCount(Timeline.INDEFINITE);
        myTimeLine.play();
    }

}
