package com.doubbel.javafxtest;

import javafx.scene.image.ImageView;

public class NewDialog extends NewMasterSpriteWithLogic {
    private NewDialogType myDialogType = NewDialogType.PAUZE;
    private int currentTimeTaskDivider;
    private int setPointTimeTaskDivider;

    NewDialog(int setPointTimeTaskDivider, NewDialogType newDialogType) {
        super(NewSpriteLogicType.DIALOG);
        this.setPointTimeTaskDivider = setPointTimeTaskDivider;
        loadAllImagesToList();
        setCurrentImageToIndex(80);

    }

    @Override
    public void loadAllImagesToList() {
        System.out.println(NewImageListAndFunctions.
                loadImageReturnSuccesCondition("nextlevel.png", 80));
        NewImageListAndFunctions.
                loadImageReturnSuccesCondition("heli-2.png", 81);
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
                NewUI.setSpriteVisability(getImageView(), true);
            }
            case DEAD: {
                NewUI.setSpriteVisability(getImageView(), true);
                break;
            }
            case PAUZE: {
                NewUI.setSpriteVisability(getImageView(), true);

                break;
            }
            case GET_READY: {
                if (true) ; // moet nog aangepast worden
                NewUI.setSpriteVisability(getImageView(), true);
                break;
            }
        }


    }

    void setMyDialogType(NewDialogType myDialogType) {
        this.myDialogType = myDialogType;
        setImageInFunctionOfDialogType();
    }

    public boolean executeThingsToDoDependingOnCurrentTimeTaskDivider() {
        //if (myDialogType == NewDialogType.VOID) return false;
        if (--currentTimeTaskDivider <= 0) {
            currentTimeTaskDivider = setPointTimeTaskDivider;
            executeThingsToDo();
            return true;
        }
        return false;
    }

    @Override
    public void executeThingsToDo() {
        //System.out.println("Hier");
        if (NewKeyHandler.keysOfInterest[NewKeyHandler.keyEnter]) {
            setCurrentImageToIndex(2);
        } else {
            System.out.println("Hier");
            setCurrentImageToIndex(80);
        }


    }
}