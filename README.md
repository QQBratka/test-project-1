Описание тестового задания для кандидатов, 
которые не могут предоставить похожие примеры 
собственных работ

* Описание: реализация REST сервиса (JSON HTTP API)
* Средства разработки: Java (Java EE, JAX-RS, CDI)
* База данных: Реляционная (PostgreSQL), 
работа с БД через JDBC (SQL)

###Требуется реализовать:
* классы для данных
* классы для работы с БД
* контроллер и сервис добавления, обновления и 
удаления данных клиента в БД

###Основной функционал сервиса (запросы):
1) Добавление клиента:
Сервис получает фио и номер телефона клиента (основной)
Выполняет проверку валидности полей - при ошибке 
возвращает 400-й статус
Выполняет поиск клиента в таблице. Если найден - 
возвращает client_id, 
если нет - сохраняет данные и возвращает client_id.

2) Добавление/обновление информации о клиенте:
Сервис получает client_id, персональную информацию, 
дополнительный номер телефона
Выполняет проверку валидности полей - при ошибке 
возвращает 400-й статус
Если информация есть в БД, активна и не отличается 
от полученной - только возвращает 
текущую персональную информацию и телефоны. 
Иначе - сохраняет полученную информацию 
(деактивируя предыдущую версию) и возвращает 
обновленную персональную информацию и телефоны.

3) Получение информации о клиенте:
Сервис получает client_id
Возвращает найденную в БД персональную информацию 
и телефоны активного клиента, 
если деактивирован - ошибка 400

4) Удаление клиента:
Сервис получает client_id
Выполняет поиск активного клиента в таблице. 
Если найден - деактивирует записи. 
Если нет - ошибка.

###Дополнительно:
- Basic авторизация для всех запросов (логин/пароль читать 
из файла config.properties)  
- Примечание: реализация через фильтр
- Логирование работы сервиса (ошибки и успешные операции)
- Описать ограничения для таблиц БД
- Преобразование объектов данных в ответы сервиса


###Предлагаемая структура данных БД:
* Клиенты:
client_id - ключ
created - дата/время создания
deleted - дата/время удаления

* Номера телефонов:
id - ключ
client_id
номер телефона
type_id - тип телефона (1 - основной, 2 - дополнительный)
created - дата/время создания
deleted - дата/время удаления

* Персональная информация:
id - ключ
client_id
фио
паспорт
дата рождения
created - дата/время создания
deleted - дата/время удаления
