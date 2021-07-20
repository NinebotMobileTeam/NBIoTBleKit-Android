package com.segwaydiscovery.bledemo.bean;

import java.io.Serializable;

/**
 * description
 *
 * @author yaxin
 * @version 2.0
 * @since 2021/5/25 10:10 AM
 */
public class CommandParse implements Serializable {

    private String index;

    private String Parameter;

    private String Description;

    private String value;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getParameter() {
        return Parameter;
    }

    public void setParameter(String parameter) {
        Parameter = parameter;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
