package sk.exceptional.spongia14.api.actions;

import sk.exceptional.spongia14.api.Mission;
import sk.exceptional.spongia14.api.MissionState;


public abstract class Action {
    public abstract void execute(Mission mission, MissionState state);
}
