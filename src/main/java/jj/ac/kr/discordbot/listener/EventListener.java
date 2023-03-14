package jj.ac.kr.discordbot.listener;

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
}
