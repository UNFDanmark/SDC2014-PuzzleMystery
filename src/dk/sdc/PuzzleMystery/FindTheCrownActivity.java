package dk.sdc.PuzzleMystery;

import android.app.Activity;
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
    }
}