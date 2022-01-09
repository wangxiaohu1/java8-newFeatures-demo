package H_nashornScript;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author wangxiaohu
 * @version Id: G_Java8NashornJavaScriptDemo1.java, v0.1 2020年11月13日 16:56:59 wangxiaohu Exp $
 */
public class NashornScriptDemo1 {
    public static void main(String[] args) {
        try{
            ScriptEngineManager se = new ScriptEngineManager();
            ScriptEngine nashorn = se.getEngineByName("nashorn");

            String value = "java";
            nashorn.eval("print('" + value + "')" );
            Integer intValue = (Integer) nashorn.eval("10 + 2");
            System.out.println("intValue：" + intValue);

            // 输出
            // java
            // intValue：12

        }catch (ScriptException e){
            e.printStackTrace();
        }

    }
}
