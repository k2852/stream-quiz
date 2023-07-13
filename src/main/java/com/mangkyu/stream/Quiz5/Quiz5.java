package com.mangkyu.stream.Quiz5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Quiz5 {

    private static final String[] STRING_ARR = {"aaa", "bb", "c", "dddd"};

    // 5.1 모든 문자열의 길이를 더한 결과를 출력하여라.
    public int quiz1() {
        
        return Arrays.stream(STRING_ARR)
                .mapToInt(String::length) // 각 문자열의 길이로 map
                .sum(); // 총합을 반환
        
        /*
        return Arrays.stream(STRING_ARR)
                .collect(Collectors.joining("")) // 문자열을 이어붙인다
                .length(); // 길이 반환
         */
    }

    // 5.2 문자열 중에서 가장 긴 것의 길이를 출력하시오.
    public int quiz2() {
        
        return Arrays.stream(STRING_ARR)
                .mapToInt(String::length) // 문자열 길이로 매핑
                .max() // 최댓값
                .orElse(0); // 옵셔널 처리
    }

    // 5.3 임의의 로또번호(1~45)를 정렬해서 출력하시오.
    public List<Integer> quiz3() {
        return new Random().ints(1, 46) // 1~45 랜덤 숫자 범위 설정
                .distinct() // 중복 제거
                .limit(6) // 6개를 뽑는다
                .boxed() // IntStream -> Stream<Integer>
                .sorted() // 정렬한다
                .collect(Collectors.toList());

    }

    // 5.4 두 개의 주사위를 굴려서 나온 눈의 합이 6인 경우를 모두 출력하시오.
    public List<Integer[]> quiz4() {
        return IntStream.rangeClosed(1, 6) // 1,2,3,4,5,6의 IntStream 생성
                .boxed() // Stream<Integer> 로 래핑
                .flatMap(a -> IntStream.rangeClosed(1, 6).boxed() // 1,2,3,4,5,6의 Stream<Integer> 또 생성
                                .map(b -> new Integer[]{a, b}) ) // Stream<Integer[]> 형태로 (a,b) 배열 생성
                .filter(arr -> arr[0] + arr[1] == 6) // 두 눈의 합이 6인 경우만 걸러낸다
                .collect(Collectors.toList());
    }
}
