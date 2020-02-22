package com.bitsun.provider.dao;

import com.bitsun.provider.Entity.UserEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends ElasticsearchRepository<UserEntity,Long> {
}
