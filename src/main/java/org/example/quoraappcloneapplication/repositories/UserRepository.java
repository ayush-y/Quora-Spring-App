package org.example.quoraappcloneapplication.repositories;

import org.example.quoraappcloneapplication.modles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
