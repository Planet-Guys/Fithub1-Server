package fithub.app.service;

import fithub.app.domain.Comments;
import fithub.app.domain.User;
import fithub.app.web.dto.requestDto.CommentsRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentsService {

    Page<Comments> findOnArticle(Long id, Long last);
    Page<Comments> findOnRecord(Long id, Long last);

    Comments createOnArticle (CommentsRequestDto.CreateCommentDto request, Long id, User user);
    Comments createOnRecord (CommentsRequestDto.CreateCommentDto request, Long id, User user);
    Comments updateOnArticle(CommentsRequestDto.UpdateCommentDto request, Long id,Long commentsId, User user);
    Comments updateOnRecord(CommentsRequestDto.UpdateCommentDto request, Long id,Long commentsId,User user);
    void deleteOnArticle(Long id, Long commentsId, User user);
    void deleteOnRecord(Long id, Long commentsId, User user);
    Comments toggleCommentsLikeOnArticle(Long id, Long commentsId, User user);
    Comments toggleCommentsLikeOnRecord(Long id, Long commentsId, User user);

}