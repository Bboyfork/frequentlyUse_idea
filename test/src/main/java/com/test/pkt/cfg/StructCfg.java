package com.test.pkt.cfg;

import java.util.List;

public class StructCfg extends AttributeableCfg implements IContainerCfg {
    private String name;
    private String desc;
    private List<IElementCfg> elements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
