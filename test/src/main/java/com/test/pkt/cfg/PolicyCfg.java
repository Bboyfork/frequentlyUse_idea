package com.test.pkt.cfg;

import java.util.List;

public class PolicyCfg extends AttributeableCfg implements IContainerCfg{
    private  String desc;
    private String type;
    private List<IElementCfg> elements;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public List<IElementCfg> getElements() {
        return elements;
    }

    @Override
    public void setElements(List<IElementCfg> elements) {
        this.elements = elements;
    }
}
