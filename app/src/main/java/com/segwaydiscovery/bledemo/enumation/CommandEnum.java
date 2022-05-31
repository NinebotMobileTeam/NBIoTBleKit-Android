package com.segwaydiscovery.bledemo.enumation;

/**
 * description
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/4/14 5:05 PM
 */
public enum CommandEnum {

    FUNCTION_UNLOCK(1, "Unlock"),
    FUNCTION_LOCK(2, "Lock"),
    FUNCTION_IOT_INFO(3, "Query IoT Information"),
    FUNCTION_VEHICLE_INFO(4, "Query Vehicle Information"),
    FUNCTION_OPEN_BATTERY_COVER(5, "Unlock Battery Cover"),
    FUNCTION_OPEN_SADDLE(6, "Unlock sit barrels"),
    FUNCTION_OPEN_TAIL_BOX(7, "Unlock tail box"),
    FUNCTION_UNLOCK_HELMET(8, "Unlock helmet"),
    FUNCTION_LOCK_HELMET(9, "Lock helmet");


    private int command;

    private String desc;

    CommandEnum(int command, String desc) {
        this.command = command;
        this.desc = desc;
    }

    public static CommandEnum getEnum(byte command) {
        for (CommandEnum commandEnum : CommandEnum.values()) {
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
