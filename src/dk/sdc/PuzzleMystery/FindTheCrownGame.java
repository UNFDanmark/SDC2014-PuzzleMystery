package dk.sdc.PuzzleMystery;

import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by sdc on 7/16/14.
 */

public class FindTheCrownGame extends View implements View.OnTouchListener {

    float dotRadius = 60;
    float scaleWidth;
    float scaleHeight;
    int dotAmount = 35;
    int rowCount = 0;
    int rng;
    boolean win = false;

    PuzzleFinishListener listener;
    Bitmap crown;
    Paint dotPaint = new Paint();
    ArrayList<Circle> dotList = new ArrayList<Circle>();


    public FindTheCrownGame(Context context) {
        super(context);
        setOnTouchListener(this);

        dotPaint.setARGB(255, 77, 148, 255);
        rng = (int) (Math.random()*35);
        crown = BitmapFactory.decodeResource(getResources(), R.drawable.crown);

        for (int i = 0; i < dotAmount; i++) {
            if (i%5 == 0)
                rowCount++;
            float x = 100+(i%5)*130;
            float y = 130*rowCount;
            dotList.add(new Circle(x, y, dotRadius, dotPaint));
        }
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



        for (int i = 0; i < dotList.size(); i++) {
            Circle circle = dotList.get(i);
            if (i == rng) {
                canvas.drawBitmap(crown, circle.getxCoord()*scaleWidth-crown.getWidth()/2, circle.getyCoord()*scaleHeight-crown.getHeight()/2, dotPaint);
            }
            canvas.drawCircle(circle.getxCoord()*scaleWidth, circle.getyCoord()*scaleHeight, circle.getRadius()*scaleWidth, circle.getPaint());
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        checkDotTap(event.getX(), event.getY());

        invalidate();
        return false;
    }

    private void checkDotTap (float x, float y) {
        for (int i = 0; i < dotList.size(); i++) {

            Circle dot = dotList.get(i);
            Paint paint = new Paint();
            paint.setARGB(0,0,0,0);

            double distanceFinger = (Math.sqrt(((x-dot.getxCoord()*scaleWidth)*(x-dot.getxCoord()*scaleWidth))+(y-dot.getyCoord()*scaleHeight)*(y-dot.getyCoord()*scaleHeight)));
            if (distanceFinger < dot.getRadius()*scaleWidth) {
                if (i == rng && dot.getPaint().getColor() == paint.getColor() && !win) {
                    listener.puzzleFinished();
                    win = true;
                }
                //Paint paint = new Paint();
                dot.setPaint(paint);
                dotList.set(i, dot);
//                if (i == rng) {
//
//                }
            }
        }
    }


}
