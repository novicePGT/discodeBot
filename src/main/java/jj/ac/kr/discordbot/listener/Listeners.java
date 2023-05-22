package jj.ac.kr.discordbot.listener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

public class Listeners extends ListenerAdapter {

//    @Override
//    public void onReady(@NotNull ReadyEvent event) {
//        Guild guild = event.getJDA().getGuildById();
//        guild.upsertCommand("play", "").addOptions(new OptionData(OptionType.STRING, "url", "url", true)).queue();
//    }
}
