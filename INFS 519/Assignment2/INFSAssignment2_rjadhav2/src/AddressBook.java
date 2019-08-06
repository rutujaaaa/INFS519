/**
 * 
 * @author rutujajadhav
 * Rutuja Jadhav
 * G01152918
 * Spring 2019
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AddressBook {
private static Scanner s;
public static void main(String[] args) throws IOException {
	Table addressBook = new Table();
	s = new Scanner(System.in);
	System.out.println("Do you want to open	a file? (yes/no).");
	String input1 = s.nextLine();
	switch(input1) {
	case "yes": String fileName = "/Users/rutujajadhav/Downloads/INFSAssignment 2/INFSAssignment3/contacts.txt";  // name of the file
	Scanner fromFile = null;
	try{
		fromFile = new Scanner(new File(fileName));    // read from file output.txt
		while (fromFile.hasNext())
		{
			String sptlitSptring = fromFile.nextLine();
			if(sptlitSptring.trim().equals(" ")) {
				continue;
			}
			String[] s = new String[2];
			if(sptlitSptring.contains(",")) {
				s = sptlitSptring.split(",");
			}else {
				s = new String[2];
				s[0] = sptlitSptring;
				s[1] = "";
			}
			String k = s[0];
			String v = s[1];
			addressBook.insert(k, v);

		}
	}
	catch(FileNotFoundException e)
	{
		System.out.println("Error : " +e);
		System.out.println("Unable to open file " + fileName);
	}
	fromFile.close();
	while (true) {
		System.out.println("Add	a name (n)");
		System.out.println("Look up a name(l)");
		System.out.println("Update address (u)");
		System.out.println("Delete an entry (d)");
		System.out.println("Display all enteries (a)");
		System.out.println("Save and exit (q) \n ->");
		//System.out.println("-> ");

		String input = s.nextLine();
		String key = "";
		String value = "";
		String msg = "";

		switch (input) {
		case "n":
			System.out.print("Name: ");
			key = s.nextLine();
			String isPresent = addressBook.lookUp(key);
			if ("" == isPresent) {
				System.out.print("Address: ");
				value = s.nextLine();
				addressBook.insert(key, value);
			} else {
				System.out.println(key + " is already present in the book");
			}

			System.out.println();
			break;
		case "l":
			System.out.print("Name: ");
			key = s.nextLine();
			if (addressBook.lookUp(key) == null) {
				System.out.print(key + " is not in the book \n");
			} else {
				System.out.print("Address is " + addressBook.lookUp(key) + "\n");
			}
			System.out.println();
			break;
		case "u":
			System.out.print("Name: ");
			key = s.nextLine();
			String oldAddress = addressBook.lookUp(key);
			if (oldAddress == null) {
				System.out.println(key + " is not in the book");
			} else {
				System.out.print("Old address is " + oldAddress + "\n");
				System.out.print("New address: ");
				value = s.nextLine();
				addressBook.update(key, value);
			}
			System.out.println();
			break;
		case "d":
			System.out.print("Name to delete: ");
			key = s.nextLine();
			if (!addressBook.delete(key)) {
				System.out.println(key + " is not in the book");
			}
			System.out.println();
			break;

		case "a":
			addressBook.displayAll();
			break;
		case "q":
			addressBook.save();
			System.exit(0);
			break;
		default:
			System.out.println("Not a valid input. Please enter a valid value..");
			System.out.println();
			break;
		}
	}
		//break;
	case "no": while (true) {
		System.out.println("Add	a name (n)");
		System.out.println("Look up a name(l)");
		System.out.println("Update address (u)");
		System.out.println("Delete an entry (d)");
		System.out.println("Display all enteries (a)");
		System.out.println("Save and exit (q) \n ->");
		//System.out.println("-> ");

		String input = s.nextLine();
		String key = "";
		String value = "";
		String msg = "";

		switch (input) {
		case "n":
			System.out.print("Name: ");
			key = s.nextLine();
			String isPresent = addressBook.lookUp(key);
			if ("" == isPresent) {
				System.out.print("Address: ");
				value = s.nextLine();
				addressBook.insert(key, value);
			} else {
				System.out.println(key + " is already present in the book");
			}

			System.out.println();
			break;
		case "l":
			System.out.print("Name: ");
			key = s.nextLine();
			if (addressBook.lookUp(key) == null) {
				System.out.print(key + " is not in the book \n");
			} else {
				System.out.print("Address is " + addressBook.lookUp(key) + "\n");
			}
			System.out.println();
			break;
		case "u":
			System.out.print("Name: ");
			key = s.nextLine();
			String oldAddress = addressBook.lookUp(key);
			if (oldAddress == null) {
				System.out.println(key + " is not in the book");
			} else {
				System.out.print("Old address is " + oldAddress + "\n");
				System.out.print("New address: ");
				value = s.nextLine();
				addressBook.update(key, value);
			}
			System.out.println();
			break;
		case "d":
			System.out.print("Name to delete: ");
			key = s.nextLine();
			if (!addressBook.delete(key)) {
				System.out.println(key + " is not in the book");
			}
			System.out.println();
			break;

		case "a":
			addressBook.displayAll();
			//System.out.println();
			break;
		case "q":
			addressBook.save();
			System.exit(0);
			break;
		default:
			System.out.println("Not a valid input. Please enter a valid value..");
			System.out.println();
			break;
		}
	}
		//break;
	default:
		System.out.println("Not a valid input. Please enter a valid value..");
		System.out.println();
		break;	
	
	}
}
}
