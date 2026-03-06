package org.example.quoraappcloneapplication.modles;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
public class User extends BaseModel{

    private String username;

    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> followedTags;
}
