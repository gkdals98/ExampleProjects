package com.example.cm.entity.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.cm.entity.cmUserAccountEntity;

public interface cmUserAccountRepository extends CrudRepository<cmUserAccountEntity, String>{

    Long countByUserid(String userid);
}
