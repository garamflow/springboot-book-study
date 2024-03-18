package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeDto coffeeDto) {
        Coffee coffee = coffeeDto.toEntity();

        if(coffee.getId() != null) {
            return null;
        }

        Coffee created = coffeeRepository.save(coffee);
        return created;
    }

    public Coffee update(Long id, CoffeeDto coffeeDto) {
        Coffee coffee = coffeeDto.toEntity();
        log.info("id: {}, coffee: {}", id, coffee.toString());
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if (target == null || id != coffee.getId()) {
            log.info("잘못된 요청 id: {}, coffee: {}", id, coffee.toString());
            return null;
        }

        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);
        return updated;
    }

    public Coffee delete(Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if (target == null) {
            return null;
        }

        coffeeRepository.delete(target);

        return target;
    }
}
