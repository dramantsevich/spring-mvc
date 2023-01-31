package org.example.daos;

import org.example.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private final List<Person> personList;

    {
        personList = new ArrayList<>();

        personList.add(new Person(++PEOPLE_COUNT, "Dima", 22, "dzmitry.ram@gmail.com"));
        personList.add(new Person(++PEOPLE_COUNT, "Diana", 21, "dziyana.skaromnikava@gmail.com"));
        personList.add(new Person(++PEOPLE_COUNT, "Pasha", 21, "pasha.stankut@gmail.com"));
        personList.add(new Person(++PEOPLE_COUNT, "Artiom", 26, "artsiom.ramantsevich@gmail.com"));
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public Person getPersonById(final int id) {
        return personList.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        personList.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = getPersonById(id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        personList.removeIf(p -> p.getId() == id);
    }
}
