package com.campus.nicecampus.service;

import com.campus.nicecampus.base.model.Comments;

import java.util.List;

public interface CommentService {
    List<Comments> getByGoodsIdAndType(long goodsId,int commentType);
    void addComment(long goodsId,String commentContent, int score);
    long getAllCommentSize();
}
