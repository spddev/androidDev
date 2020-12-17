package com.example.drawshapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

public class Circle extends Shape {
    PointF center;
    float radius;


    Circle(String color, PointF center, float radius) {
        super(color);

        this.center = center;
        this.radius = radius;
    }

    @Override
    void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.parseColor("#" + color));
        canvas.drawCircle(center.x, center.y, radius, paint);
    }

//    void draw(Canvas canvas, Paint paint) {
//        paint.setColor(Color.parseColor("#" + color));
//        canvas.drawCircle(center.x, center.y, radius, paint);
//    }
}

