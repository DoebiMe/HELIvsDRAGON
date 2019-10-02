package com.doubbel.javafxtest;


import java.util.ArrayList;

public class NewTaskDelegator {
    private ArrayList<NewSpriteLogic> allSprites = new ArrayList<>();

    private boolean isPaneSet = false;

    public NewTaskDelegator( ) {


    }

    public void executeToDo() {
        if ( (NewUI.getMyPane() != null) && (isPaneSet) ){
            loopOverAllSpritesForToExecuteLogic();
            loopOverAllSpritesForToUpdateUI();
            return;
        }
        if (!isPaneSet) {
            if (NewUI.getMyPane() == null) return;

        }
        isPaneSet = true;
        NewSpriteLogic myHeliLogic = new NewHelicopterLogic().setSetPointTimeTaskDivider(100);
        allSprites.add(myHeliLogic);
        NewUI.addSpriteToUI(myHeliLogic);
    }

    private void loopOverAllSpritesForToExecuteLogic() {
        for(NewSpriteLogic spriteLogic : allSprites) {
            spriteLogic.executeThingsToDoDependingOnCurrentTimeTaskDivider();
        }
    }
    private void loopOverAllSpritesForToUpdateUI() {
        for (NewSpriteLogic spriteLogic : allSprites) {
            // to do
        }
    }


}
