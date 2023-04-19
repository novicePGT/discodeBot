package jj.ac.kr.discordbot.connection;

import lombok.Data;

@Data
public class EmbedExamItem {

    private int seq;
    private String view1;
    private String view2;
    private String view3;
    private String view4;
    private String question;
    private String answer;

    public EmbedExamItem() {

    }

    public EmbedExamItem(String view1, String view2, String view3, String view4, String question, String answer) {
        this.view1 = view1;
        this.view2 = view2;
        this.view3 = view3;
        this.view4 = view4;
        this.question = question;
        this.answer = answer;
    }
}
