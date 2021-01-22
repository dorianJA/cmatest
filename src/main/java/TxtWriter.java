import java.io.File;
import java.io.IOException;

public interface TxtWriter {
    void writeAllFiles(File dir, File dst) throws IOException;
}
