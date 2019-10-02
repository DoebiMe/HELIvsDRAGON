package com.doubbel.javafxtest;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewUI extends Application {
    private  static ObservableList<Node> myNodes;
    private  static Pane myPane = null;

    public static void setPane(Pane paneToUse) {
        myPane = paneToUse;
        myNodes = myPane.getChildren();
        System.out.println("Pane and node set");
    }

    public static  Pane getMyPane() {
        return myPane;
    }


    public static   void main(String[] args) {

        launch();
    }

    public void launchUI(){
        //
    }

    public static void addSpriteToUI(NewSpriteLogic spriteToAdd) {
        myNodes.addAll(new ImageView(NewSpriteFunctionsAndImageHolder.getImageFromListAtIndex(0)));
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene( new NewScene(myPane, 300, 250));

        stage.show();
    }
}