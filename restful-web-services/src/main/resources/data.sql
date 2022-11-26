/*
 * diese datei MUSS so heissen! (data.sql)...
 */


insert into user_details(id, birth_date, name)
values (10001, current_date() /*function in h2*/, 'ai2045');

/*Error beim saven:
 * Caused by: org.h2.jdbc.JdbcSQLSyntaxErrorException: 
 * Tabelle "USER_DETAILS" nicht gefunden (diese Datenbank ist leer)
 * 
 * =>
 * 
 * So the data.sql is getting executed BEFORE our tables are constructed in H2 database.
 * => we want to delay that
 * =>use a property
* 						"spring.jpa.defer-datasource-initialization=true".
 * 
 */

insert into user_details(id, birth_date, name)
values (10002, current_date(), 'ai1045');

insert into user_details(id, birth_date, name)
values (10003, current_date(), 'ai1071');