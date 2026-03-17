package org.example.quoraappcloneapplication.repositories;

import org.example.quoraappcloneapplication.modles.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

}
