package jj.ac.kr.discordbot.commands.place;


import jj.ac.kr.discordbot.commands.custom.OptionItem;

import java.util.ArrayList;
import java.util.List;

public class PlaceInform {

    List<OptionItem> placeInforms = new ArrayList<>();

    private String[] majorLocation = {
            "공학1관", "공학2관", "공학실습동", "교수연구동", "대학본관", "미생물관", "순영관", "스타홈","예술관", "예술관 별관", "자유관",
            "신정문","구정문","지역혁신관", "진리관", "창조관","천잠관", "체육관", "평화관", "희망홀", "스타센터"
    };
    private String[] annexLocation = {
            "EM생활문화관", "EM연구동", "골프연습장", "공예관", "국제교육관", "군사교육관", "노천극장", "대학교회", "변전소", "창고동", "믿음관",
            "한지산업관", "벤처창업관", "스타빌", "스타짐","스타타워", "스타누리", "스타드림관", "학생회관"
    };

    public List<OptionItem> locationInform() {
        return setList();
    }

    public List<OptionItem> locationInform2() {
        return setList2();
    }

    private List<OptionItem> setList() {
        for(int i = 0 ; i < majorLocation.length ; i++){
            placeInforms.add(new OptionItem(majorLocation[i], majorLocation[i]));
        }
        return this.placeInforms;
    }

    private List<OptionItem> setList2() {
        for(int i = 0 ; i < annexLocation.length ; i++){
            placeInforms.add(new OptionItem(annexLocation[i], annexLocation[i] ));
        }
        return this.placeInforms;
    }

}
