package file_handling;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.io.*;
class company implements Serializable
{
	int p_id;
	String p_name;
	int p_price;
	
	company(int p_id, String p_name ,int p_price)
	{
		this.p_id=p_id;
		this.p_name=p_name;
		this.p_price=p_price;
	}
	public String tostring() {
		return p_id+" "+p_name+" "+p_price;
	}
}



public class File_Handling_Project 
{

	public static void main(String[] args) throws Exception
	{
		int choice =-1;
		int option =-1;
		Scanner s=new Scanner(System.in);
		Scanner s1=new Scanner(System.in);
		File file =new File("company.txt");
		ArrayList<company> al=new ArrayList<company>();
		ObjectOutputStream oos= null;
		ObjectInputStream ois= null;
		ListIterator li=null;
		
		if(file.isFile()) {
			ois =new ObjectInputStream(new FileInputStream(file));
			al = (ArrayList<company>) ois.readObject();
			ois.close();
		}
		///Wellcome Screen And Developer Name
		System.out.println("WELLCOME");
		System.out.println("-------------------------");
		System.out.println("Developer Name Is");
		System.out.println("Pushpak Patil");
		System.out.println("-------------------------");
		
		//First three Options
		do{
			System.out.println("1.Retrive File");
			System.out.println("----------------------");
			System.out.println("Business Operations");
			System.out.println("2.Add file");
			System.out.println("3.Delete File");
			System.out.println("4.Search file");
			System.out.println("----------------------");
			System.out.println("0.Close App");
			
			System.out.println("Enter Your Choice");
			choice = s.nextInt();
			
			switch(choice)
			{
			
//case no 1 to retrive
			case 1:
				if(file.isFile()) 
				{
					ois =new ObjectInputStream(new FileInputStream(file));
					al = (ArrayList<company>) ois.readObject();
					ois.close();
					System.out.println("--------------------------");
					li=al.listIterator();
					while(li.hasNext())
						System.out.println(li.next());
					System.out.println("--------------------------");
				}
				else {
					System.out.println("File not Exist.....");
				}
				break;
				
				
//case no 2 to add 
			case 2:
				System.out.println("Enter How many products you want:");
				int n=s.nextInt();
				for(int i=0;i<n;i++)
				{
					System.out.print("Enter Product ID :");
					int p_id = s.nextInt();
					
					System.out.print("Enter Product Name :");
					String p_name = s1.nextLine();
					
					System.out.print("Enter Product Price :");
					int p_price = s.nextInt();
					
					al.add(new company(p_id,p_name,p_price));
					
				}
					oos=new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(al);
					oos.close();
				break;
				
				
//case no 3 to delete
			case 3:
				if(file.isFile()) {
					ois =new ObjectInputStream(new FileInputStream(file));
					al = (ArrayList<company>) ois.readObject();
					ois.close();
					
					Boolean found = false;
					System.out.print("Enter Product ID To Delete :");
					int p_id = s.nextInt();
					System.out.println("--------------------------");
					li=al.listIterator();
					while(li.hasNext()) {
						company c = (company) li.next();
						if(c.p_id == p_id) {
							li.remove();
							found = true;
						}
					}
					if(found) {
						oos=new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(al);
						oos.close();
						System.out.println("Record Deleted Successfully");
					}
					else {
						System.out.println("Recod Not Found..........");
					}
						System.out.println("--------------------------");
				}else {
					System.out.println("File not Exist.....");
				}
				break;
				
				
//case no 4 to search		
			case 4:
				if(file.isFile()) {
					ois =new ObjectInputStream(new FileInputStream(file));
					al = (ArrayList<company>) ois.readObject();
					ois.close();
					
					Boolean found = false;
					System.out.println("Enter Product ID To Search :");
					int p_id = s.nextInt();
					System.out.println("--------------------------");
					li=al.listIterator();
					while(li.hasNext()) {
						company c = (company) li.next();
						if(c.p_id == p_id) {
							System.out.println(c);
							found = true;
						}
					}
					if(!found)
						System.out.println("Recod Not Found..........");
						System.out.println("--------------------------");
				}else {
					System.out.println("File not Exist.....");
				}
				break;
			}
		}while (choice!=0);
	}

}
