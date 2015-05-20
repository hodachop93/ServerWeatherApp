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
		String input = new String(packet.getData()).substring(0,
				packet.getLength());
		String output = RemoteFetch.getJSON(input);
		System.out.println(output);
		
		//Response Data
		
		try {
			//InetAddress ipRespone = InetAddress.getByName("10.0.3.2");
			DatagramPacket sendPacket = new DatagramPacket(output.getBytes(),
					output.length(), packet.getAddress(), packet.getPort());
			socket.send(sendPacket);
			System.out.println(output.length());
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}

}
