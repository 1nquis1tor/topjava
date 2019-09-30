package ru.javawebinar.topjava.util;

//import org.graalvm.compiler.lir.LIRInstruction;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );

        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 3000);

//        .toLocalDate();
//        .toLocalTime();
    }

    public static boolean isExceed(List<UserMeal> mealList, LocalDateTime localDateTime, int caloriesPerDay)
    {
        int caloriesSumm=0;

        for (UserMeal meal: mealList) {
            if (meal.getDateTime().getDayOfYear()==localDateTime.getDayOfYear())
                caloriesSumm+=meal.getCalories();
        }
        return caloriesPerDay<caloriesSumm?true:false;
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        List<UserMealWithExceed> result= new ArrayList<>();

        List<UserMeal> meal=mealList.stream().filter((m)-> ((m.getDateTime().toLocalTime().isAfter(startTime)) && (m.getDateTime().toLocalTime().isBefore(endTime)))).collect(Collectors.toList());
        for(UserMeal m: meal)
            result.add(new UserMealWithExceed(m.getDateTime(), m.getDescription(), m.getCalories(),isExceed(mealList, m.getDateTime(), caloriesPerDay)));


       // for (UserMeal meal: mealList) {
         //   if((meal.getDateTime().toLocalTime().isAfter(startTime)) && (meal.getDateTime().toLocalTime().isBefore(endTime)))
           //    result.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),isExceed(mealList, meal.getDateTime(), caloriesPerDay)));
       // }
        return result;
    }
}
