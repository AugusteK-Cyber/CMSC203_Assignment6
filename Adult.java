package application;

public class Adult extends Ticket {

	// Default non-arg constructor
	Adult() {}

	/**
	 * Overloaded constructor
	 * @param movieName: movie title
	 * @param movieRating: movie rating
	 * @param day: day attending the theater
	 * @param time: hour attending the theater
	 * @param movieFormat: movie format (3D, IMAX, or NONE)
	 */
	Adult(String movieName, String movieRating, 
			int day, int time, String movieFormat){

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
	Adult(String movieName, String movieRating, int day, 
			int time, String movieFormat,String type, int id){

		super(movieName, movieRating, day, time, movieFormat, type, id);
	}
	
	/**
	 * Calculate the ticket price for an adult
	 * @return ticketPrice: the price of the ticket
	 */

	@Override
	double calculateTicketPrice() {

		if (time < 18) {
			ticketCost = ADULTPRICEBEFORE6PM;
			if (movieFormat.equals("IMAX")) {
				ticketCost += ADULTPRICEIMAX;
			}
			if (movieFormat.equals("3D")) {
				ticketCost += ADULTPRICE3D;
			}
			if (movieFormat.equals("NONE")) {
				ticketCost += 0;
			}
		}
		else {
			ticketCost = ADULTPRICEAFTER6PM;
			if (movieFormat.equals("IMAX")) {
				ticketCost += ADULTPRICEIMAX;
			}
			if (movieFormat.equals("3D")) {
				ticketCost += ADULTPRICE3D;
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
