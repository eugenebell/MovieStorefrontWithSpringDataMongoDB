package com.eugene.init;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.eugene.model.Account;
import com.eugene.model.Authorities;
import com.eugene.model.Genre;
import com.eugene.model.Movie;
import com.eugene.model.User;
import com.eugene.model.VideoStoreMember;
import com.eugene.service.dao.GenreRepository;
import com.eugene.service.dao.MovieRepository;
import com.eugene.service.dao.UserRepository;
import com.eugene.service.dao.VideoStoreMemberRepository;
import com.mongodb.MongoException;

public class InitializeMongoDB {
	
	private static final Logger LOG = Logger.getLogger(InitializeMongoDB.class);

	
	private GenreRepository genreRepository;
	private MovieRepository movieRepository;
	private VideoStoreMemberRepository videoStoreMemberRepository;
	private UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setVideoStoreMemberRepository(VideoStoreMemberRepository videoStoreMemberRepository) {
		this.videoStoreMemberRepository = videoStoreMemberRepository;
	}

	@Autowired
	public void setMovieRepository(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Autowired
	public void setGenreRepository(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	@PostConstruct
	public void afterPropertiesSet() {
		try {
			genreRepository.deleteAll();
			movieRepository.deleteAll();
			videoStoreMemberRepository.deleteAll();
			userRepository.deleteAll();
			
			LOG.info("About to initialise mongo db");
			List<Genre> genres = new ArrayList<Genre>();
			Genre gAction = new Genre("Action");
			genres.add(gAction);
			Genre gAdventure = new Genre("Adventure");
			genres.add(gAdventure);
			Genre gAnimation = new Genre("Animation");
			genres.add(gAnimation);
			Genre gComedy = new Genre("Comedy");
			genres.add(gComedy);
			Genre gFamily = new Genre("Family");
			genres.add(gFamily);
			Genre gDrama = new Genre("Drama");
			genres.add(gDrama);
			Genre gMystery = new Genre("Mystery");
			genres.add(gMystery);
			Genre gThriller = new Genre("Thriller");
			genres.add(gThriller);
			Genre gHistory = new Genre("History");
			genres.add(gHistory);
			Genre gSciFi = new Genre("Sci-Fi");
			genres.add(gSciFi);
			Genre gWar = new Genre("War");
			genres.add(gWar);
			genreRepository.save(genres);
			
			List<Genre> l1 = new ArrayList<Genre>();
			l1.add(gSciFi);l1.add(gThriller);l1.add(gFamily);
			
			List<Genre> l2 = new ArrayList<Genre>();
			l2.add(gWar);l2.add(gDrama);l2.add(gAction);
			
			List<Genre> l3 = new ArrayList<Genre>();
			l3.add(gHistory);l3.add(gComedy);
			
			List<Genre> l4 = new ArrayList<Genre>();
			l4.add(gAdventure);l4.add(gMystery);l4.add(gWar);
			
			List<Genre> l5 = new ArrayList<Genre>();
			l5.add(gWar);l5.add(gAnimation);
			
			List<Movie> movies = new ArrayList<Movie>();
			Movie m13Assassins = new Movie("13 Assassins", 3.99, 2, 2010, "13_Assassins.jpg", "141 min", "A group of assassins come together for a suicide mission to kill an evil lord.", "Koji Yakusho, Takayuki Yamada, Yosuke Iseya", "Takashi Miike", ", ", ", ");
			m13Assassins.setGenres(l1);
			movies.add(m13Assassins);
			Movie m2012 = new Movie("2012", 3.99, 3, 2009, "2012.jpg", "158 min", "Dr. Adrian Helmsley, part of a worldwide geophysical team investigating the effect on the earth of radiation from unprecedented solar storms, learns that the earth's core is heating up. He warns U.S. President Thomas Wilson that the crust of the earth is becoming unstable and that without proper preparations for saving a fraction of the world's population, the entire race is doomed. Meanwhile, writer Jackson Curtis stumbles on the same information. While the world's leaders race to build \"arks\" to escape the impending cataclysm, Curtis struggles to find a way to save his family. Meanwhile, volcanic eruptions and earthquakes of unprecedented strength wreak havoc around the world.", "John Cusack, Thandie Newton, Chiwetel Ejiofor", "Roland Emmerich", ", ", ", ");
			m2012.setGenres(l2);
			movies.add(m2012);
			Movie mAvatar = new Movie("Avatar", 5.99, 5, 2009, "Avatar.jpg", "162 min", "When his brother is killed in a robbery, paraplegic Marine Jake Sully decides to take his place in a mission on the distant world of Pandora. There he learns of greedy corporate figurehead Parker Selfridge's intentions of driving off the native humanoid \"Na'vi\" in order to mine for the precious material scattered throughout their rich woodland. In exchange for the spinal surgery that will fix his legs, Jake gathers intel for the cooperating military unit spearheaded by gung-ho Colonel Quaritch, while simultaneously attempting to infiltrate the Na'vi people with the use of an \"avatar\" identity. While Jake begins to bond with the native tribe and quickly falls in love with the beautiful alien Neytiri, the restless Colonel moves forward with his ruthless extermination tactics, forcing the soldier to take a stand - and fight back in an epic battle for the fate of Pandora.", "Sam Worthington, Zoe Saldana, Sigourney Weaver", "James Cameron", ", ", ", ");
			mAvatar.setGenres(l3);
			movies.add(mAvatar);
			Movie mBadTeacher = new Movie("Bad Teacher", 3.99, 2, 2011, "Bad_Teacher.jpg", "92 min", "A comedy centered around a foul-mouthed, junior high teacher who, after being dumped by her sugar daddy, begins to woo a colleague -- a move that pits her against a well-loved teacher.", "Cameron Diaz, Jason Segel, Justin Timberlake", "Jake Kasdan", ", ", ", ");
			mBadTeacher.setGenres(l4);
			movies.add(mBadTeacher);
			Movie mBattleLosAngeles = new Movie("Battle Los Angeles", 5.99, 4, 2011, "Battle_Los_Angeles.jpg", "116 min", "Los Angeles and other cities around the world are being bombarded by meteors that seem to be slowing down once they hit the earth's atmosphere. The earth is suddenly being invaded by space aliens that have landed off the shore of LA, and who begin killing everybody along the beach. The military is ordered into action. Marine Staff Sergeant Nantz (Aaron Eckhart), who was about to retire, is reassigned to a new platoon. The platoon, flown by chopper to the forward operating base at Santa Monica Airport, is being led by a new 2nd Lt. Martinez (Ramon Rodriguez). They are sent on a mission to rescue some civilians who are trapped at the police station within alien territory. They only have 3 hours to complete their mission and get out before the Air Force bombs that zone.", "Aaron Eckhart, Michelle Rodriguez, Bridget Moynahan", "Jonathan Liebesman", ", ", ", ");
			mBattleLosAngeles.setGenres(l5);
			movies.add(mBattleLosAngeles);
			Movie mBlackSwan = new Movie("Black Swan", 5.99, 3, 2010, "Black_Swan.jpg", "108 min", "Nina (Portman) is a ballerina in a New York City ballet company whose life, like all those in her profession, is completely consumed with dance. She lives with her obsessive former ballerina mother Erica (Hershey) who exerts a suffocating control over her. When artistic director Thomas Leroy (Cassel) decides to replace prima ballerina Beth MacIntyre (Ryder) for the opening production of their new season, Swan Lake, Nina is his first choice. But Nina has competition: a new dancer, Lily (Kunis), who impresses Leroy as well. Swan Lake requires a dancer who can play both the White Swan with innocence and grace, and the Black Swan, who represents guile and sensuality. Nina fits the White Swan role perfectly but Lily is the personification of the Black Swan. As the two young dancers expand their rivalry into a twisted friendship, Nina begins to get more in touch with her dark side - a recklessness that threatens to destroy her.", "Natalie Portman, Mila Kunis, Vincent Cassel", "Darren Aronofsky", ", ", ", ");
			mBlackSwan.setGenres(l1);
			movies.add(mBlackSwan);
			Movie mBraveheart = new Movie("Braveheart", 3.99, 4, 1995, "Braveheart.jpg", "177 min", "William Wallace is a Scottish rebel who leads an uprising against the cruel English ruler Edward the Longshanks, who wishes to inherit the crown of Scotland for himself. When he was a young boy, William Wallace's father and brother, along with many others, lost their lives trying to free Scotland. Once he loses another of his loved ones, William Wallace begins his long quest to make Scotland free once and for all, along with the assistance of Robert the Bruce.", "Mel Gibson, Sophie Marceau, Patrick McGoohan", "Mel Gibson", ", ", ", ");
			mBraveheart.setGenres(l2);
			movies.add(mBraveheart);
			Movie mBurlesque = new Movie("Burlesque", 3.99, 3, 2010, "Burlesque.jpg", "119 min", "The Burlesque Lounge has its best days behind it. Tess, a retired dancer and owner of the venue, struggles to keep the aging theater alive, facing all kinds of financial and artistic challenges. With the Lounge's troupe members becoming increasingly distracted by personal problems and a threat coming from a wealthy businessman's quest to buy the spot from Tess, the good fortune seems to have abandoned the club altogether. Meanwhile, the life of Ali, a small-town girl from Iowa, is about to change dramatically. Hired by Tess as a waitress at the Lounge, Ali escapes a hollow past and quickly falls in love with the art of burlesque. Backed by newfound friends amongst the theater's crew, she manages to fulfill her dreams of being on stage herself. Things take a dramatic turn though when Ali's big voice makes her become the main attraction of the revue.", "Cher, Christina Aguilera, Alan Cumming", "Steve Antin", ", ", ", ");
			mBurlesque.setGenres(l3);
			movies.add(mBurlesque);
			Movie mCars2 = new Movie("Cars 2", 5.99, 2, 2011, "Cars_2.jpg", "112 min", "Star race car Lightning McQueen and his pal Mater head overseas to compete in the World Grand Prix race. But the road to the championship becomes rocky as Mater gets caught up in an intriguing adventure of his own: international espionage.", "Owen Wilson, Larry the Cable Guy, Michael Caine", "N/A", ", ", ", ");
			mCars2.setGenres(l4);
			movies.add(mCars2);
			Movie mCars = new Movie("Cars", 3.99, 3, 2006, "Cars.jpg", "117 min", "While traveling to California for the dispute of the final race of the Piston Cup against The King and Chick Hicks, the famous Lightning McQueen accidentally damages the road of the small town Radiator Springs and is sentenced to repair it. Lightning McQueen has to work hard and finds friendship and love in the simple locals, changing its values during his stay in the small town and becoming a true winner.", "Owen Wilson, Bonnie Hunt, Paul Newman", "N/A", ", ", ", ");
			mCars.setGenres(l5);
			movies.add(mCars);
			Movie mCenturion = new Movie("Centurion", 5.99, 4, 2010, "Centurion.jpg", "97 min", "Britain, A.D. 117. Quintus Dias, the sole survivor of a Pictish raid on a Roman frontier fort, marches north with General Virilus' legendary Ninth Legion, under orders to wipe the Picts from the face of the Earth and destroy their leader, Gorlacon.", "Michael Fassbender, Dominic West, Olga Kurylenko", "Neil Marshall", ", ", ", ");
			mCenturion.setGenres(l2);
			movies.add(mCenturion);
			Movie mClashOfTheTitans = new Movie("Clash of the Titans", 3.99, 3, 2010, "Clash_of_the_Titans.jpg", "106 min", "The mortal son of the god Zeus embarks on a perilous journey to stop the underworld and its minions from spreading their evil to Earth as well as the heavens.", "Sam Worthington, Liam Neeson, Ralph Fiennes", "Louis Leterrier", ", ", ", ");
			mClashOfTheTitans.setGenres(l1);
			movies.add(mClashOfTheTitans);
			Movie mDespicableMe = new Movie("Despicable Me", 2.99, 2, 2010, "Despicable_Me.jpg", "95 min", "In a happy suburban neighborhood surrounded by white picket fences with flowering rose bushes, sits a black house with a dead lawn. Unbeknownst to the neighbors, hidden beneath this home is a vast secret hideout. Surrounded by a small army of minions, we discover Gru, planning the biggest heist in the history of the world. He is going to steal the moon. (Yes, the moon) Gru delights in all things wicked. Armed with his arsenal of shrink rays, freeze rays, and battle-ready vehicles for land and air, he vanquishes all who stand in his way. Until the day he encounters the immense will of three little orphaned girls who look at him and see something that no one else has ever seen: a potential Dad. The world's greatest villain has just met his greatest challenge: three little girls named Margo, Edith and Agnes.", "Steve Carell, Jason Segel, Russell Brand", "Russell Brand", ", ", ", ");
			mDespicableMe.setGenres(l3);
			movies.add(mDespicableMe);
			Movie mDueDate = new Movie("Due Date", 3.99, 2, 2011, "Due_Date.jpg", "95 min", "Peter Highman (Robert Downey Jr.) must get to LA in five days to be at the birth of his firstborn. He is about to fly home from Atlanta when his luggage and wallet are sent to LA without him, and he is put on the \"no-fly\" list. Desperate to get home Peter is forced to accept the offer of Ethan Tremblay (Zach Galifianakis) to hitch a ride with him cross-country. Peter is about to go on the most terrifying and agonizing journey of his life.", "Robert Downey Jr., Zach Galifianakis, Michelle Monaghan", "Todd Phillips", ", ", ", ");
			mDueDate.setGenres(l4);
			movies.add(mDueDate);
			Movie mFairGame = new Movie("Fair Game", 5.99, 4, 2010, "Fair_Game.jpg", "108 min", "Plame's status as a CIA agent was revealed by White House officials allegedly out to discredit her husband after he wrote a 2003 New York Times op-ed piece saying that the Bush administration had manipulated intelligence about weapons of mass destruction to justify the invasion of Iraq. ", "Naomi Watts, Sean Penn, Sonya Davison", "Doug Liman", ", ", ", ");
			mFairGame.setGenres(l1);
			movies.add(mFairGame);
			Movie mFastFive = new Movie("Fast Five", 3.99, 1, 2011, "Fast_Five.jpg", "130 min", "Former cop Brian O'Conner partners with ex-con Dom Toretto on the opposite side of the law. Since Brian and Mia Toretto broke Dom out of custody, they've blown across many borders to elude authorities. Now backed into a corner in Rio de Janeiro, they must pull one last job in order to gain their freedom. As they assemble their elite team of top racers, the unlikely allies know their only shot of getting out for good means confronting the corrupt businessman who wants them dead. But he's not the only one on their tail. Hard-nosed federal agent Luke Hobbs never misses his target. When he is assigned to track down Dom and Brian, he and his strike team launch an all-out assault to capture them. But as his men tear through Brazil, Hobbs learns he can't separate the good guys from the bad. Now, he must rely on his instincts to corner his prey... before someone else runs them down first.", "Vin Diesel, Paul Walker, Dwayne Johnson", "Justin Lin", ", ", ", ");
			mFastFive.setGenres(l5);
			movies.add(mFastFive);
			Movie mFindingNemo = new Movie("Finding Nemo", 3.99, 3, 2006, "Finding_Nemo.jpg", " min", "A clown fish named Marlin living in the Great Barrier Reef loses his son, Nemo, after he ventures into the open sea, despite his father's constant warnings about many of the ocean's dangers. Nemo is abducted by a boat and netted up and sent to a dentist's office in Sydney. So, while Marlin ventures off to try to retrieve Nemo, Marlin meets a fish named Dory, a blue tang suffering from short-term memory loss. The companions travel a great distance, encountering various dangerous sea creatures such as sharks, anglerfish and jellyfish, in order to rescue Nemo from the dentist's office, which is situated by Sydney Harbor. While the two are doing this, Nemo and the other sea animals in the dentist's fish tank plot a way to return to Sydney Harbor to live their lives free again.", "Albert Brooks, Ellen DeGeneres, Alexander Gould", "Albert Brooks", ", ", ", ");
			mFindingNemo.setGenres(l1);
			movies.add(mFindingNemo);
			Movie mForrestGump = new Movie("Forrest Gump", 2.99, 4, 1994, "Forrest_Gump.jpg", "142 min", "Forrest, Forrest Gump is a simple man with little brain activity but good intentions. He struggles through childhood with his best and only friend Jenny. His 'mama' teaches him the ways of life and leaves him to choose his destiny. Forrest joins the army for service in Vietnam, finding new friends called Dan and Bubba, he wins medals, starts a ping-pong craze, creates a famous shrimp fishing fleet, inspires people to jog, create the smiley, write bumper stickers and songs, donating to people and meeting the president several times. However this is all irrelevant to Forrest who can only think of his childhood sweetheart Jenny. Who has messed up her life. Although in the end all he wants to prove is that anyone can love anyone.", "Tom Hanks, Robin Wright, Gary Sinise", "Robert Zemeckis", ", ", ", ");
			mForrestGump.setGenres(l2);
			movies.add(mForrestGump);
			Movie mFullMetalJacket = new Movie("Full Metal Jacket", 1.99, 3, 1987, "Full_Metal_Jacket.jpg", "116 min", "A two-segment look at the effect of the military mindset and war itself on Vietnam era Marines. The first half follows a group of recruits in basic training under the command of the punishing Sgt. Hartman. The second half shows one of those recruits, Joker, covering the war as a correspondent for Stars and Stripes, focusing on the Tet offensive.", "Matthew Modine, R. Lee Ermey, Vincent D'Onofrio", "Stanley Kubrick", ", ", ", ");
			mFullMetalJacket.setGenres(l3);
			movies.add(mFullMetalJacket);
			Movie mGladiator = new Movie("Gladiator", 3.99, 5, 2000, "Gladiator.jpg", "155 min", "Maximus is a powerful Roman general, loved by the people and the aging Emperor, Marcus Aurelius. Before his death, the Emperor chooses Maximus to be his heir over his own son, Commodus, and a power struggle leaves Maximus and his family condemned to death. The powerful general is unable to save his family, and his loss of will allows him to get captured and put into the Gladiator games until he dies. The only desire that fuels him now is the chance to rise to the top so that he will be able to look into the eyes of the man who will feel his revenge.", "Russell Crowe, Joaquin Phoenix, Connie Nielsen", "Ridley Scott", ", ", ", ");
			mGladiator.setGenres(l4);
			movies.add(mGladiator);
			Movie mGoodWillHunting = new Movie("Good Will Hunting", 2.99, 5, 1997, "Good_Will_Hunting.jpg", "126 min", "A janitor at MIT, Will Hunting has a gift for math and chemistry that can take him light-years beyond his blue-collar roots, but he doesn't realize his potential and can't even imagine leaving his childhood Boston South End neighborhood, his construction job, or his best friend. To complicate matters, several strangers enter the equation: a brilliant math professor who discovers, even envies, Will's gifts, an empathetic shrink who identifies with Will's blue-collar roots, and a beautiful, gifted pre-med student who shows him, for the first time in his life, the possibility of love.", "Robin Williams, Matt Damon, Ben Affleck", "Gus Van Sant", ", ", ", ");
			mGoodWillHunting.setGenres(l5);
			movies.add(mGoodWillHunting);
			Movie mInBruges = new Movie("In Bruges", 3.99, 2, 2009, "In_Bruges.jpg", "98 min", "London based hit men Ray and Ken are told by their boss Harry Waters to lay low in Bruges, Belgium for up to two weeks following their latest hit, which resulted in the death of an innocent bystander. Harry will be in touch with further instructions. While they wait for Harry's call, Ken, following Harry's advice, takes in the sights of the medieval city with great appreciation. But the charms of Bruges are lost on the simpler Ray, who is already despondent over the innocent death, especially as it was his first job. Things change for Ray when he meets Chloe, part of a film crew shooting a movie starring an American dwarf named Jimmy. When Harry's instructions arrive, Ken, for who the job is directed, isn't sure if he can carry out the new job, especially as he has gained a new appreciation of life from his stay in the fairytale Bruges. While Ken waits for the inevitable arrival into Bruges of an angry Harry...", "Colin Farrell, Brendan Gleeson, Elizabeth Berrington", "Martin McDonagh", ", ", ", ");
			mInBruges.setGenres(l2);
			movies.add(mInBruges);
			Movie mInception = new Movie("Inception", 3.99, 4, 2010, "Inception.jpg", "148 min", "Dom Cobb is a skilled thief, the absolute best in the dangerous art of extraction, stealing valuable secrets from deep within the subconscious during the dream state, when the mind is at its most vulnerable. Cobb's rare ability has made him a coveted player in this treacherous new world of corporate espionage, but it has also made him an international fugitive and cost him everything he has ever loved. Now Cobb is being offered a chance at redemption. One last job could give him his life back but only if he can accomplish the impossible-inception. Instead of the perfect heist, Cobb and his team of specialists have to pull off the reverse: their task is not to steal an idea but to plant one. If they succeed, it could be the perfect crime. But no amount of careful planning or expertise can prepare the team for the dangerous enemy that seems to predict their every move. An enemy that only Cobb could have seen coming.", "Leonardo DiCaprio, Joseph Gordon-Levitt, Ellen Page", "Christopher Nolan", ", ", ", ");
			mInception.setGenres(l3);
			movies.add(mInception);
			Movie mInglouriousBasterds = new Movie("Inglourious Basterds", 4.99, 5, 2009, "Inglourious_Basterds.jpg", "153 min", "In Nazi occupied France, young Jewish refugee Shosanna Dreyfus witnesses the slaughter of her family by Colonel Hans Landa. Narrowly escaping with her life, she plots her revenge several years later when German war hero Fredrick Zoller takes a rapid interest in her and arranges an illustrious movie premiere at the theater she now runs. With the promise of every major Nazi officer in attendance, the event catches the attention of the \"Basterds\", a group of Jewish-American guerilla soldiers led by the ruthless Lt. Aldo Raine. As the relentless executioners advance and the conspiring young girl's plans are set in motion, their paths will cross for a fateful evening that will shake the very annals of history.", "Brad Pitt, Diane Kruger, Eli Roth", "Quentin Tarantino", ", ", ", ");
			mInglouriousBasterds.setGenres(l5);
			movies.add(mInglouriousBasterds);
			Movie mIronMan = new Movie("Iron Man", 3.99, 2, 2008, "Iron_Man.jpg", "126 min", "Tony Stark is the complete playboy who also happens to be an engineering genius. While in Afghanistan demonstrating a new missile, he's captured and wounded. His captors want him to assemble a missile for them but instead he creates an armored suit and a means to prevent his death from the shrapnel left in his chest by the attack. He uses the armored suit to escape. Back in the U.S. he announces his company will cease making weapons and he begins work on an updated armored suit only to find that Obadiah Stane, his second in command at Stark industries has been selling Stark weapons to the insurgents. He uses his new suit to return to Afghanistan to destroy the arms and then to stop Stane from misusing his research.", "Robert Downey Jr., Gwyneth Paltrow, Terrence Howard", "Jon Favreau", ", ", ", ");
			mIronMan.setGenres(l2);
			movies.add(mIronMan);
			Movie mJustGoWithIt = new Movie("Just Go with It", 3.49, 1, 2011, "Just_Go_with_It.jpg", "117 min", "Danny (Adam Sandler) must engage Katherine (Jennifer Aniston), his faithful assistant, to pretend to be his soon to be ex-wife. Danny must pretend that he is married, because he lied to his dream girl, Palmer (Brooklyn Decker) the most gorgeous woman in the world. To keep the woman he loves, covering up one lie soon turns into many lies", "Adam Sandler, Jennifer Aniston, Brooklyn Decker", "Dennis Dugan", ", ", ", ");
			mJustGoWithIt.setGenres(l4);
			movies.add(mJustGoWithIt);
			Movie mLondonBoulevard = new Movie("London Boulevard", 4.54, 2, 2010, "London_Boulevard.jpg", "110 min", "Mitchel (Colin Farrell) just got out of jail and wants to stay legitimate but his friends involved in the messy London underground fear him and wants him to join them again but Mitchel tries his best to stay away. He gets himself a job as a bodyguard for a retired actress Charlotte (Keira Knightley) who is still hot news for the paparazzi. Mitchell, through his friend Billy eventually meets the underground Don by the name of Gant (Ray Winstone) who wants Mitch to work for him because of Mitch's reputations. While working together Mitch and Charlotte fall in love. Gant asks Billy to get the guy who sent Mitch to jail but it turns out to be the wrong one, Gant kills the guy in front of Mitch and lets Mitch know that he has to work for him now that Mitch has seen him commit the murder but Mitch refuses. Gant keeps trying to force Mitch to his side promising him good ranks and positions... ", "Colin Farrell, Keira Knightley, Ray Winstone", "William Monahan", ", ", ", ");
			mLondonBoulevard.setGenres(l2);
			movies.add(mLondonBoulevard);
			Movie mMonsters = new Movie("Monsters", 2.99, 2, 2010, "Monsters.jpg", "94 min", "Six years ago NASA discovered the possibility of alien life within our solar system. A probe was launched to collect samples, but crashed upon re-entry over Central America. Soon after, new life form began to appear and half of Mexico was quarantined as an INFECTED ZONE. Today, the American and Mexican military still struggle to contain \"the creatures\"...... Our story begins when a US journalist agrees to escort a shaken tourist through the infected zone in Mexico to the safety of the US border.", "Scoot McNairy, Whitney Able", "Gareth Edwards", ", ", ", ");
			mMonsters.setGenres(l3);
			movies.add(mMonsters);
			Movie mMorningGlory = new Movie("Morning Glory", 4.99, 2, 2010, "Morning_Glory.jpg", "107 min", "Becky (Rachel McAdams) is a hard-working morning TV show producer, or at least she was until she got fired. Desperate to get a job, she finally gets an interview with Jerry (Jeff Goldblum) - who is desperate to hire a producer for the struggling show \"Daybreak\". Becky accepts the job and it proves to be more difficult than even she might be able to handle. She has to fire the sexist co-host, then try to convince egotistical news reporter, Mike Pomeroy (Harrison Ford), to take the job, and then try and get him to actually do the job, properly. And she has to do this while falling for handsome Adam (Patrick Wilson), and trying to save the show from plummeting ratings. Will Becky be able to hold on to her dream job and her sanity?", "Rachel McAdams, Harrison Ford, Diane Keaton", "Roger Michell", ", ", ", ");
			mMorningGlory.setGenres(l2);
			movies.add(mMorningGlory);
			Movie mNeverLetMeGo = new Movie("Never Let Me Go", 1.99, 2, 2010, "Never_Let_Me_Go.jpg", "103 min", "As children, Ruth, Kathy and Tommy, spend their childhood at a seemingly idyllic English boarding school. As they grow into young adults, they find that they have to come to terms with the strength of the love they feel for each other, while preparing themselves for the haunting reality that awaits them.", "Keira Knightley, Carey Mulligan, Andrew Garfield", "Mark Romanek", ", ", ", ");
			mNeverLetMeGo.setGenres(l4);
			movies.add(mNeverLetMeGo);
			Movie mPredators = new Movie("Predators", 3.29, 2, 2010, "Predators.jpg", "107 min", "Chosen for their ability to kill without conscience, a group of killers, some trained and some who are not, must endeavour the alien race of predators that have set out to target them as prey. Dropped into the vast jungle of a distant world, these human predators must learn just who, or what, they are up against, and that their ability, knowledge and wits are tested to the limits in the battle of survival of kill or be killed.", "Adrien Brody, Laurence Fishburne, Topher Grace", "Nimrod Antal", ", ", ", ");
			mPredators.setGenres(l3);
			movies.add(mPredators);
			Movie mPriest = new Movie("Priest", 2.99, 2, 2011, "Priest.jpg", "87 min", "PRIEST, a post-apocalyptic sci-fi thriller, is set in an alternate world -- one ravaged by centuries of war between man and vampires. The story revolves around a legendary Warrior Priest from the last Vampire War who now lives in obscurity among the other downtrodden human inhabitants in walled-in dystopian cities ruled by the Church. When his niece is abducted by a murderous pack of vampires, Priest breaks his sacred vows to venture out on a quest to find her before they turn her into one of them. He is joined on his crusade by his niece's boyfriend, a trigger-fingered young wasteland sheriff, and a former Warrior Priestess who possesses otherworldly fighting skills.", "Paul Bettany, Cam Gigandet, Maggie Q", "Scott Charles Stewart", ", ", ", ");
			mPriest.setGenres(l5);
			movies.add(mPriest);
			Movie mRedRidingHood = new Movie("Red Riding Hood", 3.29, 2, 2011, "Red_Riding_Hood.jpg", "100 min", "Valerie (Seyfried) is a beautiful young woman torn between two men. She is in love with a brooding outsider, Peter (Fernandez), but her parents have arranged for her to marry the wealthy Henry (Irons). Unwilling to lose each other, Valerie and Peter are planning to run away together when they learn that Valerie's older sister has been killed by the werewolf that prowls the dark forest surrounding their village. For years, the people have maintained an uneasy truce with the beast, offering the creature a monthly animal sacrifice. But under a blood red moon, the wolf has upped the stakes by taking a human life. Hungry for revenge, the people call on famed werewolf hunter, Father Solomon (Oldman), to help them kill the wolf. But Solomon's arrival brings unintended consequences as he warns that the wolf, who takes human form by day, could be any one of them. As the death toll rises with each moon...", "Amanda Seyfried, Lukas Haas, Gary Oldman", "Catherine Hardwicke", ", ", ", ");
			mRedRidingHood.setGenres(l1);
			movies.add(mRedRidingHood);
			Movie mRio = new Movie("Rio", 3.99, 2, 2011, "Rio.jpg", "96 min", "When Blu, a domesticated macaw from small-town Minnesota, meets the fiercely independent Jewel, he takes off on an adventure to Rio de Janeiro with this bird of his dreams.", "Jesse Eisenberg, Anne Hathaway, George Lopez", "Carlos Saldanha", ", ", ", ");
			mRio.setGenres(l3);
			movies.add(mRio);
			Movie mRobinHood = new Movie("Robin Hood", 1.99, 2, 2010, "Robin_Hood.jpg", "140 min", "Birth of a legend. Following King Richard's death in France, archer Robin Longstride, along with Will Scarlett, Alan-a-Dale and Little John, returns to England. They encounter the dying Robert of Locksley, whose party was ambushed by treacherous Godfrey, who hopes to facilitate a French invasion of England. Robin promises the dying knight he will return his sword to his father Walter in Nottingham. Here Walter encourages him to impersonate the dead man to prevent his land being confiscated by the crown, and he finds himself with Marian, a ready-made wife. Hoping to stir baronial opposition to weak King John and allow an easy French take-over, Godfrey worms his way into the king's service as Earl Marshal of England and brutally invades towns under the pretext of collecting Royal taxes. Can Robin navigate the politics of barons, royals, traitors, and the French?", "Russell Crowe, Cate Blanchett, Matthew Macfadyen", "Ridley Scott", ", ", ", ");
			mRobinHood.setGenres(l1);
			movies.add(mRobinHood);
			Movie mSourceCode = new Movie("Source Code", 3.99, 2, 2011, "Source_Code.jpg", "93 min", "Colter Stevens, an American Army helicopter pilot whose last memory is flying in Afghanistan, wakes up on a commuter train. However, he discovers that he has assumed the identity of another man. 8 minutes later, the train explodes and Stevens finds himself in some kind of pod. He then talks to someone named Goodwin, who tells him he has to go back and find out who the bomber is. He is sent back to go through the whole thing again and attempts to find who the bomber is but fails. The bomb goes off and Stevens finds himself in the pod again. He is sent back another time, yet still cannot find out anything. When he returns, he asks what is going on. Goodwin and Rutledge, the scientist in charge, tell him that he is part of a project that can put someone in another person's consciousness during the last 8 minutes of their life. Stevens then asks why he cannot just stop the bomb.", "Jake Gyllenhaal, Michelle Monaghan, Vera Farmiga", "Duncan Jones", ", ", ", ");
			mSourceCode.setGenres(l4);
			movies.add(mSourceCode);
			Movie mStarTrek = new Movie("Star Trek", 3.99, 2, 2009, "Star_Trek.jpg", "120 min", "On the day of James Kirk's birth, his father dies on his ship in a last stand against a mysterious alien vessel. He was looking for Ambassador Spock, who is a child on Vulcan at that time, disdained by his neighbors for his half-human nature. Twenty years later, Kirk has grown into a young troublemaker inspired by Capt. Christopher Pike to fulfill his potential in Starfleet even as he annoys his instructors like young Cmdr. Spock. Suddenly, there is an emergency at Vulcan and the newly commissioned USS Enterprise is crewed with promising cadets like Nyota Uhura, Hikaru Sulu, Pavel Chekov and even Kirk himself thanks to Leonard McCoy's medical trickery. Together, this crew will have an adventure in the final frontier where the old legend is altered forever even as the new version of it is just beginning.", "Chris Pine, Zachary Quinto, Simon Pegg", "J.J. Abrams", ", ", ", ");
			mStarTrek.setGenres(l3);
			movies.add(mStarTrek);
			Movie mAdjustmentBureau = new Movie("Adjustment Bureau", 3.99, 2, 2011, "The_Adjustment_Bureau.jpg", "106 min", "Do we control our destiny, or do unseen forces manipulate us? A man glimpses the future Fate has planned for him and realizes he wants something else. To get it, he must pursue across, under and through the streets of modern-day New York the only woman he's ever loved. On the brink of winning a seat in the U.S. Senate, ambitious politician David Norris (Damon) meets beautiful contemporary ballet dancer Elise Sellas (Emily Blunt) - a woman like none he's ever known. But just as he realizes he's falling for her, mysterious men conspire to keep the two apart. David learns he is up against the agents of Fate itself - the men of The Adjustment Bureau - who will do everything in their considerable power to prevent David and Elise from being together. In the face of overwhelming odds, he must either let her go and accept a predetermined path...or risk everything to defy Fate and be with her.", "Matt Damon, Emily Blunt, Florence Kastriner", "George Nolfi", ", ", ", ");
			mAdjustmentBureau.setGenres(l2);
			movies.add(mAdjustmentBureau);
			Movie mTheAmerican = new Movie("The American", 5.99, 2, 2010, "The_American.jpg", "105 min", "Alone among assassins, Jack is a master craftsman. When a job in Sweden ends more harshly than expected for this American abroad, he vows to his contact Pavel that his next assignment will be his last. Jack reports to the Italian countryside, where he holes up in a small town and relishes being away from death for a spell. The assignment, as specified by a Belgian woman, Mathilde, is in the offing as a weapon is constructed. Surprising himself, Jack seeks out the friendship of local priest Father Benedetto and pursues romance with local woman Clara. But by stepping out of the shadows, Jack may be tempting fate.", "George Clooney, Paolo Bonacelli, Violante Placido", "Anton Corbijn", ", ", ", ");
			mTheAmerican.setGenres(l4);
			movies.add(mTheAmerican);
			Movie mTransformers = new Movie("Transformers", 4.99, 2, 2009, "Transformers__Revenge_of_the_Fallen.jpg", "150 min", "A youth chooses manhood. The week Sam Witwicky starts college, the Decepticons make trouble in Shanghai. A presidential envoy believes it's because the Autobots are around; he wants them gone. He's wrong: the Decepticons need access to Sam's mind to see some glyphs imprinted there that will lead them to a fragile object that, when inserted in an alien machine hidden in Egypt for centuries, will give them the power to blow out the sun. Sam, his girlfriend Mikaela, and Sam's parents are in danger. Optimus Prime and Bumblebee are Sam's principal protectors. If one of them goes down, what becomes of Sam?", "Shia LaBeouf, Megan Fox, Josh Duhamel", "Michael Bay", ", ", ", ");
			mTransformers.setGenres(l5);
			movies.add(mTransformers);
			movieRepository.save(movies);
	
			Authorities auUser = new Authorities();
			auUser.setAuthority("ROLE_USER");
			
			Authorities auAdmin = new Authorities();
			auAdmin.setAuthority("ROLE_ADMIN");
			
			User uUser = new User();
			Set<Authorities> aAuthoritieses = new HashSet<Authorities>();
			aAuthoritieses.add(auUser);
			uUser.setAuthoritieses(aAuthoritieses);
			uUser.setEnabled(true);
			uUser.setPassword("keri");
			uUser.setUsername("keri");
			uUser = userRepository.save(uUser);
			
			User uAdmin = new User();
			Set<Authorities> uAuthoritieses = new HashSet<Authorities>();
			uAuthoritieses.add(auAdmin);
			uAuthoritieses.add(auUser);
			uAdmin.setAuthoritieses(uAuthoritieses);
			uAdmin.setEnabled(true);
			uAdmin.setPassword("admin");
			uAdmin.setUsername("admin");
			uAdmin = userRepository.save(uAdmin);
			
			Account acc = new Account();
			acc.setTotal(50.00);
			
			VideoStoreMember vsm = new VideoStoreMember();
			vsm.setAccount(acc);
			vsm.setLocation("Cork");
			vsm.setMemebershipNumber("0100101001");
			vsm.setName("Keri");
			vsm.setUser(uUser);
			
			videoStoreMemberRepository.save(vsm);
		} catch (MongoException me) {
			LOG.error(me, me);
		}
	}

}
