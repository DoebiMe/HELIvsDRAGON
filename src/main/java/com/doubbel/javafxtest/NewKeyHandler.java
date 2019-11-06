package com.doubbel.javafxtest;

import javafx.scene.input.KeyEvent;

import static javafx.scene.input.KeyCode.*;

public class NewKeyHandler {
    public static final int keySpace = 0;
    public static final int keyUp = 1;
    public static final int keyDn = 2;
    public static final int keyLt = 3;
    public static final int keyRt = 4;
    public static final int keyEsc = 5;
    public static final int keyUpRt = 6;
    public static final int keyUpLt = 7;
    public static final int keyDnRt = 8;
    public static final int keyDnLt = 9;
    public static final int keyEnter = 10;
    public static final int key0 = keySpace;
    public static final int key1 = keyDnLt;
    public static final int key2 = keyDn;
    public static final int key3 = keyDnRt;
    public static final int key4 = keyLt;
    public static final int key5 = keyEnter;
    public static final int key6 = keyRt;
    public static final int key7 = keyUpLt;
    public static final int key8 = keyUp;
    public static final int key9 = keyUpRt;

    public static boolean[] keysOfInterest = new boolean[11];

    public void handleKeyPressed(KeyEvent keyEvent) {
        getKeyEvent(true, keyEvent);
    }

    public void handleKeyReleased(KeyEvent keyEvent) {
        getKeyEvent(false, keyEvent);
    }

    static void getKeyEvent(boolean valueToSet, KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case NUMPAD7:
                keysOfInterest[keyUpLt] = valueToSet;
                break;
            case NUMPAD9:
                keysOfInterest[keyUpRt] = valueToSet;
                break;
            case NUMPAD1:
                keysOfInterest[keyDnLt] = valueToSet;
                break;
            case NUMPAD3:
                keysOfInterest[keyDnRt] = valueToSet;
                break;
            case UP:
            case NUMPAD8:
                keysOfInterest[keyUp] = valueToSet;
                break;
            case DOWN:
            case NUMPAD2:
                keysOfInterest[keyDn] = valueToSet;
                break;
            case LEFT:
            case NUMPAD4:
                keysOfInterest[keyLt] = valueToSet;
                break;
            case RIGHT:
            case NUMPAD6:
                keysOfInterest[keyRt] = valueToSet;
                break;
            case SPACE:
            case NUMPAD0:
                keysOfInterest[keySpace] = valueToSet;
                break;
            case ESCAPE:
                keysOfInterest[keyEsc] = valueToSet;
                break;
            case ENTER:
            case NUMPAD5:
                keysOfInterest[keyEnter] = valueToSet;
        }
    }
}
