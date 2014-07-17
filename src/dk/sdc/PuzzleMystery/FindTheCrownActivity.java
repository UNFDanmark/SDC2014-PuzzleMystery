package dk.sdc.PuzzleMystery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by sdc on 7/16/14.
 */
public class FindTheCrownActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FindTheCrownGame game = new FindTheCrownGame(getApplicationContext());
        setContentView(game);
        getActionBar().setTitle("Pick up the crown");
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