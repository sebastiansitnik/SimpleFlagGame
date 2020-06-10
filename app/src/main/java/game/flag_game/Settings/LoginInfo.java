package game.flag_game.Settings;

import androidx.annotation.NonNull;

public class LoginInfo {

    private String name;
    private long points;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    @NonNull
    @Override
    public String toString() {
        return "LoginInfo{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }
}
