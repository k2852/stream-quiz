package com.mangkyu.stream.Quiz1;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Quiz1 {

    // 1.1 각 취미를 선호하는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz1() throws IOException {
        List<String[]> csvLines = readCsvLines();

        return csvLines.stream()
                .map(line -> line[1].replaceAll("\\s", "")) // line[] 배열의 두번째 인덱스인 취미란에서 정규식으로 공백 제거
                .flatMap(hobbies -> Arrays.stream(hobbies.split(":"))) // 공백이 제거된 취미를 flatMap으로 잘라서 Stream으로
                .collect(Collectors.toMap(hobby -> hobby, hobby -> 1, (oldValue, newValue) -> newValue += oldValue)); // 각 취미별로 빈도를 센다

    }

    // 1.2 각 취미를 선호하는 정씨 성을 갖는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz2() throws IOException {
        List<String[]> csvLines = readCsvLines();

        return csvLines.stream()
                .filter(line -> line[0].startsWith("정")) // line[] 배열의 첫번째 인덱스인 이름에서 정씨로 필터링
                .map(line -> line[1].replaceAll("\\s", "")) // 동일하게 취미에서 공백 제거
                .flatMap(hobbies -> Arrays.stream(hobbies.split(":"))) // 취미들만 flatMap으로 모음
                .collect(Collectors.toMap(hobby -> hobby, hobby -> 1, (oldValue, newValue) -> newValue += oldValue)); // 각 취미 빈도수
    }

    // 1.3 소개 내용에 '좋아'가 몇번 등장하는지 계산하여라.
    public int quiz3() throws IOException {
        List<String[]> csvLines = readCsvLines();

        return csvLines.stream()
                .map(line -> countContains(line[2], 0)) // 소개 내용에서 '좋아' 등장 횟수 카운트
                .reduce(0, Integer::sum); // 각 String에서의 카운트의 총합을 계산한 후 반환(Identity)
    }

    private static final String TARGET = "좋아";
    private int countContains(String src, int fromIndex) {
        int index = src.indexOf(TARGET, fromIndex);
        if(index >= 0) {
            return 1 + countContains(src , index + TARGET.length());
        }
        return 0;
    }

    private List<String[]> readCsvLines() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
        csvReader.readNext();
        return csvReader.readAll();
    }

}
