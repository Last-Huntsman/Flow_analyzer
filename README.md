# 💸 Flow Analyzer

Spring Boot-приложение для импорта финансовых транзакций из CSV, анализа данных и построения отчетов по категориям и месяцам.

---

## 🚀 Возможности

- 📥 Импорт CSV-файлов с транзакциями
- 🔍 Поиск и фильтрация транзакций
- 📊 Отчеты по категориям, месяцам и общая сводка
- 📂 Хранение данных в базе данных (JPA/Hibernate)
- ✅ Валидация данных с подробными ошибками

---

## 🧱 Технологии

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- OpenCSV
- PostgreSQL (или другая поддерживаемая база)
- Swagger (SpringDoc OpenAPI)

---

## 📦 Структура проекта

```
flow_analyzer/
├── controller/          # REST API (CSV, транзакции, отчеты)
├── service/             # Бизнес-логика
├── dao/                 # Работа с базой через EntityManager
├── dto/                 # DTO-классы
├── model/               # JPA-сущности (Transaction, Category)
├── util/                # Утилиты (например, CSV-конвертер)
```

---

## 📝 Формат CSV-файла

Ожидаемый формат (с заголовками):

```csv
operation,date,amount,description,category
EXPENSE,2024-05-01,"1 000,00",Кофейня,Еда
INCOME,2024-05-02,"50 000,00",Зарплата,Доход
```

- `operation` — `EXPENSE` или `INCOME`
- `date` — формат `yyyy-MM-dd`
- `amount` — число с запятой (например, `"1 000,00"`)
- `description` — текст
- `category` — существующая категория (или будет ошибка)

---

## 🛠️ Сборка и запуск

### 📄 Предварительные требования

- JDK 17+
- Maven
- PostgreSQL (или любая другая СУБД)
- (опционально) Docker

### 🔧 Конфигурация

Укажи настройки БД в `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/flowdb
    username: user
    password: pass
```

### ▶️ Запуск

```bash
mvn spring-boot:run
```

или

```bash
java -jar target/flow_analyzer-0.0.1-SNAPSHOT.jar
```

---

## 🧪 API-документация

После запуска доступно по адресу:

```
http://localhost:8080/swagger-ui.html
или
http://localhost:8080/swagger-ui/index.html
```

---

## 📡 Основные эндпоинты

### 📤 Импорт CSV

```
POST /api/csv
```

Form-data:
- `file`: CSV-файл

---

### 🔍 Транзакции

```
GET /api/transactions?page=0&size=20
POST /api/transactions/search
```

JSON-запрос для фильтрации:

```json
{
  "category": "Еда",
  "minAmount": 100.0,
  "maxAmount": 1000.0
}
```

---

### 📈 Отчёты

```
GET /api/report/by-category
GET /api/report/by-month
GET /api/report/summary
```

---

## 📂 Примеры ответа

#### `/api/report/by-category`

```json
[
  { "category": "Еда", "total": 1450.50 },
  { "category": "Доход", "total": 50000.00 }
]
```

#### `/api/report/summary`

```json
{
  "totalIncome": 50000.0,
  "totalExpense": 1450.5,
  "balance": 48549.5
}
```

---

## ⚙️ Возможности расширения

- Автоматическое создание новых категорий при импорте
- Импорт из Excel
- Экспорт отчетов в PDF/Excel
- Авторизация пользователей

---

## 🧑‍💻 Автор

Created by [твоё имя]  
[ссылка на GitHub, почта или Telegram (по желанию)]

---

## 📄 Лицензия

MIT License
