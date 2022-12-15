package com.clinic.grade;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GradeTestSuite {

    @Autowired
    private GradeRepository gradeRepository;

    @Test
    void shouldCreateNewGrades(){
        // given
        Grade grade = new Grade("nickname","description",9);
        gradeRepository.save(grade);

    }
}
