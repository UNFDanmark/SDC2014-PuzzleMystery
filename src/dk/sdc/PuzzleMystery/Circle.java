package dk.sdc.PuzzleMystery;

import android.graphics.Paint;

/**
 * Created by sdc on 7/16/14.
 */
public class Circle {

    private float xCoord;
    private float yCoord;
    private float radius;
    private Paint paint;


    public Circle (float x, float y, float r, Paint p){
        xCoord = x;
        yCoord = y;
        radius = r;
        paint = p;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getyCoord() {
        return yCoord;
    }

    public void setyCoord(float yCoord) {
        this.yCoord = yCoord;
    }

    public float getxCoord() {
        return xCoord;
    }

    public void setxCoord(float xCoord) {
        this.xCoord = xCoord;
    }
}
