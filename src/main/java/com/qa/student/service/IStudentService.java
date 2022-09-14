package com.qa.student.service;

import java.util.List;

import com.qa.student.Dto.StudentDto;
import com.qa.student.entity.Student;
import com.qa.student.exception.StudentAlreadyExistsException;
import com.qa.student.exception.StudentNotFoundException;

public interface IStudentService {
//CRUD operations
	
	public Student saveStudent(Student student) throws StudentAlreadyExistsException;
	public List<Student> getAllStudents();
	public Student getStudentById(int id) throws StudentNotFoundException;
	public Student updateStudent(Student student) throws StudentNotFoundException;
	public boolean deleteStudent (int id) throws StudentNotFoundException;
	
public List<Student> findStudentByDobAndSubj(String dob, String subject);

public Student updateStudentDetails(Student student);

public List<StudentDto> findStudentDetailsWithDto();
}
