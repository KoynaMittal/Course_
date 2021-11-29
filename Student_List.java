import java.io.Serializable;
import java.util.ArrayList;

public class Student_List implements Serializable{
    private ArrayList<Student>student_list = new ArrayList<>();

    public Student_List()
    {
        
    }

    public Student_List(Student_List student_list)
    {
        this.student_list = student_list.getStudent_list();
    }

    public ArrayList<Student> getStudent_list() {
        return student_list;
    }

    public void add_student()
    {
        System.out.println("Please provide information about student");
        Student st = new Student();
        st.register();
        student_list.add(st);
    }

    public Student search(String id)
    {
        for(int i=0;i<student_list.size();i++)
        {
            if(student_list.get(i).get_ID().equals(id))
            {
                return student_list.get(i);
            }
        }
        return null;
    }

    public void remove_student(String student_id)
    {
        for(int i=0;i<student_list.size();i++)
        {
            if(student_list.get(i).get_ID().equals(student_id))
            {
                student_list.remove(i);
                return;
            }
        }
        System.out.println("No student is registered with this ID");
    }

    public void view_courses_registered(String student_id)
    {
        if(search(student_id)!=null)
        {
            search(student_id).get_registered_courses();
        }
        else
        {
            System.out.println("No student is registered with this ID");
        }
    }

    public void view_all_student()
    {
        for(int i=0;i<student_list.size();i++)
        {
            Student st = student_list.get(i);
            System.out.println("Name: "+st.first_name()+" "+st.last_name()+", ID: "+st.get_ID());
        }
    }

    public void student_info(String id)
    {
        if(search(id)!=null)
        {
            System.out.println("Name : "+search(id).first_name()+" "+search(id).last_name());
            System.out.println("ID : "+search(id).get_ID());
        }
        else
        {
            System.out.println("No student is registered with this ID");
        }
    }
}
