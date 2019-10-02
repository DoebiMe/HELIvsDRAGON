package com.doubbel.javafxtest;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class NewScene extends Scene {
    private Sprite myHelicopter = new HelicopterSprite(4);
    private ArrayList<Sprite> spritesToWaste = new ArrayList<>(50);
    private ArrayList<Sprite> allSprites = new ArrayList<>(100);
    private ObservableList<Node> myNodes;

    NewScene(Pane aPane, int width, int height) {
        super(aPane, width, height);
        System.out.println("Inside SceneWithKeyAndTaskEvent "+aPane.toString());
        initializeMyNodes(aPane);
    }
    public ObservableList<Node> getMyNodes() {
        return myNodes;
    }

    private void initializeMyNodes(Pane myPane) {
        myNodes = myPane.getChildren();
        //myNodes.addAll(myHelicopter.getCurrentImageView());
    }



}
