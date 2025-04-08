

# Java Notes and Tools

This repository contains various Java-related notes and tools that cover different aspects of Java programming, including JDBC, Spring JPA, Collections, and other Java utilities. You will also find some practical tools such as a **Word-to-PDF Converter**.

## Table of Contents
1. [Java Notes](#java-notes)
   - JDBC
   - Spring JPA
   - Best Practices for Collections
2. [Tools](#tools)
   - Word to PDF Converter (Using docx4j)
3. [How to Run](#how-to-run)
4. [Dependencies](#dependencies)
5. [Contributing](#contributing)

---

## Java Notes

### 1. **JDBC (Java Database Connectivity)**
   - **JDBC Basics**: JDBC allows Java programs to interact with relational databases.
   - **Key Components**: 
     - `DriverManager`: Manages a list of database drivers.
     - `Connection`: Establishes a connection to the database.
     - `Statement` and `PreparedStatement`: Used to execute SQL queries.
     - `ResultSet`: Holds data returned by SQL queries.
   - **Best Practices**:
     - Always close `Connection`, `Statement`, and `ResultSet` to avoid memory leaks.
     - Use `PreparedStatement` over `Statement` for better performance and security (prevents SQL injection).
     - Handle exceptions properly with `try-catch` and use logging.
     - Use connection pooling for better performance in enterprise-level applications.

### 2. **Spring JPA (Java Persistence API)**
   - **Introduction to JPA**: JPA is a Java specification for managing relational data in Java applications. It provides a way to map Java objects to database tables.
   - **Key Concepts**:
     - `Entity`: A class representing a table in the database.
     - `Repository`: Interface for CRUD operations.
     - `EntityManager`: Manages the persistence lifecycle.
   - **Best Practices**:
     - Prefer `Spring Data JPA` for simplicity in database operations.
     - Use `@Transactional` for managing transaction boundaries.
     - Optimize queries with the `@Query` annotation or `Criteria API` for complex queries.
     - Use pagination and sorting where applicable (`PageRequest` in Spring Data JPA).

### 3. **Best Practices for Collections in Java**
   - **Choosing the Right Collection**:
     - `List`: Use when you need ordered elements and allow duplicates.
     - `Set`: Use when you need unique elements, order not guaranteed (`HashSet`), or ordered (`TreeSet`).
     - `Map`: Use when you need key-value pairs (`HashMap`, `TreeMap`).
   - **Performance**:
     - `ArrayList` is usually faster for random access, while `LinkedList` is better for frequent insertions and deletions.
     - `HashSet` provides constant time complexity for operations, while `TreeSet` offers sorted elements but slower performance.
     - Use `ConcurrentHashMap` for thread-safe operations.

---

## Tools

### 1. **Word to PDF Converter (Using docx4j)**
This tool converts multiple DOCX files into PDFs. It leverages the **docx4j** library for reading DOCX files and **Apache FOP** for converting the document into PDF format.

#### Features:
- Batch conversion of multiple `.docx` files.
- Automatically saves the PDF with the same name as the input DOCX file.
- Supports customization of output directory and other features.

#### How it Works:
1. The program loads the DOCX file into memory using **docx4j**.
2. It then uses **Apache FOP** to convert the loaded DOCX document into PDF format.
3. The converted PDF is saved with the same name as the DOCX file in the same directory.

#### How to Use:
1. **Place DOCX files** in the specified folder.
2. **Run the `BatchDocxToPdfConverter`** program.
3. The program will process all DOCX files in the folder and save the converted PDFs in the same folder with the same name.

---

## How to Run

1. **Clone the repository** to your local machine.

   ```bash
   git clone <your-repository-url>
   cd <your-repository-name>
   ```

2. **Import the project into your IDE** (e.g., IntelliJ IDEA or Eclipse).

3. **Build the project** using Maven:

   ```bash
   mvn clean install
   ```

4. **Run the tools or note files**:
   - For **JDBC**, you can explore the sample code in `jdbc/`.
   - For **Spring JPA**, check out the `spring-jpa/` folder.
   - For **Word-to-PDF Converter**, navigate to the folder containing the converter code and execute the `BatchDocxToPdfConverter.java` file.

---

## Dependencies

Make sure you have the following dependencies added to your `pom.xml` for the tools and frameworks:

```xml
<dependencies>
     <!-- Apache POI for Word processing -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>4.1.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>4.1.2</version>
        </dependency>

        <!-- FR Opensagres PDF Converter -->
        <dependency>
            <groupId>fr.opensagres.xdocreport</groupId>
            <artifactId>fr.opensagres.poi.xwpf.converter.pdf</artifactId>
            <version>2.0.2</version>
        </dependency>
        <dependency>
            <groupId>fr.opensagres.xdocreport</groupId>
            <artifactId>fr.opensagres.poi.xwpf.converter.core</artifactId>
            <version>2.0.2</version>
        </dependency>

        <!-- Additional dependencies needed for the PDF conversion -->
        <dependency>
            <groupId>fr.opensagres.xdocreport</groupId>
            <artifactId>fr.opensagres.xdocreport.itext.extension</artifactId>
            <version>2.0.2</version>
        </dependency>

        <dependency>
            <groupId>com.lowagie</groupId>
            <artifactId>itext</artifactId>
            <version>2.1.7</version>
        </dependency>

    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- JDBC Driver (example for MySQL) -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.23</version>
    </dependency>
</dependencies>
```

---

## Contributing

Feel free to fork this repository, create issues, and submit pull requests to improve or add more tools and notes. Contributions are always welcome!

---

## License

This repository is open-source and available under the [MIT License](LICENSE).

---

### End of README

This README file provides an organized overview of your folder, including information about your Java notes and tools (such as the Word-to-PDF converter). It also includes a section for dependencies and instructions on how to run your code. Let me know if you need further modifications!
