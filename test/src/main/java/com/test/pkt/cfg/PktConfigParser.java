package com.test.pkt.cfg;

//import com.sunline.flow.base.util.XMLUtils;

import com.test.pkt.policy.Policy;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PktConfigParser {
    private static PktConfigParser instance;

    public static synchronized PktConfigParser get(){
        if(instance ==null){
            instance = new PktConfigParser();
        }
        return instance;
    }

    public RootCfg parse(InputStream is){
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(is);
            Element root = doc.getRootElement();
            return parseRoot(root);
        }catch (DocumentException e){
            e.printStackTrace();
        }
        return null;
    }

    protected static String attrStr(Element e ,String name){
        //return (String)XMLUtils.getElementAttrValue(e,name,"");
        return null;
    }

    private void parseElementAttributes(IElementCfg cfg,Element e){
        for(int i = 0;i<e.attributeCount();i++){
            Attribute a = (Attribute)e.attributes().get(i);
            String key = a.getName();
            Field f = null;
            try {
                f = cfg.getClass().getDeclaredField(key);
                f.setAccessible(true);
                f.set(cfg,attrStr(e,key));
            }catch (NoSuchFieldException e1){
                if (cfg instanceof AttributeableCfg){
                    ((AttributeableCfg)cfg).set(key,attrStr(e,key));
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }

    /*
    *
    * ======================
    * */
    private void parseContainer(IContainerCfg cfg,Element e){
        List<IElementCfg> list = cfg.getElements();
        if(list == null){
            list = new ArrayList();
            cfg.setElements(list);
        }
        String name = e.getName();
        if("field".equals(name)){
            list.add(parseField(e));
        }else if("policy".equals(name)){
            list.add(parsePolicy(e));
        }else if("struct".equals(name)){
            list.add(parseStruct(e));
        }else if("loop".equals(name)){
            list.add(parseLoop(e));
        }else if("switch".equals(name)){
            list.add(parseSwitch(e));
        }else if("include".equals(name)){
            list.add(parseInclude(e));
        }
    }

    private RootCfg parseRoot(Element e){
        RootCfg ret = new RootCfg();
        parseElementAttributes(ret,e);
        for(Object o : e.elements()){
            parseContainer(ret,(Element)o);
        }
        return ret;
    }

    private PolicyCfg parsePolicy(Element e){
        PolicyCfg ret = new PolicyCfg();
        parseElementAttributes(ret,e);
        for(Object o : e.elements()){
            parseElementAttributes(ret,(Element) o);
        }
        return ret;
    }

    private StructCfg parseStruct(Element e){
        StructCfg ret = new StructCfg();
        parseElementAttributes(ret,e);
        for(Object o:e.elements()){
            parseContainer(ret,(Element) o);
        }
        return ret;
    }

    private LoopCfg parseLoop(Element e){
        LoopCfg ret = new LoopCfg();
        parseElementAttributes(ret,e);
        for (Object o :e.elements()) {
            parseContainer(ret,(Element) o);
        }
        return ret;
    }

    private SwitchCfg parseSwitch(Element e){
        SwitchCfg ret = new SwitchCfg();
        List<CaseCfg> cases = new ArrayList();
        ret.setCases(cases);
        parseElementAttributes(ret,e);
        for(Object o: e.elements()){
            Element ee = (Element) o;
            if("case".equals(ee.getName())){
                cases.add(parseCase(ee));
            }else if("default".equals(ee.getName())){
                ret.setDef(parseDefault(ee));
            }
        }
        return ret;
    }

    private CaseCfg parseCase(Element e){
        CaseCfg ret = new CaseCfg();
        parseElementAttributes(ret,e);
        for(Object o :e.elements()){
            parseContainer(ret,(Element) o);
        }
        return ret;
    }

    private DefaultCfg parseDefault(Element e){
        DefaultCfg ret = new DefaultCfg();
        parseElementAttributes(ret,e);
        for(Object o : e.elements()){
            parseContainer(ret,(Element) o);
        }
        return ret;
    }

    private FieldCfg parseField(Element e){
        FieldCfg ret = new FieldCfg();
        parseElementAttributes(ret,e);

        Element pe = e.element("policy");
        if(pe != null){
            ret.setPolicy(parsePolicy(pe));
        }
        pe = e.element("include");
        if(pe != null){
            ret.setInclude(parseInclude(pe));
        }
        return ret;
    }

    private IncludeCfg parseInclude(Element e){
        IncludeCfg ret = new IncludeCfg();
        parseElementAttributes(ret,e);
        return ret;
    }

}
