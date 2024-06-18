Инструкция по запуску проекта

Инструкция по созданию базы данных:
1. Установка PostgreSQL.
2. Создание базы данных.
3. Создание таблиц:

   а. book: CREATE TABLE book ( id BIGINT PRIMARY KEY, isbn VARCHAR(255) NOT NULL, title VARCHAR(255) NOT NULL, genre VARCHAR(255) NOT NULL, description TEXT, author VARCHAR(255) NOT NULL);
   
   б. library_book: CREATE TABLE library_record ( id BIGINT PRIMARY KEY, book_id BIGINT NOT NULL, borrowed_at TIMESTAMP NOT NULL, due_at TIMESTAMP, returned_at TIMESTAMP, FOREIGN KEY (book_id) REFERENCES book(id) );

Запуск проекта:
1. Переход в папку проекта.
2. Настройка файла application.properties для того, чтобы подключить базу данных из PostgreSQL.
3. Запуск проекта: mvn spring-boot:run

Переход на документацию Swagger UI доступен по следующей ссылке: http://localhost:8080/swagger-ui.html 
 
