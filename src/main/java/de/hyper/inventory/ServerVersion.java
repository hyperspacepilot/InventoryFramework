package de.hyper.inventory;

import lombok.Getter;

/**
 * @author hyperspace_pilot
 */
@Getter
public enum ServerVersion {

    V1_8(8), V1_12(12), V1_18(18), V1_19(19);

    private int priority;

    private ServerVersion(int priority) {
        this.priority = priority;
    }

    public boolean isHigherThen(ServerVersion serverVersion) {
        return (this.priority > serverVersion.getPriority());
    }

    public boolean isLowerThen(ServerVersion serverVersion) {
        return (this.priority < serverVersion.getPriority());
    }

    public boolean is(ServerVersion serverVersion) {
        return (this.priority == serverVersion.getPriority());
    }
}