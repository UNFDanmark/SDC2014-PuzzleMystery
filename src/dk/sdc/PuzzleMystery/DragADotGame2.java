package dk.sdc.PuzzleMystery;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by sdc on 7/16/14.
 */
public class DragADotGame2 extends View implements View.OnTouchListener {

    float dotX = 600;
    float dotY = 900;
    float dotR = 70;
    float dot2X = 100;
    float dot2Y = 100;

    PuzzleFinishListener listener;

    Rect wall1 = new Rect();
    Rect wall2 = new Rect();
    Rect leftWall = new Rect();
    Rect topWall = new Rect();
    Rect rightWall = new Rect();
    Rect bottomWall = new Rect();
    Paint redPaint = new Paint();
    boolean inDot = false;
    boolean win = false;

    ArrayList wallsList = new ArrayList();

    //Paint tmpPaint = new Paint();

    public DragADotGame2(Context context) {
        super(context);
        redPaint.setColor(Color.RED);
        //redPaint.setStrokeWidth(25);
        //tmpPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //tmpPaint.setAntiAlias(true);
        //tmpPaint.setColor(Color.CYAN);
        wall1.set(270, 640, 1000, 720 );
        wallsList.add(wall1);
        wall2.set(0, 220, 500, 300);
        wallsList.add(wall2);
        leftWall.set(0, 0, 10, 1500);
        wallsList.add(leftWall);
        topWall.set(0, 0, 720, 10);
        wallsList.add(topWall);
        rightWall.set(710, 0, 720, 1500);
        wallsList.add(rightWall);
        bottomWall.set(0, 1030, 720, 1080);
        wallsList.add(bottomWall);
        setOnTouchListener(this);
    }

    public DragADotGame2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragADotGame2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPuzzleFinishListener(PuzzleFinishListener pListener) {
        listener = pListener;
    }

    private void checkCollision (ArrayList arrayList, float x, float y) {
        for (int i = 0; i < arrayList.size(); i++) {
            Rect rect = (Rect) arrayList.get(i);
            if (x-dotR-rect.right < 0 && y-dotR-rect.bottom < 0 && y+dotR-rect.top > 0) {

            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(dotX,dotY,dotR,redPaint);
        canvas.drawCircle(dot2X, dot2Y, dotR, redPaint);

        for (int i = 0; i < wallsList.size(); i++) {
            canvas.drawRect((Rect) wallsList.get(i), redPaint);
        }

        if (inDot)
            canvas.drawColor(redPaint.getColor());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //dotX = event.getX();
        //dotY = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                int distanceFinger = (int) (Math.sqrt((event.getX() - dotX) * (event.getX() - dotX) + ((event.getY() - dotY) * (event.getY() - dotY))));
                if (distanceFinger < dotR)
                {
                    inDot = true;
                    dotX = event.getX();
                    dotY = event.getY();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() > 1 && inDot){
                    //Toast toast = Toast.makeText(getContext(), "Cheater! I think not", Toast.LENGTH_SHORT);
                    //toast.show();
                    inDot = false;
                    dotX = 600;
                    dotY = 900;
                }
                else {
                    if (inDot) {
                        dotX = event.getX();
                        dotY = event.getY();
                        int distanceDots = (int) (Math.sqrt((dot2X - dotX) * (dot2X - dotX) + ((dot2Y - dotY) * (dot2Y - dotY))));
                        if (distanceDots < 2 * dotR) {
                            win = true;
                            listener.puzzleFinished();
                        }
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                inDot = false;
                break;
        }

        if (inDot) {
            checkCollision(wallsList, event.getX(), event.getY());
        }

        invalidate();
        return true;
    }
}