package com.publicWifiSearch.domain.repostitory.common.columnConstant;

public enum Column {

    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5),
    SIXTH(6),
    SEVENTH(7),
    EIGHTH(8),
    NINTH(9),
    TENTH(10),
    ELEVENTH(11),
    TWELFTH(12),
    THIRTEENTH(13),
    FOURTEENTH(14),
    FIFTEENTH(15),
    SIXTEENTH(16);

    private final int position;

    Column(int position){
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
