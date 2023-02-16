package AssignementCode;
public class MessageDelivery 
{
    //These parameters are based on the ASCII table where ASCII 32 is SP
    //                                                    ASCII 13 is CR
    //                                                    ASCII 10 is LF
    //This makes our life way to easy because instead of typing what number we ant all over the place, we have it as a parameter with two letters.
    String SP = " ";    //ASCII 32
    String CR = "\r";   //ASCII 13
    String LF = "\n";   //ASCII 10 
    //Basic SMTP parameters
    enum COMMANDS {HELO, MAIL, RCPT, DATA, QUIT}
    String ServerName = "LocalHost";
    
    
    public String parseCOMMANDS(String in)
    {
        String out="";
        if(in.equals(null))
            out = "! in is null. Nothing to parse"+CR+LF;
        else if (in.length()> 512)
                out = "500 Line too long"+CR+LF;
        else
        {
            String[] comments = in.split(SP);
            if (comments.length == 1) 
            {    //single part commands
                if(!comments[0].contains(CR+LF))
                    out = "500 Syntax error. command unrecognized"+CR+LF;
                else if(comments[0].contains(CR+LF+"."+CR+LF))
                    out = "250 DATA"+SP+"OK"+CR+LF;
                else if(comments[0].contains("DATA"))
                    out = "354 Start mail input; end with <CRLF>.<CRLF>"+CR+LF;
                else if(comments[0].contains("QUIT"))
                    out = "221 Service closing transmission channel"+CR+LF;
            } 
            //Checking for more parts of message
            else 
            {   
                out = "502 Command not implemented"+CR+LF;
                for(COMMANDS c : COMMANDS.values())
                {
                    if(comments[0].toUpperCase().contains(c.toString()))
                    {
                        if(comments[0].toUpperCase().contains("HELO"))
                            out = "250"+SP+ServerName+CR+LF;      
                        else if(comments[0].toUpperCase().contains("MAIL"))
                            out = "250 MAIL"+SP+"OK"+CR+LF;         
                        else if(comments[0].toUpperCase().contains("RCPT"))
                            out = "250 RCPT"+SP+"OK"+CR+LF;         
                        else if(comments[0].toUpperCase().contains("QUIT"))
                            out = "221"+SP+ServerName+SP+"Service closing transmission channel"+CR+LF;     
                    }                
                }
            }
        }
        return out;
    }
}