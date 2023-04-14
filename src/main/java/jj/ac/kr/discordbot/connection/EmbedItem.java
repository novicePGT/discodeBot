package jj.ac.kr.discordbot.connection;

import lombok.Data;

@Data
public class EmbedItem {

    private int seq;
    private String urlName;
    private String url;
    private String distanceToNew;
    private String distanceToOld;

    /**
     * 매개변수 없는 빈 생성자를 생성한 이유는 다음과 같다.
     * Java Bean 규약을 따르기 위함.
     * 객체 직렬화를 위해서는 매개변수 없는 생성자가 필요하다
     * -> 직렬화 이유: 객체를 영구적으로 저장하고 복원 가능 + 캐시에 저장 가능 + 네트워크로 전송 가능 + 분산 환경에서 객체 공유 가능
     */

    public EmbedItem() {

    }

    public EmbedItem(String urlName, String url, String distanceToNew, String distanceToOld) {
        this.urlName = urlName;
        this.url = url;
        this.distanceToNew = distanceToNew;
        this.distanceToOld = distanceToOld;
    }
}
