package com.campus.nicecampus.service;

import com.campus.nicecampus.base.exception.FileTypeIllegalException;
import com.campus.nicecampus.base.model.User;
import com.campus.nicecampus.req.SignUpReq;
import com.campus.nicecampus.req.UpdateUserInfoReq;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User getById(long id);
    void signUp(SignUpReq req) throws Exception;
    void updateUser(UpdateUserInfoReq req, MultipartFile iconUrl) throws FileTypeIllegalException;
}
