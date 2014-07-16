package dk.sdc.PuzzleMystery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by sdc on 7/15/14.
 */
public class DragADotGame extends View implements View.OnTouchListener {

    float dotX = 150;
    float dotY = 150;
    float dotR = 70;
    float dot2X = 590;
    float dot2Y = 890;
    float scaleWidth;
    float scaleHeight;

    PuzzleFinishListener listener;

    Paint redPaint = new Paint();
    boolean inDot = false;
    boolean win = false;


    public DragADotGame(Context context) {
        super(context);
        redPaint.setARGB(255,255,0,0);
        setOnTouchListener(this);
    }

    public DragADotGame(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DragADotGame(Context context, AttributeSet attrs, int defStyleAttr) {
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

        if (inDot) {
            //canvas.drawColor(redPaint.getColor());
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //dotX = event.getX();
        //dotY = event.getY();



        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:


                int distanceFinger = (int) (Math.sqrt((event.getX() - dotX*scaleWidth) * (event.getX() - dotX*scaleWidth) + ((event.getY() - dotY*scaleHeight) * (event.getY() - dotY*scaleHeight))));
                if (distanceFinger < dotR*scaleWidth) {
                    inDot = true;
                    dotX = event.getX()/scaleWidth;
                    dotY = event.getY()/scaleHeight;
                }


                //else
                //{
                //    inDot = false;
                //}
                break;

            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() > 1 && inDot){
                    //Toast toast = Toast.makeText(getContext(), "Cheater! I think not", Toast.LENGTH_SHORT);
                    //toast.show();
                    inDot = false;
                    dotX = 100;
                    dotY = 100;
                }
                else {
                    if (inDot) {
                        dotX = event.getX()/scaleWidth;
                        dotY = event.getY()/scaleHeight;
                        int distanceDots = (int) (Math.sqrt(((dot2X - dotX) * (dot2X - dotX))*scaleWidth + ((dot2Y - dotY) * (dot2Y - dotY))*scaleHeight));
                        if (distanceDots < 2 * dotR *scaleWidth) {
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



        invalidate();
        return true;
    }
}
