package com.doubbel.javafxtest;

import java.util.ArrayList;

class NewBulletsLogic {
    private ArrayList<NewSpriteLogic> myBullets = new ArrayList<>();

    NewSpriteLogic createBullet(int XPos, int YPos) {
        NewSpriteLogic newBullet = new NewBulletLogic().setSetPointTimeTaskDivider(2);
        newBullet.setLocationAbsolute(XPos, YPos);
        newBullet.setCurrentImageToIndex(8);
        return newBullet;
    }
}
