package org.example.quoraappcloneapplication.modles;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass // This annotation is used to specify that the class is an entity class that is a superclass
@Data
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This annotation is used to specify primary key
    private Long id;


}
