# Word to PDF Converter
A Java application that converts multiple Microsoft Word documents (.doc and .docx) to PDF files while preserving formatting.

---
## Features
- Batch conversion of Word documents to PDFs
- Preserves document formatting during conversion
- Supports both .doc and .docx file formats
- Simple console interface with copy-paste support for file paths
- Detailed conversion summary with success/failure information
---
## Prerequisites

- Java 8 or higher
- Maven for dependency management and building
---
## Dependencies

This project uses the following libraries:
- Apache POI for Word document processing
- fr.opensagres for PDF conversion
- iText for PDF generation
---
## Installation

1. Clone this repository:
   ```
   git clone https://github.com/yourusername/word-to-pdf-converter.git
   cd word-to-pdf-converter
   ```

2. Build the project using Maven:
   ```
   mvn clean package
   ```

This will generate an executable JAR file in the `target` directory named `word-to-pdf-converter-1.0-SNAPSHOT-jar-with-dependencies.jar`.

---
## Usage

### Running the Application

Execute the JAR file using the following command:
```
java -jar target/word-to-pdf-converter-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Alternatively, you can provide the source and target directories as command-line arguments:
```
java -jar target/word-to-pdf-converter-1.0-SNAPSHOT-jar-with-dependencies.jar /path/to/word/files /path/to/output/pdfs
```

### Interactive Mode

If you run the application without command-line arguments, it will prompt you for:

1. Source directory (containing Word files)
2. Target directory (for PDF output)

You can simply copy and paste the full file paths from your file explorer when prompted.

### Example

```
Enter the source directory path (containing Word files):
C:\Users\YourName\Documents\WordFiles

Enter the target directory path (for PDF output):
C:\Users\YourName\Documents\PDFFiles

Source directory: C:\Users\YourName\Documents\WordFiles
Target directory: C:\Users\YourName\Documents\PDFFiles
Converting: Document1.docx to PDF...
Successfully converted: Document1.docx to Document1.pdf
Converting: Report.docx to PDF...
Successfully converted: Report.docx to Report.pdf

--- Conversion Summary ---
Total files processed: 2
Successfully converted: 2
Failed conversions: 0

Press Enter to exit...
```
---
## Project Structure

- `WordToPdfConverter.java`: Main class containing the conversion logic
- `pom.xml`: Maven project configuration with dependencies
---
## Is there a way to use Microsoft Word's built-in Word to PDF Converter?
Yes, there is a way to leverage Microsoft Word's own PDF conversion capabilities from Java, which would solve the emoji and formatting issues completely since Microsoft Word handles these perfectly.
Instead of creating your own conversion logic, you can use the Jacob library (Java COM Bridge) to automate Microsoft Word through its COM interface on Windows. This approach will use Word's built-in "Save as PDF" feature.

To use this approach, you'll need to add the Jacob library to your Maven dependencies:
``` xml
<dependencies>
    <!-- Apache POI for Word processing - still useful for reading Word files without conversion -->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi</artifactId>
        <version>5.2.3</version>
    </dependency>
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.2.3</version>
    </dependency>
    
    <!-- Jacob - Java COM Bridge -->
    <dependency>
        <groupId>com.hynnet</groupId>
        <artifactId>jacob</artifactId>
        <version>1.18</version>
    </dependency>
</dependencies>
```
### Requirements for this approach:

Microsoft Word must be installed on the computer running the code.
Windows operating system is required (since it uses Windows COM).
You need to set up the Jacob DLL:

Download the Jacob DLL that matches your JVM architecture (32-bit or 64-bit).
https://github.com/freemansoft/jacob-project/releases

Place the DLL in your Java library path (or specify its location with D:\Program Files\Java\jdk1.8.0_231\bin).

### Advantages of this approach:

Perfect rendering: Microsoft Word's own PDF export is used, so all formatting, emojis, and line dividers will be preserved exactly as they appear in Word.
Full feature support: All Word features, including tables, charts, images, and complex formatting will be preserved.
No font issues: Word handles all font substitutions automatically.

### Limitations:

Platform-dependent: This only works on Windows with Microsoft Word installed.
Requires license: Microsoft Word must be properly licensed on the computer.
Performance: Launching Word for each conversion is slower than pure Java solutions.
---
## Troubleshooting

If you encounter issues:

1. Ensure Java 8 or higher is installed and configured
2. Verify that the source directory contains valid Word documents (.doc or .docx)
3. Make sure you have write permissions for the target directory
4. Check that all dependencies are correctly resolved by Maven
---
## License

This project is licensed under the MIT License - see the LICENSE file for details.

---
## Acknowledgments

- Apache POI team for document processing libraries
- fr.opensagres for the XDocReport project and PDF conversion capabilities
