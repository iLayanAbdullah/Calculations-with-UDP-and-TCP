
import java.io.*;
import java.net.*;
import java.util.*;
public class TcpC {
public static void main(String args[]) throws IOException {
Socket cs = new Socket("localhost", 2003);
Scanner s= new Scanner(System.in);
DataOutputStream dataout = new DataOutputStream(cs.getOutputStream());
BufferedReader br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
while (true) { 
 System.out.println("Enter Equation as: firstoperand operation secondoperand \n or exit to exit ");
 String equation = s.nextLine();
 if (equation.equalsIgnoreCase("exit"))
break;
 dataout.writeBytes(equation + '\n');
 String ans = br.readLine();
 System.out.println("Result is: " + ans.trim());
}
}
}