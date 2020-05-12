package Models;

import java.io.Serializable;

import Configs.AppTheme;

public class Request implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 5272994891606676835L;
    public String name;
    public reqType type;
    public String URL="http://nowhere.no";
    public String headers="";
    public Object body="";
    public String Authentication="";
	public boolean follow=false;
    public String Query="";
    public float time=0f;
    public String code="";
	public String message="";
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