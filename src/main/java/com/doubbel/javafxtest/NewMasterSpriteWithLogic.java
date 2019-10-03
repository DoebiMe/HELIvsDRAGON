package com.doubbel.javafxtest;

import com.doubbel.javafxtest.NewSpriteLogic;

public abstract class NewMasterSpriteWithLogic implements NewSpriteLogic {

    private int xPos;
    private int yPos;
    int setPointTimeTaskDivider;
    int currentTimeTaskDivider;
    int currentImageIndex;
    int indexOnUI = -1;
    private boolean changedCondition;
    private static boolean areImagesLoaded;

    public NewMasterSpriteWithLogic() {
       // if (!areImagesLoaded) {
            loadAllImagesToList();
            areImagesLoaded = true;
      //  }
    }

    @Override
    public int getxPos() {
        return xPos;
    }

    @Override
    public int getyPos() {
        return yPos;
    }

    @Override
    public void setIndexOnUI(int index) {
        indexOnUI = index;
    }
    @Override
    public int getIndexOnUI() {
        return indexOnUI;
    }

    @Override
    public void setCurrentImageToIndex(int index) {
        currentImageIndex = index;
        changedCondition = true;
    }

    @Override
    public int getCurrentImageIndex() {
        return currentImageIndex;
    }

    @Override
    public void setLocationAbsolute(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        changedCondition = true;
    }

    @Override
    public void setLocationRelative(int xMovement, int yMovement) {
        xPos += xMovement;
        yPos += yMovement;
        changedCondition = true;
    }

    @Override
    public boolean isChangedStateForUI() {
        return changedCondition;
    }

    @Override
    public void setToUnChangedState() {
        changedCondition = false;
    }

    @Override
    public NewSpriteLogic setSetPointTimeTaskDivider(int setPointTimeTaskDivider) {
        this.setPointTimeTaskDivider = setPointTimeTaskDivider;
        return this;
    }

    @Override
    public int getCurrentTimeTaskDivider() {
        return currentTimeTaskDivider;
    }

    @Override
    public boolean executeThingsToDoDependingOnCurrentTimeTaskDivider() {
        currentTimeTaskDivider--;
        if (currentTimeTaskDivider <= 0) {
           currentTimeTaskDivider = setPointTimeTaskDivider;
            executeThingsToDo();
           return true;
        }
        return false;
    }
    @Override
    public abstract void executeThingsToDo();

    @Override
    public abstract void loadAllImagesToList();
}
