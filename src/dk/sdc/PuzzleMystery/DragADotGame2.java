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

    float dot2X = 570;
    float dot2Y = 890;
    float dotR = 70;
    float dotX = 150;
    float dotY = 150;
    float scaleWidth;
    float scaleHeight;

    PuzzleFinishListener listener;

    Rect wall1 = new Rect();
    Rect wall2 = new Rect();
    Rect leftWall = new Rect();
    Rect topWall = new Rect();
    Rect rightWall = new Rect();
    Rect bottomWall = new Rect();
    Paint redPaint = new Paint();
    Paint blackPaint = new Paint();
    boolean inDot = false;
    boolean win = false;

    ArrayList wallsList = new ArrayList();

    //Paint tmpPaint = new Paint();

    public DragADotGame2(Context context) {
        super(context);
        redPaint.setColor(Color.RED);
        blackPaint.setColor(Color.BLACK);
        //redPaint.setStrokeWidth(25);
        //tmpPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //tmpPaint.setAntiAlias(true);
        //tmpPaint.setColor(Color.CYAN);
        wall1.set(220, 660, 720, 740 );
        wallsList.add(wall1);
        wall2.set(0, 300, 500, 380);
        wallsList.add(wall2);
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

    public DragADotGame2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragADotGame2(Context context, AttributeSet attrs, int defStyleAttr) {
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
        dotX = 150;
        dotY = 150;
        inDot = false;
    }
}