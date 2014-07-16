package dk.sdc.PuzzleMystery;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by sdc on 7/15/14.
 */
public class FreeThePrincessActivity extends Activity {
    private int winClicks = 25;
    private int Tællertilis = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freetheprincess);
        final ImageButton myButton = (ImageButton) findViewById(R.id.isButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tællertilis++;
                double jump = 255/winClicks;
                int transparency = (int) (255-(Tællertilis*jump));
                myButton.setBackgroundColor(Color.argb(transparency,77,148,255));
                if (Tællertilis >= winClicks) {
                    //myButton.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.princess_theme_av2));
                    myButton.setBackgroundColor(Color.argb(0, 0, 0, 0));
                    finish();
                }
            }
        });
    }
}
