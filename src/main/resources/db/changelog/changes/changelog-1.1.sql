--liquibase formatted sql

--changeset juanmanuel:1
--Database: h2
CREATE TABLE powerstats (
  combat INT NOT NULL,
  durability INT NOT NULL,
  intelligence INT NOT NULL,
  power INT NOT NULL,
  speed INT NOT NULL,
  strength INT NOT NULL,
  hero_id INT NOT NULL,
  PRIMARY KEY (hero_id),
  CONSTRAINT fk_powerstats_hero
  FOREIGN KEY (hero_id)
  REFERENCES heroes (id)
  ON DELETE CASCADE
  ON UPDATE CASCADE
)

--changeset juanmanuel:2
INSERT INTO heroes (id, name, description) VALUES (1, 'Batman', 'Batman was created by artist Bob Kane and writer Bill Finger, and debuted in the 27th issue of the comic book Detective Comics on March 30, 1939.');
INSERT INTO heroes (id, name, description) VALUES (2, 'Spider-Man', 'Spider-Man is a superhero created by writer-editor Stan Lee and writer-artist Steve Ditko. He first appeared in the anthology comic book Amazing Fantasy #15 (Aug. 1962) in the Silver Age of Comic Books.');
INSERT INTO heroes (id, name, description) VALUES (3, 'Captain America', 'Captain America is a superhero appearing in American comic books published by Marvel Comics. Created by cartoonists Joe Simon and Jack Kirby, the character first appeared in Captain America Comics #1 (cover dated March 1941) from Timely Comics, a predecessor of Marvel Comics.');

INSERT INTO powerstats (hero_id, intelligence, strength, speed, durability, power, combat) VALUES (1, 72, 30, 85, 43, 52, 68);
INSERT INTO powerstats (hero_id, intelligence, strength, speed, durability, power, combat) VALUES (2, 55, 41, 54, 32, 52, 44);
INSERT INTO powerstats (hero_id, intelligence, strength, speed, durability, power, combat) VALUES (3, 46, 21, 66, 71, 89, 70);
