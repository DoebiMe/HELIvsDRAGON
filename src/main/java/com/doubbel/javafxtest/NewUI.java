package com.doubbel.javafxtest;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewUI extends Application {
    static final int SCREEN_MAX_WIDTH = 800;
    static final int SCREEN_MAX_HEIGHT = 514;
    private static final int OUT_OF_SCREEN_ZONE = 100;
    static final int OUT_OF_SCREEN_MAX_WIDTH = SCREEN_MAX_WIDTH + OUT_OF_SCREEN_ZONE;
    static final int OUT_OF_SCREEN_MAX_HEIGHT = SCREEN_MAX_HEIGHT + OUT_OF_SCREEN_ZONE;
    static final int OUT_OF_SCREEN_MIN_WIDTH = 0 - OUT_OF_SCREEN_ZONE;
    static final int OUT_OF_SCREEN_MIN_HEIGTH = 0 - OUT_OF_SCREEN_ZONE;
    private static ObservableList<Node> myNodes;
    private static Pane myPane = null;
    private static boolean initialized = false;
    private NewKeyHandler myKeyHandler = new NewKeyHandler();

    static void setPane(Pane paneToUse) {
        myPane = paneToUse;
        myNodes = myPane.getChildren();
        initialized = true;
    }

    static boolean isInitialized() {
        return initialized;
    }

    static void main(String[] args) {
        launch();
    }

    static boolean isCollisionBasedOnImageView(ImageView first, ImageView second) {
        return first.getBoundsInParent().intersects(second.getBoundsInParent());
    }

    static ImageView createNodeOnUI() {
        ImageView imageView = new ImageView();
        myNodes.addAll(imageView);
        return imageView;
    }

    static  void updateSpriteImage(ImageView imageView, int imageIndex) {
        imageView.setImage(NewImageListAndFunctions.getImageFromListAtIndex(imageIndex));
    }

    static void  positionSprite(ImageView imageView, int x, int y) {
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
    }

    static void rotateSprite(ImageView imageView, int degree) {
        imageView.setRotate(degree);
    }

    static void scaleSprite(ImageView imageView, double scale) {
        imageView.setScaleY(scale);
        imageView.setScaleX(scale);
    }

    static void updateSpriteOnUI(NewSpriteLogic spriteToUpdate) {
        ImageView nodeToUpdate = spriteToUpdate.getImageView();
        if (nodeToUpdate == null) return ;
        nodeToUpdate.setLayoutX(spriteToUpdate.getxPos());
        nodeToUpdate.setLayoutY(spriteToUpdate.getyPos());
        nodeToUpdate.setImage(NewImageListAndFunctions.
                getImageFromListAtIndex(spriteToUpdate.getCurrentImageIndex()));
    }

    static void wasteNode(ImageView imageViewToWaste) {
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
