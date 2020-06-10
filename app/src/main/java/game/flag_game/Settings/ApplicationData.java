package game.flag_game.Settings;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class ApplicationData {

    private SharedPreferences sharedPreferences;

    private static final String DATA_REFERENCE = "loginInfo";
    private static final String NAME_REFERENCE = "name";
    private static final String POINTS_REFERENCE = "points";
    private static final String MAX_ROUNDS_REFERENCE = "maxRounds";
    private static final String ROUND_REFERENCE = "round";

    public ApplicationData(AppCompatActivity appCompatActivity) {
        this.sharedPreferences = appCompatActivity.getSharedPreferences(DATA_REFERENCE, Context.MODE_PRIVATE);
    }

    private void put(String reference, String data){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(reference,data);
        editor.apply();
    }

    private void change(String reference, String data){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(reference);
        editor.putString(reference,data);
        editor.apply();
    }

    private void delete(String reference){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(reference);
        editor.apply();
    }

    private String get(String reference){
        return sharedPreferences.getString(reference,"");
    }

    public String getName(){
        return sharedPreferences.getString(NAME_REFERENCE,"");
    }

    public void setName(String name){
        put(NAME_REFERENCE,name);
    }

    public void updateName(String name){
        change(NAME_REFERENCE,name);
    }

    public void deleteName(){
        delete(NAME_REFERENCE);
    }

    public void setPoints(long points){
        put(POINTS_REFERENCE,String.valueOf(points));
    }

    public void updatePoints(long points){
        change(POINTS_REFERENCE,String.valueOf(points));
    }

    public void addPoints(long points){
        long newPoints = getPoints() + points;
        change(POINTS_REFERENCE,String.valueOf(newPoints));
    }

    public long getPoints(){

        String points = get(POINTS_REFERENCE);

        if ("".equals(points)){
            points = "0";
        }

        return Long.parseLong(points);
    }

    public void deletePoints(){
        delete(POINTS_REFERENCE);
    }

    public void setMaxRounds(int maxRounds){
        put(MAX_ROUNDS_REFERENCE,String.valueOf(maxRounds));
    }

    public void changeMaxRounds(int maxRounds){
        change(MAX_ROUNDS_REFERENCE,String.valueOf(maxRounds));
    }

    public void setRound(int round){
        put(ROUND_REFERENCE,String.valueOf(round));
    }

    public int getRound(){

        String rounds = get(ROUND_REFERENCE);

        if ("".equals(rounds)){
            rounds = "5";
        }

        return  Integer.parseInt(rounds);
    }




}
