package Model;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by Topper on 5/14/2018.
 */

public class Command {

    private String targetClass;
    private String instanceMethodName;
    private String methodName;
    private String[] instanceParamTypeNames;
    private Object[] instanceMethodArgs;
    private String[] methodParamTypeNames;
    private Object[] methodArguments;

    public Command(String targetClass, String instanceMethodName,
                   String methodName, String[] instanceParamTypeNames,
                   Object[] instanceMethodArgs, String[] methodParamTypeNames,
                   Object[] methodArguments) {
        this.targetClass = targetClass;
        this.instanceMethodName = instanceMethodName;
        this.methodName = methodName;
        this.instanceParamTypeNames = instanceParamTypeNames;
        this.instanceMethodArgs = instanceMethodArgs;

        this.methodParamTypeNames = methodParamTypeNames;
        this.methodArguments = methodArguments;
    }

    public Object Execute() {

        //INSPECT decoded items

        System.out.println("COMMAND PARAMETERS-------------------------------------");
        System.out.println("");

        System.out.println("-----targetClass: " + targetClass);
        System.out.println("-----instanceMethodName: " + instanceMethodName);
        System.out.println("-----methodName: " + methodName);

        System.out.println("-----instanceParamTypeNames: " + instanceParamTypeNames.toString());
        for (int i = 0; i < instanceParamTypeNames.length; ++i) {
            System.out.println("--------" + i + ": " + instanceParamTypeNames[i]);
        }
        System.out.println("-----instanceMethodArgs: " + instanceMethodArgs.toString());
        for (int i = 0; i < instanceMethodArgs.length; ++i) {
            System.out.println("--------" + i + ": " + instanceMethodArgs[i]);
        }
        System.out.println("-----methodParamTypeNames: " + methodParamTypeNames.toString());
        for (int i = 0; i < methodParamTypeNames.length; ++i) {
            System.out.println("--------" + i + ": " + methodParamTypeNames[i]);
        }
        System.out.println("-----methodArguments: " + methodArguments.toString());
        for (int i = 0; i < methodArguments.length; ++i) {
            System.out.println("--------" + i + ": " + methodArguments[i]);
        }
        System.out.println("-----methodArguments classes: " + methodArguments.toString());
        for (int i = 0; i < methodArguments.length; ++i) {
            System.out.println("--------" + i + ": " + methodArguments[i].getClass());
        }


        try{
            Class C = Class.forName(targetClass);
            Class[] instanceParamTypes = new Class[instanceParamTypeNames.length];
            Class[] methodParamTypes = new Class[methodParamTypeNames.length];

            for (int i = 0; i < instanceParamTypeNames.length; i++)
            {
                instanceParamTypes[i] = Class.forName(instanceParamTypeNames[i]);
            }

            for (int i = 0; i< methodParamTypeNames.length; i++)
            {
                methodParamTypes[i] = Class.forName(methodParamTypeNames[i]);
            }

            Method instM = C.getMethod(instanceMethodName, instanceParamTypes);
            Object targetInst = instM.invoke(null, instanceMethodArgs);

            Method M = C.getMethod(methodName, methodParamTypes);
            Object obj = M.invoke(targetInst, methodArguments);
            return obj;
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
            return null;
        }
    }
}
