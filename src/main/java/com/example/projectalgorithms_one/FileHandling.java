package com.example.projectalgorithms_one;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandling {
    public static int sizeData;
    public static List<Student> students = new ArrayList<>();

    public static Student[] readData(String path) throws IOException {
        String line;

        students.clear();

        if (path == null) {
            throw new IOException("لا يوجد بيانات لترتيبها");
        }

        File file = new File(path);

        if (!file.exists()) {
            throw new IOException("File not found at " + path);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    line = line.replace("\uFEFF", "");
                    firstLine = false;
                }

                String[] values = line.split(",");

                if (values.length < 3) {
                    System.out.println("Skipped line: " + line);
                    continue;
                }

                try {
                    int id = Integer.parseInt(values[0].trim());
                    String name = values[1].trim();
                    double average = Double.parseDouble(values[2].trim());

                    Student student = new Student(id, name, average);
                    students.add(student);

                } catch (NumberFormatException e) {
                    e.getCause();
                }
            }
        } catch (IOException e) {
            e.getCause();
        }

        sizeData = students.size();
        return students.toArray(new Student[sizeData]);
    }

    public static void writeData(Student[] students, String path) throws IOException {
        if (students == null || students.length == 0) {
            throw new IOException("لا توجد بيانات لكتابتها إلى الملف");
        }

        File file = new File(path);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Student student : students) {
                String line = student.getId() + "," + student.getName() + "," + student.getAverage();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new IOException("حدث خطأ أثناء الكتابة إلى الملف: ");
        }
    }

}


