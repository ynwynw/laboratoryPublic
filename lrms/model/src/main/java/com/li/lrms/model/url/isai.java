package com.li.lrms.model.url;


public class isai {
    private String url="jdbc:mysql://localhost:3306/login?useUnicode=true&characterEncoding=utf8";
    private String pwd="root";
    private String user="root";
    public String tome(){return this.url;}
    public String isu(){return this.user;}
    public String isp(){return this.pwd;}
}
