package BodyPlanner;

import java.util.*;

public class Wundong 
{
	static Scanner sc=new Scanner(System.in);
	static ArrayList<String> back= new ArrayList<>(Arrays.asList("풀 업", "랫 풀 다운", "바벨 로우", "시티드 로우"));
	static ArrayList<String> chest= new ArrayList<>(Arrays.asList("벤치프레스", "딥스", "인클라인 벤치프레스", "체스트 플라이"));
	static ArrayList<String> shoulder= new ArrayList<>(Arrays.asList("사이드 레터럴 레이즈", "밀리터리 프레스", "벤트오버 레이즈", "덤벨 프레스"));
	static ArrayList<String> biceps= new ArrayList<>(Arrays.asList("덤벨 컬", "바벨 컬", "해머 컬"));
	static ArrayList<String> triceps= new ArrayList<>(Arrays.asList("케이블 푸쉬다운", "라잉 트라이셉 익스텐션", "덤벨 킥백"));
	static ArrayList<String> leg= new ArrayList<>(Arrays.asList("스쿼트", "레그프레스", "런지", "레그 익스텐션"));
	static ArrayList<String> abs= new ArrayList<>(Arrays.asList("크런치", "레그레이즈"));
	public static ArrayList<Double> oxy_cal=new ArrayList<Double>();//유산소 칼로리를 저장하는 배열
	public static void PrintMuscle(int n)
	{
		if(n==1)
		{
			for(int i=0;i<back.size();i++)
				System.out.println(i+1+". "+back.get(i));
		}
		else  if(n==2)
		{
			for(int i=0;i<chest.size();i++)
				System.out.println(i+1+". "+chest.get(i));
		}
		else  if(n==3)
		{
			for(int i=0;i<shoulder.size();i++)
				System.out.println(i+1+". "+shoulder.get(i));
		}
		else  if(n==4)
		{
			for(int i=0;i<biceps.size();i++)
				System.out.println(i+1+". "+biceps.get(i));
		}
		else  if(n==5)
		{
			for(int i=0;i<triceps.size();i++)
				System.out.println(i+1+". "+triceps.get(i));
		}
		else  if(n==6)
		{
			for(int i=0;i<leg.size();i++)
				System.out.println(i+1+". "+leg.get(i));
		}
		else  if(n==7)
		{
			for(int i=0;i<abs.size();i++)
				System.out.println(i+1+". "+abs.get(i));
		}
	}
	public static ArrayList<Integer> CountWeight()
	{
		ArrayList<Integer> reps=new ArrayList<>();
		System.out.println("운동의 반복횟수 및 세트수를 구성하세요");
		System.out.print("무게 : ");
		int w=sc.nextInt();
		reps.add(w);
		System.out.print("반복 횟수 : ");
		int ea=sc.nextInt();
		reps.add(ea);
		System.out.print("세트 수: ");
		int sets=sc.nextInt();
		reps.add(sets);
		return reps;
	}
	public static ArrayList<String> Muscle(int n)
	{
		ArrayList<String> choose_num=new ArrayList<String>();
		System.out.println("다음 운동들 중 한 운동을 선택하세요"
				+"\n종료하려면 0을 선택하세요");
		if(n==1)
		{
			choose_num.clear();
			while(true)
			{
				System.out.print("선택 : ");
				int at=sc.nextInt();
				if(at==0)
					break;
				choose_num.add(back.get(at-1));
				System.out.println(back.get(at-1)
						+" 운동이 추가되었습니다");
			}
			return choose_num;
		}
		else if(n==2)
		{
			choose_num.clear();
			while(true)
			{
				System.out.print("선택 : ");
				int at=sc.nextInt();
				if(at==0)
					break;
				choose_num.add(chest.get(at-1));
				System.out.println(chest.get(at-1)
						+" 운동이 추가되었습니다");
			}
			return choose_num;
		}
		else if(n==3)
		{
			choose_num.clear();
			while(true)
			{
				System.out.print("선택 : ");
				int at=sc.nextInt();
				if(at==0)
					break;
				choose_num.add(shoulder.get(at-1));
				System.out.println(shoulder.get(at-1)
						+" 운동이 추가되었습니다");
			}
			return choose_num;
		}
		else if(n==4)
		{
			choose_num.clear();
			while(true)
			{
				System.out.print("선택 : ");
				int at=sc.nextInt();
				if(at==0)
					break;
				choose_num.add(biceps.get(at-1));
				System.out.println(biceps.get(at-1)
						+" 운동이 추가되었습니다");
			}
			return choose_num;
		}
		else if(n==5)
		{
			choose_num.clear();
			while(true)
			{
				System.out.print("선택 : ");
				int at=sc.nextInt();
				if(at==0)
					break;
				choose_num.add(triceps.get(at-1));
				System.out.println(triceps.get(at-1)
						+" 운동이 추가되었습니다");
			}
			return choose_num;
		}
		else if(n==6)
		{
			choose_num.clear();
			while(true)
			{
				System.out.print("선택 : ");
				int at=sc.nextInt();
				if(at==0)
					break;
				choose_num.add(leg.get(at-1));
				System.out.println(leg.get(at-1)
						+" 운동이 추가되었습니다");
			}
			return choose_num;
		}
		else if(n==7)
		{
			choose_num.clear();
			while(true)
			{
				System.out.print("선택 : ");
				int at=sc.nextInt();
				if(at==0)
					break;
				choose_num.add(abs.get(at-1));
				System.out.println(abs.get(at-1)
						+" 운동이 추가되었습니다");
			}
			return choose_num;
		}
		return choose_num;
	}
	public static double CalculateRunning(int v, double w,int t)
	{
		double result=0;
		if(v>=8.1)
			result=0.0175*((0.2*(v*16.667) + 3.5)/3.5)*(w*100)*t;
		else
			result = 0.0175*((0.1*(v*16.667) + 3.5)/3.5)*(w*100)*t;
		return result;
	}
	public static double CalculateCycle(int d, double m, int t)
	{
		double ret=0;
		double v=d / (t / 60) ;
		if(v<13)
			ret=0.05*m*t;
		else if(13<=ret&&ret<16)
			ret=0.065*m*t;
		else if(16<=ret&&ret<19)
			ret=0.0783*m*t;
		else if(19<=ret&&ret<22)
			ret=0.0939*m*t;
		else if(22<=ret&&ret<24)
			ret=0.113*m*t;
		else if(24<=ret&&ret<26)
			ret=0.124*m*t;
		else if(26<=ret&&ret<27)
			ret=0.136*m*t;
		else if(27<=ret&&ret<29)
			ret=0.149*m*t;
		else if(29<=ret&&ret<31)
			ret=0.163*m*t;
		else if(31<=ret&&ret<32)
			ret=0.176*m*t;
		else if(32<=ret&&ret<34)
			ret=0.196*m*t;
		else if(34<=ret&&ret<37)
			ret=0.215*m*t;
		else if(37<=ret&&ret<40)
			ret=0.259*m*t;
		else if(40<=ret)
			ret=0.311*m*t;
      return ret;
	}
	public static ArrayList<String> OxygenTraining(int a, double w)
	{
		ArrayList<String>choose_num1=new ArrayList<String>();
		double ret1=0, ret2=0, ret3=0;
		if(a==1)
		{
			choose_num1.clear();
			choose_num1.add("런닝머신");
			System.out.println("런닝머신 kcal를 계산합니다");
			System.out.print("속도를 입력하세요(km/h) : ");
			int velo=sc.nextInt();
			System.out.print("시간을 입력하세요(min)  : ");
			 int time=sc.nextInt();
			 ret1=(CalculateRunning(velo, w, time)/100);
			 System.out.println("소모한 칼로리는 약 "+String.format("%.2f", ret1)+"kcal입니다");
			 oxy_cal.add(ret1);
		}
		else if(a==2)
		{
			choose_num1.clear();
			choose_num1.add("사이클");
			System.out.println("사이클 kcal를 계산합니다");
			System.out.print("거리를 입력하세요(km/h) : ");
			int dist=sc.nextInt();
			System.out.print("시간을 입력하세요(min)  : ");
			 int time=sc.nextInt();
			 ret2=CalculateCycle(dist, w, time);
			 System.out.println("소모한 칼로리는 약 "+String.format("%.2f", ret2)+"kcal입니다");
			 oxy_cal.add(ret2);
		}
		else if(a==3)
		{
			choose_num1.clear();
			choose_num1.add("스탭퍼");
			System.out.println("스텝퍼 kcal를 계산합니다");
			System.out.print("시간을 입력하세요(min)  : ");
			 int time=sc.nextInt();
			 ret3=2*0.07*time;
			 System.out.println("소모한 칼로리는 약 "+String.format("%.2f", ret3)+"kcal입니다");
			 oxy_cal.add(ret3);
		}
		return choose_num1;
	}
	public static ArrayList<String> Training(double w)
	{
		ArrayList<String> choose=new ArrayList<String>();
		while(true)
		{
			System.out.println("한 운동을 선택해주세요\n1. 웨이트\n"
					+ "2. 유산소\n3. 종료");
			System.out.print("운동 : ");
			int n=sc.nextInt();
			if(n==1)
			{
				System.out.println("<<웨이트 트레이닝 MODE>>");
				while(true)
				{
					System.out.println("운동한 부위를 선택해주세요");
					System.out.println("1. 등\n2. 가슴\n3. 어깨\n"
							+"4. 이두\n5. 삼두\n6. 하체\n"
							+"7. 복근\n-1. 중지");
					System.out.print("운동 부위 : ");
					int part=sc.nextInt();
					if(part==-1)
						break;
					PrintMuscle(part);
					choose.addAll(Muscle(part));
				}
			}
			else if(n==2)
			{
				System.out.println("<<유산소 MODE>>");
				while(true)
				{
					System.out.println("수행한 유산소 운동을 선택하세요");
					System.out.println("1. 런닝머신\n2. 사이클\n3. 스탭퍼\n"
							+"-1. 중지");
					System.out.print("유산소 : ");
					int oxy=sc.nextInt();
					if(oxy==-1)
						break;
					else
						choose.addAll(OxygenTraining(oxy, w));
				}
			}
			else if(n==3)
			{
				System.out.println("운동 MODE를 종료합니다");
				 return choose;
			}
		}
	}
	public static void TodayTraining(ArrayList<String> n)
	{
		System.out.println("<<오늘 수행한 운동 목록>>");
		for(int i=0;i<n.size();i++)
			System.out.println(i+1+". "+n.get(i));
		double oxy_ret=0;
		for(int i=0;i<oxy_cal.size();i++)
			oxy_ret+=oxy_cal.get(i);
		System.out.println("유산소로 소모한 총 열량은 "+String.format("%.2f", oxy_ret)+"kcal입니다");
	}
}
