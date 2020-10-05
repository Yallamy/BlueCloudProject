# SIGAD Services
Microserves que compoem os serviços provedores de API para realizar as funções do SIGAD.

Microservice Interessado (mcsInteressado)
-------------------

Swagger:
```
http://localhost:8080//swagger-ui.html

```


Criar um interessado:

**POST http://localhost:8080/api/v1/sigad/interessado/** 

Body:
```
{
    "nome": "Dom Pedro I",
    "cpfCnpj": "63334342212",
    "tipoDocumento": "CPF",
    "email": "dompedro@gmail.com",
    "telefone": "839998888"
}
```

Alterar um interessado:

**PUT http://localhost:8080/api/v1/sigad/interessado/{id}**

Exemplo:
```
http://localhost:8080/api/v1/sigad/interessado/1
```
Body:
```
{
    "nome": "Dom Pedro II",
    "cpfCnpj": "63334342212",
    "tipoDocumento": "CPF",
    "email": "dompedro@gmail.com",
    "telefone": "839998888"
}
```


Recuperar um interessado:

**GET http://localhost:8080/api/v1/sigad/interessado/{id}**

Exemplo:
```
http://localhost:8080/api/v1/sigad/interessado/1
```

Recuperar um interessado por CPF/CNPJ:

**GET http://localhost:8080/api/v1/sigad/interessado/documento/{cpfCnpj}**

Exemplo:
```
http://localhost:8080/api/v1/sigad/interessado/documento/63334342212
```

Deletar um interessado:

**DELETE http://localhost:8080/api/v1/sigad/interessado/{id}**

Exemplo:
```
http://localhost:8080/api/v1/sigad/interessado/1
```

Listar interessados de acordo com os filtros:

**GET http://localhost:8080/api/v1/sigad/interessado/** 

Body - filtros possíveis:
```
{
    "nome": "Dom Pedro I",
    "cpfCnpj": "63334342212",
    "tipoDocumento": "CPF",
    "email": "dompedro@gmail.com",
    "telefone": "839998888",
    "situacao": "Ativo"
}
```

Listar histórico de um interessados:

**GET http://localhost:8080/api/v1/sigad/interessado/{id}/historico/** 

Exemplo:
```
http://localhost:8080/api/v1/sigad/interessado/1/historico/
```

