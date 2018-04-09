package com.tobycc.java.learning.javascript;

import javax.script.*;

public class ScriptEngineManagerLearning {
    public static void main(String[] args) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        // javax.script.Invocable is an optional interface.
        // Check whether your script engine implements or not!
        // Note that the JavaScript engine implements Invocable interface.
        Invocable inv = (Invocable) engine;


        // JavaScript code in a String
        String script = "function hello(name) { print('Hello, ' + name); }";
        // evaluate script
        engine.eval(script);
        // invoke the global function named "hello"
        inv.invokeFunction("hello", "Scripting!!" );


        String script2 = "var obj = new Object(); obj.hello = function(name) { print('Hello again, ' + name); }";
        engine.eval(script2);
        // get script object on which we want to call the method
        Object obj = engine.get("obj");
        // invoke the method named "hello" on the script object "obj"
        inv.invokeMethod(obj, "hello", "Scripting mark 21!");


        // If your scripting language is object-based or object-oriented, it is possible

        // to implement a Java interface by script methods on script objects.
        // get Runnable interface object from engine. This interface methods
        // are implemented by script methods of object 'obj'
        Runnable r = inv.getInterface(obj, Runnable.class);
        // start a new thread that runs the script implemented
        // runnable interface
        Thread th = new Thread(r);
        th.start();


        /*It is possible to expose multiple global "scopes" for scripts. A single scope is an instance of
        javax.script.Bindings. This interface is derived from java.util.Map<String, Object>. A scope is a
        set of name-value pairs where name is non-empty, non-null String. Multiple scopes are supported by
        javax.script.ScriptContext interface. A script context supports one or more scopes with associated
        Bindings for each scope. By default, every script engine has a default script context. The default
        script context has at least one scope called "ENGINE_SCOPE". Various scopes supported by a script
        context are available through getScopes method.
         */
        engine.put("x", "hello");
        // print global variable "x"
        engine.eval("print(x);");
        // the above line prints "hello"
        // Now, pass a different script context
        ScriptContext newContext = new SimpleScriptContext();
        Bindings engineScope = newContext.getBindings(ScriptContext.ENGINE_SCOPE);
        // add new variable "x" to the new engineScope
        engineScope.put("x", "world");
        // execute the same script - but this time pass a different script context
        engine.eval("print(x);", newContext);
        // the above line prints "world"
    }
}