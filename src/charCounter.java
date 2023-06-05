import java.util.List;

public class charCounter extends Thread {

	private List<String[]> lsa;
	private int startIndex;
	private int endIndex;
	private int counter;
	private Character ch;

	charCounter(List<String[]> lsa, int startIndex, int endIndex, Character ch) {

		this.lsa = lsa;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.ch = ch;

	}

	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub

		System.out.println("Thread gestartet");

		for (int i = startIndex; i < endIndex; i++) {
			String[] length = lsa.get(i);
			for (int j = 0; j < length.length; j++) {

				String value = length[j];

				char[] ca = value.toCharArray();

				for (int e = 0; e < ca.length; e++) {

					char c = ca[e];

					String s = c + "";

					if (s.equalsIgnoreCase(ch + "")) {
						counter++;
					}
				}

			}

		}

	}

	public int getCounter() {
		return counter;
	}

}
