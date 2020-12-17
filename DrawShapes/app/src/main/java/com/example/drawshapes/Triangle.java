package com.example.drawshapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

public class Triangle extends Shape {
    PointF a;
    PointF b;
    PointF c;
    Triangle(String color, PointF a, PointF b, PointF c ) {
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.parseColor("#" + color));
        Path path = new Path();
        path.moveTo(a.x, a.y);
        path.lineTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.lineTo(a.x, a.y);
        path.close();
        canvas.drawPath(path, paint);
    }
}