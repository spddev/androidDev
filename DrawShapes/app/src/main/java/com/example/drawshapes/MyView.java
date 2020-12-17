package com.example.drawshapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    private final static int MAX_POINTS = 5;
    public static final String TYPE_RECT = "rect";
    public static final String TYPE_CIRCLE = "circle";
    public static final String TYPE_TRIANGLE = "triangle";
    public static final String MODE_DRAW = "draw";
    public static final String MODE_MOVE = "move";
    //    public static final String TYPE_RECT = "rect";
    int width;
    int height;
    int sizeGrid = 48;
    float density;

    String mode = MODE_DRAW;
    String typeShape = TYPE_TRIANGLE;
    String color = "000000";

    int counterPoints;
    PointF[] points = new PointF[MAX_POINTS];
    int counterRect;
    Rect[] rects = new Rect[100];

    int counterCircles;
    Circle[] circles = new Circle[100];

    int counterShapes;
    Shape[] shapes = new Shape[100];



    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        density = getResources().getDisplayMetrics().density;
        sizeGrid *= density;
    }

    public void undo() {
        if (counterShapes > 0){
            counterShapes--;
            this.invalidate();
        }
    }
    public void setMode(String mode){
        this.mode = mode;
    }

    public void setTypeShape(String typeShape){
        this.typeShape = typeShape;
        this.invalidate();
    }

    public void setColor(String color){
        this.color = color;
        this.invalidate();
    }

    public String getMode(){
        return this.mode;
    }

//    public MyView(Context context) {
//        super(context);
//        density = getResources().getDisplayMetrics().density;
//        sizeGrid *= density;
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawColor(Color.RED);

        Paint paint = new Paint();
        paint.setStrokeWidth(20);
        paint.setColor(Color.RED);

        drawGrid(canvas);
        drawPoints(canvas);
//        drawRects(canvas);
//        drawCircle(canvas);
        drawShapes(canvas);
    }

    private void drawGrid(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(density);
        int numBlockWidth = canvas.getWidth() / sizeGrid;
        int numBlockHeight = canvas.getHeight() / sizeGrid;

        int yStop = canvas.getHeight();
        for (int i = 1; i <= numBlockWidth; i++) {
            int x = i * sizeGrid;
            canvas.drawLine(x, 0, x, yStop, paint);
        }

        int xStop = canvas.getWidth();
        for (int i = 1; i <= numBlockHeight; i++) {
            int y = i * sizeGrid;
            canvas.drawLine(0, y, xStop, y, paint);
        }
    }

    void drawShapes(Canvas canvas) {
         Paint paint = new Paint();
            for (int i = 0; i < counterShapes; i++) {
                Shape shape = shapes[i];
                shape.draw(canvas, paint);
            }
    }

//    void drawRects(Canvas canvas) {
//        Paint paint = new Paint();
//
//        for (int i = 0; i < counterRect; i++) {
//            Rect rect = rects[i];
//            rect.draw(canvas, paint);
//        }
//    }
//
//    void drawCircle(Canvas canvas) {
//        Paint paint = new Paint();
//
//        for (int i = 0; i < counterCircles; i++) {
//            circles[i].draw(canvas, paint);
//        }
//    }

    void drawPoints(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);


        int counter = Math.min(counterPoints, MAX_POINTS);

        for (int i = 0; i < counter; i++) {
            PointF pointF = points[i];
            canvas.drawCircle(pointF.x, pointF.y, 20, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            onDownTouch((int)event.getX(), (int)event.getY());
        }
        return true;
    }

    private void onDownTouch(int x, int y){
        if (this.mode.equals(MODE_DRAW)){
            if (counterPoints < MAX_POINTS) {
                points[counterPoints] = new PointF(x, y);
                counterPoints++;

                switch (this.typeShape) {
                    case TYPE_RECT: checkPointsForCreateRect(); break;
                    case TYPE_CIRCLE: checkPointsForCreateCircle(); break;
                    case TYPE_TRIANGLE: checkPointsForCreateTriangle();break;
                }

                this.invalidate();
            }
        }
    }

    private void checkPointsForCreateCircle() {
        if (this.getMode().equals(MyView.MODE_DRAW)) {
            if (counterPoints >= 2) {
                float a = points[1].x - points[0].x;
                float b = points[1].y - points[0].y;
                float radius = (float) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
                Circle circle = new Circle(this.color, points[0], radius);

                shapes[counterShapes] = circle;
                counterShapes++;

                counterPoints = 0;
                this.invalidate();
            }
        }
    }

    private void checkPointsForCreateRect(){
        if (this.getMode().equals(MyView.MODE_DRAW)) {
            if (counterPoints >= 2) {
                // создаем прямоугольник
                Rect rect = new Rect(this.color, points[0], points[1]);

                shapes[counterShapes] = rect;
                counterShapes++;

                counterPoints = 0;
                this.invalidate();
            }
        }
    }

    private void checkPointsForCreateTriangle(){
        if (this.getMode().equals(MyView.MODE_DRAW)) {
            if (counterPoints >= 3) {
                // создаём треугольник
                Triangle triangle = new Triangle(this.color, points[0], points[1], points[2]);

                shapes[counterShapes] = triangle;
                counterShapes++;

                counterPoints = 0;
                this.invalidate();
            }
        }
    }
}