package com.segwaydiscovery.bledemo.enumation;

/**
 * description
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/4/14 5:05 PM
 */
public enum HelmetCommandEnum {

    FUNCTION_UNLOCK(-1, "Unlock"),
    FUNCTION_HELMET_LOCK_INFO(-2, "Query helmet Information"),
    FUNCTION_VEHICLE_LOCK_STATUS(-3, "Query helmet status"),
    FUNCTION_LOCK(-4, "lock");

    private int command;

    private String desc;

    HelmetCommandEnum(int command, String desc) {
        this.command = command;
        this.desc = desc;
    }

    public static HelmetCommandEnum getEnum(byte command) {
        for (HelmetCommandEnum commandEnum : HelmetCommandEnum.values()) {
            if (command == commandEnum.command) {
                return commandEnum;
            }
        }
        return FUNCTION_UNLOCK;
    }

    public int getCommand() {
        return command;
    }

    public String getDesc() {
        return desc;
    }
}
