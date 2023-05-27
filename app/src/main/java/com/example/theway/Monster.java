package com.example.theway;

import android.content.Context;

import java.util.Random;

public class Monster extends Body {
    public int radius = 2; // радиус
    private float minSpeed = (float) 0.5; // минимальная скорость
    private float maxSpeed = (float) 0.7; // максимальная скорость

    public Monster(Context context) {
        Random random = new Random();

        bitmapId = R.drawable.monster;
        way = random.nextInt(2);

        if (way == 0) {
            y = 0;
            x = random.nextInt(GameView.maxX) - radius;
        } else {
            x = 0;
            y = random.nextInt(GameView.maxX) - radius;
        }

        size = radius * 2;
        speed = minSpeed + (maxSpeed - minSpeed) * random.nextFloat();

        init(context);
    }

    @Override
    void update() {
        if (way == 0) {
            y += speed;
        } else {
            x += speed;
        }
    }

    public boolean isCollision(float manX, float manY, float manSize) {
        return !(((x + size) < manX + 1) || (x + 1> (manX + manSize - 1))
                || ((y + size) < manY + 1) || (y > (manY + manSize + 1)));
    }
}
