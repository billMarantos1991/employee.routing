package com.bill.employee.routing.repositories;




import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.bill.employee.routing.model.Attribute;



@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {

	 @Transactional
	 @Modifying
	 @Query("Delete from Attribute a where a.attributeId=:attributeId")
	 void deleteAttributeByAttibuteId(Long attributeId);	
}
