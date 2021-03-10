package by.microfeedblog.feedblog.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDto{

    @Min(0)
    private long id;

    private String name;
}