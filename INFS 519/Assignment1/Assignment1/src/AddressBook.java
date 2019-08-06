import java.util.Scanner;

/**
 * 
 * @author rutujajadhav
 * Rutuja Jadhav
 * G01152918
 * Spring 2019
 *
 */
public class AddressBook {

	private static Scanner s;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Table addressBook = new Table();
		s= new Scanner(System.in);
		while (true) {
			System.out.println("Add	a name (n)");
			System.out.println("Look up a name(l)");
			System.out.println("Update address (u)");
			System.out.println("Delete an entry (d)");
			System.out.println("Display all enteries (a)");
			System.out.println("Quit (q) \n ->");
			//System.out.println("-> ");

			String input = s.nextLine();
			String key = "";
			String value = "";
			String msg = "";

			switch (input) {
			case "n":
				System.out.print("Name: ");
				key = s.nextLine();
				if (null == addressBook.lookUp(key)) {
					System.out.print("Address: ");
					value = s.nextLine();
					addressBook.insert(key, value);
				} else {
					System.out.println( key + " is already present in the book");
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
				System.exit(0);
				break;
			default:
				System.out.println("Not a valid input. Please enter a valid value..");
				System.out.println();
				break;
			}
		}
	}
}
