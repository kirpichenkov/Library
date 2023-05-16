package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;
@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }
    public Person show(int id){
        return jdbcTemplate.query("Select * from person where id =?", new Object[]{id},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public List<Book> getBookByPersonId(int id){
        return jdbcTemplate.query("Select * from book where person_id=?",new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }


//    public static void main(String[] args) {
//
//        PersonDAO test = new PersonDAO(JdbcTemplate);
//        System.out.println(test.index());
//    }
}
