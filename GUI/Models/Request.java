package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import Configs.AppTheme;
public class Request implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 5272994891606676835L;
    public String name="";
    public reqType type=reqType.POST;
    public String URL="http://nowhere.com";
    public HashMap<String,String> headers=new HashMap<>();
    public ArrayList<String> headers_DEACTIVATED=new ArrayList<String>();
    public Map<String,List<String> > response_headers=new HashMap<>();
    public Object body=null;
   // public String Authentication="";
   // public String Query="";
    public boolean follow=false;
    public boolean isSending=false;//prevents multiple change on request
    public float time=0f;
    public int code=404;
    public String message="";
    public long size=0;
    public String fileName="";//for previewing content
    public int redirects=0;
    public HashMap<String,String> BODY_FORM_DATA = new HashMap<>();
    public ArrayList<String> BODY_FORM_DATA_DEACTIVATED = new ArrayList<String>();
    public java.io.File BODY_Binary_DATA=null;
    public String BODY_JSON_DATA="";
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