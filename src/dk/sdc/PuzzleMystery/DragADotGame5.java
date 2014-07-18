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
public class DragADotGame5 extends View implements View.OnTouchListener {

    float dot2X = 354;
    float dot2Y = 147;
    float dotR = 50;
    float dotX = 118;
    float dotY = 147;
    float scaleWidth;
    float scaleHeight;

    PuzzleFinishListener listener;

    Rect wall1 = new Rect();
    Rect wall2 = new Rect();
    Rect wall3 = new Rect();
    Rect wall4 = new Rect();
    Rect wall5 = new Rect();
    Rect wall6 = new Rect();
    Rect wall7 = new Rect();
    Rect leftWall = new Rect();
    Rect topWall = new Rect();
    Rect rightWall = new Rect();
    Rect bottomWall = new Rect();
    Paint redPaint = new Paint();
    Paint blackPaint = new Paint();
    boolean inDot = false;
    boolean win = false;

    ArrayList wallsList = new ArrayList();

    public DragADotGame5(Context context) {
        super(context);
        redPaint.setColor(Color.RED);
        blackPaint.setColor(Color.BLACK);
        wall1.set(225, 0, 255, 815 );
        wallsList.add(wall1);
        wall2.set(255, 785, 490, 815);
        wallsList.add(wall2);
        wall3.set(650, 785, 720, 815 );
        wallsList.add(wall3);
        wall4.set(415, 540, 720, 570);
        wallsList.add(wall4);
        wall5.set(255, 295, 495, 325 );
        wallsList.add(wall5);
        wall6.set(465, 228,495, 295);
        wallsList.add(wall6);
        wall7.set(465, 0, 495, 67);
        wallsList.add(wall7);
        leftWall.set(0, 0, 10, 1040);
        wallsList.add(leftWall);
        topWall.set(0, 0, 720, 10);
        wallsList.add(topWall);
        rightWall.set(710, 0, 720, 1040);
        wallsList.add(rightWall);
        bottomWall.set(0, 1030, 720, 1040);
        wallsList.add(bottomWall);
        setOnTouchListener(this);
    }

    public DragADotGame5(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragADotGame5(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPuzzleFinishListener(PuzzleFinishListener pListener) {
        listener = pListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        long defaultPixelsWidth = 720;
        long defaultPixelsHeight = 1040;

        long width = canvas.getWidth();
        long height = canvas.getHeight();

        scaleWidth = (float) width / defaultPixelsWidth;
        scaleHeight = (float) height / defaultPixelsHeight;

        canvas.drawCircle(dotX * scaleWidth,dotY * scaleHeight,dotR * scaleWidth,redPaint);
        canvas.drawCircle(dot2X * scaleWidth, dot2Y * scaleHeight, dotR * scaleWidth, redPaint);
        canvas.drawCircle(dotX * scaleWidth,dotY * scaleHeight,dotR * scaleWidth/3,blackPaint);

        for (int i = 0; i < wallsList.size(); i++) {
            Rect rect = (Rect) wallsList.get(i);

            canvas.drawRect(rect.left * scaleWidth, rect.top * scaleHeight, rect.right * scaleWidth, rect.bottom * scaleHeight, redPaint);
        }

        if (inDot) {
            canvas.drawColor(redPaint.getColor());
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //dotX = event.getX();
        //dotY = event.getY();

        if (event.getPointerCount() > 1) {
            resetDot();
        }
        else {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    int distanceFinger = (int) (Math.sqrt((event.getX() - dotX*scaleWidth) * (event.getX() - dotX*scaleWidth) + ((event.getY() - dotY*scaleHeight) * (event.getY() - dotY*scaleHeight))));
                    if (distanceFinger < dotR*scaleWidth) {
                        inDot = true;
                        dotX = event.getX()/scaleWidth;
                        dotY = event.getY()/scaleHeight;
                    }
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (event.getPointerCount() > 1 && inDot) {
                        //Toast toast = Toast.makeText(getContext(), "Cheater! I think not", Toast.LENGTH_SHORT);
                        //toast.show();
                        inDot = false;
                        resetDot();
                    } else {
                        if (inDot) {
                            dotX = event.getX()/scaleWidth;
                            dotY = event.getY()/scaleHeight;
                            int distanceDots = (int) (Math.sqrt(((dot2X - dotX) * (dot2X - dotX))*scaleWidth + ((dot2Y - dotY) * (dot2Y - dotY))*scaleHeight));
                            if (distanceDots < 2 * dotR *scaleWidth && !win) {
                                win = true;
                                listener.puzzleFinished();
                            }
                        }
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    inDot = false;
                    resetDot();
                    break;
            }

            if (inDot) {
                checkCollision(wallsList, event.getX(), event.getY());
            }
        }

        invalidate();
        return true;
    }

    private void checkCollision (ArrayList arrayList, float x, float y) {
        for (int i = 0; i < arrayList.size(); i++) {
            Rect rect = (Rect) arrayList.get(i);
            if (x-dotR*scaleWidth < rect.right*scaleWidth && x+dotR*scaleWidth > rect.left*scaleWidth && y-dotR*scaleWidth < rect.bottom*scaleHeight && y+dotR*scaleWidth > rect.top*scaleHeight) {
                resetDot();
            }
        }
    }

    private void resetDot() {
        dotX = 118;
        dotY = 147;
        inDot = false;
    }
}