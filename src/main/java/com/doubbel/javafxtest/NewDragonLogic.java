package com.doubbel.javafxtest;

public class NewDragonLogic extends NewMasterSpriteWithLogic {
    NewDragonLogic() {
        super(NewSpriteLogicType.DRAGON);
    }

    @Override
    public void loadAllImagesToList() {
        NewImageListAndFunctions.
                loadImageReturnSuccesCondition("dr1.png", 5);
        NewImageListAndFunctions.
                loadImageReturnSuccesCondition("dr2.png", 6);
        NewImageListAndFunctions.
                loadImageReturnSuccesCondition("dr3.png", 7);
        setCurrentImageToIndex(5);
    }

    @Override
    public void executeThingsToDo() {
        if (getCurrentImageIndex() >= 7) {
            setCurrentImageToIndex(5);
        } else setCurrentImageToIndex(getCurrentImageIndex() + 1);
        setLocationRelative(-3, 0);
    }
}