package dk.sdc.PuzzleMystery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by sdc on 7/16/14.
 */

public class FindTheCrownGame extends View implements View.OnTouchListener {

    float dotRadius = 60;
    int dotAmount = 35;
    int rowCount = 0;
    Paint dotPaint = new Paint();
    ArrayList<Circle> dotList = new ArrayList<Circle>();


    public FindTheCrownGame(Context context) {
        super(context);

        dotPaint.setARGB(255, 77, 148, 255);
        for (int i = 0; i < dotAmount; i++) {
            if (i%5 == 0)
                rowCount++;
            float x = 100+(i%5)*130;
            float y = 130*rowCount;
            dotList.add(new Circle(x, y, dotRadius, dotPaint));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < dotList.size(); i++) {
            Circle circle = dotList.get(i);
            canvas.drawCircle(circle.getxCoord(), circle.getyCoord(), circle.getRadius(), circle.getPaint());
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return false;
    }
}
