import java.io.File;
import java.io.IOException;

public interface Writer {
    void writeAllFiles(File dir, File dst) throws IOException;
}
