package com.example.cloneTwitter.repos;

import com.example.cloneTwitter.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {
}
