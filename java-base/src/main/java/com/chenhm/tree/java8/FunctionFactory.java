package com.chenhm.tree.java8;

import java.util.function.Function;

/**
 * @author chen-hongmin
 * @date 2018/2/8 10:01
 * @since V1.0
 */
public class FunctionFactory {

    /**
     * Student 转为 StudentDTO
     * @return
     */
    public static Function<Student,StudentDTO> studentConvert(){

        Function<Student,StudentDTO> function = (student -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setClassName(student.getClassName());
            studentDTO.setStudentName(student.getStudentName());
            return studentDTO;
        });

        return function;
    }
}
