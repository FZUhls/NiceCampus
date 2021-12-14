package com.campus.nicecampus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.nicecampus.base.mapper.WantRecordMapper;
import com.campus.nicecampus.base.model.WantRecord;
import com.campus.nicecampus.req.WantRecordReq;
import com.campus.nicecampus.service.WantRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WantRecordServiceImpl extends BaseService implements WantRecordService {
    @Autowired
    WantRecordMapper wantRecordMapper;
    @Override
    public void add(WantRecordReq req) {
        WantRecord record = new WantRecord();
        record.setUserId(getUser().getId());
        record.setTitle(req.getTitle());
        record.setWantPrice(Double.parseDouble(req.getWantPrice()));
        record.setGoodsDesc(req.getGoodsDesc());
        wantRecordMapper.insert(record);
    }

    @Override
    public Page<WantRecord> getPages(int currentPage) {
        Page<WantRecord> page = new Page<>(currentPage,10);
        return wantRecordMapper.selectPage(page, Wrappers.emptyWrapper());
    }
}
