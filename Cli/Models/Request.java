package Models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Request implements Serializable{
    /**
     *
     */
    //change
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
    //change
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
    //#region change
    /**
     * 
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
    //#endregion change

    /**
     * get color is Here
     */



}