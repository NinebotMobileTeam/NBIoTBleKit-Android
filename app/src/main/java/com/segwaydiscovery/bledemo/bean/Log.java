package com.segwaydiscovery.bledemo.bean;

import java.util.List;

/**
 * description
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/5/25 10:42 AM
 */
public class Log extends com.sd.blecontrol.bean.Log {

    private List<CommandParse> commandParseList;

    public Log(int type, String content) {
        super(type, content);
    }

    public Log(int type, List<CommandParse> commandParseList) {
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
