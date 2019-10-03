package com.doubbel.javafxtest;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.KeyListener;

public class NewUI extends Application {
    private static ObservableList<Node> myNodes;
    private static Pane myPane = null;
    private static boolean initialized = false;
    private NewKeyHandler myKeyHandler = new NewKeyHandler();

    public static void setPane(Pane paneToUse) {
        myPane = paneToUse;
        myNodes = myPane.getChildren();
        System.out.println("Pane and node set");
        initialized = true;
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static Pane getMyPane() {
        return myPane;
    }


    public static void main(String[] args) {

        launch();
    }


    public static int addSpriteToUIReturnNodeReference(NewSpriteLogic spriteToAdd) {
        ImageView imageView = new ImageView(NewSpriteFunctionsAndImageHolder.
                getImageFromListAtIndex(0));
        myNodes.addAll(imageView);

        return myNodes.indexOf(imageView);
    }

    public static void updateSpriteOnUI(NewSpriteLogic spriteToUpdate) {
        ImageView nodeToUpdate = (ImageView) myNodes.get(spriteToUpdate.getIndexOnUI());
        nodeToUpdate.setLayoutX(spriteToUpdate.getxPos());
        nodeToUpdate.setLayoutY(spriteToUpdate.getyPos());
        nodeToUpdate.setImage(NewSpriteFunctionsAndImageHolder.
                getImageFromListAtIndex(spriteToUpdate.getCurrentImageIndex()));
    }

    @Override
    public void start(Stage stage) throws Exception {
        NewScene myScene = new NewScene(myPane, 300, 250);

        stage.setScene(myScene);

        myScene.setOnKeyPressed(keyEvent -> myKeyHandler.handleKeyPressed(keyEvent));
        myScene.setOnKeyReleased(keyEvent -> myKeyHandler.handleKeyReleased(keyEvent));

        stage.show();

    }
}
