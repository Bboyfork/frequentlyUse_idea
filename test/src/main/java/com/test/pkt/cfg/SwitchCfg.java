package com.test.pkt.cfg;

import java.util.List;

public class SwitchCfg implements IElementCfg {
    private String desc;
    private String condition;
    private List<CaseCfg> cases;
    private DefaultCfg def;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<CaseCfg> getCases() {
        return cases;
    }

    public void setCases(List<CaseCfg> cases) {
        this.cases = cases;
    }

    public DefaultCfg getDef() {
        return def;
    }

    public void setDef(DefaultCfg def) {
        this.def = def;
    }
}
