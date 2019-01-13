INSERT INTO Class(name, weekday, start_hour, start_minute, ampm, period, price) VALUES('MONDAY AFTERNOON', 'MONDAY', 5, 30, 'PM', 1.0, 12);
INSERT INTO Class(name, weekday, start_hour, start_minute, ampm, period, price) VALUES('MONDAY EVENING', 'MONDAY', 7, 00, 'PM', 1.0, 12);
INSERT INTO Class(name, weekday, start_hour, start_minute, ampm, period, price) VALUES('TUESDAY AFTERNOON', 'TUESDAY', 5, 30, 'PM', 1.0, 12);
INSERT INTO Class(name, weekday, start_hour, start_minute, ampm, period, price) VALUES('TUESDAY EVENING', 'TUESDAY', 6, 30, 'PM', 1.5, 18);
INSERT INTO Class(name, weekday, start_hour, start_minute, ampm, period, price) VALUES('WEDNESDAY EVENING', 'WEDNESDAY', 6, 30, 'PM', 1.5, 18);
INSERT INTO Class(name, weekday, start_hour, start_minute, ampm, period, price) VALUES('THURSDAY EVENING', 'THURSDAY', 6, 30, 'PM', 1.5, 18);
INSERT INTO Class(name, weekday, start_hour, start_minute, ampm, period, price) VALUES('FRIDAY AFTERNOON', 'FRIDAY', 4, 30, 'PM', 1.5, 18);
INSERT INTO Class(name, weekday, start_hour, start_minute, ampm, period, price) VALUES('FRIDAY EVENING', 'FRIDAY', 6, 30, 'PM', 1.5, 18);
INSERT INTO Class(name, weekday, start_hour, start_minute, ampm, period, price) VALUES('SATURDAY MORNING', 'SATURDAY', 10, 00, 'PM', 1.0, 12);

INSERT INTO Student(first_name, last_name, clz_id, status, started_date) VALUES('Ethan', 'Cheng', 1, 'ACTIVE', '2018-1-1');
INSERT INTO Student(first_name, last_name, clz_id, status, inactive_date) VALUES('Olivia', 'Cheng', 1, 'INACTIVE', '2018-2-17');

INSERT INTO Student(first_name, last_name, clz_id, status) VALUES('Emily', 'zhang', 2, 'ACTIVE');

INSERT INTO Contactor(email, name, relationship, telephone, wechat) VALUES('fangwang@abc.com', 'Fang Wang', 'mother', '416-777-6600', 'fangwang');
INSERT INTO Contactor(email, name, relationship, telephone, wechat) VALUES('none@abc.com', 'Minchang Wang', 'other', '647-789-6600', 'minchangwang');
INSERT INTO Contactor(email, name, relationship, telephone, wechat) VALUES('LeiZhang@abc.com', 'Lei Zhang', 'father', '416-880-2600', 'leizhang');

INSERT INTO Student_Contactor(Student_id, contactors_id) VALUES(3,1);
INSERT INTO Student_Contactor(Student_id, contactors_id) VALUES(3,2);
INSERT INTO Student_Contactor(Student_id, contactors_id) VALUES(3,3);

INSERT INTO Student(first_name, last_name, clz_id, status) VALUES('磊', '何', 2, 'ACTIVE');

INSERT INTO Contactor(email, name, relationship, telephone, wechat) VALUES('ligwang@abc.com', '王丽', 'mother', '416-820-6600', 'ligwang');
INSERT INTO Contactor(email, name, relationship, telephone, wechat) VALUES('none@abc.com', '王峰', 'other', '647-777-6676', 'fengwang');
INSERT INTO Contactor(email, name, relationship, telephone, wechat) VALUES('lihe@abc.com', '何力', 'father', '416-885-2609', 'lihe');

INSERT INTO Student_Contactor(Student_id, contactors_id) VALUES(4,4);
INSERT INTO Student_Contactor(Student_id, contactors_id) VALUES(4,5);
INSERT INTO Student_Contactor(Student_id, contactors_id) VALUES(4,6);

INSERT INTO Payment (amount, date, discount, latest_payment, times, unit_price, student_id) VALUES(90, '2018-1-1', 0, 'NO', 5, 18, 1);
INSERT INTO Payment (amount, date, discount, latest_payment, times, unit_price, student_id) VALUES(90, '2018-2-12', 0, 'YES', 5, 18, 1);
INSERT INTO Payment (amount, date, discount, latest_payment, times, unit_price, student_id) VALUES(90, '2018-2-12', 0, 'NO', 5, 18, 2);
INSERT INTO Payment (amount, date, discount, latest_payment, times, unit_price, student_id) VALUES(90, '2018-3-12', 0, 'YES', 5, 18, 2);

INSERT INTO Payment (amount, date, discount, latest_payment, times, unit_price, student_id) VALUES(90, '2018-2-10', 0, 'NO', 5, 18, 3);
INSERT INTO Payment (amount, date, discount, latest_payment, times, unit_price, student_id) VALUES(90, '2018-3-18', 0, 'YES', 5, 18, 3);

INSERT INTO Payment (amount, date, discount, latest_payment, times, unit_price, student_id) VALUES(90, '2018-1-17', 0, 'NO', 5, 18, 4);
INSERT INTO Payment (amount, date, discount, latest_payment, times, unit_price, student_id) VALUES(90, '2018-2-28', 0, 'YES', 5, 18, 4);



INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2017-12-12', 'YES', 1, 1);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2017-12-20', 'YES', 1, 1);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-1-10', 'YES', 1, 1);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-1-20', 'NO', 2, 1);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-1-26', 'NO', 2, 1);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-2-1', 'NO', 2, 1);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-2-10', 'NO', 2, 1);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-2-17', 'NO', null, 1);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-3-26', 'NO', null, 1);


INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2017-12-12', 'YES', 3, 2);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2017-12-20', 'YES', 3, 2);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-1-10', 'YES', 3, 2);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-1-20', 'NO', 4, 2);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-1-26', 'NO', 4, 2);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-2-10', 'NO', 4, 2);


INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2017-12-12', 'YES', 5, 3);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2017-12-20', 'YES', 5, 3);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-1-10', 'YES', 5, 3);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-1-20', 'NO', 6, 3);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-1-26', 'NO', 6, 3);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-2-10', 'NO', 6, 3);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-2-17', 'NO', 6, 3);

INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2017-12-12', 'YES', 7, 4);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2017-12-20', 'YES', 7, 4);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-1-10', 'YES', 7, 4);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-3-12', 'NO', 8, 4);



INSERT INTO Student(first_name, last_name, clz_id, status) VALUES('Zoey', 'Wang', 3, 'ACTIVE');
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-1-20', 'NO', null, 5);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-1-26', 'NO', null, 5);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-2-10', 'NO', null, 5);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-1-20', 'NO', null, 5);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-1-26', 'NO', null, 5);
INSERT INTO ClassRecord (comment, date, history_record, payment_id, student_id) VALUES('', '2018-2-10', 'NO', null, 5);