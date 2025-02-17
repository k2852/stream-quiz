package com.mangkyu.stream.Quiz6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class Quiz6 {

    private Student[] stuArr;

    public Quiz6() {
        stuArr = new Student[]{
                new Student("나자바", true, 1, 1, 300),
                new Student("김지미", false, 1, 1, 250),
                new Student("김자바", true, 1, 1, 200),
                new Student("이지미", false, 1, 2, 150),
                new Student("남자바", true, 1, 2, 100),
                new Student("안지미", false, 1, 2, 50),
                new Student("황지미", false, 1, 3, 100),
                new Student("강지미", false, 1, 3, 150),
                new Student("이자바", true, 1, 3, 200),
                new Student("나자바", true, 2, 1, 300),
                new Student("김지미", false, 2, 1, 250),
                new Student("김자바", true, 2, 1, 200),
                new Student("이지미", false, 2, 2, 150),
                new Student("남자바", true, 2, 2, 100),
                new Student("안지미", false, 2, 2, 50),
                new Student("황지미", false, 2, 3, 100),
                new Student("강지미", false, 2, 3, 150),
                new Student("이자바", true, 2, 3, 200)
        };
    }

    // stuArr에서 불합격(150점 미만)한 학생의 수를 남자와 여자로 구별하여라. (Boolean, List)
    public Map<Boolean, List<Student>> quiz1() {

        return Arrays.stream(stuArr) // 학생 리스트에서
                .filter(s -> s.getScore() < 150) // 150점 미만인 학생 필터링
                .collect(groupingBy(Student::isMale));// 성별로 그룹짓는다
        // Map< 남자여자 true false? , 조건에 따른 List<Student> >

    }

    // 각 반별 총점을 학년 별로 나누어 구하여라 (Map<Integer, Map<Integer, Integer>>)
    public Map<Integer, Map<Integer, Integer>> quiz2() {
        return Arrays.stream(stuArr) // 학생 리스트에서
                .collect(groupingBy(Student::getHak, // 학년끼리 그룹짓고 -> Map<학년, 학년으로 나눈 학생 List>
                        groupingBy(Student::getBan, // 반별로 그룹지어서 -> Map<학년, Map<반, 학생리스트> >
                                summingInt(Student::getScore)))); // 각 리스트의 합 구하기 -> Map<학년, Map<반, 총합> >
    }

}
