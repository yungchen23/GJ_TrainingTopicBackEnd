package com.restbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restbackend.model.Booking;
import com.restbackend.model.BookingRepository;
import com.restbackend.model.Member;
import com.restbackend.model.MemberRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api3")
public class BookingForBackendController implements CommandLineRunner {

//	@Autowired
//	private Booking dao;

	@Autowired
	private MemberRepository dao;

	@Autowired
	private BookingRepository dao2;

	@GetMapping("/bookings")
	public List<Booking> getAllBooking() {
		return dao2.findAll();
	}

	@PutMapping("/bookings/{id}")
	public ResponseEntity<String> updateBookingStatus(@PathVariable String id, @RequestBody Booking updatedBooking) {
		Booking booking = dao2.findById(id).orElse(null);

		if (booking != null) {
			booking.setBooking_states(updatedBooking.getBooking_states());
			dao2.save(booking);
			return ResponseEntity.ok("Booking status updated successfully.");
		} else {
			return ResponseEntity.status(404).body("Booking not found.");
		}
	}

	@Override
	public void run(String... args) throws Exception {

		Member m1 = dao.findById(1).orElseThrow();
		Member m2 = dao.findById(2).orElseThrow();

		dao2.save(new Booking("20200101ABCD", "2020-01-01", "13:13", 2, 1, "good1", "王大明", "小姐", "0000", "1", "1", m1));
		dao2.save(
				new Booking("20200103XDFG", "2020-01-03", "14:13", 1, 3, "good1", "王大明1", "先生", "0000", "2", "2", m1));
		dao2.save(
				new Booking("20240828WERT", "2024-08-28", "14:13", 1, 3, "good1", "王大明1", "先生", "0000", "3", "3", m2));
		dao2.save(
				new Booking("20240829ERTY", "2024-08-29", "14:13", 5, 2, "good1", "王大明1", "先生", "0000", "1", "1", m1));
		dao2.save(
				new Booking("20240830POWQ", "2024-08-30", "15:13", 1, 3, "good1", "王大明1", "先生", "0000", "2", "2", m2));
		dao2.save(
				new Booking("20240831WRTQ", "2024-08-31", "14:13", 8, 3, "good1", "王大明1", "先生", "0000", "3", "3", m2));
		dao2.save(
				new Booking("20240905EAUQ", "2024-09-05", "15:13", 1, 0, "good1", "王大明1", "先生", "0000", "1", "1", m1));
		dao2.save(
				new Booking("20240905X5UQ", "2024-09-05", "15:13", 2, 3, "good1", "王大明1", "先生", "0000", "1", "1", m2));
		dao2.save(
				new Booking("20240905TTYU", "2024-09-10", "12:00", 1, 1, "good1", "李小美1", "先生", "0000", "1", "1", m2));
		dao2.save(
				new Booking("20240908YSZG", "2024-09-12", "13:30", 1, 2, "good1", "王大明1", "先生", "0000", "1", "1", m1));
		dao2.save(
				new Booking("20240910YSZG", "2024-09-15", "13:30", 2, 3, "good1", "李小美1", "小姐", "0000", "1", "1", m2));
		dao2.save(
				new Booking("20240910GSZG", "2024-09-17", "12:30", 1, 1, "good1", "王大明1", "先生", "0000", "1", "1", m1));
		dao2.save(
				new Booking("20240913HHZG", "2024-09-21", "11:30", 3, 4, "good1", "李小美1", "小姐", "0000", "1", "1", m2));

//		dao2.save(new Booking("20200101ABCD",22,"2020-01-01","13:13",2,1,"good","王大明","小姐","0000","1","1"));
//		dao2.save(new Booking("20200103XDFG",32,"2020-01-03","14:13",1,3,"good1","王大明1","先生","0000","2","2"));
//		dao2.save(new Booking("20240828WERT",32,"2024-08-28","14:13",1,3,"good1","王大明1","先生","0000","3","3"));
//		dao2.save(new Booking("20240829ERTY",32,"2024-08-29","14:13",1,3,"good1","王大明1","先生","0000","1","1"));
//		dao2.save(new Booking("20240830POWQ",22,"2024-08-30","15:13",1,3,"good1","王大明1","先生","0000","2","2"));
//		dao2.save(new Booking("20240831WRTQ",32,"2024-08-31","14:13",1,3,"good1","王大明1","先生","0000","3","3"));
//		dao2.save(new Booking("20240905TYUQ",22,"2024-09-05","15:13",1,3,"good1","王大明1","先生","0000","1","1"));

	}

}
