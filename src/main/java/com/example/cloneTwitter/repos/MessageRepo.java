package com.example.cloneTwitter.repos;

import com.example.cloneTwitter.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
//JpaRepository extends PagingAndSortingRepository, который, в свою очередь, расширяет CrudRepository.
//Их основные функции:
//CrudRepository в основном предоставляет функции CRUD.
//PagingAndSortingRepository предоставляют методы для разбиения на страницы и сортировки записей.
//JpaRepository содержит некоторые связанные с JPA методы, такие как удаление контекста персистентности и удаление записи в пакете.
//Из-за упомянутого выше наследования JpaRepository будет иметь все функции CrudRepository и PagingAndSortingRepository. Поэтому, если вам не нужен репозиторий для функций, предоставляемых JpaRepository и PagingAndSortingRepository, используйте CrudRepository.

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
    //List<Message> findByTagOrTextContainingIgnoreCase(String tag, String text); // seach 2position

}
