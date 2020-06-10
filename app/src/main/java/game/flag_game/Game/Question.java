package game.flag_game.Game;

import java.io.Serializable;

public class Question implements Serializable {

    private int imgResource;
    private int rightAnswer;

    public Question(int imgResource, int rightAnswer) {
        this.imgResource = imgResource;
        this.rightAnswer = rightAnswer;
    }

    public int getImgResource() {
        return imgResource;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

}
