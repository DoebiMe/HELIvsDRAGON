package com.doubbel.javafxtest;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class NewScene extends Scene {
    private NewSpriteLogic myHelicopter = new NewHelicopterLogic();
    private ArrayList<NewSpriteLogic> spritesToWaste = new ArrayList<>(50);
    private ArrayList<NewSpriteLogic> allSprites = new ArrayList<>(100);
    private ObservableList<Node> myNodes;

    NewScene(Pane aPane, int width, int height) {
        super(aPane, width, height);
        initializeMyNodes(aPane);
    }

    ObservableList<Node> getMyNodes() {
        return myNodes;
    }

    private void initializeMyNodes(Pane myPane) {
        myNodes = myPane.getChildren();
        //myNodes.addAll(myHelicopter.getCurrentImageView());
    }


}
