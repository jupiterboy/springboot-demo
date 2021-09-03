package com.jupiterboy.springboot.jython;

//import org.python.util.PythonInterpreter;

import java.util.Properties;

public class Test {
    public static void main(String[] args) {
        Properties props = new Properties();
//        props.put("python.home","E:\\programs\\Anaconda3\\Lib");
//        props.put("python.console.encoding","UTF-8");
//        props.put("python.security.respectJavaAccessibility", "false");
//        props.put("python.import.site","false");
        Properties preprops = System.getProperties();
        System.out.println(preprops);
//        PythonInterpreter.initialize(props, preprops, new String[]{});
//
//        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.exec("print('Hello Jython!')");
    }
}
