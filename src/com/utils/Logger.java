package com.utils;

public class Logger {
    
    // If VERBOSE = true, message will be printed to screen
    // If VERBOSE = false, message won't be printed to screen
    static final boolean VERBOSE = true;

    private String classname = "";

    /******************************************************************************** 
     * Method setClassname
     * @param classname
     * @return
     ********************************************************************************/
    public void setClassname(String classname) {
        this.classname = classname;
    }

    /********************************************************************************
     * Common's Constructor
     * @param classname
     ********************************************************************************/
    public Logger(String classname) {
        this.classname = classname;
    }

    /********************************************************************************
     * Method log
     * Print log message to screen if VERBOSE = true
     * @param classname
     * @return
     ********************************************************************************/
    public void log(String msg) {
        if (VERBOSE) {
            System.out.println("[" + this.classname + "] " + msg);
        }
    }

    /********************************************************************************
     * Method log
     * Print error message to screen if VERBOSE = true
     * @param classname
     * @return
     ********************************************************************************/
    public void error(String msg) {
        if (VERBOSE) {
            System.out.println("[ERROR] [" + this.classname + "] " + msg);
        }
    }
    
    public static void writeln(Object o) {
    	System.out.println(" "+o);
    }
    
    public static void writeln() {
    	System.out.println();
    }

}