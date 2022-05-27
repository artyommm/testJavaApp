# test RestApi project

Подсистема выполняет следующие функций:
1. Получение списка товаров
2. Получение конкретного товара
3. Изменение объекта
4. Вставка объекта
5. Удаление объекта

|                 **Название функции**                 | **URL API функции**                   | **Параметры**                      | **Тело запроса** | **Метод** |
|:----------------------------------------------------:|---------------------------------------|------------------------------------|:----------------:|-----------|
|               Получить список товаров                | http://localhost:8080/all             | -                                  |        -         | GET       |
|         Получить список товаров постранично          | http://localhost:8080/pages           | page                               |        -         | GET       |
| Фильтр товаров по типу, сортировка по имени продукта | http://localhost:8080/filter          | type, ordering                     |        -         | GET       |
|             Получение конкретного товара             | http://localhost:8080/{id}            | {id}                               |        -         | GET       |
|                    Вставка товара                    | http://localhost:8080/product         | name                               |        -         | POST      |
|                   Удаление товара                    | http://localhost:8080/delete/{id}     | {id}                               |        -         | DELETE    |
|                   Изменение товара                   | http://localhost:8080/patchProduct    | id, name                           |        -         | PATCH     |
|                Вставка характеристики                | http://localhost:8080/property        | product_id, type, price, brand     |        -         | POST      |
|               Удаление характеристики                | http://localhost:8080/deleteProp/{id} | {id}                               |        -         | DELETE    |
|               Изменение характеристики               | http://localhost:8080/patchProperty   | id, product_id, type, price, brand |        -         | PATCH     |

Примеры:

### Добавление товара
```
http://localhost:8080/product?name=TV
```
```json
{
    "id": 76,
    "name": "TV"
}
```

### Добавление характеристики
```
http://localhost:8080/property?product_id=76&type=electronics&price=10&brand=samsung
```
```json
{
    "id": 88,
    "product": {
        "id": 76,
        "name": "TV"
    },
    "type": "electronics",
    "brand": "samsung",
    "price": 10.0
}
```

### Получение списка товаров

```
http://localhost:8080/all
```

```json
[
    {
        "id": 76,
        "name": "TV"
    },
    {
        "id": 77,
        "name": "moto"
    },
    {
        "id": 78,
        "name": "auto"
    },
    {
        "id": 79,
        "name": "ps5"
    },
    {
        "id": 80,
        "name": "t-shirt"
    },
    {
        "id": 81,
        "name": "computer"
    },
    {
        "id": 82,
        "name": "umbrella"
    },
    {
        "id": 83,
        "name": "plane"
    },
    {
        "id": 84,
        "name": "tesla model s"
    },
    {
        "id": 85,
        "name": "lada granta"
    },
    {
        "id": 86,
        "name": "harry potter"
    },
    {
        "id": 87,
        "name": "tyumen state university"
    }
]
```

### Постраничный вывод продуктов

```
http://localhost:8080/pages?page=0
```
```json
{
    "content": [
        {
            "id": 87,
            "name": "tyumen state university"
        },
        {
            "id": 86,
            "name": "harry potter"
        },
        {
            "id": 85,
            "name": "lada granta"
        },
        {
            "id": 84,
            "name": "tesla model s"
        },
        {
            "id": 83,
            "name": "plane"
        },
        {
            "id": 82,
            "name": "umbrella"
        },
        {
            "id": 81,
            "name": "computer"
        },
        {
            "id": 80,
            "name": "t-shirt"
        },
        {
            "id": 79,
            "name": "ps5"
        },
        {
            "id": 78,
            "name": "auto"
        }
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "unpaged": false,
        "paged": true
    },
    "last": false,
    "totalPages": 2,
    "totalElements": 12,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 10,
    "empty": false
}
```

### Вторая страница

```
http://localhost:8080/pages?page=1
```

```json
{
    "content": [
        {
            "id": 77,
            "name": "moto"
        },
        {
            "id": 76,
            "name": "TV"
        }
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 10,
        "pageNumber": 1,
        "pageSize": 10,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalPages": 2,
    "totalElements": 12,
    "size": 10,
    "number": 1,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": false,
    "numberOfElements": 2,
    "empty": false
}
```

### Фильтр и сортировка
```
http://localhost:8080/filter?type=clothes&ordering=1
```

```json
[
    {
        "id": 103,
        "product": {
            "id": 102,
            "name": "ab"
        },
        "type": "clothes",
        "brand": "abcd",
        "price": 1.0
    },
    {
        "id": 101,
        "product": {
            "id": 100,
            "name": "abc"
        },
        "type": "clothes",
        "brand": "abcd",
        "price": 1.0
    },
    {
        "id": 92,
        "product": {
            "id": 80,
            "name": "t-shirt"
        },
        "type": "clothes",
        "brand": "h&m",
        "price": 5.0
    },
    {
        "id": 94,
        "product": {
            "id": 82,
            "name": "umbrella"
        },
        "type": "clothes",
        "brand": "moskovskiy trikotazh",
        "price": 5.0
    }
]
```
### Получение конкретного товара
```
http://localhost:8080/84
```
```json
{
    "name": "tesla model s",
    "id": 84,
    "properties": [
        {
            "id": 96,
            "product": {
                "id": 84,
                "name": "tesla model s"
            },
            "type": "automobile",
            "brand": "tesla",
            "price": 5000.0
        },
        {
            "id": 104,
            "product": {
                "id": 84,
                "name": "tesla model s"
            },
            "type": "electrocar",
            "brand": "tesla motors",
            "price": 5000.0
        }
    ]
}
```
### Удаление товара
```
http://localhost:8080/delete/84
```
```
84
```

### Удаление характеристики
```
http://localhost:8080/deleteProp/103
```
```
103
```

### Изменение товара 
```
http://localhost:8080/patchProduct/?id=82&name=newUmbrella3000
```
Проверим
```
http://localhost:8080/82
```
```json
{
  "name": "newUmbrella3000",
  "id": 82,
  "properties": [
    {
      "id": 94,
      "product": {
        "id": 82,
        "name": "newUmbrella3000"
      },
      "type": "clothes2",
      "brand": "noname",
      "price": 777.0
    }
  ]
}
```

### Изменение хараеткристики товара
```
http://localhost:8080/putProperty/?id=94&product_id=82&type=clothes2&price=777&brand=noname
```

Проверим
```
http://localhost:8080/82
```
```json
{
    "name": "umbrella",
    "id": 82,
    "properties": [
        {
            "id": 94,
            "product": {
                "id": 82,
                "name": "umbrella"
            },
            "type": "clothes2",
            "brand": "noname",
            "price": 777.0
        }
    ]
}
```