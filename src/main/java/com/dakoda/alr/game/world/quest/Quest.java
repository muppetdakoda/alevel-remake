package com.dakoda.alr.game.world.quest;

import com.dakoda.alr.game.registrar.GameObject;
import com.dakoda.alr.game.registrar.Prerequisite;
import com.dakoda.alr.game.world.entity.Entity;

import java.util.ArrayList;
import java.util.Arrays;

public class Quest implements GameObject {

    private String description = "";
    private ArrayList<QuestObjective> criteria = new ArrayList<>();
    private ArrayList<Prerequisite> prerequisites = new ArrayList<>();

    private Boolean canTurnIn;
    private Boolean isDone;

    private Integer experienceReward;
    private Integer currencyReward;

    private Entity givenBy;

    public void turnIn() {
        finish();
    }

    public GameObject.Type objectType() {
        return GameObject.Type.QUEST;
    }

    public Quest withObjective(QuestObjective requirement) {
        criteria.add(requirement);
        return this;
    }

    public Quest withDescription(String desc) {
        this.description = desc;
        return this;
    }

    public Quest requiresThat(Prerequisite... prerequisite) {
        prerequisites.addAll(Arrays.asList(prerequisite));
        return this;
    }

    public Quest givenByNPC(Entity npc) {
        this.givenBy = npc;
        return this;
    }

    public Quest withExperienceReward(Integer exp) {
        this.experienceReward = exp;
        return this;
    }

    public Quest withCurrencyReward(Integer currency) {
        this.currencyReward = currency;
        return this;
    }

    public ArrayList<QuestObjective> criteria() {
        return criteria;
    }

    public String getDescription() {
        return description;
    }

    public Entity getNPCWhoGives() {
        return givenBy;
    }

    public Integer getExperienceReward() {
        return experienceReward;
    }

    public Integer getCurrencyReward() {
        return currencyReward;
    }

    public ArrayList<Prerequisite> getPrerequisites() {
        return prerequisites;
    }

    public void finish() {
        this.isDone = Boolean.TRUE;
    }

    public Boolean isFinished() {
        return isDone;
    }

    public Boolean canTurnIn() {
        return canTurnIn;
    }

    public void validateFinishability() {
        Boolean canFinish = Boolean.TRUE;
        for (QuestObjective criterion : criteria) {
            if (!criterion.checkFinished()) {
                canFinish = Boolean.FALSE;
                break;
            }
        }
        if (canFinish) canTurnIn = Boolean.TRUE;
    }

    private boolean hasFulfilledPrerequisites() {
        boolean doneAll = true;
        for (Prerequisite prerequisite : prerequisites) {
            if (!prerequisite.requirement()) doneAll = false;
        }
        return doneAll;
    }

    public boolean meetsQuestPrerequisites() {
        return hasFulfilledPrerequisites();
    }

    public String name() {
        return this.toString();
    }
}