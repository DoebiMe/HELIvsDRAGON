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
    private NewSpriteLogic myDialog = null;
    private NewScoreBoard myScoreBoard = null;
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
        myScoreBoard.executeThingsToDoDependingOnCurrentTimeTaskDivider();
        myDialog.executeThingsToDoDependingOnCurrentTimeTaskDivider();
    }

    private void initializeDelegator() {
        NewSpriteLogic myBackgroundLogic =
                new NewBackGroundLogic().setSetPointTimeTaskDivider(7);
        allSprites.add(myBackgroundLogic);
        myBackgroundLogic.setImageView(NewUI.createNodeOnUI());
        myHeliLogic = new NewHelicopterLogic().setSetPointTimeTaskDivider(2);
        allSprites.add(myHeliLogic);
        myHeliLogic.setImageView(NewUI.createNodeOnUI());

        myScoreBoard = new NewScoreBoard(  10);

        myDialog = new NewDialog(10,NewDialogType.NEXT_LEVEL);
        allSprites.add(myDialog);
        myDialog.setImageView(NewUI.createNodeOnUI());

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
            myScoreBoard.updateBulletsRelative(+1);
            myHeliLogic = getMyHeliLogic();
            NewSpriteLogic bulletToAdd =
                    myBulletsLogic.
                            createBullet(myHeliLogic.getxPos() + 50,
                                    myHeliLogic.getyPos() + 20);
            allSprites.add(bulletToAdd);
            ImageView bulletToAddImageView = NewUI.createNodeOnUI();
            NewUI.scaleSprite(bulletToAddImageView, 0.2);
            bulletToAdd.setImageView(bulletToAddImageView);
        }
        if (--bulletCreateDivider <= 0) bulletCreateDivider = BULLET_DIVIDER_SET_POINT;
    }

    private void createDragonsOnRandom() {
        Random rand = new Random();
        if (rand.nextInt(300) == 10) {
            NewSpriteLogic myDragonLogic =
                    new NewDragonLogic().setSetPointTimeTaskDivider(rand.nextInt(10) + 3);
            allSprites.add(myDragonLogic);
            myDragonLogic.
                    setLocationAbsolute(NewUI.OUT_OF_SCREEN_MAX_WIDTH + rand.nextInt(50),
                            rand.nextInt(NewUI.SCREEN_MAX_HEIGHT));
            myDragonLogic.setImageView(NewUI.createNodeOnUI());
            NewUI.scaleSprite(myDragonLogic.getImageView(), 0.3 + rand.nextInt(12) * 0.1);
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

    NewSpriteLogic getMyHeliLogic() {
        return allSprites.stream()
                .filter(element -> element.getTypeLogic() == NewSpriteLogicType.HELICOPTER)
                .findFirst().get();
    }

    public NewSpriteLogic getImageViewOfSingleType(NewSpriteLogicType typeToGet) {
        return allSprites.stream()
                .filter( element -> element.getTypeLogic() == typeToGet)
                .findFirst().get();
    }

    private void loopOverAllSpritesForEOLCheck() {
        loopOverAllBulletsForEOLCheck();
        loopOverAllDragonsForEOLCheck();
    }

    private void loopOverAllBulletsForEOLCheck() {
        getSpritesOfType(NewSpriteLogicType.BULLET).stream().
                filter(bulletToTest -> bulletToTest.getxPos() > NewUI.OUT_OF_SCREEN_MAX_WIDTH)
                .forEach(spritesToWaste::add);
    }

    private void loopOverAllDragonsForEOLCheck() {
        getSpritesOfType(NewSpriteLogicType.DRAGON).stream().
                filter(dragonToTest -> dragonToTest.getxPos() <= NewUI.OUT_OF_SCREEN_MIN_WIDTH).
                forEach(spritesToWaste::add);
    }

    private void loopOverAllSpritesForToExecuteLogic() {
        allSprites.forEach(NewSpriteLogic::executeThingsToDoDependingOnCurrentTimeTaskDivider);
    }

    private void loopOverAllSpritesForToUpdateUI() {
        allSprites.stream().filter(NewSpriteLogic::isChangedStateForUI).
                forEach(NewUI::updateSpriteOnUI);
    }

    private void loopOverAllSpritesForCollisionTest() {
        List<NewSpriteLogic> listBullets = getSpritesOfType(NewSpriteLogicType.BULLET);
        List<NewSpriteLogic> listDragons = getSpritesOfType(NewSpriteLogicType.DRAGON);
        for (NewSpriteLogic dragonToTest : listDragons) {
            for (NewSpriteLogic bulletToTest : listBullets) {
                if (NewUI.isCollisionBasedOnImageView(
                        dragonToTest.getImageView(),
                        bulletToTest.getImageView())) {
                    myScoreBoard.updateHitsRelative(+1);
                    myScoreBoard.setThingsToDo(NewScoreBoard.ThingsToDo.TURN_HITS);
                    spritesToWaste.add(dragonToTest);
                    spritesToWaste.add(bulletToTest);
                    return;
                }
            }
        }
        ImageView
                heliImageView = getMyHeliLogic().getImageView();
        for (NewSpriteLogic dragonToTest : listDragons) {
            if (NewUI.isCollisionBasedOnImageView(
                    dragonToTest.getImageView(), heliImageView)) {
                System.out.println("DRAGON HIT HELI **************");
                return;
            }
        }
    }
}