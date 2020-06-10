package game.flag_game;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

public class ActivitiesSupport{

    public void changeActivity(Context contextFromActivity, Class classToStart){

        Intent intent = new Intent(contextFromActivity,classToStart);
        contextFromActivity.startActivity(intent);

    }

    public FlagGameExtraObject makeFlagGameExtra(String name, Object extra){
        return new FlagGameExtraObject(name,extra);
    }

    public void changeActivity(Context contextFromActivity, Class classToStart, ArrayList<FlagGameExtraObject> extras){
        Intent intent = new Intent(contextFromActivity,classToStart);

        for (FlagGameExtraObject extra:
             extras) {
            if (extra.getExtraObject().getClass().equals(ArrayList.class)){
                intent.putExtra(extra.getName(),(ArrayList<String>) extra.getExtraObject());
            } else {
                intent.putExtra(extra.getName(),extra.getExtraObject().toString());
            }

        }

        contextFromActivity.startActivity(intent);
    }
}
