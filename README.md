*Инструкция по запуску*

1. Запустить infrastructure/docker-compose.yml

2. Или из консоли:
   3. cd infrastructure
   4. docker-compose up --build
   5. настроить realm по localhost:8084/auth

Запустить java -jar GamificationImplApplication

3. keycloak:
   4.  Перейти по http://localhost:8082/auth/admin/master 
   5.  Создать рилм main_realm (слева сверху кнопка)
   6.  Создать юзера с паролем и без флага обновления пароля
   7.  Добавить роли на выбор MANAGER, USER, ADMIN, HR
   8.  Добавить клиент myClient, где
      9. http://localhost:8080/game/* - Valid Redirect URIs 
      10. Base URL и Admin URL - http://localhost:8084