package com.doubbel.javafxtest;

public class NewBackGroundLogic extends NewMasterSpriteWithLogic {

    private final long BACKGROUND_RESET_POINT_HORIZONTAL = 5996-NewUI.SCREEN_MAX_WIDTH;

    NewBackGroundLogic() {
        super(NewSpriteLogicType.BACKGROUND);
    }

    @Override
    public void executeThingsToDo() {
        if (getxPos() > BACKGROUND_RESET_POINT_HORIZONTAL) {
            setLocationAbsolute(0, 0);
        } else {
            setLocationRelative(-1, 0);
        }
    }


    @Override
    public void loadAllImagesToList() {
        NewImageListAndFunctions.
                loadImageReturnSuccesCondition("tryout.png", 0);
                //loadImageReturnSuccesCondition("background.png", 0);

    }
}
