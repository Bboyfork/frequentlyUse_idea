package com.test.pkt.policy;

import com.test.pkt.cfg.AttributeableCfg;

import java.util.Map;

public abstract interface Packer {
    public abstract byte[] packPolicy(PolicyContext paramPolicyContext, PolicyCfg paramPolicyCfg, Map<String,Object> paramMap);

    public abstract void chenElementCfgValid(AttributeableCfg paramAttributeableCfg);
}
