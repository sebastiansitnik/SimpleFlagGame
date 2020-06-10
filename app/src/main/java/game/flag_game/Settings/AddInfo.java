package game.flag_game.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import game.flag_game.GameMenu;
import game.flag_game.R;

public class AddInfo extends AppCompatActivity {

    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        button = findViewById(R.id.confirm_button);
        editText = findViewById(R.id.type_name_field);

        button.setOnClickListener(confirmButtonOnClickListener());


    }

    private View.OnClickListener confirmButtonOnClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editText.getText().toString();
                createLoginInfo(name);
                goToMenu();

            }
        };
    }

    private void createLoginInfo(String name){
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName(name);
        loginInfo.setPoints(0);


        ApplicationData ad = new ApplicationData(this);
        ad.setName(loginInfo.getName());
        ad.setPoints(loginInfo.getPoints());
        ad.setRound(5);

    }

    private void goToMenu(){
        Intent intent = new Intent(AddInfo.this, GameMenu.class);
        startActivity(intent);
        finish();
    }
}
