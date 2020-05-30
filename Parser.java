package St;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;


public class Parser {
    HashMap<String, Commands> CommandsMap = new HashMap<String, Commands>();

    public void parsing(Stack stack, String commandName){
        try {
            //fabric:
            Commands command = CommandsMap.get(commandName);//смотрим есть ли такая команда из мэпки
            if(command == null){//рефлексия
                Class<?> cl = Class.forName(commandName);//по полному имени класса загружаем класс
                Constructor<?> con = cl.getDeclaredConstructor();//смотрим конструктор
                command = (Commands) con.newInstance();//вызываем конструктор и создаем объект
                CommandsMap.put(commandName, command);//добавляем в мэп
            }
            command.ToDo(stack);//принимает стэк
        } catch (InvocationTargetException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
