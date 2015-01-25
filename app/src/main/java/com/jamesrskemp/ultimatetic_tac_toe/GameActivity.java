package com.jamesrskemp.ultimatetic_tac_toe;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by James on 1/25/2015.
 */
public class GameActivity extends Activity {

	public static final String KEY_RESTORE = "key_restore";
	public static final String PREF_RESTORE = "pref_restore";
	private GameFragment mGameFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		// Restore game
		mGameFragment = (GameFragment) getFragmentManager().findFragmentById(R.id.game_fragment);
		boolean restore = getIntent().getBooleanExtra(KEY_RESTORE, false);
		if (restore) {
			String gameData = getPreferences(MODE_PRIVATE).getString(PREF_RESTORE, null);

			if (gameData != null) {
				mGameFragment.putState(gameData);
			}
		}
		Log.d("Ultimate Tic-Tac-Toe", "restore = " + restore);
	}

	@Override
	protected void onPause() {
		super.onPause();
		String gameData = mGameFragment.getState();
		getPreferences(MODE_PRIVATE).edit().putString(PREF_RESTORE, gameData).commit();

		Log.d("Ultimate Tic-Tac-Toe", "state = " + gameData);
	}

	public void restartGame() {
		mGameFragment.restartGame();
	}
}
