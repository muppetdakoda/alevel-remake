package com.dakoda.alr.game;
import com.dakoda.alr.game.quest.Quest;
import com.dakoda.alr.item.Items;

public class TextRPG {

    //stop committing things irrelevant to the branch you're better than this

    public static void main(String[] args) {
        Quest quest = new Quest.QuestBuilder()
                .addItemRequirement(Items.QuestItems.APPLE.get(), 10)
                .build();
    }
}