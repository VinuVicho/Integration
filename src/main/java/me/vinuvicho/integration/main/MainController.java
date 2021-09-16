package me.vinuvicho.integration.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping()
    public String confirm(Model model) {
        return "redirect:/post";
    }
}