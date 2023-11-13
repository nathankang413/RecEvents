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

CREATE OR REPLACE VIEW StudentSignups AS
SELECT S.id AS student_id
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