package in.sp.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.project.entity.CardAdd;

public interface CardRepository extends JpaRepository<CardAdd,Long>{

}
