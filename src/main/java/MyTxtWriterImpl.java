import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MyTxtWriterImpl implements TxtWriter {

    private List<File> files;

    public MyTxtWriterImpl(List<File> files) {
        this.files = files;
    }

    @Override
    public void writeAllFiles(File dir, File dst) throws IOException {
        List<File> fileList = searchAllFiles(dir).stream()
                .sorted(Comparator.comparing(File::getName)).collect(Collectors.toList());

        for (File file : fileList) {
            writeFile(file, dst);
        }
        files.clear();
    }

    private void writeFile(File srcFile, File dst) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(srcFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(dst, true))) {
            String sp = System.getProperty("line.separator");
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line + sp);
            }
        }
    }

    private List<File> searchAllFiles(File dir) throws FileNotFoundException {
        if (!dir.exists()) {
            throw new FileNotFoundException();
        }
        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                searchAllFiles(f);
            } else {
                if (f.getName().toLowerCase().endsWith(".txt")) {
                    files.add(f);
                }
            }
        }
        return files;
    }

}
