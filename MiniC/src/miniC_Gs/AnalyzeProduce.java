package miniC_Gs;

import java.io.Serializable;
import java.util.ArrayList;


public class AnalyzeProduce implements Serializable{
    private static final long serialVersionUID = 10L;
    private Integer index;
    private String analyzeStackStr;
    private ArrayList<String> str;
    private String useExpStr;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getAnalyzeStackStr() {
        return analyzeStackStr;
    }

    public void setAnalyzeStackStr(String analyzeStackStr) {
        this.analyzeStackStr = analyzeStackStr;
    }

    public ArrayList<String> getStr() {
        return str;
    }

    public void setStr(ArrayList<String> str) {
        this.str = str;
    }

    public String getUseExpStr() {
        return useExpStr;
    }

    public void setUseExpStr(String useExpStr) {
        this.useExpStr = useExpStr;
    }

}