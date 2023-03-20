package jj.ac.kr.discordbot.listener;

public class StringConversion {
    public String splitUserName(String userId) {
        String onlyUserId = userId.substring(1, userId.lastIndexOf("("));
        return onlyUserId;
    }
}
