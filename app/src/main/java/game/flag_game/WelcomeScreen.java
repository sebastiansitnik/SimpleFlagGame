package game.flag_game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import game.flag_game.Settings.AddInfo;
import game.flag_game.Settings.ApplicationData;
import game.flag_game.Settings.LoginInfo;

public class WelcomeScreen extends AppCompatActivity {

    final static long delayForLogo = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {

            setupTimerTask(checkIfUserIsLogged());

            setupConnectionForAds();

        }

    }

    private void setupConnectionForAds(){

        setupTestingDevices();
        MobileAds.initialize(this,getString(R.string.testing_add_id));

    }

    private void setupTestingDevices(){
        List<String> testDevices = new ArrayList<>();
        testDevices.add(getString(R.string.testing_device_1));

        RequestConfiguration requestConfiguration = new RequestConfiguration.Builder().setTestDeviceIds(testDevices).build();
        MobileAds.setRequestConfiguration(requestConfiguration);
    }

    private void setupTimerTask(boolean userLogged) {
        Timer timer = new Timer();
        TimerTask timerTask = goToNextActivity(userLogged);
        timer.schedule(timerTask, delayForLogo);
    }

    private boolean checkIfUserIsLogged() {
        LoginInfo loginInfo = loadLoginInfo();
        return !loginInfo.getName().isEmpty();
    }

    private LoginInfo loadLoginInfo() {

        LoginInfo loginInfo = new LoginInfo();

        ApplicationData applicationData = new ApplicationData(this);

        if (applicationData.getName().isEmpty()) {
            loginInfo.setName("");
        } else {
            loginInfo.setName(applicationData.getName());
            loginInfo.setPoints(applicationData.getPoints());
        }

        return loginInfo;

    }

    private TimerTask goToNextActivity(final boolean nameExisted) {

        return new TimerTask() {
            @Override
            public void run() {
                Class nextActivity;

                if (nameExisted) {
                    nextActivity = gameMenuClass;
                } else {
                    nextActivity = loginInfoClass;
                }
                ActivitiesSupport activitiesSupport = new ActivitiesSupport();
                activitiesSupport.changeActivity(WelcomeScreen.this, nextActivity);
                finish();

            }
        };
    }

    private Class gameMenuClass = GameMenu.class;
    private Class loginInfoClass = AddInfo.class;

}
