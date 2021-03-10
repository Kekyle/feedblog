package by.microfeedblog.feedblog.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @NotEmpty
    @NotNull
    private String title;

    @NotBlank
    @NotEmpty
    @NotNull
    private String description;

    private long viewCount;

    @JsonIgnore
//    @JsonManagedReference
    @ManyToOne
    private User user;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    @Temporal(TemporalType.DATE)
    private Date updateDate;

    @OneToOne
    private Category category;

    @ManyToMany
    private List<Tag> tags;

    @OneToMany
    private List<Like> likes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "post")
    private List<Comment> comments;

    private boolean approved;

}
