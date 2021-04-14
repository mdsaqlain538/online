package com.tata.mart.online.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tata.mart.online.model.Category;

public interface CategoryRepo  extends JpaRepository<Category, Long> {

}
