package BodyPlanner;
import java.util.*;

public class Main {

	public static void main(String[] args)
	{
		System.out.println("<Your Body Planner>");
		String name=Info.InputName();
		double height=Info.InputHeight();
		double weight=Info.InputWeight();
		int age=Info.InputAge();
		int gender=Info.InputGender();
		double harris=Info.Harr(name, height, weight, age, gender);
		double amount=Info.CalActive(harris, name);
		int body_plan=Info.BodyPlan();
		
		ArrayList<Double> your_cal=Info.Calculate(body_plan, weight,amount);
		Info.MakeBody(your_cal);
		ArrayList<Double> achieve_meal=Sikdan.Meal();
//		ArrayList<String> eaten =Sikdan.food_list;
		Sikdan.EatAll(achieve_meal);
		Sikdan.PrintFoodList();
		ArrayList<Double> lack_today=Sikdan.LackCal(your_cal, achieve_meal);
		Sikdan.PlusMinus1(lack_today.get(0));
		Sikdan.PlusMinus2(lack_today.get(1));
		Sikdan.PlusMinus3(lack_today.get(2));
		Sikdan.PlusMinus4(lack_today.get(3));
		
		ArrayList<String> choose=new ArrayList<String>();
		choose.addAll(Wundong.Training(weight));
		Wundong.TodayTraining(choose );
	}

}
