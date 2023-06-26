package com.example.demo.Utils;

import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public  class ReflectionUtils {
   private static boolean isPrivate(int modifiers ){
        return Modifier.isPrivate(modifiers);
    }
    private static boolean isPublic(int modifiers ){
        return Modifier.isPublic(modifiers);
    }

    private static boolean isProtected(int modifiers ){
        return Modifier.isProtected(modifiers);
    }
     private static boolean isStatic(int modifiers ){
        return Modifier.isStatic(modifiers);
    }
      private static boolean isFinal(int modifiers ){
        return Modifier.isFinal(modifiers);
    }

    public static String retrieveModifiers(int modifiers){
       String modifiersSting = "";
       if (isPublic(modifiers)){
           modifiersSting += "public ";
       }
       if(isPrivate(modifiers)){
           modifiersSting += "private ";
       }
        if(isProtected(modifiers)){
           modifiersSting += "protected ";
       }if(isStatic(modifiers)){
           modifiersSting += " static";
       }if(isFinal(modifiers)){
           modifiersSting += " final";
       }
       return modifiersSting;
    }

    public static String retrieveParams(Parameter[] parameters){

        String params = "";
        for (Parameter p : parameters) {
            params += p.getType().getSimpleName() + " " + p.getName();
            if (!p.equals(parameters[parameters.length - 1])) {
                params += ", ";
            }
        }
        return params;
    }
}
