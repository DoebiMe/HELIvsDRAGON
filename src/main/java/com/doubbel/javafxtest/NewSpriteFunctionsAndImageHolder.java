package com.doubbel.javafxtest;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.util.ArrayList;

public class NewSpriteFunctionsAndImageHolder {
    private static final String FIRST_PART_PATH_TO_IMAGES =
            "C:\\fork\\JavaFXTest\\src\\main\\java\\com\\doubbel\\javafxtest\\Images\\";
    private static ArrayList<Image> myImageList = new ArrayList<>();
    public static boolean isSuccesLoadImageToListAtIndex(String pathToImage,int index) {
        try {
              myImageList.add(
                      index,
                      new Image(new FileInputStream(FIRST_PART_PATH_TO_IMAGES + pathToImage)));
            System.out.println("Loaded " + pathToImage);
        } catch (Exception ex) {
            System.out.println("Not loaded " + pathToImage);
            return false;
        }
        return true;
    }
    public static Image getImageFromListAtIndex(int index) {
        if (!(index < myImageList.size())) return null;
        return myImageList.get(index);
    }

}
