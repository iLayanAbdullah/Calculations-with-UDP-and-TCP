
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.StringTokenizer;
public class UDPServer {
public static void main (String args[]) throws IOException {
double r = 0 ;
int p = 2003;
////////////////////Listening client requests////////////////////////////
DatagramSocket s = new DatagramSocket (p);
byte[] in;
byte[] outb;
DatagramPacket inp;
System.out.println("Waiting for Input..");
while(true) {
in = new byte[1024];
inp = new DatagramPacket(in, in.length);
//////////////Accept Client Requests/////////////////
s.receive(inp);
String q = new String (in,0,in.length);
System.out.println("Input Received: " + q.trim());
if(q.equalsIgnoreCase("Q")) {
System.out.println ("The Connection has been Terminated..");
break;
}
//////////////Receive Client Request//////////////////
//x + y ==> 5 + 2 
StringTokenizer t = new StringTokenizer(q);
String o1 = t.nextToken().trim();
String op = t.nextToken().trim();
String o2 = t.nextToken().trim();
///////////////////////////////Calculate Result//////////////////////
double oa = Double.parseDouble(o1);
double ob = Double.parseDouble(o2);
if (op.equals("+"))
r = oa + ob;
else if (op.equals("-"))
r = oa - ob;
else if (op.equals("*"))
r = oa * ob;
else if (op.equals("/"))
r = oa / ob;
else if (op.equals("%"))
r = oa % ob;
////////////////////Return Result to Client//////////////
outb = new byte[1024];
int clientp = inp.getPort();
InetAddress clientA = inp.getAddress();
String Result = new String (r+"");
outb = Result.getBytes();
DatagramPacket outp = new DatagramPacket 
(outb,outb.length,clientA ,clientp);
s.send(outp);
System.out.println("Sending The Result : " + Result);
}//end while
}//end main
}//end class
