package jj.ac.kr.discordbot.commands;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandProfessorInform extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();

        if (command.equals("교수정보")) {
            OptionMapping messageOption = event.getOption("교수정보");
            String message = messageOption.getAsString();
            message = getProfessorName(message);

            event.reply("데이터가 서버로 전송되었습니다 !\n\n" + message).setEphemeral(true).queue();
        }
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();

        // Command : /교수정보 <정보>
        OptionData professorInform = getProfessorInform();
        commandData.add(Commands.slash("교수정보", "학과 교수님의 정보를 검색합니다.").addOptions(professorInform));

        event.getGuild().updateCommands().addCommands(commandData).queue();

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
