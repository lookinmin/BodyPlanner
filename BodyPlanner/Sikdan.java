package BodyPlanner;

import java.util.*;


public class Sikdan
{
	static Scanner sc=new Scanner(System.in);
    static double[] hyunmi = {195, 43, 4,  1.4};
	static double[] bread = {247, 41, 13, 4};
    static double[] s_potato = {155,37, 1.4, 0};
    static double[] chicken =  {164, 0, 30, 4};
    static double[] nuts =  {85, 11, 3, 4};
	 static double[] avocado = {187, 6, 2, 19};
	 static double[] salmon = {110, 0, 20, 4};
	 static double[] my_protein =  {100, 1.5, 21, 2};
	 static double[] apple =  {52, 15, 0, 0};
	 static double[] egg = {68, 0.5, 6, 5};
	 static double[] milk =  {130, 9, 69, 8};
	 static double[] almond =  {174,6.5, 6, 15};
	 public static ArrayList<String> food_list = new ArrayList<String>();//먹은 음식을 저장하는 배열
	public static ArrayList<Double> AddSum(ArrayList<Double>a, double[] b)
	{
		a.add(0, b[0]);
		a.add(1, b[1]);
		a.add(2, b[2]);
		a.add(3, b[3]);
		return a;
	}
	public static ArrayList<Double> Meal()
	{
		ArrayList<Double> eaten= new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0));
		System.out.println("<식단을 구성해주세요>\n영양군을 선택하세요");
		while(true)
		{
			System.out.println("1.탄수화물\n2.단백질\n3.지방\n4.추가\n0.종료");
			System.out.print("영양군 : ");
			int nutri=sc.nextInt();
			if(nutri==1)
			{
				System.out.println("탄수화물 함량이 높은 음식들입니다");
				System.out.println("-------------------------");
				System.out.println("1. 현미밥\n2. 통밀빵\n3. 고구마");
				System.out.print("선택 : ");
				int c1=sc.nextInt();
				if(c1==1)
				{
					AddSum(eaten, hyunmi);
					food_list.add("현미밥");
				}
				else if(c1==2)
				{
					AddSum(eaten, bread);
					food_list.add("통밀빵");
				}
				else if(c1==3)
				{
					AddSum(eaten, s_potato);
					food_list.add("고구마");
				}
			}
			else if(nutri==2)
			{
				System.out.println("단백질 함량이 높은 음식들입니다");
				System.out.println("-------------------------");
				System.out.println("1. 닭가슴살\n2. 계란\n3. 훈제 연어");
				System.out.print("선택 : ");
				int d1=sc.nextInt();
				if(d1==1)
				{
					AddSum(eaten, chicken);
					food_list.add("닭가슴살");
				}
				else if(d1==2)
				{
					AddSum(eaten, egg);
					food_list.add("계란");
				}
				else if(d1==3)
				{
					AddSum(eaten, salmon);
					food_list.add("훈제 연어");
				}
				
			}
			else if(nutri==3)
			{
				System.out.println("지방 함량이 높은 음식들입니다");
				System.out.println("-------------------------");
				System.out.println("1. 아보카도\r\n"
						+ "2. 아몬드\r\n"
						+ "3. 우유");
				System.out.print("선택 : ");
				int f1=sc.nextInt();
				if(f1==1)
				{
					AddSum(eaten, avocado);
					food_list.add("아보카도");
				}
				else if(f1==2)
				{
					AddSum(eaten, almond);
					food_list.add("아몬드 30g");
				}
				else if(f1==3)
				{
					AddSum(eaten, milk);
					food_list.add("우유");
				}
				
			}
			else if(nutri==4)
			{
				System.out.println("추가적으로 섭취하는 식품입니다");
				System.out.println("-------------------------");
				System.out.println("1. 프로틴\r\n"
						+ "2. 하루 한줌 견과\r\n"
						+ "3. 사과");
				System.out.print("선택 : ");
				int g1=sc.nextInt();
				if(g1==1)
				{
					AddSum(eaten, my_protein);
					food_list.add("프로틴");
				}
				else if(g1==2)
				{
					AddSum(eaten, nuts);
					food_list.add("한 줌 견과");
				}
				else if(g1==3)
				{
					AddSum(eaten, apple);
					food_list.add("사과");
				}
			}
			else if(nutri==0)
			{
				System.out.println("입력을 마칩니다");
				break;
			}
		}
		return eaten;
		
	}
	public static void PrintFoodList()
	{
		System.out.println("--섭취한 음식 목록입니다--");
		System.out.print("[ ");
		for(int i=0;i<food_list.size();i++)
			System.out.print(food_list.get(i)+" ");
		System.out.println("]");
	}
	public static void EatAll(ArrayList<Double>eaten)
	{
		System.out.println("총 흡수한 열량은 "+String.format("%.2f", eaten.get(0))+"kcal입니다");
		System.out.println("총 흡수한 탄수화물은 "+String.format("%.2f", eaten.get(1))+"kcal입니다");
		System.out.println("총 흡수한 단백질은 "+String.format("%.2f", eaten.get(2))+"kcal입니다");
		System.out.println("총 흡수한 지방은 "+String.format("%.2f", eaten.get(3))+"kcal입니다");
	}
	public static ArrayList<Double> LackCal (ArrayList<Double>a, ArrayList<Double>b)
	{
		ArrayList<Double>c= new ArrayList<Double>();
		c.add(a.get(0)-b.get(0));
		c.add(a.get(1)-b.get(1));
		c.add(a.get(2)-b.get(2));
		c.add(a.get(3)-b.get(3));
		return c;
	}
	public static void PlusMinus1(double a)
	{
		if(a>=0)
			System.out.println("모자란 열량은 "+String.format("%.2f", a)+"kcal입니다");
		else
			System.out.println("초과한 열량은 "+String.format("%.2f", -a)+"kcal입니다");
	}
	public static void PlusMinus2(double a)
	{
		if(a>=0)
			System.out.println("모자란 탄수화물은 "+String.format("%.2f", a)+"g입니다");
		else
			System.out.println("초과한 탄수화물은 "+String.format("%.2f", -a)+"g입니다");
	}
	public static void PlusMinus3(double a)
	{
		if(a>=0)
			System.out.println("모자란 단백질은 "+String.format("%.2f", a)+"g입니다");
		else
			System.out.println("초과한 단백질은 "+String.format("%.2f", -a)+"g입니다");
	}
	public static void PlusMinus4(double a)
	{
		if(a>=0)
			System.out.println("모자란 지방은 "+String.format("%.2f", a)+"g입니다");
		else
			System.out.println("초과한 지방은 "+String.format("%.2f", -a)+"g입니다");
	}
}
