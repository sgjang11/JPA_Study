package com.example.jpaex.repository;

import com.example.jpaex.entity.CrudEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CrudEntityRepository extends JpaRepository<CrudEntity, String> {

    @Query(value = "select name, age from sample_member where name= :name", nativeQuery = true)
    List<CrudEntity> searchParamRepo(@Param("name") String name);
}
