import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Course_List implements Serializable{
    ArrayList<Course>course_list = new ArrayList<>();
    transient Scanner input = new Scanner(System.in);

    public Course_List()
    {

    }

    public Course_List(Course_List course_list)
    {
        this.course_list = course_list.getCourse_list();
    }

    public ArrayList<Course> getCourse_list() {
        return course_list;
    }

    public void add_course_here()
    {
        Course course = new Course();
        course.add_course();
        course_list.add(course);
    }

    public Course search(String id)
    {
        for(int i=0;i<course_list.size();i++)
        {
            if(course_list.get(i).get_course_id().equals(id))
            {
                return course_list.get(i);
            }
        }
        return null;
    }

    public void course_info(String id)
    {
        if(search(id)!=null)
        {
            System.out.println("Name: "+search(id).get_course_name());
            System.out.println("ID: "+search(id).get_course_id());
            System.out.println("Registeration limit: "+search(id).getMax_student_limit());
            System.out.println("Maximum Vacancy for teachers: "+search(id).getMax_teacher_limit());
        }
        else
        {
            System.out.println("No course is registered with this ID");
        }
    }

    public void edit_course(String id)
    {
        if(search(id)!=null)
        {
            System.out.println("Give updated information for course '" + search(id).get_course_name() + ".' Enter 0 to skip a field.\n");
			
            System.out.print("Course title: "); 
            String course_name = input.nextLine();
            if(!course_name.equals("0"))
            {
                search(id).setCourse_name(course_name);
            }
			
            System.out.print("Course registration limit: "); 
            int max_student_limit = input.nextInt();
            if(max_student_limit!=0)
            {
                search(id).setMax_student_limit(max_student_limit);
            }
            
            System.out.print("Teacher limit: "); 
            int max_teacher_limit = input.nextInt();
            if(max_teacher_limit!=0)
            {
                search(id).setMax_teacher_limit(max_teacher_limit);
            }
        }
        else
        {
            System.out.println("No course is registered with this ID");
        }
    }

    public void remove_course(String id)
    {
        for(int i=0;i<course_list.size();i++)
        {
            if(course_list.get(i).get_course_id().equals(id))
            {
                course_list.remove(i);
                return;
            }
        }
        System.out.println("No course is registered with this ID");
    }

    public void view_all_courses()
    {
        for(int i=0;i<course_list.size();i++)
        {
            System.out.println("Name: "+course_list.get(i).get_course_name());
            System.out.println("ID: "+course_list.get(i).get_course_id());
            System.out.println("Maximum Registeration limit: "+course_list.get(i).getMax_student_limit());
            System.out.println("Maximum Vacancy for teachers: "+course_list.get(i).getMax_teacher_limit());
        }
    }

    public void view_full_courses()
    {
        for(int i=0;i<course_list.size();i++)
        {
            if(course_list.get(i).getMax_student_limit()!=course_list.get(i).getStudent_count())
            {
                System.out.println("Name: "+course_list.get(i).get_course_name());
                System.out.println("ID: "+course_list.get(i).get_course_id());
                System.out.println("");
                System.out.println("------------------------------");
            }
        }
    }

    public void view_available_courses()
    {
        for(int i=0;i<course_list.size();i++)
        {
            if(course_list.get(i).getMax_student_limit()>course_list.get(i).getStudent_count())
            {
                System.out.println("Name: "+course_list.get(i).get_course_name());
                System.out.println("ID: "+course_list.get(i).get_course_id());
                System.out.println("");
                System.out.println("------------------------------");
            }
        }
    }

    public void vacant_teaching_course()
    {
        for (int i = 0; i < course_list.size(); i++) {
            if(course_list.get(i).getMax_teacher_limit()>course_list.get(i).getTeacher_count())
            {
                System.out.println("Name: "+course_list.get(i).get_course_name());
                System.out.println("ID: "+course_list.get(i).get_course_id());
                System.out.println("");
                System.out.println("------------------------------");
            }
        }
    }

    public void view_student_registered(String course_id)
    {
        if(search(course_id)!=null)
        {
            search(course_id).get_registered_student();
        }
        else
        {
            System.out.println("No course is registered with this ID");
        }
    }

    public void view_teachers_registered(String course_id)
    {
        if(search(course_id)!=null)
        {
            search(course_id).get_registered_teacher();
        }
        else
        {
            System.out.println("No course is registered with this ID");
        }
    }

    synchronized public void register_student(String course_id, Student student)
    {
        if(search(course_id)!=null)
        {
            if(search(course_id).getStudent_count()<search(course_id).getMax_student_limit())
            {
                if(!search(course_id).search_student(student))
                {
                    search(course_id).add_student(student);
                    student.enroll_in_course(search(course_id));
                }
                else
                {
                    System.out.println("Student already registered in this course");
                }
            }
            else
            {
                System.out.println("Course is full");
            }
        }
        else
        {
            System.out.println("No course is registered with this ID");
        }
    }

    public void withdraw_student(String course_id,Student student)
    {
        if(search(course_id)!=null)
        {
            if(search(course_id).search_student(student))
            {
                search(course_id).remove_student(student);
                student.unenroll_from_course(search(course_id));
            }
            else
            {
                System.out.println("You are not registered in this course.");
            }
        }
        else
        {
            System.out.println("No course is registered with this ID");
        }
    }

    synchronized public void register_teacher(String course_id, Teacher teacher)
    {
        if(search(course_id)!=null)
        {
            if(search(course_id).getMax_teacher_limit()>search(course_id).getTeacher_count())
            {
                if(!search(course_id).search_teacher(teacher))
                {
                    search(course_id).add_teacher(teacher);
                    teacher.add_course(search(course_id));
                }
                else
                {
                    System.out.println("You are already registred");
                }
            }
            else
            {
                System.out.println("No vacancies");
            }
        }
        else
        {
            System.out.println("No course is registered with this ID");
        }
    }

    public void withdraw_teacher(String course_id,Teacher teacher)
    {
        if(search(course_id)!=null)
        {
            if(search(course_id).search_teacher(teacher))
            {
                search(course_id).remove_teacher(teacher);
                teacher.remove_course(search(course_id));
            }
            else
            {
                System.out.println("You are not teaching this course.");
            }
        }
        else
        {
            System.out.println("No course is registered with this ID");
        }
    }
}

