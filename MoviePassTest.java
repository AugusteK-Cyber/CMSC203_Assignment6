package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoviePassTest {
	MoviePass m1,m2,m3, m4, m5,m6;

	@BeforeEach
	public void setUp() throws Exception {
		//Evening movie (after 6 pm)
		m1 = new MoviePass("Little Mermaid", "G", 5,19,"NONE", "MoviePass", 1111);
		//Matinee (before 6 pm)
		m2 = new MoviePass("Little Mermaid", "G", 2,12,"NONE", "MoviePass", 1232);
		//Evening IMAX movie
		m3 = new MoviePass("Wanted", "PG13", 3,20,"IMAX", "MoviePass", 3332);
		//Matinee IMAX
		m4 = new MoviePass("Deadzone", "R", 1,13,"3D", "MoviePass", 1232);
		//Evening 3D
		m5 = new MoviePass("Jigsaw", "NR", 3,21,"NONE", "MoviePass", 1111);
		//Matinee 3D
		m6 = new MoviePass("Great", "PG", 2,14,"3D", "MoviePass", 1232);
	}

	@AfterEach
	public void tearDown() throws Exception {
		m1=m2=m3=m4=m5=m6=null;
	}

	@Test
	public void test() {
		assertEquals(0.0,m1.ticketCost,.01);
		assertEquals(0.0,m2.ticketCost,.01);
		assertEquals(18.08,m3.calculateTicketPrice(),.01);
		assertEquals(14.24,m4.calculateTicketPrice(),.01);
		assertEquals(14.80,m5.calculateTicketPrice(),.01);
		assertEquals(14.24,m6.calculateTicketPrice(),.01);
	}


}
