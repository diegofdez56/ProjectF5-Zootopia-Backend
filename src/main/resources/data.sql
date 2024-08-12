-- Insertando datos en la tabla `families`
INSERT INTO families (name) VALUES 
('Felidae'), 
('Canidae'),  
('Reptilia'), 
('Mustelidae'),
('Leporidae'); 

-- Insertando datos en la tabla `types`
INSERT INTO types (name, family_id) VALUES 
('Lion', (SELECT id FROM families WHERE name = 'Felidae')), 
('Tiger', (SELECT id FROM families WHERE name = 'Felidae')),
('Wolf', (SELECT id FROM families WHERE name = 'Canidae')),
('Fox', (SELECT id FROM families WHERE name = 'Canidae')),
('Crocodile', (SELECT id FROM families WHERE name = 'Reptilia')),
('Snake', (SELECT id FROM families WHERE name = 'Reptilia')),
('Otter', (SELECT id FROM families WHERE name = 'Mustelidae')),
('Weasel', (SELECT id FROM families WHERE name = 'Mustelidae')),
('Rabbit', (SELECT id FROM families WHERE name = 'Leporidae')),
('Hare', (SELECT id FROM families WHERE name = 'Leporidae'));

-- -- Insertando datos en la tabla `animals`
INSERT INTO animals (name, type_id, gender, date_of_entry) VALUES 
('Simba', (SELECT id FROM types WHERE name = 'Lion'), 'Male', '2022-01-15'),
('Nala', (SELECT id FROM types WHERE name = 'Lion'), 'Female', '2022-03-10'),
('Shere Khan', (SELECT id FROM types WHERE name = 'Tiger'), 'Male', '2021-07-25'),
('Akela', (SELECT id FROM types WHERE name = 'Wolf'), 'Male', '2020-12-05'),
('Vixen', (SELECT id FROM types WHERE name = 'Fox'), 'Female', '2021-09-19'),
('Nagini', (SELECT id FROM types WHERE name = 'Snake'), 'Female', '2019-05-14'),
('Crocky', (SELECT id FROM types WHERE name = 'Crocodile'), 'Male', '2018-04-22'),
('Pippin', (SELECT id FROM types WHERE name = 'Otter'), 'Female', '2023-02-17'),
('Whiskers', (SELECT id FROM types WHERE name = 'Weasel'), 'Male', '2023-01-05'),
('Thumper', (SELECT id FROM types WHERE name = 'Rabbit'), 'Male', '2022-10-29'),
('Bambi', (SELECT id FROM types WHERE name = 'Hare'), 'Female', '2023-06-12');


-- login
INSERT INTO roles (id_role, name) VALUES (default, 'ROLE_ADMIN');
INSERT INTO users (id_user, username, password) VALUES (default, 'admin', '$2a$12$zMUgGcYGCb2c/vwT9s12Q.380ORJIP0NgN9NmgX6pyEf.bm6fHTiK');
INSERT INTO roles_users (role_id, user_id) VALUES (1, 1);
