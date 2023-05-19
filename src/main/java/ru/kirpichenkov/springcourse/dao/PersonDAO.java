package ru.kirpichenkov.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.kirpichenkov.springcourse.models.Book;
import ru.kirpichenkov.springcourse.models.Person;

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

    public void save(Person person){
    jdbcTemplate.update("INSERT into person (full_name, year_of_birth)  Values(?,?)", person.getFullName(),person.getYearOfBirth());
    }

    public void update(int id, Person person){
        jdbcTemplate.update("UPDATE person SET full_name=?, year_of_birth=? where id=?", person.getFullName(),person.getYearOfBirth(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("Delete from person where id=?",id);
    }


//    public static void main(String[] args) {
//
//        PersonDAO test = new PersonDAO(JdbcTemplate);
//        System.out.println(test.index());
//    }
}
