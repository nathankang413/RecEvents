-- run this in mySQL to create the views

CREATE OR REPLACE VIEW AvailableEvents AS
SELECT E.id AS event_id
    , C.name AS class_name
    , C.price AS price
    , E.room AS room
    , E.start_time AS start_time
    , E.end_time AS end_time
    , I.full_name AS instructor_name
FROM Events AS E
JOIN Classes AS C
    ON E.class_id = C.id
JOIN Instructors AS I
    ON C.instructor_id = I.id
WHERE E.seats > (
    SELECT COUNT(*)
    FROM Signups S
    WHERE S.event_id = E.id
    )
;

CREATE OR REPLACE VIEW EventSignups AS
SELECT E.id AS event_id
    , E.seats AS seats
    , COUNT(S.id) AS signups
FROM Events AS E
LEFT JOIN Signups AS S
    ON E.id = S.event_id
GROUP BY E.id, E.seats
;

CREATE OR REPLACE VIEW StudentSignups AS
SELECT S.student_id AS student_id
    , E.id AS event_id
    , C.name AS class_name
    , E.room AS room
    , E.start_time AS start_time
    , E.end_time AS end_time
    , I.full_name as instructor_name
FROM Signups AS S
JOIN Events AS E
    ON S.event_id = E.id
JOIN Classes AS C
    ON E.class_id = C.id
JOIN Instructors AS I
    ON C.instructor_id = I.id
;

CREATE OR REPLACE VIEW InstructorEventsView AS
SELECT C.instructor_id as instructor_id
    , E.id as event_id
    , C.name as class_name
    , E.room as room
    , E.start_time as start_time
    , E.end_time as end_time
    , COUNT(S.id) as signups
    , E.seats as seats
FROM Events AS E
JOIN Classes AS C
    ON E.class_id = C.id
LEFT JOIN Signups AS S
    ON E.id = S.event_id
GROUP BY C.instructor_id, E.id, C.name, E.room, E.start_time, E.end_time, E.seats
;