package Models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import Configs.AppTheme;
import CustomComponents.CTabbedPane;

public class Request implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 5272994891606676835L;
    public String name;
    public reqType type;
    public String URL="http://nowhere.com";
    public HashMap<String,String> headers=null;
    public Map<String,List<String> > response_headers=null;
    public Object body="";
   // public String Authentication="";
   // public String Query="";
    public boolean follow=false;
    public float time=0f;
    public int code=404;
    public String message="";
    public long size=0;
    public int redirects=0;
    
    public Request(String Name)
    {
        name=Name;
        type=reqType.POST;
       
    }
    /**
     * @param name will be shown in gui list
     * @param type is request type
     */
    public Request(String Name,reqType Type)
    {
        name=Name;
        type=Type;
        
    }

    /**
     *  **dont use in gui mode **
     * @param url url of the request 
     * @param Method method of this request from reqType Enum
     * @param follow follow redirects or not
     * @param Headers Headers
     * @param body message Body of request 
     * 
     */
    public Request(String url,reqType Method,boolean follow_redirects,HashMap<String,String> Headers,Object Body)
    {
        URL=url;
        type=Method;
        follow=follow_redirects;
        headers=Headers;
        this.body=Body;
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