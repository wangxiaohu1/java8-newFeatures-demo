package A_lambda;

/**
 * @author wangxiaohu
 * @version Id: B_1_closure.java, v0.1 2022年01月10日 23:24:25 wangxiaohu Exp $
 * @Description：闭包的代码例子
 *   闭包是定义在一个函数中函数，
 *   闭包能够将一个方法作为一个变量去存储，这个方法有能力去访问所在类的自由变量
 */
public class B_1_closure {
    public static void main(String[] args) {
        Coder coder = new Coder();
        coder.test();
    }
}
class Job{
    //写10行代码
    private int codeLineNum = 10;
    //吃3顿饭
    private int eatNum = 3;

    /**
     * 函数A
     * @return
     */
    public Accept accept(){
        return new Accept(){
            /**
             * 函数B，闭包，这个方法有能力去访问所在类（即Job）的自由变量（即codeLineNum和eatNum）。
             */
            @Override
            public void doWork(){
                //先吃1顿早餐
                if(eatNum <= 0){
                    System.out.println("没饭了");
                }else{
                    eatNum--;
                }

                //在写1行3行
                if(codeLineNum <= 0){
                    System.out.println("活干完了");
                }else{
                    codeLineNum=codeLineNum-3;
                }
            }
        };
    }
    //查看进度
    public void lookSpeed(){
        System.out.println("进度：还剩几顿饭:[" + eatNum + "]，还剩几行代码：[" + codeLineNum + "]");
    }
}
//受理接口
interface Accept{
    //干活，抽象方法
    void doWork();
}

//程序员小码哥
class Coder{
    public void test(){
        Job job = new Job();
        //受理这个工作
        Accept accept = job.accept();
        //开始干活
        accept.doWork();
        //开始干活
        accept.doWork();
        //开始干活
        accept.doWork();

        job.lookSpeed();
    }

}
