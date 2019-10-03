package com.doubbel.javafxtest;


import java.util.ArrayList;
import java.util.Random;

public class NewTaskDelegator {
    private ArrayList<NewSpriteLogic> allSprites = new ArrayList<>();
    private ArrayList<NewMasterSpriteWithLogic> spritesToWaste = new ArrayList<>();

    private boolean isInitialized = false;

    public NewTaskDelegator() {
    }

    public void executeToDo() {
        if (!NewUI.isInitialized()) return;
        if (!isInitialized) {
            NewSpriteLogic myHeliLogic = new NewHelicopterLogic().setSetPointTimeTaskDivider(2);
            allSprites.add(myHeliLogic);
            myHeliLogic.setIndexOnUI(NewUI.addSpriteToUIReturnNodeReference(myHeliLogic));
            isInitialized = true;
        }
        letDragonsBeBorn();
        loopOverAllSpritesForToExecuteLogic();
        loopOverAllSpritesForToUpdateUI();
        handleKeysFromUI();
    }

    private void handleKeysFromUI() {
        if (NewKeyHandler.keysOfInterest[NewKeyHandler.keySpace]) {
            System.out.println("Space");
        }
    }


    private void letDragonsBeBorn() {
        Random rand = new Random();
        if (rand.nextInt(400) == 10) {
            NewSpriteLogic myDragonLogic = new NewDragonLogic().setSetPointTimeTaskDivider(rand.nextInt(5)+10);
            myDragonLogic.setLocationAbsolute(500+rand.nextInt(50),rand.nextInt(300));
            allSprites.add(myDragonLogic);
            myDragonLogic.setIndexOnUI(NewUI.addSpriteToUIReturnNodeReference(myDragonLogic));
        }
    }

    private void loopOverAllSpritesForToExecuteLogic() {
        for (NewSpriteLogic spriteLogic : allSprites) {
            spriteLogic.executeThingsToDoDependingOnCurrentTimeTaskDivider();
        }
    }

    private void loopOverAllSpritesForToUpdateUI() {
        for (NewSpriteLogic spriteLogic : allSprites) {
            if (spriteLogic.isChangedStateForUI()) {
                NewUI.updateSpriteOnUI(spriteLogic);
            }
        }
    }
}
