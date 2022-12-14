package com.pj.untapped.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pj.untapped.domain.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer>{

}
