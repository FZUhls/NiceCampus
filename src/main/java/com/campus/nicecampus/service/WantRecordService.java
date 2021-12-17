package com.campus.nicecampus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.campus.nicecampus.base.exception.AuthorityException;
import com.campus.nicecampus.base.model.WantRecord;
import com.campus.nicecampus.req.WantRecordReq;
import org.springframework.security.access.AuthorizationServiceException;

import javax.naming.AuthenticationException;
import java.util.List;

public interface WantRecordService {
    void add(WantRecordReq req);
    Page<WantRecord> getPages(int currentPage);
    List<WantRecord> getAllWantRecordsByUserId(long userId);
    void delWantRecord(long id) throws AuthorityException;
}
