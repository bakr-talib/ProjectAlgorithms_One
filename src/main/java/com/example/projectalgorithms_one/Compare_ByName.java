package com.example.projectalgorithms_one;

import java.util.Comparator;

public class Compare_ByName implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2)
    {
        if (o1.getName().compareTo(o2.getName()) > 0)
            return 1;
        else if (o1.getName().compareTo(o2.getName()) < 0)
            return -1;
        else
            return 0;
    }
}
