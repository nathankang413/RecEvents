-- run this in mySQL to create the views

CREATE VIEW AvailableEvents AS
SELECT E.id AS event_id
    , C.name AS class_name
    , C.price AS price
    , E.room AS room
    , E.start_time AS start_time
    , E.end_time AS end_time
    , I.full_name AS instructor_name
FROM Events E
JOIN Classes C
    ON E.class_id = C.id
JOIN Instructors I
    ON C.instructor_id = I.id
WHERE E.seats > (
    SELECT COUNT(*)
    FROM Signups S
    WHERE S.event_id = E.id
    )
;
