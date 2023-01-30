package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {
        model.addAttribute("message", name + " " + surname);

        return "/first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage() {
        return "/first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculatorPage(@RequestParam(value = "first_value") int first_value,
                                 @RequestParam(value = "second_value") int second_value,
                                 @RequestParam(value = "action") String action,
                                 Model model) {
        double result;

        switch(action) {
            case "multiplication":
                result = first_value * second_value;
                break;
            case "addition":
                result = first_value + second_value;
                break;
            case "subtraction":
                result = first_value - second_value;
                break;
            case "division":
                result = first_value / (double) second_value;
                break;
            default:
                result = 0;
                break;
        }

        model.addAttribute("result", result);

        return "/first/calculator";
    }
}
