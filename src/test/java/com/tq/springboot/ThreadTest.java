/*
package com.tq.springboot;

import org.springframework.boot.test.context.SpringBootTest;

*/
/**
 * @author TQ
 * @Description 线程案例测试
 * @create 2021-11-10 9:34
 *//*


@SpringBootTest
public class ThreadTest {

    //测试方法结束，线程也会结束，这里放在main中
    public static void main(String[] args) {

        Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 616;
        gareen.damage = 50;

        Hero teemo = new Hero();
        teemo.name = "提莫";
        teemo.hp = 300;
        teemo.damage = 30;

        Hero bh = new Hero();
        bh.name = "赏金猎人";
        bh.hp = 500;
        bh.damage = 65;

        Hero leesin = new Hero();
        leesin.name = "盲僧";
        leesin.hp = 455;
        leesin.damage = 80;
*/
/*
//继承thread类
        KillThread killThread1 = new KillThread(gareen, teemo);
        killThread1.start();
        KillThread killThread2 = new KillThread(bh, leesin);
        killThread2.start();
        System.out.println("-----------继承Thread完成--------");
//实现runable接口
        KillRunable killRunable1 = new KillRunable(gareen, teemo);
        new Thread(killRunable1).start();//将实现runable子项作为参数传入，
        KillRunable killRunable2 = new KillRunable(bh, leesin);
        new Thread(killRunable2).start();
        System.out.println("-----------实现runable完成--------");
        *//*


//匿名类
        Thread t1 = new Thread() {
            public void run() {
                //匿名类中用到外部的局部变量teemo，必须把teemo声明为final,但是在JDK7以后，就不是必须加final的了
                while (!teemo.isDead()) {
                    gareen.attackHero(teemo);
                }
            }

        };
        t1.start();

        //代码执行到这里，一直是main线程在运行
        try {
            //t1线程加入到main线程中来，只有t1线程运行结束，才会继续往下走
            t1.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Thread t2 = new Thread(() -> {
            while (!leesin.isDead()) {
                bh.attackHero(leesin);
            }
        });
        t2.start();
    }

}
*/
