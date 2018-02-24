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
		User u2 = new User("jesus", "1234", "jesusmola@gmail.com", "img/userFoto.jpg", true, "ROLE_USER",
				"ROLE_MODERATOR", "ROLE_ADMIN");
		userRepository.save(u2);
		User u3 = new User("alfonso", "1234", "fonycas@hotmail.com", "img/Alfonso_Casanova.jpg", true, "ROLE_USER",
				"ROLE_ADMIN");
		userRepository.save(u3);

		// Test Data Gender
		Gender g1 = new Gender("Aventuras");
		genderRepository.save(g1);
		Gender g2 = new Gender("Terror");
		genderRepository.save(g2);
		Gender g3 = new Gender("Romantica");
		genderRepository.save(g3);
		Gender g4 = new Gender("Comedia");
		genderRepository.save(g4);
		Gender g5 = new Gender("Thriller");
		genderRepository.save(g5);
		Gender g6 = new Gender("Accion");
		genderRepository.save(g6);
		Gender g7 = new Gender("Animacion");
		genderRepository.save(g7);
		Gender g8 = new Gender("Superheroes");
		genderRepository.save(g8);
		Gender g9 = new Gender("Drama");
		genderRepository.save(g9);
		Gender g10 = new Gender("Ciencia ficcion");
		genderRepository.save(g10);
		Gender g11 = new Gender("Historica");
		genderRepository.save(g11);
		Gender g12 = new Gender("Musical");
		genderRepository.save(g12);
		Gender g13 = new Gender("Fantasia");
		genderRepository.save(g13);

		final int maxGenders = 13;

		// Test Data Film
		Film f0 = new Film("Guardianes",
				"Chris Pratt, Zoe Saldana, Bradley Cooper, Dave Bautista, Vin Diesel, Michael Rooker, Karen Gillan, Pom Klementieff, Elizabeth Debicki, Chris Sullivan, Sean Gunn, Sylvester Stallone, Kurt Russell",
				"James Gunn",
				"Guardianes de la Galaxia Vol. 2 continúa las aventuras del equipo a medida que viajan cruzando\n los confines del cosmos. Los Guardianes tendrán que luchar para\n mantener su recién...",
				"/img/Guardianes2.jpg", "https://www.youtube.com/embed/12gvJgLE4us?rel=0&amp;controls=0&amp;showinfo=0",
				2017);
		f0.getGenders().add(g1);
		filmRepository.save(f0);

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

		Film f2 = new Film("Guardianes de la Galaxia",
				"Chris Pratt, Zoe Saldana, Bradley Cooper, Dave Bautista, Vin Diesel, Michael Rooker, Karen Gillan, Djimon Hounsou, John C. Reilly, Glenn Close, Lee Pace, Benicio del Toro",
				"James Gunn",
				"Peter Quill, debe mantener una incomoda alianza con Gamora, Drax, Rocket y Groot para asegurar un artefacto que pone en peligro a toda la galaxia no caiga en las manos del temible Ronan el Acusador.",
				"/img/Guardianes2.jpg", "https://www.youtube.com/embed/dzj4P11Yr6E", 2008);

		f2.getGenders().add(g1);
		f2.getGenders().add(g8);
		f2.getGenders().add(g4);
		f2.getGenders().add(g6);
		f2.getGenders().add(g10);
		filmRepository.save(f2);

		Film f3 = new Film("Insidious", "Patrick Wilson, Rose Byrne, Barbara Hershey", "James Wan",
				"La historia se centra en una pareja cuyo hijo inexplicablemente entra en estado de coma y se convierte en un recipiente para fantasmas en una dimensión astral que quiere habitar su cuerpo",
				"/img/films/insidious.jpg", "https://www.youtube.com/embed/FRW3K0LlsD0", 2010);

		f3.getGenders().add(g2);
		f3.getGenders().add(g5);
		filmRepository.save(f3);

		Film f4 = new Film("Mi gran boda Griega",
				"Nia Vardalos, Christina Eleusiniotis, John Corbett, Michael Constantine, Lainie Kazan, Andrea Martin",
				" Joel Zwick",
				"La trama gira alrededor de Toula Portokalos, una mujer de ascendencia griego-americana, que se enamora de un chico estadounidense llamado Ian Miller. En el transcurso de la película, los protagonistas tienen que luchar por vencer las diferencias culturales",
				"/img/films/miGranBodaGriega.jpg", "https://www.youtube.com/embed/nnVMh3uDwwE", 2002);

		f4.getGenders().add(g3);
		f4.getGenders().add(g4);
		filmRepository.save(f4);

		Film f5 = new Film("Your name",
				"Ryunosuke Kamiki, Mone Kamishiraishi, Masami Nagasawa,Etsuko Ichihara, Ryo Narita, Aoi Yūki",
				"Makoto Shinkai",
				"Aki y Mitsuha descubren un día que durante el sueño sus cuerpos se intercambian, y comienzan a comunicarse por medio de notas. A medida que consiguen superar torpemente un reto tras otro, se va creando entre los dos un vínculo que poco a poco se convierte en algo más romántico",
				"/img/films/yourName.jpg", "https://www.youtube.com/embed/eHS8cPgzLsI", 2017);

		f5.getGenders().add(g3);
		f5.getGenders().add(g5);
		f5.getGenders().add(g7);
		f5.getGenders().add(g12);
		filmRepository.save(f5);

		Film f6 = new Film("Gladiator",
				"Russell Crowe, Joaquin Phoenix, Connie Nielsen, Ralf Möller, Oliver Reed, Djimon Hounsou, Derek Jacobi, John Shrapnel y Richard Harris",
				"Ridley Scott",
				"Máximo Décimo Meridio, un leal general hispano del ejército de la Antigua Roma, que es traicionado por Cómodo, el ambicioso hijo del emperador, quien ha asesinado a su padre y se ha hecho con el trono. Forzado a convertirse en esclavo, Máximo triunfa como gladiador mientras anhela vengar la muerte de su familia y su emperador",
				"/img/films/gladiador.jpg", "https://www.youtube.com/embed/s6v-bUY_wS8", 2000);

		f6.getGenders().add(g6);
		f6.getGenders().add(g11);
		filmRepository.save(f6);

		Film f7 = new Film("Indiana Johnes: El templo maldito",
				"Harrison Ford, Kate Capshaw,Amrish Puri,Jonathan Ke Quan, Roshan Seth, Philip Stone, Roy Chiao",
				"Steven Spielberg",
				" Indiana Jones llega accidentalmente al norte de India, donde los desesperados habitantes de un pequeño pueblo le piden ayuda para encontrar una piedra mágica que les han robado",
				"/img/films/indianJohns.jpg", "https://www.youtube.com/embed/jFqK5xyPQQc", 1984);

		f7.getGenders().add(g1);
		f7.getGenders().add(g6);
		filmRepository.save(f7);

		Film f8 = new Film("Jesucristo Superstar",
				"Jeff Fenholt, Ben Vereen, Yvonne Elliman, Barry Dennen, Bob Bingham, Phil Jethro, Michael Jason, Dennis Buckleys, Paul Ainsley, Ted Neeley",
				"Robert Stigwood",
				"Famoso musical de rock, basado en una exitosa obra de Broadway, que relata la historia de Jesús de Nazaret por medio de canciones. Se vendieron millones de discos de su banda sonora en todo el mundo.",
				"/img/films/jesucristo.jpg", "https://www.youtube.com/embed/edTECo3gCd4", 1970);

		f8.getGenders().add(g11);
		f8.getGenders().add(g12);
		filmRepository.save(f8);

		Film f9 = new Film("Star Wars La venganza de los Sith", "Hayden Christensen, Natalie Portman, Ewan McGregor",
				"George Lucas",
				"Último capítulo de la trilogía de precuelas de Star Wars, en el que Anakin Skywalker definitivamente se pasa al lado oscuro. En el Episodio III aparece el General Grievous, un ser implacable mitad-alien mitad-robot, el líder del ejército separatista Droid. Los Sith son los amos del lado oscuro de la Fuerza y los enemigos de los Jedi. Fueron prácticamente exterminados por los...",
				"/img/films/starWars3.jpg", "https://www.youtube.com/embed/kqkfjBKmWc4", 2005);

		f9.getGenders().add(g1);
		f9.getGenders().add(g6);
		f9.getGenders().add(g10);
		filmRepository.save(f9);

		Film f10 = new Film("El exorcista", " Ellen Burstyn, Max von Sydow, Linda Blair", "William Friedkin",
				"Adaptación de la novela de William Peter Blatty que se inspiró en un exorcismo real ocurrido en Washington en 1949. Regan, una niña de doce años, es víctima de fenómenos paranormales como la levitación o la manifestación de una fuerza sobrehumana. Su madre, aterrorizada, tras someter a su hija a múltiples análisis médicos que no ofrecen ningún resultado, acude a un sacerdote ...",
				"/img/films/theExorcist.jpg", "https://www.youtube.com/embed/HTPg9f3Win0 ", 1973);

		f10.getGenders().add(g2);
		f10.getGenders().add(g5);
		filmRepository.save(f10);

		Film f11 = new Film("La princesa de Mononoke", "Yôji Matsuda, Yuriko Ishida, Yûko Tanaka", "Hayao Miyazaki",
				"Con el fin de curar la herida que le ha causado un jabalí enloquecido, el joven Ashitaka sale en busca del dios Ciervo, pues sólo él puede liberarlo del sortilegio. A lo largo de su periplo descubre cómo los animales del bosque luchan contra hombres que están dispuestos a destruir la Naturaleza.",
				"/img/films/laPrincesa.jpg", "https://www.youtube.com/embed/WVjVkpk7wKg", 1997);

		f11.getGenders().add(g1);
		f11.getGenders().add(g6);
		f11.getGenders().add(g7);
		f11.getGenders().add(g10);
		filmRepository.save(f11);

		Film f12 = new Film("Scooby-doo y el misterio de Wrestlemania", " Frank Welker, Mindy Cohn, Grey DeLisle",
				"Brandon Vietti",
				"Scooby y sus amigos ganan unas entradas para el show de Wrestlemania, pero allí son sorprendidos con la aparición de un oso fantasma.",
				"/img/films/scoobyDoo.jpg", "https://www.youtube.com/embed/VD88HIKoFzk", 2014);

		f12.getGenders().add(g7);
		f12.getGenders().add(g1);
		f12.getGenders().add(g13);
		filmRepository.save(f12);

		Film f13 = new Film("Los vengadores", "Robert Downey Jr., Chris Evans, Scarlett Johansson", "Joss Whedon",
				"Cuando un enemigo inesperado surge como una gran amenaza para la seguridad mundial, Nick Fury, director de la Agencia SHIELD, decide reclutar a un equipo para salvar al mundo de un desastre casi seguro. Adaptación del cómic de Marvel \"Los Vengadores\", el legendario grupo de superhéroes formado por Ironman, Hulk, Thor y el Capitán América entre otros.",
				"/img/films/losVengadores.jpg", "https://www.youtube.com/embed/HQIiYqOVTWo", 2012);

		f13.getGenders().add(g1);
		f13.getGenders().add(g8);
		f13.getGenders().add(g4);
		f13.getGenders().add(g6);
		f13.getGenders().add(g10);
		filmRepository.save(f13);

		Film f14 = new Film("Capitan America", "Chris Evans, Hugo Weaving, Samuel L. Jackson", "Joe Johnston",
				"Tras los devastadores acontecimientos acaecidos en Nueva York con Los Vengadores, Steve Rogers, alias el Capitán América, vive tranquilamente en Washington D.C. intentando adaptarse al mundo moderno. Pero cuando atacan a un colega de S.H.I.E.L.D., Steve se ve envuelto en una trama de intrigas que representa una amenaza para el mundo. Se unirá entonces a la Viuda Negra para ",
				"/img/films/capitanAmerica.jpg", "https://www.youtube.com/embed/B5nTjpO4LZ0", 2011);

		f14.getGenders().add(g1);
		f14.getGenders().add(g8);
		f14.getGenders().add(g4);
		f14.getGenders().add(g6);
		filmRepository.save(f14);

		Film f15 = new Film("Doctor Strange", "Benedict Cumberbatch, Chiwetel Ejiofor, Rachel McAdams",
				"Scott Derrickson",
				"La vida del Dr. Stephen Strange cambia para siempre tras un accidente automovilístico que le deja muy malheridas sus manos. Cuando la medicina tradicional falla, se ve obligado a buscar esperanza y una cura en un lugar impensable: una comunidad aislada en Nepal llamada Kamar-Taj. Rápidamente descubre que éste no es sólo un centro de recuperación, sino también la primera línea...",
				"/img/films/drstrange.jpg", "https://www.youtube.com/embed/DYyMsYgZDJM", 2016);

		f15.getGenders().add(g1);
		f15.getGenders().add(g8);
		f15.getGenders().add(g4);
		f15.getGenders().add(g6);
		f15.getGenders().add(g10);
		filmRepository.save(f15);

		Film f16 = new Film("El padrino", " Marlon Brando, Al Pacino, James Caan", "Francis Ford Coppola",
				"Finales de los años 40 en Nueva York. Vito Corleone es, en la jerga del crimen organizado, un padrino o don, el cabecilla de la Mafia. Michael, un libre pensador que desafió a su padre al alistarse en el cuerpo de Marines en la Segunda Guerra Mundial, regresa como capitán y héroe de guerra",
				"/img/films/elPadrino.jpg", "https://www.youtube.com/embed/sY1S34973zA", 1972);

		f16.getGenders().add(g11);
		f16.getGenders().add(g6);
		filmRepository.save(f16);

		Film f17 = new Film("La naranja mecánica", " Malcolm McDowell, Patrick Magee, Michael Bates",
				" Stanley Kubrick",
				"Las aventuras de un joven cuyos principales intereses son la violación, la ultra-violencia, y Beethoven. Alex es el jefe de la banda de los drugos, que tienen sus propios métodos para divertirse y descargar su tremenda agresividad, ya sea dando una paliza a un mendigo o entrando en una casa para destrozar lo que hay dentro y violar a la mujer que viva allí. Sin embargo...",
				"/img/films/laNaranjaMecanica.jpg", "https://www.youtube.com/embed/xHFPi_3kc1U", 1971);

		f17.getGenders().add(g11);
		f17.getGenders().add(g6);
		filmRepository.save(f17);

		Film f18 = new Film("El rey leon", "Matthew Broderick, Jeremy Irons, James Earl Jones",
				"Roger Allers, Rob Minkoff",
				"Simba es un león, hijo del rey de la selva Mufasa. Cierto día, jugando al backet con amigos unos tipos del barrio le metieron en un lio, y su padre le decia una y otra vez con tu tio y con tu tia te irás a Bel-Air",
				"/img/films/reyLeon.jpg", "https://www.youtube.com/embed/4sj1MT05lAA", 1994);

		f18.getGenders().add(g1);
		f18.getGenders().add(g7);
		filmRepository.save(f18);

		Film f19 = new Film("Titanic", " Leonardo DiCaprio, Kate Winslet, Billy Zane", " James Cameron",
				"Jack es un chico pobre de barrio que no trafica con drogas pues porque aun no se han inventado todavía que se cuela en un barco con un amigo el cual muere luego bum spoiler y que se enamora de una chica un pelin guarri porque le mola que la pinten en bolas y tiene un grave problema para compartir lo cual lleva a Jack a morir cuando se hunde el barco y no le comparte el tablon flotante",
				"/img/films/titanic.jpg", "https://www.youtube.com/embed/2e-eXJ6HgkQ", 1997);

		f19.getGenders().add(g3);
		f19.getGenders().add(g11);
		filmRepository.save(f19);

		Film f20 = new Film("Interestellar", " Matthew McConaughey, Anne Hathaway, Jessica Chastain",
				"Christopher Nolan",
				"Un grupo de astronautas se lanza al espacio para buscar un futuro para la raza humana que parece perdido en 'Interstellar'. Ahora que la Tierra se acerca poco a poco al fin de su sus días debido a una más que preocupante escasez de comida por el mal estado de las tierras. Cooper deberá elegir entre quedarse con sus hijos o liderar esta expedición, que aprovechará los descubrimientos...",
				"/img/films/interestellar.jpg", "https://www.youtube.com/embed/UoSSbmD9vqc", 2014);

		f20.getGenders().add(g5);
		f20.getGenders().add(g10);
		filmRepository.save(f20);

		Film f21 = new Film("Buscando a nemo", " Albert Brooks, Ellen DeGeneres, Alexander Gould",
				" Andrew Stanton, Lee Unkrich",
				"El pequeño Nemo, un pececillo hijo único que perdió a su madre antes de nacer, es muy querido y excesivamente protegido por su padre. Nemo ha sido pescado y sacadi de la gran barrera del arrecife australiano y ahora vive en una pequeña pecera en la oficina de un dentista de Sidney. El tímido padre de Nemo se embarcará en una peligrosa aventura donde conoce Dory. Juntos vane...",
				"/img/films/buscandoAnemo.jpg", "https://www.youtube.com/embed/wZdpNglLbt8", 2003);

		f21.getGenders().add(g1);
		f21.getGenders().add(g7);
		filmRepository.save(f21);

		// Comment Film Section

		CommentFilm c1 = new CommentFilm("Esta pelicula es muy buena");
		c1.setFilm(f1);
		c1.setUser(u1);
		commentFilmRepository.save(c1);

		CommentFilm c2 = new CommentFilm("A mi me gustó mas la 2, pero bueno no está mal");
		c2.setFilm(f2);
		c2.setUser(u1);
		commentFilmRepository.save(c2);

		CommentFilm c3 = new CommentFilm("Qué miedo pasé, esa noche dormí abrazado a mi osito de peluche");
		c3.setFilm(f3);
		c3.setUser(u1);
		commentFilmRepository.save(c3);

		CommentFilm c4 = new CommentFilm("k dices tio, no es pa tanto, vaya cagueta");
		c4.setFilm(f3);
		c4.setUser(u2);
		commentFilmRepository.save(c4);

		CommentFilm c5 = new CommentFilm(
				"Esta peli la vi con mi novia, no esta mal, te ries, pero hubiera preferido esdla");
		c5.setFilm(f4);
		c5.setUser(u3);
		commentFilmRepository.save(c5);

		CommentFilm c6 = new CommentFilm("Es que eres un poco calzonazossss!!! jajajaja ");
		c6.setFilm(f4);
		c6.setUser(u2);
		commentFilmRepository.save(c6);

		CommentFilm c7 = new CommentFilm("¬¬");
		c7.setFilm(f4);
		c7.setUser(u3);
		commentFilmRepository.save(c7);

		CommentFilm c8 = new CommentFilm(
				"Buah, me ha encantado, me he descargado y todo la banda sonora, que por si a alguien le interesa se llaman RADWIMPS");
		c8.setFilm(f5);
		c8.setUser(u3);
		commentFilmRepository.save(c8);

		CommentFilm c9 = new CommentFilm(
				"Yo me rei mucho cuando cayó el meteorito, lo dije de broma pero al final resultó que cayó de verdad xD");
		c9.setFilm(f5);
		c9.setUser(u1);
		commentFilmRepository.save(c9);

		CommentFilm c10 = new CommentFilm(
				"No me gustan las peliculas de gladiadiores, me parace absurdo el final y gratuito, y el emperador lo hace todo mal en fin... ");
		c10.setFilm(f6);
		c10.setUser(u2);
		commentFilmRepository.save(c8);

		CommentFilm c11 = new CommentFilm(
				"Esta es la peli que hablan en Big Bang que si quitas a indiana Johnes no habria cambiado nada XDD ");
		c11.setFilm(f7);
		c11.setUser(u3);
		commentFilmRepository.save(c11);

		CommentFilm c12 = new CommentFilm(
				"A mi me ha gustado, lo unico que a la iglesia no le mola mucho porque al parecer la peli acaba con la muerte de jesucristo, y da un mensaje equivocado en teoria");
		c12.setFilm(f8);
		c12.setUser(u1);
		commentFilmRepository.save(c12);

		CommentFilm c13 = new CommentFilm("Un poco bodrio, pero bueno, no pueden gustarme todas");
		c13.setFilm(f9);
		c13.setUser(u2);
		commentFilmRepository.save(c13);

		// Test Data Book
		Book b1 = new Book("Los Juegos del Hambre", "",
				"Los juegos del hambre se desarrolla en un país llamado Panem, lo que es en realidad una civilización postapocalíptica ubicada en lo que antes era América del Norte.",
				"/img/los_juegos_del_hambre.jpg", 2008);
		b1.getGenders().add(g1);
		bookRepository.save(b1);

		Book b2 = new Book("El retorno del rey", "J.R.R. Tolkien",
				"Los ejércitos del Señor Oscuro van extendiendo cada vez más su maléfica sombra por la Tierra Media. Hombres, elfos y enanos unen sus fuerzas para presentar batalla a Sauron y sus huestes. Ajenos a estos preparativos, Frodo y Sam siguen adentrándose en el país de Mordor en su heroico viaje para destruir el Anillo de Poder en las Grietas del Destino",
				"/img/books/elRetornoDelRey.jpg", 1955);

		b2.getGenders().add(g1);
		b2.getGenders().add(g13);
		bookRepository.save(b2);

		Book b3 = new Book("Graceling", "Kristin Cashore",
				"Hay gente en estas tierras que tiene poderes extraordinarios a los que llamamos gracias. Una gracia puede tener un valor infinito o puede ser totalmente inútil. Una gracia puede hacer que alguien sea veloz como el viento, o que sea capaz de predecir el tiempo, mientras que otras sólo harán que hables al revés sin pensar o te subas a los árboles. Mi nombre es Katsa...",
				"/img/books/graceling.jpg", 2008);

		b3.getGenders().add(g1);
		b3.getGenders().add(g13);
		bookRepository.save(b3);

		Book b4 = new Book("Los pilares de la Tierra", "Ken Follet",
				"El gran maestro de la narrativa de acción y suspense nos transporta a la Edad Media, a un fascinante mundo de reyes, damas, caballeros, pugnas feudales, castillos y ciudades amuralladas. El amor y la muerte se entrecruzan vibrantemente en este magistral tapiz cuyo centro es la construcción de una catedral gótica. La historia se inicia con el ahorcamiento público de ...",
				"/img/books/losPilaresDeLaTierra.jpg", 1989);

		b4.getGenders().add(g5);
		b4.getGenders().add(g11);
		b4.getGenders().add(g3);
		bookRepository.save(b4);

		Book b5 = new Book("El juego de Ender", "Orson Scott",
				"La Tierra se ve amenazada por una especie extraterrestre de insectos, seres que se comunican telepáticamente y que se consideran totalmente distintos de los humanos, a los que quieren destruir. Para vencerlos, la humanidad necesita un genio militar, y por ello se permite el nacimiento de Ender, que es el tercer hijo de una pareja en un mundo que ha limitado ...",
				"/img/books/elJuegoDeEnder.jpg", 1985);

		b5.getGenders().add(g10);
		b5.getGenders().add(g1);
		b5.getGenders().add(g6);
		bookRepository.save(b5);

		Book b6 = new Book("El capitan Alatriste", "Arturno Pérez Reverte",
				"No era el hombre más honesto ni el más piadoso, pero era un hombre valiente... Con estas palabras empieza El capitán Alatriste, la historia de un soldado veterano de los tercios de Flandes que malvive como espadachín a sueldo en el Madrid del siglo XVII.",
				"/img/books/elCapitanAlatriste.jpg", 1996);

		b6.getGenders().add(g11);
		b6.getGenders().add(g1);
		b6.getGenders().add(g6);
		bookRepository.save(b6);

		Book b7 = new Book("La Biblia", "Dios",
				"Todo comenzó con Adán y Eva en el paraiso, pero cuando les echaron de ahí... tuvieron 2 hijos: Caín y Abel...¿Qué `pasó con las mujeres a partir de entonces? Estas dudas y muchas otras más aqui",
				"/img/books/laBiblia.jpg", 0);

		b7.getGenders().add(g1);
		b7.getGenders().add(g2);
		b7.getGenders().add(g3);
		b7.getGenders().add(g4);
		b7.getGenders().add(g5);
		b7.getGenders().add(g6);
		b7.getGenders().add(g7);
		b7.getGenders().add(g8);
		b7.getGenders().add(g9);
		b7.getGenders().add(g10);
		b7.getGenders().add(g11);
		b7.getGenders().add(g12);
		b7.getGenders().add(g13);
		bookRepository.save(b7);

		Book b8 = new Book("La biblioteca de los muertos", "Glenn Cooper",
				"Bretaña, siglo VII. En la abadía de Vectis crece Octavus, un niño al que le auguran poderes diabólicos. Octavus no tarda en empezar a escribir una lista de nombres y fechas sin ningún sentido aparente. Pero poco después, cuando una muerte en la abadía coincide con un nombre y una fecha de la lista, el miedo se apodera de los monjes. Nueva York, en la actualidad...",
				"/img/books/laBibliotecaDeLosMuertos.jpg", 2009);

		b8.getGenders().add(g5);
		b8.getGenders().add(g3);
		bookRepository.save(b8);

		Book b9 = new Book("Harry potter y el prisionero de Azkaban", "J.K. Rowling",
				"De la prisión de Azkaban se ha escapado un terrible villano, Sirius Black, un asesino en serie que fue cómplice de lord Voldemort y que, dicen los rumores, quiere vengarse de Harry por haber destruido a su maestro",
				"/img/books/harryPotterYazkaban.jpg", 1999);

		b9.getGenders().add(g1);
		b9.getGenders().add(g13);
		bookRepository.save(b9);

		Book b10 = new Book("El clan del oso cavernario: Los Hijos de la Tierra", "Jean M. Auel",
				"Narra como Ayla, una niña Cromagnon de 5 años, queda aislada de su tribu por culpa de un terremoto. Pese a ser de una estirpe más desarrollada, es acogida por un grupo de Neardentales, que basa sus razonamientos y comportamientos en las experiencias de sus antepasados.",
				"/img/books/elClanDeOsoCavernario.jpg", 1980);

		b10.getGenders().add(g11);
		b10.getGenders().add(g1);
		bookRepository.save(b10);

		Book b11 = new Book("El bosque de los arboles muertos", "Ana Alcolea",
				"Beatriz va a pasar sus vacaciones veraniegas en un antiguo castillo en Escocia, algo que no le agrada demasiado. Preferiría quedarse con sus amigos en la piscina o en la playa, pero su suspenso en Inglés ha hecho que su madre recurra a una antigua amiga para que la acoja durante el verano y así Beatriz pueda practicar el idioma. Lo que parece un aburrido verano...",
				"/img/books/elBosqueDeLosArbolesMuertos.jpg", 2010);

		b11.getGenders().add(g2);
		b11.getGenders().add(g3);
		b11.getGenders().add(g5);
		bookRepository.save(b11);

		Book b12 = new Book("Cronicas de la dragonlance: la reina de la oscuridad", "Margaret Weiss",
				"Al cabo de cinco años, un grupo de amigos se reúne en El Último Hogar, mientras vientos de guerra asolan el mundo en el que viven. Un arma mágica de increíble poder caerá en las manos de estos amigos y los obligará a emprender una larga aventura que decidirá el destino de Krynn, su mundo.",
				"/img/books/dragondOfSpringDawning.jpg", 1985);

		b12.getGenders().add(g1);
		b12.getGenders().add(g13);
		bookRepository.save(b12);

		Book b13 = new Book("Sherlock Holmes: la liga de los pelirojos", "Arthur Conan Doyle",
				"Wilson, un prestamista pelirrojo, acude a Sherlock Holmes y a Watson para resolver el misterio de la súbita disolución de la «Liga de los pelirrojos», de la que él había sido miembro. El detective londinense no tarda en averiguar que tras esta disolución se encuentra John Clay, uno de los delincuentes más inteligentes y peligrosos de Inglaterra.",
				"/img/books/sherlockHolmasYlosPelirojos.jpg", 1891);

		b13.getGenders().add(g5);
		b13.getGenders().add(g11);
		b13.getGenders().add(g1);
		bookRepository.save(b13);

		Book b14 = new Book("Crepúsculo", "Stephenie Meyer",
				"Cuando Isabella Swan se muda a Forks, una pequeña localidad del estado de Washington en la que nunca deja de llover, piensa que es lo más aburrido que le podía haber ocurrido en la vida. Pero su vida da un giro excitante y aterrador una vez que se encuentra con el misterioso y seductor Edward Cullen. Hasta ese momento, Edward se las ha arreglado para mantener...",
				"/img/books/crepusculo.jpg", 2005);

		b14.getGenders().add(g3);
		b14.getGenders().add(g1);
		b14.getGenders().add(g13);
		bookRepository.save(b14);

		Book b15 = new Book("A 3 metros bajo el cielo", "Federico Moccia",
				"\"Babi es una estudiante modelo y la hija perfecta. Step, en cambio, es violento y descarado. Provienen de dos mundos completamente distintos. A pesar de todo, entre los dos nacerá un amor más allá de todas las convenciones. Un amor controvertido por el que deberán luchar más de lo que esperaban. Babi...",
				"/img/books/TresMetro.jpg", 2004);

		b15.getGenders().add(g3);
		b15.getGenders().add(g1);
		bookRepository.save(b15);

		Book b16 = new Book("El quijote", "Miguel de Cervantes",
				"Como todo caballero necesita una dama, convierte el recuerdo de una campesina de la que estuvo enamorado en la hermosa Dulcinea del Toboso. Y a sí mismo se pone el nombre de Don Quijote, como el famoso caballero Lanzarote (Lancelot). Sale así al campo, con un aspecto ridículo, con la idea de realizar hazañas heroicas.",
				"/img/books/elQuijote.jpg", 1615);

		b16.getGenders().add(g1);
		b16.getGenders().add(g11);
		b16.getGenders().add(g8);
		bookRepository.save(b16);

		Book b17 = new Book("Beethoven", "Jan Swafford",
				"Beethoven, el genio romántico, hombre atormentado y fascinante capaz de componer las piezas más sublimes, vivió su tiempo con extraordinaria intensidad. Jan Swafford recrea de manera amena y profunda la vida del hombre, del compositor y del genio.",
				"/img/books/beethoven.jpg", 2017);

		b17.getGenders().add(g12);
		b17.getGenders().add(g11);
		bookRepository.save(b17);

		Book b18 = new Book("Las fronteras del significado", "Charles Rosen",
				"Charles Rosen explora cuestiones relacionadas con la composición, la interpretación y la escucha de la obra de distintos compositores, y nos ofrece así una aproximación a la música en la que tan importantes como las piezas musicales son las tradiciones en las que éstas se inscriben y se concretan...",
				"/img/books/lasFronterasDelSignificado.jpg", 2017);
		b18.getGenders().add(g12);
		b18.getGenders().add(g11);
		bookRepository.save(b18);
		
		
		Book b19 = new Book("Asesinato en el Orient Express","Agatha Christie","En un lugar aislado de la antigua Yugoslavia, en plena madrugada, una fuerte tormenta de nieve obstaculiza la línea férrea por donde circula el Orient Express. Procedente de la exótica Estambul, en él viaja el detective Hércules Poirot...",
				"/img/books/asesinatoEnOrienteExpress.jpg",1974);
		
		b19.getGenders().add(g1);
		b19.getGenders().add(g5);
		bookRepository.save(b19);
		
		
		Book b20 = new Book("La cumbre escarlata","Nancy Holder","Ambientada en el siglo XIX, en la remota y montañosa región de Cumbria, al norte de Inglaterra, La cumbre escarlata sigue a la joven escritora Edith Cushing, quien descubre que su nuevo y encantador esposo, Sir Thomas Sharpe, no es quien parece ser...",
				"/img/books/laCumbreEscarlata.jpg",2015);
		
		b20.getGenders().add(g2);
		b20.getGenders().add(g5);
		b20.getGenders().add(g1);
		bookRepository.save(b20);

		
		// Test Data Show
		Show sh1 = new Show("The Big Bang Theory", "", "",
				"La serie comienza con la llegada de Penny, aspirante a actriz, al apartamento vecino, que comparten Sheldon y Leonard, dos físicos que trabajan en el Instituto Tecnológico de California (Caltech). Leonard se enamora desde el primer momento de Penny.",
				"/img/the_big_bang_theroy.jpg", "https://www.youtube.com/embed/_uQXxvZ3afQ", 2007);
		showRepository.save(sh1);

		for (int x = 2; x <= 100; x++) {
			sh1 = new Show("The Big Bang Theory " + x, "", "",
					"La serie comienza con la llegada de Penny, aspirante a actriz, al apartamento vecino, que comparten Sheldon y Leonard, dos físicos que trabajan en el Instituto Tecnológico de California (Caltech). Leonard se enamora desde el primer momento de Penny.",
					"/img/the_big_bang_theroy.jpg", "https://www.youtube.com/embed/_uQXxvZ3afQ", 2007);
			sh1.getGenders().add(g1);
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
		PointFilm pf1 = new PointFilm((double) 5);
		pf1.setFilm(f1);
		pf1.setUser(u1);
		pointFilmRepository.save(pf1);

		PointFilm pf2 = new PointFilm((double) 2.3);
		pf2.setFilm(f1);
		pf2.setUser(u1);
		pointFilmRepository.save(pf2);

		PointFilm pf3 = new PointFilm((double) 2.3);
		pf3.setFilm(f1);
		pf3.setUser(u2);
		pointFilmRepository.save(pf3);

		PointFilm pf4 = new PointFilm((double) 4.5);
		pf4.setFilm(f1);
		pf4.setUser(u3);
		pointFilmRepository.save(pf4);

		PointFilm pf5 = new PointFilm((double) 1.5);
		pf5.setFilm(f2);
		pf5.setUser(u1);
		pointFilmRepository.save(pf5);

		PointFilm pf6 = new PointFilm((double) 3.9);
		pf6.setFilm(f3);
		pf6.setUser(u2);
		pointFilmRepository.save(pf6);

		PointFilm pf7 = new PointFilm((double) 2.3);
		pf7.setFilm(f4);
		pf7.setUser(u2);
		pointFilmRepository.save(pf7);

		PointFilm pf8 = new PointFilm((double) 4.2);
		pf8.setFilm(f5);
		pf8.setUser(u2);
		pointFilmRepository.save(pf8);

		// Test Data Point Show
		PointShow ps1 = new PointShow((double) 5);
		ps1.setShow(sh1);
		ps1.setUser(u1);
		pointShowRepository.save(ps1);

		// Test Data Point Book
		PointBook pb1 = new PointBook((double) 5);
		pb1.setBook(b1);
		pb1.setUser(u1);
		pointBookRepository.save(pb1);
	}
}
