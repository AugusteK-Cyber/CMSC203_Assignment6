package application;

public abstract class Ticket {

	// Constants
	protected final double CHILDPRICEBEFORE6PM = 5.75;
	protected final double CHILDPRICEAFTER6PM = 10.75;
	protected final double CHILDPRICEIMAX = 2;
	protected final double CHILDPRICE3D = 1.50;
	protected final double MOVIEPASSPRICE = 9.99;
	protected final double ADULTPRICEBEFORE6PM = 10.50;
	protected final double ADULTPRICEAFTER6PM = 13.50;
	protected final double ADULTPRICEIMAX = 3;
	protected final double ADULTPRICE3D = 2.50;

	protected final double TAX = 0.096;

	// Variables
	protected Format movieForm;
	protected String movieFormat;
	protected String movieName, movieRating, ticketType;
	protected int day, time, id;
	protected double ticketCost;

	/**
	 * No-arg constructor
	 */
	Ticket(){

		this.movieName = "";
		this.movieRating = "";
		this.day = 0;
		this.time = 0;
		this.movieFormat = "";
		this.ticketType = "";
		this.id = 0;
		this.ticketCost = 0;
	}

	/**
	 * Overloaded constructor
	 * @param movieName: movie title
	 * @param movieRating: movie rating
	 * @param day: day attending the theater
	 * @param time: hour attending the theater
	 * @param movieFormat: movie format (3D, IMAX, or NONE)
	 */
	Ticket(String movieName, String movieRating, 
			int day, int time, String movieFormat){

		this.movieName = movieName;
		this.movieRating = movieRating;
		this.day = day;
		this.time = time;
		this.movieFormat = movieFormat;
		this.ticketType = "";
		this.id = 0;
		this.ticketCost = 0;
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
	Ticket(String movieName, String movieRating, int day, 
			int time, String movieFormat, String ticketType, int id){

		this.movieName = movieName;
		this.movieRating = movieRating;
		this.day = day;
		this.time = time;
		this.movieFormat = movieFormat;
		this.ticketType = ticketType;
		this.id = id;
	}

	/**
	 * Abstract method to calculate a ticket price
	 */
	abstract double calculateTicketPrice();

	/**
	 * Abstract method to get an employee ID
	 */
	abstract int getId();

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		 
		String movieTypeId = ticketType.toUpperCase();
		if (id != -1) {
			movieTypeId += "-" + id;
		}

		String display = "";
		if (movieFormat.equals("3D")) {
			display = "3D";
		}
		else if (movieFormat.equals("IMAX")){
			display = "IMAX";
		}
		return movieTypeId + " " + display + " Movie: " + movieName 
				+ " Rating: " + movieRating + " Day: " + day
				+ " Time: " + time + " Price: $" + ticketCost;
	}
	
	/*
	 * Setters and getters
	 */

	public Format getMovieForm() {
		return movieForm;
	}

	public void setMovieForm(Format movieForm) {
		this.movieForm = movieForm;
	}

	public String getMovieFormat() {
		return movieFormat;
	}

	public void setMovieFormat(String movieFormat) {
		this.movieFormat = movieFormat;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(String movieRating) {
		this.movieRating = movieRating;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getTicketCost() {
		return ticketCost;
	}

	public void setTicketCost(double ticketCost) {
		this.ticketCost = ticketCost;
	}

	public double getMOVIEPASSPRICE() {
		return MOVIEPASSPRICE;
	}

	public void setId(int id) {
		this.id = id;
	}

}
