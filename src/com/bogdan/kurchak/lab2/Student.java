package com.bogdan.kurchak.lab2;

import java.util.Random;

/**
 * @author Bohdan Kurchak
 */
//Реалізуємо даний клас, щоб можна було перевизначити метод compareTo
public class Student implements Comparable<Student>{
    private int id;
    private int mark;

    // Ми не будемо призначати ID в ході програми, тому номер студент отримує при створенні
    public Student(int id) { this.id = id;
    }
    public int getId() {
        return id;
    }
    public int getMark() {
        return mark;
    }

    /* За допомогою даного метода ми
     * призначаємо студенту довільну оцінку в тих межах,
     * які будуть вказані при запуску програми
     */
    public void setMark() {
        Random random = new Random();
        int maxRate = 12;
        int print = random.nextInt(maxRate); mark = print + 1;
    }

    @Override
    public String toString() {
        return "Студент " + (id + 1) +
                ": оцінка = " + mark + "; ";
    }

    //Перевизначаємо метод, щоб можна було сортувати за власним параметром
    @Override
    public int compareTo(Student compareStudent) {
        int compareMark = compareStudent.getMark();

        //Сортування до зростання
        return this.mark - compareMark;

        //Сортування до спадання
        //return compareMark - this.mark;
    }
}
