import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

class Archives {

    public void pack(String archName, String filesList) throws IOException {
        List<String> files;
        FileOutputStream fos = new FileOutputStream(archName+".zip");
        ZipOutputStream zos = new ZipOutputStream(fos);

        // dla wielu plików do kompesji
        if(filesList.contains(",")){
            String[] filesArr = filesList.split(",");
            files = Arrays.asList(filesArr);
        }
        else{
            files=Arrays.asList(filesList);
        }
            for (String srcFile : files) {   // wiele plików do kompresji
                File toZip = new File(srcFile);
                FileInputStream fis = new FileInputStream(toZip);
                ZipEntry entry = new ZipEntry(toZip.getName());
                zos.putNextEntry(entry);

                byte bytes[] = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zos.write(bytes, 0, length);
                }
                fis.close();
            }
            zos.close();
            fos.close();
        /*}  else {
            // dla pojedynczego pliku do kompresji
            File filesToArch = new File(filesList);
            FileInputStream fis = new FileInputStream(filesToArch);
            ZipEntry entry = new ZipEntry(filesToArch.getName());
            zos.putNextEntry(entry);
            byte bytes[] = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }
            zos.close();
            fis.close();
            fos.close();
        }*/
    }
    public void unpack(String archName, String outDir, String filesList) throws IOException {
        Path path = Paths.get(outDir);
        if(!Files.exists(path)) {
            System.out.println("Docelowy katalog wypakowania nie istnieje - tworzenie: " + outDir);
            Files.createDirectory(path);
        }
        File dstDir = new File(outDir); // gdzie wypakować pliki z Archiwum
        byte buffer[] = new byte[1024];
        try {   // otwórz Strumień danych wejściowych
            FileInputStream fis = new FileInputStream(archName+".zip");
            ZipInputStream zis = new ZipInputStream(fis);   // przekaż Strumień danych do rozpoznania danych ZIP
            ZipEntry entry = zis.getNextEntry();    // pobierz następny (pierwszy) Wpis z Archiwum
            while (entry != null)
            {   // działanie na pojedynczym Wpisie z Archiwum
                File newFile = newFile(dstDir,entry);   // metoda do tworzenia nowego pliku
                // sprawdź, czy Wpis to KATALOG, czy PLIK
                if( entry.isDirectory() ){  // Wpis to KATALOG
                    if(!newFile.isDirectory() && !newFile.mkdirs()){
                        throw new IOException("Błąd w tworzeniu katalogu "+newFile);
                    }
                } else {    // Wpis to PLIK
                    // #TODO fix dla ZIP-ów stworzonych na Windows
                }
                // zapisz dane jako plik
                FileOutputStream fos = new FileOutputStream(newFile);
                int length;
                while( (length = zis.read(buffer)) > 0 )
                {   // odczytaj fragment danych i zapisz do pliku
                    fos.write(buffer, 0, length);
                }
                fos.close();    // zamknij nowo utworzony plik
                entry = zis.getNextEntry();    // pobierz następny Wpis z Archiwum
            }
            zis.closeEntry();
            zis.close();
        } catch(Exception ex){
            System.out.println("Nieodnaleziono pliku "+archName);
        }
    }
    private File newFile(File dstDir, ZipEntry entry) throws IOException{
        File destFile = new File(dstDir, entry.getName());
        String dstDirPath = dstDir.getCanonicalPath();
        String dstFilePath = destFile.getCanonicalPath();
        if(!dstFilePath.startsWith(dstDirPath+File.separator)){
			throw new IOException("Wpis jest poza docelowym katalogiem "+entry.getName());
        }
        return destFile;
    }
}
