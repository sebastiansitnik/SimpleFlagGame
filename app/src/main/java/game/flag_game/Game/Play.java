package game.flag_game.Game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

import game.flag_game.ActivitiesSupport;
import game.flag_game.FlagGameExtraObject;
import game.flag_game.R;


public class Play extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        goToFirstRound();

    }

    private void goToFirstRound(){

        ActivitiesSupport as = new ActivitiesSupport();

        ArrayList<FlagGameExtraObject> extras = new ArrayList<>();
        extras.add(as.makeFlagGameExtra(getString(R.string.questions_extra),createQuestions()));
        extras.add(as.makeFlagGameExtra(getString(R.string.round_extra),1));
        extras.add(as.makeFlagGameExtra(getString(R.string.correct_answers_extra),0));

        as.changeActivity(Play.this,Round.class,extras);

    }

    private ArrayList<Question> createQuestions(){

        ArrayList<Question> questions = new ArrayList<>();

        questions.add(new Question(R.drawable.albania,R.string.albania));
        questions.add(new Question(R.drawable.andorra,R.string.andorra));
        questions.add(new Question(R.drawable.austria,R.string.austria));
        questions.add(new Question(R.drawable.belarus,R.string.belarus));
        questions.add(new Question(R.drawable.belgium,R.string.belgium));
        questions.add(new Question(R.drawable.bosniaandherzegovina,R.string.bosnia));
        questions.add(new Question(R.drawable.bulgaria,R.string.bulgaria));
        questions.add(new Question(R.drawable.croatia,R.string.croatia));
        questions.add(new Question(R.drawable.czechrepublic,R.string.czech));
        questions.add(new Question(R.drawable.denmark,R.string.denmark));
        questions.add(new Question(R.drawable.estonia,R.string.estonia));
        questions.add(new Question(R.drawable.finland,R.string.finland));
        questions.add(new Question(R.drawable.france,R.string.france));
        questions.add(new Question(R.drawable.germany,R.string.germany));
        questions.add(new Question(R.drawable.greece,R.string.greece));
        questions.add(new Question(R.drawable.hungary,R.string.hungary));
        questions.add(new Question(R.drawable.iceland,R.string.iceland));
        questions.add(new Question(R.drawable.ireland,R.string.ireland));
        questions.add(new Question(R.drawable.italy,R.string.italy));
        questions.add(new Question(R.drawable.kazakhstan,R.string.kazakhstan));
        questions.add(new Question(R.drawable.latvia,R.string.latvia));
        questions.add(new Question(R.drawable.liechtenstein,R.string.liechtenstein));
        questions.add(new Question(R.drawable.lithuania,R.string.lithuania));
        questions.add(new Question(R.drawable.luxembourg,R.string.luxembourg));
        questions.add(new Question(R.drawable.malta,R.string.malta));
        questions.add(new Question(R.drawable.moldova,R.string.moldova));
        questions.add(new Question(R.drawable.monaco,R.string.monaco));
        questions.add(new Question(R.drawable.montenegro,R.string.montenegro));
        questions.add(new Question(R.drawable.netherlands,R.string.netherlands));
        questions.add(new Question(R.drawable.northmacedonia,R.string.north_macedonia));
        questions.add(new Question(R.drawable.norway,R.string.norway));
        questions.add(new Question(R.drawable.poland,R.string.poland));
        questions.add(new Question(R.drawable.portugal,R.string.portugal));
        questions.add(new Question(R.drawable.romania,R.string.romania));
        questions.add(new Question(R.drawable.russia,R.string.russia));
        questions.add(new Question(R.drawable.sanmarino,R.string.sanMarino));
        questions.add(new Question(R.drawable.serbia,R.string.serbia));
        questions.add(new Question(R.drawable.slovakia,R.string.slovakia));
        questions.add(new Question(R.drawable.slovenia,R.string.slovenia));
        questions.add(new Question(R.drawable.spain,R.string.spain));
        questions.add(new Question(R.drawable.sweden,R.string.sweden));
        questions.add(new Question(R.drawable.switzerland,R.string.switzerland));
        questions.add(new Question(R.drawable.turkey,R.string.turkey));
        questions.add(new Question(R.drawable.ukraine,R.string.ukraine));
        questions.add(new Question(R.drawable.unitedkingdom,R.string.united_kingdom));
        questions.add(new Question(R.drawable.vatican,R.string.vatican));

        return questions;
    }


}
