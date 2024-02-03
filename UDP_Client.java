
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
public class UDP_Client {
public static void main(String args[]) throws IOException {
Scanner scanner = new Scanner (System.in);
System.out.println("Enter Equation Syntax: number1 operation number2 \n or exit to Exit program..");
InetAddress server_Address = InetAddress.getByName("localhost");
int port = 2003;
byte[] OutBuffer = new byte[1024];
byte[] InBuffer = new byte[1024];
DatagramSocket sock = new DatagramSocket();
while(true) {
String Out_Equation = scanner.nextLine();
if(Out_Equation.equalsIgnoreCase("Q"))
break;
OutBuffer =Out_Equation.getBytes();
DatagramPacket Out_Packet = new
DatagramPacket(OutBuffer,OutBuffer.length, server_Address , port);
sock.send(Out_Packet);
DatagramPacket inPacket = new DatagramPacket
(InBuffer,InBuffer.length);
sock.receive(inPacket);
String R = new String (inPacket.getData() , 0 ,
inPacket.getData().length );
System.out.println("Result :" + R.trim() );
}}}