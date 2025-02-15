# 🎯 Projeto: Sistema de Escola (Spring Boot + MySQL)

Este projeto é um sistema de gerenciamento escolar desenvolvido com **Spring Boot**, **MySQL**.

---

## 📌 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.2**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **Postman (para testes de API)**

---

## ⚙️ Configuração do Ambiente

### 🔹 **1️⃣ Clonando o Repositório**

Para clonar o repositório corretamente, dependendo da conta usada:

🔹 **Para GitHub pessoal:**
```sh
 git clone git@github.com-pessoal:seu-usuario/seu-repositorio.git
```


## 🚀 Configuração do Projeto Spring Boot

### 🔹 **2️⃣ Configurando o Banco de Dados (MySQL)**

Foi criado as tabelas no **MySQL** automaticamente pelo Hibernate.

#### **Configuração do `application.properties`**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sistema_escola
spring.datasource.username=exemple
spring.datasource.password=exemple
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

### 🔹 **3️⃣ Entidades Criadas**

O projeto tem as seguintes **entidades**:

- **Aluno**
- **Professor**
- **Curso**
- **Turma**

#### **Correção de Loops Infinitos no JSON**
Adicionamos `@JsonIgnore` para evitar referência circular ao fazer `findAll()`

```java
@ManyToOne
@JoinColumn(name = "turma_id")
@JsonIgnore  // Evita loop infinito ao serializar JSON
private Turma turma;
```

---

### 🔹 **4️⃣ Controllers**

Foi criado as  **Controllers REST** para `Aluno`, `Professor`, `Curso` e `Turma`.
Cada controller possui endpoints para **CRUD** completo.

```java
@RestController
@RequestMapping("/api/aluno")
public class AlunoController {
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Aluno aluno) {
        return new ResponseEntity<>("Aluno salvo!", HttpStatus.OK);
    }
}
```

---

### 🔹 **5️⃣ Testei Tudo no Postman**

#### **Rotas Testadas**

- **Curso**
  - `POST /api/curso/save`
  - `GET /api/curso/findById/{id}`
  - `PUT /api/curso/update/{id}`
  - `DELETE /api/curso/delete/{id}`

- **Aluno**
  - `POST /api/aluno/save`
  - `GET /api/aluno/findById/{id}`
  - `PUT /api/aluno/update/{id}`
  - `DELETE /api/aluno/delete/{id}`

- **Professor**
  - `POST /api/professor/save`
  - `GET /api/professor/findById/{id}`
  - `PUT /api/professor/update/{id}`
  - `DELETE /api/professor/delete/{id}`

- **Turma**
  - `POST /api/turma/save`
  - `GET /api/turma/findById/{id}`
  - `PUT /api/turma/update/{id}`
  - `DELETE /api/turma/delete/{id}`

---

## 🛠 Próximos Passos

🔹 Melhorar a validação dos dados (sem `@NotBlank`, mas usando lógica própria).  
🔹 Melhorar os relacionamentos para evitar inconsistências.  
🔹 Criar testes unitários para garantir a qualidade do código.  
🔹 Implementar autenticação (JWT).  

---

## 📌 Conclusão
Este projeto está totalmente funcional e configurado com  **Spring Boot**, **MySQL** e **CRUD completo via API REST**.


