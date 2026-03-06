package org.example.quoraappcloneapplication.repositories;

import org.example.quoraappcloneapplication.modles.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
