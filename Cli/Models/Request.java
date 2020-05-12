package Models;

import java.io.Serializable;

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
    //#region change
    /**
     * 
     * @param url url of the request 
     * @param Method method of this request from reqType Enum
     * @param follow follow redirects or not
     * @param Headers Headers
     * @param body message Body of request 
     */
    public Request(String url,reqType Method,boolean follow_redirects,String Headers,Object Body)
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