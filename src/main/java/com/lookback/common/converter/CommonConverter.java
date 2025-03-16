package com.lookback.common.converter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class CommonConverter {

    /**
     * 주어진 생년월일(Long 타입, yyyyMMdd 형태)을 기반으로 현재 나이를 계산합니다.
     *
     * @param birthDt 생년월일 (형식: yyyyMMdd, Long 타입)
     * @return 나이 (int)
     */
    public static Long ageConverter(Long birthDt) {

        if (birthDt == null) {
            return 00000000L;
        }

        // Long 값을 String으로 변환
        String birthDateString = birthDt.toString();

        // 생년월일을 LocalDate로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate parsedBirthDate = LocalDate.parse(birthDateString, formatter);

        // 현재 날짜
        LocalDate currentDate = LocalDate.now();

        // 나이를 계산 (현재 날짜 기준)
        return ChronoUnit.YEARS.between(parsedBirthDate, currentDate);
    }

    public static String formatLocalDateTime(LocalDate dateTime) {
            if (dateTime == null) {
                return null;
            }
            int currentYear = LocalDateTime.now().getYear();
            int year = dateTime.getYear();

            DateTimeFormatter formatter;
            if (year == currentYear) {
                //올해면 년도 삭제
                formatter = DateTimeFormatter.ofPattern("MM/dd");
            } else {
                formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
            }

            return dateTime.format(formatter);
    }

    //올해일 경우 10월 1일, 올해가 아닐 경우 24년 10월 1일 데이터로 변환
    public static String formatData(String dateString) {
        // 입력 날짜를 LocalDate로 변환
        LocalDate date = LocalDate.parse(dateString);

        // 현재 연도 가져오기
        int currentYear = LocalDate.now().getYear();
        int targetYear = date.getYear();

        // 날짜 포맷 결정
        if (targetYear == currentYear) {
            return String.format("%d월 %d일", date.getMonthValue(), date.getDayOfMonth());
        } else {
            return String.format("%d년 %d월 %d일", targetYear % 100, date.getMonthValue(), date.getDayOfMonth());
        }
    }

    public static String formatWeekOfKorea(String dateString) {
        // 입력 날짜를 LocalDate로 변환
        LocalDate date = LocalDate.parse(dateString);

        // 현재 연도 가져오기
        int currentYear = LocalDate.now().getYear();
        int targetYear = date.getYear();

        // 요일 가져오기 (한글 요일로 변환)
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String dayOfWeekKorean = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);

        return dayOfWeekKorean;
    }

}
