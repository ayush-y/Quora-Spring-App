package org.example.quoraappcloneapplication.modles;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Tag extends BaseModel{

    private String name;

    @ManyToMany(mappedBy = "followedTags")
    private Set<Users> follower;
}
