package com.vector.authorbookweb.controler;

import com.vector.authorbookcommon.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

        private final AuthorService authorService;

    @GetMapping("/")
    public String mainPage(ModelMap modelMap) {
        modelMap.addAttribute("authors", authorService.findAll());
        return "index";
    }
}
