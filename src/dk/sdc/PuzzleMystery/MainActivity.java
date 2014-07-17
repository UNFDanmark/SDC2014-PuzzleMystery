package dk.sdc.PuzzleMystery;

import android.app.ActionBar;
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

        getActionBar().hide();

        Button playButton = (Button) findViewById(R.id.LevelSelectButton);
        Button creditsButton = (Button) findViewById(R.id.credits_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent = new Intent(getApplicationContext(), LevelSelectActivity.class);
                startActivity(playIntent);
            }
        });

        creditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent creditsIntent = new Intent(getApplicationContext(), CreditsActivity.class);
                startActivity(creditsIntent);
            }
        });
    }
}
