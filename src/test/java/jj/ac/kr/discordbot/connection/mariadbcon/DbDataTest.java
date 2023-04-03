package jj.ac.kr.discordbot.connection.mariadbcon;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DbDataTest {

    @Test
    @DisplayName("DB 테스트")
    void testDatabase() throws SQLException {
        // given
        DbData dbData = new DbData();

        //when
        String data = "공학1관";
        String value = dbData.findToDb(data);

        //then
        Assertions.assertThat(value)
                .isEqualTo("https://cdn.discordapp.com/attachments/1089918255134679101/1090969930758889562/2023-03-30_9.04.51.png");
    }

}