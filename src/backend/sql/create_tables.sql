-- run this in mySQL to create the tables

CREATE TABLE Instructors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    pw_hash VARCHAR(255) NOT NULL
);

CREATE TABLE Students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    pw_hash VARCHAR(255) NOT NULL
);

CREATE TABLE Classes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    instructor_id INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (instructor_id) REFERENCES Instructors(id)
);

CREATE TABLE Events (
    id INT AUTO_INCREMENT PRIMARY KEY,
    class_id INT NOT NULL,
    room VARCHAR(255) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    seats INT NOT NULL,
    FOREIGN KEY (class_id) REFERENCES Classes(id)
);

CREATE TABLE Signups (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    event_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Students(id),
    FOREIGN KEY (event_id) REFERENCES Events(id)
);