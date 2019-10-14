package com.doubbel.javafxtest;

import com.doubbel.javafxtest.NewSpriteLogic;
import javafx.scene.image.ImageView;

public abstract class NewMasterSpriteWithLogic implements NewSpriteLogic {

    private double xPos;
    private double yPos;
    private int setPointTimeTaskDivider;
    private int currentTimeTaskDivider;
    private int currentImageIndex;
    private boolean changedCondition;
    private ImageView imageView;
    private NewSpriteLogicType typeLogic;

    NewMasterSpriteWithLogic(NewSpriteLogicType type) {
        this.typeLogic = type;
        loadAllImagesToList();
    }

    @Override
    public void setCurrentImageToIndex(int index) {
        currentImageIndex = index;
        changedCondition = true;
    }

    @Override
    public void setLocationAbsolute(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        changedCondition = true;
    }

    @Override
    public void setLocationRelative(double xMovement, double yMovement) {
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

    @Override
    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public int getCurrentTimeTaskDivider() {
        return currentTimeTaskDivider;
    }

    public NewSpriteLogicType getTypeLogic() {
        return typeLogic;
    }

    @Override
    public int getCurrentImageIndex() {
        return currentImageIndex;
    }

    @Override
    public double getXPos() {
        return xPos;
    }

    @Override
    public double getYPos() {
        return yPos;
    }

}
