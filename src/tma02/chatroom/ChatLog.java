package tma02.chatroom;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.Charset;

public class ChatLog implements Serializable {
	
	private static final long serialVersionUID = -62152285132694648L;
	private ChatLine[] lines;

	public ChatLog(String file) throws IOException {
		System.out.println("Loading '" + file + "'...");
		lines = new ChatLine[3000000];
		String line;
		int i = 0;
		InputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		while ((line = br.readLine()) != null) {
			lines[i++] = new ChatLine(line);
		}
		br.close();
		System.out.println("Loaded " + --i + " lines of chat.");
	}
	
	public ChatLine[] getLines() {
		return this.lines;
	}
	
}
