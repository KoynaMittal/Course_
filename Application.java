import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.IOException;
import java.util.Scanner;

class Application implements Serializable
{
    private static Admin admin = new Admin();
    // private static Teacher teacher = new Teacher();
    // private static Student student = new Student();
    private static Student_List student_list = new Student_List();
    private static Course_List course_list = new Course_List();
    private static Teacher_List teacher_list = new Teacher_List();
    private static Course_List course_list_temp = null;
    private static Student_List student_list_temp = null;
    private static Teacher_List teacher_list_temp = null;
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) 
    {
        deserialize();
        login();
        serialize();
    }

    public static void login()
    {
        System.out.println("Welcome to the application.\n");
        while(true)
        {
            System.out.println("Enter 1 for Admin menu, 2 for Teacher menu, 3 for Student menu and 4 to exit");
            int sel = sc.nextInt();

            if(sel==1)
            {
                System.out.println("Enter username : ");
                String username = sc.next();
                System.out.println("Enter password : ");
                String password = sc.next();
                if(username.equals(admin.get_username()) && password.equals(admin.get_password()))
                {
                    System.out.println("Wecome to admin");
                    admin_menu();
                }
                else
                {
                    System.out.println("Wrong username or password");
                }
            }
            else if(sel==2)
            {
                while(true){
                    System.out.println("1 for Login, 2 for Register, 3 to exit");
                    sel = sc.nextInt();
                    if(sel==1)
                    {
                        System.out.println("Enter username : ");
                        String username = sc.next();
                        System.out.println("Enter password : ");
                        String password = sc.next();
                        if(teacher_list.search(username)!=null && teacher_list.search(username).getPassword().equals(password))
                        {
                            Teacher teacher = teacher_list.search(username);
                            teacher_menu(teacher);
                        }
                        else
                        {
                            System.out.println("Wrong username or password");
                        }
                    }
                    else if(sel==2)
                    {
                        teacher_list.add_teacher();
                    }
                    else if(sel==3)
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("Invalid number selected");
                    }
                }
            }
            else if(sel==3)
            {
                while(true){
                    System.out.println("1 for Login, 2 for Register, 3 to exit");
                    sel = sc.nextInt();
                    if(sel==1)
                    {
                        System.out.println("Enter username : ");
                        String username = sc.next();
                        System.out.println("Enter password : ");
                        String password = sc.next();
                        if(student_list.search(username)!=null && student_list.search(username).getPassword().equals(password))
                        {
                            Student st = student_list.search(username);
                            student_menu(st);
                        }
                        else
                        {
                            System.out.println("Wrong username or password");
                        }
                    }
                    else if(sel==2)
                    {
                        student_list.add_student();
                    }
                    else if(sel==3)
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("Invalid number selected");
                    }
                }
            }
            else if(sel==4)
            {
                break;
            }
            else
            {
                System.out.println("Invalid number selected");
            }
        }
    }

    // *************** ADMIN MENU *****************************
    public static void admin_menu()
    {
        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println();
        System.out.println("Welcome to Admin Menu");
        int selection = 0;
        while (selection!=5)
        {
            System.out.println("Please select a menu");
            System.out.println("1.Course Management");
            System.out.println("2.Student Info Portal");
            System.out.println("3.Course Info Portal");
            System.out.println("4.Teacher Info Portal");
            System.out.println("5.Exit");
            selection = sc.nextInt();
            
            if (selection==1)
            {
                // ------------COURSE MANAGEMENT--------------------------
                int choice = 0;
                while (choice!=4) 
                {
                    System.out.println("1.Add a new course");
                    System.out.println("2.Delete an existing course");
                    System.out.println("3.Edit a course");
                    System.out.println("4.Exit");
                    choice = sc.nextInt();
                    if (choice==1) 
                    {
                        course_list.add_course_here();
                    }
                    else if (choice==2)
                    {
                        System.out.println("Enter the ID of the course you want to remove: ");
                        String id = sc.next();
                        course_list.remove_course(id);
                    }
                    else if (choice==3) 
                    {
                        System.out.println("Enter the ID of the course you want to edit: ");
                        String id = sc.next();
                        course_list.edit_course(id);
                    }
                    
                    else if(choice!=4)
                    {
                        System.out.println("Invalid Number Selected");
                    }
                }                
            } 
            else if(selection==2)
            {
                // ----------------STUDENT INFO PORTAL------------------
                int choice = 0;
                while (choice!=4) 
                {
                    System.out.println("1.List of Registered Student");
                    System.out.println("2.Student's Info");
                    System.out.println("3.Student's course list");
                    System.out.println("4.Exit");
                    choice = sc.nextInt();
                    if (choice==1) 
                    {
                        student_list.view_all_student();
                    }
                    else if (choice==2)
                    {
                        System.out.println("Enter the ID of the Student : ");
                        String id = sc.next();
                        student_list.student_info(id);
                    }
                    else if (choice==3) 
                    {
                        System.out.println("Enter the ID of the Student : ");
                        String id = sc.next();
                        student_list.view_courses_registered(id);
                    }
                    else if(choice!=4)
                    {
                        System.out.println("Invalid Number Selected");
                    }
                }         
            }
            else if (selection==3)
            {
                // ----------------COURSE INFO PORTAL------------------
                int choice = 0;
                while (choice!=7) 
                {
                    System.out.println("1.View all courses");
                    System.out.println("2.View all full courses");
                    System.out.println("3.View courses that have seats left");
                    System.out.println("4.Course's Info");
                    System.out.println("5.Registred students in particular course");
                    System.out.println("6.List of teachers teaching particular course");
                    System.out.println("7.Exit");
                    choice = sc.nextInt();
                    if (choice==1) 
                    {
                        course_list.view_all_courses();
                    }
                    else if (choice==2)
                    {
                        course_list.view_full_courses();
                    }
                    else if (choice==3) 
                    {
                        course_list.view_available_courses();
                    }
                    else if (choice==4) 
                    {
                        System.out.println("Enter the ID of the course : ");
                        String id = sc.next();
                        course_list.course_info(id);
                    }
                    else if (choice==5) 
                    {
                        System.out.println("Enter the ID of the course : ");
                        String id = sc.next();
                        course_list.view_student_registered(id);
                    }
                    else if (choice==6) 
                    {
                        System.out.println("Enter the ID of the course : ");
                        String id = sc.next();
                        course_list.view_teachers_registered(id);
                    }
                    else if(choice!=7)
                    {
                        System.out.println("Invalid Number Selected");
                    }
                }       
            }
            else if (selection==4) 
            {
                // ----------------TEACHER INFO PORTAL------------------
                int choice = 0;
                while (choice!=4) 
                {
                    System.out.println("1.List of Teachers");
                    System.out.println("2.Teacher's Info");
                    System.out.println("3.Teacher's course list");
                    System.out.println("4.Exit");
                    choice = sc.nextInt();
                    if (choice==1) 
                    {
                        teacher_list.view_all_teacher();
                    }
                    else if (choice==2)
                    {
                        System.out.println("Enter the ID of the Student : ");
                        String id = sc.next();
                        teacher_list.teacher_info(id);
                    }
                    else if (choice==3) 
                    {
                        System.out.println("Enter the ID of the Student : ");
                        String id = sc.next();
                        teacher_list.view_course_teaching(id);
                    }
                    
                    else if(choice!=4)
                    {
                        System.out.println("Invalid Number Selected");
                    }
                }         
            }
            else if(selection<1 || selection>5)
            {
                System.out.println("Invalid Selection");
            }
        }
    }

    // *************** TEACHER MENU *****************************
    public static void teacher_menu(Teacher teacher)
    {
        int selection = 0;
        System.out.println("Welcome "+teacher.getTeacher_first_name()+" "+teacher.getTeacher_last_name()+" to Teacher Menu");

        while(selection!=7)
        {
            System.out.println("1.View course list");
            System.out.println("2.View teaching courses");
            System.out.println("3.View available course list");
            System.out.println("4.Teach a course");
            System.out.println("5.Withdraw from a course");
            System.out.println("6.List of students registered in your course");
            System.out.println("7.Exit");

            selection = sc.nextInt();

            if(selection==1)
            {
                course_list.view_all_courses();
            }
            else if(selection==2)
            {
                teacher_list.view_course_teaching(teacher.getTeacher_id());
            }
            else if(selection==3)
            {
                course_list.vacant_teaching_course();
            }
            else if(selection==4)
            {
                System.out.println("Enter the ID of the course : ");
                String id = sc.next();
                System.out.println("Enter your skill level on scale 100 : ");
                int marks = sc.nextInt();
                if(admin.is_qualified(marks))
                {
                    course_list.register_teacher(id, teacher);
                }
                else
                {
                    System.out.println("You are not qualified enough for this job.");
                }
            }
            else if(selection==5)
            {
                System.out.println("Enter the ID of the course from which you want to withdraw : ");
                String id = sc.next();
                course_list.withdraw_teacher(id, teacher);
            }
            else if(selection==6)
            {
                System.out.println("Enter the ID of the course: ");
                String id = sc.next();
                if(teacher.search_course(id))
                {
                    course_list.view_student_registered(id);
                }
                else
                {
                    System.out.println("You don't have access to this course");
                }
            }
            else if(selection!=7)
            {
                System.out.println("Invalid Number Selected");
            }
        }
    }

    // *************** STUDENT MENU *****************************
    public static void student_menu(Student st)
    {
        int selection = 0;
        System.out.println("Welcome "+st.first_name()+" "+st.last_name()+" to Student Menu");

        while(selection!=6)
        {
            System.out.println("1.View course list");
            System.out.println("2.View registered courses");
            System.out.println("3.View available course list");
            System.out.println("4.Register to a course");
            System.out.println("5.Withdraw from a course");
            System.out.println("6.Exit");

            selection = sc.nextInt();

            if(selection==1)
            {
                course_list.view_all_courses();
            }
            else if(selection==2)
            {
                student_list.view_courses_registered(st.get_ID());
            }
            else if(selection==3)
            {
                course_list.view_full_courses();
            }
            else if(selection==4)
            {
                System.out.println("Enter the ID of the course : ");
                String id = sc.next();
                course_list.register_student(id, st);
            }
            else if(selection==5)
            {
                System.out.println("Enter the ID of the course from which you want to withdraw : ");
                String id = sc.next();
                course_list.withdraw_student(id, st);
            }
            else if(selection!=6)
            {
                System.out.println("Invalid Number Selected");
            }
        }
    }

    public static void deserialize()
    {
        if(new File("Course_List_File.ser").exists() && new File("Student_List_File.ser").exists() && new File("Teacher_List_File.ser").exists())
        {
            try 
            {
                FileInputStream course_fis = new FileInputStream("Course_List_File.ser"); 
                FileInputStream student_fis = new FileInputStream("Student_List_File.ser"); 
                FileInputStream teacher_fis = new FileInputStream("Teacher_List_File.ser");

                ObjectInputStream course_ois = new ObjectInputStream(course_fis);
                ObjectInputStream student_ois = new ObjectInputStream(student_fis);
                ObjectInputStream teacher_ois = new ObjectInputStream(teacher_fis);
                
                course_list_temp = (Course_List) course_ois.readObject();
                student_list_temp = (Student_List) student_ois.readObject();
                teacher_list_temp = (Teacher_List) teacher_ois.readObject();

                course_list = new Course_List(course_list_temp);
                student_list = new Student_List(student_list_temp);
                teacher_list = new Teacher_List(teacher_list_temp);

                course_ois.close();
                student_ois.close();
                teacher_ois.close();

                course_fis.close();
                student_fis.close();
                teacher_fis.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void serialize()
    {
        try
        {
            FileOutputStream course_fos = new FileOutputStream("Course_List_File.ser");
            FileOutputStream student_fos = new FileOutputStream("Student_List_File.ser");
            FileOutputStream teacher_fos = new FileOutputStream("Teacher_List_File.ser");

            ObjectOutputStream course_oos = new ObjectOutputStream(course_fos);
            ObjectOutputStream student_oos = new ObjectOutputStream(student_fos);
            ObjectOutputStream teacher_oos = new ObjectOutputStream(teacher_fos);

            course_oos.writeObject(course_list);
            student_oos.writeObject(student_list);
            teacher_oos.writeObject(teacher_list);

            course_fos.close();
            student_fos.close();
            teacher_fos.close();

            course_oos.close();
            student_oos.close();
            teacher_oos.close();

            System.out.println("Data is Saved");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}