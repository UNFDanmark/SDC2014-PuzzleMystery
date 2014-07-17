package dk.sdc.PuzzleMystery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by sdc on 7/16/14.
 */
public class DragADot2Activity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DragADotGame2 game = new DragADotGame2(getApplicationContext());
        setContentView(game);
        getActionBar().setTitle("Mazer");
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