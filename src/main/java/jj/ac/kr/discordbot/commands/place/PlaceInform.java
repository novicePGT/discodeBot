package jj.ac.kr.discordbot.commands.place;


import jj.ac.kr.discordbot.commands.custom.OptionItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaceInform {

    List<OptionItem> placeInforms = new ArrayList<>();

    private String[] location = {"EM생활문화관","EM연구동","골프연습장","공예관","공학1관","공학2관","공학실습동"};
    //"교수연구동","국제교육관","군사교육관","노천극장","대학교회","대학본관","미생물관","믿음관","벤처창업관","변전소","순영관","소리연","스타누리","스타드림관","스타빌","스타센터","스타짐","스타타워","스타홈","예술관","예술관 별관","자유관","신정문","구정문","지역혁신관","진리관","창고동","창조관","천잠관","천잠연","체육관","평화관","학생회관","한지산업관","희망홀"

    public List<OptionItem> locationInform() {
        return setList();
    }


    private List<OptionItem> setList() {
        for(int i = 0 ; i < location.length ; i++){
            placeInforms.add(new OptionItem(location[i] , location[i]));
        }
        return this.placeInforms;
    }
}
