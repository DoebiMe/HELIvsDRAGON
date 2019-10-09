package com.doubbel.javafxtest;

public class NewBulletLogic extends NewMasterSpriteWithLogic {
    NewBulletLogic() {
        super(NewSpriteLogicType.BULLET);
    }

    @Override
    public void executeThingsToDo() {
        setLocationRelative(+8, 0);
    }

    @Override
    public void loadAllImagesToList() {
        NewImageListAndFunctions.
                loadImageReturnSuccesCondition("bullet.png", 8);
        //setCurrentImageToIndex(8);
    }
}