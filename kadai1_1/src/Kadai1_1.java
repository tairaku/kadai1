import java.io.*;

/*
 与えられた16文字からなるべく長い単語を出力するプログラム。
 
 ＜方針＞
 与えられた16文字の英単語(str)の中に大文字があったら小文字に変更する(str_small)。
 (str_small=BigtoSmall(str))
 与えられた英単語(str_small)の中のa、b、c、…、z、a~z以外の個数をそれぞれ数えて配列strabcdにいれる。
（strabcde = count(str_small)）
 MakeZisyo.javaで作られたファイルを読み込み、辞書内の単語もa~zの個数を数えて
 上から順に文字数を比較し、それぞれの個数が与えられた単語より全て少なかったら、発見。
 それを出力。
 */

public class Kadai1_1 {
	
	public static void main(String[] args) {
		
		int size = 235886;
		
		String str = "JEANPIErre-xxxxx";
		
		String str_small = BigtoSmall(str);   //大文字があったら小文字にする
		String[] sss = new String[2];
		String[][] zisyo = new String[2][size];
		int[] abcde  = new int[27];
		int[] strabcde = new int[27];
		int length = str_small.length();
		int length16 =16;  //与えられた文字列が16と決まっているのでエラー処理するため設定。
		
		if(length != length16){ //16文字でなかったらエラー！
			System.out.println(length16+"文字入れて下さい！");
			System.exit(-1);
		}
		strabcde = count(str_small); //a~z,z以外の数を数える。
		int i=0;
		//System.out.println(length);
		
		//ファイル読み込み
		String FileName = "data/zisyo2.txt";
		try{
			BufferedReader br = new BufferedReader(new FileReader(FileName));
			
			/* 
			 1行ずつ読み込み、２次元配列に入れる。
			  */
			String line3 = "";
			while((line3 = br.readLine()) != null){
				sss = line3.split("	",0);		
				zisyo[0][i] = sss[0];
				zisyo[1][i] = sss[1];				
				i++;				
			}
			//ファイル閉じる
			br.close();
		}catch(IOException e){
			System.out.println("Error");
		}
		
		
		int le = zisyo[0][0].length();
		int first=0; 
		while(le != length16){ //文字の長さがlength16のもののうち一番上にあるものの位置を探索。
			first++;
			le = zisyo[0][first].length();
		}
		//System.out.println(first);
		
		int count = first;
		int ch=0;
		while(count < size){
			abcde = count(zisyo[1][count]);
			/*
			 入力文字列(strabcde)のa~zの個数を辞書に入ってる文字列(abcde)のa~zの個数と比べて、
			 abcde内の個数の方が多かったらbreak。	
			 */		  
			for(ch=0;ch<27;ch++){
				if(abcde[ch] > strabcde[ch]){
					break;
				}
			}
			if(ch ==27){ //a~zの個数が全部、入力文字の方が少なかったら発見！！
				System.out.println(zisyo[0][count]);
				break;
			}
			
			count++;
		}

	}
		

	
	/*
	 文字内のアルファベット数を数える
	 abc[0]=aの個数、abc[1]=bの個数、abc[2]=bの個数、…　、abc[26]=a~z以外("-"など)の個数
	 */
	public static int[] count(String input){ 
		int[] abc = new int[27];
		char[] charArray = input.toCharArray();
		int p;
		for(int i=0;i<charArray.length;i++){
			p = charArray[i];
			if(p>='a' && p <='z'){ //a~z
				abc[p-'a']++;
			}
			else{
				abc[26]++;  //a~z以外
			}
		}
		return abc;
	}
	
	
	/*
	 文字の中に大文字があったら、小文字に
	 */
	public static String BigtoSmall(String big){ 
		char[] small = big.toCharArray();
		int strlen = big.length();
		for(int k=0;k<strlen;k++){
			if(Character.isUpperCase(small[k]) == true){
				small[k] = Character.toLowerCase(small[k]);
			}	
		}
		return new String(small);
	}
	
	

}
