#PackBack - Cadastro de Usuários

Serviço para cadastro de usuários para o sistema PackBack.

###Build e Execução

Construir imagem do banco:
```
docker build -t postgres:v0.0.1 db/
```

Executar o banco:
```
docker run --rm --name pg-packback-db -d -p 5432:5432 -v $pwd/db/volume/postgres:/var/lib/postgresql/data postgres:v0.0.1
```

Configurar DataSource e subir o servidor de aplicação.
