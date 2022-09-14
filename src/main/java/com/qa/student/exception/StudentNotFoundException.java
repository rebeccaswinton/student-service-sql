package com.qa.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Student doesn't exist with this Id")
public class StudentNotFoundException extends Exception {

}
