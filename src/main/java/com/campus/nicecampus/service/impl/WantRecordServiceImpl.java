package com.campus.nicecampus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.nicecampus.base.exception.AuthorityException;
import com.campus.nicecampus.base.mapper.WantRecordMapper;
import com.campus.nicecampus.base.model.GoodsDetail;
import com.campus.nicecampus.base.model.WantRecord;
import com.campus.nicecampus.req.WantRecordReq;
import com.campus.nicecampus.service.WantRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @Override
    public List<WantRecord> getAllWantRecordsByUserId(long userId) {
        return wantRecordMapper.selectByMap(Map.of("user_id",userId));
    }

    @Override
    public void delWantRecord(long id) throws AuthorityException {
        WantRecord wantRecord = wantRecordMapper.selectById(id);
        if(Objects.equals(getUser().getId(),wantRecord.getUserId())){
            wantRecordMapper.deleteById(id);
        }else {
            throw new AuthorityException("无权限删除此商品");
        }
    }
}
