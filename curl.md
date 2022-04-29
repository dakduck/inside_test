Запросы curl
---

### Вход в приложение

> POST auth/login

curl -i -X POST -H "Content-Type:application/json" -H "Content-Length:33" -H "Host:localhost" http://localhost:8080/auth/login -d "{\"name\":\"user\",\"password\":\"user\"}"

В ответе получим токен в виде **Bearer_ТОКЕН**

---

*Далее необходимо в запросы вставить полученный ранее токен*

### Создание новых сообщений

> POST messages/add

- Добавим первое сообщение пользователя:

curl -i -X POST -H "Content-Type:application/json" -H "Content-Length:40" -H "Authorization:Bearer ТОКЕН" -H "Host:localhost" http://localhost:8080/messages/add -d "{\"name\":\"user\",\"message\":\"Hello world1\"}"

- Добавим второе сообщение пользователя

curl -i -X POST -H "Content-Type:application/json" -H "Content-Length:40" -H "Authorization:Bearer ТОКЕН" -H "Host:localhost" http://localhost:8080/messages/add -d "{\"name\":\"user\",\"message\":\"Hello world2\"}"

- Добавим третье сообщение пользователя

curl -i -X POST -H "Content-Type:application/json" -H "Content-Length:40" -H "Authorization:Bearer ТОКЕН" -H "Host:localhost" http://localhost:8080/messages/add -d "{\"name\":\"user\",\"message\":\"Hello world3\"}"

---

### Получение последних n сообщений пользователя

> GET messages/history?count=n

curl -i -X GET -H "accept: application/json" -H "Authorization:Bearer ТОКЕН" -H "Host:localhost" http://localhost:8080/messages/history?count=2

В ответе получим два последних сообщения пользователя

---