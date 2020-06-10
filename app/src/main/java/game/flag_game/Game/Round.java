package game.flag_game.Game;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import game.flag_game.GameMenu;
import game.flag_game.R;
import game.flag_game.Settings.ApplicationData;

public class Round extends AppCompatActivity {

    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;

    private ApplicationData applicationData;

    private ImageView flag;

    private TextView correctOrIncorrectAnswer;

    private Question roundQuestion;

    private ArrayList<Question> availableQuestions;

    private int roundNumber;

    private int correctAnswers;

    private ProgressBar timeLeftBar;

    private boolean gamePause = false;
    private boolean gameFinished = false;

    private long roundTime = 10000;
    private float timeGone = 0;
    private Button[] answersButtons;

    private long endRoundTime = 2000;

    private CountDownTimer gameTimer = new GameTimer(roundTime,100);

    private ConstraintLayout pause_layout;

    private ArrayList<Integer> wrongAnswers;

    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);

        setupAds();

        setupPauseMenu();

        setupUIElements();

        setupAnswers();

        startRound();

    }

    private void startRound() {
        gameTimer.start();
    }

    private ArrayList<Question> takeAvailableQuestionsFromExtras(){
        return (ArrayList<Question>) Objects.requireNonNull(getIntent().getExtras()).get(getString(R.string.questions_extra));
    }

    private void randomlyPicQuestionFromAvailable(){
        Random random = new Random();

        int takeQuestion = random.nextInt(availableQuestions.size());

        roundQuestion = availableQuestions.get(takeQuestion);
        availableQuestions.remove(takeQuestion);

    }

    private void setTextForButton(Button button, int text){
        button.setText(text);
    }

    private void assignCorrectButton(ArrayList<Button> buttons, int buttonNumber){
        setTextForButton(buttons.get(buttonNumber),roundQuestion.getRightAnswer());

        flag.setImageResource(roundQuestion.getImgResource());

        createAListOfWrongAnswers();
    }

    private void createAListOfWrongAnswers(){

        wrongAnswers = new ArrayList<>();

        for (Question question : availableQuestions
        ) {
            wrongAnswers.add(question.getRightAnswer());
        }
    }

    private void assignWrongButton(ArrayList<Button> buttons, int wrongAnswerNumber, int wrongButtonNumber){

        setTextForButton(buttons.get(wrongButtonNumber),wrongAnswers.get(wrongAnswerNumber));

        wrongAnswers.remove(wrongAnswerNumber);
    }

    private ArrayList<Button> assignButton (ArrayList<Button> buttons){
        Random random = new Random();
        int pickButton = random.nextInt(buttons.size());

        if (buttons.size() == answersButtons.length){

            assignCorrectButton(buttons,pickButton);

        } else {

            int pickWrongAnswer = random.nextInt(wrongAnswers.size());

            assignWrongButton(buttons,pickWrongAnswer,pickButton);
        }

        buttons.remove(pickButton);

        return buttons;
    }

    private ArrayList<Button> makeAListOfAnswersButtons(){
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(answer1);
        buttons.add(answer2);
        buttons.add(answer3);
        buttons.add(answer4);

        return buttons;

    }

    private void assignButtons(ArrayList<Button> buttons){


        for (int x = 0; x < answersButtons.length; x++){
            buttons = assignButton(buttons);
        }

    }

    private void setupAnswers() {
        answersButtons = new Button[]{answer1,answer2,answer3,answer4};

        availableQuestions = takeAvailableQuestionsFromExtras();

        randomlyPicQuestionFromAvailable();

        assignButtons(makeAListOfAnswersButtons());

        setupOnClickListenersForAnswers();

    }

    private void setupOnClickListenersForAnswers() {
        for (Button button: answersButtons) {
            button.setOnClickListener(getOnClickListener(button));
        }
    }

    @SuppressLint("StringFormatInvalid")
    private void setupUIElements() {
        answer1 = findViewById(R.id.answer_button_1);
        answer2 = findViewById(R.id.answer_button_2);
        answer3 = findViewById(R.id.answer_button_3);
        answer4 = findViewById(R.id.answer_button_4);

        flag = findViewById(R.id.flag_image);

        applicationData = new ApplicationData(this);

        TextView round = findViewById(R.id.round_counter);

        TextView nameAndPoints = findViewById(R.id.name_and_points);
        nameAndPoints.setText(getString(R.string.name_and_points,setupNameAndPoints()));

        timeLeftBar = findViewById(R.id.time_left_for_question);


        correctOrIncorrectAnswer = findViewById(R.id.is_anser_correct);
        correctOrIncorrectAnswer.setVisibility(View.INVISIBLE);

        roundNumber = Integer.parseInt(Objects.requireNonNull(Objects.requireNonNull(getIntent().getExtras()).get(getString(R.string.round_extra))).toString());
        correctAnswers = Integer.parseInt(Objects.requireNonNull(getIntent().getExtras().get(getString(R.string.correct_answers_extra))).toString());

        round.setText(getString(R.string.round_counter, String.valueOf(roundNumber)));
    }

    private void setupPauseMenu() {
        pause_layout = findViewById(R.id.pause_layout);
        pause_layout.setVisibility(View.GONE);
    }

    public void backToGame(View view){
        pauseGameMenu();
    }

    public void backToMenu(View view){
        Intent intent = new Intent(Round.this, GameMenu.class);
        startActivity(intent);
        finish();
    }

    private void setupAds(){
        adView = findViewById(R.id.round_ad_view);

        AdRequest adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);
    }


    private View.OnClickListener getOnClickListener(final Button pressedButton){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String answer = pressedButton.getText().toString();
                gameTimer.cancel();


                if (answer.equals(getString(roundQuestion.getRightAnswer()))){
                    correctOrIncorrectAnswer.setText(R.string.correct_answer);
                    correctOrIncorrectAnswer.setBackgroundColor(Color.parseColor("#CC46AA28"));
                    correctOrIncorrectAnswer.setVisibility(View.VISIBLE);
                    applicationData.addPoints(1);
                    correctAnswers++;
                } else {
                    correctOrIncorrectAnswer.setText(R.string.bad_answer);
                    correctOrIncorrectAnswer.setBackgroundColor(Color.parseColor("#CCB41E32"));
                    correctOrIncorrectAnswer.setVisibility(View.VISIBLE);
                }
                gameFinished = true;
                gameTimer = new GameTimer(endRoundTime,100);
                gameTimer.start();
            }
        };
    }

    private void finishRound(int maxRounds){
        changeButtonsToUnClickable();
        goToNextRound(maxRounds);

    }

    private void goToNextRound(int maxRound){
        if (roundNumber >= maxRound){
            finishGame();
        } else {

            Intent intent = new Intent(Round.this, Round.class);
            intent.putExtra(getString(R.string.questions_extra),availableQuestions);
            roundNumber++;
            intent.putExtra(getString(R.string.round_extra),roundNumber);
            intent.putExtra(getString(R.string.correct_answers_extra),correctAnswers);
            startActivity(intent);
            finish();
        }

    }

    private void finishGame(){
        Intent intent = new Intent(Round.this,FinishGame.class);
        intent.putExtra(getString(R.string.correct_answers_extra),correctAnswers);
        startActivity(intent);
        finish();

    }

    private void changeButtonsToUnClickable(){
        for (Button button:answersButtons
             ) {
            button.setClickable(false);
        }
    }

    @Override
    public void onBackPressed() {
        pauseGameMenu();
    }

    private void pauseGameMenu(){

        if (!gamePause){
            gamePause = true;
            gameTimer.cancel();

            for (Button button:answersButtons) {
                button.setVisibility(View.GONE);
            }
            pause_layout.setVisibility(View.VISIBLE);

        } else {

            resumeGame();
        }
    }

    private void resumeGame(){
        gamePause = false;

        long timeLeft;
        if (!gameFinished){
            timeLeft = roundTime - (long)timeGone;
        } else {
            timeLeft = endRoundTime - (long)timeGone;
        }

        gameTimer = new GameTimer(timeLeft,100);

        for (Button button:answersButtons) {
            button.setVisibility(View.VISIBLE);
        }
        pause_layout.setVisibility(View.GONE);
        gameTimer.start();

    }

    private class GameTimer extends CountDownTimer{

        GameTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (!gameFinished){
                timeGone = roundTime - millisUntilFinished;
            } else {
                timeGone = endRoundTime - millisUntilFinished;
            }

            float timeGoneInFloat = timeGone;
            float percentageTimeGone = timeGoneInFloat / roundTime;

            float progress;
            if (!gameFinished){
                progress = percentageTimeGone * 100f;
            } else {
                progress = 100;
            }


            timeLeftBar.setProgress((int)progress);
        }

        @Override
        public void onFinish() {
            if (!gameFinished){
                correctOrIncorrectAnswer.setText(R.string.time_out);
                correctOrIncorrectAnswer.setBackgroundColor(Color.parseColor("#CCB41E32"));
                correctOrIncorrectAnswer.setVisibility(View.VISIBLE);

                timeLeftBar.setProgress(100);

                gameFinished = true;
                gameTimer = new GameTimer(endRoundTime,100);
                gameTimer.start();


            } else {
                finishRound(applicationData.getRound());
            }

        }
    }

    private String setupNameAndPoints(){
        return applicationData.getName() +
                " " +
                applicationData.getPoints();
    }



}
