package jj.ac.kr.discordbot.music;

import jj.ac.kr.discordbot.commands.ICommand;
import jj.ac.kr.discordbot.connection.mariadbcon.DbData;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Play implements ICommand {

    private Logger logger = LoggerFactory.getLogger(Play.class);

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getDescription() {
        return "구성원과 같이 노래를 들어보세요.";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "url", "Input your url or text", true));
        return options;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        GuildVoiceState voiceState = member.getVoiceState();

        if (!voiceState.inAudioChannel()) {
            event.reply("음악을 재생하기 전에 방에 먼저 들어가주세요.").setEphemeral(true).queue();
            return;
        }

        Member self = event.getGuild().getSelfMember();
        GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inAudioChannel()) {
            //join
            event.getGuild().getAudioManager().openAudioConnection(voiceState.getChannel());
        } else {
            if (selfVoiceState.getChannel() != voiceState.getChannel()) {
                event.reply("같은 채널 안에 있어야합니다.").setEphemeral(true).queue();
                return;
            }
        }

        String name = event.getOption("url").getAsString();
        try {
            new URL(name);
        } catch (MalformedURLException e) {
            name = "ytsearch:" + name;
        }

        PlayerManager playerManager = PlayerManager.get();
        event.reply("Playing... : [DB] 사용자의 검색 정보를 기록했습니다 !").queue();
        playerManager.play(event.getGuild(), name);

        DbData dbData = new DbData();
        try {
            dbData.saveToDb(name, 0);
        } catch (SQLException e) {
            logger.info("[DB 저장 실패]: {}", e.getMessage());
        }

        logger.info("{}님이 Play 기능을 사용했습니다.", member + name);
    }
}
