package Des;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ReadWriteFileToString {

    public  String readFile(String filePath) throws IOException{

        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return content;
    }

    public void writeFile(String text , String path) throws IOException {
        Files.write(Paths.get(path),text.getBytes());
    }

}



