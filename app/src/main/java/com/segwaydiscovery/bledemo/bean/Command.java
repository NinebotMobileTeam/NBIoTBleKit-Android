package com.segwaydiscovery.bledemo.bean;

import java.io.Serializable;

/**
 * description Command
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/4/14 5:46 PM
 */
public class Command implements Serializable {

    private int command;

    private String desc;

    public Command(int command, String desc) {
        this.command = command;
        this.desc = desc;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(byte command) {
        this.command = command;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
