import java.io.IOException;
import java.io.InputStream;

class Demo {
	public static void main(String argc[]) throws IOException {
		String plikRO = "test.txt";
		String plikRW = "nowyPlik.txt";
	// zapis do nowego pliku
		TheFiles f = new TheFiles();
		f.saveFile(plikRW,"Zawartość lekcji");
		String c = f.readFile(plikRW);
		System.out.println(c);
	// odczyt z pliku
		ClassLoader classLoader = Demo.class.getClassLoader();
		InputStream input = classLoader.getResourceAsStream(plikRO);
		String c2 = f.readFileStr(input);
		System.out.println(c2);
	// obsługa archiwów
		Archives a = new Archives();
		String archName = "noweArchiwum";
	// zapis pliku do Archiwum
		a.pack(archName,plikRO);
		a.pack(archName+"Multi",plikRO+","+plikRW);
	// odczyt pliku z Archiwum
		a.unpack(archName,"./unpack",plikRO);
	}
}
