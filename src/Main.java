import com.google.common.collect.Range;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Makarovaa on 24.11.17.
 */
public class Main {

    private static BufferedReader stringReader;

    public static void main(String[] args) {
        menu();
        try {
            stringReader.close();
        } catch (IOException i){
            i.printStackTrace();
        }
    }

    public static String keyboardInput () {

        try  {
            stringReader = new BufferedReader(new InputStreamReader(System.in));
            return stringReader.readLine();
        } catch (IOException i) {
            System.out.println("Ошибка!");
            return null;
        }
    }

    public static void menu () {

        double x;
        String path;
        String choise;
        System.out.println("1 - найти число, принадлежащее пересечению подмножеств.");
        System.out.println("2 - найти пересечения подмножеств.");
        choise = keyboardInput();
        switch (choise) {
            case ("1"): {
                double result;
                System.out.println("Введите путь к файлу");
                path = keyboardInput();
                System.out.println("Введите x");
                x = Double.parseDouble(keyboardInput());
                result = new IntersectionOfIntervals
                        (new MultiplicitiesLoader(path), x).findNumber();
                System.out.println( "Число, принадлежащее пересечению подмножеств, максимально близкое к x: "+result);
                break;
            }
            case ("2"): {
                System.out.println("Введите путь к файлу");
                path = keyboardInput();
                HashSet<Range<Double>> result = new IntersectionOfIntervals
                        (new MultiplicitiesLoader(path)).setOfIntervals();
                System.out.println("Пересечения подмножеств:");
                for (Range<Double> t : result) {
                    System.out.println(t.toString());
                }
                break;
            }
            default:{
                System.out.println("Неправильный ввод");
            }
        }
    }
    }

