package jj.ac.kr.discordbot.listener;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventListener extends ListenerAdapter {

    StringConversion stringConversion = new StringConversion();

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        String user = String.valueOf(event.getUser());
        String reaction = event.getReaction().getReactionEmote().getAsReactionCode();
        String channelMention = event.getChannel().getAsMention();

        String onlyUserId = stringConversion.splitUserName(user);

        String message = onlyUserId + " 님이  " + channelMention + " 에서  " + reaction + " 으로 반응했습니다 !!";
        event.getGuild().getDefaultChannel().sendMessage(message).queue();
    }

    /**
     * 메시지가 전송되면 이벤트가 발생한다.
     * 2022년 8월 이후 "길드 메시지" 게이트웨이 의도가 필요함.
     * @param event
     */
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.contains("1학년")) {
            event.getChannel().sendMessage("[１학년] : 23학번입니다. 복학생은 아직 없겠죠? [담당교수: 이영재]").queue();
        }
        if (message.contains("2학년")) {
            event.getChannel().sendMessage("[２학년] : 22학번입니다. 복학생은 20학번이겠네요. [담당교수: 한동욱]").queue();
        }
        if (message.contains("3학년")) {
            event.getChannel().sendMessage("[３학년] : 21학번입니다. 복학생은 19학번이겠네요. [담당교수: 강응관]").queue();
        }
        if (message.contains("4학년")) {
            event.getChannel().sendMessage("[４학년] : 20학번입니다. 복학생은 18학번이겠네요. [담당교수: 최은복]").queue();
        }
    }
}
