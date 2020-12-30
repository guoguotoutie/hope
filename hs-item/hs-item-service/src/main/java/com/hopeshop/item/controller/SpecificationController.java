package com.hopeshop.item.controller;

import com.hopeshop.item.pojo.Specification;
import com.hopeshop.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    /**
     * 查询规格
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<String> querySpecificationByCategoryId(@PathVariable("id") Long id){

        Specification spec = this.specificationService.queryById(id);
        if(spec == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(spec.getSpecifications());
    }

    /**
     * 新增规格
     * @param specification
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveSpec(Specification specification){
        this.specificationService.saveSpec(specification);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 修改规格
     * @param specification
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateSpec(Specification specification){
        this.specificationService.updateSpec(specification);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 删除规格
     * @param id
     * @return
     */
    @RequestMapping("del/{id}")
    public ResponseEntity<Void> delSpec(@PathVariable("id") Long id){
        this.specificationService.delSave(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/bycategoryid/{id}")
    public ResponseEntity<Specification> querySpecByCategoryId(@PathVariable("id") Long id){
        Specification spec = this.specificationService.queryById(id);
        return ResponseEntity.ok(spec);
    }
}
