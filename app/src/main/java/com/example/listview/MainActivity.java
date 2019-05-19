package com.example.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private int screenWidth;
    private int screenHeight;
    private int lastX, lastY;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DisplayMetrics dm = getResources().getDisplayMetrics();
        this.screenWidth = dm.widthPixels;
        this.screenHeight = dm.heightPixels - 50;
        this.button = findViewById(R.id.button3);

        button.setOnTouchListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "w", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v instanceof Button) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();

                    break;
                case MotionEvent.ACTION_MOVE:
                    int dx = (int) event.getRawX() - lastX;
                    int dy = (int) event.getRawY() - lastY;

                    int left = v.getLeft() + dx;
                    int top = v.getTop() + dy;
                    int right = v.getRight() + dx;
                    int bottom = v.getBottom() + dy;

                    if (left < 0) {
                        left = 0;
                        right = left + v.getWidth();
                    }

                    if (right > screenWidth){
                        right = screenWidth;
                        left = right - v.getWidth();
                    }

                    if (top < 0) {
                        top = 0;
                        bottom = top + v.getHeight();
                    }

                    if (bottom > screenHeight){
                        bottom = screenHeight;
                        top = bottom - v.getHeight();
                    }

                    v.layout(left, top, right, bottom);
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                    break;
            }
        }

        return false;
    }
}
