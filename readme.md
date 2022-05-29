# test RestApi project

Подсистема выполняет следующие функции:
1. Получение списка товаров
2. Получение конкретного товара
3. Изменение объекта
4. Вставка объекта
5. Удаление объекта

|                 **Название функции**                 | **URL API функции**                   | **Параметры**                      | **Тело запроса** | **Метод** |
|:----------------------------------------------------:|---------------------------------------|------------------------------------|:----------------:|-----------|
|               Получить список товаров                | http://localhost:8080/all             | productName, propTypes             |        -         | GET       |
|             Получение конкретного товара             | http://localhost:8080/{id}            | {id}                               |        -         | GET       |
|                    Вставка товара                    | http://localhost:8080/product         | -                                  |    newProduct    | POST      |
|                   Удаление товара                    | http://localhost:8080/delete/{id}     | {id}                               |        -         | DELETE    |
|                   Изменение товара                   | http://localhost:8080/patchProduct    | id, name                           |        -         | PATCH     |
|                Вставка характеристики                | http://localhost:8080/property        | product_id, type, value            |        -         | POST      |
|               Удаление характеристики                | http://localhost:8080/deleteProp/{id} | {id}                               |        -         | DELETE    |
|               Изменение характеристики               | http://localhost:8080/patchProperty   | id, product_id, type, value        |        -         | PATCH     |

Примеры:

### Добавление товара
```
http://localhost:8080/product
```
Request body:
```json
{
  "name": "tesla model s",
  "properties": [
    {
      "type": "price",
      "value": "100000"
    },
    {
      "type": "country",
      "value": "USA"
    },
    {
      "type": "weight",
      "value": "2000"
    }
  ]
}
```
Response:
```json
{
    "id": 1,
    "name": "tesla model s",
    "price": {
        "id": 1,
        "product": 1,
        "type": "price",
        "value": "100000"
    },
    "country": {
        "id": 2,
        "product": 1,
        "type": "country",
        "value": "USA"
    }
}
```

### Добавление характеристики
```
http://localhost:8080/property?product_id=1&type=owner&value=Ilon Mask
```
```json
{
  "id": 37,
  "product": 1,
  "type": "owner",
  "value": "Ilon Mask"
}
```

### Получение списка товаров

```
http://localhost:8080/all?productName
```

```json
[
  {
    "id": 2,
    "name": "algorithm book",
    "price": {
      "id": 4,
      "product": 2,
      "type": "price",
      "value": "10"
    },
    "country": {
      "id": 5,
      "product": 2,
      "type": "country",
      "value": "USA"
    }
  },
  {
    "id": 12,
    "name": "chicken",
    "price": {
      "id": 34,
      "product": 12,
      "type": "price",
      "value": "1"
    },
    "country": {
      "id": 35,
      "product": 12,
      "type": "country",
      "value": "Russia"
    }
  },
  {
    "id": 3,
    "name": "coca cola",
    "price": {
      "id": 7,
      "product": 3,
      "type": "price",
      "value": "5"
    },
    "country": {
      "id": 8,
      "product": 3,
      "type": "country",
      "value": "USA"
    }
  },
  {
    "id": 7,
    "name": "coffee",
    "price": {
      "id": 19,
      "product": 7,
      "type": "price",
      "value": "10"
    },
    "country": {
      "id": 20,
      "product": 7,
      "type": "country",
      "value": "Zimbabve"
    }
  },
  {
    "id": 4,
    "name": "jeans",
    "price": {
      "id": 10,
      "product": 4,
      "type": "price",
      "value": "15"
    },
    "country": {
      "id": 11,
      "product": 4,
      "type": "country",
      "value": "Japan"
    }
  },
  {
    "id": 8,
    "name": "laptop",
    "price": {
      "id": 22,
      "product": 8,
      "type": "price",
      "value": "1000"
    },
    "country": {
      "id": 23,
      "product": 8,
      "type": "country",
      "value": "China"
    }
  },
  {
    "id": 10,
    "name": "playstation 4",
    "price": {
      "id": 28,
      "product": 10,
      "type": "price",
      "value": "500"
    },
    "country": {
      "id": 29,
      "product": 10,
      "type": "country",
      "value": "Japan"
    }
  },
  {
    "id": 9,
    "name": "playstation 5",
    "price": {
      "id": 25,
      "product": 9,
      "type": "price",
      "value": "1000"
    },
    "country": {
      "id": 26,
      "product": 9,
      "type": "country",
      "value": "Japan"
    }
  },
  {
    "id": 6,
    "name": "pop corn",
    "price": {
      "id": 16,
      "product": 6,
      "type": "price",
      "value": "6"
    },
    "country": {
      "id": 17,
      "product": 6,
      "type": "country",
      "value": "Africa"
    }
  },
  {
    "id": 5,
    "name": "rock guitar",
    "price": {
      "id": 13,
      "product": 5,
      "type": "price",
      "value": "1500"
    },
    "country": {
      "id": 14,
      "product": 5,
      "type": "country",
      "value": "German"
    }
  }
]
```

### Постраничный вывод продуктов

```
http://localhost:8080/all?productName&page=1
```
```json
[
  {
    "id": 1,
    "name": "tesla model s",
    "price": {
      "id": 1,
      "product": 1,
      "type": "price",
      "value": "100000"
    },
    "country": {
      "id": 2,
      "product": 1,
      "type": "country",
      "value": "USA"
    }
  },
  {
    "id": 11,
    "name": "wooden house",
    "price": {
      "id": 31,
      "product": 11,
      "type": "price",
      "value": "50000"
    },
    "country": {
      "id": 32,
      "product": 11,
      "type": "country",
      "value": "Sweden"
    }
  }
]
```

### Фильтр

```
http://localhost:8080/all?productName&page=0&propTypes=producer
```

```json
[
  {
    "id": 7,
    "name": "coffee",
    "price": {
      "id": 19,
      "product": 7,
      "type": "price",
      "value": "10"
    },
    "country": {
      "id": 20,
      "product": 7,
      "type": "country",
      "value": "Zimbabve"
    }
  },
  {
    "id": 8,
    "name": "laptop",
    "price": {
      "id": 22,
      "product": 8,
      "type": "price",
      "value": "1000"
    },
    "country": {
      "id": 23,
      "product": 8,
      "type": "country",
      "value": "China"
    }
  },
  {
    "id": 10,
    "name": "playstation 4",
    "price": {
      "id": 28,
      "product": 10,
      "type": "price",
      "value": "500"
    },
    "country": {
      "id": 29,
      "product": 10,
      "type": "country",
      "value": "Japan"
    }
  },
  {
    "id": 9,
    "name": "playstation 5",
    "price": {
      "id": 25,
      "product": 9,
      "type": "price",
      "value": "1000"
    },
    "country": {
      "id": 26,
      "product": 9,
      "type": "country",
      "value": "Japan"
    }
  }
]
```

### Фильтр по имени и по имеющимся свойствам
```
http://localhost:8080/all?productName=cof&page=0&propTypes=producer
```

```json
[
  {
    "id": 7,
    "name": "coffee",
    "price": {
      "id": 19,
      "product": 7,
      "type": "price",
      "value": "10"
    },
    "country": {
      "id": 20,
      "product": 7,
      "type": "country",
      "value": "Zimbabve"
    }
  }
]
```
### Получение конкретного товара
```
http://localhost:8080/2
```
```json
{
  "name": "algorithm book",
  "id": 2,
  "properties": [
    {
      "id": 4,
      "product": 2,
      "type": "price",
      "value": "10"
    },
    {
      "id": 5,
      "product": 2,
      "type": "country",
      "value": "USA"
    },
    {
      "id": 6,
      "product": 2,
      "type": "author",
      "value": "Aditya Bhargava"
    }
  ]
}
```
### Удаление товара
```
http://localhost:8080/delete/2
```
```
2
```

### Удаление характеристики
```
http://localhost:8080/deleteProp/36
```
```
36
```

### Изменение товара 
```
http://localhost:8080/patchProduct/?id=12&name=newChicken
```
Проверим
```
http://localhost:8080/12
```
```json
{
  "name": "newChicken",
  "id": 12,
  "properties": [
    {
      "id": 34,
      "product": 12,
      "type": "price",
      "value": "1"
    },
    {
      "id": 35,
      "product": 12,
      "type": "country",
      "value": "Russia"
    }
  ]
}
```

### Изменение хараеткристики товара
```
http://localhost:8080/patchProperty?id=3&type=height&value=160
```

Проверим
```
http://localhost:8080/1
```
```json
{
  "name": "tesla model s",
  "id": 1,
  "properties": [
    {
      "id": 1,
      "product": 1,
      "type": "price",
      "value": "100000"
    },
    {
      "id": 2,
      "product": 1,
      "type": "country",
      "value": "USA"
    },
    {
      "id": 3,
      "product": 1,
      "type": "height",
      "value": "160"
    }
  ]
}
```
