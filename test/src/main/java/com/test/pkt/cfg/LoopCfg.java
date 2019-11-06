package com.test.pkt.cfg;

import java.util.List;

public class LoopCfg extends AttributeableCfg implements IContainerCfg {
    public static final String INDEX_VAR_NAME = "_idx_";
    private String name;
    private String desc;
    private String indexVar;
    private String count;
    private String len;
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

    public String getIndexVar() {
        return indexVar;
    }

    public void setIndexVar(String indexVar) {
        this.indexVar = indexVar;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len;
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
