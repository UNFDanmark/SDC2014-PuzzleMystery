package dk.sdc.PuzzleMystery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by sdc on 7/17/14.
 */
public class WinScreen extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winscreen_layout);
        Button continueButton = (Button) findViewById(R.id.ContinueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}