package com.lookback.common.converter;

import java.time.*;
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
        if(dateString == null) {
            return "";
        }
        // 입력 날짜를 LocalDate로 변환
        LocalDate date = LocalDate.parse(dateString);

        return getFormatDate(date);
    }

    //올해일 경우 10월 1일, 올해가 아닐 경우 24년 10월 1일 데이터로 변환
    public static String getFormatDate(LocalDate date) {
        if(date == null) {
            return "";
        }

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

    //20240801 -> "2024.08.01"
    public static String formatDataOfDot(Long dateLong) {
        if(dateLong == null) {
            return "";
        }

        // 입력 날짜를 LocalDate로 변환
        String dateString = String.valueOf(dateLong);
        // "yyyyMMdd" 형식으로 파싱
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(dateString, inputFormatter);

        // 원하는 형식으로 출력
        return String.format("%04d.%02d.%02d", date.getYear(), date.getMonthValue(), date.getDayOfMonth());

    }

    public static String getAge(String birthDateString) {
        // 문자열을 날짜로 파싱 (예: 19990801 -> 1999-08-01)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter);

        // 오늘 날짜
        LocalDate today = LocalDate.now();

        // 기간 계산
        int age = Period.between(birthDate, today).getYears();

        // 아직 생일이 안 지났으면 -1
        if (today.getMonthValue() < birthDate.getMonthValue() ||
                (today.getMonthValue() == birthDate.getMonthValue() && today.getDayOfMonth() < birthDate.getDayOfMonth())) {
            age--;
        }

        return String.valueOf(age);
    }



    public static String formatWeekOfKorea(String dateString) {
        if(dateString == null) {
            return "";
        }
        // 입력 날짜를 LocalDate로 변환
        LocalDate date = LocalDate.parse(dateString);

        String dayOfWeekKorean = getDayOfWeekKorean(date);

        return dayOfWeekKorean;
    }

    // 출력: 2025/07/12 14:15
    public static String formatLocalDateTime(LocalDateTime date) {
        LocalDateTime now = LocalDateTime.of(2025, 7, 12, 14, 15);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return date.format(formatter);
    }

    public static String getDayOfWeekKorean(LocalDate date) {
        // 현재 연도 가져오기
        int currentYear = LocalDate.now().getYear();
        int targetYear = date.getYear();

        // 요일 가져오기 (한글 요일로 변환)
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String dayOfWeekKorean = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
        return dayOfWeekKorean;
    }

    public static String convertGender(String gender) {
        if(gender == null) {
            return "";
        }
        if (gender.equals("M")) {return "남자";}
        if (gender.equals("W")) {return "여자";}
        return "";
    }

    public static LocalDate convertStringToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parse = LocalDate.parse(dateString, formatter);
        return parse;
    }
    //13:15:00 -> 13:15
    public static LocalTime convertStringToLocalTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime parse = LocalTime.parse(timeString, formatter);
        return parse;
    }

    //13:15:00 -> 오후 1:15
    public static String convertLocalTimeToKorString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("a h:mm", Locale.KOREAN);
        return time.format(formatter);
    }

    //13:15:00 -> 1:15
    public static String convertLocalTimeToKorHourMinuteString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm");
        return time.format(formatter);
    }
}
