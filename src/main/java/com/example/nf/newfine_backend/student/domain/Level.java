package com.example.nf.newfine_backend.student.domain;

import lombok.Builder;

import java.util.Objects;

public enum Level {
    DIA(100, null),
    PLATINUM(70, DIA),
    GOLD(50, PLATINUM),
    SILVER(30, GOLD),
    BRONZE(15, SILVER),
    NEW(1, BRONZE);

    private final int nextAmount;
    private final Level nextLevel;

    Level(int nextAmount, Level nextLevel) {
        this.nextAmount = nextAmount;
        this.nextLevel = nextLevel;
    }

    public static boolean availabelLevelUp(Level level, int totalAmount) {
        if(Objects.isNull(level)) {
            return false;
        }

        if(Objects.isNull(level.nextLevel)) {
            return false;
        }

        return totalAmount >= level.nextAmount;
    }

    static Level getNextLevel(int point) {
        // PLATINUM->DIA
        if(point >= Level.PLATINUM.nextAmount) {
            return PLATINUM.nextLevel;
        }

        // GOLD->PLATINUM
        if(point >= Level.GOLD.nextAmount) {
            return GOLD.nextLevel;
        }

        // SILVER->GOLD
        if(point >= Level.SILVER.nextAmount) {
            return SILVER.nextLevel;
        }

        // BRONZE->SILVER
        if(point >= Level.BRONZE.nextAmount) {
            return BRONZE.nextLevel;
        }

        // NEW->BRONZE
        if(point >= Level.NEW.nextAmount) {
            return NEW.nextLevel;
        }

        return NEW;
    }
}
