package com.mangkyu.stream.Quiz4;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Quiz4 {

    private List<Transaction> transactions;

    public Quiz4() {
        Trader kyu = new Trader("Kyu", "Seoul");
        Trader ming = new Trader("Ming", "Gyeonggi");
        Trader hyuk = new Trader("Hyuk", "Seoul");
        Trader hwan = new Trader("Hwan", "Busan");

        transactions = Arrays.asList(
                new Transaction(kyu, 2019, 30000),
                new Transaction(kyu, 2020, 12000),
                new Transaction(ming, 2020, 40000),
                new Transaction(ming, 2020, 7100),
                new Transaction(hyuk, 2019, 5900),
                new Transaction(hwan, 2020, 4900)
        );
    }

    // 4.1 2020년에 일어난 모든 거래 내역을 찾아 거래값을 기준으로 오름차순 정렬하라.
    public List<Transaction> quiz1() {

        return transactions.stream()
                .filter(t -> t.getYear() == 2020) // 2020년에 일어난 거래 내역
                .sorted(Comparator.comparing(Transaction::getValue)) // 거래값 기준 오름차순 정렬
                .collect(Collectors.toList());

    }

    // 4.2 거래 내역이 있는 거래자가 근무하는 모든 도시를 중복 없이 나열하라.
    public List<String> quiz2() {

        return transactions.stream() // 거래내역에서
                .map(t -> t.getTrader().getCity()) // 도시 Stream<String> 수집
                .distinct() // 중복 제거
                .collect(Collectors.toList());
    }

    // 4.3 서울에서 근무하는 모든 거래자를 찾아서 이름순서대로 정렬하라.
    public List<Trader> quiz3() {

        return transactions.stream() // 거래내역에서
                .map(Transaction::getTrader) // 거래자를 모두 찾아와서
                .distinct() // 중복 제거
                .filter(t -> t.getCity().equals("Seoul")) // 서울에서 근무하는 애들로 필터
                .sorted(Comparator.comparing(Trader::getName)) // 이름 순서대로 정렬
                .collect(Collectors.toList());
    }

    // 4.4 모든 거래자의 이름을 구분자(",")로 구분하여 정렬하라.
    public String quiz4() {

        return transactions.stream() // 거래 내역에서
                .map(t -> t.getTrader().getName()) // 모든 거래자의 이름을
                .distinct() // 중복 제거
                .sorted() // 오름차순 정렬
                .collect(Collectors.joining(",")); // "," 구분자로 정렬

    }

    // 4.5 부산에 거래자가 있는지를 확인하라.
    public boolean quiz5() {

        return transactions.stream() // 거래 내역에서
                .map(t -> t.getTrader().getCity()) // 도시를 가져와서
                .anyMatch(c -> c.equals("Busan")); // 부산이 있는지 체크
    }

    // 4.6 서울에 거주하는 거래자의 모든 거래 금액을 구하라.
    public List<Integer> quiz6() {

        return transactions.stream() // 거래 내역에서
                .filter(t -> t.getTrader().getCity().equals("Seoul")) // 서울에 사는 거래자들만 모아서
                .map(Transaction::getValue) // 거래 금액을 모아서
                .collect(Collectors.toList());
    }

    // 4.7 모든 거래 내역중에서 거래 금액의 최댓값과 최솟값을 구하라. 단, 최댓값은 reduce를 이용하고 최솟값은 stream의 min()을 이용하라.
    public Integer[] quiz7() {
        Integer[] arr = new Integer[2]; // 최댓값, 최솟값
        arr[0] = transactions.stream() // 거래 내역에서
                .map(Transaction::getValue) // 거래 금액을 가져와서
                .reduce(Integer::max) // Optional<Integer> 최댓값 (Binary 연산으로 두 숫자를 연속해서 비교해서 버블정렬처럼 비교)
                .orElse(-1); // null이면 -1

        arr[1] = transactions.stream() // 거래 내역에서
                .min(Comparator.comparingInt(Transaction::getValue)) // 거래 금액 기준으로 최솟값 구하기
                .orElseThrow(RuntimeException::new).getValue(); // Optional 처리

        return arr;
    }

}
