import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Student implements Serializable{
    private transient Scanner sc = new Scanner(System.in);
    private static int id = 1000;
    private String student_first_name;
    private String student_last_name;
    private String student_id;
    private String password;
    private String username;
    private ArrayList<Course>registered_course = new ArrayList<>();


    public Student()
    {
    }

    public void register()
    {
        System.out.println("Enter the first name of the student: ");
        student_first_name = sc.next();
        System.out.println("Enter the last name of the student: ");
        student_last_name = sc.next();
        id++;
        student_id = setID();
        username=student_id;
        System.out.println("Your username is: "+username+"\n"+"Enter password: ");
        password=sc.next();
        System.out.println(student_first_name+" "+student_last_name+" registered with student id = "+student_id+"\n");
    }

    private String setID()
    {
        String temp = student_first_name+student_last_name+id;
        return temp;
    }

    public String first_name()
    {
        return student_first_name;
    }

    public String last_name()
    {
        return student_last_name;
    }

    public String get_ID()
    {
        return student_id;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Course> getRegistered_course() {
        return registered_course;
    }

    public void enroll_in_course(Course course)
    {
        boolean exists = false;

        for (int i = 0; i < registered_course.size(); i++) {
            if(registered_course.get(i).get_course_id()==course.get_course_id())
            {
                exists = true;
            }
        }

        if (!exists) 
        {
            registered_course.add(course);
        }
    }

    public void unenroll_from_course(Course course)
    {
        for (int i = 0; i < registered_course.size(); i++) 
        {
            if(registered_course.get(i).get_course_id()==course.get_course_id())
            {
                registered_course.remove(i);
            }
        }
    }

    public void get_registered_courses()
    {
        for(int i=0;i<registered_course.size();i++)
        {
            Course course = registered_course.get(i);
            System.out.println("Name: "+course.get_course_name()+", ID: "+course.get_course_id());
        }
    }


}
