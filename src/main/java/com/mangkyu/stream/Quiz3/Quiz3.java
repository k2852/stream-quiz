package com.mangkyu.stream.Quiz3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Quiz3 {

    private static final List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    private static final List<Integer> numbers2 = Arrays.asList(3, 4);

    // 3.1 모든 숫자 쌍의 배열 리스트를 반환하여라.
    // ex) numbers1 = [1,2,3], numbers2 = [3,4] -> [(1,3), (1,4), (2,3), (2,4), (3,3), (3,4)]
    public List<Integer[]> quiz1() {

        return numbers1.stream()
                .flatMap(n -> numbers2.stream() // 넘버1 스트림의 숫자 하나당
                        .map(m -> new Integer[]{n, m}))// 넘버2 스트림의 숫자 하나를 매칭시켜 배열 생성
                // 이 때, 둘 다 Stream이므로 flatMap으로 펼쳐서 모아준다
                .collect(Collectors.toList());
    }

    // 3.2 모든 숫자 쌍의 곱이 가장 큰 값을 반환하여라.
    // ex) numbers1 = [1,2,3], numbers2 = [3,4] -> 12
    public int quiz2() {

        return numbers1.stream()
                .flatMap(n -> numbers2.stream()
                        .map(m -> new Integer[]{n, m})) // 숫자쌍 Stream<Integer[]> 를 만든다
                .mapToInt(a -> a[0] * a[1]) // 숫자쌍의 곱으로 변환한 IntStream을 만든다
                .max() // 그 중 최댓값을 OptionalInt 형태로 받는다
                .orElse(0); // null이면 0을 반환
    }

}
