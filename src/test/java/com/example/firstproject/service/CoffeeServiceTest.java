package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CoffeeServiceTest {

    @Autowired
    CoffeeService coffeeService;

    @Test
    void index() {
        List<Coffee> coffeeList = coffeeService.index();

        Coffee a = new Coffee(1L, "아메리카노", "4500");
        Coffee b = new Coffee(2L, "라떼", "5000");
        Coffee c = new Coffee(3L, "카페 모카", "5500");
        List<Coffee> expected = new ArrayList<Coffee>(Arrays.asList(a, b, c));

        assertEquals(expected.toString(), coffeeList.toString());
    }

    @Test
    void update_성공_존재하는_id와_title_content가_있는_dto_입력() {
        Long id = 1L;
        String title = "아메리";
        String price = "3000";
        CoffeeDto dto = new CoffeeDto(id, title, price);

        Coffee expected = new Coffee(id, title, price);

        Coffee coffee = coffeeService.update(id, dto);

        assertEquals(expected.toString(), coffee.toString());
    }

    @Test
    void update_성공_존재하는_id와_title만_있는_dto_입력() {
    }

    @Test
    void update_실패_존재하지_않는_id의_dto_입력() {

    }
}