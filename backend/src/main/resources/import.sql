-- Flughäfen
INSERT INTO airport (id, name, city, country, iata_code) VALUES
                                                             (1, 'Frankfurt Airport', 'Frankfurt', 'Germany', 'FRA'),
                                                             (2, 'Heathrow Airport', 'London', 'UK', 'LHR'),
                                                             (3, 'JFK International', 'New York', 'USA', 'JFK');

-- Flugzeuge
INSERT INTO airplane (id, model, airline, capacity) VALUES
                                                        (1, 'Boeing 747', 'Lufthansa', 366),
                                                        (2, 'Airbus A320', 'British Airways', 180),
                                                        (3, 'Boeing 777', 'American Airlines', 396);

-- Flüge
INSERT INTO flight (id, flight_number, departure_airport, arrival_airport, airplane_id, departure_time, arrival_time)
VALUES
    (5, 'LH100', 1, 2, 1, '2025-04-06 08:00:00', '2025-04-06 10:00:00'),
    (6, 'BA200', 1, 3, 2, '2025-04-06 12:00:00', '2025-04-06 14:30:00'),
    (7, 'AA300', 2, 1, 3, '2025-04-06 16:00:00', '2025-04-06 18:30:00'),
    (8, 'LH400', 2, 3, 1, '2025-04-06 20:00:00', '2025-04-06 22:30:00'),
    (9, 'BA500', 3, 1, 2, '2025-04-07 08:00:00', '2025-04-07 10:30:00'),
    (10, 'AA600', 3, 2, 3, '2025-04-07 12:00:00', '2025-04-07 14:30:00');


-- Crew-Mitglieder
INSERT INTO crew (id, name, role) VALUES
                                      (1, 'John Doe', 'PILOT'),
                                      (2, 'Jane Smith', 'CO_PILOT'),
                                      (3, 'Alice Brown', 'FLIGHT_ATTENDANT'),
                                      (4, 'Bob Johnson', 'FLIGHT_ATTENDANT'),
                                      (5, 'Eva Davis', 'PILOT'),
                                      (6, 'Tom Wilson', 'FLIGHT_ATTENDANT');
