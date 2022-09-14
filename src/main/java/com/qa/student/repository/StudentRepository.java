package com.qa.student.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.student.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

Optional<Student> findByName(String name);

/* Native Query (SQL)
 * select * from emp_details where emp_age > 27 and emp_dept = 'development'
 */
//Positional Parameters
//@Query(value = "select * from emp_details where emp_age > ?1 and emp_dept = ?2", nativeQuery = true)
//Named Parameters
/*
@Query(value = "select * from emp_details where emp_age > :age and emp_dept = :department", nativeQuery = true)
List<Employee> findEmployeeByAgeAndDept(int age, String department);
*/


@Query(value = "select e from Student e where e.dob > :dob and e.subject = :subject")
List<Student> findEmployeeByAgeAndDept(int age, String department);

@Query(value = "select sum(stu_subject) from stu_details", nativeQuery = true)
Double findTotalSalariesOfAllStudent();

/* update, delete (DML Query) */

@Modifying
@Query(value = "update Student e set e.firstName=:firstName , e.lastName = :lastName where e.id = :id")
int updateStuDetails(int id,String firstName, String lastName);

}
