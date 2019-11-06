package com.test.pkt.cfg;

public class FieldCfg extends AttributeableCfg implements IElementCfg {
    private String name;
    private String javaType;
    private String pktType;
    private String desc;
    private String value;
    private String defVal;
    private PolicyCfg policy;
    private IncludeCfg include;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getPktType() {
        return pktType;
    }

    public void setPktType(String pktType) {
        this.pktType = pktType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDefVal() {
        return defVal;
    }

    public void setDefVal(String defVal) {
        this.defVal = defVal;
    }

    public PolicyCfg getPolicy() {
        return policy;
    }

    public void setPolicy(PolicyCfg policy) {
        this.policy = policy;
    }

    public IncludeCfg getInclude() {
        return include;
    }

    public void setInclude(IncludeCfg include) {
        this.include = include;
    }
}
