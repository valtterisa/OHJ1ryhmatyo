-- README
/*
SUORITA KOKO TIEDOSTO VIIVAN ALAPUOLELTA LÄHTIEN.
*/

/*
-----------------------------------------------------------------------------------------------------
*/

-- ASETUKSIA 

set foreign_key_checks = 0;

-- NÄKYMIÄ

CREATE VIEW customersWithReservation AS
SELECT asiakas.asiakas_id, postinro, etunimi, sukunimi, lahiosoite, email, puhelinnro from asiakas
JOIN varaus
ON asiakas.asiakas_id = varaus.asiakas_id;

CREATE VIEW customersByPostnumber AS
SELECT asiakas_id, asiakas.postinro, etunimi, sukunimi, lahiosoite, email, puhelinnro from asiakas
JOIN posti
ON asiakas.postinro = posti.postinro;

-- ASIAKAS

INSERT INTO asiakas (asiakas_id, postinro, etunimi, sukunimi, lahiosoite, email, puhelinnro)
VALUES
  (2,"22222","Juha","Korhonen","HAHAHHAA H 12","juhak@gmail.com","0446713449"),
  (21, "96446","Ignatius","surname","Ap #217-9579 Sed Street","odio.aliquam.vulputate@icloud.org","052-6618695"),
  (22, "18254","Zelenia","surname","Ap #146-2469 Non Rd.","phasellus.libero@yahoo.net","691-4780678"),
  (3, "95393","Aphrodite","surname","P.O. Box 546, 3855 Aliquam Ave","ante.nunc.mauris@protonmail.com","186-4603044"),
  (4, "45802","Darrel","surname","261-760 Mus. Av.","diam.at@icloud.org","084-5646753"),
  (5, "12107","Orli","surname","520-7509 Ipsum Rd.","mi@outlook.couk","295-4023771"),
  (6, "82678","Portia","surname","800-8129 Eget Road","est@aol.ca","664-0881147"),
  (7, "03411","Jakeem","surname","Ap #623-4425 Penatibus Street","vitae.posuere.at@icloud.com","803-5887285"),
  (8, "77061","Elijah","surname","664-4881 Lacinia St.","nulla.tempor@google.com","312-6507734"),
  (9, "53326","Chandler","surname","893-217 Lacus. Av.","dapibus.quam@outlook.com","623-5635019"),
  (10,"32283","Ivan","surname","Ap #129-458 In St.","ultrices.a.auctor@aol.org","410-8922386"),
  (11,"37148","Chantale","surname","375-563 Ipsum. Avenue","tristique.pellentesque@hotmail.ca","442-5233949"),
  (12,"61741","Samuel","surname","408-2467 A, Avenue","lectus.nullam.suscipit@google.ca","837-3237252"),
  (13,"32326","Oprah","surname","P.O. Box 227, 7755 Et Rd.","congue.turpis@hotmail.couk","362-2583884"),
  (14,"51967","Lana","surname","P.O. Box 285, 1108 Lacus Ave","felis.adipiscing.fringilla@outlook.edu","628-0548424"),
  (15,"48431","Griffin","surname","711-500 Euismod Av.","nulla.tincidunt@yahoo.net","642-7122058"),
  (16,"41773","Rina","surname","P.O. Box 838, 3748 Nunc Ave","vitae.velit.egestas@outlook.ca","647-3241816"),
  (17,"83867","Evelyn","surname","911-3712 Phasellus St.","velit@hotmail.couk","847-2933824"),
  (18,"43692","Camille","surname","166-1683 Cum Street","dolor@hotmail.com","875-6849446"),
  (19,"41018","Anika","surname","7510 Eu St.","non.leo.vivamus@google.ca","656-3635760"),
  (20,"64157","Owen","surname","317 Vitae Rd.","netus@yahoo.edu","248-1272939");

-- POSTI

insert into posti (postinro,toimipaikka) 
VALUES
  ("96446","Jyväskylä"),
  ("18254","Järvenpää"),
  ("95393","Sievi"),
  ("45802","Kokkola"),
  ("12107","Kuhmo"),
  ("82678","Kouvola"),
  ("03411","Mikkeli"),
  ("77061","Turku"),
  ("53326","Suomussalmi"),
  ("32283","Kittilä"),
  ("37148","Vaasa"),
  ("61741","Joensuu"),
  ("32326","Rovaniemi"),
  ("51967","Oulu"),
  ("48431","Lappeenranta"),
  ("41773","Helsinki"),
  ("83867","Tampere"),
  ("43692","Kuopio"),
  ("41018","Sotkamo"),
  ("64157","Nurmes"),
  ("22222","Kajaani");

-- VARAUS

  insert into varaus (varaus_id, asiakas_id, mokki_mokki_id, varattu_pvm, vahvistus_pvm, varattu_alkupvm, varattu_loppupvm) 
VALUES
  (1,2,43,"2022-02-02 00:00:00","2022-02-03 00:00:00","2022-03-04 00:00:00","2022-03-12 00:00:00"),
  (13,21,13,"2021-12-20 04:42:54","2022-06-28 22:33:01","2023-02-11 11:24:21","2021-09-19 15:03:01"),
  (23,22,10,"2023-03-28 10:17:26","2023-04-14 20:15:27","2021-05-14 08:33:55","2023-01-17 00:20:16"),
  (12,3 ,18,"2022-04-18 21:23:09","2023-03-21 06:30:39","2021-06-29 11:27:45","2022-11-01 06:56:09"),
  (16,4 ,19,"2022-12-24 09:15:35","2022-12-28 18:52:46","2022-01-20 00:46:48","2022-11-12 13:00:24"),
  (19,5 ,13,"2021-07-08 07:30:08","2022-03-04 02:31:16","2022-05-30 07:08:08","2021-09-14 15:47:24"),
  (21,6 ,2,"2022-03-13 04:20:55","2022-08-29 04:55:06","2023-01-07 00:26:39","2022-10-29 00:34:37"),
  (22,7 ,5,"2021-06-14 04:59:45","2022-12-10 03:29:36","2022-09-25 13:43:14","2021-05-08 13:40:57"),
  (24,8 ,15,"2023-02-09 17:22:23","2023-02-17 10:54:10","2022-05-09 19:29:45","2021-09-17 12:19:08"),
  (3,9 ,17,"2022-04-02 09:31:59","2022-10-09 01:22:50","2021-06-07 06:31:42","2022-12-29 03:57:18"),
  (9 ,10,14,"2022-12-06 00:40:27","2022-12-14 16:45:47","2021-10-03 22:31:35","2021-05-09 12:37:07"),
  (7, 11,13,"2021-10-21 17:23:29","2021-08-09 13:51:23","2022-12-13 08:58:27","2022-10-11 16:02:05"),
  (18,12,17,"2023-01-03 22:51:03","2023-03-13 18:39:41","2023-01-10 03:24:36","2023-01-30 18:39:36"),
  (14,13,6,"2022-08-01 03:21:41","2022-06-09 11:04:51","2022-11-22 21:11:27","2022-05-01 13:09:13"),
  (17,14,5,"2022-06-27 11:59:01","2022-10-30 16:36:45","2022-12-17 21:00:28","2022-07-23 14:58:30"),
  (20,15,11,"2022-01-05 08:07:13","2021-08-27 03:09:26","2022-02-14 12:57:40","2022-02-17 12:02:28"),
  (15,16,17,"2022-12-06 17:59:00","2023-02-01 11:54:58","2023-04-26 01:55:18","2022-04-09 15:29:35"),
  (5, 17,3,"2022-04-13 09:49:23","2021-06-14 07:29:25","2022-09-20 04:28:25","2023-03-01 22:44:03"),
  (4, 18,20,"2023-04-15 19:57:38","2021-10-26 11:56:17","2023-02-03 19:23:14","2022-01-18 22:38:47"),
  (10,19,13,"2023-03-03 02:11:34","2022-07-05 02:55:53","2021-08-24 07:09:40","2022-02-06 07:48:14"),
  (11,20,4,"2021-09-10 04:51:15","2022-10-21 17:23:49","2021-06-10 04:52:43","2022-01-17 20:17:34");
  
  
  -- MÖKKI
  
insert into mokki (mokki_id,alue_id,postinro,mokkinimi,katuosoite,hinta,kuvaus,henkilomaara,varustelu) 
  VALUES
  (43,4,"22222","Jaskan linna","Aatelinraitti 2","1899.00","Hieno mökki on tämäkin",32,"Sauna ja bilispöytä ja palju"),
  (21,8 , "96446","eune","7034 Convallis Avenue","1589.37","ac nulla. In",14,"mi pede, nonummy ut, molestie"),
  (10,20, "18254","velit","1314 Vel St.","1448.42","Donec est. Nunc",8,"penatibus et magnis dis parturient"),
  (18,16, "95393","tellus.","P.O. Box 729, 6481 Proin Rd.","1341.86","malesuada ut, sem.",14,"arcu. Sed eu nibh vulputate"),
  (19,17, "45802","eget","149-1248 Eleifend. Road","1606.14","quis lectus. Nullam",9,"Vestibulum ut eros non enim"),
  (12,12, "12107","lorem","P.O. Box 571, 4190 Egestas St.","1291.49","Quisque libero lacus,",9,"rhoncus. Donec est. Nunc ullamcorper,"),
  (2, 9 , "82678","in","P.O. Box 489, 9908 Mus. Rd.","1169.69","Nunc mauris. Morbi",11,"lacinia orci, consectetuer euismod est"),
  (15,2 , "77061","natoque","P.O. Box 152, 282 Mollis Rd.","1808.71","ac nulla. In",15,"per conubia nostra, per inceptos"),
  (7,10, "53326","mattis","324-4140 Tristique Rd.","1749.64","dolor sit amet,",15,"dolor, tempus non, lacinia at,"),
  (14,19, "32283","magnis","Ap #363-9797 Vestibulum St.","1213.45","Cum sociis natoque",12,"Phasellus dapibus quam quis diam."),
  (13,9 , "37148","Aenean","P.O. Box 482, 5036 Mauris, Ave","1396.60","vulputate, nisi sem",7,"Donec tempor, est ac mattis"),
  (25,5 , "61741","purus,","Ap #920-9168 Nisi Rd.","1159.30","lectus rutrum urna,",16,"sem mollis dui, in sodales"),
  (6, 15, "32326","Phasellus","188-174 Molestie Street","1659.48","magna. Duis dignissim",20,"rhoncus id, mollis nec, cursus"),
  (5, 4 , "51967","nunc","Ap #153-3619 Diam Road","1160.97","facilisis, magna tellus",18,"vitae diam. Proin dolor. Nulla"),
  (11,2 , "48431","felis","Ap #860-8520 Vitae, Avenue","1180.62","suscipit, est ac",15,"massa. Quisque porttitor eros nec"),
  (17,19, "41773","nisi","Ap #311-1733 Scelerisque Rd.","1865.04","velit justo nec",13,"dignissim lacus. Aliquam rutrum lorem"),
  (3, 8 , "83867","vulputate","4459 Imperdiet St.","1025.75","neque tellus, imperdiet",12,"vitae nibh. Donec est mauris,"),
  (20,18, "43692","arcu.","Ap #410-9594 Vivamus Road","1577.35","risus. Nunc ac",16,"sit amet risus. Donec egestas."),
  (22,13, "41018","amet","P.O. Box 122, 557 Sed Street","1635.99","porttitor scelerisque neque.",15,"ante blandit viverra. Donec tempus,"),
  (4, 18, "64157","dui,","540-888 Vestibulum Road","1330.04","Quisque ornare tortor",16,"massa. Integer vitae nibh. Donec");
  
  
  -- ALUE
  
    insert into alue (alue_id,nimi) 
  VALUES
  (4,"Kuusamo"),
  (8 ,"Nulla"),
  (20,"ante"),
  (16,"aliquam"),
  (17,"Cras"),
  (12,"eu"),
  (14 ,"Integer"),
  (6 ,"vitae"),
  (1 ,"dignissim"),
  (10,"Vivamus"),
  (21,"blandit"),
  (9 ,"Duis"),
  (5 ,"ligula"),
  (15,"Integer"),
  (2 ,"nulla"),
  (19,"Donec"),
  (24 ,"eu"),
  (23,"malesuada"),
  (13,"Donec"),
  (18,"molestie");

-- LASKU

INSERT INTO  lasku (lasku_id,varaus_id,summa,alv)
VALUES
  (14,13,"1466","551"),
  (5, 23,"3146","330"),
  (15,12,"2988","66"),
  (17,16,"9211","54"),
  (12,19,"399","399"),
  (6, 21,"5176","878"),
  (2,22,"1304","382"),
  (3, 24,"5119","311"),
  (1,  3,"3491","551"),
  (11, 9,"3968","271"),
  (20, 7,"7154","936"),
  (4, 18,"261","536"),
  (7,14,"8222","595"),
  (19,17,"5331","47"),
  (9, 20,"3572","930"),
  (0, 15,"1624","75"),
  (8, 5,"5943","758"),
  (10, 4,"8824","234"),
  (21,10,"5553","988"),
  (18,11,"3019","156");
  
  -- PALVELU
  
  INSERT INTO palvelu (`palvelu_id`,`alue_id`,`nimi`,`tyyppi`,`kuvaus`,`hinta`,`alv`)
VALUES
  (30,1,"pede. Suspendisse dui.",5,"adipiscing elit. Aliquam auctor, velit","779","55"),
  (28,2,"magna. Phasellus dolor",3,"Mauris quis turpis vitae purus","990","78"),
  (29,8,"amet risus. Donec egestas",2,"fringilla, porttitor vulputate, posuere vulputate,","583","30"),
  (32,20,"dolor sit amet adipiscing",1,"Sed eu nibh vulputate mauris","607","70"),
  (34,5,"vestibulum, neque",4,"In faucibus. Morbi vehicula. Pellentesque","546","79");
  
  -- VARAUKSEN PALVELUT
  
INSERT INTO varauksen_palvelut (`varaus_id`,`palvelu_id`,`lkm`)
VALUES
  (15,30,1),
  (5, 28,2),
  (4, 29,4),
  (10,32,1),
  (11,34,3),
  (13,30,2),
  (23,28,0),
  (12,29,5),
  (16,32,2),
  (19,34,2),
  (21,30,1),
  (22,28,2),
  (24,29,4),
  ( 3,32,3),
  ( 9,34,2),
  ( 7,30,0),
  (18,28,3),
  (14,29,2),
  (17,32,0),
  (20,34,1);
