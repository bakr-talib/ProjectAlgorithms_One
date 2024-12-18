package com.example.projectalgorithms_one;

import java.util.Comparator;

public class QuickSort {
    public static void swap(Student[] student, int i, int j)
    {
        Student temp = student[i];
        student[i] = student[j];
        student[j] = temp;
    }


    public static int partition(Student[] students, int low, int high, Comparator<Student> comparator) {
        Student pivot = students[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (comparator.compare(students[j], pivot) <=0 )
            {
                i++;
                swap(students, i, j);
            }
        }
        swap(students, i + 1, high);
        return (i + 1);
    }


    public static void quickSort(Student[] students, int low, int high, Comparator<Student> comparator) {
        if (low < high) {
            int pi = partition(students, low, high, comparator);
            quickSort(students, low, pi - 1, comparator);
            quickSort(students, pi + 1, high, comparator);
        }
    }


    public static void sortData(Student[] students, Comparator<Student> comparator){
        quickSort(students, 0, students.length - 1, comparator);
    }
}
