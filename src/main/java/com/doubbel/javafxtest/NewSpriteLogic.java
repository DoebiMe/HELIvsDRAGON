package com.doubbel.javafxtest;

import javafx.scene.image.ImageView;

public interface NewSpriteLogic {
    void setLocationAbsolute(int xPos, int yPos);
    void setLocationRelative(int xMovement, int yMovement);
    NewSpriteLogic setSetPointTimeTaskDivider(int setPointTimeTaskDivider);
    int getCurrentTimeTaskDivider();
    boolean executeThingsToDoDependingOnCurrentTimeTaskDivider();
    void executeThingsToDo();
    void setCurrentImageWithIndex(int index);
    int getCurrentImageIndex();
    void loadAllImagesToList();
    boolean isChangedStateForUI();
    void setToUnChangedState();
}
