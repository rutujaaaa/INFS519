import java.util.Scanner;

/**
 * @author bhartiSharma
 *
 */
public class AddressBook {

	private static Scanner scn;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Table addressBook = new Table();
		scn = new Scanner(System.in);
		while (true) {
			System.out.println("Add a contact (n)");
			System.out.println("Look up a contact (l)");
			System.out.println("Update address (u)");
			System.out.println("Delete a contact (d)");
			System.out.println("Display all contacts (a)");
			System.out.println("Quit (q)");

			String input = scn.nextLine();
			String key = "";
			String value = "";
			String msg = "";

			switch (input) {
			case "n":
				System.out.print("Name: ");
				key = scn.nextLine();
				if (null == addressBook.lookUp(key)) {
					System.out.print("Address: ");
					value = scn.nextLine();
					addressBook.insert(key, value);
				} else {
					System.out.println( key + " is already present in the book");
				}
				
				System.out.println();
				break;
			case "l":
				System.out.print("Name: ");
				key = scn.nextLine();
				if (addressBook.lookUp(key) == null) {
					System.out.print(key + " is not in the book \n");
				} else {
					System.out.print("Address is " + addressBook.lookUp(key) + "\n");
				}
				System.out.println();
				break;
			case "u":
				System.out.print("Name: ");
				key = scn.nextLine();
				String oldAddress = addressBook.lookUp(key);
				if (oldAddress == null) {
					System.out.println(key + " is not in the book");
				} else {
					System.out.print("Old address is " + oldAddress + "\n");
					System.out.print("New address: ");
					value = scn.nextLine();
					addressBook.update(key, value);
				}
				System.out.println();
				break;
			case "d":
				System.out.print("Name to delete: ");
				key = scn.nextLine();
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
				System.out.println("Not a valid input.");
				System.out.println();
				break;
			}
		}
	}
}
