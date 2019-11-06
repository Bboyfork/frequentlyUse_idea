package com.test.pkt.cfg;

import java.util.List;

public abstract interface IContainerCfg extends IElementCfg{
    public abstract List<IElementCfg> getElements();
    public abstract void setElements(List<IElementCfg> paramList);
}
