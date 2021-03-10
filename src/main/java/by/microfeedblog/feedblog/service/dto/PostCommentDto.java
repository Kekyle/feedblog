package by.microfeedblog.feedblog.service.dto;

import by.microfeedblog.feedblog.domain.Comment;
import by.microfeedblog.feedblog.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentDto {
    private long id;
    private Comment comment;
    private User user;
}
