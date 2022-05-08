package com.example.bruce.api;

import com.example.bruce.dto.NewDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NewApi {
    private List<NewDTO> newDTOS = new ArrayList<>();
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public NewDTO createNew(@RequestBody NewDTO model) {
        return model;
    }

    @GetMapping("/new")
    public Object getAllNew() {
        return newDTOS;
    }
}