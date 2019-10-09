package com.doubbel.javafxtest;

import javafx.scene.layout.Pane;

// MOET PUBLIC CLASS BLIJVEN !!!! NEVERMIND INTELLIJ
public class NewStartUp {
    public static void main(String[] args) {
        Pane myPane = new Pane();
        NewTaskDelegator myTaskDelegator = new NewTaskDelegator();
        NewTimeLine myTimeLine = new NewTimeLine(myTaskDelegator);
        NewUI.setPane(myPane);
        NewUI.main(new String[]{""});
    }
}
