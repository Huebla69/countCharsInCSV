import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static final int THREADS_A = 1;

	public static void main(String[] args) throws FileNotFoundException, InterruptedException, IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		System.out.println("BITTE GIB DEN CHAR AN NOCH DEM DU SUACHN WÜSST: ");
		String string = sc.nextLine();
		long start = System.currentTimeMillis();
		Character ch = string.charAt(0);

		System.out.println(addChars(readFile("C:/Users/Hübla/Downloads/5m-Sales-Records/5m Sales Records.csv"), ch));
		
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println(timeElapsed/1000);

	}

	private static List<String[]> readFile(String fileLocation) throws FileNotFoundException {

		Scanner sc = new Scanner(new File(fileLocation));

		List<String[]> data = new ArrayList<>();

		while (sc.hasNext()) {

			String line = sc.nextLine();
			String[] values = line.split(",");
			data.add(values);

		}
		
		System.out.println("Einlesen fertig");

		return data;

	}

	private static int addChars(List<String[]> lsa, char ch) throws FileNotFoundException, InterruptedException, IndexOutOfBoundsException {

		charCounter[] cca = new charCounter[THREADS_A];
		
		
		// Leide an diesem Code
		int partListSize = (lsa.size() / THREADS_A) + 1;

		for (int i = 0; i < THREADS_A; i++) {

			int startIndex = i * partListSize;
			int endIndex = startIndex + partListSize;

			charCounter cc = new charCounter(lsa, startIndex, endIndex, ch);

			cca[i] = cc;

		}

		for (charCounter cc : cca) {

			cc.start();

		}

		for (charCounter cc : cca) {

			cc.join();

		}

		int charCount = 0;
		for (charCounter cc : cca) {
			charCount += cc.getCounter();
		}

		return charCount;
	}

}
