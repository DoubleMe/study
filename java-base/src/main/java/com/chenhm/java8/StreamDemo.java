package com.chenhm.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chen-hongmin
 * @date 2018/2/6 10:53
 * @since V1.0
 */
public class StreamDemo {


    public static void main(String[] args) {

        List<Student> list = new ArrayList<>();
        list.add(newStudent("一", "a"));
        list.add(newStudent("二", "b"));
        list.add(newStudent("二", "c"));

        //list 映射成map
        StreamWrapper.toMap(list, Collectors.groupingBy(Student::getClassName));

        //将list<Student> 转为 list<StudentDTO>
        List<StudentDTO> studentDTOS = StreamWrapper.map(list, FunctionFactory.studentConvert());
        studentDTOS.forEach(studentDTO -> System.out.println(studentDTO.getClassName() + studentDTO.getStudentName()));

        //Student 转为 StudentDTO
        Optional<StudentDTO> map = StreamWrapper.map(newStudent("一", "a"), FunctionFactory.studentConvert());
        StudentDTO studentDTO = map.orElse(null);
        System.out.println(studentDTO.getClassName() + studentDTO.getStudentName());

        // list join delimiter
        String joining = StreamWrapper.joining(list, Student::getClassName, ",", "{", "}");
        System.out.println(joining);

        //集合过滤
        List<Student> filter = StreamWrapper.conditionFilter(list, student -> student.getStudentName().equals("a"));

        //数据流拼接
        Student A = newStudent("A", "a");
        Student B = newStudent("B", "b");
        Stream<Student> a = Stream.of(A);
        Stream<Student> b = Stream.of(B);
        List<Student> collect = Stream.concat(a, b).collect(Collectors.toList());
        System.out.println(collect);

        //reduce 操作
        Student reduce = list.stream().reduce(list.get(0), (student1, student) -> {
            if (student1.getStudentName().compareTo(student.getStudentName()) > 0) {
                return student1;
            } else {
                return student;
            }
        });

        System.out.println(reduce.getStudentName());


        List<Integer> arr = Arrays.asList(1,2,3,4);

        Optional<Integer> reduce1 = arr.stream().reduce((integer, integer2) -> {

            System.out.println("integer - " + integer);
            System.out.println("integer2 - " + integer2);
            System.out.println("sum - " + (integer + integer2));

            return integer + integer2;
        });

        System.out.println(reduce1.orElse(0));



    }


    public static Student newStudent(String classname, String stuName) {
        Student student = new Student();
        student.setClassName(classname);
        student.setStudentName(stuName);

        return student;
    }
}
