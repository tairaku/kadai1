import java.io.*;
import java.util.*;

/*
 * 
 zisyo.txtを読み込み、
 zisyo2.txtは「文字列　ソートした文字列」の形で、ソートした文字列を大きい順に並び替えするプログラム。
 
 ＜方針＞
 ファイルを１行ずつ読み込み、単語の中に大文字があったら小文字に変更して（BigtoSmall）、
 文字列内をソート（sort）する。
 StrSteingの中には、文字列の長さ(le)、元の文字列(s1)、ソートした文字列(s2)入っている。
 文字列の長さが長い順に上からファイルに書き込む。
 *
 */

public class MakeZisyo {

	public static void main(String[] args) {
		
		int size = 235886; //辞書の中の文字の数
		StrString[] array = new StrString[size]; 
		
		MakeZisyo outer = new MakeZisyo();
		int i=0;
		int length = 0;
		
		//ファイルを読み込む
		String inputFileName = "data/zisyo.txt"; // /usr/share/dict/words
		String outputFileName2 = "data/zisyo2.txt"; //変更後のファイル(zisyo2.txt)
		
		try{			
			BufferedReader br = new BufferedReader(new FileReader(inputFileName));
			BufferedWriter bw2 = new BufferedWriter(new FileWriter(outputFileName2));
			
			/*「単語　ソートしたもの」を１行として書き込む*/
			String line = "";
			i=0;
		    length = 0;
			while((line = br.readLine()) != null){
				length = line.length();

				array[i] = outer.new StrString(length,line,sort(BigtoSmall(line)));
				
				i++;
			}
			
			
			StrStringComparator comparator = outer.new StrStringComparator(StrStringComparator.LEN);
			
			/*並び替え*/
			Arrays.sort(array, comparator);
								
			//ファイル書き込み
			for(int k=0;k<size;k++){
				bw2.write(array[k].s1);
				bw2.write("	");
			    bw2.write(array[k].s2);
				bw2.newLine();
			}
			
			//ファイルを閉じる
			br.close(); 
			bw2.close();

		}catch(IOException e){
			System.out.println("Error");
		}
		
	}
	
	public static String BigtoSmall(String big){ //大文字があったら小文字にする
		char[] small = big.toCharArray();
		int strlen = big.length();
		for(int k=0;k<strlen;k++){
			if(Character.isUpperCase(small[k]) == true){
				small[k] = Character.toLowerCase(small[k]);
			}	
		}
		return new String(small);
	}
	
	public static String sort(String before){ //ソートする
		char[] after = before.toCharArray();
		java.util.Arrays.sort(after);
		return new String(after);		
	}
	
	
	class StrStringComparator implements Comparator<StrString>{
		private final int key;
		public final static int LEN = 0;
		public final static int STR1 = 1;
		public final static int STR2 = 2;
		
		public StrStringComparator(int key){
			super();
			this.key =key;
		}
		
		/*比較*/
		public int compare(StrString o1,StrString o2){ 
			switch(key){
				default:
				case LEN:
					return o2.le.compareTo(o1.le); //大きい順
				case STR1:
					return o1.s1.compareTo(o2.s1);
				case STR2:
					return o1.s2.compareTo(o2.s2);
			}
		}

	}

	
	class StrString{ //文字列の長さ、元の単語、ソートされた単語
		Integer le;
		String s1;
		String s2;
		
		public StrString(Integer le,String s1,String s2){
			super();
			this.le =le;
			this.s1 =s1;
			this.s2 =s2;
		}
	}
	
	

}
