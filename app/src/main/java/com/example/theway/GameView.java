package com.example.theway;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {

    public static int maxX = 20; // размер по горизонтали
    public static int maxY = 28; // размер по вертикали
    public static float unitW = 0; // пикселей в юните по горизонтали
    public static float unitH = 0; // пикселей в юните по вертикали

    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Man man;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private MediaPlayer screamer;

    private ArrayList<Monster> monsters = new ArrayList<>(); // тут будут харанится монстры
    private final int MONSTER_INTERVAL = 10; // время через которое появляются мностры (в итерациях)
    private int currentTime = 0;

    public GameView(Context context) {
        super(context);
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();

        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameRunning) {
            update(); //нова позиция
            draw(); //новый кадр
            checkCollision(); //проверка на столкновение
            checkIfNewMonster(); //рисуем монстра
            control(); //пауза на 17 мс
        }
        canvas.drawColor(Color.RED); // заполняем фон чёрным
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface

            if (firstTime) { // инициализация при первом запуске
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width() / maxX; // вычисляем число пикселей в юните
                unitH = surfaceHolder.getSurfaceFrame().height() / maxY;

                man = new Man(getContext()); // добавляем человечка
            }

            canvas = surfaceHolder.lockCanvas(); // закрываем canvas
            canvas.drawColor(Color.BLACK); // заполняем фон чёрным

            man.draw(paint, canvas); // рисуем человечка

            for (Monster monster : monsters) { // рисуем монстров
                monster.draw(paint, canvas);
            }

            surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
        }
    }

    private void checkCollision() {
        for (Monster monster : monsters) {
            if (monster.isCollision(man.x, man.y, man.size)) {
                gameRunning = false; // останавливаем игру

                screamer = MediaPlayer.create(getContext(), R.raw.screamer); //создаём скример
                screamer.start();
                screamer.seekTo(400);
                screamer.setLooping(false);
            }
        }
    }

    private void update() {
        if (!firstTime) {
            man.update();
            for (Monster monster : monsters) {
                monster.update();
            }
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkIfNewMonster() {
        if (currentTime >= MONSTER_INTERVAL) {
            Monster monster = new Monster(getContext());
            monsters.add(monster);
            currentTime = 0;
        } else {
            currentTime++;
        }
    }
}
