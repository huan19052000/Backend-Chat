package com.example.bruce.api;

import com.example.bruce.dto.NewDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewApi {
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public NewDTO createNew(@RequestBody NewDTO model) {
        return model;
    }
}
