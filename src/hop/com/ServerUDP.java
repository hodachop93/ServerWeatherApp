package hop.com;

import java.io.IOException;
import java.net.*;

public class ServerUDP {
	static final int port = 8876;

	public static void main(String[] args) throws Exception {
		byte[] receiveData = new byte[1024];
		DatagramSocket socket = new DatagramSocket(port);
		while (true) {
			DatagramPacket packet = new DatagramPacket(receiveData,
					receiveData.length);
			socket.receive(packet);
			new Process(socket, packet).start();
		}
	}

}

class Process extends Thread {
	DatagramSocket socket;
	DatagramPacket packet;

	public Process(DatagramSocket socket, DatagramPacket packet) {
		this.socket = socket;
		this.packet = packet;
	}

	@Override
	public void run() {
		byte[] sendData = new byte[100];
		String output;
		String input = new String(packet.getData()).substring(0,
				packet.getLength());
		System.out.println(input);
		String[] elements = input.split("-");
		System.out.println(elements[0]);
		if (elements[0].equals("CityName")) {
			output = RemoteFetch.getJSON(elements[1]);
			System.out.println("vao day roi");
		}
		else {
			System.out.println("ko vao day");
			System.out.println(elements[1]);
			System.out.println(elements[2]);
			output = RemoteFetch.getJSONLocation(elements[1], elements[2]);
		}
		
		//Gui tra du lieu
		try {
			sendData = output.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length, packet.getAddress(), packet.getPort());
			socket.send(sendPacket);
			byte[] array = output.getBytes();
			String test = new String(array);
			System.out.println(test);
			System.out.println(test.length());
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}

}
