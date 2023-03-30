package jj.ac.kr.discordbot.connection.mariadbcon;

import java.sql.*;

public class MariaDbCon {

    private Connection con;
    private Statement statement;
    private ResultSet resultSet;

    public void dbCon() throws SQLException {
        try {
            // Mariadb 드라이버 로드
            Class.forName("org.mariadb.jdbc.Driver");
            // DB 접속 객체 선언
            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/discordBot_db", "root", "1208");
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM url_table");

            // 데이터를 확인하기 위함
            while (resultSet.next()) {
                System.out.println(resultSet.getString("urlName") + resultSet.getString("url"));
            }

            System.out.println("[database]: url_table 정보 로딩 완료");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        } finally {
            if (con != null) { con.close(); }
            if (statement != null) { statement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }
    }
}
