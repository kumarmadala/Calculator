package com.calculator;

//used List to maintain the order
import java.util.ArrayList;
import java.util.List;

public class Calculator {
	String operators[]={"^","*","/","+","-"};// List of operators valid in given expression
	public String evaluate(List<String> exp)
	{
			Double d1;
			Double d2;
			
			//evaluating all the power operations in the expression
			for (int i = 0; i < exp.size(); i++) {
			if(exp.get(i).equals("^")){
				try{
					d1=Double.parseDouble(exp.get(i-1));
					d2=Double.parseDouble(exp.get(i+1));
				}catch(NumberFormatException ne){ // if input is not numeric
					return "Invalid data to process";
				}
				exp.set(i, ""+Math.pow(d1,d2));//calculating power
				exp.remove(i-1);// removing operands
				exp.remove(i);// after evaluation
				i-=2;//adjusting index after removing the operands
			}
			}
			
			//evaluating all multiplication and division operations in the expression
			for (int i = 0; i < exp.size(); i++) {
			if(exp.get(i).equals("*") || exp.get(i).equals("/"))
			{
				try{
					d1=Double.parseDouble(exp.get(i-1));
					d2=Double.parseDouble(exp.get(i+1));
				}catch(NumberFormatException ne){//if input is not numeric
					return "Invalid data to process";
				}
				if(exp.get(i).equals("*")){
					exp.set(i, ""+(d1*d2));//multiplication
				}
				else{
					exp.set(i, ""+(d1/d2));//division
				}
				exp.remove(i-1);//removing operands
				exp.remove(i);//after evaluation
				i-=2;//adjusting index
			}
			}
			
			//evaluating all the addition and subtraction operations in the expression
			for (int i = 0; i < exp.size(); i++) {
				if(exp.get(i).equals("+") || exp.get(i).equals("-"))
				{
					try{
					d1=Double.parseDouble(exp.get(i-1));
					d2=Double.parseDouble(exp.get(i+1));
				}catch(NumberFormatException ne){//if input is not a number
					return "Invalid data to process";
				}
				if(exp.get(i).equals("+")){
					exp.set(i, ""+(d1+d2));//addition
				}
				else{
					exp.set(i, ""+(d1-d2));//subtraction
				}
				exp.remove(i-1);//removing operands
				exp.remove(i);//after evaluation
				i-=2;//adjusting index
			}
	}
		return exp.get(0);//returning the final result
	}

	//checking the expression for valid syntax
	public boolean checkSyntax(List<String> tokens) {
		try{
		for (int i = 0; i < tokens.size(); i++) {
			if(checkOperator(tokens.get(i)))
			{
				if(checkOperator(tokens.get(i+1)) || checkOperator(tokens.get(i-1)))
				{
					return false;
				}
			}
		}
		return true;
		}catch(Exception e)
		{
			return false;
		}
	}
	
	//checking the given string is a valid operator or not 
	public boolean checkOperator(String op)
	{
		for (int i = 0; i < operators.length; i++) {
			if(operators[i].equals(op))
			{
				return true;
			}
		}
		return false;
	}
	
	//breaking the given expression into sequence of operands and operators
public List<String> decomposeExpression(String expression) {
		
		List<String> l=new ArrayList<String>();
		int index=0;
		for (int i = 0; i < expression.length(); i++) {
			if(expression.charAt(i)=='^')
			{
				if(expression.substring(index, i).length()!=0)
				l.add(expression.substring(index, i));//pushing the operand
				l.add("^");//pushing the operator
				index=i+1;//adjusting starting index for reading next element
			}
			else if(expression.charAt(i)=='*')
			{
				if(expression.substring(index, i).length()!=0)
				l.add(expression.substring(index, i));
				l.add("*");
				index=i+1;
			}
			else if(expression.charAt(i)=='/')
			{
				if(expression.substring(index, i).length()!=0)
				l.add(expression.substring(index, i));
				l.add("/");
				index=i+1;
			}
			else if(expression.charAt(i)=='+')
			{
				if(expression.substring(index, i).length()!=0)
				l.add(expression.substring(index, i));
				l.add("+");
				index=i+1;
			}
			else if(expression.charAt(i)=='-')
			{
				if(expression.substring(index, i).length()!=0)
				l.add(expression.substring(index, i));
				l.add("-");
				index=i+1;
			}
		}
		l.add(expression.substring(index, expression.length()));//pushing the last remaining element
		return l;//returning the list with elements in order
	}

	
}
