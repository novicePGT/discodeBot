package jj.ac.kr.discordbot.commands.custom;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class CustomOptionData extends OptionData {

    public CustomOptionData(@NotNull OptionType type, @NotNull String name, @NotNull String description, boolean isRequired) {
        super(type, name, description, isRequired);
    }

    public OptionData customAdd(@NotNull  List<OptionItem> optionList) {
        for (int i = 0 ; i < optionList.size() ; i++ ) {
            super.addChoice(optionList.get(i).getName() , optionList.get(i).getValue());
        }
        return this;
    }

    public OptionData examListAdd(@NotNull List<ExamItem> examItemList) {
        for (int i=0; i<examItemList.size(); i++) {
            super.addChoice(examItemList.get(i).getName(), examItemList.get(i).getValue());
        }
        return this;
    }

}
