package miniC_analyzer;


import java.util.ArrayList;
import java.util.Stack;

import miniC_Gs.AnalyzeProduce;
import miniC_Gs.Gs;
import miniC_textmatch.TextUtil;

public class Analyzer {

    public Analyzer() {
    	
        super();
        analyzeStatck = new Stack<String>();
        // 结束符进栈
        analyzeStatck.push("#");
    }

    private ArrayList<AnalyzeProduce> analyzeProduces;

    /**
     * LL（1）文法
     */
    private Gs ll1Gs;

    public Gs getLl1Gs() {
        return ll1Gs;
    }

    public void setLl1Gs(Gs ll1Gs) {
        this.ll1Gs = ll1Gs;
    }

    /**
     * 开始符
     */
    private String startChar;

    /**
     * 分析栈
     */
    private Stack<String> analyzeStatck;
    /**
     * 剩余输入串
     */
    private ArrayList<String> str;
    /**
     * 推导所用产生或匹配
     */
    private String useExp;

    public ArrayList<AnalyzeProduce> getAnalyzeProduces() {
        return analyzeProduces;
    }

    public void setAnalyzeProduces(ArrayList<AnalyzeProduce> analyzeProduces) {
        this.analyzeProduces = analyzeProduces;
    }

    public String getStartChar() {
        return startChar;
    }

    public void setStartChar(String startChar) {
        this.startChar = startChar;
    }

    public Stack<String> getAnalyzeStatck() {
        return analyzeStatck;
    }

    public void setAnalyzeStatck(Stack<String> analyzeStatck) {
        this.analyzeStatck = analyzeStatck;
    }

    public ArrayList<String> getStr() {
        return str;
    }

    public void setStr(ArrayList<String> str) {
        this.str = str;
    }

    public String getUseExp() {
        return useExp;
    }

    public void setUseExp(String useExp) {
        this.useExp = useExp;
    }

    /**
     * 分析
     */
    public void analyze() {
        analyzeProduces = new ArrayList<AnalyzeProduce>();

        // 开始符进栈
        analyzeStatck.push(startChar);
        System.out.println("开始符:" + startChar);
        int index = 0;
        // 开始分析

        while (!analyzeStatck.empty()) {
            System.out.println();
            if(str.size()<=0){
            	analyzeStatck.pop();
            	System.out.println("栈——>"+analyzeStatck);
            	break;
            }
            System.out.println("本次需要匹配： "+str.get(0));
        	System.out.println("栈——>"+analyzeStatck);
            index++;
            if (!analyzeStatck.peek().equals(str.get(0))) {
            	
            	AnalyzeProduce produce = new AnalyzeProduce();
                produce.setIndex(index);
                produce.setAnalyzeStackStr(analyzeStatck.toString());
                produce.setStr(str);
                
                // 到分析表中找到这个产生式
                String tempUseExpStr = TextUtil.findUseExp(ll1Gs.getSelectMap(), analyzeStatck.peek(), str.get(0));
                
                System.out.println("下次将栈顶替换为 "+tempUseExpStr);

                
                String[] nowUseExpStr=new String[]{};
                if (null == tempUseExpStr) {
                    produce.setUseExpStr("无法匹配!");
                }
                else {
                	
                    if(tempUseExpStr.contains(" ")){
                    	nowUseExpStr=tempUseExpStr.split(" ");
                    }
                    else{
            
                    	nowUseExpStr=(tempUseExpStr+" ").split(" ");
                    }
                    produce.setUseExpStr(analyzeStatck.peek() + "->" + tempUseExpStr);
                }
                
               
                analyzeProduces.add(produce);
                
                // 将之前的分析栈中的栈顶出栈
                analyzeStatck.pop();
                
                // 将要用到的表达式入栈,反序入栈
                if (null != tempUseExpStr && ! nowUseExpStr[0].equals("ε")) {
                    for (int j = nowUseExpStr.length - 1; j >= 0; j--) {
                        String currentChar = nowUseExpStr[j];
                        analyzeStatck.push(currentChar);
                        
                    }
                }
                continue;
            }
            
            // 如果可以匹配,分析栈出栈，串队去掉一个字符串

            if (analyzeStatck.peek().equals(str.get(0))) {
                AnalyzeProduce produce = new AnalyzeProduce();
                produce.setIndex(index);
                produce.setAnalyzeStackStr(analyzeStatck.toString());
                produce.setStr(str);
                produce.setUseExpStr("“" + str.get(0) + "”匹配");
                analyzeProduces.add(produce);
                analyzeStatck.pop();
                str.remove(str.get(0));
                continue;
            }
            
        }

    }

}
