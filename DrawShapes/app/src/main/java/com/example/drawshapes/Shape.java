package com.example.drawshapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

abstract public class Shape{
    String color;


    Shape(String color) {
        this.color = color;
    }

    abstract void draw(Canvas canvas, Paint paint);

}
