package des;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class ReadWrite {

    public byte[] read(String path) throws IOException {
        byte[] array = Files.readAllBytes(new File(path).toPath());
        return array;
    }

    public void write(String path, byte[] decrypt) throws IOException {
        FileOutputStream stream = new FileOutputStream(path);
        try {
            stream.write(decrypt);
        } finally {
            stream.close();
        }
    }
}
