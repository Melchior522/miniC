package miniC_Gs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

import miniC_textmatch.TextUtil;

public class Gs implements Serializable {  
  
    /** 
     *  
     */  
    private static final long serialVersionUID = 1L;  
  
    public Gs() {  
        super();  
        gsArray = new ArrayList<String>();  
        nvSet = new TreeSet<String>();  
        ntSet = new TreeSet<String>();  
        firstMap = new HashMap<String, TreeSet<String>>();  
        followMap = new HashMap<String, TreeSet<String>>();  
        selectMap = new TreeMap<String, HashMap<String, TreeSet<String>>>();  
    }  
  
    private String[][] analyzeTable;  
  
    /** 
     * Select集合 
     */  
    private TreeMap<String, HashMap<String, TreeSet<String>>> selectMap;  
    /** 
     * LL（1）文法产生集合 
     */  
    private ArrayList<String> gsArray;  
    /** 
     * 表达式集合 
     */  
    private HashMap2 expressionMap;  
    /** 
     * 开始符 
     */  
    private String s;  
    /** 
     * Vn非终结符集合 
     */  
    private TreeSet<String> nvSet;  
    /** 
     * Vt终结符集合 
     */  
    private TreeSet<String> ntSet;  
    /** 
     * First集合 
     */  
    private HashMap<String, TreeSet<String>> firstMap;  
    /** 
     * Follow集合 
     */  
    private HashMap<String, TreeSet<String>> followMap;  
  
    public String[][] getAnalyzeTable() {  
        return analyzeTable;  
    }  
  
    public void setAnalyzeTable(String[][] analyzeTable) {  
        this.analyzeTable = analyzeTable;  
    }  
  
    public TreeMap<String, HashMap<String, TreeSet<String>>> getSelectMap() {  
        return selectMap;  
    }  
  
    public void setSelectMap(TreeMap<String, HashMap<String, TreeSet<String>>> selectMap) {  
        this.selectMap = selectMap;  
    }  
  
    public HashMap<String, TreeSet<String>> getFirstMap() {  
        return firstMap;  
    }  
  
    public void setFirstMap(HashMap<String, TreeSet<String>> firstMap) {  
        this.firstMap = firstMap;  
    }  
  
    public HashMap<String, TreeSet<String>> getFollowMap() {  
        return followMap;  
    }  
  
    public void setFollowMap(HashMap<String, TreeSet<String>> followMap) {  
        this.followMap = followMap;  
    }  
  
    public HashMap2 getExpressionMap() {  
        return expressionMap;  
    }  
  
    public void setExpressionMap(HashMap2 expressionMap) {  
        this.expressionMap = expressionMap;  
    }  
  
    public ArrayList<String> getGsArray() {  
        return gsArray;  
    }  
  
    public void setGsArray(ArrayList<String> gsArray) {  
        this.gsArray = gsArray;  
    }  
  
    public String getS() {  
        return s;  
    }  
  
    public void setS(String s) {  
        this.s = s;  
    }  
  
    public TreeSet<String> getNvSet() {  
        return nvSet;  
    }  
  
    public void setNvSet(TreeSet<String> nvSet) {  
        this.nvSet = nvSet;  
    }  
  
    public TreeSet<String> getNtSet() {  
        return ntSet;  
    }  
  
    public void setNtSet(TreeSet<String> ntSet) {  
        this.ntSet = ntSet;  
    }  
  
    /** 
     * 获取非终结符集与终结符集 
     */  
    public void getNvNt() {  
        for (String gsItem : gsArray) {  
            String[] nvNtItem = gsItem.split("->");  
            String stringItemStr = nvNtItem[0];  
// 			nv在左边  
            nvSet.add(stringItemStr);  
        }  
        for (String gsItem : gsArray) {  
            String[] nvNtItem = gsItem.split("->");  
// 			nt在右边  
            String[] nvItemStr = nvNtItem[1].split(" "); 
// 			遍历每一个字  
            for (int i = 0; i < nvItemStr.length; i++) {  
                String stringItem = nvItemStr[i];  
                if (!nvSet.contains(stringItem)) {  
                    ntSet.add(stringItem);  
                }  
            }  
        }  
    }  
  
    /** 
     * 初始化表达式集合 
     */  
    public void initExpressionMaps() {  
        expressionMap = new HashMap2();  
        for (String gsItem : gsArray) {  
            String[] nvNtItem = gsItem.split("->");  
            String charItemStr = nvNtItem[0];  
            String charItemRightStr = nvNtItem[1];   
            if (!expressionMap.containsKey(charItemStr)) {   
            	ArrayList<String> expArr = new ArrayList<String>();  
                expArr.add(charItemRightStr); 
                expressionMap.put(charItemStr, expArr);
            } else {  
                ArrayList<String> expArr = expressionMap.get(charItemStr);  
                expArr.add(charItemRightStr);  
                expressionMap.put(charItemStr, expArr);  
            }  
        }  
    }  
    
    
    /** 
     * 获取First集 
     */  
    public void getFirst() {  
    	
        // 遍历所有Nv,求出它们的First集合  
        Iterator<String> iterator = nvSet.iterator();  
        while (iterator.hasNext()) {  
            String stringItem = iterator.next();  
            ArrayList<String> arrayList = expressionMap.get(stringItem);  


            for (String itemStr : arrayList) {  
                boolean shouldBreak = false;  
                // Y1Y2Y3...Yk
                String[] stringstr=itemStr.split(" ");
                for (int i = 0; i < stringstr.length; i++) {  
                    String itemitemChar = stringstr[i];  
                    TreeSet<String> itemSet = firstMap.get(stringItem);  
                    if (null == itemSet) {  
                        itemSet = new TreeSet<String>();  
                    }  
                    shouldBreak = calcFirst(itemSet,stringItem, itemitemChar);  
                    if (shouldBreak) {  
                        break;  
                    }  
                }  
            }  
        }  
    }  
  
    /** 
     * 计算First函数 
     * @return 
     */  
    
    private boolean calcFirst(TreeSet<String> itemSet, String charItem, String itemitemChar) {  
        // get ago  
        // TreeSet<Character> itemSet = new TreeSet<Character>();  
        // 将它的每一位和Nt判断下  
        // 是终结符或空串,就停止，并将它加到FirstMap中  
        if (itemitemChar.equals("ε")|| ntSet.contains(itemitemChar)) {  
            itemSet.add(itemitemChar);  
            firstMap.put(charItem, itemSet);  
            // break;  
            return true;  
        } else if (nvSet.contains(itemitemChar)) {// 这一位是一个非终结符  
            ArrayList<String> arrayList = expressionMap.get(itemitemChar);  
            for (int i = 0; i < arrayList.size(); i++) {  
                String string = arrayList.get(i);  
                String tempChar = string.split(" ")[0];  
                calcFirst(itemSet, charItem, tempChar);  
            }  
        }  
        return true;  
    }  
  
    /** 
     * 获取Follow集合 
     */  
    public void getFollow() {  
        for (String tempKey : nvSet) {  
            TreeSet<String> tempSet = new TreeSet<String>();  
            followMap.put(tempKey, tempSet);  
        }  
        // 遍历所有Nv,求出它们的First集合  
        Iterator<String> iterator = nvSet.descendingIterator();  
   
  
        while (iterator.hasNext()) {  
            String charItem = iterator.next();  

            ArrayList<String> keySet = expressionMap.getLeft();  
            for (String keyCharItem : keySet) {  
                ArrayList<String> charItemArray = expressionMap.get(keyCharItem);  
                for (String itemCharStr : charItemArray) {  
                    TreeSet<String> itemSet = followMap.get(charItem);  
                    calcFollow(charItem, charItem, keyCharItem, itemCharStr, itemSet);  
                } 
                
            }  
        }  
    }  
  
    /** 
     * 计算Follow集 
     */  
    private void calcFollow(String putCharItem, String charItem, String keyCharItem, String itemCharStr,  
            TreeSet<String> itemSet) {  
//         （1）A是S（开始符)，加入#  
        if (charItem.equals(s)) {  
            itemSet.add("#");  

            followMap.put(putCharItem, itemSet);  
        }  
//         (2)Ab,=First(b)-ε,直接添加终结符  
        if (TextUtil.containsAb(ntSet, itemCharStr, charItem)) {  
            String alastChar = TextUtil.getAlastChar(itemCharStr, charItem);  
            itemSet.add(alastChar);  
            followMap.put(putCharItem, itemSet);    
        }  
//         (2).2AB,=First(B)-ε,=First(B)-ε，添加first集合  
        if (TextUtil.containsAB(nvSet, itemCharStr, charItem)) {  
            String alastChar = TextUtil.getAlastChar(itemCharStr, charItem);    
            TreeSet<String> treeSet = firstMap.get(alastChar);  
            itemSet.addAll(treeSet);  
            if (treeSet.contains("ε")) {  
                itemSet.add("#");  
            }  
            itemSet.remove("ε");  
            followMap.put(putCharItem, itemSet);  

            if (TextUtil.containsbAbIsNull(nvSet, itemCharStr, charItem, expressionMap)) {  
                String tempChar = TextUtil.getAlastChar(itemCharStr, charItem);  
                System.out.println("tempChar:" + tempChar + "  key" + keyCharItem);  
                if (!keyCharItem.equals(charItem)) {  
                   ArrayList<String> keySet = expressionMap.getLeft();  
                    for (String keyCharItems : keySet) {  
                        ArrayList<String> charItemArray = expressionMap.get(keyCharItems);  
                        for (String itemCharStrs : charItemArray) {  
                            calcFollow(putCharItem, keyCharItem, keyCharItems, itemCharStrs, itemSet);  
                        }  
                    }  
                }  
            }  
        }  
//       (3)B->aA,=Follow(B),添加followB  
        if (TextUtil.containsbA(nvSet, itemCharStr, charItem, expressionMap)) {  
            if (!keyCharItem.equals(charItem)) {  
                ArrayList<String> keySet = expressionMap.getLeft();  
                for (String keyCharItems : keySet) {  
                    ArrayList<String> charItemArray = expressionMap.get(keyCharItems);  
                    for (String itemCharStrs : charItemArray) {  
                        calcFollow(putCharItem, keyCharItem, keyCharItems, itemCharStrs, itemSet);  
                    }  
                }  
            }  
        }  
    }  
  
    /** 
     * 获取Select集合 
     */  
    public void getSelect() {  
        // 遍历每一个表达式  

        ArrayList<String> keySet = expressionMap.getLeft();  
        for (String selectKey : keySet) {  
            ArrayList<String> arrayList = expressionMap.get(selectKey);  
            // 每一个表达式  
            HashMap<String, TreeSet<String>> selectItemMap = new HashMap<String, TreeSet<String>>();  
            for (String selectExp : arrayList) {  
                /** 
                 * 存放select结果的集合 
                 */  
                TreeSet<String> selectSet = new TreeSet<String>();  
                // set里存放的数据分3种情况,由selectExp决定  
                // 1.A->ε,=follow(A)  
                if (TextUtil.isEmptyStart(selectExp)) {  
                    selectSet = followMap.get(selectKey);  
                    selectSet.remove("ε");  
                    selectItemMap.put(selectExp, selectSet);  
                }  
                // 2.Nt开始,=Nt  
                // <br>终结符开始  
                if (TextUtil.isNtStart(ntSet, selectExp)) {  
                    selectSet.add(selectExp.split(" ")[0]);  
                    selectSet.remove("ε");  
                    selectItemMap.put(selectExp, selectSet);  
                }  
                // 3.Nv开始，=first(Nv)  
                if (TextUtil.isNvStart(nvSet, selectExp)) {  

                    selectSet = firstMap.get(selectKey);  
                    selectSet.remove("ε");  
                    selectItemMap.put(selectExp, selectSet);  
                }  
                selectMap.put(selectKey, selectItemMap);  
            }  
        }  
    }  
}
