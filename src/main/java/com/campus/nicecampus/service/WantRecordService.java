package com.campus.nicecampus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.nicecampus.base.model.WantRecord;
import com.campus.nicecampus.req.WantRecordReq;

public interface WantRecordService {
    void add(WantRecordReq req);
    Page<WantRecord> getPages(int currentPage);
}
