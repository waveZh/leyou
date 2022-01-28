package com.leyou.item.service;

import com.leyou.item.mapper.GroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.pojo.SpecGroup;
import com.leyou.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationService {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;


    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        return groupMapper.select(specGroup);
    }

    public List<SpecParam> querySpecParam(Long gid) {
        SpecParam record = new SpecParam();
        record.setGroupId(gid);
        return specParamMapper.select(record);
    }
}
