package com.example.theway;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity implements View.OnTouchListener {

    public static boolean isUpPressed, isDownPressed, isLeftPressed, isRightPressed = false; //кнопки нажаты



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        GameView gameView = new GameView(this); //создаём gameView

        RelativeLayout gameLayout = (RelativeLayout) findViewById(R.id.game_layout); //находим gameLayout
        gameLayout.addView(gameView); //и передаём gameView в него

        Button buttonUp = findViewById(R.id.button_up);
        Button buttonDown = findViewById(R.id.button_down);
        Button buttonLeft = findViewById(R.id.button_left);
        Button buttonRight = findViewById(R.id.button_right);

        buttonUp.setOnTouchListener(this);
        buttonDown.setOnTouchListener(this);
        buttonLeft.setOnTouchListener(this);
        buttonRight.setOnTouchListener(this);

    }


    //определяем какая кнопка нажата - начало
    @Override
    public boolean onTouch(View button, MotionEvent motion) {
        switch (button.getId()) {
            case R.id.button_up:
                switch (motion.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isUpPressed = true;
                        break;

                    case MotionEvent.ACTION_UP:
                        isUpPressed = false;
                        break;
                }break;

            case R.id.button_down:
                switch (motion.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isDownPressed = true;
                        break;

                    case MotionEvent.ACTION_UP:
                        isDownPressed = false;
                        break;
                }break;

            case R.id.button_left:
                switch (motion.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isLeftPressed = true;
                        break;

                    case MotionEvent.ACTION_UP:
                        isLeftPressed = false;
                        break;
                }break;

            case R.id.button_right:
                switch (motion.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isRightPressed = true;
                        break;

                    case MotionEvent.ACTION_UP:
                        isRightPressed = false;
                        break;
                }break;
        }
        return true;
    }
    //определяем какая кнопка нажата - конец

    //системная кнопка назад - начало
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Game.this, MainActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            //пусто
        }
    }
    //системная кнопка назад - конец
}
