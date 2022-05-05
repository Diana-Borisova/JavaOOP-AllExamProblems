package viceCity.models.players;

import viceCity.common.ExceptionMessages;

public class CivilPlayer extends BasePlayer{
    private static final int INITIAL_LIFE_POINTS = 50;

    public CivilPlayer(String name) {
        super(name, INITIAL_LIFE_POINTS);
    }



}
