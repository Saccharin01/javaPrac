package LoAExchange.Modules;

import javax.annotation.Nullable;

public enum EnumItemGrade {
    RARE("희귀" , "rare"),
    HEROIC("영웅", "hero"),
    LEGENDARY("전설", "legend"),
    RELIC("유물" , "relic"),
    ANCIENT("고대", "ancient");

    private final String grade;
    private final String alies;

    EnumItemGrade(String grade, String alies) {
        this.grade = grade;
        this.alies = alies;

    }

    public String getGrade() {
       return this.grade;
    }

    public String getAlies() {
        return this.alies;
    }

    @Nullable
    public static String CheckInputToGrade (String input) {
        for (EnumItemGrade item : EnumItemGrade.values()){
            if(item.name().equalsIgnoreCase(input)
            || item.grade.equalsIgnoreCase(input)
            || item.alies.equalsIgnoreCase(input)){
              return item.getGrade();
            }

        }
        return EnumItemGrade.RARE.getGrade();
    }
}