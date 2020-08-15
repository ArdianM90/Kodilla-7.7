package com.kodilla.rps;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EnumTools {
    Paper(0, 'p', new String[]{"Rock", "Spock"}, new String[]{"Scissors", "Lizard"}),
    Rock(1, 'k', new String[]{"Scissors", "Lizard"}, new String[]{"Paper", "Spock"}),
    Scissors(2, 'n', new String[]{"Paper", "Lizard"}, new String[]{"Rock", "Spock"}),
    Lizard(3, 'j', new String[]{"Spock", "Paper"}, new String[]{"Rock", "Scissors"}),
    Spock(4, 's', new String[]{"Scissors", "Rock"}, new String[]{"Paper", "Lizard"});

    final int toolIndex;
    final char toolAbbrev;
    final String[] beats;
    final String[] isBeatenBy;

    //konstruktor
    private EnumTools(int toolIndex, char toolAbbrev, String[] beats, String[] isBeatenBy) {
        this.toolIndex = toolIndex;
        this.toolAbbrev = toolAbbrev;
        this.beats = beats;
        this.isBeatenBy = isBeatenBy;
    }

    //gettery
    public static String getToolNameByAbbrev(char i) {
        return Arrays.stream(EnumTools.values())
                .filter(o -> o.toolAbbrev == i)
                .map(Enum::name)
                .collect(Collectors.toList())
                .get(0);
    }
    public static String getToolNameByIndex(int i) {
        return Arrays.stream(EnumTools.values())
                .filter(o -> o.toolIndex == i)
                .map(Enum::name)
                .collect(Collectors.toList())
                .get(0);
    }
    public static String[] getWhoToolBeats(String toolName) {
        return Arrays.stream(EnumTools.values())
                .filter(o -> o.name().equals(toolName))
                .map(o -> o.beats).collect(Collectors.toList())
                .get(0);
    }

    public static String[] getWhoBeatsTool(String toolName) {
        return Arrays.stream(EnumTools.values())
                .filter(o -> o.name().equals(toolName))
                .map(o -> o.isBeatenBy).collect(Collectors.toList())
                .get(0);
    }

    //stream
    public static Stream<EnumTools> stream() {
        return Stream.of(EnumTools.values());
    }
}