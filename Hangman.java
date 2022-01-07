package josh;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.net.*;
import java.io.*;

class Hangman
{

	public static String getString(String urlString)throws Exception      // method to return the word fetched from the api 
	{
		URL url= new URL(urlString);
		String result;
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) 
	    {
	    	result=reader.readLine();
        }
	    
	    return result.substring(2,result.length()-2);
    }
		
	
	
	public static void printWelcomeMessage()							// method to print welcome message and rules of the game
	{
		System.out.println("\t\tWELCOME TO HANGMAN GAME\n\n");
		System.out.println("Hangman is a popular word guessing game where the player attempts to build a missing word by guessing one letter at a time\n\n");
		System.out.println("RULES OF THE GAME\n=================");
		System.out.println("1-> you are given a random word to guess");
		System.out.println("2-> you will get only 5 lives to play the game");
		System.out.println("3-> Initially you are shown “_” (underscore) for each letter in the word. For example, if the word is “banana”, you will see “_ _ _ _ _ _”");
		System.out.println("4-> For every correct letter guess, the “_” (underscore) is replace with the letter. For example, if you guessed “n”, you will see “_ _ n _ n _”");
		System.out.println("5-> For every incorrect guess, your life reduces by 1");
		System.out.println("6-> The game ends when you guess the word correctly or there are no lives remaining.");
	}
	
	
	
	public static void printWord(char[] word)						   // method to print underscores and the letters guessed right	
	{
		for(char letter:word)
			System.out.print(letter+" ");
			
	}
	
	public static void mapLettersWithIndexes(String word,int length_of_word, HashMap<Character,List<Integer>> letter_index_list)  //stores alphabets present in word with their indexes
	{
		for(int i=0;i<length_of_word;i++)
			
		{
		
			if(letter_index_list.containsKey(word.charAt(i)))
			{
				letter_index_list.get(word.charAt(i)).add(i);
			}
			else
			{
				List<Integer> index_list= new ArrayList<>();
				index_list.add(i);
				letter_index_list.put(word.charAt(i),index_list);
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception			  // main method	
	{
		printWelcomeMessage();										
		
		Scanner scanner= new Scanner(System.in);
		
		int choice;
		
		do
		{
		
			String url="https://random-word-api.herokuapp.com/word?number=1";
		
			String word=getString(url);
		
			boolean flag=false;										// flag to check whether player wins or looses
		
			char[] letter_array= word.toCharArray();				
		
			int length_of_word=word.length();
		
			HashMap<Character,List<Integer>> letter_index_list= new HashMap<>();	// HashMap to store letters present in word and their index list
		
			Set<Character> character_set= new HashSet<>();							// Set of characters guessed right by player
		
			mapLettersWithIndexes(word,length_of_word,letter_index_list);
		
			Arrays.fill(letter_array, '_');
		
			printWord(letter_array);
			System.out.println();
		
			String input;
			int wrong_input_count=0;
		
			while(wrong_input_count<5)
			
			{
				System.out.println("plz enter your choice");
				
				input=scanner.next();
				input=input.toLowerCase();
			
				char input_character=input.charAt(0);
	
				if(input.length()>1)
				{
					wrong_input_count++;
					System.out.println("allowed length of input is 1\n");
				}
			
				else if(!(input_character>='a'&&input_character<='z'))
				{
					System.out.println("invalid input!! plz enter an alphabet\n");
					wrong_input_count++;
				}
		
				else
				{
					if(letter_index_list.containsKey(input_character))
					{
						if(!character_set.contains(input_character))
						{
							System.out.println("good job ! \n");
						
							List<Integer> index_list=letter_index_list.get(input_character);
						
							for(Integer it:index_list)                                 
							letter_array[it]=input_character;
						
							character_set.add(input_character);
						}
						else
						{
							System.out.println("\ncharacter already entered Plz make another choice\n ");
						}
					}
				
					else
					{
						System.out.println("wrong input\n");
						wrong_input_count++;
					}
				}
			
				printWord(letter_array);
			
				System.out.print("\t\t\tLives left"+(5-wrong_input_count));
				System.out.println();
			
				if(character_set.size()==letter_index_list.size())
				{
					System.out.println("congrats you guessed it right the word was "+word);
					flag=true;
					break;
				}
			}
			
			if(!flag)
				System.out.println("you lost better luck next time\n the word was "+word);
		
			System.out.println("press y to continue playing or other key to exit ");
			
			choice=scanner.next().charAt(0);
		
		}while(choice=='y'||choice=='Y');
	
		scanner.close();
	}
}


