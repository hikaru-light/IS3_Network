import java.net.*;
import java.io.*;
import java.util.Arrays;

public class UdpSend{
    
    UdpSend(String args[]){
	if(args.length!=1){
	    System.exit(1);
    }

    String firstString = "tmp";
    String secondString = "test";

    byte[] first  = new byte[8];
    first = firstString.getBytes();
    byte[] second = new byte[32];
    second = secondString.getBytes();
    byte[] destination = new byte[40];

    System.arraycopy(first, 0, destination, 0, first.length);
    System.arraycopy(second, 0, destination, 8, second.length);

	try{
	    InetAddress address = InetAddress.getByName(args[0]);
	    DatagramPacket packet = new DatagramPacket(destination, destination.length, address, 4321);
	    DatagramSocket s = new DatagramSocket();
	    s.send(packet);
	    s.close();
	}catch(IOException e){
	    System.err.println(""+e);
	}
    }
    
    public static void main(String args[]){
	new UdpSend(args);
    }
}
