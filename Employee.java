package application;

public class Employee extends Ticket{

	// Variable
	protected int count;

	// Non-arg constructor
	public Employee() {}

	/**
	 * Overloaded constructor
	 * @param movieName: movie title
	 * @param movieRating: movie rating
	 * @param day: day attending the theater
	 * @param time: hour attending the theater
	 * @param movieFormat: movie format (3D, IMAX, or NONE)
	 * @param type: type of the ticket (child, adult, employee, moviepass)
	 * @param id: id of movie
	 */
	public Employee(String movieName, String movieRating, int day, 
			int time, String movieFormat, String type, int id) {

		super(movieName, movieRating, day, time, movieFormat, type, id);

	}

	/**
	 * Overloaded constructor
	 * @param movieName: movie title
	 * @param movieRating: movie rating
	 * @param day: day attending the theater
	 * @param time: hour attending the theater
	 * @param movieFormat: movie format (3D, IMAX, or NONE)
	 * @param id: id of movie
	 * @param moviesSeen: movies already seen
	 */
	public Employee(String movieName, String movieRating, 
			int day, int time, String movieFormat, int id, int moviesSeen) {

		super(movieName, movieRating, day, time, movieFormat);
		count = moviesSeen;
		this.id = id;
	}
	
	/**
	 * Calculate ticket price for an employee, half the price of an adult price + TAX
	 * @return ticketCost: price of the ticket for an employee
	 */
	@Override
	double calculateTicketPrice() {

		double ticketCost = 0;

		if (count < 2) {
			ticketCost = 0;
		}
		else {
			if (time < 18) {
				ticketCost = ADULTPRICEBEFORE6PM;
				if (movieFormat.equalsIgnoreCase("IMAX")) {
					ticketCost += ADULTPRICEIMAX;
				}
				if (movieFormat.equalsIgnoreCase("3D")) {
					ticketCost += ADULTPRICE3D;
				}
				if (movieFormat.equalsIgnoreCase("NONE")) {
					ticketCost += 0;
				}
			}
			else {
				ticketCost = ADULTPRICEAFTER6PM;
				if (movieFormat.equalsIgnoreCase("IMAX")) {
					ticketCost += ADULTPRICEIMAX;
				}
				if (movieFormat.equalsIgnoreCase("3D")) {
					ticketCost += ADULTPRICE3D;
				}
				if (movieFormat.equalsIgnoreCase("NONE")) {
					ticketCost += 0;
				}

			}
			// Total ticket price
			ticketCost /= 2;
			ticketCost += (ticketCost * TAX);
		}
		return ticketCost;
	}

	/**
	 * Return id
	 * @return id: id of the movie
	 */
	@Override
	int getId() {
		// TODO Auto-generated method stub
		return id;
	}

}
