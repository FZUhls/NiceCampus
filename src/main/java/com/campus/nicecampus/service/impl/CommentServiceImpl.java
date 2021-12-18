package com.campus.nicecampus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.campus.nicecampus.base.mapper.CommentsMapper;
import com.campus.nicecampus.base.model.Comments;
import com.campus.nicecampus.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl extends BaseService implements CommentService {
    @Autowired
    CommentsMapper commentsMapper;

    @Override
    public List<Comments> getByGoodsIdAndType(long goodsId, int commentType) {
        if (commentType == 0) {
            return commentsMapper.selectByMap(Map.of("goods_id", goodsId));
        } else {
            return commentsMapper.selectByMap(Map.of("goods_id", goodsId, "comment_type", commentType));
        }
    }

    @Override
    public void addComment(long goodsId, String commentContent, int score) {
        Comments comments = new Comments();
        comments.setUserId(getUser().getId());
        comments.setCommentContent(commentContent);
        comments.setGoodsId(goodsId);
        comments.setScore(score);
        switch (score) {
            case 1:
            case 2:
                comments.setCommentType(3);
                break;
            case 3:
            case 4:
                comments.setCommentType(2);
                break;
            case 5:
                comments.setCommentType(1);
                break;
        }
        commentsMapper.insert(comments);
    }

    @Override
    public long getAllCommentSize() {
        return commentsMapper.selectCount(Wrappers.emptyWrapper());
    }
}
