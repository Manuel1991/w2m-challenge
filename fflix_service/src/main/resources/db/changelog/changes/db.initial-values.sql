INSERT INTO title (id, name, release, type) VALUES
(1, 'Star Wars: A New Hope', 1977, 'MOVIE'),
(2, 'Star Wars: The Empire Strikes Back', 1980, 'MOVIE'),
(3, 'Star Wars: Return of the Jedi', 1983, 'MOVIE'),
(4, 'Star Trek: The Motion Picture', 1979, 'MOVIE'),
(5, 'Star Trek II: The Wrath of Khan', 1982, 'MOVIE'),
(6, 'Guardians of the Galaxy', 2014, 'MOVIE'),
(7, 'Guardians of the Galaxy Vol. 2', 2017, 'MOVIE'),
(8, 'Alien', 1979, 'MOVIE'),
(9, 'Aliens', 1986, 'MOVIE'),
(10, 'Prometheus', 2012, 'MOVIE'),
(11, 'Interstellar', 2014, 'MOVIE'),
(12, '2001: A Space Odyssey', 1968, 'MOVIE'),
(13, 'The Fifth Element', 1997, 'MOVIE'),
(14, 'Serenity', 2005, 'MOVIE'),
(15, 'Event Horizon', 1997, 'MOVIE'),
(16, 'Dune', 2021, 'MOVIE'),
(17, 'Independence Day', 1996, 'MOVIE'),
(18, 'Pacific Rim: Uprising', 2018, 'MOVIE'),
(19, 'Spaceballs', 1987, 'MOVIE'),
(20, 'Avatar', 2009, 'MOVIE'),
(21, 'Star Trek: The Original Series', 1966, 'SERIES'),
(22, 'Star Trek: The Next Generation', 1987, 'SERIES'),
(23, 'Star Trek: Deep Space Nine', 1993, 'SERIES'),
(24, 'Star Trek: Voyager', 1995, 'SERIES'),
(25, 'Battlestar Galactica', 2004, 'SERIES'),
(26, 'Firefly', 2002, 'SERIES'),
(27, 'The Expanse', 2015, 'SERIES'),
(28, 'Farscape', 1999, 'SERIES'),
(29, 'Lost in Space', 2018, 'SERIES'),
(30, 'The Mandalorian', 2019, 'SERIES'),
(31, 'The Orville', 2017, 'SERIES'),
(32, 'Cowboy Bebop', 1998, 'SERIES');

-- Insertar registros en la tabla 'spaceship'
INSERT INTO spaceship (name, model, captain, crew_size, max_speed, affiliation, weapons, status, title_id) VALUES
-- Star Wars
('Millennium Falcon', 'YT-1300 light freighter', 'Han Solo', 4, '105 MGLT', 'Rebellion', 'Laser Cannons', 'Active', 1),
('Death Star', 'DS-1 Orbital Battle Station', 'Grand Moff Tarkin', 265675, 'N/A', 'Empire', 'Superlaser', 'Destroyed', 1),
('X-Wing', 'T-65 X-wing starfighter', 'Luke Skywalker', 1, '100 MGLT', 'Rebellion', 'Proton Torpedoes', 'Active', 1),
('Slave I', 'Firespray-31-class patrol ship', 'Boba Fett', 1, '240 MGLT', 'Bounty Hunter', 'Ion Cannons', 'Active', 2),
('Executor', 'Super Star Destroyer', 'Darth Vader', 279144, 'N/A', 'Empire', 'Turbo Lasers', 'Destroyed', 2),
-- Star Trek
('USS Enterprise (NCC-1701)', 'Constitution-class', 'James T. Kirk', 430, 'Warp 8', 'Federation', 'Phasers, Photon Torpedoes', 'Active', 4),
('USS Reliant', 'Miranda-class', 'Clark Terrell', 203, 'Warp 8', 'Federation', 'Phasers', 'Destroyed', 5),
-- Guardians of the Galaxy
('Milano', 'M-class spaceship', 'Peter Quill', 5, 'Unknown', 'Guardians', 'Quad Blasters', 'Destroyed', 6),
('Benatar', 'Unknown model', 'Peter Quill', 6, 'Unknown', 'Guardians', 'Quad Blasters', 'Active', 7),
-- Alien Saga
('Nostromo', 'Commercial Towing Vehicle', 'Dallas', 7, '0.42c', 'Weyland-Yutani', 'None', 'Destroyed', 8),
('Sulaco', 'Conestoga-class starship', 'Unknown', 85, '0.74c', 'Weyland-Yutani', 'Pulse Rifles', 'Destroyed', 9),
-- Prometheus
('Prometheus', 'Exploration Vessel', 'Elizabeth Shaw', 17, 'N/A', 'Weyland Corp', 'None', 'Destroyed', 10),
-- Interstellar
('Endurance', 'Ring-shaped spacecraft', 'Joseph Cooper', 12, 'Near-light', 'NASA', 'None', 'Active', 11),
-- 2001: A Space Odyssey
('Discovery One', 'Explorer spacecraft', 'Dave Bowman', 6, 'N/A', 'HAL 9000 AI', 'None', 'Lost', 12),
-- The Fifth Element
('Fhloston Paradise', 'Luxury Cruise Ship', 'Captain Unknown', 500, 'N/A', 'Private', 'None', 'Destroyed', 13),
-- Serenity / Firefly
('Serenity', 'Firefly-class transport', 'Malcolm Reynolds', 5, 'N/A', 'Independent', 'None', 'Active', 14),
-- Event Horizon
('Event Horizon', 'Experimental Ship', 'Miller', 18, 'Faster-than-light', 'Private', 'None', 'Lost', 15),
-- Dune
('Heighliner', 'Guild Ship', 'Unknown', 10000, 'N/A', 'Spacing Guild', 'None', 'Active', 16),
('Ornithopter', 'Personal Craft', 'Duke Leto', 2, 'N/A', 'House Atreides', 'None', 'Active', 16),
-- Independence Day
('City Destroyer', 'Mothership-class', 'Unknown', 100000, 'N/A', 'Aliens', 'Energy Beam', 'Destroyed', 17),
-- Spaceballs
('Spaceball One', 'Mega Maid', 'Dark Helmet', 50, 'Ludicrous Speed', 'Spaceballs', 'Laser Cannons', 'Destroyed', 19),
-- Avatar
('Venture Star', 'ISV Venture Star', 'None', 25, '0.7c', 'RDA', 'None', 'Active', 20),
-- Star Trek Series
('USS Enterprise-D', 'Galaxy-class', 'Jean-Luc Picard', 1012, 'Warp 9', 'Federation', 'Phasers, Photon Torpedoes', 'Active', 22),
('USS Defiant', 'Defiant-class', 'Benjamin Sisko', 50, 'Warp 9.5', 'Federation', 'Phasers', 'Active', 23),
('USS Voyager', 'Intrepid-class', 'Kathryn Janeway', 150, 'Warp 9.975', 'Federation', 'Phasers', 'Active', 24),
-- Battlestar Galactica
('Battlestar Galactica', 'Battlestar-class', 'William Adama', 5000, 'Sublight', 'Colonial Fleet', 'Kinetic Weapons', 'Active', 25),
('Pegasus', 'Mercury-class Battlestar', 'Helena Cain', 2500, 'Sublight', 'Colonial Fleet', 'Kinetic Weapons', 'Destroyed', 25),
-- The Expanse
('Rocinante', 'Corvette-class', 'James Holden', 4, '0.3c', 'OPA', 'Railguns', 'Active', 27),
-- Farscape
('Moya', 'Leviathan-class', 'Pilot', 30, 'Faster-than-light', 'Independent', 'None', 'Active', 28),
-- Lost in Space
('Jupiter 2', 'Explorer-class', 'John Robinson', 6, 'N/A', 'Colonists', 'None', 'Active', 29),
-- The Mandalorian
('Razor Crest', 'ST-70 Assault Ship', 'Din Djarin', 2, 'Unknown', 'Bounty Hunter', 'Laser Cannons', 'Destroyed', 30),
('N-1 Starfighter', 'Modified N-1', 'Din Djarin', 1, 'Unknown', 'Bounty Hunter', 'Laser Cannons', 'Active', 30),
-- The Orville
('USS Orville', 'Exploratory-class', 'Ed Mercer', 300, 'Warp 9.7', 'Planetary Union', 'Plasma Cannons', 'Active', 31),
-- Cowboy Bebop
('Bebop', 'Fishing Trawler', 'Spike Spiegel', 4, 'Sublight', 'Bounty Hunters', 'None', 'Active', 32);