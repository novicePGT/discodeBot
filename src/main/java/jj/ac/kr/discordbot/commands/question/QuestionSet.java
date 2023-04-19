package jj.ac.kr.discordbot.commands.question;

import jj.ac.kr.discordbot.commands.custom.ExamItem;

import java.util.ArrayList;
import java.util.List;

public class QuestionSet {

    List<ExamItem> examItems = new ArrayList<>();

    private String[] examQuestion = {
            "자바의 장점", "자바의 단점", "자바 컴파일러", "자바 바이트코드", "자바 가상머신(JVM)"
    };

    public List<ExamItem> questionInform() {
        return setList();
    }

    private List<ExamItem> setList() {
        for (int i=0; i< examQuestion.length; i++) {
            examItems.add(new ExamItem(examQuestion[i], examQuestion[i]));
        }
        return this.examItems;
    }
}
