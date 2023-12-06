
-- https://www.asi.calpoly.edu/get-active/fitness/courses/

INSERT INTO Instructors (full_name, username, pw_hash) VALUES
    ('Darren Hartono', 'dhartono', 96354),
    ('Carly Burnside', 'cburnside', 96354),
    ('Lucy Brandy-Janowicz', 'lbrandy', 96354),
    ('Stacey Albright', 'salbright', 96354),
    ('Kaden Ashley', 'kashley', 96354),
    ('Lance Sarles', 'lsarles', 96354),
    ('Christopher Schroeter', 'cschroeter', 96354),
    ('Linus Crosby', 'lcrosby', 96354)
;

INSERT INTO Students (full_name, username, pw_hash) VALUES
    ('Nathan Kang', 'nkang', 96354),
    ('Logan Barker', 'lbarker', 96354),
    ('Gabe Hervias', 'ghervias', 96354)
;

INSERT INTO Classes (name, instructor_id, price) VALUES
    ('6-Pack Abs', 1, 10.50),
    ('Aqua Fit', 2, 7.99),
    ('Barre Connect', 3, 0),
    ('Body Pump', 4, 5.25),
    ('Breakway', 5, 3.19),
    ('Contemporary Jazz', 6, 80.87),
    ('Fusion Pilates', 7, 3.35),
    ('Acro Yoga', 8, 6.60)
;

INSERT INTO Events (class_id, room, start_time, end_time, seats) VALUES
    (1, 'Studio 3', '2023-11-08 13:30:00', '2023-11-08 14:00:00', 30),
    (1, 'Studio 3', '2023-11-10 13:30:00', '2023-11-11 14:00:00', 30),
    (2, 'Lap Pool', '2023-11-07 12:15:00', '2023-11-07 13:00:00', 30),
    (2, 'Lap Pool', '2023-11-10 10:30:00', '2023-11-11 11:15:00', 30),
    (3, 'Studio 2', '2023-11-07 07:45:00', '2023-11-07 08:30:00', 20),
    (3, 'Studio 2', '2023-11-08 17:30:00', '2023-11-08 18:15:00', 20),
    (3, 'Studio 2', '2023-11-09 07:45:00', '2023-11-09 08:30:00', 20),
    (3, 'Studio 2', '2023-11-10 10:15:00', '2023-11-10 11:00:00', 20),
    (4, 'Studio 3', '2023-11-06 12:15:00', '2023-11-06 13:15:00', 30),
    (4, 'Studio 3', '2023-11-06 16:15:00', '2023-11-06 19:15:00', 30),
    (4, 'Studio 3', '2023-11-07 16:15:00', '2023-11-07 19:15:00', 30),
    (4, 'Studio 3', '2023-11-08 12:15:00', '2023-11-08 13:15:00', 30),
    (4, 'Studio 3', '2023-11-08 16:15:00', '2023-11-08 19:15:00', 30),
    (4, 'Studio 3', '2023-11-09 16:15:00', '2023-11-09 19:15:00', 30),
    (4, 'Studio 3', '2023-11-10 12:15:00', '2023-11-10 13:15:00', 30),
    (4, 'Studio 3', '2023-11-11 09:00:00', '2023-11-11 09:00:00', 30),
    (5, 'Studio 1', '2023-11-06 09:15:00', '2023-11-06 10:00:00', 15),
    (5, 'Studio 1', '2023-11-06 16:30:00', '2023-11-06 17:15:00', 15),
    (5, 'Studio 1', '2023-11-06 18:15:00', '2023-11-06 19:00:00', 15),
    (5, 'Studio 1', '2023-11-07 09:15:00', '2023-11-07 10:00:00', 15),
    (5, 'Studio 1', '2023-11-07 12:15:00', '2023-11-07 13:00:00', 15),
    (5, 'Studio 1', '2023-11-07 16:30:00', '2023-11-07 17:15:00', 15),
    (5, 'Studio 1', '2023-11-07 18:15:00', '2023-11-07 19:00:00', 15),
    (5, 'Studio 1', '2023-11-08 11:15:00', '2023-11-08 12:00:00', 15),
    (5, 'Studio 1', '2023-11-08 16:30:00', '2023-11-08 17:15:00', 15),
    (5, 'Studio 1', '2023-11-08 18:15:00', '2023-11-08 19:00:00', 15),
    (5, 'Studio 1', '2023-11-09 06:15:00', '2023-11-09 07:30:00', 15),
    (5, 'Studio 1', '2023-11-09 11:15:00', '2023-11-09 12:00:00', 15),
    (5, 'Studio 1', '2023-11-09 16:30:00', '2023-11-09 17:15:00', 15),
    (5, 'Studio 1', '2023-11-10 10:15:00', '2023-11-10 11:00:00', 15),
    (5, 'Studio 1', '2023-11-11 10:15:00', '2023-11-11 11:00:00', 15),
    (6, 'Studio 2', '2023-11-06 17:30:00', '2023-11-06 18:00:00', 20),
    (7, 'Studio 2', '2023-11-06 09:10:00', '2023-11-06 09:55:00', 20),
    (7, 'Studio 2', '2023-11-06 17:15:00', '2023-11-06 18:00:00', 20),
    (7, 'Studio 2', '2023-11-07 10:30:00', '2023-11-07 11:15:00', 20),
    (7, 'Studio 2', '2023-11-08 09:10:00', '2023-11-08 09:55:00', 20),
    (7, 'Studio 2', '2023-11-09 16:10:00', '2023-11-09 16:55:00', 20),
    (7, 'Studio 2', '2023-11-10 09:10:00', '2023-11-10 09:55:00', 20),
    (8, 'Mat Room', '2023-11-12 18:00:00', '2023-11-12 19:30:00', 20)
;

INSERT INTO Signups (student_id, event_id) VALUES

;