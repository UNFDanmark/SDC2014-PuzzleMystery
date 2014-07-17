package dk.sdc.PuzzleMystery;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by sdc on 7/17/14.
 */
public class SequenceGame extends View implements View.OnTouchListener{

    float dotR = 60;
    int gameRadius = 250;
    int dotAmount = 10;
    int rightDots = 0;
    double angle;

    PuzzleFinishListener listener;

    float scaleWidth;
    float scaleHeight;

    Paint bluePaint = new Paint();

    ArrayList<Circle> dotList = new ArrayList<Circle>();

    public SequenceGame(Context context) {
        super(context);
        setOnTouchListener(this);
        bluePaint.setColor(Color.BLUE);
        angle = 2*Math.PI/dotAmount;
        for (int i = 0; i < dotAmount; i++) {
            dotList.add(new Circle((float) (720/2+(Math.sin(i*angle))*gameRadius), (float) (1040/2+(Math.cos(i * angle))*gameRadius), dotR, bluePaint));
        }
        Collections.shuffle(dotList);
    }

    public SequenceGame(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SequenceGame(Context context, AttributeSet attrs, int defStyleAttr) {
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

        for (int i = 0; i < dotList.size(); i++) {
            Circle dot = dotList.get(i);
            canvas.drawCircle(dot.getxCoord()*scaleWidth,dot.getyCoord()*scaleHeight,dot.getRadius()*scaleWidth,dot.getPaint());
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        checkDotTap(event.getX(),event.getY());

        invalidate();
        return false;
    }

    private void checkDotTap (float x, float y) {
        for (int i = 0; i < dotList.size(); i++) {

            Circle dot = dotList.get(i);
            Paint paint = new Paint();
            paint.setColor(Color.GREEN);

            double distanceFinger = (Math.sqrt(((x-dot.getxCoord()*scaleWidth)*(x-dot.getxCoord()*scaleWidth))+(y-dot.getyCoord()*scaleHeight)*(y-dot.getyCoord()*scaleHeight)));
            if (distanceFinger < dot.getRadius()*scaleWidth*1.2) {
                if (i == rightDots) {
                    rightDots++;
                    dot.setPaint(paint);
                    dotList.set(i, dot);
                    if (rightDots == dotAmount) {
                        listener.puzzleFinished();
                    }
                }
                else {
                    resetDots();
                }
            }
        }
    }

    private void resetDots () {
        for (int i = 0; i < dotList.size(); i++) {
            Circle dot = dotList.get(i);
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            dot.setPaint(paint);
            dotList.set(i, dot);
            rightDots = 0;
        }
    }
}
