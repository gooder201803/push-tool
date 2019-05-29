
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class push_tool extends JFrame{




    static final String TAG = "myapp";
    static String str_filename = "shell_cmds.data";

	static { 		/* 1. load */
        Mylog.d(TAG, "loadLibrary  libnative.so");
		System.loadLibrary("native"); /* libnative.so */
 	}
	public native String run_cmd(String str);
    
    
    
    private List<Cmd> gL_cmds = new ArrayList<Cmd>();
    private int have_cmds_cnt = 0;

    
    // 得到显示器屏幕的宽高
    public int Screen_width     = Toolkit.getDefaultToolkit().getScreenSize().width;
    public int Screen_height    = Toolkit.getDefaultToolkit().getScreenSize().height;
    // 定义窗体的宽高
    public int windowsWedth = 1000;
    public int windowsHeight = 600;
    
    static int s_num = 0;
    static JTextField[] s_desc_text;
    static JTextField[] s_cmd_text;

    
    public void add_head_cmd(Container cp){
        
        final JTextField num_text   = new JTextField("序号",    5);
        final JTextField desc_text  = new JTextField("描述",    5);
        final JTextField cmd_text   = new JTextField("命令(so文件使用绝对路径)",    5);
        final JButton exit_key      = new JButton("保存并退出程序");

        num_text.setEditable(false);
        cp.add(num_text);
        
        desc_text.setEditable(false);
        cp.add(desc_text);
        
        cmd_text.setEditable(false);
        cp.add(cmd_text);
        
        cp.add(exit_key);
        

        
        exit_key.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {

                List<Cmd> L_cmds = new ArrayList<Cmd>();
                
                for(int i=0; i < (have_cmds_cnt + 5); i++)
                {
                    L_cmds.add(new Cmd(s_desc_text[i].getText(), s_cmd_text[i].getText()));
                }

                Mylog.d(TAG, " s_desc_text="   + s_desc_text[0].getText());
                Mylog.d(TAG, " s_cmd_text="    + s_cmd_text[0].getText());
                
                try {
                    manag_cmd_data.write_file_cmds(L_cmds, str_filename);
                } catch (IOException e) {
                    System.out.println(e);
                }
                System.exit(0);
            }
        });
        
        
    }
    

    public void add_one_cmd(Container cp, String str_desc, String str_cmd){
  
        final JTextField num_text = new JTextField("" + (s_num+1),     5);

        s_desc_text[s_num]    = new JTextField(str_desc,   20);
        s_cmd_text[s_num]     = new JTextField(str_cmd,    200);
        final JButton cmd_run_key  = new JButton("执行命令");


        num_text.setEditable(false);
        cp.add(num_text);
        cp.add(s_desc_text[s_num]);
        cp.add(s_cmd_text[s_num]);
        cp.add(cmd_run_key);

        // 文本框里敲了回车，执行此函数
        s_cmd_text[s_num].addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                Mylog.d(TAG, " s_cmd_text ");
                // TODO 自动生成方法存根， 
                //s_cmd_text.setText("触发事件");
            }
        });
        
        cmd_run_key.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                Mylog.d(TAG, " now run cmd "+ num_text.getText());
                
                int cmd_index = Integer.parseInt(num_text.getText());
                String str_cmd = s_cmd_text[cmd_index - 1].getText();
                
                Mylog.d(TAG, " str_cmd=" + str_cmd);
                run_cmd(str_cmd);

            }
        });	

        s_num++;
    }

	public push_tool(){

		try {
            gL_cmds = manag_cmd_data.read_file_cmds(str_filename);
		} catch (IOException e) {
			System.out.println(e);
		}


        have_cmds_cnt = gL_cmds.size();
		Mylog.d(TAG, " size = "+have_cmds_cnt);
		Mylog.d(TAG, " gL_cmds.get(0).getDesc() = "+gL_cmds.get(0).getDesc());
		Mylog.d(TAG, " gL_cmds.get(0).getContent() = "+gL_cmds.get(0).getContent());
    
    
// -----------------------------  layout   ---------------------------------------------------------------     
        // 设置窗体位置和大小
        this.setBounds((Screen_width - windowsWedth) / 2, (Screen_height - windowsHeight) / 2, 
        windowsWedth, windowsHeight);
        
        // 点 x 号无法关掉这个app, 可以关掉终端来结束
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // 点 x 号关掉这个app
		//setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		Container cont = getContentPane();// 窗口是个容器
        cont.setLayout(new GridLayout(1 + have_cmds_cnt + 5, 4, 5, 5)); // 设置7行4列的网格，水平，垂直间距 5, 5

        s_desc_text   = new JTextField[have_cmds_cnt + 5];
        s_cmd_text    = new JTextField[have_cmds_cnt + 5];


        add_head_cmd(cont);
        for (Cmd cmd : gL_cmds) {
            add_one_cmd(cont, cmd.getDesc(), cmd.getContent());
        }

        for (int i = 0; i < 5; i++)
        {
            add_one_cmd(cont, "", "");
            //gL_cmds.add(new Cmd("", ""));
        }
		//Mylog.d(TAG, " end  size = "+gL_cmds.size());

        // 设置窗体可见
		setVisible(true);
	}

	public static void main(String[] args) {

        push_tool my_push = new push_tool();
	}
}






