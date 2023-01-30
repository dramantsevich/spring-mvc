package org.example.controllers;

import org.example.daos.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getPeopleList(Model model) {
        model.addAttribute("peopleList", personDAO.getPersonList());

        return "people/peopleList";
    }

    @GetMapping("/{id}")
    public String getPeople(@PathVariable("id") int id, Model model) {
        model.addAttribute("people", personDAO.getPersonById(id));

        return "people/people";
    }
}
