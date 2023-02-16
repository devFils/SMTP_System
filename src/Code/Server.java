package AssignementCode;
public class Server {
    //Here we have the parameters
    String InputMess;   
    String OutputMess;
    String StateofMess;
     //These parameters are based on the ASCII table where ASCII 32 is SP
    //                                                    ASCII 13 is CR
    //                                                    ASCII 10 is LF
    //This makes our life way to easy because instead of typing what number we ant all over the place, we have it as a parameter with two letters.
    String SP = " ";    
    String CR = "\r";   
    String LF = "\n";   
    //Constructor Of the main programm
    public Server()
    { 
        InputMess = "";
        OutputMess = "";    
        StateofMess = "CONN";
    }
    //Main programm 
    public String Server(String InputMess)
    {
        MessageDelivery sm = new MessageDelivery();
        String ServerName = "LocalHost";
        //What does the programm do in every stage
        switch(StateofMess)
        { 
            //When we start the programm
            case "CONN":
                OutputMess = "220"+SP+ServerName+SP+"SMTP Protocol Ready";
                StateofMess = "HELO";
                break;
            case "HELO":
                if(InputMess.contains("QUIT")){
                    OutputMess = "QUIT"+CR+LF;
                    StateofMess = "QUIT";
                }
                //When the programm does not do anything
                else if(InputMess.contains("HELO")){
                    OutputMess = sm.parseCOMMANDS(InputMess);
                    if(OutputMess.contains("250"))
                //When the programm succeds.
                        StateofMess = "MAIL";
                //When the programm quits. We use the 421 error code to indicate that the programm has quited.
                    else if(OutputMess.contains("421"))      
                        StateofMess  = "QUIT";
                //Whe nthe programm puts out errors for us to troubleshoot. 500 indicates a Syntax error on commands
                //                                                          501 indicates a Syntax error in parameters
                //                                                          504 indicates that a command parameter is not implemented
                    else if(OutputMess.contains("500"))     
                        StateofMess = "QUIT";
                    else if(OutputMess.contains("501"))      
                        StateofMess = "QUIT";
                    else if(OutputMess.contains("504"))     
                        StateofMess = "QUIT";
                }
                break;
            
            case "MAIL":
                if(InputMess.contains("QUIT")){
                    OutputMess = "QUIT"+CR+LF;
                    StateofMess = "QUIT";
                }
                //When the programm does not do anything
                else if(InputMess.contains("MAIL")){
                    OutputMess = sm.parseCOMMANDS(InputMess);
                //When the programm succeds.
                    if(OutputMess.contains("250"))
                        StateofMess = "RCPT";
                //When the programm has a failure with every code numered: 421 means that the domain chosen is not available 
                //                                                         451 means that there was a problem in processing
                //                                                         452 means that there is not enough system storage
                //                                                         551 means that the user is not local
                //                                                         500 means that there is a syntax error in commands
                //                                                         501 means that there is a syntax error in parameters
                    else if(OutputMess.contains("421"))      
                        StateofMess  = "QUIT";
                    else if(OutputMess.contains("451"))      
                        StateofMess  = "QUIT";
                    else if(OutputMess.contains("452"))      
                        StateofMess  = "QUIT";
                    else if(OutputMess.contains("551"))      
                        StateofMess  = "QUIT";
                    else if(OutputMess.contains("500"))      
                        StateofMess = "QUIT";
                    else if(OutputMess.contains("501"))      
                        StateofMess = "QUIT";
                }
                break;
             //Here the programm prints the recepeint of the email
            case "RCPT":
                if(InputMess.contains("QUIT")){
                    OutputMess = "QUIT"+CR+LF;
                    StateofMess = "QUIT";
                }
                //When the programm does not do anything
                else if(InputMess.contains("RCPT")){
                 //When the programm succeds.
                    OutputMess = sm.parseCOMMANDS(InputMess);
                    if(OutputMess.contains("250"))
                        StateofMess = "DATA";
                //When the programms has a failure it shows only one code: 500 is a syntax error regarding commands
                    else if(OutputMess.contains("500"))     
                        StateofMess = "QUIT";                    
                }
                break;
             //HEre we see the data stored
            case "DATA":
                if(InputMess.contains("QUIT")){
                    OutputMess = "QUIT"+CR+LF;
                    StateofMess = "QUIT";
                }
                //Starting of the programm      
                else if(InputMess.contains("DATA")){
                //When the programm waits
                    OutputMess = sm.parseCOMMANDS(InputMess);
                //When the programm succeds
                    if(OutputMess.contains("354"))
                        StateofMess = "DATA";
                //When the programm failes it shows these error codes: 500 means that there is a syntax error regarding commands                                                              
                    else if(OutputMess.contains("500"))      
                        StateofMess = "QUIT";
                } else if(InputMess.contains(CR+LF+"."+CR+LF)) {    
                    OutputMess = sm.parseCOMMANDS(InputMess);       
                    StateofMess = "QUIT";
                } else
                    OutputMess= "";       
                break;
            //Last commands for the programm to exit
            case "QUIT":
                //When the programm waits
                if(InputMess.contains("221")) 
                    OutputMess = "EXIT";   
                else if(InputMess.contains("QUIT")) 
                    OutputMess = "221"+CR+LF;
                //When it has an error it shows code 500 meaning a syntax error in command
                else if(InputMess.contains("500"))      
                    OutputMess = "QUIT 500"+CR+LF;              
                else
                    OutputMess = "QUIT"+CR+LF;
                break;
            default:
        }
        return OutputMess;
    }
}
