import java.net.*;
import java.io.*;

public class SMTP{
    String to_addr = ""; // 送信先アドレス
    String from_addr = "";  // 送信元アドレス
    String mail_server = ""; // メールサーバ
    String message = "Hello, How are you?";
    Socket socket;
    int port = 25;
    PrintWriter pw;
    BufferedReader br;

    SMTP(){
	try{
	    socket = new Socket(mail_server,port);
	    pw = new PrintWriter(socket.getOutputStream());
	    br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    System.out.println(br.readLine());
	    sendMessage("HELO "+(InetAddress.getLocalHost().getHostName()));
	    System.out.println(br.readLine());
	    sendMessage("MAIL FROM: "+from_addr);
	    System.out.println(br.readLine());
	    sendMessage("RCPT TO: "+to_addr);
	    System.out.println(br.readLine());
	    sendMessage("DATA");
	    System.out.println(br.readLine());
	    sendMessage(message);
	    sendMessage(".");
	    System.out.println(br.readLine());
	    sendMessage("QUIT");
	    System.out.println(br.readLine());
	}catch(IOException e){
	    e.printStackTrace();
	}
    }

    void sendMessage(String str){
	pw.print(str+"\r\n");
	pw.flush();
    }
    
    public static void main(String args[]){
	new SMTP();
    }
}
