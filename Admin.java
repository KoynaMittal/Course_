public class Admin {
    private String name = "Admin";
    private String password = "Admin001";

    public String get_username()
    {
        return name;
    }

    public String get_password()
    {
        return password;
    }

    public boolean is_qualified(int marks)
    {
        if(marks>=85)
        {
            return true;
        }
        return false;
    }
}
