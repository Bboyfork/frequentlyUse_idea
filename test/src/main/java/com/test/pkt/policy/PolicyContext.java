package com.test.pkt.policy;

import com.test.pkt.cfg.AttributeableCfg;
import com.test.pkt.cfg.PolicyCfg;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
* policy、policyCfg、ognlContext 的属性+set/get
*
*
* popObj    移除最后一个索引的objStack
* pushObj   添加一个objStack
* topObj    返回最后插入的objStack
* rootObj   返回objStack(0)
*
* pushCtx   添加一个到ctxStack
* popCtx    移除最后一个索引的ctxStack
* topCtx    返回最后插入的ctxStack
*
* 没啥好说的 根据键值得到
* getPolicyAttr(String attr)
* getPolicyAttr(String attr,String defValue)
*
* getNodeAttr(AttributeableCfg node,String attr)
* getNodeAttr(AttributeableCfg node,String attr,String defVal)
* */
public class PolicyContext {
    private PolicyCfg policyCfg;
    private List<Map<String,Object>> objStack;  //list套map的obj堆？？
    private List<Map<String,Object>> ctxStack;  //ctx堆？
    private Policy policy;
    private static Map<String,Object> ognlContext;

    public static Map<String, Object> getOgnlContext() {
        return ognlContext;
    }

    public static void setOgnlContext(Map<String, Object> ognlContext) {
        PolicyContext.ognlContext = ognlContext;
    }

    public PolicyContext(){
        this.objStack = new ArrayList();
        this.ctxStack = new ArrayList();
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public Policy getPolicy() {
        return this.policy;
    }

    public PolicyCfg getPolicyCfg() {
        return policyCfg;
    }

    public void setPolicyCfg(PolicyCfg policyCfg) {
        this.policyCfg = policyCfg;
    }

    public Map<String, Object> popObj() {
        if(this.objStack.size() == 0){
            return null;
        }
        return (Map)this.objStack.remove(this.objStack.size() - 1);
    }

    public void pushObj(Map<String, Object> obj) {
        this.objStack.add(obj);
    }

    public Map<String,Object> topObj(){
        if(this.objStack.size() == 0){
            return null;
        }
        return (Map)this.objStack.get(this.objStack.size() - 1);
    }

    public Map<String,Object> rootObj(){
        if(this.objStack.size() == 0){
           return null;
        }
        return (Map)this.objStack.get(0);
    }

    public void pushCtx(Map<String,Object> obj){
        this.ctxStack.add(obj);
    }

    public Map<String,Object> popCtx(){
        if(this.ctxStack.size() == 0){
            return null;
        }
        return (Map)this.ctxStack.remove(this.ctxStack.size() - 1);
    }

    public Map<String,Object> topCtx(){
        if(this.ctxStack.size() == 0){
            return null;
        }
        return (Map)this.ctxStack.get(this.ctxStack.size() - 1);
    }

    public String getPolicyAttr(String attr){
        PolicyCfg policy = getPolicyCfg();
        String ret = policy.get(attr);
        if((ret != null)&&(!"".equals(ret))){
            return ret;
        }
        return null;
    }

    public String getPolicyAttr(String attr,String defVal){
        PolicyCfg policy = getPolicyCfg();
        String ret = policy.get(attr);
        if((ret != null)&&(!"".equals(ret))){
            return ret;
        }
        return defVal;
    }

    public String getNodeAttr(AttributeableCfg node,String attr){
        String value = node.get(attr);
        if(value == null){
            return getPolicyAttr(attr);
        }
        return value;
    }

    public String getNodeAttr(AttributeableCfg node,String attr,String defVal){
        String value = node.get(attr);
        if(value == null){
            return getPolicyAttr(attr);
        }
        if(value == null){
            value = defVal;
        }
        return value;
    }


}
