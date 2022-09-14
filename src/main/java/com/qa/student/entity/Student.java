
package com.qa.student.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name = "Student")
public class Student {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "stu_id")
private int id;


@NotNull
@Size(min = 2, max = 20, message = "FirstName must be between 2 and 20 characters only")
@Pattern(regexp = "^[A-Za-z0-9]*", message = "invalid username, must contain only alphanumeric")
@Column(name = "first_name")
private String firstName;

@NotNull
@Size(min = 2, max = 20, message = "Lastname must be between 2 and 20 characters only")
@Pattern(regexp = "^[A-Za-z0-9]*", message = "invalid Lastname, must contain only alphanumeric")
@Column(name = "last_name")
private String lastName;


@Column(name = "stu_dob")
private String dob;


@Column(name = "stu_subject")
private String subject;


}