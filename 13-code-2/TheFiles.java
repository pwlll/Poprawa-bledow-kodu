import java.io.*;
import java.util.Scanner;

public class TheFiles {

	public void saveFile(String name, String content){
		try {
			File file = new File(name);
			FileWriter fw = new FileWriter(name);
			fw.write(content);
			fw.close();
		} catch(IOException ex){
			System.out.println("Wystąpił błąd");
			ex.getStackTrace();
		}
	}

	public String readFile(String input){
		String out = "";
		try {
			File file = new File(input);
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {
				out+= reader.nextLine();
			}
			reader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Brak pliku");
			ex.getStackTrace();
		}
		return out;
	}

	public String readFileStr(InputStream input){
		StringBuilder out = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
			String line;
			while ((line = br.readLine()) != null) {
				out.append(line).append("\n");
			}
		} catch (Exception ex) {
			System.out.println("Błąd odczytu");
		}
		return out.toString();
	}
}
