package application;

import java.util.ArrayList;

public class MoviePass extends Ticket {

	MovieTicketManager m = new MovieTicketManager();

	// Variable
	ArrayList<Ticket> tickets;

	// No-arg constructor
	public MoviePass() {}

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
	public MoviePass(String movieName, String movieRating, int day, 
			int time, String movieFormat, String type, int id) {

		super(movieName, movieRating, day, time, movieFormat, type, id);
	}

	/**
	 * Calculate ticket price for a person having a moviepass
	 * @return ticketCost: price of the ticket for a moviepass
	 */
	@Override
	double calculateTicketPrice() {
		// Get the format of the movie
		String movieFeature = getMovieFormat();
		// Determine the ticket price
		ticketCost = (getTime() > 18)? ADULTPRICEAFTER6PM : ADULTPRICEBEFORE6PM;
		switch(movieFeature) {
		case "IMAX": ticketCost += ADULTPRICEIMAX; break;
		case "3D": ticketCost += ADULTPRICE3D; break;
		}		
		ticketCost += (ticketCost * TAX);

		return ticketCost;
	}

	/**
	 * Return id
	 * @return id: the id of the movie
	 */
	@Override
	int getId() {
		// TODO Auto-generated method stub
		return id;
	}
}
