package com.example.myapplication;

import com.example.myapplication.ui.levels.Levels;

public class Word {
    int level;
    int part;

    String ii;
    String ru;

    int image;


    public Word(Levels.LevelId level, Parts.PartId part, String ii, String ru, int image) {
        this.level = Levels.levelId[level.ordinal()];
        this.part = Parts.partId[part.ordinal()];
        this.ii = ii;
        this.ru = ru;
        this.image = image;
    }
}
