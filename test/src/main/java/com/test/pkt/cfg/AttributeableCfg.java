package com.test.pkt.cfg;

import java.util.HashMap;
import java.util.Map;

/*
* attributes
* validated
* 的set、get
* */
public abstract class AttributeableCfg {
    private Map<String, String> attributes;     //属性
    private boolean validated;      //验证过的

    //(初始化)attributes
    public Map<String, String> getAttributes() {
        if(this.attributes == null){
            this.attributes = new HashMap();
        }
        return this.attributes;
    }

    //attributes set
    public void set(String key,String value) {
        getAttributes().put(key,value);
    }

    //attributes get
    public String get(String key){
        return (String)getAttributes().get(key);
    }

    //验证状态的set、get
    public boolean isValidated(){
        return this.validated;
    }
    public void setValidated(boolean attrValid){
        this.validated = attrValid;
    }
}
