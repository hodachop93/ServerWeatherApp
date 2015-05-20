package hop.com;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientUDP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[4000];
		Scanner input = new Scanner(System.in);
		
		try {
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName("localhost");
			System.out.println(IPAddress);
			System.out.println("Nhap chuoi: ");
			String sentence = input.nextLine();
			sendData = sentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sentence.length(), IPAddress, 8876);
			clientSocket.send(sendPacket);
			
			DatagramPacket receivePacket = new DatagramPacket(receiveData,
					receiveData.length);
			clientSocket.receive(receivePacket);
			String receivedSentence = new String(receivePacket.getData(), "UTF-8").substring(0, receivePacket.getLength());
			System.out.println(receivedSentence);
			System.out.println(receivedSentence.length());
			System.out.println(receivedSentence.charAt(receivedSentence.length()-2));
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
