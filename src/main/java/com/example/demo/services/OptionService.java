package com.example.demo.services;

import com.example.demo.entity.Option;
import com.example.demo.repos.OptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {

    private final OptionRepo optionRepo;

    @Autowired
    public OptionService(OptionRepo optionRepo) {
        this.optionRepo = optionRepo;
    }

    public List<Option> getOptions() {
        return optionRepo.findAll();
    }
}
