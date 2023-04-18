package jj.ac.kr.discordbot.commands.custom;

import lombok.Data;

@Data
public class ExamItem {

    private int seq;
    private String name;
    private String value;

    public ExamItem(String name, String value) {
        this.name = name;
        this.value = value;
    }

}
