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
        getActionBar().setTitle("Choose your path");
        getActionBar().setIcon(R.drawable.jigsaw);

        Button dragPuzzle1Button = (Button) findViewById(R.id.dragPuzzle1);
        Button dragPuzzle2Button = (Button) findViewById(R.id.dragPuzzle2);
        Button dragPuzzle3Button = (Button) findViewById(R.id.dragPuzzle3);
        Button dragPuzzle4Button = (Button) findViewById(R.id.dragPuzzle4);
        Button clickPuzzle1Button = (Button) findViewById(R.id.clickPuzzle1);
        Button clickPuzzle2Button = (Button) findViewById(R.id.clickPuzzle2);
        Button clickPuzzle3Button = (Button) findViewById(R.id.clickPuzzle3);

        dragPuzzle1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent puzzle1Intent = new Intent(getApplicationContext(), DragADotActivity.class);
                startActivity(puzzle1Intent);
            }
        });

        dragPuzzle2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent puzzle2Intent = new Intent(getApplicationContext(), DragADot2Activity.class);
                startActivity(puzzle2Intent);
            }
        });
        dragPuzzle3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent puzzle3Intent = new Intent(getApplicationContext(), DragADot3Activity.class);
                startActivity(puzzle3Intent);
            }
        });

        dragPuzzle4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent puzzle4Intent = new Intent(getApplicationContext(), DragADot4Activity.class);
                startActivity(puzzle4Intent);
            }
        });

        clickPuzzle1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent puzzle4Intent = new Intent(getApplicationContext(), FreeThePrincessActivity.class);
                startActivity(puzzle4Intent);
            }
        });

        clickPuzzle2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent puzzle5Intent = new Intent(getApplicationContext(), FindTheCrownActivity.class);
                startActivity(puzzle5Intent);
            }
        });

        clickPuzzle3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent puzzle6Intent = new Intent(getApplicationContext(), SequenceActivity.class);
                startActivity(puzzle6Intent);
            }
        });


    }
}