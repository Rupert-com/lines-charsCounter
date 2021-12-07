/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filecnt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**

 @author rupert
 */
public class Filecnt {

  /**
   @param args the command line arguments
   */
  public static void main(String[] args) throws FileNotFoundException, IOException {

    final String folderPath = "C:\\Users\\ruper\\Documents\\NetBeansProjects\\ubispaze";

    long totalLineCount = 0;
    long totalCharCount = 0;
    final List<File> folderList = new LinkedList<>();
    folderList.add(new File(folderPath));
    while (!folderList.isEmpty()) {
      final File folder = folderList.remove(0);
      if (folder.isDirectory() && folder.exists()) {
        System.out.println("Scanning " + folder.getName());
        final File[] fileList = folder.listFiles();
        for (final File file : fileList) {
          if (file.isDirectory()) {
            folderList.add(file);
          } else if (file.getName().endsWith(".java")
                  || file.getName().endsWith(".sql")
                  || file.getName().endsWith(".jsp")
                  || file.getName().endsWith(".js")
                  || file.getName().endsWith(".css")
                  || file.getName().endsWith(".html")
                  || file.getName().endsWith(".properties")) {
            long lineCount = 0;
            long charCount = 0;

            FileInputStream fileStream = new FileInputStream(file);
            InputStreamReader input = new InputStreamReader(fileStream);
            BufferedReader reader = new BufferedReader(input);

            String line;
            while ((line = reader.readLine()) != null) {
              charCount += line.replaceAll("\\s", "").length();
            }
            totalCharCount += charCount;

            final Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
              scanner.nextLine();
              lineCount++;
            }
            totalLineCount += lineCount;
            final String lineCountString;
            if (lineCount > 9999999) {
              lineCountString = "" + lineCount;
            } else {
              final String temp = ("     " + lineCount);
              lineCountString = temp.substring(temp.length() - 5);
            }
            System.out.println(lineCountString + " lines, and  Chars: " + charCount + " in " + file.getName()
            );
          }
        }
      }
    }
    System.out.println("Scan Complete: " + totalLineCount + " lines total and Chars: " + totalCharCount);
  }

}
