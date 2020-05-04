package Models;

public class Request {
    public String name;
    public reqType type;
    public Request(String Name)
    {
        name=Name;
        type=reqType.CUSTOM;
    }
    
    public Request(String Name,reqType Type)
    {
        name=Name;
        type=Type;
    }



}