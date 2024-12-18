package com.example.projectalgorithms_one;

import java.util.Comparator;

public class SortUtil {

    public static Student[] mergeSort(Student[] students, Comparator<Student> comparator) {
        MargeSort.sortData(students, comparator);

        return students;
    }


    public static Student[] quickSort(Student[] students, Comparator<Student> comparator) {
        QuickSort.sortData(students, comparator);

        return students;
    }
}
