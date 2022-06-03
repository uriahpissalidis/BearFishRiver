package proj1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Animal.Gender;

public class River 
{
	//river for the bears and fish
	public Animal[] river;
	public int length;
	public long seed;
	
	private void helper(int l){
		length = l;
		river = new Animal[length];
		
		for (int i = 0; i < length; i++) {
			int k = RandomSingleton.getInstance().nextInt(3);
			
			if(k == 0) river[i] = null;
			else if(k == 1) river[i] = new Bear();
			else river[i] = new Fish();
		}
	}
	
	//constructs a river from a file, otherwise throws a FileNotFoundException
	public River(String inputFileName) throws FileNotFoundException{
		try{
			File f = new File(inputFileName);
			Scanner s1 = new Scanner(f);
			ArrayList<String> x = new ArrayList<String>();
		
			while(s1.hasNext()){
				if(s1.hasNext()) x.add(s1.next());
				else continue;	
			}

			s1.close();
			length = x.size();
			river = new Animal[x.size()];
		
			for(int i=0; i<x.size(); i++){
				if(x.get(i).equals("---")) river[i] = null;
			
				//fish
				else if(x.get(i).charAt(0) == 'F'){

					//female
					if(x.get(i).charAt(1) == 'F'){
						river[i] = new Fish((Integer.parseInt(""+x.get(i).charAt(2))), Gender.FEMALE);
					}
					
					//male
					else{
						river[i] = new Fish((Integer.parseInt(""+x.get(i).charAt(2))), Gender.MALE);
					}
				}	
			
				//bear
				else{

					//female
					if(x.get(i).charAt(1) == 'F'){
						river[i] = new Bear((Integer.parseInt(""+x.get(i).charAt(2))), Gender.FEMALE);
					}
				
					//male
					else{ 
						river[i] = new Bear((Integer.parseInt(""+x.get(i).charAt(2))), Gender.MALE);
					}
				}
			}
		}
		
		catch(FileNotFoundException e)
		{
			System.out.println("File does not exist, Goodbye.");
			System.exit(-1);
		}
	}

	//generates a river of given length with fish and bears
	public River(int length){
		this.length = length;
		helper(this.length);
	}

	//generates a random river of given length where the seed is the
	//random number given
	public River(int length, long seed){
		this.seed = seed;
		this.length = length;
		helper(this.length);
	}

	public int getLength(){
		return length;
	}

	public void setSeed(long seed){
		this.seed = seed;
	}

	//returns number of empty slots in the river
	public int numEmpty(){
		int count = 0;
		for(int i = 0; i < river.length; i++){
			if(river[i] == null) count++;
		}
		return count;
	}

	/*
	if the river has no empty cells, do nothing and return false
	else, add an animal whose age is 0
	*/
	public boolean addRandom(Animal animal){
		animal.age = 0;
		for(int i=0; i<river.length; i++){
			if(river[i] == null){
				river[i] = animal;
				return true;
			}
			else continue;
		}
		return false;
	}

	/*
	if null do nothing.
	if it is an animal and it has reached the end of its lifespan, animal dies
	else, it decides which direction, if any, the animal should move and what
	actions to take
	*/
	public void updateCell(int i) 
	{
		int k = RandomSingleton.getInstance().nextInt(3);
		//k = 0, don't move 
		//if k = 1, move left 
		//if k = 2, move right
		Animal temp = river[i];
		river[i] = null; //sets original position to null
		if(temp == null)
		{}
		else if(k == 0){ //don't move
			river[i] = temp;
		}

		//Bear
		else if(temp.toString().charAt(0) == 'B'){ 
			if(k == 1){ //move left
				if(i-1 >= 0){ //not at beginning of river

					if(river[i-1] == null){
						river[i-1] = temp;
					}
					
					//bear eats fish
					else if(river[i-1].toString().charAt(0) == 'F'){
						river[i-1] = temp;
					}
					
					//if bear
					else if(river[i-1].toString().charAt(0) == 'B'){
						
						//same gender means fight
						if(river[i-1].gender == temp.gender){
						
							//same gender and same strength
							if(((Bear)river[i-1]).getStrength() == ((Bear)temp).getStrength()){
								//draw
								river[i] = temp;
							}

							//same gender and different strength, one bear loses
							else if(((Bear)river[i-1]).getStrength() >= ((Bear)temp).getStrength()){
							}
							
							else{
								river[i-1] = temp;
							}
						}

						//create new bear at destination
						else{
							addRandom(new Bear());
							river[i] = temp;
						}
					}
					else{}
				}

				//at beginning of river
				else{
					if(river[length - 1] == null){//if empty
						river[length - 1] = temp;
					}
					
					//fish dies
					else if(river[length - 1].toString().charAt(0) == 'F'){
						river[length - 1] = temp;
					}
					
					//if bear
					else if(river[length-1].toString().charAt(0) == 'B'){
						
						//same gender
						if(river[length-1].gender == temp.gender){
							
							//same gender and strength
							if(((Bear)river[length-1]).getStrength() == ((Bear)temp).getStrength()){
								
								//nothing happens
								river[i] = temp; 
							}

							else if(((Bear)river[length-1]).getStrength() >= ((Bear)temp).getStrength()){
								//same gender and strength
								//Bear dies, one is stronger than the other
							}
							
							else{
								river[length-1] = temp;
							}
						}
						
						//create new bear and move no where
						else{
							addRandom(new Bear());
							river[i] = temp;
						}
					}
					
					else{}
				}
			}
			
			//move right
			else{ 
				
				//not at end of river
				if(i+1 <= length - 1){

					//if empty
					if(river[i+1] == null){
						river[i+1] = temp;
					}
					
					//fish dies regardless
					else if(river[i+1].toString().charAt(0) == 'F'){
						river[i+1] = temp;
					}
					
					//if bear
					else if(river[i+1].toString().charAt(0) == 'B'){
						
						//same gender
						if(river[i+1].gender == temp.gender){

							//same gender and strength
							if(((Bear)river[i+1]).getStrength() == ((Bear)temp).getStrength()){
								river[i] = temp; //draw
							}
							
							//same gender and higher strength
							else if(((Bear)river[i+1]).getStrength() >= ((Bear)temp).getStrength()){
								//Bear dies because not as strong
							}
							
							else{
								river[i+1] = temp;
							}
						}
						
						//create new bear and move no where
						else{
							addRandom(new Bear());
							river[i] = temp;
						}
					}
					
					else{}
				}
				
				//at end of river
				else {
					
					//if empty
					if(river[0] == null){
						river[0] = temp;
					}
					
					//fish dies
					else if(river[0].toString().charAt(0) == 'F'){
						river[0] = temp;
					}
					
					//if bear
					else if(river[0].toString().charAt(0) == 'B'){
						
						//same gender
						if(river[0].gender == temp.gender){

							//same gender and strength
							if(((Bear)river[0]).getStrength() == ((Bear)temp).getStrength()){
								river[i] = temp; //draw
							}
							
							//bear dies because not as strong
							else if(((Bear)river[0]).getStrength() >= ((Bear)temp).getStrength()){
							}
							
							else{
								river[0] = temp;
							}
						}
						
						//create new bear and move no where
						else{
							addRandom(new Bear());
							river[i] = temp;
						}
					}
					
					else{}
				}
			}
		}
			
		//fish	
		else{
			
			//move left
			if(k == 1){
				
				//if not at beginning of river
				if(i-1 >= 0){
					
					//if empty
					if(river[i-1] == null){
						river[i-1] = temp;
					}
					
					//fish dies 
					else if(river[i-1].toString().charAt(0) == 'B'){
					}
					
					else if(river[i-1].toString().charAt(0) == 'F') //if fish
					{
						if(river[i-1].gender == temp.gender)//same gender
						{
							river[i] = temp; //draw
						}
						
						else //create new fish and move no where
						{
							addRandom(new Fish());
							river[i] = temp;
						}
					}
					
					else
					{
					}
				}
				
				else //at beginning of river
				{
					if(river[length - 1] == null)//if empty
					{
						river[length - 1] = temp;
					}
					
					else if(river[length - 1].toString().charAt(0) == 'B')//fish dies regardless
					{
					}
					
					else if(river[length-1].toString().charAt(0) == 'F') //if fish
					{
						if(river[length-1].gender == temp.gender)//same gender
						{
							river[i] = temp; //do nothing
						}
						
						else //create new fish and move no where
						{
							addRandom(new Fish());
							river[i] = temp;
						}
					}
					
					else
					{
					}
				}
			}
			
			else //move right
			{
				if(i+1 <= length - 1) //if not at end of river
				{
					if(river[i+1] == null)//if empty
					{
						river[i+1] = temp;
					}
					
					else if(river[i+1].toString().charAt(0) == 'B')//fish dies regardless
					{
					}
					
					else if(river[i+1].toString().charAt(0) == 'F') //if fish
					{
						if(river[i+1].gender == temp.gender)//same gender
						{
							river[i] = temp; //do nothing
						}
						
						else //create new fish and move no where
						{
							addRandom(new Fish());
							river[i] = temp;
						}
					}
					
					else
					{
					}
				}
				
				else //at end of river
				{
					if(river[0] == null)//if empty
					{
						river[0] = temp;
					}
					
					else if(river[0].toString().charAt(0) == 'B')//fish dies regardless
					{
					}
					
					else if(river[0].toString().charAt(0) == 'F') //if fish
					{
						if(river[0].gender == temp.gender)//same gender
						{
							river[i] = temp; //do nothing
						}
						
						else //create new fish and move no where
						{
							addRandom(new Fish());
							river[i] = temp;
						}
					}
					
					else
					{
					}
				}
			}
		}
	}

	/**
	 * Perform one cycle of the simulation, going through the cells of the
	 * river, updating ages, moving animals, creating animals, and killing
	 * animals, as explained in the Project Description.
	 */
	public void updateRiver() 
	{
		for(int i = 0; i < length; i++)
		{
			updateCell(i);
		}
		
		for(int j = 0; j < length; j++)
		{
			if(river[j] != null)
			{
				if(river[j].incrAge() == false)
				{
					river[j] = null;
				}
				
				else
				{
				}
			}
			
			else
			{	
			}
		}
	}

	/**
	 * Writes the river to an output file.
	 * 
	 * @param outputFileName
	 *            The name of the output file.
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException 
	{
		try
		{
			BufferedWriter printer = new BufferedWriter(new FileWriter(outputFileName));
			String a = "";
			
			for(int i = 0; i < length; i++)
			{
				if(river[i] == null)
				{
					a += "---";
				}
				
				else
				{
					a += river[i].toString();
				}
				
				a += " ";
			}
			printer.write(a);
			printer.newLine();
			printer.close();
		}
		
		catch(IOException e)
		{
			System.out.println("Sorry, this file does not exist, please try again.");
		}
	}

	/**
	 * Produces a string representation of the river following the format
	 * described in the Project Description.
	 * 
	 * @return The string representation of the river.
	 */
	@Override
	public String toString() 
	{
		String representation = "";
		
		for(int i = 0; i < length; i++)
		{
			if(river[i] == null)
			{
				representation += "---";
			}
			
			else
			{
				representation += river[i].toString();
			}
			
			representation += " ";
		}

		return representation;
	}
}