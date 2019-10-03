package com.doubbel.javafxtest;

import javafx.scene.image.ImageView;

public interface NewSpriteLogic {
    public int getxPos() ;
    public int getyPos() ;
    void setLocationAbsolute(int xPos, int yPos);
    void setLocationRelative(int xMovement, int yMovement);
    NewSpriteLogic setSetPointTimeTaskDivider(int setPointTimeTaskDivider);
    int getCurrentTimeTaskDivider();
    boolean executeThingsToDoDependingOnCurrentTimeTaskDivider();
    void executeThingsToDo();
    void setCurrentImageToIndex(int index);
    int getCurrentImageIndex();
    void setIndexOnUI(int index);
    int getIndexOnUI();
    void loadAllImagesToList();
    boolean isChangedStateForUI();
    void setToUnChangedState();
}
