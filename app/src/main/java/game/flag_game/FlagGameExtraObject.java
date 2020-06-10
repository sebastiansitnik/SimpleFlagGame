package game.flag_game;

public class FlagGameExtraObject {

    private String name;
    private Object extraObject;

    public FlagGameExtraObject(String name, Object extraObject) {
        this.name = name;
        this.extraObject = extraObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getExtraObject() {
        return extraObject;
    }

}
