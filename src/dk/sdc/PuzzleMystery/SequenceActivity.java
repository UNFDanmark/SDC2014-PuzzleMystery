package dk.sdc.PuzzleMystery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by sdc on 7/17/14.
 */
public class SequenceActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SequenceGame game = new SequenceGame(getApplicationContext());
        setContentView(game);
        getActionBar().setTitle("Memory is key");
        getActionBar().setIcon(R.drawable.jigsaw);

        game.setPuzzleFinishListener(new PuzzleFinishListener() {
            @Override
            public void puzzleFinished() {
                Intent winscreen = new Intent(getApplicationContext(), WinScreen.class);
                startActivity(winscreen);
                finish();
            }
        });
    }
}