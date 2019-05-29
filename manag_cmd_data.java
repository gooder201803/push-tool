import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class manag_cmd_data {
    static final String TAG = "myapp";


    // 文件名可随意指定，你可以用文本编辑器打开这个文件（注意，记事本无法处理换行）   
    static String str_filename = "shell_cmds.data";

    
	public static void main(String[] args) throws IOException {

        List<Cmd> L_cmds = new ArrayList<Cmd>();
 
        L_cmds = read_file_cmds(str_filename);
		Mylog.d(TAG, " size = "+L_cmds.size());

/*         L_cmds.add(new Cmd("1","重启","adb reboot"));
        L_cmds.add(new Cmd("2","哈哈","adb test"));
        L_cmds.add(new Cmd("3","root权限","adb root")); */
        
		Mylog.d(TAG, " size = "+L_cmds.size());
        
        L_cmds.remove(0);
		Mylog.d(TAG, " size = "+L_cmds.size());
        
        write_file_cmds(L_cmds, str_filename);
	}
    
    
    public static List<Cmd> read_file_cmds(String file_name) throws IOException {
        
        List<Cmd> my_Cmds = new ArrayList<Cmd>();
        
        if (!new File(file_name).exists())
        {
            Mylog.d(TAG, file_name + " not exist, create it ");
            //createAndSave("1", "root权限, 可读写权限", "adb root;adb remount");
            FileWriter writer = new FileWriter(file_name);     
            writer.close();
            return my_Cmds;
        }
        else
        {   
            Mylog.d(TAG, " exist, read it ");
            my_Cmds = readCmds(file_name);
            Mylog.d(TAG, " size = "+my_Cmds.size());
            showCmds(my_Cmds);
            return my_Cmds;
        } 
    
    }
    


    // 保存 所有 Cmd 对象到文件。保存格式为：
    // 1、每个 Cmd 一行
    // 2、每行依次存放 num, desc, content
    public static void write_file_cmds(List<Cmd> cmds, String file_name) throws IOException {

        // 生成文件内容   
        String data = "";   
        for (Cmd cmd : cmds) {   
            data += OneCmd_to_String(cmd) + "\n";   
        }   
 
        //FileWriter writer = new FileWriter(file_name); // 追加写入
        FileWriter writer = new FileWriter(file_name, false);   // 覆盖写入
        writer.write(data);
        writer.close();   
        Mylog.d(TAG, " 保存完毕。 ");

    }   
    

// -----------------------  写入  -------------------------------------------

    // 一条 cmd 拼成一行字符串
    private static String OneCmd_to_String(Cmd cmd) {
    return cmd.getDesc() + "<-->" + cmd.getContent();
    }
    

    
    
/*   
    // 保存指定的1行命令，不必每次关闭时保存所有命令，这个效率更好，待完善 
    private static void Save_one_cmd(String num, String desc, String content) throws IOException {
        String str_one_cmd = OneCmd_to_String(new Cmd(num, desc, content));
        ...
    }
 */
    
    
// -----------------------  写入  end  -------------------------------------------


// -----------------------  读取   -------------------------------------------

    
    // 一行文件内容 生成一个 Cmd 对象   
    private static Cmd String_to_OneCmd(String line) {
        String[] parts = line.split("\\<\\-\\-\\>");  // 获取被分隔的三个部分   

        Mylog.d(TAG, " 0 = "+parts[0]);
        Mylog.d(TAG, " 1 = "+parts[1]);
        
        return new Cmd(    
                parts[0],     // 描述   
                parts[1]      // 内容  
        );
    }  


    // 从文件中读取 Cmd 对象
    private static List<Cmd> readCmds(String file_name) throws IOException {
        List<Cmd> result = new ArrayList<Cmd>();

        BufferedReader reader = new BufferedReader(new FileReader(file_name));
        String line;
        while ((line = reader.readLine()) != null) {
            
            if(line.equals("")||line.equals("<-->"))
            {
                Mylog.d(TAG, "  tick = ");
                continue;
            }
            
            Cmd cmd = String_to_OneCmd(line);
            if(cmd != null)
            {
                result.add(cmd);
            }

        }

        return result;
    }

 

    // 显示 Cmd 对象   
    private static void showCmds(List<Cmd> cmds) {   
        for (Cmd cmd : cmds) {   
            Mylog.d(TAG, cmd.getDesc() + ", " + cmd.getContent());
        }   
    }
    
 
    
// -----------------------  读取  end  -------------------------------------------
    
}

 
