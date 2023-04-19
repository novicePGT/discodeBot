package jj.ac.kr.discordbot.commands;

import jj.ac.kr.discordbot.commands.custom.CustomOptionData;
import jj.ac.kr.discordbot.commands.place.PlaceInform;
import jj.ac.kr.discordbot.commands.question.QuestionSet;
import jj.ac.kr.discordbot.connection.EmbedExamItem;
import jj.ac.kr.discordbot.connection.EmbedItem;
import jj.ac.kr.discordbot.connection.mariadbcon.DbData;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CommandInform extends ListenerAdapter {

    private Logger logger = LoggerFactory.getLogger(CommandInform.class);

    private String answer;

    @SneakyThrows
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        DbData dbData = new DbData();
        String command = event.getName();
        User user = event.getUser();

        if (command.equals("교수정보")) {
            OptionMapping messageOption = event.getOption("교수정보");
            String message = messageOption.getAsString();
            message = getProfessorName(message);

            event.reply("데이터가 서버로 전송되었습니다 !\n\n" + message).setEphemeral(true).queue();

            logger.info("{}님이 교수정보를 검색했습니다.", user);
        }

        if (command.equals("전공관위치정보")) {
            OptionMapping messageOption = event.getOption("전공관위치정보");

            String message = messageOption.getAsString();

            List<EmbedItem> findToDatabase = dbData.findToDb(message);
            String distanceToNew = findToDatabase.get(0).getDistanceToNew();
            String distanceToOld = findToDatabase.get(0).getDistanceToOld();

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("전공관위치정보");
            embed.setDescription("어디로 가야하는지 알려드릴게요.");
            embed.setColor(new Color(232, 109, 255));
            embed.setThumbnail("https://cdn.discordapp.com/attachments/1089918255134679101/1089918775874293880/2023-03-27_11.27.57.png");
            embed.addField("구정문으로부터",distanceToOld,true);
            embed.addField("신정문으로부터",distanceToNew, true);
            embed.setImage(findToDatabase.get(0).getUrl());
            event.getChannel().sendMessageEmbeds(embed.build()).queue();

            event.reply("위치정보를 찾았습니다 ! \n\n" + message + "의 정보를 임베드합니다.").setEphemeral(true).queue();

            logger.info("{}님이 전공관 위치정보를 검색했습니다.", user);
        }

        if (command.equals("별관위치정보")) {
            OptionMapping messageOption = event.getOption("별관위치정보");

            String message = messageOption.getAsString();

            List<EmbedItem> findToDatabase = dbData.findToDb(message);
            String distanceToNew = findToDatabase.get(0).getDistanceToNew();
            String distanceToOld = findToDatabase.get(0).getDistanceToOld();

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("별관위치정보");
            embed.setDescription("어디로 가야하는지 알려드릴게요.");
            embed.setColor(new Color(69, 225, 255));
            embed.setThumbnail("https://cdn.discordapp.com/attachments/1089918255134679101/1089918775874293880/2023-03-27_11.27.57.png");
            embed.addField("구정문으로부터",distanceToOld,true);
            embed.addField("신정문으로부터",distanceToNew, true);
            embed.setImage(findToDatabase.get(0).getUrl());
            event.getChannel().sendMessageEmbeds(embed.build()).queue();

            event.reply("위치정보를 찾았습니다 ! \n\n" + message + "의 정보를 임베드합니다.").setEphemeral(true).queue();

            logger.info("{}님이 별관 위치정보를 검색했습니다.", user);
        }

        if (command.equals("자바기본테스트")) {
            OptionMapping messageOption = event.getOption("자바기본테스트");

            String message = messageOption.getAsString();

            List<EmbedExamItem> findExamToDb = dbData.findExamToDb(message);

            answer = findExamToDb.get(0).getAnswer();

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("자바기본테스트");
            embed.setDescription("자바를 얼마나 배웠는지 테스트합니다.");
            embed.setColor(new Color(50, 255, 80));
            embed.setThumbnail("https://cdn.discordapp.com/attachments/1089918255134679101/1089918775874293880/2023-03-27_11.27.57.png");
            embed.addField("문제: ", findExamToDb.get(0).getQuestion(), true);
            embed.addField("보기 1. ", findExamToDb.get(0).getView1(), false);
            embed.addField("보기 2. ", findExamToDb.get(0).getView2(), false);
            embed.addField("보기 3. ", findExamToDb.get(0).getView3(), false);
            embed.addField("보기 4. ", findExamToDb.get(0).getView4(), false);
            Button button1 = Button.primary("view1", "보기 1번");
            Button button2 = Button.primary("view2", "보기 2번");
            Button button3 = Button.primary("view3", "보기 3번");
            Button button4 = Button.primary("view4", "보기 4번");

            MessageAction messageAction = event.getChannel().sendMessageEmbeds(embed.build()).setActionRow(button1, button2, button3, button4);
            messageAction.queue();

            event.reply("테스트 정보 확장중..." + message).setEphemeral(true).queue();

            logger.info("{}님이 자바 기본 테스트를 진행했습니다.", user);
        }
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getButton().getId().equals(answer)) {
            event.reply("정답입니다!").setEphemeral(true).queue();
        } else {
            event.reply("오답입니다!").setEphemeral(true).queue();
        }
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();

        // Command : /교수정보 <정보>
        OptionData professorInform = getProfessorInform();
        commandData.add(Commands.slash("교수정보", "학과 교수님의 정보를 검색합니다.").addOptions(professorInform));

        OptionData universityLocationInform = getUniversityLocationInform();
        commandData.add(Commands.slash("전공관위치정보", "학교 건물의 위치를 검색합니다.").addOptions(universityLocationInform));

        OptionData universityLocationInform2 = getUniversityLocationInform2();
        commandData.add(Commands.slash("별관위치정보", "학교 건물의 위치를 검색합니다.").addOptions(universityLocationInform2));

        OptionData javaBasicExamination = getJavaBasicExamination();
        commandData.add(Commands.slash("자바기본테스트", "자바를 얼마나 학습했는지 테스트해보세요.").addOptions(javaBasicExamination));

        event.getGuild().updateCommands().addCommands(commandData).queue();

    }

    private OptionData getUniversityLocationInform() {
        PlaceInform placeInform = new PlaceInform();
        OptionData universityLocationInform = new CustomOptionData(OptionType.STRING, "전공관위치정보", "어디로 가고싶나요?", true)
                .customAdd(placeInform.locationInform());

        return universityLocationInform;
    }

    private OptionData getUniversityLocationInform2() {
        PlaceInform placeInform = new PlaceInform();
        OptionData universityPlaceInform = new CustomOptionData(OptionType.STRING, "별관위치정보", "어디로 가고싶나요?", true)
                .customAdd(placeInform.locationInform2());

        return universityPlaceInform;
    }

    private OptionData getJavaBasicExamination() {
        QuestionSet questionSet = new QuestionSet();
        OptionData javaBasicExamination = new CustomOptionData(OptionType.STRING, "자바기본테스트", "자바에 대해 얼마나 학습했는지 테스트해보세요.", true)
                .examListAdd(questionSet.questionInform());

        return javaBasicExamination;
    }

    @NotNull
    private String getProfessorName(String message) {
        if (message.contains("이영재")) {
            message = "이영재 교수님 - [연구실]: 공학 1관 317호, [번호]: 063-220-2936";
        }
        if (message.contains("한동욱")) {
            message = "한동욱 교수님 - [연구실]: 공학 1관 417호, [번호]: 063-220-2229";
        }
        if (message.contains("강응관")) {
            message = "강응관 교수님 - [연구실]: 공학 1관 318호, [번호]: 063-220-2902";
        }
        if (message.contains("최은복")) {
            message = "최은복 교수님 - [연구실]: 공학 1관 319호, [번호]: 063-220-2937";
        }
        return message;
    }

    @NotNull
    private OptionData getProfessorInform() {
        OptionData professorInform = new OptionData(OptionType.STRING, "교수정보", "물어보고 싶은 정보가 있나요?", true)
                .addChoice("이영재 교수님", "이영재")
                .addChoice("한동욱 교수님", "한동욱")
                .addChoice("강응관 교수님", "강응관")
                .addChoice("최은복 교수님", "최은복");
        return professorInform;
    }
}
