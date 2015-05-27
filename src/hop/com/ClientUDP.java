package hop.com;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP {
	public static int port = 8876;

	public static void main(String[] args) throws IOException{
		Scanner input = new Scanner(System.in);
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[4000];
		
		String sentence = input.nextLine();
		sendData = sentence.getBytes("UTF-8");
		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sendData.length, IPAddress, port);
		clientSocket.send(sendPacket);
		// Nhan goi gui ve 
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
		
		clientSocket.receive(receivePacket);
		String receivedSentence = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
		System.out.println(receivedSentence);
		System.out.println(receivedSentence.length());
		
	}
}
