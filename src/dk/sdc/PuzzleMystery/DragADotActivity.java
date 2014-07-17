package dk.sdc.PuzzleMystery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by sdc on 7/15/14.
 */
public class DragADotActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DragADotGame game = new DragADotGame(getApplicationContext());
        setContentView(game);

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