package game;

import java.util.Arrays;
import java.util.Random;

public class Number {
	
	private int[] digits;
	
	public Number(int digitCount) {
	
		this(generateRandomDigits(digitCount));
	}
	

	public Number(int[] digits){
		
		this.digits = digits;
	}

	public int[] getDigits(){
		
		return Arrays.copyOf(digits,  digits.length);
	}
	
	private static int[] generateRandomDigits(int digitCount) {
		
		int[] generatedDigits = new int[digitCount];
		Random rand = new Random();
		
		int index = 0;
		while(index != digitCount){	
			int genDigit = rand.nextInt(10);
			
			boolean alreadyExists = false;;
			for(int existingDigit: generatedDigits){
				
				if(genDigit == existingDigit){
					
					alreadyExists = true;
					break;
				}
			}
			if(!alreadyExists){
				
				generatedDigits[index] = genDigit;
				index++;
			}
		}
		
		return generatedDigits;
	}

	@Override
	public boolean equals(Object obj) {
	
		if(!(obj instanceof Number)){
			
			return false;
		}
		
		Number otherNumber = (Number) obj;
		return Arrays.equals(digits, otherNumber.digits);
	}

}
