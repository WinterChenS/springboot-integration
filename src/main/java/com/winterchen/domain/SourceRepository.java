package com.winterchen.domain;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户资源表
 * Created by winterchen on 2017/11/29.
 */
@CacheConfig(cacheNames = "sources")
public interface SourceRepository extends JpaRepository<SourceEntity, String> {


}
