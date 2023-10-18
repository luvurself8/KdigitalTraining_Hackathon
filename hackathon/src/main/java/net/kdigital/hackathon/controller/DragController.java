package net.kdigital.hackathon.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;
import net.kdigital.hackathon.domain.Data1;
import net.kdigital.hackathon.domain.Data2;
import net.kdigital.hackathon.service.DragService;

@Controller
@RequestMapping("/drag")
@Slf4j
public class DragController {
   @Autowired
   DragService dragService;
   
   @PostMapping("/busan")
    public String submitForm(
          @RequestParam("selectedOption") String selectedOption,
          Model model
          ) throws JsonMappingException, JsonProcessingException
   {
        if (selectedOption.equals("page1")) {
          LocalDateTime currentTime = LocalDateTime.now();
          DateTimeFormatter nowFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
          DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
          DateTimeFormatter HourFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            int minute = currentTime.getMinute();
            
            LocalDateTime timeStamp1, timeStamp2, timeStamp3, timeStamp4, timeStamp5;

            // 30분 이전인 경우
            if (minute < 30) {
               timeStamp1 = currentTime.withMinute(0); // 분을 00으로 설정
               timeStamp2 = currentTime.withMinute(30); // 분을 30으로 설정
               timeStamp3 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
               timeStamp4 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
               timeStamp5 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
            }
            
            // 30분 이후인 경우
            else {
               timeStamp1 = currentTime.withMinute(30); // 분을 30으로 설정
               timeStamp2 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
               timeStamp3 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
               timeStamp4 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
               timeStamp5 = currentTime.plusHours(2).withMinute(30); // 그 다음 시각의 30분으로 설정
            }

            String now = currentTime.format(nowFormatter);
            
            String test = timeStamp1.withSecond(0)
                  .format(HourFormatter);
            
            String formattedTimeStamp1 = timeStamp1.format(timeFormatter);
            String formattedTimeStamp2 = timeStamp2.format(timeFormatter);
            String formattedTimeStamp3 = timeStamp3.format(timeFormatter);
            String formattedTimeStamp4 = timeStamp4.format(timeFormatter);
            String formattedTimeStamp5 = timeStamp5.format(timeFormatter);
            
            model.addAttribute("now", now);
            model.addAttribute("timeStamp1", formattedTimeStamp1);
            model.addAttribute("timeStamp2", formattedTimeStamp2);
            model.addAttribute("timeStamp3", formattedTimeStamp3);
            model.addAttribute("timeStamp4", formattedTimeStamp4);
            model.addAttribute("timeStamp5", formattedTimeStamp5);
            
            // 유향, 유속
            Data1 data1 = dragService.api1(test);
            
            String current_direct = data1.getCurrent_direct();
            String current_speed = data1.getCurrent_speed();
            
            if(current_speed != "-") {
               current_speed = current_speed.substring(0, current_speed.length() - 2);
            }
            
            model.addAttribute("current_direct", current_direct);
            model.addAttribute("current_speed", current_speed);
            
            // 풍향, 풍속
            Data2 data2 = dragService.api2(test);
            
            String Wind_dir = data2.getWind_dir();
            String Wind_speed = data2.getWind_speed();
            
            if(Wind_dir != "-") {
               Wind_dir = Wind_dir.substring(0, Wind_dir.length() - 2);
            }
            
            model.addAttribute("Wind_dir", Wind_dir);
            model.addAttribute("Wind_speed", Wind_speed);
           
            return "/drag/drag_busan1";
        } 
        
        else if (selectedOption.equals("page2")) {
          LocalDateTime currentTime = LocalDateTime.now();
          DateTimeFormatter nowFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
          DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
          DateTimeFormatter HourFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            int minute = currentTime.getMinute();
            
            LocalDateTime timeStamp1, timeStamp2, timeStamp3, timeStamp4, timeStamp5;

            // 30분 이전인 경우
            if (minute < 30) {
               timeStamp1 = currentTime.withMinute(0); // 분을 00으로 설정
               timeStamp2 = currentTime.withMinute(30); // 분을 30으로 설정
               timeStamp3 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
               timeStamp4 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
               timeStamp5 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
            }
            
            // 30분 이후인 경우
            else {
               timeStamp1 = currentTime.withMinute(30); // 분을 30으로 설정
               timeStamp2 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
               timeStamp3 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
               timeStamp4 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
               timeStamp5 = currentTime.plusHours(2).withMinute(30); // 그 다음 시각의 30분으로 설정
            }

            String now = currentTime.format(nowFormatter);
            
            String test = timeStamp1.withSecond(0)
                  .format(HourFormatter);
            
            String formattedTimeStamp1 = timeStamp1.format(timeFormatter);
            String formattedTimeStamp2 = timeStamp2.format(timeFormatter);
            String formattedTimeStamp3 = timeStamp3.format(timeFormatter);
            String formattedTimeStamp4 = timeStamp4.format(timeFormatter);
            String formattedTimeStamp5 = timeStamp5.format(timeFormatter);
            
            model.addAttribute("now", now);
            model.addAttribute("timeStamp1", formattedTimeStamp1);
            model.addAttribute("timeStamp2", formattedTimeStamp2);
            model.addAttribute("timeStamp3", formattedTimeStamp3);
            model.addAttribute("timeStamp4", formattedTimeStamp4);
            model.addAttribute("timeStamp5", formattedTimeStamp5);
            
            // 유향, 유속
            Data1 data1 = dragService.api1(test);
            
            String current_direct = data1.getCurrent_direct();
            String current_speed = data1.getCurrent_speed();
            
            if(current_speed != "-") {
               current_speed = current_speed.substring(0, current_speed.length() - 2);
            }
            
            model.addAttribute("current_direct", current_direct);
            model.addAttribute("current_speed", current_speed);
            
            // 풍향, 풍속
            Data2 data2 = dragService.api2(test);
            
            String Wind_dir = data2.getWind_dir();
            String Wind_speed = data2.getWind_speed();
            
            if(Wind_dir != "-") {
               Wind_dir = Wind_dir.substring(0, Wind_dir.length() - 2);
            }
            
            model.addAttribute("Wind_dir", Wind_dir);
            model.addAttribute("Wind_speed", Wind_speed);
           
           return "/drag/drag_busan2";
        } 
        
        else if (selectedOption.equals("page3")) {
          LocalDateTime currentTime = LocalDateTime.now();
          DateTimeFormatter nowFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
          DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
          DateTimeFormatter HourFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            int minute = currentTime.getMinute();
            
            LocalDateTime timeStamp1, timeStamp2, timeStamp3, timeStamp4, timeStamp5;

            // 30분 이전인 경우
            if (minute < 30) {
               timeStamp1 = currentTime.withMinute(0); // 분을 00으로 설정
               timeStamp2 = currentTime.withMinute(30); // 분을 30으로 설정
               timeStamp3 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
               timeStamp4 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
               timeStamp5 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
            }
            
            // 30분 이후인 경우
            else {
               timeStamp1 = currentTime.withMinute(30); // 분을 30으로 설정
               timeStamp2 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
               timeStamp3 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
               timeStamp4 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
               timeStamp5 = currentTime.plusHours(2).withMinute(30); // 그 다음 시각의 30분으로 설정
            }

            String now = currentTime.format(nowFormatter);
            
            String test = timeStamp1.withSecond(0)
                  .format(HourFormatter);
            
            String formattedTimeStamp1 = timeStamp1.format(timeFormatter);
            String formattedTimeStamp2 = timeStamp2.format(timeFormatter);
            String formattedTimeStamp3 = timeStamp3.format(timeFormatter);
            String formattedTimeStamp4 = timeStamp4.format(timeFormatter);
            String formattedTimeStamp5 = timeStamp5.format(timeFormatter);
            
            model.addAttribute("now", now);
            model.addAttribute("timeStamp1", formattedTimeStamp1);
            model.addAttribute("timeStamp2", formattedTimeStamp2);
            model.addAttribute("timeStamp3", formattedTimeStamp3);
            model.addAttribute("timeStamp4", formattedTimeStamp4);
            model.addAttribute("timeStamp5", formattedTimeStamp5);
            
            // 유향, 유속
            Data1 data1 = dragService.api1(test);
            
            String current_direct = data1.getCurrent_direct();
            String current_speed = data1.getCurrent_speed();
            
            if(current_speed != "-") {
               current_speed = current_speed.substring(0, current_speed.length() - 2);
            }
            
            model.addAttribute("current_direct", current_direct);
            model.addAttribute("current_speed", current_speed);
            
            // 풍향, 풍속
            Data2 data2 = dragService.api2(test);
            
            String Wind_dir = data2.getWind_dir();
            String Wind_speed = data2.getWind_speed();
            
            if(Wind_dir != "-") {
               Wind_dir = Wind_dir.substring(0, Wind_dir.length() - 2);
            }
            
            model.addAttribute("Wind_dir", Wind_dir);
            model.addAttribute("Wind_speed", Wind_speed);
           
           return "/drag/drag_busan3";
        } else if (selectedOption.equals("page4")) {
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter nowFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter HourFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

              int minute = currentTime.getMinute();
              
              LocalDateTime timeStamp1, timeStamp2, timeStamp3, timeStamp4, timeStamp5;

              // 30분 이전인 경우
              if (minute < 30) {
                 timeStamp1 = currentTime.withMinute(0); // 분을 00으로 설정
                 timeStamp2 = currentTime.withMinute(30); // 분을 30으로 설정
                 timeStamp3 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
                 timeStamp4 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
                 timeStamp5 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
              }
              
              // 30분 이후인 경우
              else {
                 timeStamp1 = currentTime.withMinute(30); // 분을 30으로 설정
                 timeStamp2 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
                 timeStamp3 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
                 timeStamp4 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
                 timeStamp5 = currentTime.plusHours(2).withMinute(30); // 그 다음 시각의 30분으로 설정
              }

              String now = currentTime.format(nowFormatter);
              
              String test = timeStamp1.withSecond(0)
                    .format(HourFormatter);
              
              String formattedTimeStamp1 = timeStamp1.format(timeFormatter);
              String formattedTimeStamp2 = timeStamp2.format(timeFormatter);
              String formattedTimeStamp3 = timeStamp3.format(timeFormatter);
              String formattedTimeStamp4 = timeStamp4.format(timeFormatter);
              String formattedTimeStamp5 = timeStamp5.format(timeFormatter);
              
              model.addAttribute("now", now);
              model.addAttribute("timeStamp1", formattedTimeStamp1);
              model.addAttribute("timeStamp2", formattedTimeStamp2);
              model.addAttribute("timeStamp3", formattedTimeStamp3);
              model.addAttribute("timeStamp4", formattedTimeStamp4);
              model.addAttribute("timeStamp5", formattedTimeStamp5);
              
              // 유향, 유속
              Data1 data1 = dragService.api1(test);
              
              String current_direct = data1.getCurrent_direct();
              String current_speed = data1.getCurrent_speed();
              
              if(current_speed != "-") {
                 current_speed = current_speed.substring(0, current_speed.length() - 2);
              }
              
              model.addAttribute("current_direct", current_direct);
              model.addAttribute("current_speed", current_speed);
              
              // 풍향, 풍속
              Data2 data2 = dragService.api2(test);
              
              String Wind_dir = data2.getWind_dir();
              String Wind_speed = data2.getWind_speed();
              
              if(Wind_dir != "-") {
                 Wind_dir = Wind_dir.substring(0, Wind_dir.length() - 2);
              }
              
              model.addAttribute("Wind_dir", Wind_dir);
              model.addAttribute("Wind_speed", Wind_speed);
             
             return "/drag/drag_busan4";
          } 
        
        else {
            return "redirect:/";
        }
    }
   
   @PostMapping("/ulsan")
    public String submitFormUlsan(
          @RequestParam("selectedOption") String selectedOption,
          Model model
          ) throws JsonMappingException, JsonProcessingException
   {
        if (selectedOption.equals("page1")) {
          LocalDateTime currentTime = LocalDateTime.now();
          DateTimeFormatter nowFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
          DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
          DateTimeFormatter HourFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            int minute = currentTime.getMinute();
            
            LocalDateTime timeStamp1, timeStamp2, timeStamp3, timeStamp4, timeStamp5;

            // 30분 이전인 경우
            if (minute < 30) {
               timeStamp1 = currentTime.withMinute(0); // 분을 00으로 설정
               timeStamp2 = currentTime.withMinute(30); // 분을 30으로 설정
               timeStamp3 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
               timeStamp4 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
               timeStamp5 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
            }
            
            // 30분 이후인 경우
            else {
               timeStamp1 = currentTime.withMinute(30); // 분을 30으로 설정
               timeStamp2 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
               timeStamp3 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
               timeStamp4 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
               timeStamp5 = currentTime.plusHours(2).withMinute(30); // 그 다음 시각의 30분으로 설정
            }

            String now = currentTime.format(nowFormatter);
            
            String test = timeStamp1.withSecond(0)
                  .format(HourFormatter);
            
            String formattedTimeStamp1 = timeStamp1.format(timeFormatter);
            String formattedTimeStamp2 = timeStamp2.format(timeFormatter);
            String formattedTimeStamp3 = timeStamp3.format(timeFormatter);
            String formattedTimeStamp4 = timeStamp4.format(timeFormatter);
            String formattedTimeStamp5 = timeStamp5.format(timeFormatter);
            
            model.addAttribute("now", now);
            model.addAttribute("timeStamp1", formattedTimeStamp1);
            model.addAttribute("timeStamp2", formattedTimeStamp2);
            model.addAttribute("timeStamp3", formattedTimeStamp3);
            model.addAttribute("timeStamp4", formattedTimeStamp4);
            model.addAttribute("timeStamp5", formattedTimeStamp5);
            
            // 유향, 유속
            Data1 data1 = dragService.api1(test);
            
            String current_direct = data1.getCurrent_direct();
            String current_speed = data1.getCurrent_speed();
            
            if(current_speed != "-") {
               current_speed = current_speed.substring(0, current_speed.length() - 2);
            }
            
            model.addAttribute("current_direct", current_direct);
            model.addAttribute("current_speed", current_speed);
            
            // 풍향, 풍속
            Data2 data2 = dragService.api2(test);
            
            String Wind_dir = data2.getWind_dir();
            String Wind_speed = data2.getWind_speed();
            
            if(Wind_dir != "-") {
               Wind_dir = Wind_dir.substring(0, Wind_dir.length() - 2);
            }
            
            model.addAttribute("Wind_dir", Wind_dir);
            model.addAttribute("Wind_speed", Wind_speed);
           
            return "/drag/drag_ulsan1";
        } 
        
        else if (selectedOption.equals("page2")) {
          LocalDateTime currentTime = LocalDateTime.now();
          DateTimeFormatter nowFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
          DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
          DateTimeFormatter HourFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            int minute = currentTime.getMinute();
            
            LocalDateTime timeStamp1, timeStamp2, timeStamp3, timeStamp4, timeStamp5;

            // 30분 이전인 경우
            if (minute < 30) {
               timeStamp1 = currentTime.withMinute(0); // 분을 00으로 설정
               timeStamp2 = currentTime.withMinute(30); // 분을 30으로 설정
               timeStamp3 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
               timeStamp4 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
               timeStamp5 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
            }
            
            // 30분 이후인 경우
            else {
               timeStamp1 = currentTime.withMinute(30); // 분을 30으로 설정
               timeStamp2 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
               timeStamp3 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
               timeStamp4 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
               timeStamp5 = currentTime.plusHours(2).withMinute(30); // 그 다음 시각의 30분으로 설정
            }

            String now = currentTime.format(nowFormatter);
            
            String test = timeStamp1.withSecond(0)
                  .format(HourFormatter);
            
            String formattedTimeStamp1 = timeStamp1.format(timeFormatter);
            String formattedTimeStamp2 = timeStamp2.format(timeFormatter);
            String formattedTimeStamp3 = timeStamp3.format(timeFormatter);
            String formattedTimeStamp4 = timeStamp4.format(timeFormatter);
            String formattedTimeStamp5 = timeStamp5.format(timeFormatter);
            
            model.addAttribute("now", now);
            model.addAttribute("timeStamp1", formattedTimeStamp1);
            model.addAttribute("timeStamp2", formattedTimeStamp2);
            model.addAttribute("timeStamp3", formattedTimeStamp3);
            model.addAttribute("timeStamp4", formattedTimeStamp4);
            model.addAttribute("timeStamp5", formattedTimeStamp5);
            
            // 유향, 유속
            Data1 data1 = dragService.api1(test);
            
            String current_direct = data1.getCurrent_direct();
            String current_speed = data1.getCurrent_speed();
            
            if(current_speed != "-") {
               current_speed = current_speed.substring(0, current_speed.length() - 2);
            }
            
            model.addAttribute("current_direct", current_direct);
            model.addAttribute("current_speed", current_speed);
            
            // 풍향, 풍속
            Data2 data2 = dragService.api2(test);
            
            String Wind_dir = data2.getWind_dir();
            String Wind_speed = data2.getWind_speed();
            
            if(Wind_dir != "-") {
               Wind_dir = Wind_dir.substring(0, Wind_dir.length() - 2);
            }
            
            model.addAttribute("Wind_dir", Wind_dir);
            model.addAttribute("Wind_speed", Wind_speed);
           
           return "/drag/drag_ulsan2";
        } 
        
        else if (selectedOption.equals("page3")) {
          LocalDateTime currentTime = LocalDateTime.now();
          DateTimeFormatter nowFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
          DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
          DateTimeFormatter HourFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            int minute = currentTime.getMinute();
            
            LocalDateTime timeStamp1, timeStamp2, timeStamp3, timeStamp4, timeStamp5;

            // 30분 이전인 경우
            if (minute < 30) {
               timeStamp1 = currentTime.withMinute(0); // 분을 00으로 설정
               timeStamp2 = currentTime.withMinute(30); // 분을 30으로 설정
               timeStamp3 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
               timeStamp4 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
               timeStamp5 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
            }
            
            // 30분 이후인 경우
            else {
               timeStamp1 = currentTime.withMinute(30); // 분을 30으로 설정
               timeStamp2 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
               timeStamp3 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
               timeStamp4 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
               timeStamp5 = currentTime.plusHours(2).withMinute(30); // 그 다음 시각의 30분으로 설정
            }

            String now = currentTime.format(nowFormatter);
            
            String test = timeStamp1.withSecond(0)
                  .format(HourFormatter);
            
            String formattedTimeStamp1 = timeStamp1.format(timeFormatter);
            String formattedTimeStamp2 = timeStamp2.format(timeFormatter);
            String formattedTimeStamp3 = timeStamp3.format(timeFormatter);
            String formattedTimeStamp4 = timeStamp4.format(timeFormatter);
            String formattedTimeStamp5 = timeStamp5.format(timeFormatter);
            
            model.addAttribute("now", now);
            model.addAttribute("timeStamp1", formattedTimeStamp1);
            model.addAttribute("timeStamp2", formattedTimeStamp2);
            model.addAttribute("timeStamp3", formattedTimeStamp3);
            model.addAttribute("timeStamp4", formattedTimeStamp4);
            model.addAttribute("timeStamp5", formattedTimeStamp5);
            
            // 유향, 유속
            Data1 data1 = dragService.api1(test);
            
            String current_direct = data1.getCurrent_direct();
            String current_speed = data1.getCurrent_speed();
            
            if(current_speed != "-") {
               current_speed = current_speed.substring(0, current_speed.length() - 2);
            }
            
            model.addAttribute("current_direct", current_direct);
            model.addAttribute("current_speed", current_speed);
            
            // 풍향, 풍속
            Data2 data2 = dragService.api2(test);
            
            String Wind_dir = data2.getWind_dir();
            String Wind_speed = data2.getWind_speed();
            
            if(Wind_dir != "-") {
               Wind_dir = Wind_dir.substring(0, Wind_dir.length() - 2);
            }
            
            model.addAttribute("Wind_dir", Wind_dir);
            model.addAttribute("Wind_speed", Wind_speed);
           
           return "/drag/drag_ulsan3";
        } else if (selectedOption.equals("page4")) {
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter nowFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter HourFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

              int minute = currentTime.getMinute();
              
              LocalDateTime timeStamp1, timeStamp2, timeStamp3, timeStamp4, timeStamp5;

              // 30분 이전인 경우
              if (minute < 30) {
                 timeStamp1 = currentTime.withMinute(0); // 분을 00으로 설정
                 timeStamp2 = currentTime.withMinute(30); // 분을 30으로 설정
                 timeStamp3 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
                 timeStamp4 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
                 timeStamp5 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
              }
              
              // 30분 이후인 경우
              else {
                 timeStamp1 = currentTime.withMinute(30); // 분을 30으로 설정
                 timeStamp2 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
                 timeStamp3 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
                 timeStamp4 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
                 timeStamp5 = currentTime.plusHours(2).withMinute(30); // 그 다음 시각의 30분으로 설정
              }

              String now = currentTime.format(nowFormatter);
              
              String test = timeStamp1.withSecond(0)
                    .format(HourFormatter);
              
              String formattedTimeStamp1 = timeStamp1.format(timeFormatter);
              String formattedTimeStamp2 = timeStamp2.format(timeFormatter);
              String formattedTimeStamp3 = timeStamp3.format(timeFormatter);
              String formattedTimeStamp4 = timeStamp4.format(timeFormatter);
              String formattedTimeStamp5 = timeStamp5.format(timeFormatter);
              
              model.addAttribute("now", now);
              model.addAttribute("timeStamp1", formattedTimeStamp1);
              model.addAttribute("timeStamp2", formattedTimeStamp2);
              model.addAttribute("timeStamp3", formattedTimeStamp3);
              model.addAttribute("timeStamp4", formattedTimeStamp4);
              model.addAttribute("timeStamp5", formattedTimeStamp5);
              
              // 유향, 유속
              Data1 data1 = dragService.api1(test);
              
              String current_direct = data1.getCurrent_direct();
              String current_speed = data1.getCurrent_speed();
              
              if(current_speed != "-") {
                 current_speed = current_speed.substring(0, current_speed.length() - 2);
              }
              
              model.addAttribute("current_direct", current_direct);
              model.addAttribute("current_speed", current_speed);
              
              // 풍향, 풍속
              Data2 data2 = dragService.api2(test);
              
              String Wind_dir = data2.getWind_dir();
              String Wind_speed = data2.getWind_speed();
              
              if(Wind_dir != "-") {
                 Wind_dir = Wind_dir.substring(0, Wind_dir.length() - 2);
              }
              
              model.addAttribute("Wind_dir", Wind_dir);
              model.addAttribute("Wind_speed", Wind_speed);
             
             return "/drag/drag_ulsan4";
          } 
        
        else {
            return "redirect:/";
        }
    }
   
   @GetMapping("/ulsan")
   public String drag_(Model model) throws JsonMappingException, JsonProcessingException {
      LocalDateTime currentTime = LocalDateTime.now();
      DateTimeFormatter nowFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
      DateTimeFormatter HourFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        int minute = currentTime.getMinute();
        
        LocalDateTime timeStamp1, timeStamp2, timeStamp3, timeStamp4, timeStamp5;

        // 30분 이전인 경우
        if (minute < 30) {
           timeStamp1 = currentTime.withMinute(0); // 분을 00으로 설정
           timeStamp2 = currentTime.withMinute(30); // 분을 30으로 설정
           timeStamp3 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
           timeStamp4 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
           timeStamp5 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
        }
        
        // 30분 이후인 경우
        else {
           timeStamp1 = currentTime.withMinute(30); // 분을 30으로 설정
           timeStamp2 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
           timeStamp3 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
           timeStamp4 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
           timeStamp5 = currentTime.plusHours(2).withMinute(30); // 그 다음 시각의 30분으로 설정
        }

        String now = currentTime.format(nowFormatter);
        
        String test = timeStamp1.withSecond(0)
              .format(HourFormatter);
        
        String formattedTimeStamp1 = timeStamp1.format(timeFormatter);
        String formattedTimeStamp2 = timeStamp2.format(timeFormatter);
        String formattedTimeStamp3 = timeStamp3.format(timeFormatter);
        String formattedTimeStamp4 = timeStamp4.format(timeFormatter);
        String formattedTimeStamp5 = timeStamp5.format(timeFormatter);
        
        model.addAttribute("now", now);
        model.addAttribute("timeStamp1", formattedTimeStamp1);
        model.addAttribute("timeStamp2", formattedTimeStamp2);
        model.addAttribute("timeStamp3", formattedTimeStamp3);
        model.addAttribute("timeStamp4", formattedTimeStamp4);
        model.addAttribute("timeStamp5", formattedTimeStamp5);
        
        // 유향, 유속
        Data1 data1 = dragService.api1(test);
        
        String current_direct = data1.getCurrent_direct();
        String current_speed = data1.getCurrent_speed();
        
        if(current_speed != "-") {
           current_speed = current_speed.substring(0, current_speed.length() - 2);
        }
        
        model.addAttribute("current_direct", current_direct);
        model.addAttribute("current_speed", current_speed);
        
        // 풍향, 풍속
        Data2 data2 = dragService.api2(test);
        
        String Wind_dir = data2.getWind_dir();
        String Wind_speed = data2.getWind_speed();
        
        if(Wind_dir != "-") {
           Wind_dir = Wind_dir.substring(0, Wind_dir.length() - 2);
        }
        
        model.addAttribute("Wind_dir", Wind_dir);
        model.addAttribute("Wind_speed", Wind_speed);
        
      return "/drag/drag_ulsan1";
   }
   
   @GetMapping("/busan")
   public String drag(Model model) throws JsonMappingException, JsonProcessingException {
      LocalDateTime currentTime = LocalDateTime.now();
      DateTimeFormatter nowFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
      DateTimeFormatter HourFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        int minute = currentTime.getMinute();
        
        LocalDateTime timeStamp1, timeStamp2, timeStamp3, timeStamp4, timeStamp5;

        // 30분 이전인 경우
        if (minute < 30) {
           timeStamp1 = currentTime.withMinute(0); // 분을 00으로 설정
           timeStamp2 = currentTime.withMinute(30); // 분을 30으로 설정
           timeStamp3 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
           timeStamp4 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
           timeStamp5 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
        }
        
        // 30분 이후인 경우
        else {
           timeStamp1 = currentTime.withMinute(30); // 분을 30으로 설정
           timeStamp2 = currentTime.plusHours(1).withMinute(0); // 다음 시각의 00분으로 설정
           timeStamp3 = currentTime.plusHours(1).withMinute(30); // 다음 시각의 30분으로 설정
           timeStamp4 = currentTime.plusHours(2).withMinute(00); // 그 다음 시각의 00분으로 설정
           timeStamp5 = currentTime.plusHours(2).withMinute(30); // 그 다음 시각의 30분으로 설정
        }

        String now = currentTime.format(nowFormatter);
        
        String test = timeStamp1.withSecond(0)
              .format(HourFormatter);
        
        String formattedTimeStamp1 = timeStamp1.format(timeFormatter);
        String formattedTimeStamp2 = timeStamp2.format(timeFormatter);
        String formattedTimeStamp3 = timeStamp3.format(timeFormatter);
        String formattedTimeStamp4 = timeStamp4.format(timeFormatter);
        String formattedTimeStamp5 = timeStamp5.format(timeFormatter);
        
        model.addAttribute("now", now);
        model.addAttribute("timeStamp1", formattedTimeStamp1);
        model.addAttribute("timeStamp2", formattedTimeStamp2);
        model.addAttribute("timeStamp3", formattedTimeStamp3);
        model.addAttribute("timeStamp4", formattedTimeStamp4);
        model.addAttribute("timeStamp5", formattedTimeStamp5);
        
        // 유향, 유속
        Data1 data1 = dragService.api1(test);
        
        String current_direct = data1.getCurrent_direct();
        String current_speed = data1.getCurrent_speed();
        
        if(current_speed != "-") {
           current_speed = current_speed.substring(0, current_speed.length() - 2);
        }
        
        model.addAttribute("current_direct", current_direct);
        model.addAttribute("current_speed", current_speed);
        
        // 풍향, 풍속
        Data2 data2 = dragService.api2(test);
        
        String Wind_dir = data2.getWind_dir();
        String Wind_speed = data2.getWind_speed();
        
        if(Wind_dir != "-") {
           Wind_dir = Wind_dir.substring(0, Wind_dir.length() - 2);
        }
        
        model.addAttribute("Wind_dir", Wind_dir);
        model.addAttribute("Wind_speed", Wind_speed);
      
      return "/drag/drag_busan1";
   }
}