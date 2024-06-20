DELETE FROM reservation;
DELETE FROM reservation_time;
ALTER TABLE reservation ALTER COLUMN id RESTART;
ALTER TABLE reservation_time ALTER COLUMN id RESTART;

INSERT INTO reservation_time(start_at)
VALUES ('10:00:00'),
       ('19:00:00'),
       ('21:00:00');

INSERT INTO reservation(name, date, time_id)
VALUES ('브라운', CURRENT_DATE - 1, 1),
       ('브리', CURRENT_DATE - 2, 1),
       ('오리', CURRENT_DATE - 2, 2),
       ('브라운', '2022-05-05', 2),
       ('브라운', '2050-05-05', 2);

