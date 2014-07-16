package dk.sdc.PuzzleMystery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by sdc on 7/16/14.
 *///kagemand
public class LevelSelectActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelselectlayout);

        Button level1Button = (Button) findViewById(R.id.Puzzle1Button);
        Button level2Button = (Button) findViewById(R.id.Puzzle2Button);
        Button level3Button = (Button) findViewById(R.id.Puzzle3Button);
        Button level4Button = (Button) findViewById(R.id.Puzzle4Button);
        Button level5Button = (Button) findViewById(R.id.Puzzle5Button);
        Button level6Button = (Button) findViewById(R.id.Puzzle6Button);

        level1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent puzzle1Intent = new Intent(getApplicationContext(),DragADotActivity.class);
                startActivity(puzzle1Intent);
            }
        });

        level2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent puzzle2Intent = new Intent(getApplicationContext(), FreeThePrincessActivity.class);
                startActivity(puzzle2Intent);
            }
        });
        level3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent puzzle3Intent = new Intent(getApplicationContext(), DragADot2Activity.class);
                startActivity(puzzle3Intent);
            }
        });
    }
}