package com.briup.ch12;

import java.util.Scanner;
class teacher{
	private long id;
	private String name;
	private int age;

	public teacher(){
	
	}
	public teacher(long id,String name,int age){
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public void setId(long id){
		this.id = id;
	}
	public long getId(){
		return this.id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setAge(int age){
		this.age = age;
	}
	public int getAge(){
		return this.age;
	}
	public String toString(){
		return "teacher[id:"+this.id+",name:"+this.name+",age:"+this.age+"]";
	}
}
public class Sms {
	//��ʦ���飬�����������н�ʦ����Ϣ��
	private teacher[] stus = new teacher[3];
	private int index = 0; //��ʦ�ĸ���
	//��ӽ�ʦ
	public void add(teacher stu){
		//��������е�Ԫ�صĸ������ڵ������鳤�ȵ�ʱ��˵�����鳤�Ȳ���
		if(index>=stus.length){
			//������չ
			teacher[] demo = new teacher[stus.length+3];
			//����Ŀ���
			System.arraycopy(stus,0,demo,0,stus.length);
			stus = demo;
		}
		stus[index++] = stu;// stus[index] = stu; index++;
	}
	/**
	stus = {
		{1001,terry},
		{1002,larry},	
		{1003,tom},
		null,
		null
	
	}
	// 1002  stuIndex = 1; index = 3
	for(int i=1;i<2;i++){
		//stus[0] = stus[1]
		//stus[1] = stus[2]
		stus[2] = stus[3]
	}
	index = 3
	{1,2,4,5,6,7,8,9,0}
	*/
	//ͨ��idɾ����ʦ 1002  1T  "HELLO WORLD"
	public void deleteById(long id){
		//���÷�����ȡidΪָ�����������������е�λ��
		int stuIndex = queryIndexById(id); // 1
		if(stuIndex!=-1){
			for(int i=stuIndex;i<index-1;i++){
				stus[i] = stus[i+1];
			}
			stus[--index] = null;
		}
	}
	
	//ͨ��id���Ҹý�ʦ���ڵ�λ�� 1002
	private int queryIndexById(long id){
		int stuIndex= -1;
		for(int i=0;i<index;i++){
			if(stus[i].getId() == id){
				stuIndex = i;
				break;
			}
		}
		return stuIndex;
	}
	//ͨ��id��ѯ��ʦ ddl  dml
	public teacher queryById(long id){
		//���÷�����ȡidΪָ�����������������е�λ��
		int stuIndex = queryIndexById(id);
		return stuIndex==-1?null:stus[stuIndex];
	}

	//�鿴���н�ʦ��Ϣ
	public teacher[] queryAll(){
		teacher[] demo = new teacher[index];
		System.arraycopy(stus,0,demo,0,index);
		return demo;
	}
	/**
	{
	{1001,terry,12},
	{1002,larry,13},
	null
	}
	1002
	{1002,larry,13}
	�����롣������[name,age]
	tom#14
	{1002,tom,14}
	*/  
	public void update(teacher stu){
		for(int i=0;i<index;i++){
			if(stu.getId() == stus[i].getId()){
				stus[i].setName(stu.getName());
				stus[i].setAge(stu.getAge());
			}
		}
	}
	//�˵�
	public void menu(){
		System.out.println("********��ʦ����ϵͳ*******");
		System.out.println("*1���鿴���н�ʦ��Ϣ*");
		System.out.println("*2����ӽ�ʦ��Ϣ*");
		System.out.println("*3��ɾ����ʦ��Ϣ*");
		System.out.println("*4����ѯ��ʦ��Ϣ*");
		System.out.println("*5���޸Ľ�ʦ��Ϣ*");
		System.out.println("*exit���˳�*");
		System.out.println("*help������*");
		System.out.println("***************************");
	}

	public static void main(String[] args){
		//����sms����
		Sms sms = new Sms();
		sms.menu();	//��ʾ��ҳ��
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.print("�����빦�ܱ�ţ�");
			//�ȴ��û����빦�ܱ�ţ����û�����س���ʱ���ȡ�س�ǰ���������
			String option = scanner.nextLine();
			switch(option){
				case "1"://��ѯ����
					System.out.println("���������н�ʦ����Ϣ��");
					teacher[] stus = sms.queryAll();
					for(teacher stu : stus){
						System.out.println(stu);
					}
					System.out.println("�ܼƣ�"+stus.length+" ��");
					break;
				case "2"://���
					while(true){
						System.out.println("�������ʦ��Ϣ��id#name#age����������break�ص���һ��Ŀ¼");
						String stuStr = scanner.nextLine();
						if(stuStr.equals("break")){
							break;
						}
						String[] stuArr = stuStr.split("#");
						long id = Long.parseLong(stuArr[0]);
						String name = stuArr[1];
						int age = Integer.parseInt(stuArr[2]);
						//��װ����
						teacher stu = new teacher(id,name,age);
						//�жϸ��û�id�Ƿ��Ѿ�����ռ��
						teacher dbStu = sms.queryById(id);
						if(dbStu!=null){
							System.out.println("��id�Ѿ�����ռ�ã�������¼�룡");
							continue;
						}

						sms.add(stu);
						System.out.println("��ӳɹ���");
					}
					
					break;
				case "3"://ɾ��
					while(true){
						System.out.print("��������Ҫɾ����ʦ��id��break������һ��Ŀ¼:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						sms.deleteById(Long.parseLong(id));
						System.out.println("ɾ���ɹ�����ʦ����Ϊ��"+sms.index);
					}
					break;
				case "4"://��ѯ
					while(true){
						System.out.print("��������Ҫ��ѯ��ʦ��id��break������һ��Ŀ¼:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						teacher stu = sms.queryById(Long.parseLong(id));
						System.out.println("��������Ҫ���ҵĽ�ʦ����Ϣ��");
						System.out.println(stu!=null?stu:"not found!");
					}
					break;
				case "5"://�޸�
					while(true){
						System.out.print("��������Ҫ�޸Ľ�ʦ��id��break������һ��Ŀ¼:");
						String id = scanner.nextLine();
						if(id.equals("break")){
							break;
						}
						teacher stu = sms.queryById(Long.parseLong(id));
						if(stu == null){
							System.out.println("�ý�ʦ�����ڣ�");
							continue;
						}
						System.out.println("ԭ��ϢΪ��"+stu);
						System.out.println("��������Ҫ�޸ĵ���Ϣ��name#age��");
						String stuStr = scanner.nextLine();
						String[] stuArr = stuStr.split("#");

						String name = stuArr[0];
						int age = Integer.parseInt(stuArr[1]);

						teacher newStu = new teacher(Long.parseLong(id),name,age);

						sms.update(newStu);
						System.out.println("�޸ĳɹ���");
					}
					break;
				case "help":
					sms.menu();
					break;
				case "exit":
					System.out.println("bye bye");
					System.exit(0);
				default:
					System.out.println("����������������룡");
			}
		}
			
	}
}