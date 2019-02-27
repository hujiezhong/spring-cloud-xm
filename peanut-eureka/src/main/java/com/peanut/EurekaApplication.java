package com.peanut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
//@EnableRedisHttpSession
public class EurekaApplication {

    public static void main(String[] args) {
        /*String stu = "123abc中国";
        int num = 0;
        int yin = 0;
        int guo = 0;
        for(int i=0;i<stu.length();i++){
            char s = stu.charAt(i);
            if(s>='0'&&s<='9'){
                num++;
            }else if((s>='a'&&s<='z')||(s>='A'&&s<='Z')){
                yin++;
            }else{
                guo++;
            }
        }
        System.out.println(num+"-"+yin+"-"+guo);

        int[] shu = {5,3,1,4,2};
        for(int i=1;i<shu.length;i++){
            for(int a=0;a<shu.length - i;a++){
                if(shu[a]>shu[a+1]){
                    int sh = shu[a];
                    shu[a] = shu[a+1];
                    shu[a+1] = sh;
                }
            }

        }
        for(int i=0;i<shu.length;i++){
            System.out.println(shu[i]);
        }

        System.out.println(di(8));
*/
        SpringApplication.run(EurekaApplication.class,args);
    }

  /*  public static int di(int i){
        if(i == 1){
            return 10;
        }
        System.out.println(i);
        return di(i-1) + 2;
    }*/
}
