package com.doubbel.javafxtest;

public class NewDialog extends NewMasterSpriteWithLogic {
    private NewDialogType myDialogType;
    private int currentTimeTaskDivider;
    private int setPointTimeTaskDivider;

    NewDialog(int setPointTimeTaskDivider, NewDialogType newDialogType) {
        super(NewSpriteLogicType.DIALOG);
        this.setPointTimeTaskDivider = setPointTimeTaskDivider;
        loadAllImagesToList();
        myDialogType = NewDialogType.NEXT_LEVEL;
    }

    @Override
    public void loadAllImagesToList() {
        NewImageListAndFunctions.
                loadImageReturnSuccesCondition("nextlevel.png", 80);
        NewImageListAndFunctions.
                loadImageReturnSuccesCondition("hitenter.png", 81);
        NewImageListAndFunctions.
                loadImageReturnSuccesCondition("heli-3.png", 82);
        NewImageListAndFunctions.
                loadImageReturnSuccesCondition("heli-4.png", 83);
    }

    void setImageInFunctionOfDialogType() {
        switch (myDialogType) {
            case VOID: {
                NewUI.setSpriteVisability(getImageView(), false);
                break;
            }
            case NEXT_LEVEL: {
                setCurrentImageToIndex(80);
                NewUI.setSpriteVisability(getImageView(), true);
            }
            case PAUZE: {
                setCurrentImageToIndex(81);
                NewUI.setSpriteVisability(getImageView(), true);
                break;
            }
            case DEAD: {
                NewUI.setSpriteVisability(getImageView(), true);
                break;
            }
            case GET_READY: {
                NewUI.setSpriteVisability(getImageView(), !false);
                break;
            }
        }
        setLocationAbsolute( NewUI.calculateSpritePosLeftToCenterSpriteOnUI(getImageView()),
                NewUI.calculateSpritePosTopToCenterSpriteOnUI(getImageView()));
    }


    public boolean executeThingsToDoDependingOnCurrentTimeTaskDivider() {
        if (--currentTimeTaskDivider <= 0) {
            currentTimeTaskDivider = setPointTimeTaskDivider;
            executeThingsToDo();
            return true;
        }
        setImageInFunctionOfDialogType();//****************************************************
        return false;
    }

    @Override
    public void executeThingsToDo() {
        if (NewKeyHandler.keysOfInterest[NewKeyHandler.keyEnter]) {
            myDialogType = NewDialogType.VOID;
            setImageInFunctionOfDialogType();
            return;
        }
        if (NewKeyHandler.keysOfInterest[NewKeyHandler.key0]) {
            myDialogType = NewDialogType.PAUZE;
            setImageInFunctionOfDialogType();
        }
    }
}