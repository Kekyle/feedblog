package by.microfeedblog.feedblog.service.dto;

import by.microfeedblog.feedblog.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private User user;

    private boolean approved;

    private Date updateDate;

    private CategoryDto categoryDto;

    private List<TagDto> tagDto;

}
