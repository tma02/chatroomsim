package tma02.chatroom;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatAnalyzer {
	
	private HashMap<String, ArrayList<String>> markov;
	
	public ChatAnalyzer(ChatLog log) {
		System.out.println("Anaylzing chat log...");
		this.markov = new HashMap<String, ArrayList<String>>();
		String[] words;
		for (ChatLine line : log.getLines()) {
			if (line == null) {
				break;
			}
			words = line.getMessage().split(" ");
			this.analyzeWords(words, 0);
		}
		System.out.println("Anaylzed all lines of chat.");
	}
	
	private void analyzeWords(String[] words, int i) {
		if (i >= words.length) {
			return;
		}
		if (!this.markov.containsKey(words[i])) {
			this.markov.put(words[i], new ArrayList<String>());
		}
		if (i >= words.length - 1) {
			this.markov.get(words[i]).add((char) 0 + "");
			return;
		}
		this.markov.get(words[i]).add(words[++i]);
		this.analyzeWords(words, i);
	}
	
	public HashMap<String, ArrayList<String>> getMarkov() {
		return this.markov;
	}
	
	public String getGeneratedMessage() {
		String[] initialSet = this.getMarkov().keySet().toArray(new String[] {});
		StringBuilder messageBuilder = new StringBuilder();
		String lastWord = this.getElementFromArray(initialSet);
		while (!lastWord.equals((char) 0 + "")) {
			messageBuilder.append(lastWord);
			messageBuilder.append(" ");
			lastWord = this.getElementFromList(this.markov.get(lastWord));
		}
		messageBuilder.delete(messageBuilder.length() - 1, messageBuilder.length());
		return messageBuilder.toString();
	}
	
	private String getElementFromArray(String[] array) {
		return array[(int) (Math.random() * array.length)];
	}
	
	private String getElementFromList(ArrayList<String> list) {
		if (list == null || list.size() == 0) {
			return (char) 0 + "";
		}
		return list.get((int) (Math.random() * list.size()));
	}

}
