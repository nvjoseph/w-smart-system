package com.wsmartsystem.usereditor.user.dao;

import com.wsmartsystem.usereditor.user.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
