package application;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MovieTicketManagerSTUDENT_Test {
	private MovieTicketManager ticketList;


	@Before
	public void setUp() throws Exception {
		ticketList = new MovieTicketManager();

		//Student add adult tickets
		ticketList.addTicket("Freaky", "PG13", 5,20,"NONE","Adult",0);
		ticketList.addTicket("Die Hard", "PG13", 3,13,"NONE","Adult",0);
		ticketList.addTicket("War Zone", "PG13", 4,15,"3D","Adult",0);

		//Student add children tickets
		ticketList.addTicket("Tom & Jerry", "G", 6,20,"NONE","Child",0);
		ticketList.addTicket("Tom & Jerry", "G", 3,13,"NONE","Child",0);
		ticketList.addTicket("The Croods", "PG", 3,15,"3D","Child",0);

		//Student add employee tickets
		ticketList.addTicket("Avengers", "NR", 6,20,"NONE","Employee",78342);
		ticketList.addTicket("Wonder Woman", "NR", 3,13,"NONE","Employee",23456);
		ticketList.addTicket("All My Life", "PG13", 2,14,"IMAX","Employee",37242);

		//Student add MoviePass member tickets
		ticketList.addTicket("Avengers", "NR", 6,20,"NONE","MoviePass",35362);
		ticketList.addTicket("Avengers", "G", 2,14,"IMAX","MoviePass",78374);
		ticketList.addTicket("The Polar", "PG13", 3,13,"NONE","MoviePass",26732);

	}

	@After
	public void tearDown() throws Exception {
		//Student set ticketList to null;
		ticketList = null;
	}

	/**
	 * Student Test the number of visits to the theater within the month
	 * This only applied to those who have id members - Employees or MoviePass members
	 */
	@Test
	public void testNumVisits() {
		//fail("testNumVisits not implemented yet");
		//Student test Employees' number of visits
		assertEquals(1,ticketList.numVisits(37242));
		ticketList.addTicket("The Polar", "PG13", 7,19,"NONE","Employee",37242);
		assertEquals(2,ticketList.numVisits(37242));
		ticketList.addTicket("The Polar", "PG13", 9,19,"NONE","Employee",37242);
		assertEquals(3,ticketList.numVisits(37242));

		//Student test MoviePass members' number of visits
		assertEquals(1,ticketList.numVisits(78374));
		ticketList.addTicket("Avengers", "NR", 4,12,"NONE","MoviePass",78374);
		assertEquals(2,ticketList.numVisits(78374));
		ticketList.addTicket("Wonder Woman", "NR", 6,12,"NONE","MoviePass",78374);
		assertEquals(3,ticketList.numVisits(78374));

	}

	/**
	 * Student Test the number of times this movie has been viewed
	 * This only applied to those who have id numbers - Employees or MoviePass members
	 */
	@Test
	public void testNumThisMovie() {
		//fail("testNumThisMovie not implemented yet");
		//Student test Employees' number of views
		assertEquals(1,ticketList.numThisMovie(37242,"All My Life"));
		ticketList.addTicket("The Polar", "PG13", 7,19,"NONE","Employee",37242);
		assertEquals(1,ticketList.numThisMovie(37242,"The Polar"));
		ticketList.addTicket("The Polar", "PG13", 9,19,"NONE","Employee",37242);
		assertEquals(2,ticketList.numThisMovie(37242,"The Polar"));

		//Student test MoviePass members' number of views
		assertEquals(1,ticketList.numThisMovie(78374,"Avengers"));
		ticketList.addTicket("The Polar", "NR", 4,12,"NONE","MoviePass",78374);
		assertEquals(1,ticketList.numThisMovie(78374,"Avengers"));
		ticketList.addTicket("Avengers", "NR", 6,12,"NONE","MoviePass",78374);
		assertEquals(2,ticketList.numThisMovie(78374,"Avengers"));

	}

}