package edu.cnu.ascii.base;

public class Base64 {

	private final static int SizeSix = 6;
	private final static int SizeEight = 8;

	private final static String Upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final static String Lower = "abcdefghijklmnopqrstuvmxyz";
	private final static String Number = "1234567890";
	private final static String Spec = "+/";

	private final static String Base64 = Upper + Lower + Number + Spec;

	private static int[] sumOfBinaryString(String binary) {
		char[] bin = binary.toCharArray();
		int[] derp = new int[bin.length];

		for (int i = 0; i < bin.length; i++) {
			if (bin[i] == '0') {
				derp[i] = 0;
			} else if (bin[i] == '1') {
				derp[i] = binary(bin.length - i - 1);
			} else {
				throw new NullPointerException(
						"The String must contain only '0' or '1'");
			}
		}

		return derp;
	}

	private static int binary(int pwr) {
		if (pwr < 0) {
			throw new NullPointerException("Must be positive (+)");
		} else {
			if (pwr > 0) {
				return binary(pwr - 1) * 2;
			}
		}
		return 1;
	}

	private static int sumOfArray(int[] binAry) {
		int sum = 0;

		for (int i = 0; i < binAry.length; i++) {
			sum += binAry[i];
		}

		return sum;
	}

	public static String toBase64(String base) {
		base = toBinary(base);
		if (base.length() % SizeSix == 0) {
			return holder(base);
		} else if (base.length() % 6 == 2) {
			base += "0000";
			return holder(base) + "==";
		} else if (base.length() % 6 == 4) {
			base += "00";
			return holder(base) + "=";
		} else {
			System.err.println("Wait what?");
			return "Dat Fqu";
		}
	}

	private static String holder(String base) {
		int div = base.length() / SizeSix;
		char[][] six = new char[div + 1][SizeSix];

		for (int i = 0, j = 0, k = 0; k < base.length(); i++, k++) {
			if (i >= SizeSix) {
				j++;
				i = 0;
			}

			six[j][i] = base.charAt(k);
		}

		String[] end = new String[div];

		for (int i = 0; i < end.length; i++) {
			end[i] = "";
			for (int j = 0; j < SizeSix; j++) {
				end[i] += six[i][j];
			}
		}

		return converting(end);
	}

	private static String converting(String[] end) {
		String conver = "";

		for (int i = 0; i < end.length; i++) {
			int bin = sumOfArray(sumOfBinaryString(end[i]));
			conver += Base64.charAt(bin);
		}

		return conver;
	}

	private static String toBinary(String testWord) {
		String letters = "";
		char[] word = testWord.toCharArray();
		
		for(int i = 0; i < word.length; i++){
			letters += toBinary((int) word[i], SizeEight);
		}
		
		return letters;
	}
	
	private static String toBinary(int num, int size){
		String temp = "";
		
		for(int i = size - 1; i > -1; i--){
			int bin = binary(i);
			if(num >= bin){
				temp += "1";
				num = num - bin;
			} else {
				temp += "0";
			}
		}
		
		return temp;
	}
	
	public static String toWords(String base){
		String word = "";
		int lenght = base.length();
		
		char last = base.charAt(lenght - 1), secL = base.charAt(lenght - 2);
		
		if(last == '=' && secL != '='){
			word = base.substring(0, lenght - 2);
		} else if(last == '=' && secL == '=') {
			word = base.substring(0, lenght - 4);
		} else {
			word = base;
		}
		
		int div = word.length() / SizeEight;
		char[][] tmp = new char[div][SizeEight];
		
		for(int i = 0, j = 0, k = 0; k < word.length(); i++, k++){
			if( i >= SizeEight ){
				i = 0;
				j++;
			}
			
			tmp[j][i] = word.charAt(k);
		}
		
		String[] end = new String[div];
		
		for(int i = 0; i < end.length; i++){
			end[i] = "";
			for(int j = 0; j < SizeEight; j++){
				end[i] += tmp[i][j];
			}
		}
		
		word = "";
		
		for(int i = 0; i < end.length; i++){
			int sum = toNumFromString(end[i]);
			word += (char) sum;
		}
		
		return word;
	}
	
	private static int toNumFromString(String binary){
		int sum = 0;
		char[] all = binary.toCharArray();
		
		for(int i = 0; i < all.length; i++){
			if(all[i] == '1'){
				sum += binary(all.length - 1 - i);
			}
		}
		
		return sum;
	}
	
	public static String fromBase64(String bianry){
		int lenght = bianry.length() - 1;
		char last = bianry.charAt(lenght), secL = bianry.charAt(lenght - 1);
		
		char[] derp = bianry.toCharArray();
		String theEnd = "";
		
		for(int i = 0; i < derp.length; i++){
			int temp = Base64.indexOf(derp[i]);
			theEnd += toBinary(temp, SizeSix);
		}
		
		theEnd = toWords(theEnd);
		
		if(last == '=' && secL != '='){
			theEnd = theEnd.substring(0, lenght - 2);
		} else if(last == '=' && secL == '='){
			theEnd = theEnd.substring(0, lenght - 3);
		}
		
		return theEnd;
	}
	
}
