package com.doubbel.javafxtest;

public class NewHelicopterLogic extends NewMasterSpriteWithLogic {
    public NewHelicopterLogic() {
        super( NewSpriteLogicType.HELICOPTER);
    }

    @Override
    public void loadAllImagesToList() {
        NewImageListAndFunctions.
                loadImageReturnSuccesCondition("heli-1.png", 1);
        NewImageListAndFunctions.
                loadImageReturnSuccesCondition("heli-2.png", 2);
        NewImageListAndFunctions.
                loadImageReturnSuccesCondition("heli-3.png", 3);
        NewImageListAndFunctions.
                loadImageReturnSuccesCondition("heli-4.png", 4);
    }

    @Override
    public void executeThingsToDo() {
        processKeyInputFromUI();
        if (getCurrentImageIndex() >=4 ) {
            setCurrentImageToIndex(1);
        }
        else setCurrentImageToIndex(getCurrentImageIndex()+1);
        //setLocationRelative(+1,0);
    }

    private void processKeyInputFromUI() {
       if (NewKeyHandler.keysOfInterest[NewKeyHandler.keyDn])
           setLocationRelative(0,1);
       if (NewKeyHandler.keysOfInterest[NewKeyHandler.keyUp])
           setLocationRelative(0,-1);
       if (NewKeyHandler.keysOfInterest[NewKeyHandler.keyLt])
           setLocationRelative(-1,0);
       if (NewKeyHandler.keysOfInterest[NewKeyHandler.keyRt])
           setLocationRelative(+1,0);
    }
}
