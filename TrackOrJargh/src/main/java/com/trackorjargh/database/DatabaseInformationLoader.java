package com.trackorjargh.database;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trackorjargh.javaclass.Book;
import com.trackorjargh.javaclass.CommentBook;
import com.trackorjargh.javaclass.CommentFilm;
import com.trackorjargh.javaclass.CommentShow;
import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javaclass.Gender;
import com.trackorjargh.javaclass.Lists;
import com.trackorjargh.javaclass.PointBook;
import com.trackorjargh.javaclass.PointFilm;
import com.trackorjargh.javaclass.PointShow;
import com.trackorjargh.javaclass.Show;
import com.trackorjargh.javaclass.User;
import com.trackorjargh.javarepository.BookRepository;
import com.trackorjargh.javarepository.CommentBookRepository;
import com.trackorjargh.javarepository.CommentFilmRepository;
import com.trackorjargh.javarepository.CommentShowRepository;
import com.trackorjargh.javarepository.FilmRepository;
import com.trackorjargh.javarepository.GenderRepository;
import com.trackorjargh.javarepository.ListsRepository;
import com.trackorjargh.javarepository.PointBookRepository;
import com.trackorjargh.javarepository.PointFilmRepository;
import com.trackorjargh.javarepository.PointShowRepository;
import com.trackorjargh.javarepository.ShowRepository;
import com.trackorjargh.javarepository.UserRepository;

@Component
public class DatabaseInformationLoader {

	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private GenderRepository genderRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShowRepository showRepository;
	@Autowired
	private ListsRepository listsRepository;
	@Autowired
	private CommentFilmRepository commentFilmRepository;
	@Autowired
	private CommentBookRepository commentBookRepository;
	@Autowired
	private CommentShowRepository commentShowRepository;
	@Autowired
	private PointFilmRepository pointFilmRepository;
	@Autowired
	private PointBookRepository pointBookRepository;
	@Autowired
	private PointShowRepository pointShowRepository;

	@PostConstruct
	private void initDatabase() {
		// Test Data User
		User u1 = new User("oscar", "1234", "oscarsotosanchez@gmail.com", "", true, "ROLE_USER");
		userRepository.save(u1);
		User u2 = new User("jesus", "1234", "jesusmola@gmail.com", "img/userFoto.jpg", true, "ROLE_USER", "ROLE_ADMIN");
		userRepository.save(u2);
		User u3 = new User("alfonso", "1234", "fonycas@hotmail.com","img/Alfonso_Casanova.jpg",true,"ROLE_USER", "ROLE_ADMIN");

		// Test Data Gender
		Gender g1 = new Gender("Aventuras");
		genderRepository.save(g1);
		Gender g2 = new Gender("Terror");
		genderRepository.save(g2);
		Gender g3 = new Gender("Romántica");
		genderRepository.save(g3);
		Gender g4 = new Gender("Comedia");
		genderRepository.save(g4);
		Gender g5 = new Gender("Thriller");
		genderRepository.save(g5);
		Gender g6 = new Gender("Acción");
		genderRepository.save(g6);
		Gender g7 = new Gender("Animación");
		genderRepository.save(g7);
		Gender g8 = new Gender("Superhéroes");
		genderRepository.save(g8);
		Gender g9 = new Gender("Drama");
		genderRepository.save(g9);
		Gender g10 = new Gender("Ciencia ficción");
		genderRepository.save(g10);
		Gender g11 = new Gender("Histórica");
		genderRepository.save(g11);
		Gender g12 = new Gender("Musical");
		genderRepository.save(g12);

		// Test Data Film
		Film f1 = new Film("Guardianes de la Galaxia 2",
				"Chris Pratt, Zoe Saldana, Bradley Cooper, Dave Bautista, Vin Diesel, Michael Rooker, Karen Gillan, Pom Klementieff, Elizabeth Debicki, Chris Sullivan, Sean Gunn, Sylvester Stallone, Kurt Russell",
				"James Gunn",
				"Guardianes de la Galaxia Vol. 2 continúa las aventuras del equipo a medida que viajan cruzando\n los confines del cosmos. Los Guardianes tendrán que luchar para\n mantener su recién...",
				"/img/Guardianes2.jpg", "https://www.youtube.com/embed/12gvJgLE4us?rel=0&amp;controls=0&amp;showinfo=0",
				2017);
		f1.getGenders().add(g1);
		f1.getGenders().add(g8);
		f1.getGenders().add(g4);
		f1.getGenders().add(g6);
		f1.getGenders().add(g10);
		filmRepository.save(f1);
		
		Film f2 = new Film("Guardianes de la Galaxia","Chris Pratt, Zoe Saldana, Bradley Cooper, Dave Bautista, Vin Diesel, Michael Rooker, Karen Gillan, Djimon Hounsou, John C. Reilly, Glenn Close, Lee Pace, Benicio del Toro",
				"James Gunn","Peter Quill, debe mantener una incomoda alianza con Gamora, Drax, Rocket y Groot para asegurar un artefacto que pone en peligro a toda la galaxia no caiga en las manos del temible Ronan el Acusador.",
				"/img/Guardianes2.jpg","https://www.youtube.com/embed/dzj4P11Yr6E",2008);
		
		f2.getGenders().add(g1);
		f2.getGenders().add(g8);
		f2.getGenders().add(g4);
		f2.getGenders().add(g6);
		f2.getGenders().add(g10);
		filmRepository.save(f2);
		
		Film f3 = new Film("Insidious", "Patrick Wilson, Rose Byrne, Barbara Hershey", "James Wan","La historia se centra en una pareja cuyo hijo inexplicablemente entra en estado de coma y se convierte en un recipiente para fantasmas en una dimensión astral que quiere habitar su cuerpo",
				"/img/peliculas/insidious.jpg", "https://www.youtube.com/embed/FRW3K0LlsD0", 2010);
		
		f3.getGenders().add(g2);
		f3.getGenders().add(g5);
		filmRepository.save(f3);
		
		Film f4 = new Film("Mi gran boda Griega", "Nia Vardalos, Christina Eleusiniotis, John Corbett, Michael Constantine, Lainie Kazan, Andrea Martin"," Joel Zwick",
				"La trama gira alrededor de Toula Portokalos, una mujer de ascendencia griego-americana, que se enamora de un chico estadounidense llamado Ian Miller. En el transcurso de la película, los protagonistas tienen que luchar por vencer las diferencias culturales",
				"/img/peliculas/miGranBodaGriega.jpg", "https://www.youtube.com/embed/nnVMh3uDwwE", 2002);
		
		f4.getGenders().add(g3);
		f4.getGenders().add(g4);
		filmRepository.save(f4);
		
		Film f5 = new Film("Your name","Ryunosuke Kamiki, Mone Kamishiraishi, Masami Nagasawa,Etsuko Ichihara, Ryo Narita, Aoi Yūki","Makoto Shinkai",
				"Aki y Mitsuha descubren un día que durante el sueño sus cuerpos se intercambian, y comienzan a comunicarse por medio de notas. A medida que consiguen superar torpemente un reto tras otro, se va creando entre los dos un vínculo que poco a poco se convierte en algo más romántico",
				"/img/peliculas/yourName","https://www.youtube.com/embed/eHS8cPgzLsI",2017);
		
		f5.getGenders().add(g3);
		f5.getGenders().add(g5);
		f5.getGenders().add(g7);
		f5.getGenders().add(g12);
		filmRepository.save(f5);
		
		Film f6 = new Film("Gladiator","Russell Crowe, Joaquin Phoenix, Connie Nielsen, Ralf Möller, Oliver Reed, Djimon Hounsou, Derek Jacobi, John Shrapnel y Richard Harris", "Ridley Scott",
				"Máximo Décimo Meridio, un leal general hispano del ejército de la Antigua Roma, que es traicionado por Cómodo, el ambicioso hijo del emperador, quien ha asesinado a su padre y se ha hecho con el trono. Forzado a convertirse en esclavo, Máximo triunfa como gladiador mientras anhela vengar la muerte de su familia y su emperador",
				"/img/peliculas/gladiator", "https://www.youtube.com/embed/s6v-bUY_wS8",2000);
		
		
		f6.getGenders().add(g6);
		f6.getGenders().add(g11);
		filmRepository.save(f6);
		
		
		Film f7 = new Film("Indiana Johnes: El templo maldito","Harrison Ford, Kate Capshaw,Amrish Puri,Jonathan Ke Quan, Roshan Seth, Philip Stone, Roy Chiao","Steven Spielberg"," Indiana Jones llega accidentalmente al norte de India, donde los desesperados habitantes de un pequeño pueblo le piden ayuda para encontrar una piedra mágica que les han robado",
				"/img/indianJohns","https://www.youtube.com/embed/jFqK5xyPQQc",1984);
		
		f7.getGenders().add(g1);
		f7.getGenders().add(g6);
		filmRepository.save(f7);
		
		Film f8 = new Film("Jesucristo Superstar", "Jeff Fenholt, Ben Vereen, Yvonne Elliman, Barry Dennen, Bob Bingham, Phil Jethro, Michael Jason, Dennis Buckleys, Paul Ainsley, Ted Neeley","Robert Stigwood","Famoso musical de rock, basado en una exitosa obra de Broadway, que relata la historia de Jesús de Nazaret por medio de canciones. Se vendieron millones de discos de su banda sonora en todo el mundo.",
				"img/peliculas/jesucristo","https://www.youtube.com/embed/edTECo3gCd4", 1970);
			

		f8.getGenders().add(g11);
		f8.getGenders().add(g12);
		filmRepository.save(f7);
		
		
		
		
		
		
		
		
		

		CommentFilm cAux;
		for (int x = 3; x <= 100; x++) {
			f1 = new Film("Guardianes de la Galaxia " + x, "", "",
					"Guardianes de la Galaxia Vol. 2 continúa las aventuras del equipo a medida que viajan cruzando\n los confines del cosmos. Los Guardianes tendrán que luchar para\n mantener su recién...",
					"/img/Guardianes2.jpg",
					"https://www.youtube.com/embed/12gvJgLE4us?rel=0&amp;controls=0&amp;showinfo=0", 2017);
			f1.getGenders().add(g1);
			filmRepository.save(f1);

			cAux = new CommentFilm("Esta pelicula es muy buena");
			cAux.setFilm(f1);
			cAux.setUser(u1);
			commentFilmRepository.save(cAux);
		}

		// Test Data Book
		Book b1 = new Book("Los Juegos del Hambre", "",
				"Los juegos del hambre se desarrolla en un país llamado Panem, lo que es en realidad una civilización postapocalíptica ubicada en lo que antes era América del Norte.",
				"/img/los_juegos_del_hambre.jpg", 2008);
		b1.getGenders().add(g1);
		bookRepository.save(b1);

		for (int x = 2; x <= 100; x++) {
			b1 = new Book("Los Juegos del Hambre " + x, "",
					"Los juegos del hambre se desarrolla en un país llamado Panem, lo que es en realidad una civilización postapocalíptica ubicada en lo que antes era América del Norte.",
					"/img/los_juegos_del_hambre.jpg", 2008);
			b1.getGenders().add(g1);
			bookRepository.save(b1);
		}

		// Test Data Show
		Show sh1 = new Show("The Big Bang Theory", "", "",
				"La serie comienza con la llegada de Penny, aspirante a actriz, al apartamento vecino, que comparten Sheldon y Leonard, dos físicos que trabajan en el Instituto Tecnológico de California (Caltech). Leonard se enamora desde el primer momento de Penny.",
				"/img/the_big_bang_theroy.jpg", "https://www.youtube.com/embed/_uQXxvZ3afQ", 2007);
		showRepository.save(sh1);

		for (int x = 2; x <= 100; x++) {
			sh1 = new Show("The Big Bang Theory " + x, "", "",
					"La serie comienza con la llegada de Penny, aspirante a actriz, al apartamento vecino, que comparten Sheldon y Leonard, dos físicos que trabajan en el Instituto Tecnológico de California (Caltech). Leonard se enamora desde el primer momento de Penny.",
					"/img/the_big_bang_theroy.jpg", "https://www.youtube.com/embed/_uQXxvZ3afQ", 2007);
			showRepository.save(sh1);
		}

		// Test Data Lists
		Lists l1 = new Lists("Mi Lista Molona");
		l1.getFimls().add(f1);
		l1.getBooks().add(b1);
		l1.getShows().add(sh1);
		listsRepository.save(l1);

		// Test Data Comment Film
		CommentFilm cf1 = new CommentFilm("Esta pelicula es muy buena");
		cf1.setFilm(f1);
		cf1.setUser(u1);
		commentFilmRepository.save(cf1);

		// Test Data Comment Show
		CommentShow cs1 = new CommentShow("Esta serie es muy buena");
		cs1.setShow(sh1);
		cs1.setUser(u1);
		commentShowRepository.save(cs1);

		// Test Data Comment Book
		CommentBook cb1 = new CommentBook("Este libro es muy bueno");
		cb1.setBook(b1);
		cb1.setUser(u1);
		commentBookRepository.save(cb1);

		// Test Data Point Film
		PointFilm pf1 = new PointFilm((long) 5);
		pf1.setFilm(f1);
		pf1.setUser(u1);
		pointFilmRepository.save(pf1);

		// Test Data Point Show
		PointShow ps1 = new PointShow((long) 5);
		ps1.setShow(sh1);
		ps1.setUser(u1);
		pointShowRepository.save(ps1);

		// Test Data Point Book
		PointBook pb1 = new PointBook((long) 5);
		pb1.setBook(b1);
		pb1.setUser(u1);
		pointBookRepository.save(pb1);
	}
}
