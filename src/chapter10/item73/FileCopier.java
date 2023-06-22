package chapter10.item73;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopier {
    public void copyFile(String sourcePath, String destinationPath) throws FileCopyException {
        try (InputStream inputStream = new FileInputStream(sourcePath);
             OutputStream outputStream = new FileOutputStream(destinationPath)) {
        	
        } catch (IOException e) {
            // IOException을 FileCopyException으로 변환하여 던짐
            throw new FileCopyException("파일 복사 중 오류가 발생했습니다.", e);
        }
    }

    public static void main(String[] args) {
        FileCopier fileCopier = new FileCopier();
        try {
            fileCopier.copyFile("source.txt", "destination.txt");
        } catch (FileCopyException e) {
            e.printStackTrace();
        }
    }
}