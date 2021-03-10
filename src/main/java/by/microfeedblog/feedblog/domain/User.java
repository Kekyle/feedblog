package by.microfeedblog.feedblog.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(value = {
        "id",
        "login",
        "password",
        "name",
        "bornDate",
        "postList",
        "likes",
        "role",
        "createDate",
        "updateDate"
})
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @NotEmpty
    private String login;

    @NotBlank
    @NotEmpty
    private String password;

    private Role role;

//    @JsonBackReference
    @OneToMany
    private List<Post> postList;

    @OneToMany
    private List<Like> likes;

    @NotBlank
    @NotEmpty
    private String name;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd.MM.yyyy hh:mm:ss")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd.MM.yyyy hh:mm:ss")
    @Temporal(TemporalType.DATE)
    private Date updateDate;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd.MM.yyyy")
    @Temporal(TemporalType.DATE)
    private Date bornDate;
}
