package AssignementCode;
public class Client 
{
    //Here are the parameteres of the Client programm
    String InputMess;   
    String OutputMess;
    String stateV;
    //These parameters are based on the ASCII table where ASCII 32 is SP
    //                                                    ASCII 13 is CR
    //                                                    ASCII 10 is LF
    //This makes our life way to easy because instead of typing what number we ant all over the place, we have it as a parameter with two letters.
    String SP = " ";    //ASCII 32
    String CR = "\r";   //ASCII 13
    String LF = "\n";   //ASCII 10   
    String USERONLINE = "useronline";
    String USEROFFLINE = "useroffline";
    String LOCAL = "client.gr";
    String REMOTE = "server.gr";
    
    //Constructor of the programm
    public Client()
    {
        InputMess = "";
        OutputMess = "";    
        stateV = "CONN";
    }  
    
    public String Client(String messageIN)
    {
        MessageDelivery sm = new MessageDelivery();
        String ClientName = "Client 1";
        
        switch(stateV)
        { 
            case "CONN":
                if (messageIN.contains("220"))
                {         //message in
                    stateV = "HELO";                    //set next state
                    OutputMess = "HELO"+SP+ClientName+CR+LF;  //and go to it
                }
                break;
            case "HELO":
                if(InputMess.contains("QUIT"))
                {
                    stateV = "QUIT";
                    OutputMess = "221"+CR+LF;
                }
                
               //Here we have 3 stages of the algorithm. Beggining, waiting and executing. 
               //When we have the code 250 it means that everything is working properly
                else if(InputMess.contains("250")) 
                {        
                    stateV = "MAIL";
                    OutputMess = "MAIL"+SP+"FROM:"+USERONLINE+"@"+LOCAL+CR+LF;
                }
                //Here is when there is an error and the codes are: 421 means that the domain is not working
                //                                                  500 means that there is a syntax error in commands
                //                                                  501 means that there is a syntax error in parameters
                //                                                  504 means that the commands are not working properly                                                
                else if(InputMess.contains("421")) 
                {    
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("500")) 
                {   
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("501")) 
                {     
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("504")) 
                {     
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                break;
            case "MAIL":
                if(InputMess.contains("QUIT")) 
                {
                    stateV = "QUIT";
                    OutputMess = "221"+CR+LF;
                }                
                //Here we have the start of the programm but also some codes meaning: 250 means that everything is ok
                //                                                                    421 means that service is not available
                //                                                                    451 means that there was an error in processing
                //                                                                    452 means that there is insufficient system storage
                //                                                                    500 means that there is a syntax error in commands
                //                                                                    501 means that there is a syntax error in parameters
                //                                                                    551 means that the server is not local
                else if(InputMess.contains("250")) 
                {   
                    stateV = "RCPT";
                    OutputMess = "RCPT"+SP+"TO:"+USERONLINE+"@"+REMOTE+CR+LF;
                }
                else if(InputMess.contains("421")) 
                {   
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("451")) 
                {   
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("452")) 
                {    
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("500")) 
                {     
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("501")) 
                {     
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("551")) 
                {     
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                break;
            case "RCPT":
                if(InputMess.contains("QUIT")) 
                {
                    stateV = "QUIT";
                    OutputMess = "221"+CR+LF;
                }                
                else if(InputMess.contains("250")) 
                {          //250 OK 
                    stateV = "DATA";
                    OutputMess = "DATA"+CR+LF;
                }
                //Failure codes mentioned before are: 
                //                                   421 means that the domain chosen is not available 
                //                                   451 means that there was an error in processing
                //                                   452 means that there is insufficient system storage
                //                                   500 means that there is a syntax error in commands
                //                                   501 means that there is a syntax error in parameters
                //                                   504 means that a command parameter is not implemented
                //                                   550 means that there is insufficient system memory
                //                                   551 means that the user is not local
                //                                   552 means that there is a port forwarding problem
                //                                   553 means that the programm was not able to complete
                else if(InputMess.contains("421")) 
                {     //421 <domain> Service not available
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("450")) 
                {     
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("451")) 
                {     
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("452")) 
                {     
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("500")) 
                {     
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("501")) 
                {     
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("504")) 
                { 
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("550")) 
                {     
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("551")) 
                {     
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("552")) 
                {     
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }
                else if(InputMess.contains("553")) 
                {     
                    stateV  = "QUIT";
                    OutputMess = "QUIT"+CR+LF; 
                }                
                break;
            case "DATA":
                if(InputMess.contains("QUIT")) 
                {
                    stateV = "QUIT";
                    OutputMess = "221"+CR+LF;
                }                
                else if(InputMess.contains("354")) 
                {
                    stateV = "DATA";
                    OutputMess = "test 1/n";
                } else if(InputMess.contains(""))
                {
                    stateV = "QUIT";
                    OutputMess = CR+LF+"."+CR+LF;
                }
                break;
                //Terminating the programm with only one command meaning 500 syntax error in commands
            case "QUIT":            
                if(InputMess.contains("221")) 
                    OutputMess = "EXIT";    
                else if(InputMess.contains("QUIT")) 
                    OutputMess = "221"+CR+LF;
                else if(InputMess.contains("500"))      
                    OutputMess = "QUIT"+CR+LF;
                else
                    OutputMess = "QUIT"+CR+LF;
                break;
            default:
        }
        return OutputMess;
    }
    
        
    
    
}
