package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.*;

public class MovieTicketManager implements MovieTicketManagerInterface{

	// Variables and instances
	private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
	private ArrayList<Ticket> ticketList = new ArrayList<>();
	final double MOVIEPASSPRICE = 9.99;
	final double FREE = 0.0;
	final double TAX = 0.096;

	// No-arg constructor
	MovieTicketManager(){}

	/**
	 * Overloaded constructor
	 * @param movieName: movie title
	 * @param movieRating: movie rating
	 * @param day: day attending the theater
	 * @param time: hour attending the theater
	 * @param movieFormat: movie format (3D, IMAX, or NONE)
	 * @param ticketType: type of the ticket (child, adult, employee, moviepass)
	 * @param id: id of movie
	 */
	MovieTicketManager(String movieName, String movieRating, 
			int day, int time, String movieFeature, String movieType, int id){

		ticketList = new ArrayList<>();
		movieName = "";
		movieRating = "";
		day = 0;
		time = 0;
		movieFeature = "";
		movieType = "";
		id = 0;
	}

	/**
	 *  Returns the number of times this patron has visited the theater this month
	 *  @param id: movie id
	 *  @return count: number of times the patron has visited the theater
	 */
	@Override
	public int numVisits(int id) {
		int count = 0;

		for(int i = 0; i < ticketList.size(); i++) {
			if (ticketList.get(i).id == id) {
				count++;
			}
		}
		return count;
	}

	/**
	 *  Returns the number of times the patron has seen this movie
	 *  @param id: movie id
	 *  @param movie: movie name
	 *  @return count: number of times the movie was seen
	 */
	@Override
	public int numThisMovie(int id, String movie) {
		int count = 0;

		for (int i = 0; i < ticketList.size(); i++) {
			if ((ticketList.get(i).id == id) 
					&& (ticketList.get(i).movieName.equals(movie))){
				count++;
			}
		}
		return count;
	}

	/**
	 * Checks to see if this patron already saw a movie today
	 * @param id: the movie id
	 * @param date: that day
	 * @return numMovieWatched: number of times the movie was watched
	 */
	@Override
	public int numMoviesToday(int id, int date) {
		int count = 0;

		for (int i = 0; i < ticketList.size(); i++) {
			if ((ticketList.get(i).id == id) 
					&& (ticketList.get(i).day == date)) {
				count++;				
			}
		}
		return count;
	}

	/**
	 * Adds a patron to the list and returns the ticket price
	 * @return ticketPrice the price of the ticket
	 */
	@Override
	public double addTicket(String movieN, String rating, 
			int d, int t, String f, String type, int id) {

		double ticketPrice = 0;

		switch(type) {
		case "Child":
			Child ticketChild = new Child(movieN, rating, d, t, f, type, id);
			ticketPrice = ticketChild.calculateTicketPrice();
			ticketList.add(ticketChild);
			return ticketPrice;

		case "Adult":
			Adult ticketAdult = new Adult(movieN, rating, d, t, f, type, id);
			ticketPrice = ticketAdult.calculateTicketPrice();
			ticketList.add(ticketAdult);
			return ticketPrice;

		case "Employee":
			Employee ticketEmployee = new Employee(movieN, rating, d, t, f, type, id);
			ticketPrice = ticketEmployee.calculateTicketPrice();
			ticketList.add(ticketEmployee);
			return ticketPrice;

		case "MoviePass":
			MoviePass ticketMoviePass = new MoviePass(movieN, rating, d, t, f, type, id);
			if (numMoviesToday(id, d) == 0 && numThisMovie(id, movieN) == 0
					&& ticketMoviePass.getMovieFormat().equals("NONE")) {
				if (numVisits(id) == 0) {
					ticketMoviePass.setTicketCost(MOVIEPASSPRICE);
					ticketList.add(ticketMoviePass);
					return MOVIEPASSPRICE;
				}
				ticketMoviePass.setTicketCost(FREE);
				ticketList.add(ticketMoviePass);
				return FREE;
			}
			else {
				ticketMoviePass.setTicketCost(ticketMoviePass.calculateTicketPrice());
				ticketList.add(ticketMoviePass);
				return ticketMoviePass.calculateTicketPrice();
			}
		}
		return -1;
	}

	/**
	 * Returns the sales for the entire month
	 * @return sum: sales of the month
	 */
	@Override
	public double totalSalesMonth() {
		double sum = 0;

		for(int i = 0; i < ticketList.size(); i++) {
			sum += ticketList.get(i).ticketCost;
		}
		return sum;
	}

	/**
	 * Monthly Sales Report
	 * @return a toString report of the month's sales with NumberFormat formatted display
	 */
	@Override
	public String monthlySalesReport() {
		double salesTotal = salesThisMonth("Adult") 
				+ salesThisMonth("Child") 
				+ salesThisMonth("Employee") 
				+ salesThisMonth("MoviePass");
		
		return "\t\t\tMonthly Sales Report\n\n" 
				+ "\t\t\tSales\t\tNumber\n" 
				+ "ADULT\t\t" + currencyFormat.format(salesThisMonth("Adult")) + "\t\t\t" + ticketsSoldThisMonth("Adult") + "\n" 
				+ "CHILD\t\t" + currencyFormat.format(salesThisMonth("Child")) + "\t\t\t" + ticketsSoldThisMonth("Child") + "\n" 
				+ "EMPLOYEE\t" + currencyFormat.format(salesThisMonth("Employee")) + "\t\t\t" + ticketsSoldThisMonth("Employee") + "\n" 
				+ "MOVIEPASS\t" + currencyFormat.format(salesThisMonth("MoviePass")) + "\t\t\t" + ticketsSoldThisMonth("MoviePass") + "\n\n" 
				+ "Total Monthly Sales: " + currencyFormat.format(salesTotal);
	}

	/**
	 * Returns an ArrayList of strings that represent 3D tickets sorted by day
	 * @return list3DTickets sorted by day
	 */
	@Override
	public ArrayList<String> get3DTickets() {

		String text = "";
		ArrayList<String> list3DTickets = new ArrayList<>();
		sortByDay();
		for (Ticket t: ticketList) {
			if (t.movieFormat.equals("3D")) {
				text = t.toString();
				list3DTickets.add(text);
			}
		}
		return list3DTickets;
	}

	/**
	 * Returns an arrayList of strings which represent tickets, in chronological 
	 * order use the toString of each Ticket in the ticketList
	 * @return ticketStringList sorted chronologically
	 */
	@Override
	public ArrayList<String> getAllTickets() {
		ArrayList<String> ticketStringList = new ArrayList<>();

		for(int i = 0; i < ticketList.size(); i++) {
			ticketStringList.add(ticketList.toString());
		}
		return ticketStringList;
	}

	/**
	 * Returns an Arraylist of string representation of MoviePass tickets 
	 * sorted by movieId
	 * @return listMoviePassTickets sorted by movieId
	 */
	@Override
	public ArrayList<String> getMoviePassTickets() {

		String text = "";
		ArrayList<String> listMoviePassTickets = new ArrayList<>();
		sortById();
		for (Ticket t: ticketList) {
			if (t.ticketType.equals("MoviePass")) {
				text = t.toString();
				listMoviePassTickets.add(text);
			}
		}
		return listMoviePassTickets;
	}

	/**
	 * Reads from a file and populates an arraylist of Ticket objects
	 * @param file: a File parameter
	 */
	@Override
	public void readFile(File file) throws FileNotFoundException {

		Scanner input = new Scanner(file);
		while(input.hasNextLine()) {
			String[] ticket = input.nextLine().split(":");
			addTicket(ticket[0], ticket[1], Integer.parseInt(ticket[2]),
					Integer.parseInt(ticket[3]),  ticket[4],  ticket[5],
					Integer.parseInt(ticket[6]));
		}
		// Close Scanner
		input.close();
	}

	/**
	 *  Sorts the arraylist of Ticket object by day
	 */
	private void sortByDay() {
		Ticket temp = null;

		for (int i = 1; i < ticketList.size(); i++) {
			for(int j = i; j > 0; j--) {
				if (ticketList.get(j).day < ticketList.get(j-1).day) {
					temp = ticketList.get(j);
					ticketList.set(j, ticketList.get(j-1));
					ticketList.set(j-1, temp);
				}
			}
		}
	}

	/**
	 * Sorts the arraylist of Ticket object by id
	 */
	private void sortById() {
		Ticket temp = null;

		for (int i = 1; i < ticketList.size(); i++) {
			for(int j = i; j > 0; j--) {
				if (ticketList.get(j).id < ticketList.get(j-1).id) {
					temp = ticketList.get(j);
					ticketList.set(j, ticketList.get(j-1));
					ticketList.set(j-1, temp);
				}
			}
		}
	}

	/**
	 * Calculate the total number of tickets sold depending on the type of the ticket
	 * @param type: Child, Adult, Employee, or MoviePass
	 * @return count: the total number of tickets sold per type
	 */
	private int ticketsSoldThisMonth(String type) {
		int count = 0;

		for (int i = 0; i < ticketList.size(); i++) {
			if (ticketList.get(i).getTicketType().equals(type)) {
				count++;
			}
		}		
		return count;
	}

	/**
	 * Calculate the sales total of tickets sold this month
	 * @param type: Child, Adult, Employee, or MoviePass
	 * @return total: total sales
	 */
	private double salesThisMonth(String type) {
		double total = 0;

		for (int i = 0; i < ticketList.size(); i++) {
			if (ticketList.get(i).getTicketType().equals(type)) {
				total += ticketList.get(i).ticketCost;
			}
		}
		return total;
	}
}
