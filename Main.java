package miniC_main;



import miniC_analyzer.*;
import miniC_Gs.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;





public class Main {
    public static void main(String[] args) throws Exception {
    	
//		LL（1）文法产生集合
        ArrayList<String> gsArray = new ArrayList<String>();
        
        Gs gs = new Gs();
        
//		初始化文法集
        initGs(gsArray);
        
        gs.setGsArray(gsArray);

//      获取非终结符集与终结符集 
        gs.getNvNt();
        
//      初始化表达式集合 
        gs.initExpressionMaps();
        
//      获取First集 
        gs.getFirst();
        
//        System.out.println(gs.getFirstMap());
        
        System.out.println("FirstMap——");
        Iterator<String> it2 = gs.getFirstMap().keySet().iterator();  
        while (it2.hasNext()) {  
          //it.next()得到的是key，tm.get(key)得到obj  
        	String tCharacter=it2.next();
        	System.out.print(tCharacter+"  ");
        	System.out.println(gs.getFirstMap().get(tCharacter));  
        }  
        System.out.println();
        System.out.println();
        System.out.println();
//      设置开始符
        gs.setS("Program");

//      获取Follow集合
        gs.getFollow();
        System.out.println("Follow——");
        Iterator<String> it3 = gs.getFirstMap().keySet().iterator();  
        while (it3.hasNext()) {  
          //it.next()得到的是key，tm.get(key)得到obj  
        	String tCharacter=it3.next();
        	System.out.print(tCharacter+"  ");
        	System.out.println(gs.getFirstMap().get(tCharacter));  
        }  
        System.out.println();
        System.out.println();
        System.out.println();
        
        
        
        
//      获取Select集合
        gs.getSelect();
        System.out.println("select——");
        Iterator<String> it = gs.getSelectMap().keySet().iterator();  
        while (it.hasNext()) {  
          //it.next()得到的是key，tm.get(key)得到obj  
        	String tCharacter=it.next();
        	System.out.println(tCharacter);
        	System.out.println(gs.getSelectMap().get(tCharacter));  
        }  
//      创建一个分析器
        Analyzer analyzer = new Analyzer();
        analyzer.setStartChar("Program");
        analyzer.setLl1Gs(gs);
        
//      分析的程序
//        String str="int main() { bool i = 1 ; i = 2 ; return 1 ; }";
        String str="int main() { int i = 0 ; for ( int i = 0 ; i < 10 ; i++ ) { a = a + i ; } return 1 ; }";
        ArrayList<String> temp=new ArrayList<>();
        temp.addAll(Arrays.asList(str.split(" ")));
        
//      输入
        analyzer.setStr(temp);
        
//       分析
        analyzer.analyze();

    }

 

    /**
     * 初始化LL(1)文法
     * @param gsArray
     */
    private static void initGs(ArrayList<String> gsArray) {
    
        gsArray.add("Program->Type main() Block");
        gsArray.add("Type->int");
        gsArray.add("Type->bool");
        gsArray.add("Block->{ Stmts return Digit ; }");

//        gsArray.add("Num->1");
        
        gsArray.add("Stmts->Stmt t1");
        gsArray.add("t1->Stmt t1");
        gsArray.add("t1->ε");
//        
        gsArray.add("Stmt->Type i = 0 ; ");
        gsArray.add("Stmt->a = a + i ; ");
        gsArray.add("Stmt->for ( Fora ; Forb ; Forc ) { Stmt }");
//        gsArray.add("Stmt->for ( int i = 0 ; i < 10 ; i++ ) { a = a + i ; } ");
        gsArray.add("Fora->Type i = 0 ");
        gsArray.add("Forb-> i Judge_op 10 ");
        gsArray.add("Forc-> i ++ ");
//
        gsArray.add("Judge_op->==");
        gsArray.add("Judge_op->!=");
        
        gsArray.add("Judge_op->>");
        gsArray.add("Judge_op-><");
        gsArray.add("Judge_op->>=");
        gsArray.add("Judge_op-><=");

       
        gsArray.add("Digit->0");
        gsArray.add("Digit->1");
        gsArray.add("Digit->2");
        gsArray.add("Digit->3");
        gsArray.add("Digit->4");
        gsArray.add("Digit->5");
        gsArray.add("Digit->6");
        gsArray.add("Digit->7");
        gsArray.add("Digit->8");
        gsArray.add("Digit->9");
        

        

    }
}
