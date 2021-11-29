import java.io.Serializable;
import java.util.ArrayList;

public class Teacher_List implements Serializable{
    
    private ArrayList<Teacher> teacher_list = new ArrayList<>();

    public Teacher_List()
    {
        
    }

    public Teacher_List(Teacher_List teacher_list)
    {
        this.teacher_list = teacher_list.getTeacher_list();
    }

    public ArrayList<Teacher> getTeacher_list() {
        return teacher_list;
    }

    public void add_teacher()
    {
        System.out.println("Enter the details of the teacher ");
        Teacher t = new Teacher();
        t.register();
        teacher_list.add(t);
    }

    public Teacher search(String id)
    {
        for (int i = 0; i < teacher_list.size(); i++)
        {
            if (teacher_list.get(i).getTeacher_id().equals(id)) 
            {
                return teacher_list.get(i);
            }
        }
        return null;
    }

    public void view_course_teaching(String id)
    {
        if(search(id)!=null)
        {
            search(id).get_courses();
        }
        else
        {
            System.out.println("Invaild Teacher ID");
        }
    }

    public void teacher_info(String id)
    {
        if(search(id)!=null)
        {
            System.out.println("Name: "+search(id).getTeacher_first_name()+" "+search(id).getTeacher_last_name());
            System.out.println("ID: "+search(id).getTeacher_id());
        }
        else
        {
            System.out.println("Invaild Teacher ID");
        }
    }

    public void view_all_teacher()
    {
        for(int i=0;i<teacher_list.size();i++)
        {
            System.out.println("Name: "+teacher_list.get(i).getTeacher_first_name()+" "+teacher_list.get(i).getTeacher_last_name());
            System.out.println("ID: "+teacher_list.get(i).getTeacher_id());
        }
    }
}
