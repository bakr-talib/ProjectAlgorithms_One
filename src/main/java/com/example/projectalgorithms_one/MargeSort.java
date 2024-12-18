package com.example.projectalgorithms_one;

import java.util.Comparator;

public class MargeSort {
    public static void merge(Student[] students, int left, int middle, int right, Comparator<Student> comparator) {
        int low = middle - left + 1;
        int high = right - middle;
        Student[] L = new Student[low];
        Student[] R = new Student[high];
        int i, j;

        for (i = 0; i < low; ++i) {
            L[i] = students[left + i];
        }

        for (j = 0; j < high; ++j) {
            R[j] = students[middle + 1 + j];
        }

        int k = left;
        i = 0;
        j = 0;

        while (i < low && j < high) {
            if (comparator.compare(L[i], R[j]) <= 0) {
                students[k] = L[i];
                i++;
            } else {
                students[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < low) {
            students[k] = L[i];
            i++;
            k++;
        }

        while (j < high) {
            students[k] = R[j];
            j++;
            k++;
        }
    }


    public static void mergeSort(Student[] students, int left, int right, Comparator<Student> comparator) {
        if (left < right) {
            int middle = (left + right) / 2;

            mergeSort(students, left, middle, comparator);
            mergeSort(students, middle + 1, right, comparator);

            merge(students, left, middle, right, comparator);
        }
    }


    public static void sortData(Student[] students, Comparator<Student> comparable) {
        mergeSort(students, 0, students.length - 1, comparable);
    }
}

