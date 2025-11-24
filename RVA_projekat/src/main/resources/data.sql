insert into turisticka_agencija (id, adresa, kontakt, naziv)
values
	(nextval('turisticka_agencija_seq'), 'Doza Djerdja 16', '066980115', 'Sun Turisticka Agencija'),
	(nextval('turisticka_agencija_seq'), 'Mise Dimitrijevica 8', '065947475', 'Moon Turisticka Agencija'),
	(nextval('turisticka_agencija_seq'), 'Puskinova 43', '0666061465', 'Mars Turisticka Agencija'),
	(nextval('turisticka_agencija_seq'), 'Gogoljeva 12', '066953175', 'Jupiter Turisticka Agencija');
	
insert into destinacija (id, drzava, mesto, opis)
values
	(nextval('destinacija_seq'), 'Republika Srpska', 'Gacko', 'Malo mjesto puno dobrih ljudi na 1000 metara nadmorske visine pruza nezaboravan dozivljaj za sve svoje posjetioce'),
	(nextval('destinacija_seq'), 'Republika Srpska', 'Bileca', 'Malo mjesto puno dobrih ljudi na 600 metara nadmorske visine pruza nezaboravan dozivljaj za sve svoje posjetioce'),
	(nextval('destinacija_seq'), 'Srbija', 'Priboj', 'Malo mjesto puno dobrih ljudi na 850 metara nadmorske visine pruza nezaboravan dozivljaj za sve svoje posjetioce'),
	(nextval('destinacija_seq'), 'Italija', 'Rovereto', 'Malo mjesto puno dobrih ljudi na 400 metara nadmorske visine pruza nezaboravan dozivljaj za sve svoje posjetioce');
	
insert into hotel(id, naziv, broj_zvezdica, opis, destinacija)
values
	(nextval('hotel_seq'), 'Hotel Metohija', 4, 'Hotel sa 4 zvjezdice najveci i jedini u gradu pruzice sve sta vam je neophodno za ugodan boravak u nasem gradu', 1),
	(nextval('hotel_seq'), 'Hotel Dijamant', 4, 'Hotel sa 4 zvjezdice pruzice sve sta vam je neophodno za ugodan boravak u nasem gradu ukljucujuci parking i obroke', 2),
	(nextval('hotel_seq'), 'Hotel Lim', 5, 'Hotel sa 5 zvjezdica najveci u gradu pruzice sve sta vam je neophodno za ugodan boravak u nasem gradu hvala vam', 3),
	(nextval('hotel_seq'), 'Hotel Rovereto', 5, 'Hotel sa 5 zvjezdica najveci u gradu pruzice sve sta vam je neophodno za ugodan boravak u nasem gradu', 4);
	
insert into aranzman(id, ukupna_cena, placeno, datum_realizacije, hotel, agencija)
values
	(nextval('aranzman_seq'), 450, true, to_date('11.12.2023', 'dd.mm.yyyy.'), 1, 2),
	(nextval('aranzman_seq'), 550, true, to_date('10.09.2023', 'dd.mm.yyyy.'), 2, 1),
	(nextval('aranzman_seq'), 650, true, to_date('09.03.2023', 'dd.mm.yyyy.'), 3, 4),
	(nextval('aranzman_seq'), 850, false, to_date('08.11.2022', 'dd.mm.yyyy.'), 4, 3);