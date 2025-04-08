import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class WordToPdfConverterUsingMSWord {

    // Microsoft Word constants
    private static final int wdFormatPDF = 17; // PDF format
    private static final int wdDoNotSaveChanges = 0; // Don't save changes
    private static final boolean VISIBLE_WORD = false; // Set to true for debugging

    // Directory containing Word files
    private String sourceDir;
    // Directory where PDF files will be saved
    private String targetDir;
    // List to store conversion results
    private List<ConversionResult> results;

    public WordToPdfConverterUsingMSWord(String sourceDir, String targetDir) {
        this.sourceDir = sourceDir;
        this.targetDir = targetDir;
        this.results = new ArrayList<>();

        // Create target directory if it doesn't exist
        File targetDirFile = new File(targetDir);
        if (!targetDirFile.exists()) {
            targetDirFile.mkdirs();
        }
    }

    /**
     * Converts all Word files in the source directory to PDFs
     */
    public void convertAll() {
        // Initialize COM
        ComThread.InitSTA();

        try {
            File dir = new File(sourceDir);
            if (!dir.exists() || !dir.isDirectory()) {
                System.err.println("Source directory does not exist: " + sourceDir);
                return;
            }

            File[] files = dir.listFiles();
            if (files == null || files.length == 0) {
                System.out.println("No files found in source directory.");
                return;
            }

            // Create Word application instance
            ActiveXComponent wordApp = new ActiveXComponent("Word.Application");
            wordApp.setProperty("Visible", new Variant(VISIBLE_WORD));

            try {
                // Process each Word file
                for (File file : files) {
                    if (isWordFile(file.getName())) {
                        convertFile(wordApp, file);
                    }
                }
            } finally {
                // Close Word and release COM resources
                wordApp.invoke("Quit", new Variant(wdDoNotSaveChanges));
                ComThread.Release();
            }

            // Print conversion summary
            printSummary();

        } catch (Exception e) {
            System.err.println("Error during conversion process: " + e.getMessage());
            e.printStackTrace();
            ComThread.Release();
        }
    }

    /**
     * Converts a single Word file to PDF using Microsoft Word
     */
    private void convertFile(ActiveXComponent wordApp, File wordFile) {
        String fileName = wordFile.getName();
        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
        String pdfFileName = baseName + ".pdf";
        File pdfFile = new File(targetDir, pdfFileName);

        System.out.println("Converting: " + fileName + " to PDF...");

        Dispatch documents = null;
        Dispatch document = null;

        try {
            // Get Documents collection
            documents = wordApp.getProperty("Documents").toDispatch();

            // Open the document
            document = Dispatch.call(documents, "Open", wordFile.getAbsolutePath()).toDispatch();

            // Save as PDF
            Dispatch.call(document, "SaveAs", pdfFile.getAbsolutePath(), wdFormatPDF);

            // Close the document
            Dispatch.call(document, "Close", new Variant(wdDoNotSaveChanges));

            results.add(new ConversionResult(fileName, pdfFileName, true, null));
            System.out.println("Successfully converted: " + fileName + " to " + pdfFileName);

        } catch (Exception e) {
            results.add(new ConversionResult(fileName, pdfFileName, false, e.getMessage()));
            System.err.println("Failed to convert: " + fileName + ". Error: " + e.getMessage());
            e.printStackTrace();

            // Attempt to close document if it's open
            if (document != null) {
                try {
                    Dispatch.call(document, "Close", new Variant(wdDoNotSaveChanges));
                } catch (Exception closeError) {
                    // Ignore close errors
                }
            }
        }
    }

    /**
     * Checks if the file is a Word document
     */
    private boolean isWordFile(String fileName) {
        return fileName.toLowerCase().endsWith(".docx") || fileName.toLowerCase().endsWith(".doc");
    }

    /**
     * Prints summary of conversion results
     */
    private void printSummary() {
        System.out.println("\n--- Conversion Summary ---");
        int successCount = 0;
        for (ConversionResult result : results) {
            if (result.isSuccess()) {
                successCount++;
            }
        }

        System.out.println("Total files processed: " + results.size());
        System.out.println("Successfully converted: " + successCount);
        System.out.println("Failed conversions: " + (results.size() - successCount));

        if (results.size() - successCount > 0) {
            System.out.println("\nFailed conversions:");
            for (ConversionResult result : results) {
                if (!result.isSuccess()) {
                    System.out.println(" - " + result.getSourceFileName() + " : " + result.getErrorMessage());
                }
            }
        }
    }

    /**
     * Inner class to store conversion results
     */
    private static class ConversionResult {
        private String sourceFileName;
        private String targetFileName;
        private boolean success;
        private String errorMessage;

        public ConversionResult(String sourceFileName, String targetFileName, boolean success, String errorMessage) {
            this.sourceFileName = sourceFileName;
            this.targetFileName = targetFileName;
            this.success = success;
            this.errorMessage = errorMessage;
        }

        public String getSourceFileName() {
            return sourceFileName;
        }

        public String getTargetFileName() {
            return targetFileName;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sourceDir;
        String targetDir;

        if (args.length >= 2) {
            sourceDir = args[0];
            targetDir = args[1];
        } else {
            System.out.println("Enter the source directory path (containing Word files):");
            sourceDir = scanner.nextLine().trim();

            System.out.println("Enter the target directory path (for PDF output):");
            targetDir = scanner.nextLine().trim();
        }

        // Clean up paths (handle both forward and backward slashes)
        sourceDir = sourceDir.replace("\"", "").trim();
        targetDir = targetDir.replace("\"", "").trim();

        System.out.println("Source directory: " + sourceDir);
        System.out.println("Target directory: " + targetDir);

        WordToPdfConverterUsingMSWord converter = new WordToPdfConverterUsingMSWord(sourceDir, targetDir);
        converter.convertAll();

        // Keep console window open until user presses Enter
        System.out.println("\nPress Enter to exit...");
        scanner.nextLine();
        scanner.close();
    }
}
