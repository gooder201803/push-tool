

// 要持久化的类   
public class Cmd {  
    private String desc;        // 描述   
    private String content;     // 内容 
    
    Cmd(String desc, String content) {   
        this.desc = desc;
        this.content = content;
    }
    
  

    public void setDesc(String desc) {   this.desc = desc;}   
    public String getDesc() {   return desc;}   

    public void setContent(String content) {   this.content = content;}   
    public String getContent() {   return content;}   


} 