package miniC_Gs;


import java.util.ArrayList;

public class HashMap2 {
	private static ArrayList<String> left;
	private static ArrayList<ArrayList<String>> right;
	private static int num;
	
	
	@Override
	public String toString(){
		StringBuffer temp=new StringBuffer();
		
		for(int i=0;i<left.size();i++){
			temp.append("left: ");
			temp.append(left.get(i)+"\n");
			temp.append("right：");
			for(int j=0;j<right.get(i).size();j++){
				temp.append(right.get(i).get(j)+"\n");
			}
			temp.append("\n");
		}
		String re=temp.toString();
		return re;
		
	}

	
	public int size(){
		return num;
	}
	public HashMap2(){
		super();
		left=new ArrayList<String>();
		right=new ArrayList<ArrayList<String>>();
	}
	
	public boolean containsKey(String charItemRightStr) {
		return left.contains(charItemRightStr);
	}
	
	public ArrayList<String> get(String le){
		return right.get(left.indexOf(le));
	}
	
	public void put(String le,ArrayList<String> ri) {
		left.add(le);
		right.add(ri);
		num++;
	}
	
	public ArrayList<String> getLeft() {
		return left;
	}
	public static void setLeft(ArrayList<String> left) {
		HashMap2.left = left;
	}
	public static ArrayList<ArrayList<String>> getRight() {
		return right;
	}
	public static void setRight(ArrayList<ArrayList<String>> right) {
		HashMap2.right = right;
	}

	
	
}