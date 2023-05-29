package jj.ac.kr.discordbot.connection;

public class MusicData {

    private int seq;

    private String name;

    private int num;

    public MusicData() {
    }
    public MusicData(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }
}
