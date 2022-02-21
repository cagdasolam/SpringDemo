package com.example.demo.controller;

import com.example.demo.entity.Option;
import com.example.demo.request.OptionCreateRequest;
import com.example.demo.request.OptionUpdateRequest;
import com.example.demo.services.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/options")
public class OptionController {

    private final OptionService optionService;

    @Autowired
    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping
    public List<Option> getAllOptions(@RequestParam Optional<Long> surveyId) {
        return optionService.getAllOptions(surveyId);
    }

    @GetMapping("/{optionId}")
    public Option getOneOption(@PathVariable Long optionId){
        return optionService.getOneOption(optionId);
    }

    @PostMapping
    public Option addOption(@RequestBody OptionCreateRequest optionCreateRequest){
        return optionService.createOption(optionCreateRequest);
    }

    @PutMapping("/{optionId}")
    public Option updateOption(@PathVariable Long optionId, @RequestBody OptionUpdateRequest optionUpdateRequest){
        return optionService.updateOption(optionId, optionUpdateRequest);
    }

    @DeleteMapping("/{optionId}")
    public void deleteOption(@PathVariable Long optionId){
        optionService.deleteOption(optionId);
    }
}
