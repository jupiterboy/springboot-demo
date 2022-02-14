package com.jupiterboy.springboot.validation;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

    @RequestMapping("/foo")
    public String foo(@Validated Foo foo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            FieldError fieldError = (FieldError) bindingResult.getAllErrors().get(0);
            return fieldError.getDefaultMessage();
        }
        return "success";
    }

}