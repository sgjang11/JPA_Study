package com.example.jpaex.controller;

import com.example.jpaex.entity.CrudEntity;
import com.example.jpaex.repository.CrudEntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class CrudController {

    private final CrudEntityRepository crudEntityRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/search")
    public String search() {
        return crudEntityRepository.findAll().toString();
    }

    @GetMapping("/searchParamRepo")
    public String searchParamRepo(@RequestParam(value = "name") String name) {
        return crudEntityRepository.searchParamRepo(name).toString();
    }

    @GetMapping("/insert")
    public String insert(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age) {
        if(crudEntityRepository.findById(name).isPresent()) {
            return "동일한 이름이 이미 있습니다.";
        } else {
            CrudEntity crudEntity = CrudEntity.builder().name(name).age(age).build();
            crudEntityRepository.save(crudEntity);
            return "이름 : [" + name + "] 나이 : [" + age + "]으로 추가 되었습니다.";
        }
    }

    @GetMapping("/update")
    public String update(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age) {
        if(crudEntityRepository.findById(name).isEmpty()) {
            return "입력한 [" + name + "]은 존재하지 않습니다.";
        }else{

            crudEntityRepository.save(CrudEntity.builder().name(name).age(age).build());
            return "[" + name + "]의 나이를 [" + age + "]로 수정하였습니다.";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "name") String name) {
        if(crudEntityRepository.findById(name).isEmpty()) {
            return "입력한 [" + name + "]은 존재하지 않습니다." ;
        }else{
            crudEntityRepository.delete(crudEntityRepository.findById(name).get());
            return "[" + name + "]이 삭제되었습니다.";
        }
    }
}
