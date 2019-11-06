package com.test.pkt.policy;

import com.test.pkt.cfg.PolicyCfg;

import java.util.Map;

public abstract interface Policy {
    public abstract byte[] pack(Map<String,Object> paramMap1, PolicyCfg parmpolicyCfg, Map<String,Object> paramMap2) throws PktException;

    public abstract Map<String,Object> unpack(Map<String, Object> paramMap1, ByteBuffer paramByteBuffer, PolicyCfg paramPolicyCfg, Map<String,Object> paramMap2) throws PktException;

    public abstract Packer getPacker();

    public abstract Unpacker getUnpacker();
}
