package com.example.theway;

import android.content.Context;

public class Man extends Body {

    @Override
    void update() {
        if (Game.isUpPressed && y >= 0) {
            y -= speed;
        }

        if (Game.isDownPressed && y <= GameView.maxY - 5) {
            y += speed;
        }

        if(Game.isLeftPressed && x >= 0){
            x -= speed;
        }
        if(Game.isRightPressed && x <= GameView.maxX - 5){
            x += speed;
        }
        //перемещаем картинку
    }

    public Man(Context context) {
        bitmapId = R.drawable.man; // определяем начальные параметры
        size = 2;
        x=7;
        y=GameView.maxY - size - 1;
        speed = (float) 0.2;

        init(context); // инициализируем человечка
    }
}