package com.doubbel.javafxtest;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

class NewScoreBoard {

    public enum ThingsToDo {NORMAL, TURN_HITS}

    private double turnHitsValue;
    private ThingsToDo thingsToDo;

    private static final int MAX_NODES_FOR_BULLETS = 5;
    private static final int MAX_NODES_FOR_HITS = 5;
    private static final int MAX_NODES_FOR_LIVES = 2;
    private ImageView[] nodesForBullets = new ImageView[MAX_NODES_FOR_BULLETS];
    private ImageView[] nodesForHits = new ImageView[MAX_NODES_FOR_HITS];
    private ImageView[] nodesForLives = new ImageView[MAX_NODES_FOR_LIVES];
    private static final int START_INDEX_IMAGE_ARRAY = NewImageListAndFunctions.MAX_IMAGES_IN_LIST - 11;
    private int bulletNumber = 0;
    private int hitNumber = 0;
    private int liveNumber = 0;
    private int currentTimeTaskDivider;
    private int setPointTimeTaskDivider;


    NewScoreBoard(int setPointTimeTaskDivider) {
        this.setPointTimeTaskDivider = setPointTimeTaskDivider;
        currentTimeTaskDivider = setPointTimeTaskDivider;
        turnHitsValue = 0;
        loadAllImagesToList();
        createNodesForBullets();
        createNodesForHits();
        createNodesForLives();
    }


    void setThingsToDo(ThingsToDo thingsToDo) {
        this.thingsToDo = thingsToDo;
        turnHitsValue = 0.1;
    }

    boolean executeThingsToDoDependingOnCurrentTimeTaskDivider() {
        if (thingsToDo == ThingsToDo.NORMAL) return false;
        if (--currentTimeTaskDivider <= 0) {
            currentTimeTaskDivider = setPointTimeTaskDivider;
            executeThingsToDo();
            return true;
        }
        return false;
    }

    private void executeThingsToDo() {
        if (thingsToDo == ThingsToDo.TURN_HITS) {
            if ((turnHitsValue > 0) && (turnHitsValue < 360)) {
                turnHitsValue += 10;
            } else {
                turnHitsValue = 0;
                thingsToDo = ThingsToDo.NORMAL;
            }
            for (ImageView hitView : nodesForHits) {
                NewUI.rotateSprite(hitView, turnHitsValue);
            }
        }
    }


    void updateBulletsRelative(int relativeNumber) {
        bulletNumber += relativeNumber;
        fillNodeArrayWithCharArray(nodesForBullets, getNumberAsCharArray(bulletNumber, MAX_NODES_FOR_BULLETS));
    }

    void updateHitsRelative(int relativeNumber) {
        hitNumber += relativeNumber;
        fillNodeArrayWithCharArray(nodesForHits, getNumberAsCharArray(hitNumber, MAX_NODES_FOR_HITS));
    }

    void updateLivesRelative(int relativeNumber) {
        liveNumber += relativeNumber;
        fillNodeArrayWithCharArray(nodesForLives, getNumberAsCharArray(hitNumber, MAX_NODES_FOR_LIVES));
    }

    private void fillNodeArrayWithCharArray(ImageView[] nodes, char[] chars) {
        if (nodes.length != chars.length) throw new
                IllegalArgumentException("Argument length not compatible in fillNodeArrayWithCharArray");
        for (int indexer = 0; indexer < nodes.length; indexer++) {
            NewUI.updateSpriteImage(
                    nodes[indexer],
                    START_INDEX_IMAGE_ARRAY + getCorrespondingNumberOfChar(chars[indexer]));
        }
    }

    private char[] getNumberAsCharArray(int nr, int sizeOfReturnArray) {
        char[] nrChars = Integer.toString(nr).toCharArray();
        char[] nrCharsFixedSize = new char[sizeOfReturnArray];
        Arrays.fill(nrCharsFixedSize, '0');
        for (int indexerOnFixed = nrCharsFixedSize.length - 1, indexerOnVariable = nrChars.length - 1;
             indexerOnFixed > 0; indexerOnFixed--, indexerOnVariable--) {
            if (indexerOnVariable < 0) break;
            nrCharsFixedSize[indexerOnFixed] = nrChars[indexerOnVariable];
        }
        return nrCharsFixedSize;
    }

    private int getCorrespondingNumberOfChar(char aChar) {
        return Character.getNumericValue(aChar) - Character.getNumericValue('0');
    }

    public void loadAllImagesToList() {
        ArrayList<Image> imagesFromAllNumbers = new ArrayList<>();
        for (int indexer = 0; indexer < 10; indexer++) {
            String name = "nr" + indexer + ".png";
            NewImageListAndFunctions.loadImageReturnSuccesCondition
                    (name, START_INDEX_IMAGE_ARRAY + indexer);
        }
    }

    private void createNodesForLives() {
        initialiseNodes(MAX_NODES_FOR_LIVES, nodesForLives, 340);
    }

    private void createNodesForHits() {
        initialiseNodes(MAX_NODES_FOR_HITS, nodesForHits, 180);
    }

    private void initialiseNodes(int maxNodes, ImageView[] nodes, int i) {
        for (int indexer = 0; indexer < maxNodes; indexer++) {
            initialiseNodesWithImage(indexer, nodes, i);
        }
    }


    private void createNodesForBullets() {
        initialiseNodes(MAX_NODES_FOR_BULLETS, nodesForBullets, 20);
    }

    private void initialiseNodesWithImage(int indexer, ImageView[] nodes, int horizontalStartPosition) {
        nodes[indexer] = NewUI.createNodeOnUI();
        NewUI.updateSpriteImage(nodes[indexer], START_INDEX_IMAGE_ARRAY);
        NewUI.positionSprite(nodes[indexer], horizontalStartPosition + indexer * 25, -30);
        NewUI.scaleSprite(nodes[indexer], 0.4);
        NewUI.rotateSprite(nodes[indexer], 25);
    }
}