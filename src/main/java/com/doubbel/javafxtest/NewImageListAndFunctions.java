package com.doubbel.javafxtest;

import javafx.scene.image.Image;

import java.io.FileInputStream;


class NewImageListAndFunctions {
    private static final String FIRST_PART_PATH_TO_IMAGES =
            "C:\\fork\\JavaFXTest\\src\\main\\java\\com\\doubbel\\javafxtest\\Images\\";
    static final int MAX_IMAGES_IN_LIST = 100;
    private static final int MAX_INDEX_TYPES = 3;
    private static Object[][] allImages = new Object[MAX_IMAGES_IN_LIST][MAX_INDEX_TYPES];

    private enum indexTypes {
        IS_LOADED(0), IMAGE_BIN(1), IMAGE_PATH(2);
        private int nr;

        public int value() {
            return nr;
        }

        indexTypes(int nrToSet) {
            nr = nrToSet;
        }
    }

    static boolean loadImageReturnSuccesCondition(String pathToImage, int index) {
        if (index >= MAX_IMAGES_IN_LIST) return false;
        if (allImages[index][indexTypes.IS_LOADED.value()] == null) {
            try {
                allImages[index][indexTypes.IMAGE_BIN.value()] = new Image(
                        new FileInputStream(FIRST_PART_PATH_TO_IMAGES + pathToImage));
                allImages[index][indexTypes.IMAGE_PATH.value()] = pathToImage;
                allImages[index][indexTypes.IS_LOADED.value()] = true;
            } catch (Exception ex) {
                allImages[index][indexTypes.IS_LOADED.value()] = false;
            }
        }
        return (boolean) allImages[index][indexTypes.IS_LOADED.value()];
    }

    static Image getImageFromListAtIndex(int index) {
        return ((index >= MAX_IMAGES_IN_LIST) || (allImages[index][indexTypes.IS_LOADED.value()] == null)) ?
                null : (Image) allImages[index][indexTypes.IMAGE_BIN.value()];
    }

}

