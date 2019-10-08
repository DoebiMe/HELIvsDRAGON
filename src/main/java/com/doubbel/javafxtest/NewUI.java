package com.doubbel.javafxtest;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewUI extends Application {
    static final int SCREEN_MAX_WIDTH = 800;
    static final int SCREEN_MAX_HEIGHT = 600;
    private static final int OUT_OF_SCREEN_ZONE = 100;
    static final int OUT_OF_SCREEN_MAX_WIDTH = SCREEN_MAX_WIDTH + OUT_OF_SCREEN_ZONE;
    public static final int OUT_OF_SCREEN_MAX_HEIGHT = SCREEN_MAX_HEIGHT + OUT_OF_SCREEN_ZONE;
    static final int OUT_OF_SCREEN_MIN_WIDTH = 0 - OUT_OF_SCREEN_ZONE;
    public static final int OUT_OF_SCREEN_MIN_HEIGTH = 0 - OUT_OF_SCREEN_ZONE;
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

    public static boolean isCollisionBasedOnImageView(ImageView first, ImageView second) {
        return first.getBoundsInParent().intersects(second.getBoundsInParent());
    }

    public static ImageView addSpriteToUIReturnImageView() {
        ImageView imageView = new ImageView();
        myNodes.addAll(imageView);
        return imageView;
    }

    public static int getIndexOfImageView(ImageView imageView) {
        return myNodes.indexOf(imageView);
    }


    public static void updateSpriteOnUI(NewSpriteLogic spriteToUpdate) {
        ImageView nodeToUpdate = spriteToUpdate.getImageView();
        if (nodeToUpdate != null) {
            nodeToUpdate.setLayoutX(spriteToUpdate.getxPos());
            nodeToUpdate.setLayoutY(spriteToUpdate.getyPos());
            nodeToUpdate.setImage(NewImageListAndFunctions.
                    getImageFromListAtIndex(spriteToUpdate.getCurrentImageIndex()));
        }
    }

    public static void wasteNode(ImageView imageViewToWaste) {
        myNodes.remove(imageViewToWaste);
    }

    @Override
    public void start(Stage stage) {
        NewScene myScene = new NewScene(myPane, SCREEN_MAX_WIDTH, SCREEN_MAX_HEIGHT);
        stage.setScene(myScene);
        myScene.setOnKeyPressed(keyEvent -> myKeyHandler.handleKeyPressed(keyEvent));
        myScene.setOnKeyReleased(keyEvent -> myKeyHandler.handleKeyReleased(keyEvent));
        stage.show();
    }
}
