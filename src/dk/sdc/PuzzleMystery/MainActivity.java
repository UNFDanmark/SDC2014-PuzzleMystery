package dk.sdc.PuzzleMystery;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        Button playButton = (Button) findViewById(R.id.play_button);
        Button levelSelect = (Button) findViewById(R.id.LevelSelectButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent = new Intent(getApplicationContext(), DragADotActivity.class);
                startActivity(playIntent);

            }
        });

        levelSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playLevelSelect = new Intent(getApplicationContext(), LevelSelectActivity.class);
                startActivity(playLevelSelect);
            }
        });
    }
}
