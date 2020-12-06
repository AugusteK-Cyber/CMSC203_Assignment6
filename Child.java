package application;

public class Child extends Ticket {

	// Default non-arg constructor
	Child() {}

	/**
	 * Overloaded constructor
	 * @param movieName: movie title
	 * @param movieRating: movie rating
	 * @param day: day attending the theater
	 * @param time: hour attending the theater
	 * @param movieFormat: movie format (3D, IMAX, or NONE)
	 */
	Child(String movieName, String movieRating, int day, 
			int time, String movieFormat){

		super(movieName, movieRating, day, time, movieFormat);

	}

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
	Child(String movieName, String movieRating, int day, 
			int time, String movieFormat, String ticketType, int id){

		super(movieName, movieRating, day, time, movieFormat, ticketType, id);

	}
	
	/**
	 * Calculate ticket price for a child
	 * @return ticketCost: price of the child ticket
	 */
	@Override
	double calculateTicketPrice() {

		if (time < 18) {
			ticketCost = CHILDPRICEBEFORE6PM;
			if (movieFormat.equals("IMAX")) {
				ticketCost += CHILDPRICEIMAX;
			}
			if (movieFormat.equals("3D")) {
				ticketCost += CHILDPRICE3D;
			}
			if (movieFormat.equals("NONE")) {
				ticketCost += 0;
			}
		}
		else {
			ticketCost = CHILDPRICEAFTER6PM;
			if (movieFormat.equals("IMAX")) {
				ticketCost += CHILDPRICEIMAX;
			}
			if (movieFormat.equals("3D")) {
				ticketCost += CHILDPRICE3D;
			}
			if (movieFormat.equals("NONE")) {
				ticketCost += 0;
			}

		}
		// Total ticket price
		ticketCost += (ticketCost * TAX);

		return ticketCost;
	}

	/**
	 * Return id
	 * @return -1
	 */
	@Override
	int getId() {
		// TODO Auto-generated method stub
		return -1;
	}

}
