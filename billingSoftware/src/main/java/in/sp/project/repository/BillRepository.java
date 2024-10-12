package in.sp.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.project.entity.Billing_Product;

public interface BillRepository extends JpaRepository<Billing_Product,Long> {

}
