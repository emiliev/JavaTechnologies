package game;

public class NumberMatcher {

	
	public static Answer match(Number guess, Number original){
		
		int[] digits = original.getDigits();
		int[] guessedDigits = guess.getDigits();
		int bulls = 0;
		int cows = 0;

		for(int index = 0;index < digits.length; ++index ){
			
			for(int j = 0; j < guessedDigits.length; ++j){
				
				if(digits[index] == guessedDigits[j]){
					
					cows++;
				}
			}
			
			if(digits[index] == guessedDigits[index]){
				
				bulls++;
				cows--;
			}
		}

		Answer answer = new Answer(bulls, cows);
		return answer;
	}
}
