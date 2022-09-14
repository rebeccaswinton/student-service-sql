package com.qa.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.student.exception.StudentAlreadyExistsException;
import com.qa.student.exception.StudentNotFoundException;

import entity1.Student;
import service.StudentService;

@RestController
@RequestMapping("api/v1/student-service")
public class StudentController {

@Autowired
StudentService stuService;

/*
 * It is responsible for sending the response to the client converting java
 * objects to json by default along with the status code
 * 
 * 
 */

ResponseEntity<?> responseEntity;

@PostMapping("/student")
public ResponseEntity<?> saveStudent(@RequestBody Student student) {
	Student createdStudent;
	try {
		createdStudent = this.stuService.saveStudent(student);
	}catch(StudentAlreadyExistsException e) {
		throw e;
	}
	
	responseEntity = new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
	return responseEntity; 
}
@GetMapping("/student")
public ResponseEntity<?> getAllEmployees() {
	return new ResponseEntity <>(this.stuService.getAllEmployees(), HttpStatus.OK);
	
}
@DeleteMapping("/student/{id}")
public ResponseEntity<?> getStudentById(@PathVariable("id")int id) throws StudentNotFoundException {
	boolean status;
	try {
		status = this.stuService.deleteStudentById(id);
		responseEntity = new ResponseEntity<>("Student Deleted!", HttpStatus.OK);
	} catch (StudentNotFoundException e) {
		throw e;
	} catch(Exception e)  {
		responseEntity = new ResponseEntity<>("An internal error occured, please try again.", HttpStatus.OK);
	}
	return responseEntity;
	}

@PutMapping("/student")
public ResponseEntity<?> updateEmployee(@RequestBody Student student) throws StudentNotFoundException {
	try {
		responseEntity = new ResponseEntity<>(stuService.updateStudent(student), HttpStatus.OK);
	} catch (StudentNotFoundException e) {
		throw e;
	}
	return responseEntity;
}

@GetMapping("/student/dob/{dob}/subj/{subj}")
public ResponseEntity<?> getStudenntByDobAndSubj(@PathVariable("dob") String dob, @PathVariable("subj") String subject) {
	return new ResponseEntity<>(stuService.findStudentByAgeAndSubj(dob, subject), HttpStatus.OK);
}
@GetMapping("/student/subject")
public ResponseEntity<?> getTotalSubjectsOfAllStudents(){
	return new ResponseEntity<>(stuService.findTotalSubjectsOfAllStudents(),HttpStatus.OK);
}
@PutMapping("/student/updatedetails")
public ResponseEntity<?> updateEmpDetails(@RequestBody Student student){
	return new ResponseEntity<>(stuService.updateStudentDetails(student),HttpStatus.OK);
}

@GetMapping("/student/dto-details")
public ResponseEntity<?> getStuDtoDetails(){
	return new ResponseEntity<>(stuService.findStudentDetailsWithDto(),HttpStatus.OK);
}



}









