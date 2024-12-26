# Задание

* Создайте БД, например, `SchoolDB`.
* В этой БД создайте таблицу Course с полями `id`, `title` и `duration`.
* Настройте `Hibernate` для работы с вашей БД.
* Создайте Java-класс `Course`, соответствующий таблице `Course`, с необходимыми \
аннотациями `Hibernate`.
* Используя `Hibernate`, напишите код для вставки, чтения, обновления и удаления \
данных в таблице `Course`.
* Убедитесь, что каждая операция выполняется в отдельной транзакции.

### Запуск контейнера с БД
```shell
docker run --rm --name school-db -p 5433:5432/tcp -e POSTGRES_PASSWORD=example -e POSTGRES_DB=SchoolDB -d postgres:16-alpine
```

### Подключение к БД
```shell
docker exec -it school-db psql -h localhost -p 5433 -U postgres -d SchoolDB
```
