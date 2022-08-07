import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main {

    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        Scanner inFile;
        File selectedFile;
        String line;
        int lineNum = 0;
        //ArrayList<String> words = new ArrayList<>();


        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);

        try
        {
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();

                Path file = selectedFile.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                inFile = new Scanner(selectedFile);
                System.out.println("\n" + selectedFile);

                String[] words = new String[0];
                int wordCounter = 0;
                int count = 0;
                while (inFile.hasNextLine())
                {

                    line = inFile.nextLine();
                    lineNum++;
                    System.out.printf("\nLine %-4d %-65s ", lineNum, line);

                    words = line.split(" ");
                    wordCounter = words.length + wordCounter;

                    for(int i = 0; i < line.length(); i++)
                    {
                        if(line.charAt(i) != ' ')
                            count++;
                    }
                }

                System.out.println("\n\nData file read!");
                System.out.println("\nThe number of words in this file is: " + wordCounter);
                System.out.println("The number of characters in this file is: " + count);
            }
            else
            {
                System.out.println("Failed to choose a file to process!");
                System.out.println("Terminating program. Please try again.");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("ERROR! File not found!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("IOException Error");
            e.printStackTrace();
        }
    }

}
