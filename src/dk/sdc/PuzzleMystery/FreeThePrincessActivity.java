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
    private int winClicks = 10;
    private int Tællertilis = winClicks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freetheprincess);
        final ImageButton myButton = (ImageButton) findViewById(R.id.isButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tællertilis--;
                //int transparency = 255*(Tællertilis/winClicks);
                //myButton.setBackgroundColor(Color.argb(100,77,148,255));
                if (Tællertilis <= 0) {
                    //myButton.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.princess_theme_av2));
                    myButton.setBackgroundColor(Color.argb(0, 0, 0, 0));
                }
            }
        });
    }
}