import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Teacher implements Serializable{
    private transient Scanner sc = new Scanner(System.in);
    private static int id = 1000;
    private String teacher_first_name;
    private String teacher_last_name;
    private String teacher_id;
    private String password;
    private String username;
    private ArrayList<Course> teaching_course = new ArrayList<>();

    public void register()
    {
        System.out.println("Enter the first name of the teacher: ");
        teacher_first_name = sc.next();
        System.out.println("Enter the last name of the teacher: ");
        teacher_last_name = sc.next();
        id++;
        teacher_id = setID();
        username=teacher_id;
        System.out.println("Your username is: "+username+"\n"+"Enter password: ");
        password=sc.next();
        System.out.println(teacher_first_name+" "+teacher_last_name+" registered with teacher id = "+teacher_id+"\n");
    }

    private String setID()
    {
        String temp = teacher_first_name+teacher_last_name+id;
        return temp;
    }

    public String getTeacher_first_name() {
        return teacher_first_name;
    }

    public String getTeacher_last_name() {
        return teacher_last_name;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Course> getTeaching_course() {
        return teaching_course;
    }

    public void get_courses()
    {
        for (int i = 0; i < teaching_course.size(); i++) 
        {
            System.out.println("Name: "+teaching_course.get(i).get_course_name());
            System.out.println("ID: "+teaching_course.get(i).get_course_id());
        }
    }

    public boolean search_course(String id)
    {
        for (int i = 0; i < teaching_course.size(); i++) 
        {
            if(teaching_course.get(i).get_course_id().equals(id))
            {
                return true;
            }
        }
        return false;
    }

    public void add_course(Course course)
    {
        for (int i = 0; i < teaching_course.size(); i++) 
        {
            if(teaching_course.get(i).get_course_id()==course.get_course_id())
            {
                System.out.println("Course already exists");
                return;
            }
        }
        teaching_course.add(course);
    }

    public void remove_course(Course course)
    {
        for (int i = 0; i < teaching_course.size(); i++) 
        {
            if(teaching_course.get(i).get_course_id()==course.get_course_id())
            {
                teaching_course.remove(i);
                return;
            }
        }
    }
}
