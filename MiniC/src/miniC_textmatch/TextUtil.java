package miniC_textmatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import miniC_Gs.*;

public class TextUtil {

    public static boolean containsbA(TreeSet<String> nvSet, String i, String a,
                                     HashMap2 expressionMap) {
        String aStr = a;
        String[] itemCharStr=i.split(" ");
        String lastStr = itemCharStr[itemCharStr.length-1];
        if (lastStr.equals(aStr)) {
            return true;
        }
        return false;
        
    }

    public static boolean containsbAbIsNull(TreeSet<String> nvSet, String itemCharStr, String a,HashMap2 expressionMap) {
        String aStr = a;
        if (containsAB(nvSet, itemCharStr, a)) {
            String alastChar = getAlastChar(itemCharStr, a);
            ArrayList<String> arrayList = expressionMap.get(alastChar);
            if (arrayList.contains("ε")) {
                System.out.println(alastChar + "  contains('ε')" + aStr);
                return true;
            }
        }
        return false;

    }

    public static boolean containsAb(TreeSet<String> ntSet, String i, String a) {
        String aStr = a;
        String[] itemCharStr=i.split(" ");
        for(int x=0;x<itemCharStr.length;x++){
        	if(itemCharStr[x].equals(a)){
        		int aIndex = x;
                ArrayList<String>findStr =new ArrayList<>();
                try {
                    findStr.add(itemCharStr[x+1]);
                    findStr.add(itemCharStr[x+2]);
                } catch (Exception e) {
                    return false;
                }
                if (ntSet.contains(findStr.get(0))) {
                    return true;
                } else {
                    return false;
                }
        	}
        }
        return false;
    }


    public static boolean containsAB(TreeSet<String> nvSet, String i, String a) {
    	String aStr = a;
        String[] itemCharStr=i.split(" ");
        for(int x=0;x<itemCharStr.length;x++){
        	if(itemCharStr[x].equals(a)){
        		int aIndex = x;
                ArrayList<String>findStr =new ArrayList<>();
                try {
                    findStr.add(itemCharStr[x+1]);
                    findStr.add(itemCharStr[x+2]);
                } catch (Exception e) {
                    return false;
                }
                if (nvSet.contains(findStr.get(0))) {
                    return true;
                } else {
                    return false;
                }
        	}
        }
        return false;
    }

    public static String getAlastChar(String i, String a) {
        String aStr = a;
        String[] itemCharStr=i.split(" ");
        for(int x=0;x<itemCharStr.length;x++){
        	if(itemCharStr[x].equals(a)){
        		int aIndex = x;
                ArrayList<String>findStr =new ArrayList<>();
                try {
                    findStr.add(itemCharStr[x+1]);
                    findStr.add(itemCharStr[x+2]);
                } catch (Exception e) {
                    return null;
                }
                return findStr.get(0);
        	}
        }
        return null;
    }

  
    public static boolean isEmptyStart(String selectExp) {
    	String charAt = selectExp.split(" ")[0];
        if (selectExp.equals("ε")) {
            return true;
        }
        return false;
    }

    public static boolean isNtStart(TreeSet<String> ntSet, String selectExp) {
    	String charAt = selectExp.split(" ")[0];
        if (ntSet.contains(charAt)) {
            return true;
        }
        return false;
    }


    public static boolean isNvStart(TreeSet<String> nvSet, String selectExp) {
    	
    	String charAt = selectExp.split(" ")[0];
        if (nvSet.contains(charAt)) {
            return true;
        }
        return false;
    }


    public static String findUseExp(TreeMap<String, HashMap<String, TreeSet<String>>> selectMap, String peek,
                                    String charAt) {
        try {
            HashMap<String, TreeSet<String>> hashMap = selectMap.get(peek);
            Set<String> keySet = hashMap.keySet();
            for (String useExp : keySet) {
                TreeSet<String> treeSet = hashMap.get(useExp);
                if (treeSet.contains(charAt)) {
                    return useExp;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}