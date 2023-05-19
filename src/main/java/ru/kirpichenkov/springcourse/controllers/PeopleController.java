package ru.kirpichenkov.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kirpichenkov.springcourse.dao.PersonDAO;
import ru.kirpichenkov.springcourse.models.Person;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired//для автоматического создания personDao
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    //метод контроллера который передает гет запрос для отображения страницы с списокм всех людей
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }
    //метод контроллера, который (передает/или принимает?) гет запрос по отображению самой странички
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", personDAO.getBookByPersonId(id));

        return "people/show";
    }
    //у нас должна быть возможность создавать новых людей из страницы браузера и записывать в бд. По мне это магия - как это реализовать? сейчас узнаем
    @GetMapping("/new")
    //тысячу раз я уже сталкивался с этим вопросом и тысячу раз забывал на него ответ.
    //Зачем а главное как работает @ModelAtribute
    //
    public String newPerson(@ModelAttribute("person")Person person){
        return "people/new";
    }

    //POST запрос который будет отправлять данные в БД
    @PostMapping()
    public String create(@ModelAttribute("person") Person person){

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id")int id){
        //мы должны получить в форме сам объект поэтому передаем этот объект в форме
        model.addAttribute(("personn"), personDAO.show(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("personn") Person person, @PathVariable("id") int id){
        personDAO.update(id, person);
        return "redirect:/people";

    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }


}
