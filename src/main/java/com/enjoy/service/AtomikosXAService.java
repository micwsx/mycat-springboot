package com.enjoy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtomikosXAService {

//    @Qualifier("jdbcTemplate")
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Qualifier("jdbcTemplate2")
//    @Autowired
//    private JdbcTemplate jdbcTemplate2;

//    @Transactional
//    public String transfer(int money) {
//        int resultJames = jdbcTemplate.update("INSERT INTO bank_boc(account,amount)VALUES (?,?)", "michael", -money);
//        int resultPeter = jdbcTemplate2.update("INSERT INTO bank_abc(account,amount)VALUES (?,?)", "perter", money);
//        if (money > 20) {
//            throw new RuntimeException("money too large");//系统宕机了怎么办？
//        }
//        return resultJames + "-" + resultPeter;
//    }

    /*table script:

    create table bank_boc(
        id bigint unsigned auto_increment primary key,
        account varchar(50) not null,
        amount decimal(20,2) not null,
        created datetime not null default current_timestamp
    );

    create table bank_abc(
        id bigint unsigned auto_increment primary key,
        account varchar(50) not null,
        amount decimal(20,2) not null,
        created datetime not null default current_timestamp
    );

    */

}
