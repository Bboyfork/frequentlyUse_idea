package com.test.pkt.cfg;

public class IncludeCfg implements IElementCfg {
    private String file;
    private String desc;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
