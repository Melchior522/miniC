

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;


public class Main {
    public static void main(String[] args) throws Exception {
        // // LL（1）文法产生集合
        ArrayList<String> gsArray = new ArrayList<String>();
        // // Vn非终结符集合
        // TreeSet<Character> nvSet = new TreeSet<Character>();
        // // Vt终结符集合
        // TreeSet<Character> ntSet = new TreeSet<Character>();
        Gs gs = new Gs();
        initGs(gsArray);
        gs.setGsArray(gsArray);
        // getNvNt(gsArray, gs.getNvSet(), gs.getNtSet());
        gs.getNvNt();
        
        
        
//        System.out.println(gs.getNtSet());
//        System.out.println(gs.getNvSet());
        gs.initExpressionMaps();
//        System.out.println(gs.getExpressionMap());
        gs.getFirst();
//        System.out.println(gs.getFirstMap());
//        System.out.println(gs.getFirstMap());
//        // 设置开始符
        gs.setS("Program");
//        System.out.println(gs.getS());
        gs.getFollow();
//        System.out.println(gs.getFollowMap());
        gs.getSelect();
//        System.out.println("select——"+gs.getSelectMap());
//        Iterator<String> it = gs.getSelectMap().keySet().iterator();  
//        while (it.hasNext()) {  
//            //it.next()得到的是key，tm.get(key)得到obj  
//        	String tCharacter=it.next();
//        	System.out.println(tCharacter);
//            System.out.println(gs.getSelectMap().get(tCharacter));  
//        } 
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
        
//         创建一个分析器
        Analyzer analyzer = new Analyzer();
        analyzer.setStartChar("Program");
        analyzer.setLl1Gs(gs);
        String str="void main() { int a = 0 ; for ( int i = 0 ; i<10 ; i++ ) { a = a + i ; } int b = 1 ; if ( a > b ) { a = 1 ; } else { a = 2 ; } while ( a > 10 ) { a = a - 1 ; }}";
        ArrayList<String> temp=new ArrayList<>();
        temp.addAll(Arrays.asList(str.split(" ")));
        analyzer.setStr(temp);
        analyzer.analyze();
//        gs.genAnalyzeTable();
        System.out.println("");
    }

    /**
     * 获取非终结符集与终结符集
     *
     * @param gsArray
     * @param nvSet
     * @param ntSet
     */
//    private static void getNvNt(ArrayList<String> gsArray, TreeSet<Character> nvSet, TreeSet<Character> ntSet) {
//        for (String gsItem : gsArray) {
//            String[] nvNtItem = gsItem.split("->");
//            String charItemStr = nvNtItem[0];
//            char charItem = charItemStr.charAt(0);
//            // nv在左边
//            nvSet.add(charItem);
//        }
//        for (String gsItem : gsArray) {
//            String[] nvNtItem = gsItem.split("->");
//            // nt在右边
//            String nvItemStr = nvNtItem[1];
//            // 遍历每一个字
//            for (int i = 0; i < nvItemStr.length(); i++) {
//                char charItem = nvItemStr.charAt(i);
//                if (!nvSet.contains(charItem)) {
//                    ntSet.add(charItem);
//                }
//            }
//        }
//
//    }

    /**
     * 初始化LL(1)文法
     *
     * @param gsArray
     */
    private static void initGs(ArrayList<String> gsArray) {
//        gsArray.add("D->*FD");
//        gsArray.add("D->ε");
//        gsArray.add("T->FD");
//        gsArray.add("E->TC");
//        gsArray.add("F->(E)");
//        gsArray.add("F->i");
//        gsArray.add("C->+TC");
//        gsArray.add("C->ε");
        
        
        
        gsArray.add("Program->Type main() Block");
        gsArray.add("Type->int");
        gsArray.add("Type->bool");
        gsArray.add("Block->{ Stmts return Num ; }");
        gsArray.add("Stmts->Type i = 1 ; ");
        gsArray.add("Num->1");
        gsArray.add("Stmt->Assignment ;");
        gsArray.add("Stmts->Stmts M stmt");
        gsArray.add("Stmts->stmt");
        gsArray.add("Decl->Type Array ;");
        gsArray.add("Array->Identifier [ Num ] ;");
        gsArray.add("Array->Identifier [ Identifier ];");
        gsArray.add("Array->Identifier ;");
        gsArray.add("Self_op-> ++ ;");
        gsArray.add("Self_op-> -- ;");
        gsArray.add("HLogic_op-> &&;");
        gsArray.add("LLogic_op-> ||;");
        gsArray.add("HMath_op-> *;");
        gsArray.add("HMath_op-> /;");
        gsArray.add("HMath_op-> %;");
        gsArray.add("LMath_op-> +;");
        gsArray.add("LMath_op-> -;");
        gsArray.add("Judge_op-> ==;");
        gsArray.add("Judge_op-> !=;");
        gsArray.add("Judge_op-> >=;");
        gsArray.add("Judge_op-> <=;");
        gsArray.add("Judge_op-> >;");
        gsArray.add("Judge_op-> <;");
        gsArray.add("Stmt-> Decl ;");
        gsArray.add("Stmt-> if ( Bool ) M Stmt ;");
        gsArray.add("Stmt-> if ( Bool ) M Stmt N else M Stmt ;");
        gsArray.add("Stmt-> while M ( Bool ) M Stmt ;");
        gsArray.add("Stmt-> for ( Fora ; M Forb ; Forc ) M Stmt ;");
        gsArray.add("Stmt-> { Stmts } ;");
        gsArray.add("Fora-> Assignment ;");
        gsArray.add("Fora-> ε ;");
        gsArray.add("Forb-> ε ;");
        gsArray.add("Forc-> ε ;");
        gsArray.add("Forb-> Bool ;");
        gsArray.add("Forc-> ForAssignment ;");
        gsArray.add("Factor-> ( Bool ) ;");
        gsArray.add("Factor-> Array ;");
        gsArray.add("Factor-> Num ;");
        gsArray.add("Factor-> Bool_value ;");
        gsArray.add("Factor-> ! ( Bool ) ;");
        gsArray.add("HExpr-> HExpr HMath_op Factor ;");
        gsArray.add("HExpr-> Factor ;");
        gsArray.add("LExpr-> LExpr LMath_op HExpr ;");
        gsArray.add("LExpr-> HExpr ;");
        gsArray.add("Rel-> Rel Judge_op LExpr ;");
        gsArray.add("Rel-> LExpr ;");
        gsArray.add("HRel-> HRel HLogic_op M Rel ;");
        gsArray.add("HRel-> Rel ;");
        gsArray.add("Bool-> Bool LLogic_op M HRel ;");
        gsArray.add("Bool-> HRel ;");
        gsArray.add("M-> ε ;");
        gsArray.add("N-> ε ;");
        gsArray.add("Letter->A");
        gsArray.add("Letter->B");
        gsArray.add("Letter->C");
        gsArray.add("Letter->D");
        gsArray.add("Letter->E");
        gsArray.add("Letter->F");
        gsArray.add("Letter->G");
        gsArray.add("Letter->H");
        gsArray.add("Letter->I");
        gsArray.add("Letter->J");
        gsArray.add("Letter->K");
        gsArray.add("Letter->L");
        gsArray.add("Letter->M");
        gsArray.add("Letter->N");
        gsArray.add("Letter->O");
        gsArray.add("Letter->P");
        gsArray.add("Letter->Q");
        gsArray.add("Letter->R");
        gsArray.add("Letter->S");
        gsArray.add("Letter->T");
        gsArray.add("Letter->U");
        gsArray.add("Letter->V");
        gsArray.add("Letter->W");
        gsArray.add("Letter->X");
        gsArray.add("Letter->Y");
        gsArray.add("Letter->Z");
        gsArray.add("Letter->a");
        gsArray.add("Letter->b");
        gsArray.add("Letter->c");
        gsArray.add("Letter->d");
        gsArray.add("Letter->e");
        gsArray.add("Letter->f");
        gsArray.add("Letter->g");
        gsArray.add("Letter->h");
        gsArray.add("Letter->i");
        gsArray.add("Letter->j");
        gsArray.add("Letter->k");
        gsArray.add("Letter->l");
        gsArray.add("Letter->m");
        gsArray.add("Letter->n");
        gsArray.add("Letter->o");
        gsArray.add("Letter->p");
        gsArray.add("Letter->q");
        gsArray.add("Letter->r");
        gsArray.add("Letter->s");
        gsArray.add("Letter->t");
        gsArray.add("Letter->u");
        gsArray.add("Letter->v");
        gsArray.add("Letter->w");
        gsArray.add("Letter->x");
        gsArray.add("Letter->y");
        gsArray.add("Letter->z");
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
        gsArray.add("Non_digit->1");
        gsArray.add("Non_digit->2");
        gsArray.add("Non_digit->3");
        gsArray.add("Non_digit->4");
        gsArray.add("Non_digit->5");
        gsArray.add("Non_digit->6");
        gsArray.add("Non_digit->7");
        gsArray.add("Non_digit->8");
        gsArray.add("Non_digit->9");
        gsArray.add("Num->Num Digit");
        gsArray.add("Num->Non_digit");
        gsArray.add("Identifier->Identifier Digit");
        gsArray.add("Identifier->Identifier Letter");
        gsArray.add("Identifier->Letter");
        gsArray.add("Bool_value->true");
        gsArray.add("Bool_value->false");
        gsArray.add("LArray->Type Letter");
        gsArray.add("Assignment->LArray=Bool");
        gsArray.add("Assignment->LArray Self_op");
        gsArray.add("Assignment->Self_op LArray");
        gsArray.add("ForAssignment->LArray=Bool");
        gsArray.add("ForAssignment->LArray Self_op");
        gsArray.add("ForAssignment->Self_op LArray");
//        Program -> Type main() Block
//        Type -> int | bool
//        Block -> { Stmts return Num ; }
//        Decl -> Type Array ;
        //gsArray.add("Array->Identifier [ Num ];");
//        Stmts -> Stmts M Stmt | Stmt
    }
}
