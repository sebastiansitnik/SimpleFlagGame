package game.flag_game.Game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

import game.flag_game.GameMenu;
import game.flag_game.R;
import game.flag_game.Settings.ApplicationData;

public class FinishGame extends AppCompatActivity {

    private Button playAgain;
    private Button backToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_game);

        ApplicationData applicationData = new ApplicationData(this);

        setupButtons();

        setupFinishGameText(applicationData.getName(),String.valueOf(applicationData.getRound()));

    }

    private int colorId;

    @SuppressLint("StringFormatInvalid")
    private String getFinishedText(String name, String maxRounds){
        String pointsGet = Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).get("correctAnswers")).toString();

        StringBuilder stringBuilder = new StringBuilder();


        if (Float.parseFloat(pointsGet) / Float.parseFloat(maxRounds) > 0.5){

            stringBuilder.append(getString(R.string.winning_conditions,name));
            colorId = Color.parseColor("#CC46AA28");

        } else {
            stringBuilder.append(getString(R.string.loosing_conditions,name));
            colorId = Color.parseColor("#CCB41E32");
        }

        stringBuilder.append(" ");
        stringBuilder.append(getString(R.string.points_acquired,pointsGet));
        stringBuilder.append(" ");
        stringBuilder.append(getString(R.string.max_rounds,maxRounds));

        return stringBuilder.toString();
    }

    private void setupFinishGameText(String name, String maxRounds){
        TextView finishGameText = findViewById(R.id.win_or_loss_text);

        finishGameText.setText(getFinishedText(name, maxRounds));
        finishGameText.setBackgroundColor(colorId);
    }

    private void setupButtons(){
        playAgain = findViewById(R.id.play_again_button);
        backToMenu = findViewById(R.id.back_to_menu);

        setupOnClickListeners();
    }

    private void setupOnClickListeners(){
        playAgain.setOnClickListener(setClick(playAgain));
        backToMenu.setOnClickListener(setClick(backToMenu));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToNextActivity(GameMenu.class);
    }

    private View.OnClickListener setClick(Button buttonPressed){
        final Class nextActivityClass;
        if (buttonPressed.getId() == R.id.play_again_button){
            nextActivityClass = Play.class;
        } else {
            nextActivityClass = GameMenu.class;
        }

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextActivity(nextActivityClass);
            }
        };

    }
    private void goToNextActivity(Class nextActivityClass){
        Intent intent = new Intent(FinishGame.this,nextActivityClass);
        startActivity(intent);
        finish();
    }
}
