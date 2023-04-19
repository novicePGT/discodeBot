package jj.ac.kr.discordbot.connection.mariadbcon;

import jj.ac.kr.discordbot.commands.custom.ExamItem;
import jj.ac.kr.discordbot.connection.EmbedExamItem;
import jj.ac.kr.discordbot.connection.EmbedItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbData {

    private final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private final String DB_URL = "jdbc:mariadb://localhost:3306/discordBot_db";
    private final String USER = "root";
    private final String PASS = "1208";
    private Connection connection = null;
    private Statement statement = null;

    public List<EmbedItem> findToDb(String data) throws SQLException {
        List<EmbedItem> embedItems = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM url_table");

            while (resultSet.next()) {
                String valueColum = resultSet.getString("urlName");
                if (valueColum.equals(data)) {
                    String valueUrl = resultSet.getString("url");
                    String valueDistanceToNew = resultSet.getString("distanceToNew");
                    String valueDistanceToOld = resultSet.getString("distanceToOld");

                    EmbedItem embedItem = new EmbedItem(valueColum, valueUrl, valueDistanceToNew, valueDistanceToOld);
                    embedItems.add(embedItem);
                }
            }
            System.out.println("[DB]: 정보 조회 완료 ");
        } catch (ClassNotFoundException e) {
            throw new SQLException();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return embedItems;
    }

    public List<EmbedExamItem> findExamToDb(String data) throws SQLException {
        List<EmbedExamItem> embedExamItems = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM java_exam_table");

            while (resultSet.next()) {
                String valueColum = resultSet.getString("message");
                if (valueColum.equals(data)) {
                    String view1 = resultSet.getString("view1");
                    String view2 = resultSet.getString("view2");
                    String view3 = resultSet.getString("view3");
                    String view4 = resultSet.getString("view4");
                    String question = resultSet.getString("question");
                    String answer = resultSet.getString("answer");

                    EmbedExamItem examItem = new EmbedExamItem(view1, view2, view3, view4, question, answer);
                    embedExamItems.add(examItem);
                }
            }

        } catch (ClassNotFoundException e) {
            throw new SQLException();
        }
        return embedExamItems;
    }
}
