package com.doubbel.javafxtest;

import com.doubbel.javafxtest.NewSpriteLogic;
import javafx.scene.image.ImageView;

public abstract class NewMasterSpriteWithLogic implements NewSpriteLogic {

    private int xPos;
    private int yPos;
    private int setPointTimeTaskDivider;
    private int currentTimeTaskDivider;
    private int currentImageIndex;
    private boolean changedCondition;

    @Override
    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    private ImageView imageView;


    public NewSpriteLogicType getTypeLogic() {
        return typeLogic;
    }

    private NewSpriteLogicType typeLogic;

    NewMasterSpriteWithLogic(NewSpriteLogicType type) {
        this.typeLogic = type;
        loadAllImagesToList();
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
