package com.test.pkt.cfg;

import java.util.List;

/*
* 属性+set/get
* */
public class CaseCfg implements IContainerCfg{
    private String value;
    private String desc;
    private List<IElementCfg> elements;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public List<IElementCfg> getElements() {
        return this.elements;
    }

    @Override
    public void setElements(List<IElementCfg> list) {
        this.elements = list;
    }
}
