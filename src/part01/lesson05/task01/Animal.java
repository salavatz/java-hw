package part01.lesson05.task01;

public class Animal {
    private final int id;
    private final String nickname;
    private final Person owner;
    private final int weight;

    public Animal(int id, String nickname, Person owner, int weight) {
        this.id = id;
        this.nickname = nickname;
        this.owner = owner;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public Person getOwner() {
        return owner;
    }

    public int getWeight() {
        return weight;
    }

}
