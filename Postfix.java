
import java.util.EmptyStackException;

public class Postfix
{
    public static String convertToPostfix(String infix)
    {
        StackInterface<Character> operatorStack = new LinkedStack<>();

        // use a StringBuilder object rather than a String, since appending is much more efficient.
        // To add to the StringBuilder object 'postfix':
        // postfix.append(stringToAppend);
        StringBuilder postfix = new StringBuilder();
        int length = infix.length();
        for(int i = 0; i < length; i++)
        {
            char nextCharacter = infix.charAt(i);
            if(isVariable(nextCharacter))
            {
                postfix.append(nextCharacter);
            }
            else
            {
                switch(nextCharacter)
                {
                	case '^': case '(':
                		operatorStack.push(nextCharacter);
                		break;
                	case ')':
                		char topOperator = operatorStack.pop();
                		while (topOperator != '(')
                		{
                			postfix.append(topOperator);
                			topOperator = operatorStack.pop();
                		}
                		break;
                	case '+': case '-': case '*': case '/':
                		//checks for nonempty stack and compares precedence of nextCharacter 
                		//with the precedence of the operator on top of the stack
                		while (!operatorStack.isEmpty() && getPrecedence(nextCharacter) 
                				<= getPrecedence(operatorStack.peek()))
                		{
                			postfix.append(operatorStack.peek());
                			operatorStack.pop();
                		}
                		operatorStack.push(nextCharacter);
                		break;
                	default:
                        break;
                }
            }
        }
        while (!operatorStack.isEmpty())
        {
        	postfix.append(operatorStack.peek());
        	operatorStack.pop();
        }
        return postfix.toString();
    }
    
    public static int evaluatePostfix(String postfix)
    {
    	StackInterface<Integer> valueStack = new LinkedStack<>();
    	int length = postfix.length();
    	try
    	{
    		for(int i = 0; i < length; i++)
    		{
    			char nextCharacter = postfix.charAt(i);
    			if (isVariable(nextCharacter))
    			{
    			valueStack.push(getValue(nextCharacter));
    			}
    			else if (isOperator(nextCharacter))
    			{
    				int operandOne = valueStack.pop();
    				int operandTwo = valueStack.pop();
    				int result = performOperation(operandOne, operandTwo, nextCharacter);
    				valueStack.push(result);
    			}
    		}
    		return valueStack.peek();
    	}
    	catch (ArithmeticException e) //Dividing by zero
    	{
    		System.out.println(e);
    		return 0;				  //Since this method requires an integer to be returned
    	}							  //I just returned zero
    	catch (EmptyStackException e)
    	{
    		System.out.println(e);
    		return 0;
    	}
    		
    }

    private static int getValue(Character c)
    {
        switch(c)
        {
            case 'a':
                return 2;
            case 'b':
                return 3;
            case 'c':
                return 4;
            case 'd':
                return 5;
            case 'e':
                return 6;
            default:
                return 0;
        }
    }

    private static int performOperation(int operandOne, int operandTwo, char operator)
    {
    	switch (operator)
    	{
    		case '^' :
    			int result = operandTwo;
    			for (int i = 2; i <= operandOne; i++)
    			{
    				result *= operandTwo;
    			}
    			return result;
    		case '*' :
    			return operandOne * operandTwo;
    		case '/' :
    			return operandTwo / operandOne;
    		case '+' :
    			return operandOne + operandTwo;
    		case '-' :
    			return operandTwo - operandOne;
    		default:
    			return 0;
    	}
    }

    private static int getPrecedence(char operator)
    {
        switch (operator)
        {
            case '(': case ')': return 0;
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^':           return 3;
        }
        return -1;
    }

    private static boolean isOperator(char c)
    {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^');
    }

    private static boolean isVariable(Character c)
    {
        return Character.isLetter(c);
    }
}