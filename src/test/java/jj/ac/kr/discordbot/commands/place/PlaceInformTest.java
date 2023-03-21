package jj.ac.kr.discordbot.commands.place;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlaceInformTest {

    PlaceInform placeInform = new PlaceInform();

    @Test
    @DisplayName("stream test")
    void streamTest() {
        //given
        List<String> placeInforms = new ArrayList<>();

        //when
        placeInforms = (List<String>) placeInform.locationInform().stream()
                .collect(Collectors.toList());

        //then
        System.out.println(placeInforms);
    }
}