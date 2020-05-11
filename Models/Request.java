package Models;

import java.util.HashMap;

import Configs.AppTheme;

public class Request {
    public String name;
    public reqType type;
    public String URL="http://nowhere.no";
    public HashMap<String,String> headers=null;
    public String body="";
    public String Authentication="";
    public String Query="";
    public float time=0f;
    public String code="";
    public double size=0d;

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
    @Override
    public String toString() {
        
        return type+" "+name;
    }

    public static java.awt.Color getColor(reqType value)
    {
            java.awt.Color c;
            switch(value)
            {
                case GET:
                c=AppTheme.GET_COLOR;
                break;

                
                case POST:
                c=AppTheme.POST_COLOR;
                break;
         
                case DELETE:
                c=AppTheme.DELETE_COLOR;
                break;
                
                case PUT:
                c=AppTheme.PUT_COLOR;
                break;

                case PATCH:
                c=AppTheme.PATCH_COLOR;
                break;

                
                default:
                c=AppTheme.Unkown;
                break;
            }
            return c;
    }



}