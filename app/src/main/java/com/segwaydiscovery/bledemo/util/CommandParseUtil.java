package com.segwaydiscovery.bledemo.util;

import com.segwaydiscovery.bledemo.bean.CommandParse;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/5/25 11:43 AM
 */
public class CommandParseUtil {

    public static List<CommandParse> creatCommandParseList(byte[] hex) {
        List<CommandParse> commandParseList = new ArrayList<>();
        CommandParse commandParse0 = new CommandParse();
        commandParse0.setIndex("0");
        commandParse0.setParameter("STX");
        commandParse0.setDescription("Data head/frame head, fixed value: 0xA3");
        commandParse0.setValue(byteToHex(hex[0]));
        commandParseList.add(commandParse0);

        CommandParse commandParse1 = new CommandParse();
        commandParse1.setIndex("1");
        commandParse1.setParameter("STX");
        commandParse1.setDescription("Data head/frame head, fixed value: 0xA4");
        commandParse1.setValue(byteToHex(hex[1]));
        commandParseList.add(commandParse1);

        CommandParse commandParse2 = new CommandParse();
        commandParse2.setIndex("2");
        commandParse2.setParameter("LEN");
        commandParse2.setDescription("Data length");
        commandParse2.setValue(byteToHex(hex[2]));
        commandParseList.add(commandParse2);

        CommandParse commandParse3 = new CommandParse();
        commandParse3.setIndex("3");
        commandParse3.setParameter("RAND");
        commandParse3.setDescription("Random number");
        commandParse3.setValue(byteToHex(hex[3]));
        commandParseList.add(commandParse3);

        CommandParse commandParse4 = new CommandParse();
        commandParse4.setIndex("4");
        commandParse4.setParameter("KEY");
        commandParse4.setDescription("Communication KEY");
        commandParse4.setValue(byteToHex(hex[4]));
        commandParseList.add(commandParse4);

        CommandParse commandParse5 = new CommandParse();
        commandParse5.setIndex("5");
        commandParse5.setParameter("CMD");
        commandParse5.setDescription("Command");
        commandParse5.setValue(byteToHex(hex[5]));
        commandParseList.add(commandParse5);

        return commandParseList;

    }

    public static List<CommandParse> addCRC(List<CommandParse> commandParseList, byte[] hex) {
        CommandParse commandParseCRC = new CommandParse();
        commandParseCRC.setIndex(String.valueOf(hex.length - 1));
        commandParseCRC.setParameter("CRC");
        commandParseCRC.setDescription("CRC-8 check value");
        commandParseCRC.setValue(byteToHex(hex[hex.length - 1]));
        commandParseList.add(commandParseCRC);
        return commandParseList;
    }

    public static String byteToHex(byte b) {
        int i = b & 0xFF;
        return Integer.toHexString(i).toUpperCase();
    }
}
