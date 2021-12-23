package com.leyou.item.service;

import com.leyou.item.mapper.GroupMapper;
import com.leyou.pojo.SpecGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationService {

    @Autowired
    private GroupMapper groupMapper;


    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        return groupMapper.select(specGroup);
    }
}
