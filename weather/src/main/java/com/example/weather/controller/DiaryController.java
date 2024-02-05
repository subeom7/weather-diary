package com.example.weather.controller;

import com.example.weather.domain.Diary;
import com.example.weather.service.DiaryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @ApiOperation(value = "일기 텍스트와 날씨를 이용해서 DB에 일기저장", notes = "일기를 생성하는 api입니다")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "날짜 형식: yyyy-mm-dd", example = "2024-01-01") LocalDate date,
                     @RequestBody String text) throws IOException {
        diaryService.createDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                          @ApiParam(value = "날짜 형식: yyyy-mm-dd", example = "2024-01-01") LocalDate date){
        return diaryService.readDiary(date);
    }

    @ApiOperation("선택한 기간중의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
                            @ApiParam(value = "조회할 기간의 첫번째 날", example = "2024-01-01") LocalDate startDate,
                            @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
                            @ApiParam(value = "조회할 기간의 마지막 날", example = "2024-01-01") LocalDate endDate){
        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation("선택한 날짜의 일기 데이터를 수정합니다")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "날짜 형식: yyyy-mm-dd", example = "2024-01-01")LocalDate date,
                     @RequestBody String text){
        diaryService.updateDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 일기 데이터를 삭제합니다")
    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
                     @ApiParam(value = "날짜 형식: yyyy-mm-dd", example = "2024-01-01") LocalDate date){
        diaryService.deleteDiary(date);
    }

}
