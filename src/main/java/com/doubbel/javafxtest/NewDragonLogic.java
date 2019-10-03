package com.doubbel.javafxtest;

public class NewDragonLogic extends NewMasterSpriteWithLogic {
    @Override
    public void loadAllImagesToList() {
        NewSpriteFunctionsAndImageHolder.
                isSuccesLoadImageToListAtIndex("dr1.png", 4);
        NewSpriteFunctionsAndImageHolder.
                isSuccesLoadImageToListAtIndex("dr2.png", 5);
        NewSpriteFunctionsAndImageHolder.
                isSuccesLoadImageToListAtIndex("dr3", 6);
        setCurrentImageToIndex(10);
    }

    @Override
    public void executeThingsToDo() {
        if (getCurrentImageIndex() >= 6) {
            setCurrentImageToIndex(4);
        } else setCurrentImageToIndex(getCurrentImageIndex() + 1);

        setLocationRelative(-1, 0);
    }
}