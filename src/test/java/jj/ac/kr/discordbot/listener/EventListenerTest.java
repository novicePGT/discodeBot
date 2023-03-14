package jj.ac.kr.discordbot.listener;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EventListenerTest {

    StringConversion stringConversion = new StringConversion();

    @Test
    @DisplayName("subString Test")
    void subStringTest() {
        //given
        String userId = "U:짱구는 윗머리 3센치지만 사실 66미리일지도?(1122113)";

        //when
        String onlyUserId = stringConversion.splitUserName(userId);

        //then
        assertThat(onlyUserId).isEqualTo(":짱구는 윗머리 3센치지만 사실 66미리일지도?");
    }
}