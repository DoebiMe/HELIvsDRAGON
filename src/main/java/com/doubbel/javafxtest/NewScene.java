package com.doubbel.javafxtest;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class NewScene extends Scene {
    private oldSprite myHelicopter = new oldHelicopterSprite(4);
    private ArrayList<oldSprite> spritesToWaste = new ArrayList<>(50);
    private ArrayList<oldSprite> allSprites = new ArrayList<>(100);
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
