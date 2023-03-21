package jj.ac.kr.discordbot.commands.custom;

public class OptionItem {
    private String name;
    private String value;

    public OptionItem(final String name, final String value){
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return this.name;
    }
    public String getValue() {
        return this.value;
    }

}
