package com.doubbel.javafxtest;

import javafx.scene.image.ImageView;

interface NewSpriteLogic {
    double getXPos();

    double getYPos();

    NewSpriteLogicType getTypeLogic();

    void setLocationAbsolute(double xPos, double yPos);

    void setLocationRelative(double xMovement, double yMovement);

    NewSpriteLogic setSetPointTimeTaskDivider(int setPointTimeTaskDivider);

    int getCurrentTimeTaskDivider();

    boolean executeThingsToDoDependingOnCurrentTimeTaskDivider();

    void executeThingsToDo();

    void setCurrentImageToIndex(int index);

    int getCurrentImageIndex();

    void loadAllImagesToList();

    boolean isChangedStateForUI();

    void setToUnChangedState();

    ImageView getImageView();

    void setImageView(ImageView imageView);
}
