package com.yjx.individual.docexe;


import com.yjx.individual.common.vo.ReturnMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/docExe")
public class DocExe {


    @PostMapping("/getDoc")
    public ReturnMsg getDoc(){
        ReturnMsg msg = new ReturnMsg();

        return msg;
    }


    public static void main(String[] args) {

        String osName = System.getProperty("os.name" );
        System.out.println("----------"+osName);
//        try{
//            Runtime mt =Runtime.getRuntime();
//            //找到相对应的绝对路径。启动记事本文件
////            File myfile =new File("c:\\Windows","Notepad.exe");
//            File myfile =new File("C:\\Users\\Administrator\\Desktop\\737-700-7B22\\TAKEOFF","STAS.exe");
//            mt.exec(myfile.getAbsolutePath());
//            //创建新的文件路径,启动ie浏览器
//            myfile = new File("C:\\Users\\Administrator\\Desktop\\737-700-7B22","IEXPLORE.www.sohu.com");
//            mt.exec(myfile.getAbsolutePath());
//        }
//        catch(Exception e)
//        {
//            System.out.println(e);
//        }
        String ExePath = "C:\\Users\\Administrator\\Desktop\\737-700-7B22\\TAKEOFF\\STAS.exe";
        String DataPath = "C:\\Users\\Administrator\\Desktop\\737-700-7B22\\TAKEOFF\\STAS.inp";
        String goPath = "C:\\Users\\Administrator\\Desktop\\737-700-7B22\\test\\aa.txt";

        String batPath ="E:\\nfrock\\windows_amd64\\Sunny-Ngrok启动工具.bat";
        String batExePath = "E:\\nfrock\\windows_amd64\\sunny.exe";
        String batid= "80f02938f686a98d";

        String jarDoc ="F:\\IntelliJProject\\individual_payment\\target\\individual-0.0.1-SNAPSHOT.jar";
        String jarPath ="java -jar ";

        String cmdPath="C:/Windows/System32/cmd.exe";
        String cmdDoc ="ipconfig";

        String[] cmd = {ExePath,DataPath}; // 飞行程序 exe 文件启动 加 入参
        String[] batcmd = {batPath,batid}; // bat 文件启动 加 入参
        String[] javacmd = {jarPath,jarDoc}; //java 文件启动
        String[] cmdCmd ={cmdPath,cmdDoc};//cmd exe 文件执行加入参
        String aa = ExePath + "  " +DataPath+"  "+goPath;
        String[] s = aa.split("  ");
        for (String ss:s
             ) {
            System.out.println(ss);
        }

        Process process = null;
        try {
            Runtime runtime = Runtime.getRuntime();

            String[] aaa= {"80f02938f686a98d"};

            process = runtime.exec(batcmd);
//            process = runtime.exec("cmd /c start /b E:/nfrock/windows_amd64/Sunny-Ngrok启动工具.bat 80f02938f686a98d");
//            process = runtime.exec("C:/Windows/System32/cmd.exe /C ipconfig");
//            process = runtime.exec(jarPath);
//            process = runtime.exec("C:/Users/Administrator/Desktop/737-700-7B22/TAKEOFF/STAS.exe  C:/Users/Administrator/Desktop/737-700-7B22/TAKEOFF/STAS.inp");
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));

            String str;
            while(( str = br.readLine()) != null)
            {
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                process.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

}
