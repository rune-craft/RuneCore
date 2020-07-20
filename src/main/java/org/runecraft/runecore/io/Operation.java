package org.runecraft.runecore.io;

import java.util.Arrays;

public enum Operation {
    LINK_DISCORD_REQUEST(1), LINK_DISCORD_CONFIRM(2), LINK_DISCORD_CANCEL(3);

    private int id;

    private Operation(int id){
        this.id = id;
    }

    public static Operation by(int id){
        return Arrays.asList(values()).stream().filter(x -> x.getId() == id).findFirst().get();
    }

    public int getId() {
        return id;
    }
}
