package com.test;

import java.util.List;
import java.util.Scanner;

import com.calculator.Calculator;

public class TestCalculator {

	public static void main(String[] args) {
		System.out.print("Enter an expression to evaluate:\n");
		Scanner scanner=new Scanner(System.in);
		String expression=scanner.nextLine();
		scanner.close();
		Calculator c= new Calculator();//creating object for calculator class
		List<String> tokens=c.decomposeExpression(expression);// breaking down the expression based on the operators 
		if(c.checkSyntax(tokens)) //checking the expression is valid or not
		{
			System.out.println("The result="+c.evaluate(tokens));//evaluating the expression if it has valid syntax
		}
		else{
			System.out.println("Invalid Syntax");
		}
		
	}
}