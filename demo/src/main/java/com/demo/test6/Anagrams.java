package com.demo.test6;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/*
正常的hash：
//	System.out.println("----");
//	System.out.println("ABCDEa123abd".hashCode());  // 165374702
//
//	System.out.println("ABCDFB123abc".hashCode()); //  165374702
而我们实现的是一个类似hash
* */

public class Anagrams {

	final Integer[] primes = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTbale = new HashMap<Long,ArrayList<String>>();;
	
	public Anagrams() {
		buildLetterTable();
	}

	/*
		这个方法应该由类Anagrams的构造函数调用，并且应该构建由以下条目组成的哈希表letterTable:
	* */
	private void buildLetterTable() {
		/*terebra
test
			{a =2, b =3, c =5, d =7, e =11, f =13, g =17, h =19, i =23, j =29, k =31, l =37，
			2 m =41, n =43, o =47, p =53, q =59, r =61, s =67, t =71, u =73, v =79, w =83，
			x =89, y =97, z =101}
		* */
		letterTable = new HashMap<Character,Integer>(){{
			put('a',primes[0]);
			put('b',primes[1]);
			put('c',primes[2]);
			put('d',primes[3]);
			put('e',primes[4]);
			put('f',primes[5]);
			put('g',primes[6]);
			put('h',primes[7]);
			put('i',primes[8]);
			put('j',primes[9]);
			put('k',primes[10]);
			put('l',primes[11]);
			put('m',primes[12]);
			put('n',primes[13]);
			put('o',primes[14]);
			put('p',primes[15]);
			put('q',primes[16]);
			put('r',primes[17]);
			put('s',primes[18]);
			put('t',primes[19]);
			put('u',primes[20]);
			put('v',primes[21]);
			put('w',primes[22]);
			put('x',primes[23]);
			put('y',primes[24]);
			put('z',primes[25]);
		}};
	}

	/*
		这个方法应该计算作为参数传递的字符串s的哈希码，并且应该将这个单词添加到散列表anagramTable中。
	* */
	private void addWord(String s) {
		System.out.println(s+"<----------");
		Long aLong = myHashCode(s);
		anagramTbale.get(aLong);

		if(anagramTbale.get(aLong) == null){
			anagramTbale.put(aLong,new ArrayList<>());
		}
		anagramTbale.get(aLong).add(s);
	}
	/*
		这个方法，给定一个字符串s，应该计算它的哈希码。
	* */
	private Long myHashCode(String s) {
		Long lon = 1l;
		char[] chars = s.toCharArray();
		for (char ch:chars) {
			lon *= letterTable.get(ch);
		}
		return lon;
	}


	/*
		主要方法是processFile，它接收包含单词的文本文件的名称，每行一个单词，并构建散列表anagramTable。
		为此，它使用addWord方法。
	* */
	private void processFile(String s) throws IOException{
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while((strLine = br.readLine()) != null) {
			this.addWord(strLine);
		}
		br.close();
	}


	/*
		此方法应返回anagramTable中数量最大的项字谜。它返回它们的一个列表，因为可能有一个以上的字谜列表最大尺寸。
		它将由main方法调用		Map<Long,ArrayList<String>> anagramTbale
	* */
	private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries(){
		Long temp = 0l;
		for (Map.Entry<Long,ArrayList<String>> entry : anagramTbale.entrySet()) {
			if(entry.getKey()>temp){
				temp = entry.getKey();
			}
		}
		ArrayList strings = this.anagramTbale.get(temp);


		return new ArrayList(){{
			add(new HashMap(){}
			);
		}};
	}



	/*
		主方法将读取文件中的所有字符串，并将它们放入字谜的哈希表中
		然后遍历哈希表，以报告哪些单词的字谜数量最多。
		注意，它引用了一个名为words_alpha.txt的文件。
		这个文件包含一个单词字典;
	* */
	public static void main (String[] args){

		Anagrams anagrams = new Anagrams();

		//记录开始时间
		final long startTime = System.nanoTime();

		try {
			//anagrams.processFile ("words_alpha.txt");//按道理这样就可以
			/*
				我记得是有个相对路径的概念的  ../ 是回退一层  /进入下一层
				但是怎么就试不出来呢 先写个绝对路径应付一下
			* */
			anagrams.processFile ("H:\\workspaceForIdea\\demo\\src\\main\\java\\com\\demo\\test6\\words_alpha.txt");

//			anagrams.processFile ("TestWord.txt");//照我想 同路径下直接就能读到才对 可惜 失败 你用eclipse试试 说不定可以 不行就试试下面几个
//			anagrams.processFile ("/TestWord.txt");//失败
//			anagrams.processFile ("../test6/TestWord.txt");//失败
//			anagrams.processFile ("..\\test6\\TestWord.txt");//失败

			/*
				但要是web项目启 这个就应该是正确的 是项目里的相对路径
				若是不对你就试试下面几个 和编译器可能也有关系
			* */
//			anagrams.processFile ("com/demo/test6/TestWord.txt");

//			anagrams.processFile ("/com/demo/test6/TestWord.txt");//失败
//			anagrams.processFile ("\\com\\demo\\test6\\TestWord.txt");//失败
//			anagrams.processFile ("com\\demo\\test6\\TestWord.txt");//失败

		} catch ( IOException e1 ) {
			e1.printStackTrace ();
		}
		//System.out.println(anagrams.anagramTbale.size()+"<---size--");//我们的hash表size

		ArrayList<Map.Entry<Long,ArrayList<String>> > maxEntries = anagrams.getMaxEntries();

		//计算结束时间
		final long estimatedTime = System.nanoTime() - startTime ;
		final double seconds = (( double ) estimatedTime /1000000000);
		//格式输出
		Scanner sc = new Scanner(System.in);


		boolean falg = true;
		do{
			String next = sc.next();
			if("exit".equals(next)){
				falg = false;
			}
			Long aLong = anagrams.myHashCode(next);
			System.out.println("输入-->"+next+"其hash值-->"+aLong);
			for (String str:anagrams.anagramTbale.get(aLong) == null? new ArrayList<String>(){{add("空,没有对应的值");}}:anagrams.anagramTbale.get(aLong)) {
				System.out.println(str);
			}
		}while (falg);

		System.out.println("Elapsed Time :"+ seconds);
		System.out.println("Key of max anagrams : ");
		System.out.println("List of max anagrams : " + maxEntries);
		System.out.println("Length of list of max anagrams : " + maxEntries.size());


		/*
Elapsed Time : 0.689135767
Key of max anagrams : 236204078
List of max anagrams : [ alerts , alters , artels , estral , laster , lastre ,
rastle , ratels , relast , resalt , salter , slater , staler , stelar , talers ]
Length of list of max anagrams : 15
		* */
	}

}
