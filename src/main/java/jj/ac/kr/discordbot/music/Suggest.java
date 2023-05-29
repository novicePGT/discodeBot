package jj.ac.kr.discordbot.music;

import jj.ac.kr.discordbot.commands.ICommand;
import jj.ac.kr.discordbot.connection.MusicData;
import jj.ac.kr.discordbot.connection.mariadbcon.DbData;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class Suggest implements ICommand {

    private Logger logger = LoggerFactory.getLogger(Suggest.class);

    @Override
    public String getName() {
        return "suggest";
    }

    @Override
    public String getDescription() {
        return "음악을 추천해드릴게요.";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
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

        GuildMusicManager guildMusicManager = PlayerManager.get().getGuildMusicManager(event.getGuild());
        DbData dbData = new DbData();
        List<MusicData> musicDataList;
        try {
            musicDataList = dbData.findMusicToDb();
            Collections.sort(musicDataList, (a, b) -> Integer.compare(b.getNum(), a.getNum()));
            List<MusicData> items = musicDataList.subList(0, Math.min(musicDataList.size(), 5));

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("추천 목록");
            embedBuilder.setDescription("음악을 추천해서 보여드릴게요.");
            embedBuilder.setColor(new Color(47, 221, 255));
            for (int i = 0; i < items.size(); i++) {
                embedBuilder.addField(i+1 + ". ", items.get(i).getName(), false);
            }

            event.replyEmbeds(embedBuilder.build()).queue();
        } catch (SQLException e) {
            logger.info("[MusicList]: error -> {}", e.getMessage() );
        }
    }
}
