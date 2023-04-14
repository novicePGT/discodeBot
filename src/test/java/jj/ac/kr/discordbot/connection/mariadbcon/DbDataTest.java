package jj.ac.kr.discordbot.connection.mariadbcon;

import jj.ac.kr.discordbot.connection.EmbedItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DbDataTest {

    @Test
    @DisplayName("DB 테스트")
    void testDatabase() throws SQLException {
        // given
        DbData dbData = new DbData();

        //when
        String data = "공학1관";
        List<EmbedItem> toDb = dbData.findToDb(data);
        String url = toDb.get(0).getUrl();

        //then
        Assertions.assertThat(url)
                .isEqualTo("https://cdn.discordapp.com/attachments/1089918255134679101/1090969930758889562/2023-03-30_9.04.51.png");
    }

}