package com.chenhm.java8.lambda;

import com.chenhm.java8.Student;
import com.chenhm.java8.StudentDTO;

/**
 * @author chen-hongmin
 * @date 2018/2/9 11:18
 * @since V1.0
 */
public class LambdaDemo {

    public static void main(String[] args) {
        StudentDTO studentDTO = BaseConvert.convert(new Student(),student -> {
            StudentDTO studentDTO1 = new StudentDTO();
            studentDTO1.setStudentName("ss");
            return studentDTO1;
        });
    }


}
