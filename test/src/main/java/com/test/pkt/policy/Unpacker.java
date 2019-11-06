package com.test.pkt.policy;

import com.test.pkt.cfg.AttributeableCfg;
import com.test.pkt.cfg.PolicyCfg;

import java.util.Map;

public abstract interface Unpacker {
    public abstract Map<String,Object> unpackPolicy(PolicyContext pararPolicyContext, PolicyCfg paramPolicyCfg,Map<String,Object> paramMap);

    public abstract void checkElementCfgValid(AttributeableCfg paramAttributtributrableCfg);
}
