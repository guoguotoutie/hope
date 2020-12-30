package com.hopeshop.item.service;

import com.hopeshop.item.mapper.SpecificationMapper;
import com.hopeshop.item.pojo.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;

    public Specification queryById(Long id) {

        return this.specificationMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void saveSpec(Specification specification) {
        this.specificationMapper.insertSelective(specification);

    }

    @Transactional
    public void updateSpec(Specification specification) {
        this.specificationMapper.updateByPrimaryKey(specification);
    }

    @Transactional
    public void delSave(Long id) {
        this.specificationMapper.deleteByPrimaryKey(id);
    }
}
