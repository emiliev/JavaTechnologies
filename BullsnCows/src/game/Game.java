package game;

import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		
		final int DIGIT_COUNT = 8;
		Number generatedNumber = new Number(DIGIT_COUNT);
		
		boolean success = false;
		
		Scanner scan = new Scanner(System.in);
		
		while(!success){
			
			System.out.println("Enter your guess: ");
			String userInput = scan.nextLine();
			if(userInput == null || userInput.length() != DIGIT_COUNT){
				
				System.out.println("Wrong input!");
				continue;
			}
			
			
			boolean correctInput = true;
			for(int i = 0; i < DIGIT_COUNT; ++i){
				
				if(!Character.isDigit(userInput.charAt(i))){
					correctInput = false;
					break;
				}
			}
			
			if(!correctInput){
				
				continue;
			}
			
			int[] userDigits = new int[DIGIT_COUNT];
			for(int index = 0; index < userInput.length(); ++index){
				
				userDigits[index] = Integer.parseInt(userInput.substring(index,index+1));
			}
			
			Number guess = new Number(userDigits);
			
			if(generatedNumber.equals(guess)){
				
				success = true;
			}
			else{
				
				Answer ans = NumberMatcher.match(generatedNumber, guess);
				System.out.println(ans);
			}	
		}
	
	scan.close();
	}
}
