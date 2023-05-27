package com.example.theway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;

    public MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttonStart = (Button) findViewById(R.id.button_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainActivity.this, Game.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    //пусто
                }
            }
        });

        Button buttonOptions = (Button) findViewById(R.id.button_options);
        buttonOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, Options.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    //пусто
                }
            }
        });

        //музыка
        mediaPlayer = MediaPlayer.create(this, R.raw.music_menu);
        mediaPlayer.start();
    }

    public void goHome(View v) {
        mediaPlayer.stop();
        super.onBackPressed();
    }

    //системная кнопка назад - начало
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы выйти", Toast.LENGTH_SHORT);
            backToast.show();
            mediaPlayer.stop();
        }
        backPressedTime = System.currentTimeMillis();
    }
    //системная кнопка назад - конец
}