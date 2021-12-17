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

insert into game values(1, 1,'2021-01-01 01:11:01','juegoUno','2021-01-01 01:01:01',2,null)
insert into game values(2,2,'2021-02-02 02:22:02','juegoDos','2021-02-02 02:02:02',2,null)
insert into game values(3,3,'2021-03-03 03:33:03','juegoTres','2021-03-03 03:03:03',2,null)
insert into game values(4,4,'2021-04-04 04:44:04','juegoCuatro','2021-04-04 04:04:04',2,null)


-- Load of role

insert into role values(1,'player')
insert into role values(2,'admin')

-- Load of privilege

insert into privilege values(1, 'VIEW_AWARDS')


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
insert into player_games_played values(4,4)
insert into player_games_played values(5,4)


-- Load of result

insert into result values(1,"5 0 5",10,1,1)
insert into result values(2,"0 0 0",0,1,2)
insert into result values(3,"0 5 5",10,1,3)
insert into result values(4,"5 0 5",10,1,4)
insert into result values(5,"0 0 0",0,1,5)
insert into result values(6,"0 0 0",0,1,6)
insert into result values(7,"10 10 10",30,2,2)
insert into result values(8,"0 0 0",0,2,4)
insert into result values(9,"0 0 0",0,2,6)
insert into result values(10,"0 0 10",20,3,3)
insert into result values(11,"5 0 5",10,3,6)
insert into result values(12,"5 10 5",20,4,1)
insert into result values(13,"0 0 0",0,4,3)
insert into result values(14,"5 5 5",10,4,5)

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


--Load playerfigures

INSERT INTO playerfigures VALUES (0,255,1,1)
INSERT INTO playerfigures VALUES (1,33,2,1)
INSERT INTO playerfigures VALUES (2,380,3,1)
INSERT INTO playerfigures VALUES (3,448,4,1)
INSERT INTO playerfigures VALUES (4,305,5,1)
INSERT INTO playerfigures VALUES (5,180,6,1)
INSERT INTO playerfigures VALUES (6,34,7,1)
INSERT INTO playerfigures VALUES (7,378,8,1)
INSERT INTO playerfigures VALUES (8,481,9,1)
INSERT INTO playerfigures VALUES (9,202,10,1)
INSERT INTO playerfigures VALUES (10,423,11,1)
INSERT INTO playerfigures VALUES (11,44,12,1)
INSERT INTO playerfigures VALUES (12,403,13,1)
INSERT INTO playerfigures VALUES (13,35,14,1)
INSERT INTO playerfigures VALUES (14,175,15,1)
INSERT INTO playerfigures VALUES (15,470,16,1)
INSERT INTO playerfigures VALUES (16,252,17,1)
INSERT INTO playerfigures VALUES (17,74,18,1)
INSERT INTO playerfigures VALUES (18,86,19,1)
INSERT INTO playerfigures VALUES (19,65,20,1)
INSERT INTO playerfigures VALUES (20,243,21,1)
INSERT INTO playerfigures VALUES (21,18,22,1)
INSERT INTO playerfigures VALUES (22,260,23,1)
INSERT INTO playerfigures VALUES (23,106,24,1)
INSERT INTO playerfigures VALUES (24,435,25,1)
INSERT INTO playerfigures VALUES (25,31,26,1)
INSERT INTO playerfigures VALUES (26,35,27,1)
INSERT INTO playerfigures VALUES (27,321,28,1)
INSERT INTO playerfigures VALUES (28,286,29,1)
INSERT INTO playerfigures VALUES (29,125,30,1)
INSERT INTO playerfigures VALUES (30,5,31,1)
INSERT INTO playerfigures VALUES (31,23,32,1)
INSERT INTO playerfigures VALUES (32,10,33,1)
INSERT INTO playerfigures VALUES (33,156,34,1)
INSERT INTO playerfigures VALUES (34,396,35,1)
INSERT INTO playerfigures VALUES (35,416,36,1)
INSERT INTO playerfigures VALUES (36,477,37,1)
INSERT INTO playerfigures VALUES (37,113,38,1)
INSERT INTO playerfigures VALUES (38,347,39,1)
INSERT INTO playerfigures VALUES (39,163,40,1)
INSERT INTO playerfigures VALUES (40,125,41,1)
INSERT INTO playerfigures VALUES (41,179,42,1)
INSERT INTO playerfigures VALUES (42,384,43,1)
INSERT INTO playerfigures VALUES (43,1,44,1)
INSERT INTO playerfigures VALUES (44,396,45,1)
INSERT INTO playerfigures VALUES (45,285,46,1)
INSERT INTO playerfigures VALUES (46,47,47,1)
INSERT INTO playerfigures VALUES (47,353,48,1)
INSERT INTO playerfigures VALUES (48,205,49,1)
INSERT INTO playerfigures VALUES (49,377,50,1)
INSERT INTO playerfigures VALUES (50,327,51,1)
INSERT INTO playerfigures VALUES (51,77,52,1)
INSERT INTO playerfigures VALUES (52,54,53,1)
INSERT INTO playerfigures VALUES (53,62,54,1)
INSERT INTO playerfigures VALUES (54,448,55,1)
INSERT INTO playerfigures VALUES (55,361,56,1)
INSERT INTO playerfigures VALUES (56,379,57,1)
INSERT INTO playerfigures VALUES (57,465,1,2)
INSERT INTO playerfigures VALUES (58,92,2,2)
INSERT INTO playerfigures VALUES (59,18,3,2)
INSERT INTO playerfigures VALUES (60,289,4,2)
INSERT INTO playerfigures VALUES (61,444,5,2)
INSERT INTO playerfigures VALUES (62,432,6,2)
INSERT INTO playerfigures VALUES (63,26,7,2)
INSERT INTO playerfigures VALUES (64,382,8,2)
INSERT INTO playerfigures VALUES (65,161,9,2)
INSERT INTO playerfigures VALUES (66,475,10,2)
INSERT INTO playerfigures VALUES (67,292,11,2)
INSERT INTO playerfigures VALUES (68,43,12,2)
INSERT INTO playerfigures VALUES (69,323,13,2)
INSERT INTO playerfigures VALUES (70,441,14,2)
INSERT INTO playerfigures VALUES (71,207,15,2)
INSERT INTO playerfigures VALUES (72,380,16,2)
INSERT INTO playerfigures VALUES (73,497,17,2)
INSERT INTO playerfigures VALUES (74,95,18,2)
INSERT INTO playerfigures VALUES (75,380,19,2)
INSERT INTO playerfigures VALUES (76,383,20,2)
INSERT INTO playerfigures VALUES (77,323,21,2)
INSERT INTO playerfigures VALUES (78,217,22,2)
INSERT INTO playerfigures VALUES (79,301,23,2)
INSERT INTO playerfigures VALUES (80,488,24,2)
INSERT INTO playerfigures VALUES (81,366,25,2)
INSERT INTO playerfigures VALUES (82,143,26,2)
INSERT INTO playerfigures VALUES (83,200,27,2)
INSERT INTO playerfigures VALUES (84,177,28,2)
INSERT INTO playerfigures VALUES (85,143,29,2)
INSERT INTO playerfigures VALUES (86,218,30,2)
INSERT INTO playerfigures VALUES (87,220,31,2)
INSERT INTO playerfigures VALUES (88,307,32,2)
INSERT INTO playerfigures VALUES (89,317,33,2)
INSERT INTO playerfigures VALUES (90,405,34,2)
INSERT INTO playerfigures VALUES (91,490,35,2)
INSERT INTO playerfigures VALUES (92,399,36,2)
INSERT INTO playerfigures VALUES (93,166,37,2)
INSERT INTO playerfigures VALUES (94,128,38,2)
INSERT INTO playerfigures VALUES (95,30,39,2)
INSERT INTO playerfigures VALUES (96,333,40,2)
INSERT INTO playerfigures VALUES (97,201,41,2)
INSERT INTO playerfigures VALUES (98,91,42,2)
INSERT INTO playerfigures VALUES (99,314,43,2)
INSERT INTO playerfigures VALUES (100,255,44,2)
INSERT INTO playerfigures VALUES (101,327,45,2)
INSERT INTO playerfigures VALUES (102,335,46,2)
INSERT INTO playerfigures VALUES (103,133,47,2)
INSERT INTO playerfigures VALUES (104,66,48,2)
INSERT INTO playerfigures VALUES (105,315,49,2)
INSERT INTO playerfigures VALUES (106,421,50,2)
INSERT INTO playerfigures VALUES (107,198,51,2)
INSERT INTO playerfigures VALUES (108,4,52,2)
INSERT INTO playerfigures VALUES (109,273,53,2)
INSERT INTO playerfigures VALUES (110,301,54,2)
INSERT INTO playerfigures VALUES (111,348,55,2)
INSERT INTO playerfigures VALUES (112,26,56,2)
INSERT INTO playerfigures VALUES (113,38,57,2)
INSERT INTO playerfigures VALUES (114,88,1,3)
INSERT INTO playerfigures VALUES (115,246,2,3)
INSERT INTO playerfigures VALUES (116,130,3,3)
INSERT INTO playerfigures VALUES (117,134,4,3)
INSERT INTO playerfigures VALUES (118,82,5,3)
INSERT INTO playerfigures VALUES (119,197,6,3)
INSERT INTO playerfigures VALUES (120,277,7,3)
INSERT INTO playerfigures VALUES (121,252,8,3)
INSERT INTO playerfigures VALUES (122,323,9,3)
INSERT INTO playerfigures VALUES (123,230,10,3)
INSERT INTO playerfigures VALUES (124,396,11,3)
INSERT INTO playerfigures VALUES (125,233,12,3)
INSERT INTO playerfigures VALUES (126,185,13,3)
INSERT INTO playerfigures VALUES (127,325,14,3)
INSERT INTO playerfigures VALUES (128,77,15,3)
INSERT INTO playerfigures VALUES (129,56,16,3)
INSERT INTO playerfigures VALUES (130,3,17,3)
INSERT INTO playerfigures VALUES (131,343,18,3)
INSERT INTO playerfigures VALUES (132,82,19,3)
INSERT INTO playerfigures VALUES (133,229,20,3)
INSERT INTO playerfigures VALUES (134,358,21,3)
INSERT INTO playerfigures VALUES (135,172,22,3)
INSERT INTO playerfigures VALUES (136,488,23,3)
INSERT INTO playerfigures VALUES (137,241,24,3)
INSERT INTO playerfigures VALUES (138,487,25,3)
INSERT INTO playerfigures VALUES (139,135,26,3)
INSERT INTO playerfigures VALUES (140,442,27,3)
INSERT INTO playerfigures VALUES (141,325,28,3)
INSERT INTO playerfigures VALUES (142,176,29,3)
INSERT INTO playerfigures VALUES (143,466,30,3)
INSERT INTO playerfigures VALUES (144,40,31,3)
INSERT INTO playerfigures VALUES (145,5,32,3)
INSERT INTO playerfigures VALUES (146,4,33,3)
INSERT INTO playerfigures VALUES (147,483,34,3)
INSERT INTO playerfigures VALUES (148,150,35,3)
INSERT INTO playerfigures VALUES (149,66,36,3)
INSERT INTO playerfigures VALUES (150,101,37,3)
INSERT INTO playerfigures VALUES (151,492,38,3)
INSERT INTO playerfigures VALUES (152,139,39,3)
INSERT INTO playerfigures VALUES (153,389,40,3)
INSERT INTO playerfigures VALUES (154,241,41,3)
INSERT INTO playerfigures VALUES (155,163,42,3)
INSERT INTO playerfigures VALUES (156,423,43,3)
INSERT INTO playerfigures VALUES (157,383,44,3)
INSERT INTO playerfigures VALUES (158,344,45,3)
INSERT INTO playerfigures VALUES (159,75,46,3)
INSERT INTO playerfigures VALUES (160,474,47,3)
INSERT INTO playerfigures VALUES (161,22,48,3)
INSERT INTO playerfigures VALUES (162,348,49,3)
INSERT INTO playerfigures VALUES (163,165,50,3)
INSERT INTO playerfigures VALUES (164,455,51,3)
INSERT INTO playerfigures VALUES (165,232,52,3)
INSERT INTO playerfigures VALUES (166,273,53,3)
INSERT INTO playerfigures VALUES (167,457,54,3)
INSERT INTO playerfigures VALUES (168,488,55,3)
INSERT INTO playerfigures VALUES (169,377,56,3)
INSERT INTO playerfigures VALUES (170,178,57,3)