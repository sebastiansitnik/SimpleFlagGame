package game.flag_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import game.flag_game.Game.Play;
import game.flag_game.Settings.ApplicationData;
import game.flag_game.Settings.GameSettings;

public class GameMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        setupUI();
    }

    private void setupUI() {
        setupButtons();
        setupTextView();
    }

    private void setupTextView() {
        TextView pointsText = findViewById(R.id.game_menu_points);
        pointsText.setText(getString(R.string.points, setupPoints()));
    }

    private void setupButtons() {
        Button playButton = findViewById(R.id.play_button);
        Button settingsButton = findViewById(R.id.settings_button);

        playButton.setOnClickListener(setupOnClickListener(Play.class));
        settingsButton.setOnClickListener(setupOnClickListener(GameSettings.class));
    }

    private View.OnClickListener setupOnClickListener(final Class nextActivityClass) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivitiesSupport activitiesSupport = new ActivitiesSupport();
                activitiesSupport.changeActivity(GameMenu.this, nextActivityClass);
                finish();
            }
        };
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private String setupPoints() {
        ApplicationData applicationData = new ApplicationData(this);
        return String.valueOf(applicationData.getPoints());
    }
}
