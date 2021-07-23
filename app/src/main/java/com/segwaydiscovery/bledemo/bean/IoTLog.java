package com.segwaydiscovery.bledemo.bean;

import com.segwaydiscovery.nbiot.bean.BleLog;

import java.util.List;

/**
 * description
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/5/25 10:42 AM
 */
public class IoTLog extends BleLog {

    private List<CommandParse> commandParseList;

    public IoTLog(int type, String content) {
        super(type, content);
    }

    public IoTLog(int type, List<CommandParse> commandParseList) {
        super(type);
        this.commandParseList = commandParseList;
    }

    public List<CommandParse> getCommandParseList() {
        return commandParseList;
    }

    public void setCommandParseList(List<CommandParse> commandParseList) {
        this.commandParseList = commandParseList;
    }
}
