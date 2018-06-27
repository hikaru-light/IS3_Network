import java.net.*;
import java.io.*;

public class POP3 {
    String username = "ユーザ名";
    String password = "パスワード";
    String mail_server = ""; // メールサーバ名
    Socket socket;
    int port = 110;
    PrintWriter pw;
    BufferedReader br;

    POP3() {
        try {
            socket = new Socket(mail_server, port);
            pw = new PrintWriter(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(br.readLine());
            sendMessage("USER " + username);
            System.out.println(br.readLine());
            sendMessage("PASS " + password);
            System.out.println(br.readLine());
            sendMessage("STAT");
            System.out.println(br.readLine());

            BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String m = breader.readLine();
                if (m.equals(""))
                    break;
                sendMessage("RETR " + m);
                while (true) {
                    String message = br.readLine();
                    System.out.println(message);
                    if (message.equals("."))
                        break;
                }
            }
            sendMessage("QUIT");
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendMessage(String str) {
        pw.print(str + "\r\n");
        pw.flush();
    }

    public static void main(String args[]) {
        new POP3();
    }
}
