package jj.ac.kr.discordbot.music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import jj.ac.kr.discordbot.commands.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class NowPlaying implements ICommand {
    @Override
    public String getName() {
        return "playing";
    }

    @Override
    public String getDescription() {
        return "현재 재생되는 노래를 보여드릴게요.";
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
            event.reply("음악을 스킵하기 전에 방에 먼저 들어가주세요.").setEphemeral(true).queue();
            return;
        }

        Member self = event.getGuild().getSelfMember();
        GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inAudioChannel()) {
            event.reply("같은 채널 안에 있어야합니다.").setEphemeral(true).queue();
            return;
        }

        if (selfVoiceState.getChannel() != voiceState.getChannel()) {
            event.reply("같은 채널 안에 있지 않습니다").setEphemeral(true).queue();
            return;
        }

        GuildMusicManager guildMusicManager = PlayerManager.get().getGuildMusicManager(event.getGuild());
        if (guildMusicManager.getTrackScheduler().getPlayer().getPlayingTrack() == null) {
            event.reply("현재 아무것도 재생중이지 않습니다.").setEphemeral(true).queue();
            return;
        }
        AudioTrackInfo info = guildMusicManager.getTrackScheduler().getPlayer().getPlayingTrack().getInfo();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("현재 재생중...");
        embedBuilder.setDescription("재생중인 음악 정보: `" + info.title + "`");
        embedBuilder.appendDescription("\n 제공자: `" + info.author + "`");
        embedBuilder.appendDescription("\n 링크: `" + info.uri + "`");
        event.replyEmbeds(embedBuilder.build()).queue();
    }
}
