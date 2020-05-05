package com.bogdan.kurchak.lab2;

import java.util.*;

/**
 * @author Bogdan Kurchak
 */
public class Lab2 {
    public static void main(String[] args) {
        Lab2 lab2 = new Lab2();
        Scanner scanner = new Scanner(System.in);

        //Запитуємо у користувача кількість студентів
        System.out.print("Введіть кількість студентів у групі: ");
        int groupSize = scanner.nextInt();

        //Запитуємо у користувача розмір шкали оцінювання
        System.out.print("Шкала оцінювання: ");
        int maxMark = scanner.nextInt();

        //Створюємо групу студентів
        Student[] students = lab2.creatingGroup(groupSize);

        //Вихідні дані
        lab2.header("Вихідні дані");
        lab2.showStudentsList(students);

        /* У класі Student є перевизначений метод compareTo,
         * в якому ми сортуємо по власному параметру,
         * а саме по величині оцінки
         */
        Arrays.sort(students);

        //Варіаційний ряд
        lab2.header("Варіаційний ряд");
        lab2.showStudentsList(students);

        //Статистичний розподіл
        lab2.header("Статистичний розподіл");
        int[] distribution = lab2.distribution(students, groupSize, maxMark);
        for (int i = 0; i < maxMark; i++) {
            System.out.println("<" + (i + 1)+"> " + distribution[i]);
        }

        //Мода
        lab2.header("Мода");
        int moda = lab2.moda(distribution);
        System.out.println(moda);

        //Медіана
        lab2.header("Медіана");
        float mediana = lab2.mediana(students, groupSize);
        System.out.println(mediana);

        //Середнє арифметичне
        lab2.header("Середнє арифметичне");
        float arithmeticMean = lab2.arithmeticMean(students);
        System.out.println(arithmeticMean);
    }

    //Даний метод створює загаловки з ліній і тексту
    public void header(String text) {
        for (int i = 0; i < 50; i++) {
            System.out.print("\u23AF");
        }
        System.out.println();
        System.out.println(text);
        for (int i = 0; i < 50; i++) {
            System.out.print("\u23AF");
        }
        System.out.println();
    }

    //Даний метод сторює масив - групу студентів
    public Student[] creatingGroup(int groupSize) {
        Student[] students = new Student[groupSize];
        for (int i = 0; i < groupSize; i++) {
            Student student = new Student(i);
            student.setMark();
            students[i] = student;
        }
        return students;
    }

    //Даний метод виводить список студентів
    public void showStudentsList(Student[] students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    //За допомогою даного методу ми можемо порахувати статичний розподіл
    public int[] distribution(Student[] students, int groupSize, int maxMark) {
        int[] distribution = new int[maxMark];
        for (int i = 0; i < maxMark; i++) {
            for (int j = 0; j < groupSize; j++) {
                if (students[j].getMark() == (i + 1)) {
                    distribution[i]++;
                }
            }
        }
        return distribution;
    }

    //За допомогою даного методу ми можемо порахувати моду
    public int moda(int[] distribution) {
        int max = distribution[0];
        for (int i = 1; i < distribution.length; i++) {
            if (distribution[i] > max) {
                max = distribution[i];
            }
        }
        return max;
    }

    //За допомогою даного методу ми можемо порахувати медіану
    public float mediana(Student[] students, int groupSize) {
        float mediana;
        if (groupSize % 2 == 0) {
            int previousNumber = (groupSize / 2) - 1;
            int nextNumber = groupSize / 2;
            mediana = (float) (students[previousNumber].getMark() + students[nextNumber].getMark()) / 2.0f;
        } else {
            int searchedNumber = (int) ((groupSize / 2) + 0.5f);
            mediana = students[searchedNumber].getMark();
        }
        return mediana;
    }

    public float arithmeticMean(Student[] students) {
        int sum = 0;
        for (Student student : students) {
            sum += student.getMark();
        }
        return (float)sum / 2;
    }
}
