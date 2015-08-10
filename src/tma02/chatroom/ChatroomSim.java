package tma02.chatroom;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ChatroomSim {
	
	public static ChatLog log;

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		ChatroomSim.loadLogTxt(false);
		//ChatroomSim.loadLogCache();
		System.out.println(log.getLines()[0].getMessage());
		ChatAnalyzer azr = new ChatAnalyzer(log);
		while (true) {
			Thread.sleep(500);
			System.out.println(azr.getGeneratedMessage());
		}
	}
	
	public static void loadLogTxt(boolean createCache) throws IOException {
		log = new ChatLog("data/log.txt");
		if (createCache) {
			FileOutputStream fileOut = new FileOutputStream("data/log.dat");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(log);
			out.close();
			fileOut.close();
		}
	}
	
	public static void loadLogCache() throws ClassNotFoundException, IOException {
		System.out.println("Loading log from cache...");
		FileInputStream fileIn = new FileInputStream("data/log.dat");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        log = (ChatLog) in.readObject();
        in.close();
        fileIn.close();
		System.out.println("Loaded " + "data/log.dat" + ".");
	}

}
