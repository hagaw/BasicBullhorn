package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller

public class HomeController {


    @Autowired
    BullHornRepository BullHornRepository;


    @RequestMapping("/")

    public String myBullHorn(Model model) {
        model.addAttribute("bullhorn", BullHornRepository.findAll());
        return "bulllist";
    }

    @GetMapping("/add")
    public String bullHornForm(Model model) {
        model.addAttribute("bullhorn", new BullHorn());
        return "bullhorn";

    }

    @PostMapping("/process")

    public String processForm(@Valid @ModelAttribute("bullhorn") BullHorn bullhorn,
                              BindingResult resualt) {
        if (resualt.hasErrors()) {

            return "bullhorn";

        }

        BullHornRepository.save(bullhorn);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model) {
        model.addAttribute("bullhorn", BullHornRepository.findById(id));
        return "show";

    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model) {
        model.addAttribute("bullhorn", BullHornRepository.findById(id));
        return "bullhorn";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id, Model model) {
        model.addAttribute("bullhorn", BullHornRepository.findById(id));
        return "redirect";

    }
}