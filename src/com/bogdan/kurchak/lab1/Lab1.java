package com.bogdan.kurchak.lab1;

import java.util.*;

/**
 * @author Bogdan Kurchak
 */
public class Lab1 {
    public static void main(String[] args) {
        Lab1 lab1 = new Lab1();
        Scanner scanner = new Scanner(System.in);

        //Запитуємо у користувача кількість студентів
        System.out.print("Введіть кількість студентів у групі: ");
        int groupSize = scanner.nextInt();

        //Запитуємо у користувача розмір шкали оцінювання
        System.out.print("Шкала оцінювання: ");
        int maxMark = scanner.nextInt();

        //Створюємо групу студентів
        Student[] students = lab1.creatingGroup(groupSize);

        //Вихідні дані
        lab1.header("Вихідні дані");
        lab1.showStudentsList(students);

        /* У класі Student є перевизначений метод compareTo,
         * в якому ми сортуємо по власному параметру,
         * а саме по величині оцінки
         */
        Arrays.sort(students);

        //Варіаційний ряд
        lab1.header("Варіаційний ряд");
        lab1.showStudentsList(students);

        //Статистичний розподіл
        lab1.header("Статистичний розподіл");
        int[] distribution = lab1.distribution(students, groupSize, maxMark);
        for (int i = 0; i < maxMark; i++) {
            System.out.println("<" + (i + 1)+"> " + distribution[i]);
        }

        //Частота
        lab1.header("Частота");
        float[] frequency = lab1.frequency(distribution, groupSize, maxMark);
        for (int i = 0; i < maxMark; i++) {
            System.out.println("<" + (i + 1)+"> " + frequency[i]);
        }

        //Інтегральна частота
        lab1.header("Інтегральна частота");
        float[] integralFrequency = lab1.integralFrequency(distribution, groupSize, maxMark);
        for (int i = 0; i < maxMark; i++) {
            System.out.println("<" + (i + 1)+"> " + integralFrequency[i]);
        }
    }

    //Даний метод створює загаловки з ліній і тексту
    public void header(String text) {
        for (int i = 0; i < 50; i++) {
            System.out.print("\u23AF");
        }
        System.out.println();
        System.out.println(text);
        for (int i = 0; i < 50; i++){
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

    //За допомогою даного методу ми можемо порахувати частоту
    public float[] frequency(int[] distribution, int groupSize, int maxMark) {
        float[] frequency = new float[maxMark];
        for (int i = 0; i < maxMark; i++) {
            frequency[i] = (float) distribution[i] / groupSize;
        }
        return frequency;
    }

    //За допомогою даного методу ми можемо порахувати інтегральну частоту
    public float[] integralFrequency(int[] distribution, int groupSize, int maxMark) {
        int[] integralFrequency = new int[maxMark];
        float[] result = new float[maxMark];
        for (int i = 0; i < maxMark; i++) {
            for (int j = 0; j < (i + 1); j++) {
                integralFrequency[i] += distribution[j];
            }
            result[i] = (float)integralFrequency[i] / groupSize;
        }
        return result;
    }
}
