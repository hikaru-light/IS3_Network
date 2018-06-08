import java.net.*;
import java.io.*;

public class UdpReceive {
	UdpReceive() {
		byte buffer[] = new byte[40];
		try {
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			DatagramSocket dsocket = new DatagramSocket(4321);
			while (true) {
				dsocket.receive(packet);
				String name = new String(buffer, 0, 8);
				String message = new String(buffer, 8, 32);

				System.out.println("From :" + name);
				System.out.println(message);
			}
		} catch (IOException e) {
			System.err.println("" + e);
		}
	}

	public static void main(String args[]) {
		new UdpReceive();
	}
}
