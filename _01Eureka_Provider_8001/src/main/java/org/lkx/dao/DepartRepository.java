package org.lkx.dao;

import org.lkx.dom.Depart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartRepository extends PagingAndSortingRepository<Depart,Integer>, JpaRepository<Depart,Integer> {
}
