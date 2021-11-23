-- Load of player

insert into player values(1,'uno@gmail.com','Uno','unito','uno','uno',1,3,1)
insert into player values(2,'dos@gmail.com','dos','dosito','dos','dos',1,3,1)
insert into player values(3,'tres@gmail.com','tres','tresito','tres','tres',1,3,1)
insert into player values(4,'cuatro@gmail.com','cuatro','cuatrito','cuatro','cuatro',1,3,1)
insert into player values(5,'cinco@gmail.com','cinco','cinquito','cinco','cinco',1,3,1)
insert into player values(6,'seis@gmail.com','seis','seisito','seis','seis',1,3,1)

-- Load of admin

insert into admin values(1,'unaco@gmail.com','Unaco','unacon','unaco','unaco',1,3,2)

-- Load of modification

insert into modification values(1, 'UNO', 'uno','2021-01-01 01:01:01','cambio1',null, 2)

-- Load of friendship

insert into friendship values(1, 1, 1, 2);

-- Load of game

insert into game values(1,'juegoUno', 1,'2021-01-01 01:01:01','2021-01-01 01:11:01',1,null)
insert into game values(2,'juegoDos',2,'2021-02-02 02:02:02','2021-02-02 02:22:02',2,null)
insert into game values(3,'juegoTres',3,'2021-03-03 03:03:03','2021-03-03 03:33:03',2,null)
insert into game values(4,'juegoCuatro',4,'2021-04-04 04:04:04','2021-04-04 04:44:04',2,null)


-- Load of role

insert into role values(1,'player')
insert into role values(2,'admin')

-- Load of privilege

insert into privilege values(1, 'VIEW_AWARDS', 1)


-- Load of player_games_played

insert into player_games_played values(1,1)
insert into player_games_played values(2,1)
insert into player_games_played values(3,1)
insert into player_games_played values(4,1)
insert into player_games_played values(5,1)
insert into player_games_played values(6,1)
insert into player_games_played values(2,2)
insert into player_games_played values(4,2)
insert into player_games_played values(6,2)
insert into player_games_played values(3,3)
insert into player_games_played values(6,3)
insert into player_games_played values(1,4)
insert into player_games_played values(3,4)
insert into player_games_played values(5,4)


-- Load of result

insert into result values(1,"Minigame1:5, Minigame2:0, Minigame3:5",10,1,1)
insert into result values(2,"Minigame1:0, Minigame2:0, Minigame3:0",0,1,2)
insert into result values(3,"Minigame1:0, Minigame2:5, Minigame3:5",10,1,3)
insert into result values(4,"Minigame1:5, Minigame2:0, Minigame3:5",10,1,4)
insert into result values(5,"Minigame1:0, Minigame2:0, Minigame3:0",0,1,5)
insert into result values(6,"Minigame1:0, Minigame2:0, Minigame3:0",0,1,6)
insert into result values(7,"Minigame1:10, Minigame2:10, Minigame3:10",30,2,2)
insert into result values(8,"Minigame1:0, Minigame2:0, Minigame3:0",0,2,4)
insert into result values(9,"Minigame1:0, Minigame2:0, Minigame3:0",0,2,6)
insert into result values(10,"Minigame1:0, Minigame2:10, Minigame3:10",20,3,3)
insert into result values(11,"Minigame1:5, Minigame2:0, Minigame3:5",10,3,6)
insert into result values(12,"Minigame1:5, Minigame2:10, Minigame3:5",20,4,1)
insert into result values(13,"Minigame1:0, Minigame2:0, Minigame3:0",0,4,3)
insert into result values(14,"Minigame1:5, Minigame2:5, Minigame3:",10,4,5)

-- Load of minigame

insert into minigame values(1,'Uno','Ejemplo uno')
insert into minigame values(2,'Dos','Ejemplo dos')
insert into minigame values(3,'Tres','Ejemplo tres')

-- Load of minigame_game

insert into minigame_games values(2,1)
insert into minigame_games values(3,1)
insert into minigame_games values(1,1)
insert into minigame_games values(1,2)
insert into minigame_games values(2,2)
insert into minigame_games values(3,2)
insert into minigame_games values(1,3)
insert into minigame_games values(2,3)
insert into minigame_games values(3,3)
insert into minigame_games values(1,4)
insert into minigame_games values(2,4)
insert into minigame_games values(3,4)

-- Load of figure

INSERT INTO figure VALUES (1, "ajedrez")
INSERT INTO figure VALUES (2, "ancla")
INSERT INTO figure VALUES (3, "arana")
INSERT INTO figure VALUES (4, "arbol")
INSERT INTO figure VALUES (5, "biberon")
INSERT INTO figure VALUES (6, "bomba")
INSERT INTO figure VALUES (7, "bombilla")
INSERT INTO figure VALUES (8, "bullseye")
INSERT INTO figure VALUES (9, "cactus")
INSERT INTO figure VALUES (10, "calavera")
INSERT INTO figure VALUES (11, "candado")
INSERT INTO figure VALUES (12, "cebra")
INSERT INTO figure VALUES (13, "clave")
INSERT INTO figure VALUES (14, "copoNieve")
INSERT INTO figure VALUES (15, "corazon")
INSERT INTO figure VALUES (16, "delfin")
INSERT INTO figure VALUES (17, "dragon")
INSERT INTO figure VALUES (18, "exclamacion")
INSERT INTO figure VALUES (19, "fantasma")
INSERT INTO figure VALUES (20, "gafas")
INSERT INTO figure VALUES (21, "gato")
INSERT INTO figure VALUES (22, "gota")
INSERT INTO figure VALUES (23, "hielo")
INSERT INTO figure VALUES (24, "hoguera")
INSERT INTO figure VALUES (25, "hoja")
INSERT INTO figure VALUES (26, "iglu")
INSERT INTO figure VALUES (27, "interrogacion")
INSERT INTO figure VALUES (28, "labios")
INSERT INTO figure VALUES (29, "lapiz")
INSERT INTO figure VALUES (30, "llave")
INSERT INTO figure VALUES (31, "luna")
INSERT INTO figure VALUES (32, "manchas")
INSERT INTO figure VALUES (33, "mano")
INSERT INTO figure VALUES (34, "manzana")
INSERT INTO figure VALUES (35, "margarita")
INSERT INTO figure VALUES (36, "mariquita")
INSERT INTO figure VALUES (37, "martillo")
INSERT INTO figure VALUES (38, "muneco")
INSERT INTO figure VALUES (39, "ojo")
INSERT INTO figure VALUES (40, "pajaro")
INSERT INTO figure VALUES (41, "payaso")
INSERT INTO figure VALUES (42, "perro")
INSERT INTO figure VALUES (43, "prohibido")
INSERT INTO figure VALUES (44, "queso")
INSERT INTO figure VALUES (45, "rayo")
INSERT INTO figure VALUES (46, "relog")
INSERT INTO figure VALUES (47, "sol")
INSERT INTO figure VALUES (48, "taxi")
INSERT INTO figure VALUES (49, "telarana")
INSERT INTO figure VALUES (50, "tijeras")
INSERT INTO figure VALUES (51, "tortuga")
INSERT INTO figure VALUES (52, "trebol")
INSERT INTO figure VALUES (53, "trex")
INSERT INTO figure VALUES (54, "us")
INSERT INTO figure VALUES (55, "vela")
INSERT INTO figure VALUES (56, "yinyan")
INSERT INTO figure VALUES (57, "zanahoria")

-- Load of achievement
INSERT INTO achievement VALUES(1,'Streak10','Streak 10 games', 1)
INSERT INTO achievement VALUES(2,'Streak20','Streak 20 games', 2)

-- Load of card

INSERT INTO card VALUES (1,"c01")
INSERT INTO card VALUES (10,"c10")
INSERT INTO card VALUES (11,"c11")
INSERT INTO card VALUES (12,"c12")
INSERT INTO card VALUES (13,"c13")
INSERT INTO card VALUES (14,"c14")
INSERT INTO card VALUES (15,"c15")
INSERT INTO card VALUES (16,"c16")
INSERT INTO card VALUES (17,"c17")
INSERT INTO card VALUES (18,"c18")
INSERT INTO card VALUES (19,"c19")
INSERT INTO card VALUES (2,"c02")
INSERT INTO card VALUES (20,"c20")
INSERT INTO card VALUES (21,"c21")
INSERT INTO card VALUES (22,"c22")
INSERT INTO card VALUES (23,"c23")
INSERT INTO card VALUES (24,"c24")
INSERT INTO card VALUES (25,"c25")
INSERT INTO card VALUES (26,"c26")
INSERT INTO card VALUES (27,"c27")
INSERT INTO card VALUES (28,"c28")
INSERT INTO card VALUES (29,"c29")
INSERT INTO card VALUES (3,"c03")
INSERT INTO card VALUES (30,"c30")
INSERT INTO card VALUES (31,"c31")
INSERT INTO card VALUES (32,"c32")
INSERT INTO card VALUES (33,"c33")
INSERT INTO card VALUES (34,"c34")
INSERT INTO card VALUES (35,"c35")
INSERT INTO card VALUES (36,"c36")
INSERT INTO card VALUES (37,"c37")
INSERT INTO card VALUES (38,"c38")
INSERT INTO card VALUES (39,"c39")
INSERT INTO card VALUES (4,"c04")
INSERT INTO card VALUES (40,"c40")
INSERT INTO card VALUES (41,"c41")
INSERT INTO card VALUES (42,"c42")
INSERT INTO card VALUES (43,"c43")
INSERT INTO card VALUES (44,"c44")
INSERT INTO card VALUES (45,"c45")
INSERT INTO card VALUES (46,"c46")
INSERT INTO card VALUES (47,"c47")
INSERT INTO card VALUES (48,"c48")
INSERT INTO card VALUES (49,"c49")
INSERT INTO card VALUES (5,"c05")
INSERT INTO card VALUES (50,"c50")
INSERT INTO card VALUES (51,"c51")
INSERT INTO card VALUES (52,"c52")
INSERT INTO card VALUES (53,"c53")
INSERT INTO card VALUES (54,"c54")
INSERT INTO card VALUES (55,"c55")
INSERT INTO card VALUES (56,"c56")
INSERT INTO card VALUES (57,"c57")
INSERT INTO card VALUES (6,"c06")
INSERT INTO card VALUES (7,"c07")
INSERT INTO card VALUES (8,"c08")
INSERT INTO card VALUES (9,"c09")


-- Carga de figuras de cartas

INSERT INTO figure_cards VALUES (29,1)
INSERT INTO figure_cards VALUES (22,1)
INSERT INTO figure_cards VALUES (15,1)
INSERT INTO figure_cards VALUES (8,1)
INSERT INTO figure_cards VALUES (57,1)
INSERT INTO figure_cards VALUES (43,1)
INSERT INTO figure_cards VALUES (36,1)
INSERT INTO figure_cards VALUES (1,1)


INSERT INTO figure_cards VALUES (16,10)
INSERT INTO figure_cards VALUES (50,10)
INSERT INTO figure_cards VALUES (21,10)
INSERT INTO figure_cards VALUES (20,10)
INSERT INTO figure_cards VALUES (19,10)
INSERT INTO figure_cards VALUES (18,10)
INSERT INTO figure_cards VALUES (17,10)
INSERT INTO figure_cards VALUES (15,10)


INSERT INTO figure_cards VALUES (26,11)
INSERT INTO figure_cards VALUES (25,11)
INSERT INTO figure_cards VALUES (24,11)
INSERT INTO figure_cards VALUES (23,11)
INSERT INTO figure_cards VALUES (50,11)
INSERT INTO figure_cards VALUES (28,11)
INSERT INTO figure_cards VALUES (27,11)
INSERT INTO figure_cards VALUES (22,11)


INSERT INTO figure_cards VALUES (30,12)
INSERT INTO figure_cards VALUES (50,12)
INSERT INTO figure_cards VALUES (35,12)
INSERT INTO figure_cards VALUES (34,12)
INSERT INTO figure_cards VALUES (33,12)
INSERT INTO figure_cards VALUES (32,12)
INSERT INTO figure_cards VALUES (31,12)
INSERT INTO figure_cards VALUES (29,12)


INSERT INTO figure_cards VALUES (40,13)
INSERT INTO figure_cards VALUES (39,13)
INSERT INTO figure_cards VALUES (38,13)
INSERT INTO figure_cards VALUES (37,13)
INSERT INTO figure_cards VALUES (50,13)
INSERT INTO figure_cards VALUES (42,13)
INSERT INTO figure_cards VALUES (41,13)
INSERT INTO figure_cards VALUES (36,13)


INSERT INTO figure_cards VALUES (44,14)
INSERT INTO figure_cards VALUES (50,14)
INSERT INTO figure_cards VALUES (49,14)
INSERT INTO figure_cards VALUES (48,14)
INSERT INTO figure_cards VALUES (47,14)
INSERT INTO figure_cards VALUES (46,14)
INSERT INTO figure_cards VALUES (45,14)
INSERT INTO figure_cards VALUES (43,14)


INSERT INTO figure_cards VALUES (33,15)
INSERT INTO figure_cards VALUES (25,15)
INSERT INTO figure_cards VALUES (17,15)
INSERT INTO figure_cards VALUES (9,15)
INSERT INTO figure_cards VALUES (51,15)
INSERT INTO figure_cards VALUES (49,15)
INSERT INTO figure_cards VALUES (41,15)
INSERT INTO figure_cards VALUES (1,15)


INSERT INTO figure_cards VALUES (16,16)
INSERT INTO figure_cards VALUES (51,16)
INSERT INTO figure_cards VALUES (7,16)
INSERT INTO figure_cards VALUES (48,16)
INSERT INTO figure_cards VALUES (40,16)
INSERT INTO figure_cards VALUES (32,16)
INSERT INTO figure_cards VALUES (24,16)
INSERT INTO figure_cards VALUES (8,16)


INSERT INTO figure_cards VALUES (47,17)
INSERT INTO figure_cards VALUES (39,17)
INSERT INTO figure_cards VALUES (31,17)
INSERT INTO figure_cards VALUES (23,17)
INSERT INTO figure_cards VALUES (51,17)
INSERT INTO figure_cards VALUES (14,17)
INSERT INTO figure_cards VALUES (6,17)
INSERT INTO figure_cards VALUES (15,17)


INSERT INTO figure_cards VALUES (30,18)
INSERT INTO figure_cards VALUES (51,18)
INSERT INTO figure_cards VALUES (21,18)
INSERT INTO figure_cards VALUES (13,18)
INSERT INTO figure_cards VALUES (5,18)
INSERT INTO figure_cards VALUES (46,18)
INSERT INTO figure_cards VALUES (38,18)
INSERT INTO figure_cards VALUES (22,18)


INSERT INTO figure_cards VALUES (12,19)
INSERT INTO figure_cards VALUES (4,19)
INSERT INTO figure_cards VALUES (45,19)
INSERT INTO figure_cards VALUES (37,19)
INSERT INTO figure_cards VALUES (51,19)
INSERT INTO figure_cards VALUES (28,19)
INSERT INTO figure_cards VALUES (20,19)
INSERT INTO figure_cards VALUES (29,19)


INSERT INTO figure_cards VALUES (9,2)
INSERT INTO figure_cards VALUES (57,2)
INSERT INTO figure_cards VALUES (44,2)
INSERT INTO figure_cards VALUES (37,2)
INSERT INTO figure_cards VALUES (30,2)
INSERT INTO figure_cards VALUES (23,2)
INSERT INTO figure_cards VALUES (16,2)
INSERT INTO figure_cards VALUES (2,2)


INSERT INTO figure_cards VALUES (44,20)
INSERT INTO figure_cards VALUES (51,20)
INSERT INTO figure_cards VALUES (35,20)
INSERT INTO figure_cards VALUES (27,20)
INSERT INTO figure_cards VALUES (19,20)
INSERT INTO figure_cards VALUES (11,20)
INSERT INTO figure_cards VALUES (3,20)
INSERT INTO figure_cards VALUES (36,20)


INSERT INTO figure_cards VALUES (26,21)
INSERT INTO figure_cards VALUES (18,21)
INSERT INTO figure_cards VALUES (10,21)
INSERT INTO figure_cards VALUES (2,21)
INSERT INTO figure_cards VALUES (51,21)
INSERT INTO figure_cards VALUES (42,21)
INSERT INTO figure_cards VALUES (34,21)
INSERT INTO figure_cards VALUES (43,21)


INSERT INTO figure_cards VALUES (16,22)
INSERT INTO figure_cards VALUES (52,22)
INSERT INTO figure_cards VALUES (42,22)
INSERT INTO figure_cards VALUES (27,22)
INSERT INTO figure_cards VALUES (12,22)
INSERT INTO figure_cards VALUES (46,22)
INSERT INTO figure_cards VALUES (31,22)
INSERT INTO figure_cards VALUES (1,22)


INSERT INTO figure_cards VALUES (19,23)
INSERT INTO figure_cards VALUES (4,23)
INSERT INTO figure_cards VALUES (38,23)
INSERT INTO figure_cards VALUES (23,23)
INSERT INTO figure_cards VALUES (52,23)
INSERT INTO figure_cards VALUES (49,23)
INSERT INTO figure_cards VALUES (34,23)
INSERT INTO figure_cards VALUES (8,23)


INSERT INTO figure_cards VALUES (30,24)
INSERT INTO figure_cards VALUES (7,24)
INSERT INTO figure_cards VALUES (41,24)
INSERT INTO figure_cards VALUES (26,24)
INSERT INTO figure_cards VALUES (52,24)
INSERT INTO figure_cards VALUES (11,24)
INSERT INTO figure_cards VALUES (45,24)
INSERT INTO figure_cards VALUES (15,24)


INSERT INTO figure_cards VALUES (33,25)
INSERT INTO figure_cards VALUES (18,25)
INSERT INTO figure_cards VALUES (3,25)
INSERT INTO figure_cards VALUES (37,25)
INSERT INTO figure_cards VALUES (52,25)
INSERT INTO figure_cards VALUES (14,25)
INSERT INTO figure_cards VALUES (48,25)
INSERT INTO figure_cards VALUES (22,25)


INSERT INTO figure_cards VALUES (44,26)
INSERT INTO figure_cards VALUES (52,26)
INSERT INTO figure_cards VALUES (21,26)
INSERT INTO figure_cards VALUES (6,26)
INSERT INTO figure_cards VALUES (40,26)
INSERT INTO figure_cards VALUES (25,26)
INSERT INTO figure_cards VALUES (10,26)
INSERT INTO figure_cards VALUES (29,26)


INSERT INTO figure_cards VALUES (47,27)
INSERT INTO figure_cards VALUES (32,27)
INSERT INTO figure_cards VALUES (17,27)
INSERT INTO figure_cards VALUES (2,27)
INSERT INTO figure_cards VALUES (52,27)
INSERT INTO figure_cards VALUES (28,27)
INSERT INTO figure_cards VALUES (13,27)
INSERT INTO figure_cards VALUES (36,27)


INSERT INTO figure_cards VALUES (9,28)
INSERT INTO figure_cards VALUES (52,28)
INSERT INTO figure_cards VALUES (35,28)
INSERT INTO figure_cards VALUES (20,28)
INSERT INTO figure_cards VALUES (5,28)
INSERT INTO figure_cards VALUES (39,28)
INSERT INTO figure_cards VALUES (24,28)
INSERT INTO figure_cards VALUES (43,28)


INSERT INTO figure_cards VALUES (40,29)
INSERT INTO figure_cards VALUES (18,29)
INSERT INTO figure_cards VALUES (45,29)
INSERT INTO figure_cards VALUES (23,29)
INSERT INTO figure_cards VALUES (53,29)
INSERT INTO figure_cards VALUES (35,29)
INSERT INTO figure_cards VALUES (1,29)
INSERT INTO figure_cards VALUES (13,29)


INSERT INTO figure_cards VALUES (31,3)
INSERT INTO figure_cards VALUES (24,3)
INSERT INTO figure_cards VALUES (17,3)
INSERT INTO figure_cards VALUES (10,3)
INSERT INTO figure_cards VALUES (57,3)
INSERT INTO figure_cards VALUES (45,3)
INSERT INTO figure_cards VALUES (38,3)
INSERT INTO figure_cards VALUES (3,3)


INSERT INTO figure_cards VALUES (30,30)
INSERT INTO figure_cards VALUES (53,30)
INSERT INTO figure_cards VALUES (42,30)
INSERT INTO figure_cards VALUES (20,30)
INSERT INTO figure_cards VALUES (47,30)
INSERT INTO figure_cards VALUES (25,30)
INSERT INTO figure_cards VALUES (25,30)
INSERT INTO figure_cards VALUES (8,30)


INSERT INTO figure_cards VALUES (5,31)
INSERT INTO figure_cards VALUES (32,31)
INSERT INTO figure_cards VALUES (10,31)
INSERT INTO figure_cards VALUES (37,31)
INSERT INTO figure_cards VALUES (53,31)
INSERT INTO figure_cards VALUES (49,31)
INSERT INTO figure_cards VALUES (27,31)
INSERT INTO figure_cards VALUES (15,31)


INSERT INTO figure_cards VALUES (44,32)
INSERT INTO figure_cards VALUES (53,32)
INSERT INTO figure_cards VALUES (7,32)
INSERT INTO figure_cards VALUES (34,32)
INSERT INTO figure_cards VALUES (12,32)
INSERT INTO figure_cards VALUES (39,32)
INSERT INTO figure_cards VALUES (17,32)
INSERT INTO figure_cards VALUES (22,32)


INSERT INTO figure_cards VALUES (41,33)
INSERT INTO figure_cards VALUES (19,33)
INSERT INTO figure_cards VALUES (46,33)
INSERT INTO figure_cards VALUES (24,33)
INSERT INTO figure_cards VALUES (2,33)
INSERT INTO figure_cards VALUES (53,33)
INSERT INTO figure_cards VALUES (14,33)
INSERT INTO figure_cards VALUES (41,33)


INSERT INTO figure_cards VALUES (9,34)
INSERT INTO figure_cards VALUES (53,34)
INSERT INTO figure_cards VALUES (21,34)
INSERT INTO figure_cards VALUES (48,34)
INSERT INTO figure_cards VALUES (26,34)
INSERT INTO figure_cards VALUES (4,34)
INSERT INTO figure_cards VALUES (31,34)
INSERT INTO figure_cards VALUES (36,34)


INSERT INTO figure_cards VALUES (33,35)
INSERT INTO figure_cards VALUES (11,35)
INSERT INTO figure_cards VALUES (38,35)
INSERT INTO figure_cards VALUES (16,35)
INSERT INTO figure_cards VALUES (53,35)
INSERT INTO figure_cards VALUES (28,35)
INSERT INTO figure_cards VALUES (6,35)
INSERT INTO figure_cards VALUES (43,35)


INSERT INTO figure_cards VALUES (30,36)
INSERT INTO figure_cards VALUES (54,36)
INSERT INTO figure_cards VALUES (28,36)
INSERT INTO figure_cards VALUES (48,36)
INSERT INTO figure_cards VALUES (19,36)
INSERT INTO figure_cards VALUES (39,36)
INSERT INTO figure_cards VALUES (10,36)
INSERT INTO figure_cards VALUES (1,36)


INSERT INTO figure_cards VALUES (26,37)
INSERT INTO figure_cards VALUES (46,37)
INSERT INTO figure_cards VALUES (17,37)
INSERT INTO figure_cards VALUES (37,37)
INSERT INTO figure_cards VALUES (54,37)
INSERT INTO figure_cards VALUES (35,37)
INSERT INTO figure_cards VALUES (6,37)
INSERT INTO figure_cards VALUES (8,37)


INSERT INTO figure_cards VALUES (44,38)
INSERT INTO figure_cards VALUES (54,38)
INSERT INTO figure_cards VALUES (42,38)
INSERT INTO figure_cards VALUES (13,38)
INSERT INTO figure_cards VALUES (33,38)
INSERT INTO figure_cards VALUES (4,38)
INSERT INTO figure_cards VALUES (24,38)
INSERT INTO figure_cards VALUES (15,38)


INSERT INTO figure_cards VALUES (40,39)
INSERT INTO figure_cards VALUES (11,39)
INSERT INTO figure_cards VALUES (31,39)
INSERT INTO figure_cards VALUES (2,39)
INSERT INTO figure_cards VALUES (54,39)
INSERT INTO figure_cards VALUES (49,39)
INSERT INTO figure_cards VALUES (20,39)
INSERT INTO figure_cards VALUES (22,39)


INSERT INTO figure_cards VALUES (11,4)
INSERT INTO figure_cards VALUES (57,4)
INSERT INTO figure_cards VALUES (46,4)
INSERT INTO figure_cards VALUES (39,4)
INSERT INTO figure_cards VALUES (32,4)
INSERT INTO figure_cards VALUES (25,4)
INSERT INTO figure_cards VALUES (18,4)
INSERT INTO figure_cards VALUES (4,4)


INSERT INTO figure_cards VALUES (9,40)
INSERT INTO figure_cards VALUES (54,40)
INSERT INTO figure_cards VALUES (7,40)
INSERT INTO figure_cards VALUES (27,40)
INSERT INTO figure_cards VALUES (47,40)
INSERT INTO figure_cards VALUES (18,40)
INSERT INTO figure_cards VALUES (38,40)
INSERT INTO figure_cards VALUES (29,40)


INSERT INTO figure_cards VALUES (5,41)
INSERT INTO figure_cards VALUES (25,41)
INSERT INTO figure_cards VALUES (45,41)
INSERT INTO figure_cards VALUES (16,41)
INSERT INTO figure_cards VALUES (54,41)
INSERT INTO figure_cards VALUES (14,41)
INSERT INTO figure_cards VALUES (34,41)
INSERT INTO figure_cards VALUES (36,41)


INSERT INTO figure_cards VALUES (23,42)
INSERT INTO figure_cards VALUES (54,42)
INSERT INTO figure_cards VALUES (21,42)
INSERT INTO figure_cards VALUES (41,42)
INSERT INTO figure_cards VALUES (12,42)
INSERT INTO figure_cards VALUES (32,42)
INSERT INTO figure_cards VALUES (3,42)
INSERT INTO figure_cards VALUES (43,42)


INSERT INTO figure_cards VALUES (47,43)
INSERT INTO figure_cards VALUES (11,43)
INSERT INTO figure_cards VALUES (24,43)
INSERT INTO figure_cards VALUES (37,43)
INSERT INTO figure_cards VALUES (55,43)
INSERT INTO figure_cards VALUES (21,43)
INSERT INTO figure_cards VALUES (34,43)
INSERT INTO figure_cards VALUES (1,43)


INSERT INTO figure_cards VALUES (44,44)
INSERT INTO figure_cards VALUES (55,44)
INSERT INTO figure_cards VALUES (28,44)
INSERT INTO figure_cards VALUES (41,44)
INSERT INTO figure_cards VALUES (5,44)
INSERT INTO figure_cards VALUES (18,44)
INSERT INTO figure_cards VALUES (31,44)
INSERT INTO figure_cards VALUES (8,44)


INSERT INTO figure_cards VALUES (12,45)
INSERT INTO figure_cards VALUES (25,45)
INSERT INTO figure_cards VALUES (38,45)
INSERT INTO figure_cards VALUES (2,45)
INSERT INTO figure_cards VALUES (55,45)
INSERT INTO figure_cards VALUES (35,45)
INSERT INTO figure_cards VALUES (48,45)
INSERT INTO figure_cards VALUES (15,45)


INSERT INTO figure_cards VALUES (9,46)
INSERT INTO figure_cards VALUES (55,46)
INSERT INTO figure_cards VALUES (42,46)
INSERT INTO figure_cards VALUES (6,46)
INSERT INTO figure_cards VALUES (19,46)
INSERT INTO figure_cards VALUES (32,46)
INSERT INTO figure_cards VALUES (45,46)
INSERT INTO figure_cards VALUES (22,46)


INSERT INTO figure_cards VALUES (26,47)
INSERT INTO figure_cards VALUES (39,47)
INSERT INTO figure_cards VALUES (3,47)
INSERT INTO figure_cards VALUES (16,47)
INSERT INTO figure_cards VALUES (55,47)
INSERT INTO figure_cards VALUES (49,47)
INSERT INTO figure_cards VALUES (13,47)
INSERT INTO figure_cards VALUES (29,47)


INSERT INTO figure_cards VALUES (23,48)
INSERT INTO figure_cards VALUES (55,48)
INSERT INTO figure_cards VALUES (7,48)
INSERT INTO figure_cards VALUES (20,48)
INSERT INTO figure_cards VALUES (33,48)
INSERT INTO figure_cards VALUES (46,48)
INSERT INTO figure_cards VALUES (10,48)
INSERT INTO figure_cards VALUES (36,48)


INSERT INTO figure_cards VALUES (40,49)
INSERT INTO figure_cards VALUES (4,49)
INSERT INTO figure_cards VALUES (17,49)
INSERT INTO figure_cards VALUES (30,49)
INSERT INTO figure_cards VALUES (55,49)
INSERT INTO figure_cards VALUES (14,49)
INSERT INTO figure_cards VALUES (27,49)
INSERT INTO figure_cards VALUES (43,49)


INSERT INTO figure_cards VALUES (40,5)
INSERT INTO figure_cards VALUES (33,5)
INSERT INTO figure_cards VALUES (26,5)
INSERT INTO figure_cards VALUES (19,5)
INSERT INTO figure_cards VALUES (12,5)
INSERT INTO figure_cards VALUES (57,5)
INSERT INTO figure_cards VALUES (47,5)
INSERT INTO figure_cards VALUES (5,5)


INSERT INTO figure_cards VALUES (44,50)
INSERT INTO figure_cards VALUES (56,50)
INSERT INTO figure_cards VALUES (14,50)
INSERT INTO figure_cards VALUES (20,50)
INSERT INTO figure_cards VALUES (26,50)
INSERT INTO figure_cards VALUES (32,50)
INSERT INTO figure_cards VALUES (38,50)
INSERT INTO figure_cards VALUES (1,50)


INSERT INTO figure_cards VALUES (33,51)
INSERT INTO figure_cards VALUES (39,51)
INSERT INTO figure_cards VALUES (45,51)
INSERT INTO figure_cards VALUES (3,51)
INSERT INTO figure_cards VALUES (56,51)
INSERT INTO figure_cards VALUES (21,51)
INSERT INTO figure_cards VALUES (27,51)
INSERT INTO figure_cards VALUES (8,51)


INSERT INTO figure_cards VALUES (9,52)
INSERT INTO figure_cards VALUES (56,52)
INSERT INTO figure_cards VALUES (28,52)
INSERT INTO figure_cards VALUES (34,52)
INSERT INTO figure_cards VALUES (40,52)
INSERT INTO figure_cards VALUES (46,52)
INSERT INTO figure_cards VALUES (3,52)
INSERT INTO figure_cards VALUES (15,52)


INSERT INTO figure_cards VALUES (41,53)
INSERT INTO figure_cards VALUES (47,53)
INSERT INTO figure_cards VALUES (4,53)
INSERT INTO figure_cards VALUES (10,53)
INSERT INTO figure_cards VALUES (16,53)
INSERT INTO figure_cards VALUES (56,53)
INSERT INTO figure_cards VALUES (35,53)
INSERT INTO figure_cards VALUES (22,53)


INSERT INTO figure_cards VALUES (23,54)
INSERT INTO figure_cards VALUES (56,54)
INSERT INTO figure_cards VALUES (42,54)
INSERT INTO figure_cards VALUES (48,54)
INSERT INTO figure_cards VALUES (5,54)
INSERT INTO figure_cards VALUES (11,54)
INSERT INTO figure_cards VALUES (17,54)
INSERT INTO figure_cards VALUES (29,54)


INSERT INTO figure_cards VALUES (6,55)
INSERT INTO figure_cards VALUES (12,55)
INSERT INTO figure_cards VALUES (18,55)
INSERT INTO figure_cards VALUES (24,55)
INSERT INTO figure_cards VALUES (30,55)
INSERT INTO figure_cards VALUES (56,55)
INSERT INTO figure_cards VALUES (49,55)
INSERT INTO figure_cards VALUES (36,55)


INSERT INTO figure_cards VALUES (37,56)
INSERT INTO figure_cards VALUES (56,56)
INSERT INTO figure_cards VALUES (7,56)
INSERT INTO figure_cards VALUES (13,56)
INSERT INTO figure_cards VALUES (19,56)
INSERT INTO figure_cards VALUES (25,56)
INSERT INTO figure_cards VALUES (31,56)
INSERT INTO figure_cards VALUES (43,56)


INSERT INTO figure_cards VALUES (51,57)
INSERT INTO figure_cards VALUES (57,57)
INSERT INTO figure_cards VALUES (56,57)
INSERT INTO figure_cards VALUES (55,57)
INSERT INTO figure_cards VALUES (54,57)
INSERT INTO figure_cards VALUES (53,57)
INSERT INTO figure_cards VALUES (52,57)
INSERT INTO figure_cards VALUES (50,57)


INSERT INTO figure_cards VALUES (13,6)
INSERT INTO figure_cards VALUES (20,6)
INSERT INTO figure_cards VALUES (57,6)
INSERT INTO figure_cards VALUES (48,6)
INSERT INTO figure_cards VALUES (6,6)
INSERT INTO figure_cards VALUES (41,6)
INSERT INTO figure_cards VALUES (34,6)
INSERT INTO figure_cards VALUES (27,6)


INSERT INTO figure_cards VALUES (14,7)
INSERT INTO figure_cards VALUES (57,7)
INSERT INTO figure_cards VALUES (21,7)
INSERT INTO figure_cards VALUES (7,7)
INSERT INTO figure_cards VALUES (49,7)
INSERT INTO figure_cards VALUES (42,7)
INSERT INTO figure_cards VALUES (28,7)
INSERT INTO figure_cards VALUES (35,7)


INSERT INTO figure_cards VALUES (6,8)
INSERT INTO figure_cards VALUES (5,8)
INSERT INTO figure_cards VALUES (7,8)
INSERT INTO figure_cards VALUES (4,8)
INSERT INTO figure_cards VALUES (1,8)
INSERT INTO figure_cards VALUES (50,8)
INSERT INTO figure_cards VALUES (3,8)
INSERT INTO figure_cards VALUES (2,8)


INSERT INTO figure_cards VALUES (50,9)
INSERT INTO figure_cards VALUES (9,9)
INSERT INTO figure_cards VALUES (10,9)
INSERT INTO figure_cards VALUES (14,9)
INSERT INTO figure_cards VALUES (13,9)
INSERT INTO figure_cards VALUES (8,9)
INSERT INTO figure_cards VALUES (11,9)
INSERT INTO figure_cards VALUES (12,9)