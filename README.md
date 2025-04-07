## Dados da Inscrição:

- Inscrição: 9381
- Data da Inscrição: 03/04/2025 22:28:59
- Nome: ALEFE PATRICK DIAS RAMOS
- E-mail: alefepdias@gmail.com
- CPF: 057.118.301-80
- RG: 26386003
- Celular: (65) 99303-8547

# 📘 Seplag Teste

Projeto desenvolvido para gerenciar servidores efetivos e temporários com autenticação JWT, upload de fotos no MinIO e persistência em banco PostgreSQL.

---

## 🛠 Tecnologias utilizadas

- ☕ Java (Spring Boot)
- 🔐 Spring Security com JWT
- 🐳 Docker & Docker Compose
- 🐘 PostgreSQL
- 🗂 MinIO

---

## 🚀 Instruções para execução

- Verifique se a máquina possui o Docker instalado. Caso não, instale em: https://www.docker.com

- Clone este projeto. Após a clonagem, entre na pasta `scripts`:

```bash
cd seplag-teste/scripts
docker-compose up -d
```

### Saída esperada:

```bash
[+] Running 5/5
 ✔ Network scripts_default      Created
 ✔ Volume "scripts_pgdata"      Created
 ✔ Volume "scripts_minio_data"  Created
 ✔ Container minio              Started
 ✔ Container postgres           Started
```

- O banco de dados e o MinIO estarão prontos para uso, com usuário e senha configurados.

- Conecte-se ao banco com os dados abaixo (ex: via DBeaver: https://dbeaver.io):

```bash
Host: localhost
Banco de dados: meubanco
Usuário: admin
Senha: admin123
```

- Acesse o painel do MinIO em: http://localhost:9001

```bash
Username: admin
Password: admin123
```

- Instale o Java JDK 23: https://www.oracle.com/br/java/technologies/downloads/#java23

- Após a instalação, configure a variável de ambiente:

```bash
JAVA_HOME=C:\Program Files\Java\jdk-23
Path += C:\Program Files\Java\jdk-23\bin
```

![JAVA_HOME](https://github.com/user-attachments/assets/59a2a0e4-52c1-42a6-abcf-fa94cc32ca9f)

- Volte para a raiz do projeto e compile com Maven:

```bash
cd ..
./mvnw clean package -DskipTests
```

### Saída esperada:
```bash
[INFO] --- jar:3.4.2:jar (default-jar) @ seplag-api-teste ---
[INFO] BUILD SUCCESS
```

- Rode a aplicação:

```bash
java -jar ./target/seplag-api-teste-0.0.1-SNAPSHOT.jar
```

### Saída esperada (resumo):
```bash
Tomcat started on port 8080 (http) with context path '/seplag-api'
Started SeplagApiTesteApplication
```

- Acesse o Swagger em: http://localhost:8080/seplag-api/swagger-ui/index.html

![Swagger](https://github.com/user-attachments/assets/628ff20d-559f-404d-b4cd-613341435eeb)

---

## 🧪 Conteúdo Swagger

Endpoints principais:

- `POST /auth/login`: autenticação
- `POST /servidor-efetivo/salvar`: cadastro de servidor
- `POST /endpoints-edital/upload-fotos`: upload de fotografias

---

### 🧾 Exemplo de login:

```bash
{
  "email": "seplag@email.com",
  "senha": "admin123"
}
```

![Login](https://github.com/user-attachments/assets/ec7e5a8f-6094-4fa7-a484-c121b0d772ac)

Resposta esperada:
```bash
{
  "token": "seu-token-jwt",
  "expiresIn": 300000,
  "refreshToken": "seu-refresh-token"
}
```

Cole o token no botão **Authorize** do Swagger.

![Authorize](https://github.com/user-attachments/assets/361f25fc-71ec-4cb3-aed3-d4da61b1555c)

---

## 👤 Cadastro de pessoa e envio de foto

1. `POST /servidor-efetivo/salvar`

![Salvar Pessoa](https://github.com/user-attachments/assets/085d81fe-139e-47f3-9d1d-21cb29b86336)

2. `POST /endpoints-edital/upload-fotos`

![Upload Foto](https://github.com/user-attachments/assets/73b8cfdc-265c-4f87-8777-d746bac48478)

Caso o token expire:

```bash
{
  "error": "Sessão expirada. Favor fazer login novamente."
}
```

Use o endpoint de **refresh**:

```bash
{
  "refreshToken": "seu-refresh-token"
}
```

![Refresh](https://github.com/user-attachments/assets/77c12457-666e-4849-b8a0-63a75c92d1e5)

---

## 🏢 Cadastro de unidade e lotação

1. Inserir cidade:
```bash
INSERT INTO gestor_servidor.cidade (cid_id, cid_nome, cid_uf) VALUES (1, 'Cuiabá', 'MT');
```

2. Cadastrar unidade:
```bash
POST /unidade/salvar

{
  "nome": "Seplag",
  "sigla": "SG",
  "endereco": {
    "tipoLogradouro": "Rua",
    "logradouro": "Bloco III - Complexo Paiaguás...",
    "numero": 1,
    "bairro": "Complexo Paiaguás",
    "cidade": { "id": 1 }
  }
}
```

3. Lotar servidor:
```bash
POST /lotacao/salvar

{
  "pessoa": { "id": 1 },
  "unidade": { "id": 1 },
  "dataLotacao": "2025-04-04",
  "dataRemocao": "2025-04-04",
  "portaria": "123"
}
```

---

## 🔍 Consultar servidor efetivo

`GET /endpoints-edital/consultar-servidores-efetivos/1`

Retorna:

```bash
{
  "idPessoa": 1,
  "nome": "Alefe Patrick",
  ...
  "linksFotosTemporarias": [
    "http://localhost:9000/fotos-pessoa/1/seu-hash.png?...assinatura..."
  ]
}
```

Acesse a URL para visualizar a imagem (expira em 5 minutos).

---

## 📍 Consultar endereço por nome

`GET /endpoints-edital/consultar-endereco-unidade-servidor-efetivo?nome=al`

```bash
[
  {
    "id": 1,
    "tipoLogradouro": "Rua",
    "logradouro": "Bloco III - Complexo Paiaguás...",
    "cidade": {
      "nome": "Cuiabá",
      "uf": "MT"
    }
  }
]
```

---

## ✅ Final

Você pode cadastrar novas pessoas, lotá-las, enviar fotos e visualizar os dados via Swagger.

Parabéns! Projeto rodando com sucesso! 🚀
