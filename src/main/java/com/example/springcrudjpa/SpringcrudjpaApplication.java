package com.example.springcrudjpa;
import java.util.Scanner;
import java.util.Scanner.*;
import com.example.springcrudjpa.dao.StudentDAO;
import com.example.springcrudjpa.dao.StudentDAOimpl;
import com.example.springcrudjpa.entity.Student;
import jakarta.persistence.TypedQuery;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static java.lang.Integer.parseInt;

@SpringBootApplication
public class SpringcrudjpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcrudjpaApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			queryForStudents(studentDAO);
		};
	}

	private void queryForStudents(StudentDAO studentDAO) {

		//save the student object
		//System.out.println("Saving the object");
		//studentDAO.save(tempStudent);


		//display id of the saved student
		//int theId=tempStudent.getId();
		//System.out.println("Saved student: "+tempStudent.getId());

		//display student
		//Student myStudent = studentDAO.findById(theId);
		//System.out.println("Student Info : "+ myStudent );


		//update Students
		//int studentId=1;
		//Student myStudent= studentDAO.findById(studentId);
		//myStudent.setEmail("Harshalgangurde45@gmail.com");
		//studentDAO.update(myStudent);
		//System.out.println(myStudent);



		Scanner scanner = new Scanner(System.in);

		int flag=1;
		while (flag==1){

			List<Student> theStudents=studentDAO.findAll();
			System.out.println("Select 1.Show 2.Add 3.Update 4.Delete 5.Close");

			String input = scanner.nextLine();

			if (parseInt(input) ==2) {
				System.out.println("Enter First Name");
				String fname= scanner.nextLine();

				System.out.println("Enter Last Name");
				String lname=scanner.nextLine();

				System.out.println("Enter Email");
				String email=scanner.nextLine();
				Student tempStudent=new Student(fname,lname,email);
				studentDAO.save(tempStudent);
				System.out.println(fname +" "+lname+" is added in database");
			}
			else if (parseInt(input)==1) {

				for (int i=0;i<theStudents.size();i++){
					System.out.println(theStudents.get(i));
				}
			}
			else if (parseInt(input)==3) {
				System.out.println("Update");
				System.out.println("Which id You want to update");
				String id=scanner.nextLine();
				Student mystudent=studentDAO.findById(parseInt(id));

				System.out.println("1. First Name 2.Last Name 3. Email");
				String which=scanner.nextLine();
				System.out.println("Enter the Value");
				String value=scanner.nextLine();
				if (parseInt(which)==1){

					mystudent.setFirstName(value);
				}
				if (parseInt(which)==2){
					mystudent.setLastName(value);
				}
				if (parseInt(which)==3){
					mystudent.setEmail(value);
				}
				studentDAO.update(mystudent);

			}
			else if (parseInt(input)==4) {
				System.out.println("Enter id which you want to delete");
				String id=scanner.nextLine();
				if (theStudents.size()<parseInt(id)){
					System.out.println("Id not present");
				}
				else{
					studentDAO.delete(parseInt(id));
				}
			}
			else if (parseInt(input)==5) {
				System.out.println("Thank you program is getting closed");
				flag=2;
			}
			else {
				System.out.println("Not in List");
			}
		}

	}
}

