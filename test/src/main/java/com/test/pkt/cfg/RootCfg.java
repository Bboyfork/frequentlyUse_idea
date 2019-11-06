package com.test.pkt.cfg;

import java.util.List;

public class RootCfg implements IContainerCfg {
    public static final String FORMAT_STR = "str";
    public static final String FORMAT_HEX = "hex";
    public static final String FORMAT_MOCK = "mock";
    private String consoleFormat;
    private String consoleEncoding;
    private List<IElementCfg> elements;

    public String getConsoleFormat() {
        return consoleFormat;
    }

    public void setConsoleFormat(String consoleFormat) {
        this.consoleFormat = consoleFormat;
    }

    public String getConsoleEncoding() {
        return consoleEncoding;
    }

    public void setConsoleEncoding(String consoleEncoding) {
        this.consoleEncoding = consoleEncoding;
    }

    @Override
    public List<IElementCfg> getElements() {
        return elements;
    }

    @Override
    public void setElements(List<IElementCfg> elements) {
        this.elements = elements;
    }
}
