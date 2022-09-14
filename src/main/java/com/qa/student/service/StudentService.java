package com.qa.student.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.student.Dto.StudentDto;
import com.qa.student.entity.Student;
import com.qa.student.exception.StudentAlreadyExistsException;
import com.qa.student.exception.StudentNotFoundException;
import com.qa.student.repository.StudentRepository;

@Service
public class StudentService implements IStudentService{
	
	@Autowired
	StudentRepository stuRespository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public Student saveStudent(Student student)throws StudentAlreadyExistsException {
		/*
		 * 1. Check whether student already exists with the name
		 * 2. If yes, throw StudentAlreadyExistsException
		 * 3. If no, save the student object into the database
		 * 4. Return the saved student	
		 */
		
		Optional<Student> findByFirstName = stuRespository.findByName(student.getFirstName());
		if(findByFirstName.isPresent())
			throw new StudentAlreadyExistsException();
		return stuRespository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return stuRespository.findAll();	
		}

	@Override
	public Student getStudentById(int id) throws StudentNotFoundException {
		Optional<Student> findById = stuRespository.findById(id);
		if(!findById.isPresent())
			throw new StudentNotFoundException();
		else 
		return findById.get();
	}

	@Override
	public Student updateStudent(Student student)throws StudentNotFoundException {
	Optional<Student> findById = stuRepository.findById(student.getId());
		
		if(!findById.isPresent())
			throw new StudentNotFoundException();
		else {
				Student existingStu = findById.get(); 
				existingStu.setDob(student.getDob());			
			return stuRepository.saveAndFlush(existingStu);
		}
	}

	@Override
	public boolean deleteStudentById(int id) throws StudentNotFoundException{
		boolean status = false;
		Optional<Student> findById = stuRepository.findById(id);
		if(!findById.isPresent())
			throw new StudentNotFoundException();
		else {
			stuRepository.delete(findById.get());
			status = true;
			}
		
		return status;
	}

	@Override
	public boolean deleteStudent(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Student> findStudentByDobAndSubj(String dob, String subject) {
		
		return StuRepository.findStudentByDobandSubj(dob, subject);
	}

	@Override
	public Student updateStudentDetails(Student student) {
		int rows = stuRespository.updateStuDetails(student.getId(), student.getFirstName(), student.getLastName(), student.getDob(),student.getSubject());
		System.out.println(rows);
		
		return stuRespository.findById(student.getId()).get();
	}

	@Override
	public List<StudentDto> findStudentDetailsWithDto() {
		return this.modelMapper.map(student, StudentDto.class);
	}


}
