package com.exercise9.util;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class InputUtil {
	public static Integer inputOptionCheck(Integer maxValue) {
		Scanner userInput = new Scanner(System.in);
		String inputValue = new String();
		Integer output = new Integer(0);

		while (true){
			inputValue = userInput.nextLine();
			
			try{
				output = Integer.parseInt(inputValue);
				
				if ((output < 1) || (output > maxValue)) {
					System.out.print("Input invalid. Choose another: ");
				} else {
					break;
				}

			} catch (NumberFormatException ne) {
				System.out.print("Input not a number, please provide another: ");
			}
		}

		return output;
	}

	public static Integer inputOptionCheck() {
		Scanner userInput = new Scanner(System.in);
		String inputValue = new String();
		Integer output = new Integer(0);

		while (true){
			inputValue = userInput.nextLine();
			
			try{
				output = Integer.parseInt(inputValue);
				
				if (output < 1) {
					System.out.print("Input invalid. Choose another: ");
				} else {
					break;
				}

			} catch (NumberFormatException ne) {
				System.out.print("Input not a number, please provide another: ");
			}
		}

		return output;
	}	

	public static String getRequiredInput() {
		Scanner userInput = new Scanner(System.in);
		String output = userInput.nextLine();

		while(true) {
			if(output.isEmpty()) {
				System.out.print("Input is blank.\nInput another value: ");
				output = userInput.nextLine();
			} else if(output.length() > 255)  {
				System.out.print("Input can only be 255 characters long.\nInput another value: ");
				output = userInput.nextLine();
			} else {
				break;
			}
		}
		return output;
	}

	public static String getOptionalInput() {
		Scanner userInput = new Scanner(System.in);
		String output = userInput.nextLine();

		while(true) {
			if(output.length() > 255) {
				System.out.print("Input can only be 255 characters long.\nInput another value: ");
				output = userInput.nextLine();				
			} else {
				break;
			}
		}
		return output;	
	}


	public static Boolean checkDate(String input) {
		Date date = null;
		Boolean flag = true;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);

			try {
				date = format.parse(input);
			} catch(ParseException pe) {
				flag = false;
			}
		return flag;
	}

	public static Date getDate(String input) {
		Date date = null;
		Boolean flag = true;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);

			try {
				date = format.parse(input);
			} catch(ParseException pe) {
				flag = false;
				System.out.print("Date or format incorrect. Input another: ");
			}
		return date;
	}

	public static Boolean getStatus() {
		Scanner userInput = new Scanner(System.in);
		String input = new String();
		Boolean output = false;

		input = userInput.nextLine();

		while(true) {
			if(input.equalsIgnoreCase("Y")){
				output = true;
				break;
			} else if (input.equalsIgnoreCase("N")) {
				break;
			} else {
				System.out.print("Input not Y or N. Input another value: ");
				input = userInput.nextLine();
			}
		}

		return output;
	}

	public static Boolean checkGrade(Float grade) {
		
		if ((grade < 1) || grade > 5) {
			return false;
		} else {
			return true;
		}
	}	
}