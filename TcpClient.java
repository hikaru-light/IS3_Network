import java.net.*;
import java.io.*;

class TcpClient {
	public static void main(String args[]) {
		Socket s;
		InputStream sIn;
		OutputStream sOut;
		BufferedReader br;
		PrintWriter pw;
		String str;
		String tmp[];

		if (args.length != 1) {
			System.out.println("No hostname given");
			System.exit(1);
		}

		try {
			s = new Socket(args[0], 4321);

			sIn = s.getInputStream();
			sOut = s.getOutputStream();
			br = new BufferedReader(new InputStreamReader(sIn));
			pw = new PrintWriter(new OutputStreamWriter(sOut), true);

			pw.println("hello");

			str = br.readLine();
			tmp = str.split(" ");

			if (!tmp[0].equals("ack")) {
				System.exit(1);
			}

			pw.println("1");
			str = br.readLine();
			tmp = str.split(" ");

			if (!tmp[0].equals("ack")) {
				System.exit(1);
			}

			pw.println("2");

			str = br.readLine();
			System.out.println(str);

			sIn.close();
			sOut.close();
			s.close();
		} catch (IOException e) {
			System.err.println("Caught IOException");
			System.exit(1);
		}
	}
}
