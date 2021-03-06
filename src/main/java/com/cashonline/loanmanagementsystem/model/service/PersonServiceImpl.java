package com.cashonline.loanmanagementsystem.model.service;

import com.cashonline.loanmanagementsystem.persistence.dao.PersonDAO;
import com.cashonline.loanmanagementsystem.model.entities.Person;
import com.jasongoodwin.monads.Try;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("PersonService")
public class PersonServiceImpl implements PersonService {

    private final PersonDAO personDAO;

    @Autowired
    public PersonServiceImpl(@Qualifier("PersonDAO") PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public Optional<Person> getPerson(Integer i) {
        return personDAO.findPerson(i);
    }

    public Try addPerson(Person person) {
        return  personDAO.savePerson(person);
    }

    public void removePerson(Integer l) {
        try {
            personDAO.deletePerson(l);
        }
        catch(Exception e){
            LoggerFactory.getLogger(PersonServiceImpl.class).warn("Removing person failed, id="+l);
        }

    }

    public void updatePerson(Person p) {
        personDAO.updatePerson(p);
    }

    private static class PersonInExistenceException extends Throwable {
    }
}
