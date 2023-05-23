package top.leonardsaikou.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class BaseController<T> {

    @Autowired
    private BaseService<T> baseService;

    @GetMapping("/{id}")
    public T getById(@PathVariable("id") Long id) {
        return baseService.getById(id);
    }

    @PostMapping("/")
    public T create(@RequestBody T entity) {
        return baseService.save(entity);
    }

    @PutMapping("/{id}")
    public T update(@PathVariable("id") Long id, @RequestBody T entity) {
        return baseService.updateById(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        baseService.removeById(id);
    }
}

