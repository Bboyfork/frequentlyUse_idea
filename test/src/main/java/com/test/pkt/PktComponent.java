package com.test.pkt;

import com.test.pkt.cfg.IElementCfg;
import com.test.pkt.cfg.PolicyCfg;
import com.test.pkt.cfg.RootCfg;
import com.test.pkt.policy.PktProcessor;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *几个pack 和unpack 的重载方法
 *
 * */
public class PktComponent {

    @Bizlet("公共组包")
    public static byte[] pack(String pktPath, Map<String, Object> map){
        return PktProcessor.pack(map,pktPath,null);
    }

    public  static byte pack(String pktPath, Map<String, Object> map, Map<String,Object> attachment){
        return PktProcessor.pack(map,pktPath,attachment);
    }

    public static String pack(String pktPath,Map<String, Object> map,String encoding){
        try{
            return new String(PktProcessor.pack(map,pktPath,null),encoding);
        }catch (UnsupportedEncodingException e){
            throw new PktException(e.getMessage(), e);
        }
    }

    //-------上面是几个pack、unpack过程-------------------
    //-------下面-------------------
    @Bizlet("内部报文组包：包括CR、SR、SunFront等")
    public static byte[] flowMsgPack(Map<String,Object> map){
        byte[] mapBytes = mapSerialPack(map);
    }

    @Bizlet("Map序列化组包")
    public static byte[] mapSerialPack(Map<String,Object> map){
        RootCfg cfg = new RootCfg();
        List<IElementCfg> list =new ArrayList();
        PolicyCfg policy = new PolicyCfg();
        policy.setType("serial");
        list.add(policy);
        cfg.setElements(list);
        return PktProcessor.pack(map,cfg,null);
    }

    @Bizlet("内部报文解包：包括CR、SR、SunFront等")
    public static Map<String,Object> flowMsgUnpack(byte[] bs){
        byte[] lenBytes = new byte[4];
        System.arraycopy(bs,0,lenBytes,0,4);
        int len = ByteUtils.bytes2Int(lenBytes);
        byte[] mapBytes = new byte[len+4];
        System.arraycopy(bs,4,mapBytes,0,len);
        return mapSerialUnPack(mapBytes);
    }

    @Bizlet("Map序列化解包")
    public static Map<String,Object> mapSerialUnPack(byte[] bs){
        RootCfg cfg = new RootCfg();
        List<IElementCfg> list = new ArrayList();
        PolicyCfg policy = new PolicyCfg();
        policy.setType("serial");
        list.add(policy);
        cfg.setElements(list);
        return PktProcessor.unpack(bs,cfg,null);
    }

    @Bizlet("将Map打包成ltts报文字符串")
    public static String lttsPack(Map<String,Object> data){
        return LttsPackUtil.pack(data);
    }

    @Bizlet("将Map打包成ltts报文字符串，根据现在")
    public static String lttsPackByLimit(Map<String,Object> data,int limitLength){
    return LttsPackUtil.pack(data,limitLength);
    }

    public  static Map<String,Object> lttsUnpack(String ltts){
        return LttsPackUtil.unpack(ltts);
    }
}
