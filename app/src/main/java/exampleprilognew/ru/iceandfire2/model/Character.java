package exampleprilognew.ru.iceandfire2.model;

/**
 * Created by Максим on 11.08.2017.
 */

public class Character {

private String nameHerou;
private String gender;
private String title;

    public Character(String name, String gender, String title) {
        this.nameHerou = name;
        this.gender = gender;
        this.title = title;
    }

    public String getNameHerou() {
        return nameHerou;
    }

    public String getGender() {
        return gender;
    }

    public String getTitle() {
        return title;
    }

}
