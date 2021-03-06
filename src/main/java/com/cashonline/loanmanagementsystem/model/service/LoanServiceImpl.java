package com.cashonline.loanmanagementsystem.model.service;

import com.cashonline.loanmanagementsystem.model.entities.Loan;
import com.cashonline.loanmanagementsystem.model.entities.Person;
import com.cashonline.loanmanagementsystem.model.requestmodel.Page;
import com.cashonline.loanmanagementsystem.persistence.dao.*;
import com.cashonline.loanmanagementsystem.model.responsemodel.PagedLoans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("LoanService")
public class LoanServiceImpl implements LoanService {

    private final PersonDAO personDao;
    private final LoanDAO loanDao;

    @Autowired
    public LoanServiceImpl(@Qualifier("PersonDAO") PersonDAO personDao, @Qualifier("LoanDAO")  LoanDAO loan) {
        this.personDao = personDao;
        this.loanDao = loan;
    }

    public List<Loan> getLoanByPersonId(Integer i) {
        Optional<Person> person = personDao.findPerson(i);
        return person.map(Person::getLoans).orElse(new ArrayList<>());
    }

    @Override
    public PagedLoans getLoans(Page page) {

        return loanDao.getLoansPaged(page);
    }
}
