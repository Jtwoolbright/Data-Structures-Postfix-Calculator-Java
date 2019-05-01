/*        Name: Josh Woolbright
 * Description: This is a driver program that test the calculator for bugs.
 *
 */
public class Main 
{
	public static void main(String[] args) 
	{
		String postfix = Postfix.convertToPostfix("a ^ a"); 
		System.out.println(postfix);                            
		System.out.println(Postfix.evaluatePostfix(postfix));
		
		postfix = Postfix.convertToPostfix("d / (a -a)"); 
		System.out.println(postfix);                            
		System.out.println(Postfix.evaluatePostfix(postfix));
		
		postfix = Postfix.convertToPostfix(""); 
		System.out.println(postfix);                            
		System.out.println(Postfix.evaluatePostfix(postfix));
		
		postfix = Postfix.convertToPostfix("a + a"); 
		System.out.println(postfix);                            
		System.out.println(Postfix.evaluatePostfix(postfix));
		
		postfix = Postfix.convertToPostfix("a - a"); 
		System.out.println(postfix);                            
		System.out.println(Postfix.evaluatePostfix(postfix));
		
		postfix = Postfix.convertToPostfix("a / a"); 
		System.out.println(postfix);                            
		System.out.println(Postfix.evaluatePostfix(postfix));
		
		postfix = Postfix.convertToPostfix("a * a"); 
		System.out.println(postfix);                            
		System.out.println(Postfix.evaluatePostfix(postfix));
		
		postfix = Postfix.convertToPostfix("(a ^ a) / (b * c) - d"); 
		System.out.println(postfix);                            
		System.out.println(Postfix.evaluatePostfix(postfix));
		
		postfix = Postfix.convertToPostfix("a + b / c"); 
		System.out.println(postfix);                            
		System.out.println(Postfix.evaluatePostfix(postfix));
		
	}
}
