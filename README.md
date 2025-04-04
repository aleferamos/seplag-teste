# 📘 Seplag Teste

Projeto Spring Boot para gestão de servidores efetivos e temporários, com integração de autenticação JWT, armazenamento de fotos via MinIO e banco de dados PostgreSQL.

---

## 🛠 Tecnologias Utilizadas

- ☕ **Java 23** + **Spring Boot**
- 🔐 **Spring Security** (JWT)
- 🐳 **Docker** + Docker Compose
- 🐘 **PostgreSQL**
- 🗂 **MinIO** (Armazenamento de arquivos)
- 🧪 **Swagger UI** (Documentação de API)

---

## 🚀 Como Executar o Projeto

### ✅ Pré-requisitos

- [Instalar Docker](https://www.docker.com)
- [Instalar Java/OpenJDK 23](https://www.oracle.com/br/java/technologies/downloads/#java23)
- (Opcional) [DBeaver - cliente de banco recomendado](https://dbeaver.io)

---

### 📦 Subindo os containers com Docker

1. Clone o repositório:
   git clone https://github.com/seu-user/seplag-teste
   cd seplag-teste/scripts

2. Suba os containers:
   docker-compose up -d

   Saída esperada:
   [+] Running 5/5
    ✔ Network scripts_default      Created
    ✔ Volume "scripts_pgdata"      Created
    ✔ Volume "scripts_minio_data"  Created
    ✔ Container postgres           Started
    ✔ Container minio              Started

---

### 🗄 Banco de Dados

- **Host:** localhost
- **Porta:** 5432
- **Banco:** meubanco
- **Usuário:** admin
- **Senha:** admin123

---

### 🧱 MinIO (Armazenamento)

- **Painel:** http://localhost:9001
- **Access Key:** admin
- **Secret Key:** admin123

---

## ☕ Compilando o projeto

1. Verifique se a variável de ambiente JAVA_HOME está apontando para seu JDK 23:
   JAVA_HOME=C:\Program Files\Java\jdk-23
   Path += C:\Program Files\Java\jdk-23\bin

2. Compile o projeto:
   ./mvnw clean package -DskipTests

3. Execute o .jar:
   java -jar ./target/seplag-api-teste-0.0.1-SNAPSHOT.jar

---

## 🧪 Acessando o Swagger

- URL: http://localhost:8080/seplag-api/swagger-ui/index.html

---

## 🔐 Autenticação

1. Faça login com o usuário padrão:
   POST /auth/login
   {
     "email": "seplag@email.com",
     "senha": "admin123"
   }

2. Copie o token JWT da resposta e clique em Authorize no Swagger para colar o token.

---

## 📷 Upload de Fotos

- Endpoint: POST /endpoints-edital/upload-fotos
- Deve ser usado após cadastrar a pessoa no sistema.

---

## 🧑 Cadastro de Servidor Efetivo

1. Faça login para obter o token.
2. Utilize o endpoint:
   POST /servidor-efetivo/salvar

---

## 🏢 Cadastro de Unidade

1. Execute o SQL para cadastrar a cidade:
   INSERT INTO gestor_servidor.cidade (cid_id, cid_nome, cid_uf) VALUES (1, 'Cuiabá', 'MT');

2. Cadastrar a unidade:
   POST /unidade/salvar
   {
     "nome": "Seplag",
     "sigla": "SG",
     "endereco": {
       "tipoLogradouro": "Rua",
       "logradouro": "Bloco III - Complexo Paiaguás, R. C - Centro Político Administrativo, Cuiabá - MT, 78049-005",
       "numero": 1,
       "bairro": "Complexo Paiaguás",
       "cidade": { "id": 1 }
     }
   }

---

## 🧍 Lotação de Servidor

POST /lotacao/salvar
{
  "pessoa": { "id": 1 },
  "unidade": { "id": 1 },
  "dataLotacao": "2025-04-04",
  "dataRemocao": "2025-04-04",
  "portaria": "123"
}

---

## 🔄 Refresh Token

Se o token JWT expirar (5 minutos), use o refreshToken no endpoint:

POST /auth/refresh
{
  "refreshToken": "seu_refresh_token_aqui"
}

---

## 🔎 Buscar Endereço por Nome de Servidor

GET /endpoints-edital/consultar-endereco-unidade-servidor-efetivo?nome=al

Retorna o endereço da unidade onde o servidor está lotado.

---

## ✅ Exemplo de Resposta Final

{
  "idPessoa": 1,
  "nome": "Alefe Patrick",
  "idade": 28,
  "unidadeLotacao": {
    "id": 1,
    "nome": "Seplag",
    "sigla": "SG",
    "endereco": {
      "id": 1,
      "tipoLogradouro": "Rua",
      "logradouro": "Bloco III - Complexo Paiaguás",
      "numero": 1,
      "bairro": "Complexo Paiaguás",
      "cidade": {
        "id": 1,
        "nome": "Cuiabá",
        "uf": "MT"
      }
    }
  },
  "linksFotosTemporarias": [
    "http://localhost:9000/fotos-pessoa/1/hash_da_foto.png?...assinatura..."
  ]
}

---

## 👏 Conclusão

O projeto está pronto para testes. Explore os endpoints, cadastre novos servidores, lote-os em unidades e gerencie as fotos associadas.

Boa sorte no processo seletivo! 🚀

---

> Desenvolvido por **Alefe Patrick Dias Ramos / Equipe Seplag Teste**
