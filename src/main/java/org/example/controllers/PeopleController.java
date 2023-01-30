package org.example.controllers;

import org.example.daos.PersonDAO;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    private static final String REDIRECT_PEOPLE = "redirect:/people";

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getPeopleList(Model model) {
        model.addAttribute("people", personDAO.getPersonList());

        return "people/peopleList";
    }

    @GetMapping("/{id}")
    public String getPeople(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPersonById(id));

        return "people/people";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {
        personDAO.save(person);

        return REDIRECT_PEOPLE;
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.getPersonById(id));

        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        personDAO.update(id, person);

        return REDIRECT_PEOPLE;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);

        return REDIRECT_PEOPLE;
    }
}
