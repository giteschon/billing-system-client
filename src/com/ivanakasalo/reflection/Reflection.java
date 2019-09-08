/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.reflection;

import com.ivanakasalo.dal.*;
import com.ivanakasalo.model.*;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ivy
 */
public class Reflection {

    private static final String LIST_OF_CONSTRUCTORS = "List of constructors";
    private static final String CONSTRUCTOR_PARAMETERS = "Constructor parameters";
    private static final String CONSTRUCTOR_NAME = "Constructor name";

    private static final String LIST_OF_METHODS = "List of methods";
    private static final String INPUT_METHOD_PARAMETERS = "Input method parameters";
    private static final String OUTPUT_METHOD_PARAMETER = "Output method parameter";
    private static final String METHOD_NAME = "Method name";

    private int count = 0;

    public String generateHtml(String title) {

        StringBuffer buffer = new StringBuffer();
        //buffer.append(htmlStart(title));
        htmlStart(title, buffer);
        generateBody(buffer);
        htmlEnd(buffer);

        return buffer.toString();

    }

    private StringBuffer htmlStart(String title, StringBuffer buffer) {
        //StringBuffer buffer= new StringBuffer();
        buffer.append("<!DOCTYPE html>\n");
        buffer.append("<html>\n");
        buffer.append("<head>\n");
        buffer.append("<title>");
        buffer.append(title);
        buffer.append("</title>\n");
        buffer.append("</head>\n");
        buffer.append("<body>\n");
        buffer.append("<h1>");
        buffer.append(title);
        buffer.append("</h1>\n");

        return buffer;

    }

    private StringBuffer htmlEnd(StringBuffer buffer) {
        buffer.append("</body>\n");
        buffer.append("</html>");

        return buffer;
    }

    private StringBuffer generateBody(StringBuffer buffer) {
        List<Object> list= createListOfClasses();
        //instance of a class
       // Category category = new Category();
        
        for (Object object : list) {
           //Class c = category.getClass();
            Class c = object.getClass();
        buffer.append("<h2>");
        buffer.append(c.getName());
        buffer.append("</h2>");
        //table of ctor-s
        generateTable(LIST_OF_CONSTRUCTORS, c, buffer, CONSTRUCTOR_NAME, CONSTRUCTOR_PARAMETERS); 
        
        count=0;
        }
        

        return buffer;

    }

    private StringBuffer generateTable(String title, Class c, StringBuffer buffer, String col1, String col2, String... col3) {
        buffer.append("<h3>");
        buffer.append(title);
        buffer.append("</h3>\n");
        buffer.append("<table border='1'>\n");
        buffer.append("<tr>");
        buffer.append("<th>");
        buffer.append(col1);
        buffer.append("</th>\n");
        buffer.append("<th>");
        buffer.append(col2);
        buffer.append("</th>");
        if (count == 1) {
            buffer.append("<th>");
            //optional parameter is an array, it has to be "casted"
            buffer.append(Arrays.toString(col3));
            buffer.append("</th>");
        }
        buffer.append("</tr>\n");

        if (count == 0) {
            for (Executable ctor : c.getConstructors()) {
                fillData(ctor, buffer);

            }
            buffer.append("</table>");
            count++;
            return generateTable(LIST_OF_METHODS, c, buffer, METHOD_NAME, INPUT_METHOD_PARAMETERS, OUTPUT_METHOD_PARAMETER);
        } else if (count == 1) {
            for (Executable method : c.getMethods()) {
                fillData(method, buffer);
            }
            buffer.append("</table>");
            count++;
            return generateTable("List of fields", c, buffer, "Fiel name", "Access modifier");
        } else if (count == 2) {
            for (Field declaredField : c.getDeclaredFields()) {
                generateFields(declaredField, buffer);

            }
        }

        buffer.append("</table>");
        return buffer;
    }
    

    private StringBuffer fillData(Executable executable, StringBuffer buffer) {
        buffer.append("<tr>\n");
        buffer.append("<td>");
        buffer.append(executable.getName());
        buffer.append("</td>");
        // buffer.append("</tr>\n");
        //buffer.append("<tr>");
        buffer.append("<td>");
        if (executable.getParameterCount() > 0) {
            for (Parameter parameter : executable.getParameters()) {

                buffer.append(parameter.getType().getName());
                buffer.append(", ");
                buffer.append(parameter.getName());
                buffer.append("\n");
            }
        }
        if (executable instanceof Method) {
            buffer.append("<td>");
            buffer.append(((Method) executable).getReturnType().getName()).append("</td>");

        }
        buffer.append("</td></tr>\n");

        return buffer;
    }

    private void generateFields(Field declaredField, StringBuffer buffer) {
        buffer.append("<tr>\n");
        buffer.append("<td>");
        buffer.append(declaredField.getName());
        buffer.append("</td>");
        //decode modifier
        buffer.append("<td>");
        buffer.append(Modifier.toString(declaredField.getModifiers()));

        buffer.append("</td>");
        buffer.append("</tr>\n");
    }

    private List<Object> createListOfClasses() {
       List<Object> list= new ArrayList<>();
       list.add(new Category());
       list.add(new City());
       list.add(new Client());
       list.add(new Fee());
       list.add(new Invoice());
       list.add(new Item());
       list.add(new Service());
       list.add(new Specification());
       list.add(new Task());
       list.add(new TimeLog());
       list.add(new User());
       list.add(new SqlRepository());
       list.add(new RepositoryFactory());
       
       return  list;
    }

}
