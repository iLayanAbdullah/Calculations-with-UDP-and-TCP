
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
public class TcpS {
int port = 2003;
double result = 0;
String equ;
ServerSocket socket;
public TcpS() throws IOException {
// Create Server Socket to receive client connections
System.out.println("Server is waiting for a new connection..");
//////////////////// Listening client requests//////////


socket = new ServerSocket(port); // welcome socket
while (true) { // Loop Forever waiting for client connection

// Create Client socket for communications..
Socket connectionSocket = socket.accept();
System.out.println("Server is accepting a new Client..");
//Create a new instance of the CalcRunnable class
Runnable rr = new CalcRunnable(connectionSocket);
//Start a new thread for each client connection
Thread thread = new Thread(rr);
thread.start();
} // end while
}// end constructor
public static void main(String args[]) throws IOException 
{
TcpS s = new TcpS();
}
}
///////////////////////////////////Multi Threading/////////////
class CalcRunnable implements Runnable {
private Socket cS;
public CalcRunnable(Socket cs) {
this.cS = cs;
}
@Override
public void run() {
try {
BufferedReader inFromClient = new BufferedReader(
new InputStreamReader(cS.getInputStream()));
DataOutputStream outC = new
DataOutputStream(cS.getOutputStream());
System.out.println("Waiting for Equation..");
while (true) {
String eq = inFromClient.readLine();
System.out.println("Received Equation: " + eq.trim());
if (eq.equalsIgnoreCase("Q")) {
System.out.println("Connection Terminated..");
break;
}
//5 + 2
StringTokenizer t = new StringTokenizer(eq);
String val1 = t.nextToken().trim();
String operation = t.nextToken().trim();
String val2 = t.nextToken().trim();
double A = Double.parseDouble(val1);
double B = Double.parseDouble(val2);
double result = 0;
if (operation.equals("+"))
result = A + B;
else if (operation.equals("-"))
result = A - B;
else if (operation.equals("*"))
result = A * B;
else if (operation.equals("/"))
result = A / B;
else if (operation.equals("%"))
result = A % B;
String outR = String.valueOf(result);
outC.writeBytes(outR + '\n');
System.out.println("Submi answer: " + outR);
}
} catch (IOException e) {
// Handle exceptions
System.out.println(" Close Connection ");
}
}//end run
}//end CalcRunnable
