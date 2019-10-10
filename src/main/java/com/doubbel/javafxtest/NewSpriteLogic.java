package com.doubbel.javafxtest;

import javafx.scene.image.ImageView;

interface NewSpriteLogic {
    int getxPos();

    int getyPos();

    NewSpriteLogicType getTypeLogic();

    void setLocationAbsolute(int xPos, int yPos);

    void setLocationRelative(int xMovement, int yMovement);

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
