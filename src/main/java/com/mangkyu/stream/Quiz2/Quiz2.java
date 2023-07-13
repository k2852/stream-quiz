package com.mangkyu.stream.Quiz2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Quiz2 {

    private static List<String> WORDS = Arrays.asList("TONY", "a", "hULK", "B", "america", "X", "nebula", "Korea");

    // 2.1 List에 저장된 단어들의 접두사가 각각 몇개씩 있는지 Map<String, Integer>으로 변환하여라.
    // ex) ("T", 1), ("a", 2) ...
    public Map<String, Integer> quiz1() {

        return WORDS.stream()
                .map(w -> w.substring(0, 1)) // 각 WORD를 접두사로 매핑
                .collect(Collectors.toMap( // Map으로 변환해서 모은다
                        prefix -> prefix, prefix -> 1, // 접두사를 받아서, 값을 1로 변환
                        (oldValue, newValue) -> (newValue += oldValue) // 등장 빈도 수 체크
                ));
    }

    // 2.2 List에 저장된 단어들 중에서 단어의 길이가 2 이상인 경우에만, 접두사를 대문자로 변환하여 스페이스로 구분한 하나의 문자열로 합한 결과를 반환하여라.
    // ex) ["Hello", "a", "Island", "b"] -> “H I”
    public String quiz2() {

        return WORDS.stream()
                .filter(w -> w.length() > 1) // 단어의 길이 2 이상으로 필터
                .map(String::toUpperCase) // 대문자로 변환
                .map(w -> w.substring(0,1)) // 접두사로 매핑
                .collect(Collectors.joining(" ")); // " " 구분자로 문자열 합하기
    }

}
