package AssignementCode;
import java.util.Scanner;
public class Main 
{
    public static void main(String[] args) 
    {
    Scanner input = new Scanner(System.in);

    String username;
    String password;

    System.out.println("Log in:");
    System.out.println("username: ");
    username = input.next();

    System.out.println("password: ");
    password = input.next();
    if(username.equals(username) && password.equals(password))
    {
        System.out.println("You are logged in");       
        Client client = new Client();
        Server server = new Server();
        String FirstMess = "";
        String SecondMess = "";
        
        for(int i=1;i<10;i++)
        {
            //System.out.println("Step "+i+": message: " +message);
            SecondMess = server.Server(FirstMess);
            System.out.println("Step "+i+": server to client: "+SecondMess);
            FirstMess = client.Client(SecondMess);
            System.out.println("Step "+i+": client to server: "+FirstMess);
        } 
    }
    else 
    {
     System.out.println("Incorrect Username or Password");      
     return;
    }

   }
    
}
