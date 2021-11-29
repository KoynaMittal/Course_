import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Course implements Serializable{
    private transient Scanner sc = new Scanner(System.in);
    static int id = 200;
    private String course_name;
    private String course_id;
    private int max_student_limit;
    private int student_count=0;
    private ArrayList<Student>registered_students = new ArrayList<>();
    private ArrayList<Teacher>registered_teachers = new ArrayList<>();
    private int max_teacher_limit;
    private int teacher_count=0;

    public void add_course()
    {
        System.out.println("Enter course name : ");
        course_name = sc.nextLine();
        System.out.println("Enter maximum registeration limit : ");
        max_student_limit = sc.nextInt();
        System.out.println("Enter maximum teacher limit : ");
        max_teacher_limit = sc.nextInt();
        course_id = generate_id();
        System.out.println("ID of this course is: "+course_id);
    }

    private String generate_id()
    {
        ++id;
        String temp = course_name.replaceAll("\\s", "");
        temp+=id;
        return temp;
    }
    
    public String get_course_name()
    {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setMax_student_limit(int max_student_limit) {
        this.max_student_limit = max_student_limit;
    }
    
    public void setMax_teacher_limit(int max_teacher_limit) {
        this.max_teacher_limit = max_teacher_limit;
    }

    public String get_course_id()
    {
        return course_id;
    }

    public int getMax_student_limit() {
        return max_student_limit;
    }

    public int getMax_teacher_limit() {
        return max_teacher_limit;
    }

    public int getTeacher_count() {
        return teacher_count;
    }

    public int getStudent_count() {
        return student_count;
    }

    public ArrayList<Teacher> getregistered_teachers() {
        return registered_teachers;
    }
    // public void 

    // public boolean isFull()
    // {
    //     if()
    // }

    public boolean search_student(Student st)
    {
        for (int i = 0; i < registered_students.size(); i++) {
            if(registered_students.get(i).get_ID()==st.get_ID())
            {
                return true;
            }
        }
        return false;
    }

    public boolean search_teacher(Teacher teacher)
    {
        for (int i = 0; i < registered_teachers.size(); i++) {
            if(registered_teachers.get(i).getTeacher_id()==teacher.getTeacher_id())
            {
                return true;
            }
        }
        return false;
    }

    public void get_registered_student()
    {
        for(int i=0;i<registered_students.size();i++)
        {
            Student student = registered_students.get(i);
            System.out.println("Name: "+student.first_name()+", ID: "+student.last_name()+", ID: "+student.get_ID());
        }
    }

    public void get_registered_teacher()
    {
        for(int i=0;i<registered_teachers.size();i++)
        {
            System.out.println("Name: "+registered_teachers.get(i).getTeacher_first_name()+", ID: "+registered_teachers.get(i).getTeacher_last_name()+", ID: "+registered_teachers.get(i).getTeacher_id());
        }
    }

    public void add_student(Student student)
    {
        boolean exists = false;
        for (int i = 0; i < registered_students.size(); i++) {
            if(registered_students.get(i).get_ID()==student.get_ID())
            {
                exists = true;
                return;
            }
        }
        if(!exists)
        {
            registered_students.add(student);
            student_count++;
        }
    }

    public void remove_student(Student student)
    {
        for (int i = 0; i < registered_students.size(); i++) {
            if(registered_students.get(i).get_ID()==student.get_ID())
            {
                registered_students.remove(i);
                student_count--;
            }
        }
    }

    public void add_teacher(Teacher teacher)
    {
        boolean exists = false;

        for (int i = 0; i < registered_teachers.size(); i++) {
            if(registered_teachers.get(i).getTeacher_id()==teacher.getTeacher_id())
            {
                exists=true;
            }
        }
        if(!exists)
        {
            registered_teachers.add(teacher);
            teacher_count++;
        }
    }

    public void remove_teacher(Teacher teacher)
    {
        for (int i = 0; i < registered_teachers.size(); i++) {
            if(registered_teachers.get(i).getTeacher_id()==teacher.getTeacher_id())
            {
                registered_teachers.remove(teacher);
                teacher_count--;
            }
        }
    }
}
