package sk.exceptional.spongia14.api;

import sk.exceptional.spongia14.pnc.ClickableRegion;

public abstract class ClickAction {
    public abstract void execute(Mission mission, ClickableRegion clicked);
}
