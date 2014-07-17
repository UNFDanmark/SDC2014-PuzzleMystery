package dk.sdc.PuzzleMystery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by sdc on 7/15/14.
 */
public class FreeThePrincessActivity extends Activity {
    private int winTaps = 20;
    private int tapCount = 0;
    Paint filter = new Paint();
    Vibrator vibrate;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vibrate = (Vibrator) this.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        setContentView(R.layout.freetheprincess);
        getActionBar().setTitle("Ice tap");
        getActionBar().setIcon(R.drawable.jigsaw);
        filter.setARGB(255, 77, 148, 255);
        final ImageButton myButton = (ImageButton) findViewById(R.id.isButton);
        myButton.setColorFilter(filter.getColor());
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapCount++;
                vibrate.vibrate(100);
                double jump = 255 / winTaps;
                int transparency = (int) (255 - (tapCount * jump));
                filter.setARGB(transparency, 77, 148, 255);
                myButton.setColorFilter(filter.getColor());
                myButton.setBackgroundColor(filter.getColor());
                if (tapCount == winTaps) {
                    //myButton.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.princess_theme_av2));
                    Intent winscreen = new Intent(getApplicationContext(), WinScreen.class);
                    startActivity(winscreen);

                    finish();
                }
            }
        });
    }
}
