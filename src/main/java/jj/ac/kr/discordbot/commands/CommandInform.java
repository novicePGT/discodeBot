package jj.ac.kr.discordbot.commands;

import jj.ac.kr.discordbot.commands.custom.CustomOptionData;
import jj.ac.kr.discordbot.commands.place.PlaceInform;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CommandInform extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();

        if (command.equals("교수정보")) {
            OptionMapping messageOption = event.getOption("교수정보");
            String message = messageOption.getAsString();
            message = getProfessorName(message);

            event.reply("데이터가 서버로 전송되었습니다 !\n\n" + message).setEphemeral(true).queue();
        }

        if (command.equals("전공관위치정보")) {
            OptionMapping messageOption = event.getOption("전공관위치정보");

            String message = messageOption.getAsString();

            EmbedBuilder embed = new EmbedBuilder();

            embed.setTitle("전공관위치정보");
            embed.setDescription("어디로 가야하는지 알려드릴게요.");
            embed.setColor(new Color(232, 109, 255));

            embed.setThumbnail("https://cdn.discordapp.com/attachments/1089918255134679101/1089918775874293880/2023-03-27_11.27.57.png");
            embed.setImage("https://cdn.discordapp.com/attachments/1089918255134679101/1089919277907312783/2023-03-27_11.29.56.png");
            event.getChannel().sendMessageEmbeds(embed.build()).queue();

            event.reply("위치정보를 찾았습니다 ! \n\n" + message + "의 정보를 임베드 합니다.").setEphemeral(true).queue();
        }

        if (command.equals("별관위치정보")) {
            OptionMapping messageOption = event.getOption("별관위치정보");

            String message = messageOption.getAsString();
            message = "별관 위치정보 메세지 설정 확인";

            event.reply("위치정보를 찾았습니다 ! \n\n" + message).setEphemeral(true).queue();
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
