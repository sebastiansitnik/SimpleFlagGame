package game.flag_game.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import game.flag_game.ActivitiesSupport;
import game.flag_game.GameMenu;
import game.flag_game.R;

public class GameSettings extends AppCompatActivity {

    private TextView displayedName;
    private TextView displayedRounds;

    private RadioGroup chooseRoundsGroup;

    private static ApplicationData ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);

        setupButtons();
        setupText();
        setupRadioButtonsForRounds();


    }

    private void setupButtons(){
        Button changeNameButton = findViewById(R.id.change_name_button);
        Button changeRoundNumber = findViewById(R.id.change_rounds_button);
        Button backToMenu = findViewById(R.id.back_to_menu_from_settings);

        changeNameButton.setOnClickListener(onClickListenerForChangingName());
        changeRoundNumber.setOnClickListener(onClickListenerForChangingRounds());
        backToMenu.setOnClickListener(backToMenuOnClickListener());
    }

    private void setupText(){
        displayedName = findViewById(R.id.textName);
        displayedRounds = findViewById(R.id.rounds_number);

        ad = new ApplicationData(this);

        displayedRounds.setText(String.valueOf(ad.getRound()));
        displayedName.setText(ad.getName());
    }

    private void setupRadioButtonsForRounds(){
        chooseRoundsGroup = findViewById(R.id.choose_runds_group);
        RadioButton chooseRound1 = findViewById(R.id.choose_rounds_radioButton1);
        RadioButton chooseRound2 = findViewById(R.id.choose_rounds_radioButton2);
        RadioButton chooseRound3 = findViewById(R.id.choose_rounds_radioButton3);
        RadioButton chooseRound4 = findViewById(R.id.choose_rounds_radioButton4);

        RadioButton[] radioButtons = new RadioButton[]{chooseRound1, chooseRound2, chooseRound3, chooseRound4};
        setupOnClickListenersForRadioButtons(radioButtons);
    }
    private void setupOnClickListenersForRadioButtons(RadioButton[] radioButtons){
        for (RadioButton radioButton: radioButtons) {
            radioButton.setOnClickListener(onClickListenerForRounds(radioButton));
        }
    }


    @Override
    public void onBackPressed() {
        exitSettings();
    }

    private View.OnClickListener backToMenuOnClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               exitSettings();
            }
        };
    }

    private void exitSettings(){
        ActivitiesSupport activitiesSupport = new ActivitiesSupport();
        activitiesSupport.changeActivity(GameSettings.this,GameMenu.class);
        finish();
    }

    private View.OnClickListener onClickListenerForChangingName() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameSettings.this, AddInfo.class);
                startActivity(intent);
                finish();
            }
        };
    }

    private View.OnClickListener onClickListenerForChangingRounds() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundsChangerVisibility(true);
            }
        };
    }

    private void roundsChangerVisibility(boolean changing){
        if (changing){
            displayedRounds.setVisibility(View.GONE);
            displayedName.setVisibility(View.GONE);
            chooseRoundsGroup.setVisibility(View.VISIBLE);
        } else {
            displayedName.setVisibility(View.VISIBLE);
            displayedRounds.setVisibility(View.VISIBLE);
            chooseRoundsGroup.setVisibility(View.GONE);
        }
    }

    private void setRounds(int newRounds){
        ad.setRound(newRounds);
        displayedRounds.setText(String.valueOf(ad.getRound()));
        roundsChangerVisibility(false);

    }

    private View.OnClickListener onClickListenerForRounds(final RadioButton radioButton){
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int newRounds;

                if (radioButton.getText().toString().equals(getString(R.string.choose_rounds_1))){
                    newRounds = 5;
                } else {
                    if (radioButton.getText().toString().equals(getString(R.string.choose_rounds_2))){
                        newRounds = 10;
                    } else {
                        if (radioButton.getText().toString().equals(getString(R.string.choose_rounds_3))){
                            newRounds = 20;
                        } else {
                            if (radioButton.getText().toString().equals(getString(R.string.choose_rounds_4))){
                                newRounds = 30;
                            } else {
                                newRounds = 5;
                            }
                        }
                    }
                }
                setRounds(newRounds);
            }
        };
    }

}
