package com.doubbel.javafxtest;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class NewTaskDelegator {
    private ArrayList<NewSpriteLogic> allSprites = new ArrayList<>();
    private ArrayList<NewSpriteLogic> spritesToWaste = new ArrayList<>();
    private NewBulletsLogic myBulletsLogic = new NewBulletsLogic();
    private NewSpriteLogic myHeliLogic = null;
    private static int bulletCreateDivider = 0;
    private static final int BULLET_DIVIDER_SET_POINT = 20;
    private boolean isInitialized = false;

    void executeToDo() {
        if (!NewUI.isInitialized()) return;
        if (!isInitialized) initializeDelegator();
        createDragonsOnRandom();
        loopOverAllSpritesForEOLCheck();
        loopOverAllSpritesForToExecuteLogic();
        loopOverAllSpritesForToUpdateUI();
        loopOverAllSpritesForCollisionTest();
        disposeSpritesToWaste();
        handleKeysFromUI();
    }

    private void initializeDelegator() {
        NewSpriteLogic myBackgroundLogic =
                new NewBackGroundLogic().setSetPointTimeTaskDivider(7);
        allSprites.add(myBackgroundLogic);
        myBackgroundLogic.setImageView(NewUI.addSpriteToUIReturnImageView());
        NewSpriteLogic myHeliLogic =
                new NewHelicopterLogic().setSetPointTimeTaskDivider(2);
        allSprites.add(myHeliLogic);
        myHeliLogic.setImageView(NewUI.addSpriteToUIReturnImageView());
        isInitialized = true;
    }

    private void disposeSpritesToWaste() {
        spritesToWaste.forEach(spriteToWaste -> {
            NewUI.wasteNode(spriteToWaste.getImageView());
            allSprites.remove(spriteToWaste);
        });
        spritesToWaste.clear();
    }

    private void handleKeysFromUI() {
        if (NewKeyHandler.keysOfInterest[NewKeyHandler.keySpace]) createBullet();
        if (NewKeyHandler.keysOfInterest[NewKeyHandler.keyEsc]) System.exit(0);
    }

    private void createBullet() {
        if (bulletCreateDivider == BULLET_DIVIDER_SET_POINT) {
            myHeliLogic = getMyHeliLogic();
            NewSpriteLogic bulletToAdd =
                    myBulletsLogic.
                            createBullet(myHeliLogic.getxPos() + 50,
                                    myHeliLogic.getyPos() + 20);
            allSprites.add(bulletToAdd);
            ImageView bulletToAddImageView = NewUI.addSpriteToUIReturnImageView();
            bulletToAddImageView.setScaleX(0.2);
            bulletToAddImageView.setScaleY(0.2);
            bulletToAdd.setImageView(bulletToAddImageView);
        }
        if (--bulletCreateDivider <= 0) bulletCreateDivider = BULLET_DIVIDER_SET_POINT;
    }

    private void createDragonsOnRandom() {
        Random rand = new Random();
        if (rand.nextInt(300) == 10) {
            NewSpriteLogic myDragonLogic =
                    new NewDragonLogic().setSetPointTimeTaskDivider(rand.nextInt(5) + 10);
            myDragonLogic.
                    setLocationAbsolute(NewUI.OUT_OF_SCREEN_MAX_WIDTH + rand.nextInt(50),
                            rand.nextInt(NewUI.SCREEN_MAX_HEIGHT));
            allSprites.add(myDragonLogic);
            myDragonLogic.setImageView(NewUI.addSpriteToUIReturnImageView());
        }
    }

    public NewSpriteLogic createBullet(int XPos, int YPos) {
        return myBulletsLogic.createBullet(XPos, YPos);
    }

    private List<NewSpriteLogic> getSpritesOfType(NewSpriteLogicType listType) {
        return allSprites.stream().
                filter(element -> element.getTypeLogic() == listType).
                collect(Collectors.toList());
    }

    private NewSpriteLogic getMyHeliLogic() {
        List<NewSpriteLogic> heliList = allSprites.stream().
                filter(element -> element.getTypeLogic() == NewSpriteLogicType.HELICOPTER).
                collect(Collectors.toList());
        return heliList.get(0);
    }

    private void loopOverAllSpritesForEOLCheck() {
        loopOverAllBulletsForEOLCheck();
        loopOverAllDragonsForEOLCheck();
    }

    private void loopOverAllBulletsForEOLCheck() {
        List<NewSpriteLogic> listBullets = getSpritesOfType(NewSpriteLogicType.BULLET);
        listBullets.forEach(bulletToTest -> {
            if (bulletToTest.getxPos() > NewUI.OUT_OF_SCREEN_MAX_WIDTH)
                spritesToWaste.add(bulletToTest);
        });
    }

    private void loopOverAllDragonsForEOLCheck() {
        List<NewSpriteLogic> listDragons = getSpritesOfType(NewSpriteLogicType.DRAGON);
        listDragons.forEach(dragonToTest -> {
            if (dragonToTest.getxPos() <= NewUI.OUT_OF_SCREEN_MIN_WIDTH) spritesToWaste.add(dragonToTest);
        });
    }

    private void loopOverAllSpritesForToExecuteLogic() {
        allSprites.forEach(NewSpriteLogic::executeThingsToDoDependingOnCurrentTimeTaskDivider);
    }

    private void loopOverAllSpritesForToUpdateUI() {
        allSprites.
                forEach(spriteToUpdate ->
                {
                    if (spriteToUpdate.isChangedStateForUI()) NewUI.updateSpriteOnUI(spriteToUpdate);
                });
    }

    private void loopOverAllSpritesForCollisionTest() {
        List<NewSpriteLogic> listBullets = getSpritesOfType(NewSpriteLogicType.BULLET);
        List<NewSpriteLogic> listDragons = getSpritesOfType(NewSpriteLogicType.DRAGON);
        for (NewSpriteLogic dragonToTest : listDragons) {
            for (NewSpriteLogic bulletToTest : listBullets) {
                if (NewUI.isCollisionBasedOnImageView(
                        dragonToTest.getImageView(),
                        bulletToTest.getImageView())) {
                    spritesToWaste.add(dragonToTest);
                    spritesToWaste.add(bulletToTest);
                    return;
                }
            }
        }
        ImageView heliImageView = getMyHeliLogic().getImageView();
        for (NewSpriteLogic dragonToTest : listDragons) {
            if (NewUI.isCollisionBasedOnImageView(
                    dragonToTest.getImageView(), heliImageView)) {
                System.out.println("DRAGON HIT HELI BABY **************");
                return;
            }
        }
    }
}

