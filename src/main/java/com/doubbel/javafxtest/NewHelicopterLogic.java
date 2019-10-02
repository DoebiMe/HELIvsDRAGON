package com.doubbel.javafxtest;

public class NewHelicopterLogic extends NewMasterSpriteWithLogic {
    @Override
    public void loadAllImagesToList() {
        NewSpriteFunctionsAndImageHolder.
                isSuccesLoadImageToListAtIndex("heli-1.png", 0);
        NewSpriteFunctionsAndImageHolder.
                isSuccesLoadImageToListAtIndex("heli-2.png", 1);
        NewSpriteFunctionsAndImageHolder.
                isSuccesLoadImageToListAtIndex("heli-3.png", 2);
        NewSpriteFunctionsAndImageHolder.
                isSuccesLoadImageToListAtIndex("heli-4.png", 3);

    }

    @Override
    public void executeThingsToDo() {
    }
}
