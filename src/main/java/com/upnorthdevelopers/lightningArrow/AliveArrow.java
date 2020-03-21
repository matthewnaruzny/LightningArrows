package com.upnorthdevelopers.lightningArrow;

import java.util.UUID;

public class AliveArrow {

    private UUID arrowUUID;
    private BowType arrowAction;

    public AliveArrow(UUID arrowUUID, BowType arrowAction) {
        this.arrowUUID = arrowUUID;
        this.arrowAction = arrowAction;
    }

    public UUID getArrowUUID() {
        return arrowUUID;
    }

    public BowType getArrowAction() {
        return arrowAction;
    }

}
